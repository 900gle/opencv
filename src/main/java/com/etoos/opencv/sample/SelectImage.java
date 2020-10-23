package com.etoos.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Range;
import org.opencv.core.Rect;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class SelectImage {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String filePath="./images/";

        int y1 = 300;
        int y2 = 600;
        int x1 = 250;
        int x2 = 750;

        Range xRange = new Range(x1, x2);
        Range yRange = new Range(y1, y2);



        Mat img = Imgcodecs.imread(filePath + "faces_in_image.jpg", Imgcodecs.IMREAD_COLOR);
        Rect rectCrop = new Rect(100,20,100,200);

        Mat subImage= new Mat(img, rectCrop);



        System.out.println(subImage);



//        Imgcodecs.imwrite(filePath +"2.png",subImage);





//        Mat ret_img = new Mat();

//        ret_img = img.submat(100,100,100,100);

        HighGui.imshow("test", subImage);
        HighGui.waitKey();

//        ret_img = img.submat(300,600,250,750);


//        HighGui.imshow("ret_image",ret_img);







    }
}
