package com.example.entity;

import java.util.List;

public class Job {
	private String jobName;
	private String material;
	private double thickness;
	private String thicknessUnit;
	private double totalCuttingTime;
	private double totalNonProdTime;
	private double totalTime;
	private List<Sheet> sheets;
	private List<Part> parts;
	private CuttingPlan cuttingPlan;
	private String machine;
	
	public String getMachine() {
		return machine;
	}
	public String getMaterial() {
		return material;
	}
	public CuttingPlan getCuttingPlan() {
		return cuttingPlan;
	}
	public void setCuttingPlan(CuttingPlan cuttingPlan) {
		this.cuttingPlan = cuttingPlan;
	}
	public void setThicknessUnit(String thicknessUnit) {
		this.thicknessUnit = thicknessUnit;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}
	public void setTotalCuttingTime(double totalCuttingTime) {
		this.totalCuttingTime = totalCuttingTime;
	}
	public void setTotalNonProdTime(double totalNonProdTime) {
		this.totalNonProdTime = totalNonProdTime;
	}
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}
	public List<Sheet> getSheets() {
		return sheets;
	}
	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}
	public List<Part> getParts() {
		return parts;
	}
	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
	public Job(){
	
	}
	@Override
	public String toString() {
		return
				"jobName=" + jobName +
				"\nmachine=" + machine  +
				"\nmaterial=" + material  +
				"\nthickness=" + thickness +" "+thicknessUnit+
				"\ntotalCuttingTime=" + totalCuttingTime + " ms"+
				"\ntotalNonProdTime=" + totalNonProdTime +" ms"+
				"\ntotalTime=" + totalTime+" ms"  ;
	}
}