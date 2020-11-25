package com.etoos.opencv.service;

import com.etoos.opencv.domain.image.Images;
import com.etoos.opencv.repository.ImagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ImageService {

    ImagesRepository imagesRepository;

    public Page<Images> findAll (Pageable pageable) {
        return imagesRepository.findAll(pageable);
    }
}
