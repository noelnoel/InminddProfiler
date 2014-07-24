package com.inmindd.dcu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.User;

public class Contact implements EntryPoint {

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
					System.out.println("[RB_Contact::getUser] \\ user null");
					Window.alert("please connect before check Contact");
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					setUser(user);
					
					DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
					DOM.getElementById("contactDiv").setAttribute("style", "");
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
		DOM.getElementById("eu-advert-message").setInnerHTML(constants.euFunding());
		DOM.getElementById("menu-support-apps").setInnerHTML(constants.menu_support_apps());
		DOM.getElementById("menu-inmindd").setInnerHTML(constants.menu_inmindd());
		DOM.getElementById("menu-contact").setInnerHTML(constants.menu_contact());
		
		DOM.getElementById("contact1").setInnerHTML(constants.contact1());
		DOM.getElementById("contact2").setInnerHTML(constants.contact2());
		DOM.getElementById("contact3").setInnerHTML(constants.contact3());
		DOM.getElementById("ireland").setInnerHTML(constants.ireland());
		DOM.getElementById("scotland").setInnerHTML(constants.scotland());
		DOM.getElementById("france").setInnerHTML(constants.france());
		DOM.getElementById("netherlands").setInnerHTML(constants.netherlands());
	}

}
