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

import java.awt.EventQueue;
import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.sauronsoftware.ftp4j.FTPClient;

public class PS3Customization {

	private static final Logger LOGGER = Logger.getLogger("");
	private static FTPClient ftpClient;
	private static String[] gplNotice = {
			"PS3 Customization  Copyright (C) 2016  Kowalski7cc",
			"This program comes with ABSOLUTELY NO WARRANTY.",
			"This is free software, and you are welcome to redistribute it",
			"under certain conditions. http://www.gnu.org/licenses/"
	};

	public static void main(String[] args) {
		printGPLNotice();
		chackIntegrity();
		if(System.getProperty("debug")!=null&&System.getProperty("debug").equalsIgnoreCase("true"))
			LoggerManager.setUp(Level.ALL);
		else
			LoggerManager.setUp(Level.INFO);
		// Set host system like ui
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e2) {
			// Not blocking
		}
		ftpClient = new FTPClient();
		if(System.getProperty("offline")!=null&&System.getProperty("offline").equalsIgnoreCase("true"))
			RtmDialog.show(null);
		else
			showConnectionDialog(ftpClient);
	}

	/**
	 * Start connection dialog for ip request
	 * @param ftpClient
	 * 
	 */
	private static void showConnectionDialog(FTPClient ftpClient2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionDialog frame = new ConnectionDialog(ftpClient);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Print GNU GPL v3 notice
	 */
	private static void printGPLNotice() {
		Arrays.asList(gplNotice).forEach(p->System.out.println(p));
	}

	/**
	 * Check if needed folders are available
	 */
	private static void chackIntegrity() {
		LOGGER.fine("Checking data folder");
		File file = Costants.DATA_PATH;
		if(!file.exists()) {
			LOGGER.fine(Messages.getString("ConnectionDialog.MissingFolderError")); //$NON-NLS-1$
			if(!file.mkdir())
				System.exit(1);
		} else {
			LOGGER.fine("Data folder found");
		}
	}
}
