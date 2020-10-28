package com.etoos.opencv.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PointDTO {
    private int x;
    private int y;
    private int width;
    private int height;

    private String filePath;
    private String fileDir;
    private String fileName;

}
