package com.etoos.opencv.component;

import com.etoos.opencv.dto.ImageIndexDTO;
import com.etoos.opencv.dto.ImageSearchDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class ImageToVectorTensorflow {

    static final String BASE_DIR = "/Users/doo/project/opencv/common/temp";

    public static Vector<Double> getVector(ImageSearchDTO imageSearchDTO) throws IOException {

        String filePath = BASE_DIR + "/" + imageSearchDTO.getFile().getOriginalFilename();
        imageSearchDTO.getFile().transferTo(new File(filePath));

        String result = SendRestUtil.sendRest("http://127.0.0.1:5000/api/" + imageSearchDTO.getFile().getOriginalFilename(), "");
        try {

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(result);
            JSONObject jsonObj = (JSONObject) obj;

            String step1 = jsonObj.get("vectors").toString().replace("[", "");
            String step2 = step1.replace("]", "");

            Vector<Double> vector = new Vector<>();
            Arrays.stream(step2.split(",")).forEach(x -> {
                vector.add(Double.valueOf(x));
            });

            return vector;

        } catch (ParseException p) {
            p.getStackTrace();
        }

        return null;
    }

    public static Vector<Double> getVector(ImageIndexDTO imageIndexDTO) throws IOException {

        String result = SendRestUtil.sendRest("http://127.0.0.1:5000/vectors/" + imageIndexDTO.getImageName(), "");
        try {

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(result);
            JSONObject jsonObj = (JSONObject) obj;

            String step1 = jsonObj.get("vectors").toString().replace("[", "");
            String step2 = step1.replace("]", "");

            Vector<Double> vector = new Vector<>();
            Arrays.stream(step2.split(",")).forEach(x -> {
                vector.add(Double.valueOf(x));
            });

            return vector;

        } catch (ParseException p) {
            p.getStackTrace();
        }

        return null;
    }

}
