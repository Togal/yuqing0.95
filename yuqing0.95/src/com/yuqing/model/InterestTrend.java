package com.yuqing.model;

import java.util.ArrayList;

public class InterestTrend {

	private String name;
	private Marker marker;
	private String type;
	
	private ArrayList data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Marker getMarker() {
		return marker;
	}
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList getData() {
		return data;
	}
	public void setData(ArrayList data) {
		this.data = data;
	}
	
	
}
