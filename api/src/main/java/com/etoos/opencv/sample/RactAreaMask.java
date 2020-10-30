package com.etoos.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class RactAreaMask {


    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filePath="./images/";

        Mat source = Imgcodecs.imread(filePath+"avengers.jpg");
        int thickness = Imgproc.FILLED;
        Imgproc.rectangle(source, new Point(10, 10), new Point(100 ,
                100), new Scalar(0,0,0), thickness);

        HighGui.imshow("point fill test", source);
        HighGui.waitKey();

        Imgcodecs.imwrite(filePath+"point_fill_black.png", source);
        System.out.println("Complated.");

    }
}
