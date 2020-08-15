package com.lockedme;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class FileOperationManager {

	public static File rootDirectory;
	public static Queue<File> queue = new LinkedList<File>();

	public boolean setRootDirectory(String dirPath) {

		rootDirectory = new File(dirPath);
		return rootDirectory.exists();

	}

	public static String addFile(String filePath) {
		String result;
		try {

			result = "";
			InputStream reader;
			OutputStream writer;
			String destFilePath;

			File sourceFile = new File(filePath);

			if (!sourceFile.exists()) {

				result = "Invalid source file.";
				return result;
			}

			File destFile = new File(rootDirectory.getPath() + File.separatorChar + sourceFile.getName());
			destFilePath = rootDirectory.getPath() + File.separatorChar + sourceFile.getName();

			if (destFile.exists()) {
				// System.out.println(sourceFile.getName() + " exists in the destination
				// folder.");
				result = sourceFile.getName() + " exists in the root folder.";
				return result;
			} else {

				byte[] buffer = new byte[1024];
				int length;
				reader = new FileInputStream(filePath);
				writer = new FileOutputStream(destFilePath);

				while ((length = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, length);
				}

				reader.close();
				writer.close();

				result = sourceFile.getName() + " File added sucessfully .";
				return result;

			}

		} catch (Exception e) {
			result = "Error while adding file.";
			System.out.println("Error while adding file.");
			e.printStackTrace();
		}

		return result;
	}

	public String deleteFile(String fileName) {

		String message;
		boolean operation = false;

		if (rootDirectory.exists()) {

			File files[] = rootDirectory.listFiles();

			for (File f : files) {

				if (f.getName().equalsIgnoreCase(fileName)) {
					operation = f.delete();
				}

			}

			if (operation) {
				message = fileName + " deleted sucessfully.";
			} else {
				message = "File " + fileName + " does not exists.";
			}

		} else {
			message = "Root directory does not exists.";
		}

		return message;

	}

	public File[] getDirfiles(File directory, String fileName) {

		File[] files = null;

		if (directory == null) {

		} else {
			files = directory.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					// TODO Auto-generated method stub

					if (fileName.indexOf(".") != -1) {

						return name.toLowerCase().equals(fileName.toLowerCase());
					} else {

						return name.toLowerCase().contains(fileName.toLowerCase());
					}

				}
			});

//			for (File f : files) {
//				System.out.println(f);
//			}
		}

		return files;

	}

	public void searchFile(String fileName) {

		List<File> fileSearchResult = new ArrayList<File>();

		File[] dirResult = null;

		queue.add(rootDirectory);

		while (!queue.isEmpty()) {

			File node = queue.remove();

			dirResult = getDirfiles(node, fileName);

			if (dirResult != null) {

				Collections.addAll(fileSearchResult, dirResult);

			}

			// Get sub directories from current directory.
			File[] childs = node.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					if (pathname.isDirectory()) {
						return true;
					} else {
						return false;
					}
				}
			});

			if (childs != null) {
				for (File s : childs) {
					queue.add(s);
				}
			}
		}

		if (fileSearchResult.isEmpty()) {
			System.out.println("No files found matching search criteria.");	
		} else {
			for (File l : fileSearchResult) {
				System.out.println(l.getAbsolutePath() + l.separatorChar +  l.getName());
			}
		}

	}

	public void listFiles() {

		try {

			List<File> filelist = Arrays.asList(rootDirectory.listFiles());
			Collections.sort(filelist);
			String is_dir;			

			for (File f : filelist) {

				if (f.isDirectory()) {
					is_dir = "<DIR>";
				} else {
					is_dir = "";
				}
				
				System.out.println(is_dir + "" + f.getName());

			}

		} catch (Exception e) {

		}
	}

	public void performFileOperations() {

		FileOptions fo;
		Scanner sc;

		do {
			DisplayInfo.fileOperations();
			sc = new Scanner(System.in);

			try {
				fo = FileOptions.convert(Integer.parseInt(sc.next()));
			} catch (NumberFormatException e) {
				fo = FileOptions.valueOf("INVALID_OPERATION");
			}

			switch (fo) {
			case ADD:
				System.out.println("Enter full path of source file to be added.");
				sc = new Scanner(System.in);
				String filePath = sc.next();
				String result;
				result = addFile(filePath);
				System.out.println(result);
				break;

			case REMOVE:
				System.out.println("Enter file name to be deleted");
				String fileName;
				fileName = sc.next();
				System.out.println(deleteFile(fileName));
				break;

			case SEARCH:
				System.out.println("Enter file name.");
				fileName = sc.next();
				System.out.println("Search Result.");
				searchFile(fileName);
				break;

			case MAIN_MENU:
				break;

			case INVALID_OPERATION:
				System.out.println("Invalid option, Please select correct option");
				break;

			}

		} while (fo != FileOptions.MAIN_MENU);
	}
}