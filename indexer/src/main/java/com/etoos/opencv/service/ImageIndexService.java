package com.etoos.opencv.service;

import com.etoos.opencv.apis.ImageIndexApi;
import com.etoos.opencv.config.Client;
import com.etoos.opencv.domain.image.Images;
import com.etoos.opencv.repository.ImagesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.admin.indices.flush.FlushResponse;
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
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.index.query.functionscore.ScriptScoreQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageIndexService {


    private final RestHighLevelClient client;

    String indexName = "images-" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE).toString();

    public void staticIndex() {

        try {

            GetIndexRequest requestGetIndex = new GetIndexRequest(indexName);
            boolean existsIndex = client.indices().exists(requestGetIndex, RequestOptions.DEFAULT);

            GetAliasesRequest aliasesRequest = new GetAliasesRequest("images");
            GetAliasesResponse getAliasesResponse = client.indices().getAlias(aliasesRequest, RequestOptions.DEFAULT);

            String oldIndexName = "";
            if (getAliasesResponse.getAliases().size() > 0) {
                oldIndexName = Optional.ofNullable(getAliasesResponse.getAliases().keySet().iterator().next()).orElse("");
            }

            if (existsIndex == false) {
                CreateIndexRequest request = ImageIndexApi.createIndex(indexName);
                CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            } else {

                DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
                AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

                if (deleteIndexResponse.isAcknowledged()) {
                    CreateIndexRequest request = ImageIndexApi.createIndex(indexName);
                    CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getIndexer() {

        try {

            SearchRequest searchRequest = new SearchRequest();

            searchRequest.indices("images-2020-11-03");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            Map<String, Object> map = new HashMap<>();
            map.put("query_vector", "[random.gauss(0, 0.432) for _ in range(128)]");

//            ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.scriptFunction(
//                    new Script( Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
//                    "dotProduct(params.query_vector, doc['feature']) + 1.0", map)
//            );

            ScriptScoreQueryBuilder functionScoreQueryBuilder = new ScriptScoreQueryBuilder(QueryBuilders.matchAllQuery(), new Script(Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
                    "dotProduct(params.query_vector1, doc['feature']) + 1.0", map));

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
