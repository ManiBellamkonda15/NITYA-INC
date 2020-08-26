package com.sss.engine.testcases;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//Hear I am Assuming that, In Folder contains only folders but,not other sub folders.

/*For example hear i am taking the folder Like  "D:\\MANI-DOC"  in this Folder have the three files 
 * and i am printing count of files in that Folder. 
 * And also i am printing Month wise files ,I mean in each month how many files are their (count of files in Month wise)*/

/*2.1)Write the example output of the above program first.
 Ans :- numMonths :-  Mothname= Count OF No.Of files in the Month
 ex output: - numMonths :{Aug=1, Mar=2}*/

public class FileCreationDate {

	public static void main(String[] argv){
String path_main = "D:\\MANI-DOC";
String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug","Sep","Oct","Nov","Dec" };
String formatted = "";

//Map<String, File> map  = new HashMap<String, File>();
Map<File,String> map  = new HashMap<File,String>();
	    	File file = new File(path_main);
	   
		      if(file.exists() && file.isDirectory()){
		         File my_arr[] = file.listFiles();
		         for(File value : my_arr) {
		        	
		        
		        	 int count = 0;
		                for (File file1 : file.listFiles()) {
		                        if (file1.isFile()) {
		                                count++;
		                        }
		                }
		             // System.out.println("NO.OF FILES "+count);
		        	 BasicFileAttributes attrs;
				 		try {
				 		    attrs = Files.readAttributes(value.toPath(), BasicFileAttributes.class);
				 		    FileTime time = attrs.creationTime();
				 		    
				 		  // String pattern = "yyyy-MMM-dd HH:mm:ss";
				 		    String pattern = "MMM";
				 		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				 			
				 		     formatted = simpleDateFormat.format( new Date( time.toMillis() ) );

				 		   // System.out.println( value+  " -- " + formatted );
				 		  
				 		   map.put(value,formatted);
				 		 
					         
				 		 
				 		} catch (IOException e) {
				 		    e.printStackTrace();
				 		}
		         }
		         System.out.println("Output: " + map);
		         Map<String, Integer> numMonths = new HashMap<>();
		         for (String month : months) {
		        	int countValue = countAnimal(month, map);
		        	if(countValue!=0) {
		        	 numMonths.put(month, countValue);
		        	}
		         }
		         System.out.println("numMonths :"+numMonths);
		   
		        
		      }
		
	}

	private static Integer countAnimal(String month, Map<File, String> map) {
		 int cnt = 0;
		    for (String val : map.values()) {
		        // split the val by space
		        String[] tokens = val.split("[\\s]+");
		        for (String token : tokens) {
		            if (token.equalsIgnoreCase(month)) {
		                ++cnt;
		            }
		        }
		    }

		    return new Integer(cnt);
	}
}
