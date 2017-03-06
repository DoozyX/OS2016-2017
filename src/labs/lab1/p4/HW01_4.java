package labs.lab1.p4;

import java.io.File;
import java.util.Scanner;

/**
 * Task 4 Problem 4
 * Write a Java program which takes two arguments: the location of a text file and a word.
 * The program should print out the number of appearances of the word (the second argument) in the text file (the first argument).
 */
public class HW01_4 {
	static int countWord(File file, String word) throws Exception {
		Scanner scanner = new Scanner(file);
		int count = 0;

		while (scanner.hasNextLine()) {
			String words = scanner.next();
			if (words.equalsIgnoreCase(word)) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 2) {
			File file = new File(args[0]);
			if (file.exists()) {
				System.out.println(countWord(file, args[1]));
			}
		} else {
			System.out.println("Enter the file path and word in console argument.");
		}
	}
}
