package com.inmindd.dcu.shared;

import java.io.Serializable;

public class SupportFAQ implements Serializable {
	
	private int id;
	private String lang;
	private String question;
	private String answer;
	
	public SupportFAQ(){
		
	}
	
	public SupportFAQ(int id, String lang, String question, String answer){
		setId(id);
		setQuestion(question);
		setAnswer(answer);
		setLang(lang);
	}
	
	public int getId() {
		return id;
	}
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	public String getLang() {
		return lang;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public String toJSON(){
		return "{ \"id\":" + getId() +
				",\"lang\": \"" + getLang() +
				"\" , \"question\":  \"" +  getQuestion().replace("\t", " ").replace("\\", "\\\\").replace("\"", "\\\"") +
				"\" ,\"answer\": \"" +  getAnswer().replace("\t", " ").replace("\\", "\\\\").replace("\"", "\\\"") +
				"\" }";
	}

}
