package com.xspacesoft.kowalski7cc.rtmmodding;

import java.util.HashMap;
import java.util.Map;

public class Utils {
	
	public static Map<String, String> ArrayToMap(String[][] array) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < array.length; i++) {
			map.put(array[i][0], array[i][1]);
		}
		return map;
	}

	public static String[] getArrayPart(String[][] array, int n) {
		String[] single = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			single[i] = array[i][n];
		}
		return single;
	}
	
	public static String getIconSystem() {
		if(System.getProperty("os.name").equalsIgnoreCase("Windows 8.1"))
			return "PS3_Custom_Win10";
		return "PS3_Custom";
	}
}
