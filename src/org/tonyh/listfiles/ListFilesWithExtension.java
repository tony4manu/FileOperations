/*
Java 8 : Get files from folder / subfolder
Asked 1 year, 11 months ago | Active 1 month ago | Viewed 18k times
https://stackoverflow.com/questions/48563709/java-8-get-files-from-folder-subfolder/48563887

NOTE - This only prints filenames NO directory pathnames
 */
package org.tonyh.listfiles;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ListFilesWithExtension {

	public static void main(String[] args) {
		
		String[] dirList = {
				"F:/ProjectDir/Pty_Area_Srch/Results_Bucket_Dir/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Mitcham/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Croydon/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Norwood_South/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Thornton_Heath/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Greenwich/"};
		
		Path configFilePath = FileSystems.getDefault().getPath( dirList[0] );

	    List<Path> fileWithName = null;
		try {
			fileWithName = Files.walk(configFilePath)
			        .filter(s -> s.toString().endsWith(".xlsx"))
			        .map(Path::getFileName).sorted().collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error: File not found!");
			e.printStackTrace();
		}

        // printing the name of file in every sub folder
	    for (Path name : fileWithName) {
	    	System.out.println(name);
	    }
	}

}
