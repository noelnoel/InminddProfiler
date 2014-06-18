package com.inmindd.dcu.shared;

import java.io.Serializable;

public class SmokeAlcoholInfo implements Serializable {
	private String userId;
	
	private String smoker_type;
	private int current_year_start;
	private int current_num_smoke;
	private int former_year_start;
	private int former_year_stop;
	private int former_num_smoke;
	private String drinks_freq;
	private String num_drinks;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getSmoker_type() {
		return smoker_type;
	}
	public void setSmoker_type(String smoker_type) {
		this.smoker_type = smoker_type;
	}
	public int getCurrent_year_start() {
		return current_year_start;
	}
	public void setCurrent_year_start(int current_year_start) {
		this.current_year_start = current_year_start;
	}
	public int getCurrent_num_smoke() {
		return current_num_smoke;
	}
	public void setCurrent_num_smoke(int current_num_smoke) {
		this.current_num_smoke = current_num_smoke;
	}
	public int getFormer_year_start() {
		return former_year_start;
	}
	public void setFormer_year_start(int former_year_start) {
		this.former_year_start = former_year_start;
	}
	public int getFormer_year_stop() {
		return former_year_stop;
	}
	public void setFormer_year_stop(int former_year_stop) {
		this.former_year_stop = former_year_stop;
	}
	public int getFormer_num_smoke() {
		return former_num_smoke;
	}
	public void setFormer_num_smoke(int former_num_smoke) {
		this.former_num_smoke = former_num_smoke;
	}
	public String getDrinks_freq() {
		return drinks_freq;
	}
	public void setDrinks_freq(String drinks_freq) {
		this.drinks_freq = drinks_freq;
	}
	public String getNum_drinks() {
		return num_drinks;
	}
	public void setNum_drinks(String num_drinks) {
		this.num_drinks = num_drinks;
	}

}
