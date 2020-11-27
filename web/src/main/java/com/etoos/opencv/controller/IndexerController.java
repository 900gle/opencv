package com.etoos.opencv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("indexer")
public class IndexerController {

    @GetMapping("opencv")
    public String getOpencv() {
        return "image_indexer";
    }

    @GetMapping("tensor")
    public String getTensorflow() {
        return "tensor_indexer";
    }
}
