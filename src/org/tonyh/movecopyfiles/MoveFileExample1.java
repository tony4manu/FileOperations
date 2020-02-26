/*
Tonyh
20200124Fr

Objective:
	Move file to relevant archieve folder
	  Get area-name of the source path filename
	  Get Source path filename
	  Create Target path filename string
	  Copy/Move file from Source to its Target folder
	    (Delete the Source file)
	END-STEP


----------------------------------------------------------
Java program to illustrate Moving a file 
and deleting the original file 
Source: https://www.geeksforgeeks.org/moving-file-one-directory-another-using-java/

Moving a file from one directory to another using Java

Java provides functions to move files between directories. Two ways to 
achieve this are described here. The first method utilizes Files package
for moving while the other method first copies the file to destination 
and then deletes the original copy from the source.

---------------------------------------------------------
Using Files.Path move() method: Renaming and moving the file permanently to a new location.

Syntax:
public static Path move(Path source, Path target, CopyOption..options)
           throws IOException
Parameters: 
source - the path to the file to move
target - the path to the target file 
(may be associated with a different provider to the source path)
options - options specifying how the move should be done
Returns: the path to the target file
*/

package org.tonyh.movecopyfiles;

import java.io.IOException;
//import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveFileExample1 {

	// Java program to illustrate renaming and 
	// moving a file permanently to a new location 
	public static void main(String[] args) throws IOException {
		
		Path src = Paths.get("F:\\ProjectDir\\Pty_Area_Srch\\Results_Bucket_Dir\\ZPL_Thornton_Heath_20200121_10recs.xlsx");
		Path dest = Paths.get("F:\\ProjectDir\\Pty_Area_Srch\\Results_Bucket_Dir\\done\\ZPL_Thornton_Heath_20200121_10recs.xlsx");
		
		
		// REPLACE_EXISTING - HowTo Source:
		//  https://stackoverflow.com/questions/27749755/files-move-replace-existing-cannot-be-resolved-to-a-variable
			//Path temp = Files.move(src, dest); // If destination file already exists, throw an error.
		Path temp = Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING );

		if (temp != null) {
			System.out.println("File renamed and moved successfully."
					+ "\n\tCopy status: " + Files.exists(dest));
		}
		else {
			System.out.println("Failed to move the file.");
		}
	}

}






















