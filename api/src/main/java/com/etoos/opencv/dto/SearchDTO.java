package com.etoos.opencv.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchDTO {

    private String keyword;
    private int page;
    private int size;

}
