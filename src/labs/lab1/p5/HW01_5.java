package labs.lab1.p5;

import java.io.*;

/**
 * Task 5 Problem 5
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
	public static void main(String[] args) throws IOException {

		String line = "";
		BufferedWriter output = null;

		try (BufferedReader br = new BufferedReader(new FileReader("results.csv"))) {
			line = br.readLine();
			String first[] = line.split(",");
			File file = new File("results.tsv");
			output = new BufferedWriter(new FileWriter(file));
			for (String string : first) {
				output.write(string + "\t");
			}
			output.write("\n");
			int nSubjects = first.length-1;
			int[] student = new int[nSubjects];
			int count = 0;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				int grades = 0;
				for (int i = 0; i < nSubjects+1; i++) {
					output.write(parts[i] + "\t");
					if (i == 0 ) {
						continue;
					}
					int grade = Integer.parseInt(parts[i]);
					grades += grade;
					student[i-1] += grade;
				}
				output.write("\n");
				System.out.println(parts[0] + " " +(double)grades/nSubjects);
				++count;
			}
			for (int i = 0; i < nSubjects; i++) {
				System.out.println(first[i+1] + " " + (double)student[i]/count);
			}
			output.flush();
		} finally {
			if (output != null) {
				output.close();
			}

		}
	}
}
