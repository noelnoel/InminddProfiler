package com.inmindd.dcu.shared;

import java.io.Serializable;

public class MedicalInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	private double cent;
	private double feet;
	private double inches;	
	private double kilo;
	private double stone;
	private double lbs;
	private double mmol;
	private double systolic;
	private double diastolic;

	private String chol;	
	private String highChol;
	private String lifeStyleChange;
	private String medChol;
	private String cvd;
	private String hyper;
	private String bloodPressure;
	private String medBlood;
	private String mellitus;
	private String diabetesTreat;
	private String sugar;
	private String kidney;
	

	public String getChol() {
		return chol;
	}
	public void setChol(String chol) {
		this.chol = chol;
	}
	
	
	public double getCent() {
		return cent;
	}
	public void setCent(double cent) {
		this.cent = cent;
	}
	public double getFeet() {
		return feet;
	}
	public void setFeet(double feet) {
		this.feet = feet;
	}
	public double getInches() {
		return inches;
	}
	public void setInches(double inches) {
		this.inches = inches;
	}
	public double getKilo() {
		return kilo;
	}
	public void setKilo(double kilo) {
		this.kilo = kilo;
	}
	public double getStone() {
		return stone;
	}
	public void setStone(double stone) {
		this.stone = stone;
	}
	public double getLbs() {
		return lbs;
	}
	public void setLbs(double lbs) {
		this.lbs = lbs;
	}
	public double getMmol() {
		return mmol;
	}
	public void setMmol(double mmol) {
		this.mmol = mmol;
	}
	public double getSystolic() {
		return systolic;
	}
	public void setSystolic(double systolic) {
		this.systolic = systolic;
	}
	public double getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(double diastolic) {
		this.diastolic = diastolic;
	}
	public String getHighChol() {
		return highChol;
	}
	public void setHighChol(String highChol) {
		this.highChol = highChol;
	}
	public String getLifeStyleChange() {
		return lifeStyleChange;
	}
	public void setLifeStyleChange(String lifeStyleChange) {
		this.lifeStyleChange = lifeStyleChange;
	}
	public String getMedChol() {
		return medChol;
	}
	public void setMedChol(String medChol) {
		this.medChol = medChol;
	}
	public String getCvd() {
		return cvd;
	}
	public void setCvd(String cvd) {
		this.cvd = cvd;
	}
	public String getHyper() {
		return hyper;
	}
	public void setHyper(String hyper) {
		this.hyper = hyper;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getMedBlood() {
		return medBlood;
	}
	public void setMedBlood(String medBlood) {
		this.medBlood = medBlood;
	}
	public String getMellitus() {
		return mellitus;
	}
	public void setMellitus(String mellitus) {
		this.mellitus = mellitus;
	}
	public String getDiabetesTreat() {
		return diabetesTreat;
	}
	public void setDiabetesTreat(String diabetesTreat) {
		this.diabetesTreat = diabetesTreat;
	}
	public String getSugar() {
		return sugar;
	}
	public void setSugar(String sugar) {
		this.sugar = sugar;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String string) {
		this.userId = string;
	}

	public String getKidney() {
		return kidney;
	}
	public void setKidney(String kidney) {
		this.kidney = kidney;
	}

}
