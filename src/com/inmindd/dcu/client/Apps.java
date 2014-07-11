package com.inmindd.dcu.client;

import java.util.ArrayList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.SupportApps;
import com.inmindd.dcu.shared.User;

public class Apps implements EntryPoint {

	private InminddServiceAsync InminddServiceSvc;
	private User user;

	@Override
	public void onModuleLoad() {
		globalize();
		callServiceSetup();
		
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Apps::getUser] \\ user null");
					Window.alert("please connect before check Apps");
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					setUser(user);
					getApps();
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
	
	private void getApps(){
		String lang = user.getLang();
		
		
		AsyncCallback<ArrayList<SupportApps>> callback = new AsyncCallback<ArrayList<SupportApps>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error: NO APPS");
			}

			@Override
			public void onSuccess(ArrayList<SupportApps> result) {
				if(result == null || result.size() < 1){
					Window.alert("Error: No APPS");
				} else {
					String output = "[";
					boolean firstTime = true;
					for(SupportApps expert : result){
						if(!firstTime){ output += ","; }
						else { firstTime = false; }
						output += expert.toJSON();
					}
					output += "]";

					DOM.getElementById("appsInputRPC").setAttribute("value",output);

					trigerJavascript();
				}				
			}
			
		};
		InminddServiceSvc.querySupportApps(lang, callback);
		return;
		
	}
	
	private void setUser(User user) {
		this.user = user;				
	}
	
	
	public static native void trigerJavascript() /*-{
		$wnd.trigeredByGWT();
	 }-*/;
	
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
		
		DOM.getElementById("apps1").setInnerHTML(constants.apps1());
		DOM.getElementById("apps2").setInnerHTML(constants.apps2());
	}

}