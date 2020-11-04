package com.etoos.opencv.controller;

import com.etoos.opencv.domain.image.Images;
import com.etoos.opencv.service.ImageService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "1. Opencv image index Apis")
@RequestMapping("indexer/test")
@RequiredArgsConstructor
public class TestController {

    private final ImageService imageService;
    @GetMapping
    public Page<Images> getImages (final Pageable pageable){

        System.out.println(pageable);

        return imageService.findAll(pageable);
    }
}
