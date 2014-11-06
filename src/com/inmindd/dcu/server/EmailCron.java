package com.inmindd.dcu.server;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.icu.util.GregorianCalendar;

@SuppressWarnings("serial")
public class EmailCron extends HttpServlet
{
	private static InminddServiceImpl impl=new InminddServiceImpl();
	private static final Logger _logger = Logger.getLogger(EmailCron.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	{
		//Get 
	}
	
	
	
	
	private int getMonthsSinceRegisistration(Date registrationDate)
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
}
