package com.etoos.opencv.controller;


import com.etoos.opencv.service.ImageIndexService;
import com.etoos.opencv.service.MaskingIndexService;
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
@RequestMapping("indexer/")
@RequiredArgsConstructor
public class MaskingIndexerRestController {

    private final MaskingIndexService maskingIndexService;

    @CrossOrigin("*")
    @ApiOperation(value = "index", notes = "마스킹 데이타 색인")
    @PostMapping("masking")
    public CommonResult staticIndexer(){

        maskingIndexService.staticIndex();

        return new CommonResult();
    }

}
