package com.etoos.opencv.controller;


import com.etoos.opencv.dto.PointDTO;
import com.etoos.opencv.model.response.CommonResult;
import com.etoos.opencv.model.response.RestApiResponse;
import com.etoos.opencv.service.ImageMaskingService;
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
@Api(tags = "1. Opencv image Apis")
@RequestMapping("api/masking")
@RequiredArgsConstructor
public class ImageMaskingRestController {

    private final ImageMaskingService imageMaskingService;

    @CrossOrigin("*")
    @ApiOperation(value = "template masking", notes = "이미지 템플릿 마스킹")
    @PostMapping("template")
    public RestApiResponse postImageTemplate() {
        return new RestApiResponse<>(imageMaskingService.postTemplateMaskingImage());
    }

    @CrossOrigin("*")
    @ApiOperation(value = "point masking", notes = "이미지 포인트 마스킹")
    @PostMapping(value = "point", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult postImagePoint(
            @ApiParam(value = "파일") @RequestParam(value = "file", required = true) @Validated final MultipartFile file,
            @ApiParam(value = "마스킹 위치 x 축 px") @RequestParam(value = "x", defaultValue = "0", required = true) @Validated final int x,
            @ApiParam(value = "마스킹 위치 y 축 px") @RequestParam(value = "y", defaultValue = "0", required = true) @Validated final int y,
            @ApiParam(value = "마스킹 넓이 px") @RequestParam(value = "width", defaultValue = "100", required = true) @Validated final int width,
            @ApiParam(value = "마스킹 높이 px") @RequestParam(value = "height", defaultValue = "100", required = true) @Validated final int height
    ) {
        try {

            String baseDir = "/Users/doo/project/opencv/temp/";
            String filePath = baseDir + "//" + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            return imageMaskingService.postPointMaskingImage(
                    PointDTO.builder()
                            .x(x)
                            .y(y)
                            .width(width)
                            .height(height)
                            .filePath(filePath)
                            .fileDir(baseDir)
                            .fileName(file.getOriginalFilename())
                            .build()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CommonResult();

    }
}
