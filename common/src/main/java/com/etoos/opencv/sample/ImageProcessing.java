package com.etoos.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageProcessing {


    public static void main(String[] args) {
        //Loading the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Reading the input image

        String filePath = "/Users/doo/project/opencv/common/temp/";
        String file = "photo_t2.jpeg";
//        String file = "cat1.jpeg";

        Mat src = Imgcodecs.imread(filePath + file, Imgcodecs.IMREAD_GRAYSCALE);
        Mat ori = Imgcodecs.imread(filePath + file);
        Mat dst = new Mat();
//
//        int row = 0, col = 0;
//        Mat kernel1 = new Mat(3, 3, CvType.CV_8S);
//        kernel1.put(row, col, 0, -1, 0, -1, 5, -1, 0, -1, 0);
//        Imgproc.filter2D(src, dst, -1, kernel1);
//        Imgproc.equalizeHist( src, dst );


        Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 99, 10);

        Imgproc.equalizeHist( dst, dst );
//        Imgproc.filter2D(dst, dst, -1, kernel1);


//        Imgcodecs.imwrite(filePath + "process_test2.jpg", dst);


        HighGui.imshow("origin", ori);
        HighGui.imshow("Process", dst);
        HighGui.waitKey();
    }


}
