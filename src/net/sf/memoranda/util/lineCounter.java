package net.sf.memoranda.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class lineCounter {

	String line = null;

	public lineCounter() {
	}

	/*
	 * @param the absolute path of the location of the file being read
	 * 
	 * @return Returns the number of lines as an int or -1 if there was an error
	 * 
	 */

	public int getLines(String fileName) {
		int lineCount = 0;

		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while (bufferedReader.readLine() != null) {
				lineCount++;
			}
			bufferedReader.close();
			return lineCount;

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open '" + fileName);
		} catch (IOException ex) {
			System.out.println("Error reading file: " + fileName);
		}
		return -1;

	}

	/*
	 * http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
	 * 
	 * @param the absolute path of the location of the file directory
	 * 
	 */
	public void readDirectory(String fileLocation) {
		try {
			File folder = new File(fileLocation);
			for (final File fileEntry : folder.listFiles()) {

				if (fileEntry.isDirectory()) {
					readDirectory(fileEntry.getAbsolutePath());
				} else {
					if (fileEntry.isFile()) {
						String temp = fileEntry.getName();
						if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("java"))
						{
							String loc = Util.getEnvDir();
							try {
								Writer output = new BufferedWriter(new FileWriter(loc + "lines.txt", true));
								output.append("\nFile= " + "\\" + fileEntry.getName() + " line count= " + getLines(folder.getAbsolutePath() + "\\" + fileEntry.getName()));
								output.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}

				}
			}

		} catch (NullPointerException ex) {
			System.out.println("Unable to open Folder");
		}

	}

	public static void setFileLOC(String path) {

		String fileLocation = Util.getEnvDir();
		try {
			Writer output = new BufferedWriter(new FileWriter(fileLocation + "lines.txt", false));
			output.write(path);
			output.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void updateLineFile() {
		String fileLocation = Util.getEnvDir();
		try {
			if(!(new File(fileLocation + "lines.txt")).exists()){
				FileReader fileReader = new FileReader(fileLocation + "lines.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String loc = bufferedReader.readLine();
				bufferedReader.close();
				//Writer output = new BufferedWriter(new FileWriter(fileLocation + "lines.txt", false));
				PrintWriter output = new PrintWriter(fileLocation + "times.txt", "UTF-8");
				output.write(loc);
				output.close();
				readDirectory(loc);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open '" + fileLocation);
		} catch (IOException ex) {
			System.out.println("Error reading file: " + fileLocation);
		}

	}

}