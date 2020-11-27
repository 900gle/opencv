package com.etoos.opencv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping("opencv")
    public String getOpencv() {
        return "image_search";
    }

    @GetMapping("tensor")
    public String getTensorflow() {
        return "tensor_search";
    }
}
