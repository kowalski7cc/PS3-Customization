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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	
//	private final static String[] SUPPORTED_LANGUAGES = new String[] {
//		"it",
//		"hu",
//		"de",
//		"fr",
//	};
	
	private static final String BUNDLE_NAME = "com.xspacesoft.kowalski7cc.rtmmodding.locales.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);
	
//	private static boolean forceEnglish = false;

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

//	private static String getLocale() {
//		if(Arrays.asList(SUPPORTED_LANGUAGES).contains(Locale.getDefault().getLanguage()))
//			return "_" + Locale.getDefault().getLanguage();
//		return "";
//	}


//	public static boolean isForceEnglish() {
//		return forceEnglish;
//	}
//
//
//	public static void setForceEnglish(boolean forceEnglish) {
//		Messages.forceEnglish = forceEnglish;
//		
//	}
	
}
