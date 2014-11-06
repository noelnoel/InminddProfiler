package com.inmindd.dcu.emailService;

/***
 * Represents an email to be sent
 * @author Michael
 *
 */
public class EmailDetails 
{
	private String subject;
	private String messageBody;
	private int emailGroup;
	private int monthToSend;
	private String lang;
	
	
	public EmailDetails(String sub, String messageB, int emailG, int month, String lng)
	{
		this.setSubject(sub);
		this.setMessageBody(messageB);
		this.setEmailGroup(emailG);
		this.setMonthToSend(month);
		this.setLang(lng);
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMessageBody() {
		return messageBody;
	}


	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}


	public int getEmailGroup() {
		return emailGroup;
	}


	public void setEmailGroup(int emailGroup) {
		this.emailGroup = emailGroup;
	}


	public int getMonthToSend() {
		return monthToSend;
	}


	public void setMonthToSend(int monthToSend) {
		this.monthToSend = monthToSend;
	}


	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
	
}
