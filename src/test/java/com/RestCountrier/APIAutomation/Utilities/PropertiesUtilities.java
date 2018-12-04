package com.RestCountrier.APIAutomation.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtilities {
	public static String readProperty(String path, String key) {

		Properties prop = new Properties();
		InputStream input = null;
		String value = "";
		try {

			input = new FileInputStream(path);

			// load a properties file
			prop.load(input);

			value = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return value;
	}
}
