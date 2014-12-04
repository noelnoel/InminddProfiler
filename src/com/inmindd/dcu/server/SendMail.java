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
	private static final String backUpEmail = "inminddfeasabilitystudy@gmail.com";
	private static final String backupPassword = "lifestyleintervention";
	
	private static void getUserNameAndPassword()
	{

			userName = backUpEmail;
			password = backupPassword;
		
	}
	
	public static void sendMail(String emailAddr,String message, String subject,String plainText)
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
			msg.setText(plainText);
			msg.setContent(message, EmailGroupConstants.EMAIL_HTML_MIME_TYPE);
			Transport.send(msg);
			
			
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
		}
		
	}
}
