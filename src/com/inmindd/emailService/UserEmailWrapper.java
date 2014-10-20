package com.inmindd.emailService;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.inmindd.dcu.client.InminddService;
import com.inmindd.dcu.client.InminddServiceAsync;
import com.inmindd.dcu.server.InminddServiceImpl;
import com.inmindd.dcu.shared.User;

/**
 * This class will take in a user, determine their localisation and send them an email
 * @author mscriney
 *
 */
public class UserEmailWrapper
{
	private static java.util.Properties emailTemplates;
	private static HashMap<String, String> emailTemplatesMap;
	
	private static boolean templatesLoaded = false;
	
	public static final int SET_FIRST_GOAL = 0;
	public static final int REMIND_USER_OF_THINGS = 1;
	
	
	
	private static InminddServiceImpl InminddServiceSvc;
	
	
	public UserEmailWrapper()
	{
		//Create the InMinddService
		InminddServiceSvc = new InminddServiceImpl();
		//Load properties file, this should only happen if another instance is not already created
		if(!templatesLoaded)
		{
			emailTemplatesMap = new HashMap<String, String>();
			emailTemplates = new java.util.Properties();
			InputStream input = null;
			try
			{
				input = new FileInputStream("emailTemplates.properties");
				emailTemplates.load(input);
				for(final String propertyKey: emailTemplates.stringPropertyNames()) //Load the properties file into a email templates map
				{
					emailTemplatesMap.put(propertyKey, emailTemplates.getProperty(propertyKey));
				}
				
			}
			catch(java.io.IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(input!=null)
				{
					try
					{
						input.close();
					} 
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
	}
	
	public static UserEmailWrapper newInstance()
	{
		return new UserEmailWrapper();
	}
	
	
	public static String getLocalizationForUser(User user)
	{
		return user.getLang();
	}
	
	public static boolean userHasEmail(User user)
	{
		String id = user.getUserId();
		//Now query the inminddserviceimpl for the users email address
		//TODO: Query email table
		return false;
		
		
	}
	
	public static void sendEmail(User user, int emailtype)
	{
		//Does the user have an email address
		if(userHasEmail(user))
		{
			//Get localisation
			String lang = user.getLang();
			//Get the email template to send
			String emailtemplate = getEmailTemplate(lang, emailtype);
			//Get the subject template to send
			String subject = getEmailSubject(lang, emailtype);
			String emailAddress = getEmailAddressForUser(user);
			SendMail.sendMail(emailAddress, emailtemplate, subject);
		}
	}
	
	private static String getEmailAddressForUser(User user)
	{
		if(userHasEmail(user))
		{
			String userEncryptedEmail = InminddServiceImpl.getUserEmailEncrypted(User u);
			//Decrypt the users email address
			String unEncrypt = EmailEncryption.decrypt(userEncryptedEmail);
			return unEncrypt;
		}
	}
	
	private static String getEmailTemplate(String lang, int emailType)
	{
		//This will load the specified email template for a given user in their selected language
		//TODO: Finish this
		return "";
	}
	
	private static String getEmailSubject(String lang, int emailType)
	{
		//This will load the specified email template for a given user in their selected language
		//TODO: Finish this
		return "";
	}
	
	
	
}
