package com.etoos.opencv.proc;

import com.etoos.opencv.domain.image.Images;
import com.etoos.opencv.dto.PointDTO;
import com.etoos.opencv.repository.ImagesRepository;
import lombok.RequiredArgsConstructor;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
public class MaskingImageProcess {

    private final ImagesRepository imagesRepository;

    public boolean process(PointDTO pointDTO) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filePath = pointDTO.getFilePath();

        Mat source = Imgcodecs.imread(filePath);
        int thickness = Imgproc.FILLED;
        Imgproc.rectangle(source, new Point(pointDTO.getX(), pointDTO.getY()), new Point(pointDTO.getWidth(),
                pointDTO.getHeight()), new Scalar(0, 0, 0), thickness);

        String fileDir = "./images/";
        Imgcodecs.imwrite(fileDir + "masking_" + pointDTO.getFileName(), source);

        File file = new File(pointDTO.getFilePath());
        if (file.exists()) {
            file.delete();
        }

        imagesRepository.save(Images.builder()
                .imagePath(fileDir)
                .originalImageName(pointDTO.getFileName())
                .imageName("masking_" + pointDTO.getFileName()).build());

        System.out.println("Complated.");
        return true;
    }
}
