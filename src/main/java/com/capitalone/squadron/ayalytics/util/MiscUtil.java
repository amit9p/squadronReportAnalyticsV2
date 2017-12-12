package com.capitalone.squadron.ayalytics.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MiscUtil {
	
	public static MiscUtil miscUtil = new MiscUtil();

	public String getProperties(String key) throws IOException {
		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("config.properties")
				.getFile());
		FileReader reader = new FileReader(file);
		Properties p = new Properties();
		p.load(reader);
		return p.getProperty(key);
	}
}