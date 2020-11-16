package com.etoos.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Vector;
import java.util.stream.Stream;

public class TestSample {


    public static final double sparsity = 10;

    public static void main(String[] args) {


        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String filePath = "/Users/doo/project/opencv/images/";
        String imageName = "avengers.jpg";

        Mat image = Imgcodecs.imread(filePath + imageName);


        MatOfKeyPoint keyPointOfAvengers = new MatOfKeyPoint();
        SIFT.create().detect(image, keyPointOfAvengers);

        Mat discripters = new Mat();
        Mat mask = new Mat();
        SIFT.create().detectAndCompute(image, mask, keyPointOfAvengers, discripters);
        double sum = 0;

        Vector<Double> doubleVector = new Vector<>();



        for (int i = 0; i < discripters.size(1); i++) {
            StringBuilder binaryString = new StringBuilder();
            Vector<Boolean> booleanVector = new Vector<>();

            for (int j = 0; j < discripters.size(0); j++) {
//                sum += discripters.get(j, i)[0];
                booleanVector.add(discripters.get(j, i)[0] % 2 > 0 ? true : false);
            }

            double hashNum = booleanVector.hashCode();
            doubleVector.add(hashNum);
            System.out.println(hashNum);





//            double asdf = Integer.parseInt(binaryString.toString(), 2);

//            System.out.println(asdf);

//            sum =  Integer.valueOf(binaryString.toString(), 2);



//
//            System.out.println(sum);
//            binaryString.delete(0, binaryString.length());
//            doubleVector.add(sum);
//            sum = 0;
        }

//        doubleVector.stream().forEach(x -> System.out.println(x));




//        int a = 10;
//
//        //숫자를 2진수,8진수,16진수 형태의 문자열로 변환
//        String bin = Integer.toBinaryString(a);
//        String oct = Integer.toOctalString(a);
//        String hex = Integer.toHexString(a);
//
//        System.out.println("int -> 문자열 이진수 : " + bin);
//        System.out.println("int -> 문자열 8진수  : " + oct);
//        System.out.println("int -> 문자열 16진수 : " + hex);
//        System.out.println();
//
//        // 문자열 2,8,16진수를 숫자로 변환
//        System.out.println("문자열 이진수 -> int : " + Integer.valueOf(bin, 2));
//        System.out.println("문자열 8진수  -> int : " + Integer.valueOf(oct, 8));
//        System.out.println("문자열 16진수 -> int : " + Integer.valueOf(hex, 16));
//        System.out.println();
//
//        //2진수, 8진수, 16진수 리터럴, 각 진수를 표현하는 접미사는 대소문자 가리지 않음.
//        int binLiteral = 0b1010; //2진수 리터럴 (자바7버전 부터 지원)
//        int octLiteral = 012; //8진수 리터럴
//        int hexLiteral = 0xa; //16진수 리터럴
//
//        System.out.println("binLiteral:" +binLiteral); //10, 10진수로 출력
//        System.out.println("octLiteral:" +octLiteral); //10, 10진수로 출력
//        System.out.println("hexLiteral:" +hexLiteral); //10, 10진수로 출력
//
//
//


    }
}
