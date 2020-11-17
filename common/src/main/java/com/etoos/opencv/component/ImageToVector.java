package com.etoos.opencv.component;

import com.etoos.opencv.dto.ImageSearchDTO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class ImageToVector {

    static final String BASE_DIR = "/Users/doo/project/opencv/common/temp";

    public static Vector<Double> getVector(ImageSearchDTO imageSearchDTO) throws IOException {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String filePath = BASE_DIR + "/" + imageSearchDTO.getFile().getOriginalFilename();
        imageSearchDTO.getFile().transferTo(new File(filePath));

        Mat image = Imgcodecs.imread(filePath);
        MatOfKeyPoint keyPointOfAvengers = new MatOfKeyPoint();
        SIFT.create().detect(image, keyPointOfAvengers);

        Mat discripters = new Mat();
        Mat mask = new Mat();
        SIFT.create().detectAndCompute(image, mask, keyPointOfAvengers, discripters);
        return getDoubleVector(discripters);
    }

    public static Vector<Integer> getIntVector(Mat discripters) {
        Vector<Integer> integerVector = new Vector<>();
        for (int i = 0; i < discripters.size(1); i++) {
            Vector<Boolean> booleanVector = new Vector<>();
            for (int j = 0; j < discripters.size(0); j++) {
                booleanVector.add(discripters.get(j, i)[0] % 2 > 0);
            }
            int hashNum = booleanVector.hashCode();
            integerVector.add(hashNum);
        }
        return integerVector;
    }

    public static Vector<Double> getDoubleVector(Mat discripters) {
        Vector<Double> doubleVector = new Vector<>();
        for (int i = 0; i < discripters.size(1); i++) {
            Vector<Double> booleanVector = new Vector<>();
            for (int j = 0; j < discripters.size(0); j++) {
                booleanVector.add(discripters.get(j, i)[0]);
            }
            double hashNum = booleanVector.hashCode();
            doubleVector.add(hashNum);
        }
        return doubleVector;
    }

}
