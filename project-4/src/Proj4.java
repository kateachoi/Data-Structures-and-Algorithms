/**********************************************************************
 * @file: Proj4.java
 * @description: Main driver program for reading datasets, timing hash table operations, and outputting results.
 * @author: Kate Choi
 * @date: 4 December 2024
 **********************************************************************/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // store data in ArrayList
        ArrayList<Chocolate> chocolates = new ArrayList<>();
        while (inputFileNameScanner.hasNextLine() && chocolates.size() < numLines) {
            String line = inputFileNameScanner.nextLine();
            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Handle commas within quotes

            // Parse the fields safely
            String company = fields[0].trim();
            String origin = fields[1].trim();
            int ref = Integer.parseInt(fields[2].trim());
            int reviewDate = Integer.parseInt(fields[3].trim());
            double cocoaPercent = Double.parseDouble(fields[4].replace("%", "").trim());
            String location = fields[5].trim();
            double rating = Double.parseDouble(fields[6].trim());

            Chocolate chocolate = new Chocolate(company, origin, ref, reviewDate, cocoaPercent, location, rating);
            chocolates.add(chocolate);
        }

        // hash table initialization
        SeparateChainingHashTable<Chocolate> hash = new SeparateChainingHashTable<>();

        for (String order : new String[]{"sorted", "shuffled", "reversed"}) {
            switch (order) {
                case "sorted":
                    Collections.sort(chocolates);
                    break;
                case "shuffled":
                    Collections.shuffle(chocolates);
                    break;
                case "reversed":
                    Collections.sort(chocolates, Collections.reverseOrder());
                    break;
            }

            long insertTime = 0, searchTime = 0, deleteTime = 0;

            // insert
            long start = System.nanoTime();
            for (Chocolate chocolate : chocolates) {
                hash.insert(chocolate);
            }
            insertTime = System.nanoTime() - start;

            // search
            start = System.nanoTime();
            for (Chocolate chocolate : chocolates) {
                hash.contains(chocolate);
            }
            searchTime = System.nanoTime() - start;

            // delete
            start = System.nanoTime();
            for (Chocolate chocolate : chocolates) {
                hash.remove(chocolate);
            }
            deleteTime = System.nanoTime() - start;

            // log results
            System.out.printf("Number of Lines: %d\n", numLines);
            System.out.printf("Order: %s\nInsert Time: %d ns\nSearch Time: %d ns\nDelete Time: %d ns\n",
                   order, insertTime, searchTime, deleteTime);

            // write to txt
            File analysis = new File("analysis.txt");
            boolean isNewFile = !analysis.exists();
            try (FileOutputStream fos = new FileOutputStream(analysis, true)) {
                // write header if new
                if (isNewFile) {
                    String header = "numLines,insertTime,searchTime,deleteTime,order\n";
                    fos.write(header.getBytes());
                }
                // append data
                fos.write(String.format("%d,%d,%d,%d,%s\n",
                        numLines, insertTime, searchTime, deleteTime, order).getBytes());
            }
        }

    }
}
