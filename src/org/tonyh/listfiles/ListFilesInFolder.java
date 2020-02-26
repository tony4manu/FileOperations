/*
List All files in a folder/directory - includes the pathnames

TonyH
20200120Mn
*/

package org.tonyh.listfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ListFilesInFolder {
	
	public static void main(String[] args) throws IOException {
		
		String[] dirList = {
				"F:/ProjectDir/Pty_Area_Srch/Results_Bucket_Dir/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Mitcham/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Croydon/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Norwood_South/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Thornton_Heath/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Greenwich/"};
		
		System.out.println("# ListFilesExample1()");
		
		Files.find( Paths.get( dirList[0] ),
				1,	// MaxDepth to search. Default is: Integer.MAX_VALUE
				(filePath, fileAttr) -> fileAttr.isRegularFile())
				.forEach(System.out::println);
	}
	
}
