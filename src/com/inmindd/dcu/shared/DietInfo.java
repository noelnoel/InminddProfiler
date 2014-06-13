package com.inmindd.dcu.shared;

import java.io.Serializable;

public class DietInfo  implements Serializable {
    private String userId;
    /**
     * the radio button input for each question is encoded as integer codes
     * 
     * 
     */
    private int culinaryFat;
    private int rapeSeedOil;
    private int vegetableServings;
    private int fruit;
    private int redMeat;
    private int butter;
    private int beverages;
    private int wine;
    private int legumes;
    private int fish;
    private int sweets;
    private int nuts;
    private int chicken;
    private int sauce;
    
	public int getFruit() {
		return fruit;
	}

	public void setFruit(int fruit) {
		this.fruit = fruit;
	}

	public int getRedMeat() {
		return redMeat;
	}

	public void setRedMeat(int redMeat) {
		this.redMeat = redMeat;
	}

	public int getButter() {
		return butter;
	}

	public void setButter(int butter) {
		this.butter = butter;
	}

	public int getBeverages() {
		return beverages;
	}

	public void setBeverages(int beverages) {
		this.beverages = beverages;
	}

	public int getWine() {
		return wine;
	}

	public void setWine(int wine) {
		this.wine = wine;
	}

	public int getLegumes() {
		return legumes;
	}

	public void setLegumes(int legumes) {
		this.legumes = legumes;
	}

	public int getFish() {
		return fish;
	}

	public void setFish(int fish) {
		this.fish = fish;
	}

	public int getSweets() {
		return sweets;
	}

	public void setSweets(int sweets) {
		this.sweets = sweets;
	}

	public int getNuts() {
		return nuts;
	}

	public void setNuts(int nuts) {
		this.nuts = nuts;
	}

	public int getChicken() {
		return chicken;
	}

	public void setChicken(int chicken) {
		this.chicken = chicken;
	}

	public int getSauce() {
		return sauce;
	}

	public void setSauce(int sauce) {
		this.sauce = sauce;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCulinaryFat() {
		return culinaryFat;
	}

	public void setCulinaryFat(int culinaryFat) {
		this.culinaryFat = culinaryFat;
	}

	public int getRapeSeedOil() {
		return rapeSeedOil;
	}

	public void setRapeSeedOil(int rapeSeedOil) {
		this.rapeSeedOil = rapeSeedOil;
	}

	public int getVegetableServings() {
		return vegetableServings;
	}

	public void setVegetableServings(int vegetableServings) {
		this.vegetableServings = vegetableServings;
	}

	public DietInfo() {
		
	}

}
