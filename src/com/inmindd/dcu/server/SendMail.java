package com.inmindd.dcu.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendMail
{
	private static String userName, password;
	private static final String backUpEmail = "";
	private static final String backupPassword = "";
	
	private static void getUserNameAndPassword()
	{
		Properties prop = new Properties();
		InputStream in = null;
		try
		{
			in = new FileInputStream("email.properties");
			prop.load(in);
			userName = prop.getProperty("emailUsername");
			password = prop.getProperty("emailPassword");
		}
		catch(IOException e)
		{
			userName = backUpEmail;
			password = backupPassword;
			e.printStackTrace();
		}
		
	}
	
	public static void sendMail(String emailAddr,String message, String subject)
	{
		getUserNameAndPassword();
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, 
			new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(userName, password);
			}
		});
		
		try
		{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(userName));
			msg.setRecipients(RecipientType.BCC, InternetAddress.parse(emailAddr));
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
			
			
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
		}
		
	}
}
