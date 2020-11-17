package com.etoos.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Vector;

public class TestSample {

    public static final double sparsity = 10;

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String filePath = "/Users/doo/project/opencv/images/";
        String imageName = "avengers.jpg";

        Mat image = Imgcodecs.imread(filePath + imageName);

        MatOfKeyPoint keyPointOfAvengers = new MatOfKeyPoint();
        SIFT.create().detect(image, keyPointOfAvengers);

        Mat discripters = new Mat();
        Mat mask = new Mat();
        SIFT.create().detectAndCompute(image, mask, keyPointOfAvengers, discripters);

        Vector<Double> doubleVector = new Vector<>();

        for (int i = 0; i < discripters.size(1); i++) {
            Vector<Double> booleanVector = new Vector<>();

            for (int j = 0; j < discripters.size(0); j++) {
                booleanVector.add(discripters.get(j, i)[0]);
            }

            double hashNum = booleanVector.hashCode();
            doubleVector.add(hashNum);

            System.out.println(hashNum);

//            double asdf = Integer.parseInt(binaryString.toString(), 2);
//            System.out.println(asdf);

//            sum =  Integer.valueOf(binaryString.toString(), 2);

//            System.out.println(sum);
//            binaryString.delete(0, binaryString.length());
//            doubleVector.add(sum);
//            sum = 0;
        }

//        doubleVector.stream().forEach(x -> System.out.println(x));

    }
}
