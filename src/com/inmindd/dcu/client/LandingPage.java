package com.inmindd.dcu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
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
				if (Event.ONCLICK == event.getTypeInt()) {
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
				if (Event.ONCLICK == event.getTypeInt()) {
					DOM.getElementById("profilerPanel").setAttribute("style", "");
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
		
		DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
		DOM.getElementById("indexPanel").setAttribute("style", "");
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
}
