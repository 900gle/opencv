package com.etoos.opencv.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AliasType {

    ALIAS(
            "images",
            "tensor_images"
    );

    private String opencv;
    private String tensor;

}
