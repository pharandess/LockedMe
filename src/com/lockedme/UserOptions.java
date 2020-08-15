package com.lockedme;

import java.util.Arrays;
import java.util.Optional;

public enum UserOptions {

	LIST_FILES(1), FILE_OPERATION(2), EXIT(3), INVALID_OPTION(4);

	private int value;

	private UserOptions(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static UserOptions convert(int i) {

		return Arrays.stream(UserOptions.values()).filter(v -> v.value == i).findFirst().orElse(INVALID_OPTION);

		/*
		 * for (UserOptions o : UserOptions.values()) {
		 * 
		 * if (o.getValue() == i) { return o; } }
		 * 
		 * return null; }
		 */

	}

}
