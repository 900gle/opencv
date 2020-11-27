package com.etoos.opencv.service;

import com.etoos.opencv.apis.ImageIndexApi;
import com.etoos.opencv.apis.TensorImageIndexApi;
import com.etoos.opencv.component.ImageToVector;
import com.etoos.opencv.component.ImageToVectorTensorflow;
import com.etoos.opencv.dto.ImageIndexDTO;
import com.etoos.opencv.model.response.CommonResult;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeRequest;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.ScriptScoreQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.simple.JSONObject;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;

@Slf4j
@Service
@RequiredArgsConstructor
public class TensorflowImageIndexService {

    private final ResponseService responseService;

    private final RestHighLevelClient client;

    private final String ALIAS = "tensor_images";
    String INDEX_NAME = "tensor-images-" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE).toString();

    public CommonResult staticIndex(ImageIndexDTO imageIndexDTO) {

        try {

            GetIndexRequest requestGetIndex = new GetIndexRequest(INDEX_NAME);
            boolean existsIndex = client.indices().exists(requestGetIndex, RequestOptions.DEFAULT);

            GetAliasesRequest aliasesRequest = new GetAliasesRequest(ALIAS);
            GetAliasesResponse getAliasesResponse = client.indices().getAlias(aliasesRequest, RequestOptions.DEFAULT);

            String oldIndexName = "";
            if (getAliasesResponse.getAliases().size() > 0) {
                oldIndexName = Optional.ofNullable(getAliasesResponse.getAliases().keySet().iterator().next()).orElse("");
            }

            if ( getAliasesResponse.getAliases().size() < 1 && existsIndex == false) {
                CreateIndexRequest request = TensorImageIndexApi.createIndex(INDEX_NAME);
                CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

                IndicesAliasesRequest aliasRequest = new IndicesAliasesRequest();
                IndicesAliasesRequest.AliasActions aliasAction =
                        new IndicesAliasesRequest.AliasActions(IndicesAliasesRequest.AliasActions.Type.ADD)
                                .index(INDEX_NAME)
                                .alias(ALIAS);
                aliasRequest.addAliasAction(aliasAction);
                AcknowledgedResponse indicesAliasesResponse =
                        client.indices().updateAliases(aliasRequest, RequestOptions.DEFAULT);

            } else {

//                DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
//                AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//
//                if (deleteIndexResponse.isAcknowledged()) {
//                    CreateIndexRequest request = ImageIndexApi.createIndex(indexName);
//                    CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//                }

            }



          return responseService.getSingleResult(insertData(imageIndexDTO));




        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseService.getFailResult();
    }

    int i = 0;

    public boolean insertData(ImageIndexDTO imageIndexDTO) {

//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//
//
//
//        Mat imageAvengers = Imgcodecs.imread(imageIndexDTO.getFilePath());
//
//
//
//        MatOfKeyPoint keyPointOfAvengers = new MatOfKeyPoint();
//        SIFT.create().detect(imageAvengers, keyPointOfAvengers);
//
//        Mat discripters = new Mat();
//        Mat mask = new Mat();
//        SIFT.create().detectAndCompute(imageAvengers, mask, keyPointOfAvengers, discripters);
//        Vector<Integer> denseVector = ImageToVector.getIntVector(discripters);
//        JSONObject denseVector = ImageToVectorTensorflow.getVector();




        BulkRequest bulkRequest = new BulkRequest();
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.field("feature", ImageToVectorTensorflow.getVector(imageIndexDTO));
                builder.field("image_id", imageIndexDTO.getImageId());
                builder.field("image_name", imageIndexDTO.getImageName());

            }
            builder.endObject();
            IndexRequest indexRequest = new IndexRequest(ALIAS)
                    .type("_doc")
                    .id(null)
                    .source(builder);
            bulkRequest.add(indexRequest);

            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()) {
                System.out.println(bulkResponse.buildFailureMessage());
                return false;
            }

            ForceMergeRequest forceMergeRequest = new ForceMergeRequest(INDEX_NAME);
            forceMergeRequest.flush(true);
            ForceMergeResponse forceMergeResponse = client.indices().forcemerge(forceMergeRequest, RequestOptions.DEFAULT);
            System.out.println(forceMergeResponse.getStatus());

        } catch (IOException e) {
            e.printStackTrace();
        }



        return true;
    }


    public void getIndexer() {

        try {

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("images-2020-11-03");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            Map<String, Object> map = new HashMap<>();
            map.put("query_vector", "[random.gauss(0, 0.432) for _ in range(128)]");

            ScriptScoreQueryBuilder functionScoreQueryBuilder = new ScriptScoreQueryBuilder(QueryBuilders.matchAllQuery(), new Script(Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
                    "dotProduct(params.query_vector, doc['feature']) + 1.0", map));

            searchSourceBuilder.query(functionScoreQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            System.out.println(searchRequest.source());

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            System.out.println("--------------------------------");
            System.out.println(searchResponse);

        } catch (IOException e) {
            e.getStackTrace();
        }

    }


}
