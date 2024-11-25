/**********************************************************************
 * @file: Proj1.java
 * @description: Main file to run program and make it executable from the command line.
 * @author: Kate Choi
 * @date: 17 September 2024
 **********************************************************************/

import java.io.FileNotFoundException;

public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 1){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser(args[0]);
    }
}
