package com.etoos.opencv.sample;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MaskingImage {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source=null;
        Mat template=null;
        String filePath="./images/";

        source= Imgcodecs.imread(filePath+"avengers.jpg");
        template=Imgcodecs.imread(filePath+"title.png");

        Mat outputImage=new Mat();
        int machMethod= Imgproc.TM_CCOEFF;
        Imgproc.matchTemplate(source, template, outputImage, machMethod);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc=mmr.maxLoc;

//        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(),
//                matchLoc.y + template.rows()), new Scalar(255, 255, 255));

        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(255, 255, 255, 0));


        HighGui.imshow("masking test", source);
        HighGui.waitKey();


//        Imgcodecs.imwrite(filePath+"template_masking.png", source);
//        System.out.println("Complated.");




//




    }
}
