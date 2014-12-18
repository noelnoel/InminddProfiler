package com.inmindd.dcu.server;

public class EmailGroupConstants
{
	public static final int USER_ENGAGED=1;
	public static final int USER_NOT_ENGAGED=2;
	public static final int ALL_USERS=0;
	
	//Not part of the email groups, but needed to make sure people in control group are not sent emails
	public static final int RANDOMIZED_DONT_EMAIL=2;
	public static final int INTERVENTION_GROUP=1;
	public static final int USER_NOT_RANDOMISED = 3;
	
	
	public static final String EMAIL_HEADER = "<html><head></head><body><table style=\"table-layout:fixed\"><tr><td><a href=\"http://inmindd-profiler.appspot.com\"><img src=\"http://inmindd-profiler.appspot.com/images/inmindd_logo.png\"/></a></td><td style=\"width:200px\"><a href=\"http://inmindd-profiler.appspot.com\">";
	public static final String EMAIL_LINK_EN = "Login to In-MINDD Support Environment";
	public static final String EMAIL_LINK_FR = "Se connecter Ã  l'environnement de soutien In-MINDD";
	public static final String EMAIL_LINK_NL = "Log in op de In-MINDD ondersteuningspagina";
	public static final String EMAIL_HEADER_END = "</a></td></tr><tr><td><br/><p>";
	public static final String EMAIL_FOOTER_START = "</td></tr><tr style=\"outline:thin solid\"><td>";
	public static final String EMAIL_FOOTER_TEXT_EN = "Funded by EU FP7: This project has received funding from the European Union's Seventh Framework Programme for research, technological development and demonstration under grant agreement no 304979.";
	public static final String EMAIL_FOOTER_TEXT_NL = "Dit project wordt gefinancierd door het Zevende Kaderprogramma van de Europese Unie voor onderzoek, technische ontwikkeling en demonstratie, subsidieovereenkomst nummer 304979";
	public static final String EMAIL_FOOTER_TEXT_FR = "Subventionné par EU FP7: Ce projet reçoit des fonds du Septième Programme-Cadre de l’Union Européenne pour la recherche, le développement et la démonstration technologique (sous le numéro d'autorisation no 304979).";
	public static final String EMAIL_FOOTER_END = "</td><td><img src=\"http://inmindd-integration.appspot.com/images/eu-flag.png\"/></td></tr></table></body></html>";
	public static final String EMAIL_HTML_MIME_TYPE = "text/html; charset=utf-8";
}
