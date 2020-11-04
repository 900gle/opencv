package com.etoos.opencv.sample;

import info.debatty.java.lsh.LSHMinHash;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageSIFTOpenCV {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        showLennaSIFT();


    }
    static int  i =1;

//    static int stages = 2;
//    static int buckets = 10;
//    static int n = 100;


    public static void showLennaSIFT() {
        try {

            String filePath="/Users/doo/project/opencv/images/";
//            String filePath="./img/";

            Mat imageAvengers = Imgcodecs.imread(filePath+"avengers.jpg");
            // Start SIFT KeyPoint
            MatOfKeyPoint keyPointOfAvengers = new MatOfKeyPoint();
            SIFT.create().detect(imageAvengers, keyPointOfAvengers);


            System.out.println(keyPointOfAvengers);

//            LSHMinHash lsh = new LSHMinHash(stages, buckets, n);


//            lsh.hash()

            keyPointOfAvengers.toList().stream().forEach(x-> {System.out.println( x); i++;} );


            System.out.println(" int i ::: "+ i);

            System.out.println("keyPointOfAvengers : " + keyPointOfAvengers);

            Mat keyPointAvengers = new Mat();

            Features2d.drawKeypoints(imageAvengers, keyPointOfAvengers
                    , keyPointAvengers, new Scalar(0, 0, 255)
                    , Features2d.DrawMatchesFlags_DRAW_RICH_KEYPOINTS);
            // End SIFT KeyPoint

            // Show
            HighGui.imshow("SIFT KeyPoint", keyPointAvengers);

            HighGui.waitKey();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

