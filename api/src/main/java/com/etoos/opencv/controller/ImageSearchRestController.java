package com.etoos.opencv.controller;

import com.etoos.opencv.model.response.CommonResult;
import com.etoos.opencv.service.ImageSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import com.etoos.opencv.dto.ImageSearchDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = "2. Image Search Apis")
@RequestMapping("api/images")
@RequiredArgsConstructor
public class ImageSearchRestController {

    private final ImageSearchService imageSearchService;

    @CrossOrigin("*")
    @ApiOperation(value = "이미지 검색", notes = "이미지 검색")
    @PostMapping()
    public CommonResult postImagePoint(
            @ApiParam(value = "파일") @RequestParam(value = "file", required = true) @Validated final MultipartFile file,
            @ApiParam(value = "이미지 아이디") @RequestParam(value = "imageId", defaultValue = "1") @Validated final int imageId
    ) {
        return imageSearchService.getImages(
                ImageSearchDTO.builder()
                        .imageId(imageId)
                        .file(file)
                        .build()
        );
    }
}
