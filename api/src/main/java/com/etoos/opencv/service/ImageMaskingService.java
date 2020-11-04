package com.etoos.opencv.service;

import com.etoos.opencv.dto.PointDTO;
import com.etoos.opencv.model.response.CommonResult;
import com.etoos.opencv.proc.MaskingImageProcess;
import com.etoos.opencv.proc.TemplateImageProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageMaskingService {

    private final TemplateImageProcess templateImageProcess;
    private final MaskingImageProcess maskingImageProcess;
    private final ResponseService responseService;

    public CommonResult postTemplateMaskingImage(){
        if(templateImageProcess.process()){
            return responseService.getSuccessResult();
        } else {
            return responseService.getFailResult();
        }
    }

    public CommonResult postPointMaskingImage(PointDTO pointDTO){

        if(maskingImageProcess.process(pointDTO)){
            return responseService.getSuccessResult();
        } else {
            return responseService.getFailResult();
        }
    }
}
