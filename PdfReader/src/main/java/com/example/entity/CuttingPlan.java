package com.example.entity;

public class CuttingPlan {
private String name;
private String material;
	private String machine;
	
	private double planDimensionX;
	private String planDimensionXUnit;
	private double planDimensionY;
	private String planDimensionYUnit;
	private double sheetDimensionX;
	private String sheetDimensionXUnit;
	private double sheetDimensionY;
	private String sheetDimensionYUnit;
	private double thickness;
	private String thicknessUnit;
	private int numberOfCycles;
	private int numberOfParts;
	private double wastePercentage;
	private double cuttingLength;
	private double internalScrap;
	private double cuttingTime;
	private double nonProdTime;
	private double totalTime;
	public void setMachine(String machine) {
		this.machine = machine;
	}
	
	public void setPlanDimensionXUnit(String planDimensionXUnit) {
		this.planDimensionXUnit = planDimensionXUnit;
	}
	
	public void setPlanDimensionYUnit(String planDimensionYUnit) {
		this.planDimensionYUnit = planDimensionYUnit;
	}
	
	public void setSheetDimensionXUnit(String sheetDimensionXUnit) {
		this.sheetDimensionXUnit = sheetDimensionXUnit;
	}
	
	public void setSheetDimensionYUnit(String sheetDimensionYUnit) {
		this.sheetDimensionYUnit = sheetDimensionYUnit;
	}
	
	public void setThicknessUnit(String thicknessUnit) {
		this.thicknessUnit = thicknessUnit;
	}
	
	public CuttingPlan() {
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public void setPlanDimensionX(double planDimensionX) {
		this.planDimensionX = planDimensionX;
	}
	public void setPlanDimensionY(double planDimensionY) {
		this.planDimensionY = planDimensionY;
	}
	public void setSheetDimensionX(double sheetDimensionX) {
		this.sheetDimensionX = sheetDimensionX;
	}
	public void setSheetDimensionY(double sheetDimensionY) {
		this.sheetDimensionY = sheetDimensionY;
	}
	public void setNumberOfCycles(int numberOfCycles) {
		this.numberOfCycles = numberOfCycles;
	}
	public void setNumberOfParts(int numberOfParts) {
		this.numberOfParts = numberOfParts;
	}
	public void setWastePercentage(double wastePercentage) {
		this.wastePercentage = wastePercentage;
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
				"material=" + material  +
						"\nmachine=" + machine  +
						"\nplanDimensionX=" + planDimensionX + " "+planDimensionXUnit+
						"\nplanDimensionY=" + planDimensionY +" "+planDimensionYUnit+
						"\nsheetDimensionX=" + sheetDimensionX + " "+sheetDimensionXUnit+
						"\nsheetDimensionY=" + sheetDimensionY +" "+sheetDimensionYUnit+
 						"\nnumberOfCycles=" + numberOfCycles +
						"\nnumberOfParts=" + numberOfParts +
						"\nwastePercentage=" + wastePercentage +" %"+
 						"\ncuttingTime=" + cuttingTime +" ms"+
						"\nnonProdTime=" + nonProdTime +" ms"+
						"\ntotalTime=" + totalTime +" ms"
				;
	}
	
	
}