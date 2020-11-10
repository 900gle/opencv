package com.etoos.opencv.sample;

import info.debatty.java.lsh.LSHSuperBit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LSHSuperBitExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int count = 100;

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
                vectors[i][j] = r.nextGaussian();
            }
        }
        try {
            LSHSuperBit lsh = new LSHSuperBit(stages, buckets, n);

            // Compute a SuperBit signature, and a LSH hash
            for (int i = 0; i < count; i++) {
                double[] vector = vectors[i];
                int[] hash = lsh.hash(vector);
                for (double v : vector) {
                    System.out.printf("%6.2f\t", v);
                }
                System.out.print(hash[0]);
                System.out.print("\n");
            }
        } catch (Exception ex) {
            Logger.getLogger(LSHSuperBitExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
