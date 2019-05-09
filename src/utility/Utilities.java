package utility;

import java.io.File;

public class Utilities {

	public static int getNumberOfLevels(String dirPath){
	    File f = new File(dirPath);
	    File[] files = f.listFiles();
	    return (files.length-2);
	}
	
	
	
	
	
	
}
