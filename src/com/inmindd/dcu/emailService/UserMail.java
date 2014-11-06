package com.inmindd.dcu.emailService;

import java.util.Date;

/**
 * Representation of a USER_Mail row in the database
 * @author mscriney
 *
 */
public class UserMail
{
	private String userId;
	private String encryptedEmail;
	private Date lastLogin;
	private int emailGroup; //Which message the user should be sent, i.e are they engaging in the study or not
	private int lastSendEmail; //The last email message the user was sent
	private Date dateRegistered;
	private String lang;
	
	public UserMail(String id, String addr, Date login, int emailG, int lastEmail, Date reg)
	{
		this.setUserId(id);
		this.setEncryptedEmail(addr);
		this.setLastLogin(login);
		this.setEmailGroup(emailG);
		this.setLastSendEmail(lastEmail);
		this.setDateRegistered(reg);
		this.determineLang();
	}
	
	
	private void determineLang()
	{
		if(this.getUserId().startsWith("11"))
		{
			this.setLang("en");
		}
		else if(this.getUserId().startsWith("22"))
		{
			this.setLang("en");//TODO: Get the language codes
		}
		else if(this.getUserId().startsWith("33"))
		{
			this.setLang("en");
		}
		else if(this.getUserId().startsWith("44"))
		{
			this.setLang("en");
		}
		else if(this.getUserId().startsWith("55"))
		{
			this.setLang("en");
		}
		else
		{
			this.setLang("en"); //If everythign breaks, default to english
		}
	}
	
	public String getLang()
	{
		return this.lang;
	}
	
	public void setLang(String lng)
	{
		this.lang = lng;
	}
	
	public Date getDateRegistered()
	{
		return this.dateRegistered;
	}
	
	public void setDateRegistered(Date d)
	{
		this.dateRegistered = d;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getEncryptedEmail()
	{
		return encryptedEmail;
	}

	public void setEncryptedEmail(String encryptedEmail)
	{
		this.encryptedEmail = encryptedEmail;
	}

	public Date getLastLogin()
	{
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	public int getEmailGroup()
	{
		return emailGroup;
	}

	public void setEmailGroup(int emailGroup)
	{
		this.emailGroup = emailGroup;
	}

	public int getLastSendEmail()
	{
		return lastSendEmail;
	}

	public void setLastSendEmail(int lastSendEmail)
	{
		this.lastSendEmail = lastSendEmail;
	}
	
	
	
}
