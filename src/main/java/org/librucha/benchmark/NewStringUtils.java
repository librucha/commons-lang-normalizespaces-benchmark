package org.librucha.benchmark;

public class NewStringUtils {

	public static final String SPACE = " ";
	public static final String EMPTY = "";

	public static String normalizeSpace(final String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return str;
		}
		final int size = str.length();
		final char[] newChars = new char[size];
		int count = 0;
		int whitespacesCount = 0;
		boolean startWhitespaces = true;
		for (int i = 0; i < size; i++) {
			char actualChar = str.charAt(i);
			boolean isWhitespace = Character.isWhitespace(actualChar);
			if (!isWhitespace) {
				startWhitespaces = false;
				newChars[count++] = (actualChar == 160 ? 32 : actualChar);
				whitespacesCount = 0;
			} else {
				if (whitespacesCount == 0 && !startWhitespaces) {
					newChars[count++] = SPACE.charAt(0);
				}
				whitespacesCount++;
			}
		}
		if (startWhitespaces) {
			return EMPTY;
		}
		return new String(newChars, 0, count - (whitespacesCount > 0 ? 1 : 0));
	}

}
