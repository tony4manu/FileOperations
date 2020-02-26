
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

public class MoveFileToAnotherFolder {

	public static void main(String[] args) throws ParseException, IOException {

		String[] dirList = {
				"F:/ProjectDir/Pty_Area_Srch/Results_Bucket_Dir",
				"F:/ProjectDir/Pty_Area_Srch/Results_Croydon/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Mitcham/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Norwood_South/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Thornton_Heath/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Greenwich/" };
		
		System.out.println("\n#05 main(); - Match a areaName to a folder's Path.");	// delthis test

		// Process the list of .xlsx file in folder
		try (Stream<Path> walk = Files.walk(Paths.get(dirList[0]),
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
				moveSourceFileToTargetFolder(name, dirList);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		
	// Move Source file to Target folder based on the filename
	private static void moveSourceFileToTargetFolder(String inName, String[] inDirList) throws IOException {
		String[] dirList = inDirList;
		String filename = inName;
		
		String[] areaName = { "Croydon", "Mitcham",
				"Norwood_South", "Thornton_Heath", "Greenwich" };
		
		String area = "";
		String path = "";
		
		// Match 'filename' to an areaName
		int areaIndex = -1;
		for (int i = 0; i < areaName.length; i++) {
			area = regexChecker(areaName[i], filename);
			areaIndex = i;
			if (area.length() != 0) { // match found
				break;
			}
		}

		System.out.printf("#110 areaIndex [%d] - Area Name : [%s]\n", areaIndex, areaName[areaIndex]);
		

		// Match 'areaName' to dirList path
		int dirIndex = -1;
		for (int i = 0; i < dirList.length; i++) {
			path = regexChecker(area, dirList[i]);
			dirIndex = i;
			if (path.length() != 0) { // match found
				path =  dirList[i]; // set string 'path' to the folder path
				break;
			}
		}

		System.out.printf("#125 dirIndex  [%d] - Dir Path [%s]\n", dirIndex, dirList[dirIndex]);
		System.out.println("#127 Path : " + path);

		// Build command string and move Source file to Target destination
		String tempFilename = regexChecker("([0-9A-Za-z_.]+)$", filename);
		System.out.printf("#130 Filename only [%s]\n", tempFilename);
		
		// Target filename
		System.out.println("#132 Source Filename : " + filename );
		System.out.println("#133 Target Filename : " + path + tempFilename );
		
		// Do the move
		Path src  = Paths.get(filename);
		Path dest = Paths.get( path + tempFilename);

		Path temp = Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING );
		if (temp != null) {
			System.out.println("File renamed and moved successfully."
					+ "  - Copy status: " + Files.exists(dest));
		}
		else {
			System.out.println("Failed to move the file.");
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




