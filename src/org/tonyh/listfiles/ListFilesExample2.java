/*
 * Example 2
 * List ALL files in a folder with extension "xlsx"
 * Does NOT list directory paths.
 * 
 * Description:
 * Below example shows how to get list of all file objects from the 
 * given folder. First create File object by passing folder path. 
 * Call listFiles() method on file object to get list of file names 
 * in the given folder
 */
package org.tonyh.listfiles;

import java.io.File;
import java.io.FilenameFilter;

public class ListFilesExample2 {

	public static void main(String[] args) {
		
		String[] dirList = {
				"F:/ProjectDir/Pty_Area_Srch",
				"F:/ProjectDir/Pty_Area_Srch/Results_Bucket_Dir/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Mitcham/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Croydon/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Norwood_South/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Thornton_Heath/",
				"F:/ProjectDir/Pty_Area_Srch/Results_Greenwich/" };

		System.out.println("\n# ListFilesExample2()");
		
		File file = new File(dirList[0]);
		
		File[] files = file.listFiles( new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if(name.toLowerCase().endsWith(".xlsx")) {
					return true;
				} else {
					return false;
				}
			}
		});
        
		System.out.println("Directory path: " + file);
        for(File f : files){
            System.out.println(f.getName());
            System.out.println("Path/Filename: " + dirList[0] + "/" + f.getName());
            // Do something with filename(s) ...
        }
	}
}
