package com.inmindd.dcu.shared;

import java.io.Serializable;

public class SupportRiskFactorInfos implements Serializable {
	
	private int id;
	private String lang;
	private String name;
	private String image_url;
	private String desc_keep;
	private String desc_improv;
	
	public SupportRiskFactorInfos(){
	}
	
	public SupportRiskFactorInfos(int id, String lang, String name, String image_url, String desc_keep, String desc_improv){
		setId(id);
		setLang(lang);
		setName(name);
		setImage_url(image_url);
		setDesc_keep(desc_keep);
		setDesc_improv(desc_improv);
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
	public String getImage_url() {
		return image_url;
	}
	public String getDesc_keep() {
		return desc_keep;
	}
	public String getDesc_improv() {
		return desc_improv;
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
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public void setDesc_keep(String desc_keep) {
		this.desc_keep = desc_keep;
	}
	public void setDesc_improv(String desc_improv) {
		this.desc_improv = desc_improv;
	}
}
