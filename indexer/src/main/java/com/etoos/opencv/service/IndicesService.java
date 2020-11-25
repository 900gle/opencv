package com.etoos.opencv.service;

import com.etoos.opencv.apis.ImageIndexApi;
import com.etoos.opencv.apis.TensorImageIndexApi;
import com.etoos.opencv.dto.ImageIndexDTO;
import com.etoos.opencv.model.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndicesService {

    private final ResponseService responseService;

    private final RestHighLevelClient client;

    private final String ALIAS = "tensor_images";
    String INDEX_NAME = "tensor-images-" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE).toString();


    public CommonResult createIndex() {

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


            return responseService.getSuccessResult();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseService.getFailResult();
    }


}



