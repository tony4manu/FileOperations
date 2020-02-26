/*
List file in a given directory to MAX Directory level of 1 (no further down).
20200123 
*/
package org.tonyh.listfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListFilesExample3 {

	public static void main(String[] args) {
		String[] dirList = {
				"F:/ProjectDir/Pty_Area_Srch",
				"F:/ProjectDir/Pty_Area_Srch/Results_Bucket_Dir",
	    		"F:/ProjectDir/Pty_Area_Srch/Results_Mitcham/",
	    		"F:/ProjectDir/Pty_Area_Srch/Results_Croydon/",
	    		"F:/ProjectDir/Pty_Area_Srch/Results_Norwood_South/",
	    		"F:/ProjectDir/Pty_Area_Srch/Results_Thornton_Heath/",
	    		"F:/ProjectDir/Pty_Area_Srch/Results_Greenwich/"};
		
		try (Stream<Path> walk = Files.walk(Paths.get(dirList[1]),
				1)	// Options: How many levels to traverse. Default is Integer.MAX_VALUE
		) {
			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(".xlsx"))
					.collect(Collectors.toList());
			
			// Print list of path/filename.
			// I Want to used the filename else could have printed thus: result.forEach(System.out::println);
			for (String name : result) {
				doPrint(name);		//System.out.println("Filename: " + name);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void doPrint(String str) {
		System.out.println("Path/Filename: " + str);
	}

}
