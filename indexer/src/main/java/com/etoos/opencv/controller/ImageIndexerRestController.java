package com.etoos.opencv.controller;


import com.etoos.opencv.service.ImageIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import opencv.model.response.CommonResult;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "1. Opencv image index Apis")
@RequestMapping("indexer/")
@RequiredArgsConstructor
public class ImageIndexerRestController {

    private final ImageIndexService imageIndexService;

    @CrossOrigin("*")
    @ApiOperation(value = "index", notes = "이미지 검색 색인 테스트")
    @PostMapping("images")
    public CommonResult staticIndexer(){

        imageIndexService.staticIndex();

        return new CommonResult();
    }

    @CrossOrigin("*")
    @ApiOperation(value = "index", notes = "이미지 검색 TEST")
    @GetMapping("images")
    public CommonResult getIndexer(){

        imageIndexService.getIndexer();

        return new CommonResult();
    }

}
