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
	private int summerWalkingHours;
	private int winterWalkingHours;
	private int summerCyclingHours;
	private int winterCyclingHours;
	private int summerGardenHours;
	private int winterGardenHours;
	private int diyHours;
	private int summerPhysicalHours;
	private int winterPhysicalHours;
	private int summerHouseworkHours;
	private int winterHouseworkHours;
	private String vigorous;
	private int vigorousHours;
	public int getVigorousHours() {
		return vigorousHours;
	}

	public void setVigorousHours(int vigorousHours) {
		this.vigorousHours = vigorousHours;
	}


	private int flightStairs;
	

	
	public PhysicalActivityInfo() {
		
	}

	public int getSummerHouseworkHours() {
		return summerHouseworkHours;
	}


	public void setSummerHouseworkHours(int summerHouseworkHours) {
		this.summerHouseworkHours = summerHouseworkHours;
	}

	public String getPhysicalWork() {
		return physicalWork;
	}


	public void setPhysicalWork(String physicalWork) {
		this.physicalWork = physicalWork;
	}


	public int getSummerWalkingHours() {
		return summerWalkingHours;
	}


	public void setSummerWalkingHours(int summerWalkingHours) {
		this.summerWalkingHours = summerWalkingHours;
	}


	public int getWinterWalkingHours() {
		return winterWalkingHours;
	}


	public void setWinterWalkingHours(int winterWalkingHours) {
		this.winterWalkingHours = winterWalkingHours;
	}


	public int getSummerCyclingHours() {
		return summerCyclingHours;
	}


	public void setSummerCyclingHours(int summerCyclingHours) {
		this.summerCyclingHours = summerCyclingHours;
	}


	public int getWinterCyclingHours() {
		return winterCyclingHours;
	}


	public void setWinterCyclingHours(int winterCyclingHours) {
		this.winterCyclingHours = winterCyclingHours;
	}


	public int getSummerGardenHours() {
		return summerGardenHours;
	}


	public void setSummerGardenHours(int summerGardenHours) {
		this.summerGardenHours = summerGardenHours;
	}


	public int getWinterGardenHours() {
		return winterGardenHours;
	}


	public void setWinterGardenHours(int winterGardenHours) {
		this.winterGardenHours = winterGardenHours;
	}


	public int getDiyHours() {
		return diyHours;
	}


	public void setDiyHours(int diyHours) {
		this.diyHours = diyHours;
	}


	public int getSummerPhysicalHours() {
		return summerPhysicalHours;
	}


	public void setSummerPhysicalHours(int summerPhysicalHours) {
		this.summerPhysicalHours = summerPhysicalHours;
	}


	public int getWinterPhysicalHours() {
		return winterPhysicalHours;
	}


	public void setWinterPhysicalHours(int winterPhysicalHours) {
		this.winterPhysicalHours = winterPhysicalHours;
	}


	public int getWinterHouseworkHours() {
		return winterHouseworkHours;
	}


	public void setWinterHouseworkHours(int winterHouseworkHours) {
		this.winterHouseworkHours = winterHouseworkHours;
	}


	public String getVigorous() {
		return vigorous;
	}


	public void setVigorous(String vigorous) {
		this.vigorous = vigorous;
	}


	

	public int getFlightStairs() {
		return flightStairs;
	}


	public void setFlightStairs(int flightStairs) {
		this.flightStairs = flightStairs;
	}

}
