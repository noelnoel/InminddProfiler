package com.inmindd.dcu.shared;

import java.io.Serializable;

public class Patient  implements Serializable {
	private String userId;
	
	private int age;
	private String gender;
	private String countryOfBirth;	
	private String maritalStatus;
	private String livingArrangements;
	private String occupationalGroup;
	private String educationLevel;
	private String employmentStatus;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String string) {
		this.userId = string;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
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
	public String getCountryOfBirth() {
		return countryOfBirth;
	}
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getLivingArrangements() {
		return livingArrangements;
	}
	public void setLivingArrangements(String livingArrangements) {
		this.livingArrangements = livingArrangements;
	}
	public String getOccupationalGroup() {
		return occupationalGroup;
	}
	public void setOccupationalGroup(String occupationalGroup) {
		this.occupationalGroup = occupationalGroup;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	

}
