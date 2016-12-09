package com.ice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Util {
	/**
	 * 根据文件名获取配置文件
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> config(String fileName) throws IOException{
		Map<String, String> configMap;
		Properties config = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		config.load(inputStream);
		Iterator<String> it = config.stringPropertyNames().iterator();
		configMap = new HashMap<String, String>();
		while (it.hasNext()) {
			String key = it.next();
			configMap.put(key, config.getProperty(key));
		}
		
		return configMap;
	}
	
	/**
     * 将首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpper(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
