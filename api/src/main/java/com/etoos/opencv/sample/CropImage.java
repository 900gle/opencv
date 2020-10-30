package com.etoos.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class CropImage {
    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filePath="./images/";

        Mat img = Imgcodecs.imread(filePath + "avengers.jpg", Imgcodecs.IMREAD_COLOR);
        Rect rectCrop = new Rect(100,20,300,200);
        Mat subImage= new Mat(img, rectCrop);
//        Imgcodecs.imwrite(filePath +"2.png",subImage);

        HighGui.imshow("test", subImage);
        HighGui.waitKey();

    }

}
