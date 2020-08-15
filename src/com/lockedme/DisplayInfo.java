/**	This class provides the static method to display the information about the application 
 * 
 */
package com.lockedme;

public class DisplayInfo {

	public static void displayAppInfo() {
		
		System.out.println("************** WELCOME TO LOCKEDME **************");
		System.out.println("** LockedMe application allows you to Manage your files");
		System.out.println("** You can add, delete and list files.");
		System.out.println("** Developed by Sandeep Pharande ");
		System.out.println("** Version 1.0 ");
		System.out.println("*************************************************");		
	}

	public static void userOptions() {
		
		System.out.println("****Choose your option****");
		System.out.println("1 : View files");
		System.out.println("2 : Add, remove, search file.");
		System.out.println("3 : Exit");
		
	}
	
	public static void fileOperations() {
		
		System.out.println("***Choose your option***");
		System.out.println("1 : Add file.");
		System.out.println("2 : Remove file.");
		System.out.println("3 : Search file.");
		System.out.println("4 : Retun to main menu.");
		
		
	}	
}
