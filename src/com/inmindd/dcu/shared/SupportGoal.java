package com.inmindd.dcu.shared;

import java.io.Serializable;

public class SupportGoal implements Serializable {
	private int id;
	private int goalNb;
	private String name;
	private String text;
	private String image_url;
	
	public SupportGoal(int id, int goalNb, String name, String text, String image_url) {
		setId(id); setGoalNb(goalNb); setName(name); setText(text); setImage_url(image_url);
	}

	public SupportGoal() {
	}

	public int getId() {
		return id;
	}
	public int getGoalNb() {
		return goalNb;
	}
	public String getName() {
		return name;
	}
	public String getText() {
		return text;
	}
	public String getImage_url() {
		return image_url;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setGoalNb(int goalNb) {
		this.goalNb = goalNb;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String toJSON(){
		return "{ \"id\":" + getId() +
				",\"goalNb\":" + getGoalNb() +
				", \"name\":  \"" + getName() +
				"\" ,\"text\": \"" + getText() +
				"\" ,\"image_url\": \"" + getImage_url() +
				"\" }";
	}
}
