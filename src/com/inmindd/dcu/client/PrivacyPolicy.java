package com.inmindd.dcu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.User;

public class PrivacyPolicy implements EntryPoint
{

	private InminddServiceAsync InminddServiceSvc;
	private User user;
	static InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	@Override
	public void onModuleLoad()
	{
		globalise();
		callServiceSetup();
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Contact::getUser] \\ user null");
					Window.alert(constants.errorNotLoggedIn());
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					setUser(user);
					trigerUserIDJavascript(user.getUserId());
					DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
					DOM.getElementById("indexPanel").setAttribute("style", "");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Contact::getUser] \\ exception null");
				// TODO print error
			}
		};

		InminddServiceSvc.getUserConnected(callback);
	}
	
	
	public static native void trigerUserIDJavascript(String userID) /*-{
		$wnd.trigeredUserIDByGWT(userID);
 	}-*/;

	public void setUser(User user) {
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

	
	
	private void globalise()
	{
		//General Stuff
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
		DOM.getElementById("keepthisup").setInnerHTML(constants.keepthisup());
		DOM.getElementById("rfi").setInnerHTML(constants.rfi());
		DOM.getElementById("rmw").setInnerHTML(constants.rmw());
		
		DOM.getElementById("landing-index-2").setInnerHTML(constants.trial_id());
		DOM.getElementById("trial_website").setInnerHTML(constants.trial_website());
		DOM.getElementById("gen_statement").setInnerHTML(constants.gen_statement());
		DOM.getElementById("gen_statement_text").setInnerHTML(constants.gen_statement_text());
		DOM.getElementById("collect_statement").setInnerHTML(constants.collect_info());
		DOM.getElementById("collect_statement_text").setInnerHTML(constants.collect_info_text());;
		DOM.getElementById("request_stat").setInnerHTML(constants.request_statement());
		DOM.getElementById("request_statement_text").setInnerHTML(constants.request_statement_text());
		DOM.getElementById("complaints").setInnerHTML(constants.complaints());
		DOM.getElementById("complaints_text").setInnerHTML(constants.complaints_text());
		DOM.getElementById("collection_tech").setInnerHTML(constants.collection_tech_statement());
		DOM.getElementById("collection_tech_text").setInnerHTML(constants.collection_tech_statement_text());;
		DOM.getElementById("tech_stat_1").setInnerHTML(constants.tech_stat_1());
		DOM.getElementById("tech_stat_2").setInnerHTML(constants.tech_stat_2());
		DOM.getElementById("tech_stat_3").setInnerHTML(constants.tech_stat_3());
		DOM.getElementById("tech_stat_4").setInnerHTML(constants.tech_stat_4());
		DOM.getElementById("tech_stat_5").setInnerHTML(constants.tech_stat_5());
		
		DOM.getElementById("privacy_signoff").setInnerHTML(constants.pricavy_signoff());
		DOM.getElementById("privacy-policy").setInnerHTML(constants.privacy_policy());
		
		
	}

}
