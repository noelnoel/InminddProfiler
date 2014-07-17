package com.inmindd.dcu.shared;

import java.io.Serializable;

public class SupportExperts implements Serializable {

	private int id;
	private String lang;
	private String country;
	private String image_url;
	private String description;
	
	public SupportExperts(){
		
	}
	
	public SupportExperts(int id, String lang, String country, String image_url, String description){
		setId(id);
		setLang(lang);
		setCountry(country);
		setImage_url(image_url);
		setDescription(description);
	}
	
	public int getId() {
		return id;
	}
	public String getLang() {
		return lang;
	}
	public String getCountry() {
		return country;
	}
	public String getImage_url() {
		return image_url;
	}
	public String getDescription() {
		return description;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toJSON(){
		return "{ \"id\":" + getId() +
				",\"lang\": \"" + getLang() +
				"\" , \"country\":  \"" +  getCountry() +
				"\" ,\"image_url\": \"" +  getImage_url() +
				"\" ,\"description\": \"" +  getDescription().replace("\\", "\\\\").replace("\"", "\\\"") +
				"\" }";
	}
	
}
