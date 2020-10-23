package com.etoos.opencv.service;

import com.etoos.opencv.proc.MaskingImageProcess;
import com.etoos.opencv.proc.TemplateImageProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final TemplateImageProcess templateImageProcess;
    private final MaskingImageProcess maskingImageProcess;

    public String postTemplateImage(){
        if(templateImageProcess.process()){
            return "Complated";
        } else {
            return "ERROR";
        }
    }

    public String postMaskingImage(){
        if(maskingImageProcess.process()){
            return "Complated";
        } else {
            return "ERROR";
        }
    }
}
