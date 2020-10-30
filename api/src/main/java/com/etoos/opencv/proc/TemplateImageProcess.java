package com.etoos.opencv.proc;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

@Component
public class TemplateImageProcess {


    public boolean process(){
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


        Imgcodecs.imwrite(filePath+"template_matching.png", source);
        return true;
    }
}
