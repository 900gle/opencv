package com.etoos.opencv.sample;

import info.debatty.java.lsh.LSHMinHash;
import org.opencv.core.*;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.Vector;

public class IndexingTest {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        showLennaSIFT();
    }

    static int i = 1;
    static int stages = 1;
    static int buckets = 100;
    static int n = 300;

    public static void showLennaSIFT() {
        try {

            String filePath = "/Users/doo/project/opencv/images/";
//            String filePath="./img/";

            //avengers.jpg
            String imageName = "title.png";
//            String imageName = "avengers.jpg";

            Mat imageAvengers = Imgcodecs.imread(filePath + imageName);
            // Start SIFT KeyPoint
            MatOfKeyPoint keyPointOfAvengers = new MatOfKeyPoint();
//            SIFT.create().detect(imageAvengers, keyPointOfAvengers);

            double sparsity = 30.75;
            Mat discripters = new Mat();
//            System.out.println(discripters);

            Mat mask = new Mat();
            Mat r = new Mat();

//            Vector<Mat> matVector = new Vector<>();
            Vector<Vector<Double>> matVector = new Vector<>();

            SIFT.create().detectAndCompute(imageAvengers, mask, keyPointOfAvengers, discripters, false);

            System.out.println("size : " + discripters.size(0));
            System.out.println("size : " + discripters.size(1));
//            System.out.println("discripters : " + discripters.get(0, 0));

            LSHMinHash lsh = new LSHMinHash(stages, buckets, discripters.size(0));


            boolean[][] vectors = new boolean[discripters.size(1)][discripters.size(0)];
            int[][] hashes = new int[discripters.size(1)][];

            for (int i = 0; i < discripters.size(1); i++) {


                for (int j = 0; j < discripters.size(0); j++) {

                    System.out.print(discripters.get(j, i)[0]);

//                    System.out.println(j);

                    vectors[i][j] = discripters.get(j, i)[0] > sparsity;
                }


                hashes[i] = lsh.hash(vectors[i]);

                System.out.println("__");


//                System.out.println(lsh.hash(vectors[i]));

            }

            Arrays.stream(hashes).forEach(x-> System.out.println(x[0]));


            System.out.println(hashes.length);




            int stages = 2;
            int buckets = 10;



            System.out.println("--------------");
            System.out.println(discripters);
            System.out.println("--------------");


            if (discripters instanceof Mat) {
                System.out.println("Mat!!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

