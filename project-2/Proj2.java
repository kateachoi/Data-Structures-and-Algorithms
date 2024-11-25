import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = new FileInputStream(inputFileName);
        Scanner inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // store dataset in an ArrayList
        ArrayList<Chocolate> chocolates = new ArrayList<>();

        // read up to specified number of lines
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

        // close input streams
        inputFileNameScanner.close();
        inputFileNameStream.close();

        // create copies of data for sorted and shuffled versions
        ArrayList<Chocolate> sortedData = new ArrayList<>(chocolates);
        ArrayList<Chocolate> shuffledData = new ArrayList<>(chocolates);

        // sort and shuffle data
        Collections.sort(sortedData);
        Collections.shuffle(shuffledData);

        // create BST and AVL trees
        BST<Chocolate> bstSorted = new BST<>();
        BST<Chocolate> bstShuffled = new BST<>();
        AvlTree<Chocolate> avlSorted = new AvlTree<>();
        AvlTree<Chocolate> avlShuffled = new AvlTree<>();

        // measure insertion times
        long bstSortedInsertTime = measureInsertionTime(bstSorted, sortedData);
        long bstShuffledInsertTime = measureInsertionTime(bstShuffled, shuffledData);
        long avlSortedInsertTime = measureInsertionTime(avlSorted, sortedData);
        long avlShuffledInsertTime = measureInsertionTime(avlShuffled, shuffledData);

        // measure search times
        long bstSearchTime = measureSearchTime(bstSorted, chocolates);
        long avlSearchTime = measureSearchTime(avlSorted, chocolates);

        // print results to console
        System.out.printf("BST Insert (Sorted): %d ns%n", bstSortedInsertTime);
        System.out.printf("BST Insert (Shuffled): %d ns%n", bstShuffledInsertTime);
        System.out.printf("AVL Insert (Sorted): %d ns%n", avlSortedInsertTime);
        System.out.printf("AVL Insert (Shuffled): %d ns%n", avlShuffledInsertTime);
        System.out.printf("BST Search: %d ns%n", bstSearchTime);
        System.out.printf("AVL Search: %d ns%n", avlSearchTime);

        // write results to output.txt
        try (FileOutputStream out = new FileOutputStream("output.txt", true)) {
            String result = String.format("%d,%d,%d,%d,%d,%d,%d%n",
                    numLines, bstSortedInsertTime, bstShuffledInsertTime,
                    avlSortedInsertTime, avlShuffledInsertTime,
                    bstSearchTime, avlSearchTime);
            out.write(result.getBytes());
        }

    }

    private static <T extends Comparable<T>> long measureInsertionTime(BST<T> tree, ArrayList<T> data) {
        long start = System.nanoTime();
        for (T value : data) {
            tree.insert(value);
        }
        return System.nanoTime() - start;
    }

    private static <T extends Comparable<T>> long measureInsertionTime(AvlTree<T> tree, ArrayList<T> data) {
        long start = System.nanoTime();
        for (T value : data) {
            tree.insert(value);
        }
        return System.nanoTime() - start;
    }

    private static <T extends Comparable<T>> long measureSearchTime(BST<T> tree, ArrayList<T> data) {
        long start = System.nanoTime();
        for (T value : data) {
            tree.search(value);
        }
        return System.nanoTime() - start;
    }

    private static <T extends Comparable<T>> long measureSearchTime(AvlTree<T> tree, ArrayList<T> data) {
        long start = System.nanoTime();
        for (T value : data) {
            tree.contains(value);
        }
        return System.nanoTime() - start;
    }
}
