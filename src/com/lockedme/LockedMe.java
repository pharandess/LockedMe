/**	This is starting point for the LockedMe application. 
 *  This class contains main method, which will provide options to interact with user. 
 */

package com.lockedme;

import java.util.Scanner;

public class LockedMe {

	public static void main(String args[]) {

		boolean dirExist;
		FileOperationManager fo = new FileOperationManager();

		// check if user provided input
		if (args[0] == null) {
			System.out.println("Provide root directory as argument, exiting application.");
		}

		dirExist = fo.setRootDirectory(args[0]);

		if (!dirExist) {

			System.out.println("Not valid root directory, exiting program.");
			return;
		}

		
		UserOptions option;
		Scanner sc = new Scanner(System.in);

		try {
			DisplayInfo.displayAppInfo();

			do {

				DisplayInfo.userOptions();

				try {
					option = UserOptions.convert(Integer.parseInt(sc.next()));
				} catch (NumberFormatException e) {
					option = UserOptions.valueOf("INVALID_OPTION");
				}

				switch (option) {
				case LIST_FILES:
					fo.listFiles();
					break;
				case FILE_OPERATION:

					fo.performFileOperations();
					break;
				case EXIT:
					System.out.println("Thank you for using LockedMe.");
					break;
				case INVALID_OPTION:
					System.out.println("Invalid option, Please select correct option");
					break;
				}

			} while (option != UserOptions.EXIT);
		} finally {
			sc.close();
		}
	}

}
