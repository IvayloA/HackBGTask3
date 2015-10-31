import java.io.*;
import org.json.*;

public class Main {
	public static void main(String args[]) throws Exception{
		String text = "";
		String line;
		String FolderPath = "bin/installed_modules/";
		
		DataInputStream in = new DataInputStream( new FileInputStream("bin/dependencies.json") );
		while((line =  in.readLine()) != null)  {
				text += line ;
		}
		in.close();
	//	System.out.println(text);
		JSONObject dependencies = new JSONObject( text );
		
		text = "";
		in = new DataInputStream( new FileInputStream("bin/all_packages.json") );
		while((line =  in.readLine()) != null)  {
				text += line + "\n";
		}
		in.close();
	//	System.out.println(text);
		
		JSONObject packages = new JSONObject( text );
		
		/*
		 * My project directoriers.
		 */
		JSONArray values1 = dependencies.getJSONArray("dependencies");
		for(int i=0; i<values1.length(); i++) {
		//	System.out.println( values1.get(i) );
				File newDir = new File(FolderPath + values1.get(i));
	            if(!newDir.exists()) {
	            	System.out.println("Installing " + values1.get(i) );
	                 newDir.mkdirs();
	            } else {
	                    System.out.println(values1.get(i) + " Is already installed");
	                }
		}
		
		System.out.println();
		
		/*
		 * Gets all packages dependencies.
		 */
		String names[] = packages.getNames(packages); 
		for(String name:names) {
			JSONArray values2 = packages.getJSONArray(name);
			//System.out.println(name + values2.length());
			
			/* 
			 * Checking if all of the folders are installed and installing those who are not.  
			 */
			
			for(int i=0; i<values2.length(); i++) {
				File newDir = new File(FolderPath + values2.get(i));
	            if(!newDir.exists()) {
	            	System.out.println("Installing " + values2.get(i) );
	                 newDir.mkdirs();
	            } else {
	                    System.out.println(values2.get(i) + " Is already installed");
	                }
			//	System.out.print( values2.get(i) );
			}
			System.out.println();
		}
		
		System.out.println();
		
		/*
		 * Check available directoriers.
		 */
		File file = new File(FolderPath);
		String[] directories = file.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
			return new File(current, name).isDirectory();
		  }
		});
		file.mkdirs();
		for(String name : directories) {
			System.out.println(name);

			}
		}
	}
