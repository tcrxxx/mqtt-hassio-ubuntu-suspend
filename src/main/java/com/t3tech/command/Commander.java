package com.t3tech.command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Commander {

	public static String command(String[] strings, boolean output, boolean errorOutput) throws IOException {
		Runtime rt = Runtime.getRuntime();
//		String[] commands = {"system.exe", "-get t"};
//		String[] commands = {"ls"};
		String[] commands = strings.clone();
		
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new 
		     InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
		     InputStreamReader(proc.getErrorStream()));

		String s = null;
		StringBuffer sb = new StringBuffer();
		
		if (output) {
			// Read the output from the command
			System.out.println("Command output:\t");
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			    sb.append(s);
			    sb.append(System.getProperty("line.separator"));
			}
			System.out.println("------------------------------\t");
		}
			
		// Read any errors from the attempted command
		if (errorOutput) {
			System.out.println("Error output command (if any):\t");
			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			    sb.append(s);
			    sb.append(System.getProperty("line.separator"));
			}
			System.out.println("------------------------------\t");
		}
		
		return sb.toString();
	}
	
	public static String[] brokenMessageCommand(String str) {
		return str.split("\\s+");
	}
	
}
