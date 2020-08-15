package com.lockedme;

import java.util.Arrays;

public enum FileOptions {

	ADD(1), REMOVE(2), SEARCH(3), MAIN_MENU(4), INVALID_OPERATION(5);

	int value;

	private FileOptions(int value) {

		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

	public static FileOptions convert(int i) {
		
		return Arrays.stream(FileOptions.values()).filter(f -> f.getValue() == i).findFirst().orElse(FileOptions.INVALID_OPERATION);
	}
}
