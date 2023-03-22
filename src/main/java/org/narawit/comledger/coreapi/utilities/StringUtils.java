package org.narawit.comledger.coreapi.utilities;

public final class StringUtils {
	public static boolean isStringNotEmpty(String s) {
		if(s != null && !s.isEmpty() && !s.trim().isEmpty())
			return true;
		else
			return false;
	}
}
