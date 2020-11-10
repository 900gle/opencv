package com.etoos.opencv.sample;

import info.debatty.java.lsh.LSHMinHash;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Arrays;
import java.util.Vector;

public class VectorTest {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        showLennaSIFT();
    }

    static int i = 1;
    static int stages = 1;
    static int buckets = 100;
    static int n = 300;


//    dense_vector로 색인해야 할것은 128차원의 Descriptor 정보입니다.
//    keypoint는 위치 방향 크기 정보이고 이것을 벡터화 한것이 Descriptor입니다.
//    kp1, desc1 = sift.detectAndCompute(imgray, None)
//
//    print(desc1)
//    doc = {"name": i, "feature": desc1[0]}
//    윗값이 128차원 벡터 값입니다.


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

            Vector<Vector<Double>> matVector = new Vector<>();

            SIFT.create().detectAndCompute(imageAvengers, mask, keyPointOfAvengers, discripters, false);

            System.out.println("size : " + discripters.size(0));
            System.out.println("size : " + discripters.size(1));


//            Double[][] vectors = new Double[discripters.size(1)][discripters.size(0)];
            int[][] hashes = new int[discripters.size(1)][];

            Vector<Double> doubleVector = new Vector<>();

            double sum = 0;
            for (int i = 0; i < discripters.size(1); i++) {

                for (int j = 0; j < discripters.size(0); j++) {
                    sum += (discripters.get(j, i)[0]);
                }

                doubleVector.add(sum);

                System.out.println(sum);

                sum = 0;

//                hashes[i] = lsh.hash(vectors[i]);

                System.out.println("__");

            }
            Arrays.stream(hashes).forEach(x -> System.out.println(x[0]));
            System.out.println(hashes.length);
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

