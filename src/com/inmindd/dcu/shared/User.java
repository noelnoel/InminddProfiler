package com.inmindd.dcu.shared;

import java.io.Serializable;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class User implements Serializable {
	private String userId;
	private String password;	
	private String maidenName;
	private String favoriteColour;
	public String getMaidenName() {
		return maidenName;
	}
	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}
	public String getFavoriteColour() {
		return favoriteColour;
	}
	public void setFavoriteColour(String favoriteColour) {
		this.favoriteColour = favoriteColour;
	}

	private String lang;
	
	public User() {
		
	}
	public User(String userId, String password) {
		this.userId = userId;
		this.password =password;
	
		
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLang() {
		if(lang == null || lang == ""){
			setLangFromCountryCode();
		}
		return lang;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setLangFromCountryCode() {
		RegExp regExp = RegExp.compile("^([0-9]{2})[0-9]{5}$");
		MatchResult matcher = regExp.exec(userId);
		boolean matchFound = (matcher != null); // equivalent to regExp.test(inputStr); 

		if (matchFound) {
			if (matcher.getGroup(1).equals("44")) {
				lang = "fr";
			} else if (matcher.getGroup(1).equals("22")) {
				lang = "sc";
			} else if (matcher.getGroup(1).equals("33")) {
				lang = "nl";
			} else {
				lang = "en";
			}
		}
	}

}
