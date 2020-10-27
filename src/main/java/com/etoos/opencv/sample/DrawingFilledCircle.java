package com.etoos.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class DrawingFilledCircle {

    public static void main(String[] args) {


        //Loading the OpenCV core library
//        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        //Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        //Reading the source image in to a Mat object

        String filePath="./images/";


        Mat src = Imgcodecs.imread(filePath+"faces_in_image.jpg");
        //Drawing a Circle
        Point center = new Point(300, 200);
        int radius =100;
        Scalar color = new Scalar(64, 64, 64);
        int thickness = Imgproc.FILLED;
        Imgproc.circle (src, center, radius, color, thickness);




        //Saving and displaying the image
//        Imgcodecs.imwrite("arrowed_line.jpg", src);
        HighGui.imshow("Drawing a circle", src);
        HighGui.waitKey();

    }
}
