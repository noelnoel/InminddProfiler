package com.inmindd.dcu.client;

import java.util.Date;



//import com.google.appengine.api.datastore.ReadPolicy.Consistency;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.Crypto;
import com.inmindd.dcu.shared.User;
import com.sun.java.swing.plaf.windows.resources.windows;

public class LandingPage implements EntryPoint {

	private InminddServiceAsync InminddServiceSvc;
	private InminddConstants constants;
	private String userId="";

	@Override
	public void onModuleLoad() {
		callServiceSetup();
		globalize();
		triggerCookieMessage();
		EventListener eventRegister= new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				if (Event.ONCLICK == event.getTypeInt()) {
					Window.Location.assign(GWT.getHostPageBaseURL() + "InminddProfiler.html");
				}
			}
		};
		
		EventListener eventLogin = new EventListener() {
			@Override
			public void onBrowserEvent(Event event) 
			{
				if (Event.ONCLICK == event.getTypeInt()) 
				{
					//  generate digest of the password
					String hashedPassword = Crypto.getSHA1for(DOM.getElementById("password").getPropertyString("value"));
					String username = DOM.getElementById("username").getPropertyString("value");

					AsyncCallback<User> callback = new AsyncCallback<User>() 
					{
						public void onSuccess(User user) {
							if ((user == null)){	            		
								Window.alert(constants.supportMessageInvalidLogin());
								DOM.getElementById("loginPanel").setAttribute("style", "");
								DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
							}
							else {
								Date now = new Date();
								long nowLong = now.getTime();
								nowLong = nowLong + (1000 * 60 * 60 * 24 * 21);
								now.setTime(nowLong);
								Cookies.setCookie("gwtLocale", user.getLang(), now);
								Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
								userId = user.getUserId();
								DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
								DOM.getElementById("supportPanel").setAttribute("style", "");   
								
								
							}
						}
						@Override
						public void onFailure(Throwable caught) {
							String onFailureError = caught.getMessage();
							if (onFailureError.equalsIgnoreCase("You have to wait up to 6 months for entering the support environment."))
							{
								Window.alert(constants.wait_six_months());
							}
						
							DOM.getElementById("loginPanel").setAttribute("style", "");
							DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
						}
					};

					InminddServiceSvc.authenticateUserSupportEnvironement(username, hashedPassword, callback);
					DOM.getElementById("loadingPanel").setAttribute("style", "text-align:center;margin-top:150px;");
					DOM.getElementById("loginPanel").setAttribute("style", "display:none");
				}
			}
		};
		
		EventListener eventSupport = new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				if (event == null || Event.ONCLICK == event.getTypeInt()) {
					AsyncCallback<User> callback = new AsyncCallback<User>() {
						@Override
						public void onSuccess(User user) {
							if (user == null) {
								DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
								DOM.getElementById("loginPanel").setAttribute("style", "");
								
							} else {
								DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
								DOM.getElementById("supportPanel").setAttribute("style", "");
								//Get the logged in user, elaborate hack to check for glaswegians
								String userId = user.getUserId();
								if(userId.startsWith("11")) //Dublin
								{
									
									//Remove nice Widget
									Element nieceWidget = DOM.getElementById("twitter-widget-1");
									nieceWidget.getParentNode().removeChild(nieceWidget);
									//DOM.getElementById("nice-twitter").setAttribute("style", "display:none");
									
									//maastricht
									Element maasWidget = DOM.getElementById("twitter-widget-2");
									maasWidget.getParentNode().removeChild(maasWidget);
									//DOM.getElementById("maas-twitter").setAttribute("style", "display:none");
									
									//glasgow
									Element glasWidget = DOM.getElementById("twitter-widget-3");
									glasWidget.getParentNode().removeChild(glasWidget);
									//DOM.getElementById("glas-twitter").setAttribute("style", "display:none");
									
								}
								else if(userId.startsWith("22")) //Check for glaswegians 
								{
									//Dublin
									Element dubWidget = DOM.getElementById("twitter-widget-0");
									dubWidget.getParentNode().removeChild(dubWidget);
									//DOM.getElementById("dub-twitter").setAttribute("style", "display:none");
									//Remove nice Widget
									Element nieceWidget = DOM.getElementById("twitter-widget-1");
									nieceWidget.getParentNode().removeChild(nieceWidget);
									//DOM.getElementById("nice-twitter").setAttribute("style", "display:none");
									
									//maastricht
									Element maasWidget = DOM.getElementById("twitter-widget-2");
									maasWidget.getParentNode().removeChild(maasWidget);
									//DOM.getElementById("maas-twitter").setAttribute("style", "display:none");
								}
								else if(userId.startsWith("33")) 
								{
									//Dublin
									Element dubWidget = DOM.getElementById("twitter-widget-0");
									dubWidget.getParentNode().removeChild(dubWidget);
									//DOM.getElementById("dub-twitter").setAttribute("style", "display:none");
									//Remove nice Widget
									Element nieceWidget = DOM.getElementById("twitter-widget-1");
									nieceWidget.getParentNode().removeChild(nieceWidget);
									//DOM.getElementById("nice-twitter").setAttribute("style", "display:none");
									
									//glasgow
									Element glasWidget = DOM.getElementById("twitter-widget-3");
									glasWidget.getParentNode().removeChild(glasWidget);
									//DOM.getElementById("glas-twitter").setAttribute("style", "display:none");
									
								}
								else if(userId.startsWith("44"))
								{
									//maastricht
									Element maasWidget = DOM.getElementById("twitter-widget-2");
									maasWidget.getParentNode().removeChild(maasWidget);
									//DOM.getElementById("maas-twitter").setAttribute("style", "display:none");
									
									//glasgow
									Element glasWidget = DOM.getElementById("twitter-widget-3");
									glasWidget.getParentNode().removeChild(glasWidget);
									//DOM.getElementById("glas-twitter").setAttribute("style", "display:none");
									
									//Dublin
									Element dubWidget = DOM.getElementById("twitter-widget-0");
									dubWidget.getParentNode().removeChild(dubWidget);
									//DOM.getElementById("dub-twitter").setAttribute("style", "display:none");
								}
	
							}
							
						}

						@Override
						public void onFailure(Throwable caught) {
							System.out.println("[RB_Score::getScore] \\ exception null");
							DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
							DOM.getElementById("loginPanel").setAttribute("style", "");
						}
					};

					InminddServiceSvc.getUserConnected(callback);
					DOM.getElementById("loadingPanel").setAttribute("style", "text-align:center;margin-top:150px;");
					DOM.getElementById("indexPanel").setAttribute("style", "display:none");
				}
			}
		};
		
		EventListener eventProfiler = new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				if (event == null || Event.ONCLICK == event.getTypeInt()) {
					DOM.getElementById("profilerPanel").setAttribute("style", "");
					DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
					DOM.getElementById("indexPanel").setAttribute("style", "display:none");
				}
			}
		};
		

			Element elem = DOM.getElementById("linkProfiler");
			DOM.sinkEvents(elem, Event.ONCLICK);
			DOM.setEventListener(elem, eventProfiler);
			Element elem1 = DOM.getElementById("linkSupport");
			DOM.sinkEvents(elem1, Event.ONCLICK);
			DOM.setEventListener(elem1, eventSupport);
			Element elem2 = DOM.getElementById("linkLogin");
			DOM.sinkEvents(elem2, Event.ONCLICK);
			DOM.setEventListener(elem2, eventLogin);
			Element elem3 = DOM.getElementById("linkRegister");
			DOM.sinkEvents(elem3, Event.ONCLICK);
			DOM.setEventListener(elem3, eventRegister);
		
		
		AsyncCallback<Boolean> callbackLogout = new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(constants.supportMessageUnableLogout());
			}

			@Override
			public void onSuccess(Boolean result) {
				Window.alert(constants.supportMessageLogout());
				Window.Location.assign(GWT.getHostPageBaseURL() + "index.html");
			}
		};
		
		String page = com.google.gwt.user.client.Window.Location.getParameter("page");
		if(page != null && page.equals("support")){
			eventSupport.onBrowserEvent(null);
		} else if(page != null && page.equals("profiler")){
			eventProfiler.onBrowserEvent(null);
		} else if(page != null && page.equals("logout")){
			InminddServiceSvc.unsetUserConnected(callbackLogout);
		} else {

				DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
				DOM.getElementById("indexPanel").setAttribute("style", "");

		}
	}


	private boolean callServiceSetup() {
		// set up rpc call
		InminddServiceSvc = (InminddServiceAsync) GWT
				.create(InminddService.class);
		ServiceDefTarget target = (ServiceDefTarget) InminddServiceSvc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "Inmindd";
		target.setServiceEntryPoint(moduleRelativeURL);
		return true;

	}
	
	public static native void triggerCookieMessage() /*-{
    $wnd.cookieCont();
}-*/;
	
	private void globalize(){
		constants = 
				   (InminddConstants)GWT.create(InminddConstants.class);
		DOM.getElementById("menu-home").setInnerHTML(constants.menu_home());
		DOM.getElementById("menu-profiler").setInnerHTML(constants.menu_profiler());
		DOM.getElementById("menu-support").setInnerHTML(constants.menu_support());
		DOM.getElementById("menu-support-profile").setInnerHTML(constants.menu_support_profile());
		DOM.getElementById("menu-support-experts").setInnerHTML(constants.menu_support_experts());
		DOM.getElementById("menu-support-faq").setInnerHTML(constants.menu_support_faq());
		DOM.getElementById("menu-support-blog").setInnerHTML(constants.menu_support_blog());
		DOM.getElementById("menu-support-blog").setAttribute("href", constants.blog_link());
		
		DOM.getElementById("menu-support-goals").setInnerHTML(constants.goal_0());
		DOM.getElementById("menu-support-logout").setInnerHTML(constants.logout());
		DOM.getElementById("eu-advert-message").setInnerHTML(constants.euFunding());
		DOM.getElementById("menu-support-apps").setInnerHTML(constants.menu_support_apps());
		DOM.getElementById("menu-inmindd").setInnerHTML(constants.menu_inmindd());
		DOM.getElementById("menu-contact").setInnerHTML(constants.menu_contact());
		DOM.getElementById("landing-loading").setInnerHTML(constants.landing_loading());
		DOM.getElementById("landing-index-1").setInnerHTML(constants.landing_index_1());
		DOM.getElementById("landing-index-2").setInnerHTML(constants.landing_index_2());
		DOM.getElementById("landing-index-3").setInnerHTML(constants.landing_index_3());
		DOM.getElementById("landing-index-4").setInnerHTML(constants.landing_index_4());
		DOM.getElementById("landing-index-5").setInnerHTML(constants.landing_index_5());
		DOM.getElementById("landing-index-6").setInnerHTML(constants.landing_index_6());
		DOM.getElementById("landing-index-5-1").setInnerHTML(constants.landing_index_5());
		DOM.getElementById("landing-index-6-1").setInnerHTML(constants.landing_index_6());
		DOM.getElementById("linkProfiler").setInnerHTML(constants.landing_index_7());
		DOM.getElementById("linkRegister").setInnerHTML(constants.landing_index_7());
		DOM.getElementById("landing-index-8").setInnerHTML(constants.landing_index_8());
		DOM.getElementById("landing-index-9").setInnerHTML(constants.landing_index_9());
		DOM.getElementById("landing-index-10").setInnerHTML(constants.landing_index_10());
		DOM.getElementById("linkSupport").setInnerHTML(constants.landing_index_11());
		DOM.getElementById("landing-login-1").setInnerHTML(constants.landing_login_1());
		DOM.getElementById("landing-login-2").setInnerHTML(constants.landing_login_2());
		DOM.getElementById("landing-login-3").setInnerHTML(constants.landing_login_3());
		DOM.getElementById("landing-profiler-1").setInnerHTML(constants.landing_profiler_1());
		DOM.getElementById("landing-profiler-2").setInnerHTML(constants.landing_profiler_2());
		DOM.getElementById("landing-profiler-3").setInnerHTML(constants.landing_profiler_3());
		DOM.getElementById("landing-profiler-4").setInnerHTML(constants.landing_profiler_4());
		DOM.getElementById("landing-profiler-5").setInnerHTML(constants.landing_profiler_5());
		DOM.getElementById("landing-profiler-6").setInnerHTML(constants.landing_profiler_6());
		DOM.getElementById("landing-profiler-7").setInnerHTML(constants.landing_profiler_7());
		DOM.getElementById("landing-support-1").setInnerHTML(constants.landing_support_1());
		DOM.getElementById("landing-support-2").setInnerHTML(constants.landing_support_2());
		DOM.getElementById("landing-support-3").setInnerHTML(constants.landing_support_3());
		DOM.getElementById("landing-support-4").setInnerHTML(constants.landing_support_4());
		DOM.getElementById("landing-support-5").setInnerHTML(constants.landing_support_5());
		DOM.getElementById("landing-support-6").setInnerHTML(constants.landing_support_6());
		DOM.getElementById("landing-support-8").setInnerHTML(constants.landing_support_8());
		DOM.getElementById("supportLogin").setInnerHTML(constants.supportLogin());
		DOM.getElementById("supportPassword").setInnerHTML(constants.supportPassword());
		DOM.getElementById("linkLogin").setInnerHTML(constants.supportSignin());
		
		DOM.getElementById("cookie_message").setInnerHTML(constants.cookieConsent());
		DOM.getElementById("cookie_message_button").setInnerHTML(constants.doNotShowMessage());

		DOM.getElementById("privacy-policy").setInnerHTML(constants.privacy_policy());

		
	}
}
