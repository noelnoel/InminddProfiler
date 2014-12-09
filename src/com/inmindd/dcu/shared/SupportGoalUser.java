package com.inmindd.dcu.shared;

import java.io.Serializable;

public class SupportGoalUser  implements Serializable {
	private String id_user;
	private int id_goal;
	private String timestamp;
	private String comment;
	private SupportGoal goal;
	private String riskfactor_name;
	private String riskfactor_image_url;
	
	public SupportGoalUser(String id_user, int id_goal, String comment) {
		setId_user(id_user);
		setId_goal(id_goal);
		setComment(comment);
		goal = null;
	}
	
	public SupportGoalUser() {
		goal = null;
	}

	public String getComment() {
		return comment;
	}
	public int getId_goal() {
		return id_goal;
	}
	public String getId_user() {
		return id_user;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public SupportGoal getGoal() {
		return goal;
	}
	public String getRiskfactor_name() {
		return riskfactor_name;
	}
	public String getRiskfactor_image_url() {
		return riskfactor_image_url;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId_goal(int id_goal) {
		this.id_goal = id_goal;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setGoal(SupportGoal goal) {
		this.goal = goal;
	}
	public void setRiskfactor_name(String riskfactor_name) {
		this.riskfactor_name = riskfactor_name;
	}
	public void setRiskfactor_image_url(String riskfactor_image_url) {
		this.riskfactor_image_url = riskfactor_image_url;
	}
	
	public String toJSON(){
		return "{ \"id_user\":" + getId_goal() +
				",\"id_goal\":" + getId_user() +
				", \"timestamp\":  \"" + getTimestamp() +
				"\" ,\"comment\": \"" + (getComment() == null || getComment() == "null" ? "" : getComment()) +
				"\" ,\"goal\": " + (getGoal() != null ? getGoal().toJSON() : "null") +
				", \"riskfactor_name\":  \"" + getRiskfactor_name() +
				"\" , \"riskfactor_image_url\":  \"" + getRiskfactor_image_url() +
				"\"  }";
	}

}
