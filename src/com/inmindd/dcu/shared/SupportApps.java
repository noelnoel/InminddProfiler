package com.inmindd.dcu.shared;

import java.io.Serializable;

public class SupportApps implements Serializable {
	private int id;
	private String lang;
	private String name;
	private String logo_url;
	private String category;
	private String description;
	
	public SupportApps(){
		
	}
	
	public SupportApps(int id, String lang, String name, String logo_url, String category, String description){
		setId(id);
		setLang(lang);
		setLogo_url(logo_url);
		setName(name);
		setCategory(category);
		setDescription(description);
	}
	
	public int getId() {
		return id;
	}
	public String getLang() {
		return lang;
	}
	public String getName() {
		return name;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public String getCategory() {
		return category;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String toJSON(){
		return "{ \"id\":" + getId() +
				",\"lang\": \"" + getLang() +
				"\" , \"logo_url\":  \"" +  getLogo_url() +
				"\" ,\"name\": \"" +  getName() +
				"\" ,\"category\": \"" +  getCategory() +
				"\" ,\"description\": \"" +  getDescription().replace("\\", "\\\\").replace("\"", "\\\"") +
				"\" }";
	}
}
