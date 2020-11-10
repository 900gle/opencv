package com.etoos.opencv.sample;

import info.debatty.java.lsh.LSHMinHash;

import java.io.*;
import java.util.Random;

public class SerializeExample {


        public static void main(String[] args)
                throws IOException, ClassNotFoundException {

            // Create a single random boolean vector
            int n = 100;
            double sparsity = 0.75;
            boolean[] vector = new boolean[n];
            Random rand = new Random();
            for (int j = 0; j < n; j++) {
                vector[j] = rand.nextDouble() > sparsity;
            }

            // Create and configure LSH
            int stages = 2;
            int buckets = 10;
            LSHMinHash lsh = new LSHMinHash(stages, buckets, n);
            println(lsh.hash(vector));

            // Create another LSH object
            // as the parameters of the hashing function are randomly initialized
            // these two LSH objects will produce different hashes for the same
            // input vector!
            LSHMinHash other_lsh = new LSHMinHash(stages, buckets, n);
            println(other_lsh.hash(vector));

            // Moreover, signatures produced by different LSH objects cannot
            // be used to compute estimated similarity!
            // The solution is to serialize and save the object, so it can be
            // reused later...
            File tempfile = File.createTempFile("lshobject", ".ser");
            FileOutputStream fout = new FileOutputStream(tempfile);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(lsh);
            oos.close();
            System.out.println(
                    "LSH object serialized to " + tempfile.getAbsolutePath());

            FileInputStream fin = new FileInputStream(tempfile);
            ObjectInputStream ois = new ObjectInputStream(fin);
            LSHMinHash saved_lsh = (LSHMinHash) ois.readObject();
            println(saved_lsh.hash(vector));
        }

        static void println(int[] array) {
            System.out.print("[");
            for (int v : array) {
                System.out.print("" + v + " ");
            }
            System.out.println("]");
        }


}
