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

-----------
Using Java.io.File.renameTo() and Java.io.File.delete() methods: Copying the file and deleting the original file using these two methods.
Syntax of renameTo():

public boolean renameTo(File dest)
Description: Renames the file denoted by this abstract path name.
Parameters: dest - The new abstract path name for the named file
Returns: true if and only if the renaming succeeded; false otherwise

Syntax of delete():

public boolean delete()
Description: Deletes the file or directory 
denoted by this abstract path name.
Returns: true if and only if the file or 
directory is successfully deleted; false otherwise
*/
package org.tonyh.movecopyfiles;

import java.io.File;

public class CopyFileExample2 {

	public static void main(String[] args) {
	
		File file = new File("F:\\ProjectDir\\Pty_Area_Srch\\Results_Bucket_Dir\\ZPL_Thornton_Heath_20200121_10recs.xlsx");
		
		// renaming the file and moving it to a new location
		if ( file.renameTo(
				new File("F:\\ProjectDir\\Pty_Area_Srch\\Results_Bucket_Dir\\done\\ZPL_Thornton_Heath_20200121_10recs.xlsx"))) {
			
			// If file copied successfully then delete the original file
			file.delete();
			System.out.println("File moved successfully.");
		}
		else {
			// Fails if destination file already exists.
			System.out.println("Failed to move the file!");
		}
	}
	
}



























