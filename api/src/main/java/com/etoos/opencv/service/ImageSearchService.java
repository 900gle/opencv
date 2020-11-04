package com.etoos.opencv.service;

import com.etoos.opencv.dto.SearchDTO;
import com.etoos.opencv.model.response.CommonResult;
import com.etoos.opencv.proc.MaskingImageProcess;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.lucene.search.function.ScriptScoreQuery;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class ImageSearchService {

    private final ResponseService responseService;

    public CommonResult getImages(SearchDTO searchDTO) {

//
//        curl -X GET "localhost:9200/_search?pretty" -H 'Content-Type: application/json' -d'
//        {
//            "query": {
//            "script_score": {
//                "query": {
//                    "match_all": {  }
//                },
//                "script": {
//                    "source": "dotProduct(params.query_vector, doc['feature']) + 1.0",
//                    "params": {
//                             "query_vector": [random.gauss(0, 0.432) for _ in range(128)],
//                        }
//
//
//                }
//            }
//        }
//        }
//        '





        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query();
        searchRequest.source(searchSourceBuilder);



        return responseService.setListResult(new LinkedList<>());
    }


}
