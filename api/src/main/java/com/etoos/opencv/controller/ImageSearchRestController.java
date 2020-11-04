package com.etoos.opencv.controller;

import com.etoos.opencv.dto.PointDTO;
import com.etoos.opencv.dto.SearchDTO;
import com.etoos.opencv.model.response.CommonResult;
import com.etoos.opencv.service.ImageSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Api(tags = "2. Image Search Apis")
@RequestMapping("api/images")
@RequiredArgsConstructor
public class ImageSearchRestController {


    private final ImageSearchService imageSearchService;

    @CrossOrigin("*")
    @ApiOperation(value = "이미지 검색", notes = "이미지 검색")
    @GetMapping()
    public CommonResult postImagePoint(

            @ApiParam(value = "검색어") @RequestParam(value = "keyword", defaultValue = "", required = true) @Validated final String keyword,
            @ApiParam(value = "페이지") @RequestParam(value = "page", defaultValue = "", required = true) @Validated final int page,
            @ApiParam(value = "사이") @RequestParam(value = "size", defaultValue = "100", required = true) @Validated final int size
    ) {

            return imageSearchService.getImages(
                    SearchDTO.builder()
                            .keyword(keyword)
                            .page(page)
                            .size(size)
                            .build()
            );




    }
}
