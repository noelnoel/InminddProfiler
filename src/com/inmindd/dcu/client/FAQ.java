package com.inmindd.dcu.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.SupportFAQ;
import com.inmindd.dcu.shared.SupportGoalUser;
import com.inmindd.dcu.shared.User;

public class FAQ implements EntryPoint {

	private InminddServiceAsync InminddServiceSvc;
	private User user;
	private InminddConstants constants;

	@Override
	public void onModuleLoad() {
		globalize();
		callServiceSetup();
		
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Goals::getUser] \\ user null");
					Window.alert(constants.errorNotLoggedIn());
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					setUser(user);
					trigerUserIDJavascript(user.getUserId());
					getFAQ();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_goals::getUser] \\ exception null");
				// TODO print error
			}
		};

		InminddServiceSvc.getUserConnected(callback);
	}

	public static native void trigerUserIDJavascript(String userID) /*-{
		$wnd.trigeredUserIDByGWT(userID);
     }-*/;
	
	private void getFAQ(){
		String lang = user.getLang();
		
		
		AsyncCallback<ArrayList<SupportFAQ>> callback = new AsyncCallback<ArrayList<SupportFAQ>>() {
			
			@Override
			public void onSuccess(ArrayList<SupportFAQ> result) {
				if(result == null || result.size() < 1){
					Window.alert(constants.supportMessageNoFaq());
				} else {
					String output = "[";
					boolean firstTime = true;
					for(SupportFAQ faq : result){
						if(!firstTime){ output += ","; }
						else { firstTime = false; }
						output += faq.toJSON();
					}
					output += "]";

					DOM.getElementById("faqInputRPC").setAttribute("value",output);

					trigerJavascript();
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(constants.supportMessageNoFaq());
			}
		};
		InminddServiceSvc.querySupportFAQ(lang, callback);
		return;
		
	}
	
	public static native void trigerJavascript() /*-{
		$wnd.trigeredByGWT();
	 }-*/;

	private void setUser(User user) {
		this.user = user;				
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
		constants = 
				   (InminddConstants)GWT.create(InminddConstants.class);
		DOM.getElementById("menu-home").setInnerHTML(constants.menu_home());
		DOM.getElementById("menu-profiler").setInnerHTML(constants.menu_profiler());
		DOM.getElementById("menu-support").setInnerHTML(constants.menu_support());
		DOM.getElementById("menu-support-profile").setInnerHTML(constants.menu_support_profile());
		DOM.getElementById("menu-support-experts").setInnerHTML(constants.menu_support_experts());
		DOM.getElementById("menu-support-faq").setInnerHTML(constants.menu_support_faq());
		DOM.getElementById("menu-support-blog").setInnerHTML(constants.menu_support_blog());
		DOM.getElementById("menu-support-goals").setInnerHTML(constants.goal_0());
		DOM.getElementById("menu-support-logout").setInnerHTML(constants.logout());
		DOM.getElementById("eu-advert-message").setInnerHTML(constants.euFunding());
		DOM.getElementById("menu-support-apps").setInnerHTML(constants.menu_support_apps());
		DOM.getElementById("menu-inmindd").setInnerHTML(constants.menu_inmindd());
		DOM.getElementById("menu-contact").setInnerHTML(constants.menu_contact());
		DOM.getElementById("landing-loading").setInnerHTML(constants.landing_loading());
		
		DOM.getElementById("faqh1").setInnerHTML(constants.faq());
		DOM.getElementById("privacy-policy").setInnerHTML(constants.privacy_policy());
	}

}
