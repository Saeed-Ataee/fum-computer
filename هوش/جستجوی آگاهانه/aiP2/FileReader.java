package aiP2;

import java.io.*;

public class FileReader {

	private String fileName, fileContent = null, line;

	public FileReader(String f) {
		fileName = f;
		reader();
	}

	private void reader() {
		try {
			fileContent = "";
			// FileReader reads text files in the default encoding
			Reader fileReader = new java.io.FileReader(fileName);

			// Wrapping FileReader in BufferedReader
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				fileContent += line;
				fileContent += '\n';
			}

			// Closing the file
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	/**
	 * Get content of file
	 * 
	 * @return String
	 */
	public String getFileContent() {
		return fileContent;
	}

}
