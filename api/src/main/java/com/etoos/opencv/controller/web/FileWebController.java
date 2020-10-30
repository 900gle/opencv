package com.etoos.opencv.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileWebController {

    @GetMapping("file")
    public String file() {


        return "file_upload";
    }
}
