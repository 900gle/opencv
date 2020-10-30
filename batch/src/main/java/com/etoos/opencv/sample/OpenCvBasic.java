package com.etoos.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCvBasic {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        String filePath = "./images/";

        Mat img = Imgcodecs.imread(filePath+"avengers.jpg");

////        Mat img1 =img.clone();
//        Rect r = new Rect(10, 10, 100, 100);
//        Mat img1 = img.submat(r);
////        Mat img1 = new Mat();
////        Imgproc.cvtColor(img, img1, Imgproc.COLOR_BGR2BGRA);
//        HighGui.imshow("show image", img1);
//        HighGui.waitKey();

        Mat grey = new Mat();
        Imgproc.cvtColor(img, grey, Imgproc.COLOR_BGR2GRAY);

//                HighGui.imshow("show image", grey);
//        HighGui.waitKey();

        Mat sobelx = new Mat();
        Imgproc.Sobel(grey, sobelx, CvType.CV_32F, 1, 0);
        Core.MinMaxLocResult res = Core.minMaxLoc(sobelx); // find minimum and maximum intensities
        Mat draw = new Mat();
        double maxVal = res.maxVal, minVal = res.minVal;
        sobelx.convertTo(draw, CvType.CV_8U, 255.0 / (maxVal - minVal), -minVal * 255.0 / (maxVal - minVal));
        HighGui.namedWindow("image", HighGui.WINDOW_AUTOSIZE);
        HighGui.imshow("image", draw);
        HighGui.waitKey();

    }
}
