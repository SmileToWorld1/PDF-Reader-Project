package com.example.entity;

public class Part {
private String partNumber;
private String name;
private int quantity;
private double dimensionX;
private String dimensionXUnit;
private double dimensionY;
private String dimensionYUnit;
private double area;
private String areaUnit;
private double weight;
private String weightUnit;
private double cuttingTime;
private double nonProdTime;
private double totalTime;
	
	public Part() {
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public void setAreaUnit(String areaUnit) {
		this.areaUnit = areaUnit;
	}
	public void setDimensionYUnit(String dimensionYUnit) {
		this.dimensionYUnit = dimensionYUnit;
	}
	public void setDimensionXUnit(String dimensionXUnit) {
		this.dimensionXUnit = dimensionXUnit;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setDimensionX(double dimensionX) {
		this.dimensionX = dimensionX;
	}
	public void setDimensionY(double dimensionY) {
		this.dimensionY = dimensionY;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setCuttingTime(double cuttingTime) {
		this.cuttingTime = cuttingTime;
	}
	public void setNonProdTime(double nonProdTime) {
		this.nonProdTime = nonProdTime;
	}
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}
	@Override
	public String toString() {
		return
				"partNumber=" + partNumber  +
				"\nname=" + name  +
				"\nquantity=" + quantity +
				"\ndimensionX=" + dimensionX +" "+dimensionXUnit+
				"\ndimensionY=" + dimensionY +" "+dimensionYUnit+
				"\narea=" + area +" "+areaUnit+
				"\nweight=" + weight +" "+weightUnit+
				"\ncuttingTime=" + cuttingTime + " ms"+
				"\nnonProdTime=" + nonProdTime +" ms"+
				"\ntotalTime=" + totalTime +" ms";
	}
}