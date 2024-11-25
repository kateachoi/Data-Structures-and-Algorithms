# Project 1 @ CSC 201 Fall 2024: Binary Search Tree (Part 1)

## Pledged Work Policy

This is a ___Pledged Work___ assignment.  This means that the work you submit for grading ___must___ be your work product.  
You may not submit the work of others outside of your team , or the modification of work of others outside of your team.
You are encouraged to talk with each other about general problems.  For example, you may talk to someone about "What does it mean when the compiler says there is a semicolon missing on line 20", or "I can not get my assignment template to download from GitHub, what did you do?".  However, you may not engage in "Could you send me a copy of your work so I can see how to get started?".  You may get full and detailed assistance from me, the Teaching Assistant (TA), and the TAs in the Computer Science Center.  If you have any question about the appropriateness of assistance, do not hesitate to consult with me.

If I believe you have violated our ___Pledge Work___ agreement, I will pursue this matter through the college Honor Council.

## Overview

Database software typically comprises two essential components: an interface for user interaction and a data structure for storing data and supporting various functions. This project involves the creation of a program that manages a Binary Search Tree (BST).

In this project, you will implement: 

1. A generic BST with iterator interface.
2. A parser capable of reading and processing input commands.

## Invocation and I/O Files:

The name of the program is `Proj1` ( provided with a `main` method in`Proj1.java` ). 

You are encouraged to run and debug code in __IntelliJ IDEA__. Also, the program can be invoked from the command-line as:

```shell
java Proj1 {command-file}
```

For Sections 1 and 2, your program will read a series of commands from the input file `input.txt` and generate the output file  `result.txt`, which will be used for grading.


## 1. **Generic BST with Iterator Interface**

In this part, you will construct **a generic and iterable Binary Search Tree (BST)**.  

First, create a `Node` class with **Comparable interface** to represent nodes of the BST. Each node should encompass a value, references to both left and right subtrees, along with necessary utility methods as instructed in file `Node.java`.

Then, develop a `BST` class to manage the structure of BST. Please finish the implementation in file `BST.java`.

Make sure these operations of BST are handled in a general way that does not require the BST to understand the type of its elements. For example, the `value` in `Node` may be a user-defined data structure with `Comparable` interface, necessitating the use of `compareTo() ` method for comparison.

## 2. **Parse Input Commands**

Your program will read a series of commands from the input file, with one command per line. The commands are free-format in that any number of spaces may come before, between, or after the command name and its parameters.

You're going to implement `process()` ,`operate_BST() `, and `writeToFile()` methods in `Parser` class for this part.

In `process()` method, you will read the command file line by line, remove redundant spaces, ignore blank lines, to handle different input formatting. 

Then, you need to split each line into string array `command`, then create your  `operate_BST(String[] command)` method to test your BST. 

After this, use the `writeToFile()` method to write your BST result into `result.txt`. In the `writeToFile()` method, you're asked to open the specified file (create it if it doesn't exist), append a new line at the end of the file, and write the output  (`String content`).

The `BST` class shall support the following commands. (Check example commands in the provided input file):

+ `insert val`: 

  Insert a new `Node` with value of `val` into BST. Write `insert <val>` to `result.txt`.

  ```
  ## sample input.txt
  insert 23
  
  ## sample result.txt
  insert 23
  ```

+ `search val`: 

  Searches the BST to find out if a node with value `val` exists.

  If found, return the `Node`, and write `found <val>` to `result.txt`, otherwise return `null` and write `search failed` to `result.txt`.

  ```
  ## sample input.txt
  search 23
  search 114
  
  ## sample result.txt
  found 23
  search failed
  ```

+ `remove val`:

  Removes a node with value `val` from the BST, return this node, and write `removed <val>` to `result.txt`. If the value does not exist in the tree, return `null` and write `remove failed` to `result.txt`.

  ```
  ## sample input.txt
  remove 23
  remove 114
  
  ## sample result.txt
  removed 23
  remove failed
  ```

+ `print`:

   Implement an iterator in class `BST`, and print out elements in the BST in ascending order to  `result.txt`. 

  ```
  ## sample input.txt
  print
  ## sample result.txt
  2 3 44 214
  ```
  
+ invalid command:

   Write `Invalid Command` to `result.txt`. 
   
   ```
   ## sample input.txt
   Hello CSC 201
   
   ## sample result.txt
   Invalid Command
   ```

Note that you need to **implement an iterator** interface that enables the iterator to traverse all nodes in the BST in ascending order. 

## Submission:

Your project will be developed and graded via GitHub. Your final "push" is your final submission, and it must occur before it is due. On Canvas, enter the url to your Github repository. Your project will not be graded without it.

## Recommendations:

I ___strongly suggest___ that you carefully think through your strategy before just jumping into the code.  Once that is working, start adding in new features individually.  A good place to start is building your class.

*In order to get full points of Commenting and Code Style, you need to add comments to every methods and head comments for each file (providing file description, author, date, and acknowledgement).

```
/∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*
∗ @file: filename.java
∗ @description: This program implements . . .
∗ @author: Your Name
∗ @date: September 20 , 2024
∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗/
```
