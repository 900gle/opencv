package com.etoos.opencv.sample;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ImageProcessing {


    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String filePath = "/Users/doo/project/opencv/common/temp/";
        String file = "t_img7.jpeg";
//        String file = "angry_dog.jpg";

        Mat src = Imgcodecs.imread(filePath + file, Imgcodecs.IMREAD_GRAYSCALE);
        Mat ori = Imgcodecs.imread(filePath + file);
        Mat dst = new Mat();

        Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 99, 10);
        Imgproc.equalizeHist(dst, dst);





/*
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(dst, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        System.out.println("contours : "+ contours.size());
        Imgproc.drawContours(dst, contours, 2000, new Scalar(255, 255, 255), -1);
*/


//        Test.1
/*
        Mat image = Imgcodecs.imread("C:/Users/ja/workspace/imgtomath/bin/imgtomath/lena.png");
        if(image.empty() == true) {
            System.out.println("Error: no image found!");
        }
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat image32S = new Mat();
        dst.convertTo(image32S, CvType.CV_32SC1);

        Imgproc.findContours(image32S, contours, new Mat(), Imgproc.RETR_FLOODFILL, Imgproc.CHAIN_APPROX_SIMPLE);
        Mat contourImg = new Mat(image32S.size(), image32S.type());
        for (int i = 0; i < contours.size(); i++) {
            Imgproc.drawContours(contourImg, contours, 1, new Scalar(255, 255, 255), -1);
        }
        Imgcodecs.imwrite(filePath + "debug_image.jpg", contourImg); // DEBUG
*/

//
//
//        final List<MatOfPoint> points = new ArrayList<>();
//        final Mat hierarchy = new Mat();
//        Imgproc.findContours(dst, points, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
//
//        Imgproc.cvtColor(dst, dst, Imgproc.COLOR_GRAY2BGR);
//


//        double maxArea = -1;
//        int maxAreaIdx = -1;
//        for (int idx = 0; idx < contours.size(); idx++) {
//            Mat contour = contours.get(idx);
//            double contourarea = Imgproc.contourArea(contour);
//            if (contourarea > maxArea) {
//                maxArea = contourarea;
//                maxAreaIdx = idx;
//            }
//        }
//
//
//        System.out.println("maxArea : " + maxArea);

//        Mat se = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3, 3));
//        Imgproc.morphologyEx(dst, dst, Imgproc.MORPH_OPEN, se);
//        Mat se2 = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(7, 7));
//        Imgproc.morphologyEx(dst, dst, Imgproc.MORPH_CLOSE, se2);
//
//        Mat points = Mat.zeros(new Size(), 10);
//       Core.findNonZero(se2, dst);


//        Mat image_original;
//        Point p1,p2,p3,p4;
//        Rect rectCrop = new Rect(p1.x, p1.y , (p4.x-p1.x+1), (p4.y-p1.y+1));
//        Mat image_output= image_original.submat(rectCrop);

//        Mat image_original;
//        Point p1;
//        Point p2;
//        Point p3;
//        Point p4;

// crop 시작

//        Rect rectCrop = new Rect(10, 100 , 1000, 1000);
//        Mat image_output= dst.submat(rectCrop);


//        Imgproc.filter2D(dst, dst, -1, kernel1);
        Imgcodecs.imwrite(filePath + "process_test.jpg", dst);


//        HighGui.imshow("origin", ori);
//        HighGui.imshow("Process", dst);
//        HighGui.waitKey();
    }


}
