package com.etoos.opencv.sample;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;


public class TemplateMatching {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source=null;
        Mat template=null;
        String filePath="./images/";

        source= Imgcodecs.imread(filePath+"resource.png");
        template=Imgcodecs.imread(filePath+"temp.png");

        Mat outputImage=new Mat();
        int machMethod= Imgproc.TM_CCOEFF;
        Imgproc.matchTemplate(source, template, outputImage, machMethod);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc=mmr.maxLoc;

        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(255, 255, 255));




        Imgcodecs.imwrite(filePath+"result.png", source);
        System.out.println("Complated.");
    }
}
