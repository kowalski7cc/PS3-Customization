/*This file is part of PS3 Customization.

PS3 Customization is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PS3 Customization is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PS3 Customization.  If not, see <http://www.gnu.org/licenses/>.*/

package com.xspacesoft.kowalski7cc.rtmmodding;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JWindow;

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
	
	public static void setWindowPosition(JWindow jWindow) {        
		// get the size of the screen, on systems with multiple displays,		
		// the primary display is used
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// calculate the new location of the window
		int w = jWindow.getSize().width;
		int h = jWindow.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		// moves this component to a new location, the top-left corner of
		// the new location is specified by the x and y
		// parameters in the coordinate space of this component's parent
		jWindow.setLocation(x, y);
	}
}
