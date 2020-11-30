package com.etoos.opencv.service;

import com.etoos.opencv.apis.TensorImageIndexApi;
import com.etoos.opencv.component.ImageToVectorTensorflow;
import com.etoos.opencv.dto.ImageIndexDTO;
import com.etoos.opencv.dto.ImageSearchDTO;
import com.etoos.opencv.model.response.CommonResult;
import com.etoos.opencv.model.type.AliasType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeRequest;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScriptScoreQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataService {

    private final ResponseService responseService;
    private final RestHighLevelClient client;

    private String getAlias(String indexType) {
        String alias = "";
        switch (indexType) {
            case "opencv":
                alias = AliasType.ALIAS.getOpencv();
                break;
            case "tensor":
                alias = AliasType.ALIAS.getTensor();
                break;
        }
        return alias;
    }


    public CommonResult getDatas(String indexType, String imageName) {

        try {


            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices(getAlias(indexType));
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            String[] includeFields = new String[]{"image_id", "image_name"};
            String[] excludeFields = new String[]{"feature"};
            searchSourceBuilder.fetchSource(includeFields, excludeFields);

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            if (!StringUtils.isEmpty(imageName)){
                boolQueryBuilder.must(QueryBuilders.matchQuery("image_name", imageName).operator(Operator.AND));
            }
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            System.out.println(searchRequest);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            List<Map<String, Object>> returnValue = new ArrayList<>();
            SearchHit[] results = searchResponse.getHits().getHits();
            Arrays.stream(results).forEach(hit -> {
                Map<String, Object> result = hit.getSourceAsMap();
                result.put("score", hit.getScore() - 1.0);
                result.put("id", hit.getId());
                returnValue.add(result);
            });

            return responseService.setListResult(returnValue);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseService.getFailResult();

    }

    public CommonResult deleteData(String indexType, String documentId) {

        try {

            String indexName = getAlias(indexType);
            DeleteRequest request = new DeleteRequest();
            request.index(indexName);
            request.id(documentId);

            DeleteResponse deleteResponse = client.delete(
                    request, RequestOptions.DEFAULT);

            System.out.println(deleteResponse);

            ForceMergeRequest forceMergeRequest = new ForceMergeRequest(indexName);
            forceMergeRequest.flush(true);
            ForceMergeResponse forceMergeResponse = client.indices().forcemerge(forceMergeRequest, RequestOptions.DEFAULT);
            System.out.println(forceMergeResponse.getStatus());

        } catch (IOException e) {
            e.getStackTrace();
        }



        return responseService.getSuccessResult();


    }


}
