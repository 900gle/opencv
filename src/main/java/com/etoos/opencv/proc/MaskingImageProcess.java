package com.etoos.opencv.proc;

import com.etoos.opencv.dto.PointDTO;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MaskingImageProcess {

    public boolean process(PointDTO pointDTO){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filePath= pointDTO.getFilePath();

        Mat source = Imgcodecs.imread(filePath);
        int thickness = Imgproc.FILLED;
        Imgproc.rectangle(source, new Point(pointDTO.getX(), pointDTO.getY()), new Point(pointDTO.getWidth() ,
                pointDTO.getHeight()), new Scalar(0,0,0), thickness);

        String fileDir = "./images/";
        Imgcodecs.imwrite(fileDir +"masking_" + pointDTO.getFileName(), source);

        File file = new File(pointDTO.getFilePath());
        if(file.exists()){
            file.delete();
        }
        System.out.println("Complated.");
        return true;
    }
}
