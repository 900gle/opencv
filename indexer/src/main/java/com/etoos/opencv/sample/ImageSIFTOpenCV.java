package com.etoos.opencv.sample;

import info.debatty.java.lsh.LSHMinHash;
import org.opencv.core.*;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.List;
import java.util.Vector;

public class ImageSIFTOpenCV {



    static int i = 1;
    static int stages = 2;
    static int buckets = 10;
    static int n = 100;
    static final String FILE_PATH = "/Users/doo/project/opencv/web/src/main/resources/static/images/";
    static final String FILE_NAME = "search_test4.png";

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        showLennaSIFT();
    }

    public static void showLennaSIFT() {
        try {


            Mat imageAvengers = Imgcodecs.imread(FILE_PATH + FILE_NAME);
            MatOfKeyPoint keyPointOfAvengers = new MatOfKeyPoint();
            SIFT.create().detect(imageAvengers, keyPointOfAvengers);

            System.out.println(keyPointOfAvengers);

            LSHMinHash lsh = new LSHMinHash(stages, buckets, n);

//            lsh.hash()
//            keyPointOfAvengers.toList().stream().forEach(x-> {System.out.println( x); i++;} );

            Vector<KeyPoint> keyPointVector = new Vector<>();
            List<KeyPoint> keyPoints = keyPointOfAvengers.toList();

            for (KeyPoint keyPoint : keyPoints) {
                keyPointVector.add(keyPoint);
                System.out.println(keyPoint);
//                i++;
            }


//            keyPointVector.stream().forEach(x-> System.out.println(x));

            System.out.println(" int i ::: " + i);

//            System.out.println("keyPointOfAvengers : " + keyPointOfAvengers);

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

