package com.etoos.opencv.domain;

import com.etoos.opencv.domain.image.Images;
import com.etoos.opencv.repository.ImagesRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ImagesRepositoryTest {

    @Autowired
    ImagesRepository imagesRepository;

    @After
    public void cleanup() {
        imagesRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        imagesRepository.save(Images.builder()
                .imageName("masking_images.jpg")
                .imagePath("/root/images/image/")
                .originalImageName("dog.jpg").build()
        );

        //when
        List<Images> imagesList = imagesRepository.findAll();

        //then
        Images images = imagesList.get(0);
        assertThat(images.getImageName(), is("masking_images.jpg"));

    }
}


