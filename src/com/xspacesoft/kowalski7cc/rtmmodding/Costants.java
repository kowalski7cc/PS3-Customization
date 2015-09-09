 package com.xspacesoft.kowalski7cc.rtmmodding;

import java.util.Map;

public class Costants {

	public final static boolean DEBUG = false;
	public final static String APPLICATION_NAME = "PS3 Customization" + (DEBUG?" - Developer debug mode":"");
	public final static String APPLICATION_DEVELOPER = "Kowalski7cc";
	public final static String RECENT_CONNECTIONS_KEY = "recent_conns";
	
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
