package com.inmindd.dcu.server;

public class EmailGroupConstants
{
	public static final int USER_ENGAGED=1;
	public static final int USER_NOT_ENGAGED=2;
	public static final int ALL_USERS=0;
	
	//Not part of the email groups, but needed to make sure people in control group are not sent emails
	public static final int RANDOMIZED_DONT_EMAIL=2;
	public static final int INTERVENTION_GROUP=1;
}
