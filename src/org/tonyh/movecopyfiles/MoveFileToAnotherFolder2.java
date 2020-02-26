package org.tonyh.movecopyfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoveFileToAnotherFolder2 {

	public static void main(String[] args) {
		String inputDir  = "F:/ProjectDir/Pty_Area_Srch/Results_Bucket_Dir";
		String outputDir = "F:/ProjectDir/Pty_Area_Srch/Results_Bucket_Dir/done";

		System.out.println("\n#05 MoveFileToAnotherFolder2 > main(); - Match a areaName to a folder's Path.");	// delthis test

		// Process the list of .xlsx file in folder
		try (Stream<Path> walk = Files.walk(Paths.get(inputDir),
				1)	// Options: How many levels to traverse. Default is Integer.MAX_VALUE
		) {
			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(".xlsx"))
					.collect(Collectors.toList());
			
			// Print list of path/filename.
			// I Want to used the filename else could have printed thus: result.forEach(System.out::println);
			for (String name : result) {
				// Print the filename.			//loadData(name);	// Clean each data file
				System.out.println("#10 Filename : " + name);
				
				// Move Source file to Target folder based on the filename
				moveSourceFileToTargetFolder(name, outputDir);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void moveSourceFileToTargetFolder(String inName, String inOutputDir) {
		String inputFile = inName;
		String outputDir = inOutputDir;
		
		System.out.printf("#100 Input file [%s]\n", inputFile);
		System.out.printf("#101 Ouput Dir  [%s]\n", outputDir);

		// Build output Filename string and move Source file to Target destination
		String tempName = regexChecker("([0-9A-Za-z_.]+)$", inputFile);
		System.out.printf("#110 Filename only [%s]\n", tempName);
		
		String outFile = outputDir + '/' + tempName;
		System.out.printf("#115 Filename only [%s]\n", outFile);
		
		// Do the move
		//File file = new File( inputFile);
		Path src = Paths.get(inputFile);
		Path dest = Paths.get(outFile);
		try {
			Path temp = Files.move( src, dest, StandardCopyOption.REPLACE_EXISTING );
			if (temp != null) {
				System.out.println("#120 INFO: moveSourceFileToTargetFolder() - File renamed and moved successfully."
						+ "\n\tCopy status: " + Files.exists(dest));
			}
			else {
				System.out.println("Failed to move the file.");
			}
		} catch (IOException e) {
			System.out.println("#125 ERROR: moveSourceFileToTargetFolder() - \n");
			e.printStackTrace();
		}
		
	}
	

	
	/**
	 * regexChecker()
	 * Regular expression checker. 
	 * @pram regex_string
	 * @param String_to_Check
	 * @return String of the Match found by 'The Regex' against 'StringToCheck'
	 */
	private static String regexChecker(String inTheRegex, String inStringToCheck) {
		String theRegex = inTheRegex;
		String stringToCheck = inStringToCheck;
		
		Pattern p = Pattern.compile( theRegex);
		Matcher m = p.matcher( stringToCheck);
		
		String str = "";
		while ( m.find()) {  // search for a match
			if (m.group().length() != 0) { // got a match
				str = m.group().trim();
				//System.out.println("regexChecker() - Got a match: [" + stringToCheck + "]");	// delthis test
			}
			else {
				System.out.printf("INFO: regexChecker() - No Match for [%s]\n\t in string [%s]\n",
						theRegex, stringToCheck);			// delthis test
			}
		}
		return str;
	}
		
}
