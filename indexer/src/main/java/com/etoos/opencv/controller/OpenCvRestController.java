package com.etoos.opencv.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import opencv.model.response.CommonResult;
import org.opencv.core.Core;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(tags = "1. Opencv image Apis")
@RequestMapping("api/opencv")
@RequiredArgsConstructor
public class OpenCvRestController {




    @CrossOrigin("*")
    @ApiOperation(value = "opencv test", notes = "오픈 CV TEST")
    @PostMapping(value = "test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult postImagePoint(
            @ApiParam(value = "파일") @RequestParam(value = "file", required = true) @Validated final MultipartFile file

    ) {
//        try {


            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);





        return new CommonResult();

    }
}
