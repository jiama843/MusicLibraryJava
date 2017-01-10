import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 * This class contains all the attributes and behaviours for reading from a file and writing to a file
 * @author Ms. Cianci
 * @since  2012
 *
 * Some modifications: Two new methods were added. A println(Url url) and a println(File file)
 * They allow you to print a URL and a File as a String.
 *
 */

public class IOTextFile {

		//WRITING AN IO FILE
		private static PrintWriter fileOut;
		
		//Create a new File, stored in the current folder
		public static void createOutputFile(String fileName)
		{
			createOutputFile(fileName, false);
			try{
				fileOut = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			}
			catch(IOException e){
				System.out.println("***Cannot create file: "+ fileName + " ***");
			}
		}
		
		//Adds to the Existing File IF our append boolean is set to TRUE
		//and rewrites the file IF set to FALSE
		public static void createOutputFile(String fileName, boolean append)
		{
			try{
				fileOut = new PrintWriter(new BufferedWriter(new FileWriter(fileName, append)));
			}
			catch(IOException e){
				System.out.println("***Cannot create file: "+ fileName + " ***");
			}
		}
		
		public static void print(String text)
		{
			fileOut.print(text);
		}
		
		public static void println(String text)
		{
			fileOut.println(text);
		}
		
		public static void println(File file)
		{
			fileOut.println(file);
		}
		
		public static void println(URL url)
		{
			fileOut.println(url);
		}
		
		//MUST call this method when we are done writing to a file
		//IN ORDER TO SAVE IT
		public static void closeOutputFile()
		{
			fileOut.close();
		}
		
		//READING FROM A FILE
		private static BufferedReader fileIn;
		
		public static void openInputFile(String fileName)
		{
			try{
				fileIn = new BufferedReader(new FileReader(fileName));
			}
			catch(FileNotFoundException e)
			{
				System.out.println("*** Cannot open: " + fileName + " ***");
			}
		}
		
		public static String readLine()
		{
			try{
				return fileIn.readLine();
			}
			catch(IOException e){}
			return null;
		}
		
		public static void closeInputFile()
		{
			try{
				fileIn.close();
			}
			catch(IOException e){}
		}
		
}






