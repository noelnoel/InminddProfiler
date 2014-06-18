package com.inmindd.dcu.shared;

import java.io.Serializable;

public class FamilyHistoryInfo implements Serializable{
	
	private String userId;
	private String motherDementia;
	private String motherCvd;
	private String motherDiabetes;
	
	private String fatherDementia;
	private String fatherCvd;
	private String fatherDiabetes;
	
	private String siblingDementia;
	private String siblingCvd;
	private String siblingDiabetes;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMotherDementia() {
		return motherDementia;
	}

	public void setMotherDementia(String motherDementia) {
		this.motherDementia = motherDementia;
	}

	public String getMotherCvd() {
		return motherCvd;
	}

	public void setMotherCvd(String motherCvd) {
		this.motherCvd = motherCvd;
	}

	public String getMotherDiabetes() {
		return motherDiabetes;
	}

	public void setMotherDiabetes(String motherDiabetes) {
		this.motherDiabetes = motherDiabetes;
	}

	public String getFatherDementia() {
		return fatherDementia;
	}

	public void setFatherDementia(String fatherDementia) {
		this.fatherDementia = fatherDementia;
	}

	public String getFatherCvd() {
		return fatherCvd;
	}

	public void setFatherCvd(String fatherCvd) {
		this.fatherCvd = fatherCvd;
	}

	public String getFatherDiabetes() {
		return fatherDiabetes;
	}

	public void setFatherDiabetes(String fatherDiabetes) {
		this.fatherDiabetes = fatherDiabetes;
	}

	public String getSiblingDementia() {
		return siblingDementia;
	}

	public void setSiblingDementia(String siblingDementia) {
		this.siblingDementia = siblingDementia;
	}

	public String getSiblingCvd() {
		return siblingCvd;
	}

	public void setSiblingCvd(String siblingCvd) {
		this.siblingCvd = siblingCvd;
	}

	public String getSiblingDiabetes() {
		return siblingDiabetes;
	}

	public void setSiblingDiabetes(String siblingDiabetes) {
		this.siblingDiabetes = siblingDiabetes;
	}

	
	
	public FamilyHistoryInfo() {
		
	}
	
	

}
