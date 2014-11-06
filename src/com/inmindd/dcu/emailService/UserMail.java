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
	
	
	public UserMail(String id, String addr, Date login, int emailG, int lastEmail, Date reg)
	{
		this.setUserId(id);
		this.setEncryptedEmail(addr);
		this.setLastLogin(login);
		this.setEmailGroup(emailG);
		this.setLastSendEmail(lastEmail);
		this.setDateRegistered(reg);
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
