package com.xspacesoft.kowalski7cc.rtmmodding;

import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	
	private final static String[] SUPPORTED_LANGUAGES = new String[] {
		"it",
		"hu",
		"de",
		"fr",
	};
	
	private static final String BUNDLE_NAME = "com.xspacesoft.kowalski7cc.rtmmodding.locales.messages" + getLocale(); //$NON-NLS-1$

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

	private static String getLocale() {
		if(Arrays.asList(SUPPORTED_LANGUAGES).contains(Locale.getDefault().getLanguage()))
			return "_" + Locale.getDefault().getLanguage();
		return "";
	}


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
