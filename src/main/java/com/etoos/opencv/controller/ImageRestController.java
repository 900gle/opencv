package com.etoos.opencv.controller;


import com.etoos.opencv.model.response.RestApiResponse;
import com.etoos.opencv.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "1. Opencv image Apis")
@RequestMapping("api/images")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImageService imageService;

    @CrossOrigin("*")
    @ApiOperation(value = "이미지템플릿", notes = "이미지템플")
    @PostMapping("template")
    public RestApiResponse postImageTemplate(){
        return new RestApiResponse<>(imageService.postTemplateImage());
    }

    @CrossOrigin("*")
    @ApiOperation(value = "이미지템플릿", notes = "이미지템플릿")
    @PostMapping("masking")
    public RestApiResponse postImageMasking(){
        return new RestApiResponse<>(imageService.postMaskingImage());
    }
}
