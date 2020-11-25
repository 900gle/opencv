package com.etoos.opencv.controller;


import com.etoos.opencv.model.response.CommonResult;
import com.etoos.opencv.service.IndicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "1. Index create for Tensorflow test apis")
@RequestMapping("indexer/")
@RequiredArgsConstructor
public class IndicesRestController {

    private final IndicesService indicesService;

    @CrossOrigin("*")
    @ApiOperation(value = "index", notes = "이미지 검색 - 인덱스생성")
    @PostMapping("indices")
    public CommonResult createIndex(

    ) {
        return indicesService.createIndex();
    }


}
