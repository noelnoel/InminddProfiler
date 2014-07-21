package com.inmindd.dcu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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


	@Override
	public void onModuleLoad() {
		globalize();
		callServiceSetup();
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
			public void onBrowserEvent(Event event) {
				if (Event.ONCLICK == event.getTypeInt()) {
					//  generate digest of the password
					String hashedPassword = Crypto.getSHA1for(DOM.getElementById("password").getPropertyString("value"));
					String username = DOM.getElementById("username").getPropertyString("value");

					AsyncCallback<User> callback = new AsyncCallback<User>() {
						public void onSuccess(User user) {
							if ((user == null)){	            		
								Window.alert("Invalid User Id or Password  - please reenter. Check Caps lock");
								DOM.getElementById("loginPanel").setAttribute("style", "");
								DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
							}
							else {
								DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
								DOM.getElementById("supportPanel").setAttribute("style", "");     			
							}

						}
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Invalid User Id or Password  - please reenter. Check Caps lock");
							DOM.getElementById("loginPanel").setAttribute("style", "");
							DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
						}
					};

					InminddServiceSvc.authenticateUser(username, hashedPassword, callback);
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
		
		String page = com.google.gwt.user.client.Window.Location.getParameter("page");
		if(page != null && page.equals("support")){
			eventSupport.onBrowserEvent(null);
		} else if(page != null && page.equals("profiler")){
			eventProfiler.onBrowserEvent(null);
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
	
	private void globalize(){
		InminddConstants constants = 
				   (InminddConstants)GWT.create(InminddConstants.class);
		DOM.getElementById("menu-home").setInnerHTML(constants.menu_home());
		DOM.getElementById("menu-profiler").setInnerHTML(constants.menu_profiler());
		DOM.getElementById("menu-support").setInnerHTML(constants.menu_support());
		DOM.getElementById("menu-support-profile").setInnerHTML(constants.menu_support_profile());
		DOM.getElementById("menu-support-experts").setInnerHTML(constants.menu_support_experts());
		DOM.getElementById("menu-support-faq").setInnerHTML(constants.menu_support_faq());
		DOM.getElementById("menu-support-blog").setInnerHTML(constants.menu_support_blog());
		DOM.getElementById("menu-support-forum").setInnerHTML(constants.menu_support_forum());
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
		/*DOM.getElementById("keepthisup").setInnerHTML(constants.keepthisup());
		DOM.getElementById("rfi").setInnerHTML(constants.rfi());
		DOM.getElementById("rmw").setInnerHTML(constants.rmw());
		DOM.getElementById("rf-cognitive").setInnerHTML(constants.rf_cognitive());
		DOM.getElementById("rf-mood").setInnerHTML(constants.rf_mood());
		DOM.getElementById("rf-diet").setInnerHTML(constants.rf_diet());
		DOM.getElementById("rf-pressure").setInnerHTML(constants.rf_pressure());
		DOM.getElementById("rf-obesity").setInnerHTML(constants.rf_obesity());
		DOM.getElementById("rf-smoking").setInnerHTML(constants.rf_smoking());
		DOM.getElementById("rf-cholesterol").setInnerHTML(constants.rf_cholesterol());
		DOM.getElementById("rf-diabetes").setInnerHTML(constants.rf_diabetes());
		DOM.getElementById("rf-kidney").setInnerHTML(constants.rf_kidney());
		DOM.getElementById("rf-heart").setInnerHTML(constants.rf_heart());
		DOM.getElementById("rf-activity").setInnerHTML(constants.rf_activity());
		DOM.getElementById("rf-alchool").setInnerHTML(constants.rf_alchool());
		DOM.getElementById("score-1").setInnerHTML(constants.score_1());
		DOM.getElementById("score-2").setInnerHTML(constants.score_2());
		DOM.getElementById("score-3").setInnerHTML(constants.score_3());
		DOM.getElementById("score-4").setInnerHTML(constants.score_4());
		DOM.getElementById("score-5").setInnerHTML(constants.score_5());
		DOM.getElementById("score-6").setInnerHTML(constants.score_6());
		DOM.getElementById("score-7").setInnerHTML(constants.score_7());
		DOM.getElementById("score-8").setInnerHTML(constants.score_8());
		DOM.getElementById("score-9").setInnerHTML(constants.score_9());
		DOM.getElementById("score-10").setInnerHTML(constants.score_10());
		DOM.getElementById("score-11").setInnerHTML(constants.score_11());
		DOM.getElementById("score-12").setInnerHTML(constants.score_12());
		DOM.getElementById("score-13").setInnerHTML(constants.score_13());
		DOM.getElementById("score-14").setInnerHTML(constants.score_14());
		DOM.getElementById("score-15").setInnerHTML(constants.score_15());
		DOM.getElementById("score-16").setInnerHTML(constants.score_16());
		DOM.getElementById("score-17").setInnerHTML(constants.score_17());
		DOM.getElementById("score-18").setInnerHTML(constants.score_18());
		DOM.getElementById("score-19").setInnerHTML(constants.score_19());
		DOM.getElementById("score-20").setInnerHTML(constants.score_20());
		DOM.getElementById("score-21").setInnerHTML(constants.score_21());
		DOM.getElementById("score-22").setInnerHTML(constants.score_22());
		DOM.getElementById("score-23").setInnerHTML(constants.score_23());
		DOM.getElementById("score-24").setInnerHTML(constants.score_24());
		DOM.getElementById("score-25").setInnerHTML(constants.score_25());
		DOM.getElementById("experts-1").setInnerHTML(constants.experts_1());
		DOM.getElementById("experts-2").setInnerHTML(constants.experts_2());
		DOM.getElementById("experts-3").setInnerHTML(constants.experts_3());
		DOM.getElementById("experts-4").setInnerHTML(constants.experts_4());
		DOM.getElementById("experts-5").setInnerHTML(constants.experts_5());
		DOM.getElementById("experts-6").setInnerHTML(constants.experts_6());
		DOM.getElementById("experts-7").setInnerHTML(constants.experts_7());
		DOM.getElementById("experts-8").setInnerHTML(constants.experts_8());
		DOM.getElementById("experts-9").setInnerHTML(constants.experts_9());*/
	}
}
