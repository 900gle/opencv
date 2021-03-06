package com.etoos.opencv.controller;


import com.etoos.opencv.dto.ImageIndexDTO;
import com.etoos.opencv.model.response.CommonResult;
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
@Api(tags = "1. Opencv image index Apis")
@RequestMapping("indexer/")
@RequiredArgsConstructor
public class ImageIndexerRestController {

    private final ImageIndexService imageIndexService;

    @CrossOrigin("*")
    @ApiOperation(value = "index", notes = "이미지 검색 - 이미지 데이타 색인")
    @PostMapping("images")
    public CommonResult staticIndexer(
            @ApiParam(value = "파일") @RequestParam(value = "file", required = true) @Validated final MultipartFile file,
            @ApiParam(value = "이미지 아이디") @RequestParam(value = "imageId", defaultValue = "1", required = true) @Validated final int imageId

    ) {
        try {

            String baseDir = "/Users/doo/project/opencv/web/src/main/resources/static/images";
            String filePath = baseDir + "//" + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            return imageIndexService.staticIndex(ImageIndexDTO.builder().imageId(imageId).imageName(file.getOriginalFilename()).filePath(filePath).build());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonResult();
    }


    @CrossOrigin("*")
    @ApiOperation(value = "index", notes = "이미지 검색 TEST")
    @GetMapping("images")
    public CommonResult getIndexer() {

        imageIndexService.getIndexer();
        return new CommonResult();
    }

}
