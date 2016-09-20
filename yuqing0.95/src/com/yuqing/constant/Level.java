package com.yuqing.constant;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

public class Level {
	
	public static Map<Integer,String> levelMap;
	
	private Level(){};
	
	static {
		Level.loadFile();
	}
	
	public static void loadFile() {
		levelMap = new HashMap<Integer,String>();
		Properties p = new Properties();
		String path = ServletActionContext.getRequest().getRealPath("")+"/source/file/level.properties";
		try {
			p.load(new InputStreamReader(new FileInputStream(path),"utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		Enumeration<?> en = p.keys();
		while(en.hasMoreElements()) {
			Integer key = (Integer.parseInt(en.nextElement().toString()));
			String value = p.getProperty(key.toString());
			levelMap.put(key,value);
		}
	}
	
	public static void main(String[] args) {
		Level.loadFile();
		System.out.println(Level.levelMap);
	}
}
