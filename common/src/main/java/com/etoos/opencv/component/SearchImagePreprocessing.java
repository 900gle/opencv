package com.etoos.opencv.component;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class SearchImagePreprocessing {


    public static boolean searchImage(String file, String filePath) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat src = Imgcodecs.imread(filePath + "/" + file, Imgcodecs.IMREAD_GRAYSCALE);



        Mat dst = new Mat();

        Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 99, 10);
        Imgproc.equalizeHist( dst, dst );
        Imgcodecs.imwrite(filePath + "/" + file, dst);
        return true;
    }

}
