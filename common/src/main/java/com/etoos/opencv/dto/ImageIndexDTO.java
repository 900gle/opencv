package com.etoos.opencv.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageIndexDTO {

    private int imageId;
    private String imageName;
    private String filePath;
}
