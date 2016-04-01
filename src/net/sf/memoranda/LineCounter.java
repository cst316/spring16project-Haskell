package net.sf.memoranda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter {

	String line = null;

	LineCounter() {
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
						
						if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("txt"))
							System.out.println("File= " + folder.getAbsolutePath() + "\\" + fileEntry.getName());
					}

				}
			}

		} catch (NullPointerException ex) {
			System.out.println("Unable to open Folder");
		}

	}

}