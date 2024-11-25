/**********************************************************************
 * @file: Parser.java
 * @description: Parses input commands defined by the user
 * @author: Kate Choi
 * @date: 17 September 2024
 **********************************************************************/

import java.io.*;
import java.util.Scanner;

public class Parser {

    //Create a BST tree of Integer type
    private BST<Integer> mybst = new BST<>();

    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim(); // remove leading/trailing spaces
            if (!line.isEmpty()) {
                String[] command = line.split("\\s+"); // split by 1+ spaces
                operate_BST(command);
            }
        }
        scanner.close();
    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        switch (command[0]) {
            case "insert" -> {
                if (command.length == 2) {
                    try {
                        int val = Integer.parseInt(command[1]);
                        mybst.insert(val);
                        writeToFile("insert " + val, "./result.txt");
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }
            case "search" -> {
                if (command.length == 2) {
                    try {
                        int val = Integer.parseInt(command[1]);
                        Node<Integer> n = mybst.search(val);
                        if (n != null) {
                            writeToFile("found " + val, "./result.txt");
                        } else {
                            writeToFile("search failed", "./result.txt");
                        }
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }
            case "remove" -> {
                if (command.length == 2) {
                    try {
                        int val = Integer.parseInt(command[1]);
                        Node<Integer> removedNode = mybst.remove(val);
                        if (removedNode != null) {
                            writeToFile("removed " + val, "./result.txt");
                        } else {
                            writeToFile("remove failed", "./result.txt");
                        }
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }
            case "print" -> {
                StringBuilder result = new StringBuilder();
                for (Integer val : mybst) {
                    result.append(val).append(" ");
                }
                writeToFile(result.toString().trim(), "./result.txt");
            }

            // default case for Invalid Command
            default -> writeToFile("Invalid Command", "./result.txt");
        }
    }

    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
