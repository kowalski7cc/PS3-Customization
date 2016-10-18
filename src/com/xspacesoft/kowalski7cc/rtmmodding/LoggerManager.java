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

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerManager {
	private static final Logger LOGGER = Logger.getLogger("");
	
	public static void setUp(Level logLevel) {
		Handler[] handlers = LOGGER.getHandlers();
		Arrays.asList(handlers).forEach(p -> LOGGER.removeHandler(p));
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		consoleHandler.setFormatter(new Formatter() {
			/* (non-Javadoc)
			 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
			 */
			@Override
			public String format(LogRecord record) {
				if(record.getLevel().equals(Level.FINE))
					return "DEBUG: " + record.getMessage() + "\n";
				return record.getLevel().getName() + ": " + record.getMessage() + "\n";
			}
		});
		LOGGER.addHandler(consoleHandler);
	}

}
