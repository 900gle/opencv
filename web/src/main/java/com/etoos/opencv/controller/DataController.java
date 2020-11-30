package com.etoos.opencv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("data")
public class DataController {

    @GetMapping("opencv")
    public String getOpencv() {
        return "opencv_data";
    }

    @GetMapping("tensor")
    public String getTensorflow() {
        return "tensor_data";
    }
}
