/*
How to read all files in a folder from Java?

Asked 10 years, 1 month ago | Active 7 months ago | Viewed 1.0m times

https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
*/

package org.tonyh.listfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListFilesForDirectory {
	
	public static void listFilesForFolder(final File folder) {
		// Uses java.io.File
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
			}
		}
	}

	public static void doPrint(String str) {
		System.out.println("String filename: " + str);
	}
	
	/*
	 * JAVA 8
	 * 5. List files of certain extention with Files.newDirectoryStream() You can
	 * change the path filter expression passed in second argument to get files of
	 * certain extension only.
	 */
	public static void listFilesWithExtension(String folder) {
		// Uses java.nio.file.Files &  java.nio.file.Paths
		try {
			Files.newDirectoryStream( Paths.get(folder),
					path -> path.toString().endsWith(".xlsx"))
					.forEach( System.out::println);;
		} catch (IOException e) {
			System.out.println("ERROR: File not found!\n");
			e.printStackTrace();
		}
	}
	
	/*
	 * Java 8 : Get files from folder / subfolder [duplicate]
	 * Asked 1 year, 11 months ago | Active 1 month ago | Viewed 18k times
	 * https://stackoverflow.com/questions/48563709/java-8-get-files-from-folder-subfolder/48563887
	 * Uses:
	 * 	import java.io.IOException;
	 * 	import java.nio.file.FileSystems;
	 * 	import java.nio.file.Files;
	 * 	import java.nio.file.Path;
	 * 	import java.util.List;
	 * 	import java.util.stream.Collectors;
	 */
	public static void listFilesWithExtension1(String folder) {
		Path configFilePath = FileSystems.getDefault().getPath( folder );

	    List<Path> fileWithName = null;
		try {
			fileWithName = Files.walk(configFilePath)
			        .filter(s -> s.toString().endsWith(".xlsx"))
			        .map(Path::getFileName).sorted().collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error: File not found!");
			e.printStackTrace();
		}

	    for (Path name : fileWithName) {
	        // printing the name of file in every sub folder
	    	System.out.println(name);
	    }
	}
	
	public static void main(String[] args) {
		String[] dirList = {
				"F:\\ProjectDir\\Pty_Area_Srch\\Results_Bucket_Dir",
				"F:\\ProjectDir\\Pty_Area_Srch",
				"F:\\ProjectDir\\Pty_Area_Srch\\Results_Croydon" };
		
		final File folder = new File( dirList[0]);
		System.out.println("\n#10 Call: listFilesForFolder(folder); - Recursively List all files dir tree.");
		listFilesForFolder(folder);
		
		System.out.println("\n#20 Call: listFilesWithExtension(dirList[0]); - List all .XLSX files.");
		listFilesWithExtension(dirList[0]);
		
		System.out.println("\n#30 Call: listFilesWithExtension1(dirList[2]); - List all .XLSX files.");
		listFilesWithExtension1(dirList[2]);
	
	}

}
