package com.etoos.opencv.service;

import com.etoos.opencv.dto.PointDTO;
import com.etoos.opencv.proc.MaskingImageProcess;
import com.etoos.opencv.proc.TemplateImageProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final TemplateImageProcess templateImageProcess;
    private final MaskingImageProcess maskingImageProcess;

    public String postTemplateMaskingImage(){
        if(templateImageProcess.process()){
            return "Complated";
        } else {
            return "ERROR";
        }
    }

    public String postPointMaskingImage(PointDTO pointDTO){
        if(maskingImageProcess.process(pointDTO)){
            return "Complated";
        } else {
            return "ERROR";
        }
    }
}
