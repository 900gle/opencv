package com.etoos.opencv.sample;

import info.debatty.java.lsh.LSHMinHash;
import info.debatty.java.lsh.LSHSuperBit;
import info.debatty.java.lsh.MinHash;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HathLSHVector {

    public static void main(String[] args) {
        int count = 1;

        // R^n
        int n = 3;

        int stages = 2;
        int buckets = 4;

        // Produce some vectors in R^n
        Random r = new Random();

        double[][] vectors = new double[count][];
        for (int i = 0; i < count; i++) {
            vectors[i] = new double[n];

            for (int j = 0; j < n; j++) {
                vectors[i][j] = 2 + j;
            }
        }




        try {
            LSHSuperBit lsh = new LSHSuperBit(stages, buckets, n);

            double[] vector = vectors[0];


            System.out.println(vector.length);




            int[] hash = lsh.hash(vector);

            System.out.println("length : " + hash.length);


            Arrays.stream(hash).forEach(x-> System.out.println("x " + x));


            System.out.println();

        } catch (Exception ex) {
            Logger.getLogger(LSHSuperBitExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
