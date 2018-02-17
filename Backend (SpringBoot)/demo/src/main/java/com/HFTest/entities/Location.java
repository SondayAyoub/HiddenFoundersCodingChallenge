package com.HFTest.entities;

import java.io.Serializable;

public class Location implements Serializable{

	private static final long serialVersionUID = 1L;
	private String type;
	private double[] coordinates;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
	public Location() {
		super();
	}
	public Location(String type, double[] coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}
	
	
}