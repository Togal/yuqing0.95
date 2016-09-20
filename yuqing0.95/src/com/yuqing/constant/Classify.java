package com.yuqing.constant;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

public class Classify {
	
	public static Map<Integer,String> classifyMap;
	
	private Classify(){};
	
	static {
		Classify.loadFile();
	}
	
	public static void loadFile() {
		classifyMap = new HashMap<Integer,String>();
		Properties p = new Properties();
		String path = ServletActionContext.getRequest().getRealPath("")+"/source/file/classify.properties";
		try {
			p.load(new InputStreamReader(new FileInputStream(path),"utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		Enumeration<?> en = p.keys();
		while(en.hasMoreElements()) {
			Integer key = (Integer.parseInt(en.nextElement().toString()));
			String value = p.getProperty(key.toString());
			classifyMap.put(key,value);
		}
	}
	
	public static void main(String[] args) {
		Classify.loadFile();
		System.out.println(Classify.classifyMap);
	}
}
