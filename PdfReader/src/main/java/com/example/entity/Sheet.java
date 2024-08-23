package com.example.entity;

public class Sheet {
private double dimensionX;
private String dimensionXUnit;
private double dimensionY;
private String dimensionYUnit;
private double weight;
private String weightUnit;
private int numberOfSheets;
	
	public Sheet() {
	}
	public void setDimensionXUnit(String dimensionXUnit) {
		this.dimensionXUnit = dimensionXUnit;
	}
	public void setDimensionYUnit(String getDimensionYUnit) {
		this.dimensionYUnit = getDimensionYUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public void setDimensionX(double dimensionX) {
		this.dimensionX = dimensionX;
	}
	public void setDimensionY(double dimensionY) {
		this.dimensionY = dimensionY;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setNumberOfSheets(int numberOfSheets) {
		this.numberOfSheets = numberOfSheets;
	}
	@Override
	public String toString() {
		return
				"dimensionX=" + dimensionX + " "+dimensionXUnit+
				"\ndimensionY=" + dimensionY +" "+dimensionYUnit+
				"\nweight=" + weight +" "+weightUnit+
				"\nnumberOfSheets=" + numberOfSheets ;
	}
}