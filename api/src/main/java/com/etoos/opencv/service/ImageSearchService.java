package com.etoos.opencv.service;

import com.etoos.opencv.model.response.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.etoos.opencv.component.ImageToVector;
import com.etoos.opencv.dto.ImageSearchDTO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.ScriptScoreQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageSearchService {

    private final ResponseService responseService;
    private final RestHighLevelClient client;
    private final String ALIAS = "images";


    public CommonResult getImages(ImageSearchDTO imageSearchDTO) {

        try {
            Vector<Integer> vectors = ImageToVector.getVector(imageSearchDTO);

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices(ALIAS);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            String[] includeFields = new String[]{"image_id", "image_name"};
            String[] excludeFields = new String[]{"feature"};
            searchSourceBuilder.fetchSource(includeFields, excludeFields);

            Map<String, Object> map = new HashMap<>();
            map.put("query_vector", vectors);

            ScriptScoreQueryBuilder functionScoreQueryBuilder = new ScriptScoreQueryBuilder(
                    QueryBuilders.matchAllQuery(),
                    new Script(
                            Script.DEFAULT_SCRIPT_TYPE,
                            Script.DEFAULT_SCRIPT_LANG,
                            "cosineSimilarity(params.query_vector, 'feature') + 1.0", map)
            );

            searchSourceBuilder.query(functionScoreQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            List<Map<String, Object>> returnValue = new ArrayList<>();
            SearchHit[] results = searchResponse.getHits().getHits();
            Arrays.stream(results).forEach(hit -> {
                Map<String, Object> result = hit.getSourceAsMap();
                result.put("score", hit.getScore() - 1.0);
                returnValue.add(result);
            });

            return responseService.setListResult(returnValue);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseService.getFailResult();


    }


}
