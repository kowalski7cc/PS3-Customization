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

import java.io.File;
import java.util.Map;

public class Costants {

	public final static boolean DEBUG = false;
	public final static String APPLICATION_NAME = "PS3 Customization" + (DEBUG?" - Developer debug mode":"");
	public final static String APPLICATION_DEVELOPER = "Kowalski7cc";
	public final static String RECENT_CONNECTIONS_KEY = "recent_conns";
	public final static File DATA_PATH = new File(new File(System.getProperty("user.home")), "PS3 Customization");
	
	public final static String[][] PAIR = new String[][] {
		{ "Coldboots", "/nand/vsh/resource/" },
		{ "Boot sounds", "/nand/vsh/resource/" },
		{ "Waves", "/nand/vsh/resource/qgl/" },
		{ "Themes", "/dev_hdd0/theme/" },
		{ "Gameboots", "/nand/vsh/resource/" },
		{ "Cobra Plugins", "/nand/vsh/resource/" },
		{ "Packages", "/dev_hdd0/packages/" },
		{ "Eboots", "/game/" },
	};
	
	public final static String[] STOCKABLE = new String[] {
		"Coldboots",
		"Boot sounds",
		"Waves",
		"Gameboots",
	};
	
	public final static String[][] PERFECT_RECOVERY = new String[][] {
		{ "Coldboots", "Rebug" },
		{ "Boot sounds", "Alien" },
		{ "Waves", "Original red" },
		{ "Gameboots", "Rebug" },
	};
	
	public final static String[] MODS_SUPPORTED = Utils.getArrayPart(PAIR, 0);
	
	public final static Map<String, String> INSTALL_PATH = Utils.ArrayToMap(PAIR);
	
}
