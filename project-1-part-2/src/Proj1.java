import java.io.FileNotFoundException;

public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.err.println("Usage: java Parser <WineDataset.csv> <input.txt>");
            System.exit(1);
        }
        new Parser(args[0], args[1]);  // Load the dataset and process input commands
    }
}
