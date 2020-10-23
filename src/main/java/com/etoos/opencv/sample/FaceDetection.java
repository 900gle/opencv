package com.etoos.opencv.sample;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetection {


    public static void main(String[] args) {
        //Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        //Reading the Image from the file
        String file ="./images/faces_in_image.jpg";
        Mat src = Imgcodecs.imread(file);
        //Instantiating the CascadeClassifier
        String xmlFile = "lbpcascade_frontalface.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFile);
        //Detecting the face in the snap
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(src, faceDetections);
        System.out.println(String.format("Detected %s faces",
                faceDetections.toArray().length));
        //Drawing boxes
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                    src,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255),
                    3
            );
        }
        //Writing the image
        Imgcodecs.imwrite("./images/faces_in_image_result.jpg", src);
        System.out.println("Image Processed");
    }



}
