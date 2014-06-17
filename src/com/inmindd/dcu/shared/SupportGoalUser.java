package com.inmindd.dcu.shared;

import java.io.Serializable;

public class SupportGoalUser  implements Serializable {
	private String id_user;
	private int id_goal;
	private String timestamp;
	private String comment;
	
	public SupportGoalUser(String id_user, int id_goal, String comment) {
		setId_user(id_user);
		setId_goal(id_goal);
		setComment(comment);
	}
	
	public SupportGoalUser() {
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

}
