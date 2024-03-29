package com.inmindd.dcu.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@SuppressWarnings("serial")
public class EmailCron extends HttpServlet
{
	private static InminddServiceImpl impl=new InminddServiceImpl();
	private static final Logger _logger = Logger.getLogger(EmailCron.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	{
		//Get List of users
		ArrayList<UserMail> mailTable = impl.getUserMailList();
		for(UserMail mailUser:mailTable)
		{
			if(mailUser.getRandomized()!=EmailGroupConstants.USER_NOT_RANDOMISED && mailUser.getDateRegistered()!=null)
			{
				if(mailUser.getRandomized()!=EmailGroupConstants.RANDOMIZED_DONT_EMAIL)
				{
					int monthsSinceReg = getMonthsSinceRegisistration(mailUser.getDateRegistered());
					if(monthsSinceReg>mailUser.getLastSentEmail())//
					{
						ArrayList<EmailDetails> emailList = impl.getEmail(mailUser.getLastSentEmail()+1,mailUser.getEmailGroup(), mailUser.getLang());
						String unencryptEmail = EmailEncryption.decrypt(mailUser.getEncryptedEmail());
						for(EmailDetails email:emailList)
						{
							_logger.log(Level.INFO, "Sent emial to: "+unencryptEmail);
							SendMail.sendMail(unencryptEmail, buildEmail(email.getMessageBody(), mailUser.getLang()), email.getSubject(), email.getTextContent());
						}
						if(emailList.size()>0) //Check to make sure an email was sent
						{
							impl.updateLastSentEmail(mailUser.getUserId(),mailUser.getLastSentEmail()+1);
						}
						
						//Check to see if they should be sent the questionaire
						if(isEligibleforQuestionaire(mailUser.getDateRegistered()))
						{
							//TODO: Send questionaire email
						}
						
					}
				}
				else //User is in control group, they should be sent a different email
				{
					if(isEligibleforQuestionaire(mailUser.getDateRegistered()))
					{
						//TODO: send questionaire email
					}
				}
			
			}
			
		}
	}
	
	
	private static String buildEmail(String messageBody, String lang)
	{
		String start = EmailGroupConstants.EMAIL_HEADER;
		switch(lang)
		{
			case("nl"):
				start+= EmailGroupConstants.EMAIL_LINK_NL;
				break;
			case("en"):
				start+= EmailGroupConstants.EMAIL_LINK_EN;
				break;
			case("fr"):
				start+= EmailGroupConstants.EMAIL_LINK_FR;
				break;
			default:
				start+=EmailGroupConstants.EMAIL_LINK_EN;
				break;
		}
		start+= EmailGroupConstants.EMAIL_HEADER_END;
		start+= messageBody;
		start+= EmailGroupConstants.EMAIL_FOOTER_START;
		switch(lang)
		{
			case("nl"):
				start+= EmailGroupConstants.EMAIL_FOOTER_TEXT_NL;
				break;
			case("en"):
				start+= EmailGroupConstants.EMAIL_FOOTER_TEXT_EN;
				break;
			case("fr"):
				start+= EmailGroupConstants.EMAIL_FOOTER_TEXT_FR;
				break;
			default:
				start+=EmailGroupConstants.EMAIL_FOOTER_TEXT_EN;
				break;
		}
		start+= EmailGroupConstants.EMAIL_FOOTER_END;
		return start;
	}
	
	
	
	
	
	private static int getMonthsSinceRegisistration(Date registrationDate)
	{
		Date today = new Date();
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(registrationDate);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(today);
		
		int differenceinYears = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int differenceInMonths = (differenceinYears*12)+(endCalendar.get(Calendar.MONTH)-startCalendar.get(Calendar.MONTH));
		return differenceInMonths;
	}
	
	/***
	 * This method will determine if the user should be sent the questionaire email
	 * The method will check to see if the user has been registered for 3 and a half months
	 * If this returns true they should be sent the questionaire
	 * @param registrationDate
	 * @return
	 */
	private static boolean isEligibleforQuestionaire(Date registrationDate)
	{
		//first how long have they been registered
		int numMonths = getMonthsSinceRegisistration(registrationDate);
		if(numMonths==3)
		{
			Calendar testCalendar = Calendar.getInstance();
			testCalendar.setTime(registrationDate);
			int numOfDaysInMonth = testCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			int dayOfMonth = testCalendar.get(Calendar.MONTH);
			return (numOfDaysInMonth/2)==dayOfMonth;//if the number of days/2 is the same as the day in the month return true
		}
		else
		{
			return false;
		}
	}
	
	
}
