package com.inmindd.dcu.shared;

import java.io.Serializable;

public class RiskFactorScore  implements Serializable {
	private int age;
	private String gender;
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	private double alcohol;	
	private double coronaryHeartDisease;
	private int physicalInactivity;
	private double chronicKidneyDisease;
	private double diabetes;
	private double cholesterolNetherlands;
	private double cholesterolOthers;	
	private double smoking;
	private double midlifeObesity;
	private double midlifeHypertension;
	private double healthyDiet;
	private double depression;
	private int highCognitiveActivity;
	
	
	public double getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}
	
	public double getCoronaryHeartDisease() {
		return coronaryHeartDisease;
	}
	public void setCoronaryHeartDisease(double coronaryHeartDisease) {
		this.coronaryHeartDisease = coronaryHeartDisease;
	}
	public int getPhysicalInactivity() {
		return physicalInactivity;
	}
	public void setPhysicalInactivity(int physicalInactivity) {
		this.physicalInactivity = physicalInactivity;
	}
	public double getChronicKidneyDisease() {
		return chronicKidneyDisease;
	}
	public void setChronicKidneyDisease(double chronicKidneyDisease) {
		this.chronicKidneyDisease = chronicKidneyDisease;
	}
	public double getDiabetes() {
		return diabetes;
	}
	public void setDiabetes(double diabetes) {
		this.diabetes = diabetes;
	}
	
	public double getSmoking() {
		return smoking;
	}
	public void setSmoking(double smoking) {
		this.smoking = smoking;
	}
	public double getMidlifeObesity() {
		return midlifeObesity;
	}
	public void setMidlifeObesity(double midlifeObesity) {
		this.midlifeObesity = midlifeObesity;
	}
	public double getMidlifeHypertension() {
		return midlifeHypertension;
	}
	public void setMidlifeHypertension(double midlifeHypertension) {
		this.midlifeHypertension = midlifeHypertension;
	}
	public double getHealthyDiet() {
		return healthyDiet;
	}
	public void setHealthyDiet(double healthyDiet) {
		this.healthyDiet = healthyDiet;
	}
	public double getDepression() {
		return depression;
	}
	public void setDepression(double depression) {
		this.depression = depression;
	}
	public int getHighCognitiveActivity() {
		return highCognitiveActivity;
	}
	public void setHighCognitiveActivity(int highCognitiveActivity) {
		this.highCognitiveActivity = highCognitiveActivity;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getCholesterolNetherlands() {
		return cholesterolNetherlands;
	}
	public void setCholesterolNetherlands(double cholesterolNetherlands) {
		this.cholesterolNetherlands = cholesterolNetherlands;
	}
	public double getCholesterolOthers() {
		return cholesterolOthers;
	}
	public void setCholesterolOthers(double cholesterolOthers) {
		this.cholesterolOthers = cholesterolOthers;
	}

}
