package com.etoos.opencv.controller;


import com.etoos.opencv.dto.ImageIndexDTO;
import com.etoos.opencv.model.response.CommonResult;
import com.etoos.opencv.service.DataService;
import com.etoos.opencv.service.ImageIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@Api(tags = "1. Data manage Apis")
@RequestMapping("indexer/")
@RequiredArgsConstructor
public class DataRestController {

    private final DataService dataService;

    @CrossOrigin("*")
    @ApiOperation(value = "index", notes = "색인 데이타 확인")
    @GetMapping("data")
    public CommonResult getDatas(
            @ApiParam(value = "인덱스 종류") @RequestParam(value = "indexKey", defaultValue = "tensor", required = true) @Validated final String indexKey,
            @ApiParam(value = "이미지 명") @RequestParam(value = "imageName", defaultValue = "", required = true) @Validated final String imageName
    ) {
        return dataService.getDatas(indexKey, imageName);
    }

    @CrossOrigin("*")
    @ApiOperation(value = "index", notes = "색인 데이타 확인")
    @DeleteMapping("data")
    public CommonResult deleteData(
            @ApiParam(value = "인덱스 종류") @RequestParam(value = "indexKey", defaultValue = "tensor", required = true) @Validated final String indexKey,
            @ApiParam(value = "도큐먼트아이디") @RequestParam(value = "documentId", defaultValue = "", required = true) @Validated final String documentId
    ) {


        System.out.println("documentId :: " + documentId);

        return dataService.deleteData(indexKey, documentId);
    }


}
