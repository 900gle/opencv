package com.etoos.opencv.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import opencv.model.response.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Api(tags = "1. Opencv image index Apis")
@RequestMapping("indexer/images")
@RequiredArgsConstructor
public class IndexerRestController {

    @CrossOrigin("*")
    @ApiOperation(value = "template masking", notes = "이미지 템플릿 마스킹")
    @PostMapping("template")
    public CommonResult staticIndexer(){

        return new CommonResult();
    }

}
