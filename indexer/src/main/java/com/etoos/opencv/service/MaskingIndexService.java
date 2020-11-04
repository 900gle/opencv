package com.etoos.opencv.service;

import com.etoos.opencv.apis.ImageIndexApi;
import com.etoos.opencv.apis.MaskingIndexApi;
import com.etoos.opencv.domain.image.Images;
import com.etoos.opencv.repository.ImagesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.admin.indices.flush.FlushResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaskingIndexService {

    @Autowired
    ImagesRepository imagesRepository;

    private final RestHighLevelClient client;

    String indexName = "masking-images" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE).toString();

    public void staticIndex() {

        try {

            GetIndexRequest requestGetIndex = new GetIndexRequest(indexName);
            boolean existsIndex = client.indices().exists(requestGetIndex, RequestOptions.DEFAULT);

            GetAliasesRequest aliasesRequest = new GetAliasesRequest("masking");
            GetAliasesResponse getAliasesResponse = client.indices().getAlias(aliasesRequest, RequestOptions.DEFAULT);

            String oldIndexName = "";
            if (getAliasesResponse.getAliases().size() > 0) {
                oldIndexName = Optional.ofNullable(getAliasesResponse.getAliases().keySet().iterator().next()).orElse("");
            }

            if (existsIndex == false) {
                CreateIndexRequest request = MaskingIndexApi.createIndex(indexName);
                CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            } else {

                DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
                AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

                if (deleteIndexResponse.isAcknowledged()) {
                    CreateIndexRequest request = MaskingIndexApi.createIndex(indexName);
                    CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
                }
            }



       List<Images> imagesList = imagesRepository.findAll();


            System.out.println("size :: " + imagesList.size());

            BulkRequest bulkRequest = new BulkRequest();
            imagesList.forEach(
                    x -> {
                        try {
                            XContentBuilder builder = XContentFactory.jsonBuilder();
                            builder.startObject();
                            {
                                builder.field("id", x.getId());
                                builder.field("image_name", x.getImageName());
                                builder.field("image_path", x.getImagePath());
                                builder.field("original_image_name", x.getOriginalImageName());
                                builder.field("created_date", x.getCreatedDate());
                                builder.field("modified_date", x.getModifiedDate());
                            }
                            builder.endObject();
                            IndexRequest indexRequest = new IndexRequest(indexName)
                                    .type("_doc")
                                    .id(x.getId().toString())
                                    .source(builder);
                            bulkRequest.add(indexRequest);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );


            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);



            log.info("bulkResponse : {}", bulkResponse.hasFailures());
            log.info("bulkResponse : {}", bulkResponse.buildFailureMessage());
            log.info("bulkResponse : {}", bulkResponse.hasFailures());



            bulkResponse.iterator().forEachRemaining(x-> System.out.println(x.getFailure() + x.getFailureMessage() + x.getId()));

            FlushRequest flushRequest = new FlushRequest(indexName);
            FlushResponse flushResponse = client.indices().flush(flushRequest, RequestOptions.DEFAULT);

//            ForceMergeRequest forceMergeRequest = new ForceMergeRequest(indexName);
//            ForceMergeResponse forceMergeResponse = client.indices().forcemerge(forceMergeRequest, RequestOptions.DEFAULT);
//            IndicesAliasesRequest indicesAliasesRequest = new IndicesAliasesRequest();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
