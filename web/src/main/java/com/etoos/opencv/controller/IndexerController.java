package com.etoos.opencv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("indexer")
public class IndexerController {

    @GetMapping("")
    public String getImages() {
        return "image_indexer";
    }
}
