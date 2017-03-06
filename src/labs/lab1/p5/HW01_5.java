package labs.lab1.p5;

import java.io.File;

/**
 * Task 5 Problem 5 (0 / 0)
 * One e-learning system generates grade reports in a CSV (Comma Separated Values) format.
 * Write a Java program which will print out the average grade of each student from the file results.csv (see the example below),
 * and the average grade of each course (given in the first line of the file). The program should work with any number of lines in an input CSV file.
 * <p>
 * Student,SP,OOP,CAO
 * 11234,8,9,8
 * 13456,6,7,9
 * 11111,7,8,8
 * 10123,10,10,10
 * <p>
 * Bonus: For better readability of the file, transform the input file results.csv into a TSV (Tab Separated Values) formatted file, and save it as results.tsv.
 */
public class HW01_5 {
	public static void main(String[] args) {
		File file = new File("results.csv");
	}
}
