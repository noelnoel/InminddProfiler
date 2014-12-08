package com.inmindd.dcu.shared;

import java.io.Serializable;

import com.inmindd.dcu.client.InminddServiceAsync;

public class PhysicalActivityInfo  implements Serializable{	
	
	private String userId;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	private String physicalWork;
	private double summerWalkingHours;
	private double winterWalkingHours;
	private double summerCyclingHours;
	private double winterCyclingHours;
	private double summerGardenHours;
	private double winterGardenHours;
	private double diyHours;
	private double summerPhysicalHours;
	private double winterPhysicalHours;
	private double summerHouseworkHours;
	private double winterHouseworkHours;
	private String vigorous;
	private double vigorousHours;
	
	public double getVigorousHours() {
		return vigorousHours;
	}

	public void setVigorousHours(double vigorousHours) {
		this.vigorousHours = vigorousHours;
	}


	private double flightStairs;
	

	
	public PhysicalActivityInfo() {
		
	}

	public double getSummerHouseworkHours() {
		return summerHouseworkHours;
	}


	public void setSummerHouseworkHours(double summerHouseworkHours) {
		this.summerHouseworkHours = summerHouseworkHours;
	}

	public String getPhysicalWork() {
		return physicalWork;
	}


	public void setPhysicalWork(String physicalWork) {
		this.physicalWork = physicalWork;
	}


	public double getSummerWalkingHours() {
		return summerWalkingHours;
	}


	public void setSummerWalkingHours(double summerWalkingHours) {
		this.summerWalkingHours = summerWalkingHours;
	}


	public double getWinterWalkingHours() {
		return winterWalkingHours;
	}


	public void setWinterWalkingHours(double d) {
		this.winterWalkingHours = d;
	}


	public double getSummerCyclingHours() {
		return summerCyclingHours;
	}


	public void setSummerCyclingHours(double summerCyclingHours) {
		this.summerCyclingHours = summerCyclingHours;
	}


	public double getWinterCyclingHours() {
		return winterCyclingHours;
	}


	public void setWinterCyclingHours(double winterCyclingHours) {
		this.winterCyclingHours = winterCyclingHours;
	}


	public double getSummerGardenHours() {
		return summerGardenHours;
	}


	public void setSummerGardenHours(double summerGardenHours) {
		this.summerGardenHours = summerGardenHours;
	}


	public double getWinterGardenHours() {
		return winterGardenHours;
	}


	public void setWinterGardenHours(double winterGardenHours) {
		this.winterGardenHours = winterGardenHours;
	}


	public double getDiyHours() {
		return diyHours;
	}


	public void setDiyHours(double diyHours) {
		this.diyHours = diyHours;
	}


	public double getSummerPhysicalHours() {
		return summerPhysicalHours;
	}


	public void setSummerPhysicalHours(double summerPhysicalHours) {
		this.summerPhysicalHours = summerPhysicalHours;
	}


	public double getWinterPhysicalHours() {
		return winterPhysicalHours;
	}


	public void setWinterPhysicalHours(double winterPhysicalHours) {
		this.winterPhysicalHours = winterPhysicalHours;
	}


	public double getWinterHouseworkHours() {
		return winterHouseworkHours;
	}


	public void setWinterHouseworkHours(double winterHouseworkHours) {
		this.winterHouseworkHours = winterHouseworkHours;
	}


	public String getVigorous() {
		return vigorous;
	}


	public void setVigorous(String vigorous) {
		this.vigorous = vigorous;
	}


	

	public double getFlightStairs() {
		return flightStairs;
	}


	public void setFlightStairs(double d) {
		this.flightStairs = d;
	}

}
