package com.inmindd.dcu.client;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.SupportExperts;
import com.inmindd.dcu.shared.User;

/*mail purpose*/
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/*end of mail*/

public class Experts implements EntryPoint {

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
					System.out.println("[RB_Experts::getUser] \\ user null");
					Window.alert("please connect before check Experts");
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					setUser(user);
					getExperts();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_goals::getUser] \\ exception null");
				// TODO print error
			}
		};

		InminddServiceSvc.getUserConnected(callback);
		
		EventListener eventSendMail = new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				if (Event.ONCLICK == event.getTypeInt()) {
					String emailFrom = DOM.getElementById("emailForm").getAttribute("value");
					String body = DOM.getElementById("questionForm").getAttribute("value");
					if(emailFrom == "" || body == ""){
						return;
					}
					
					Properties props = new Properties();
					Session session = Session.getDefaultInstance(props, null);
					
					try {
					    Message msg = new MimeMessage(session);
					    msg.setFrom(new InternetAddress("admin@1-dot-inmindd-profiler.appspotmail.com", "Inmindd Support Environment"));
					    msg.addRecipient(Message.RecipientType.TO,
					     new InternetAddress("romain@romainbeuque.fr", "Mr. User"));
					    msg.setSubject("[ask-the-experts] new question");
					    msg.setText(body);
					    Transport.send(msg);
					} catch (AddressException e) {
					    // ...
					} catch (MessagingException e) {
					    // ...
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		};
		
		Element elem = DOM.getElementById("askExperts8");
		DOM.sinkEvents(elem, Event.ONCLICK);
		DOM.setEventListener(elem, eventSendMail);
	}
	
	private void getExperts(){
		String lang = user.getLang();
		
		
		AsyncCallback<ArrayList<SupportExperts>> callback = new AsyncCallback<ArrayList<SupportExperts>>() {
			
			@Override
			public void onSuccess(ArrayList<SupportExperts> result) {
				if(result == null || result.size() < 1){
					Window.alert("Error: No Experts");
				} else {
					String output = "[";
					boolean firstTime = true;
					for(SupportExperts expert : result){
						if(!firstTime){ output += ","; }
						else { firstTime = false; }
						output += expert.toJSON();
					}
					output += "]";

					DOM.getElementById("expertsInputRPC").setAttribute("value",output);

					trigerJavascript();
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error: NO FAQ");
			}
		};
		InminddServiceSvc.querySupportExperts(lang, callback);
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
		
		DOM.getElementById("askTheExperts").setInnerHTML(constants.askTheExperts());
		DOM.getElementById("askExperts1").setInnerHTML(constants.askExperts1());
		DOM.getElementById("askExperts2").setInnerHTML(constants.askExperts2());
		DOM.getElementById("askExperts3").setInnerHTML(constants.askExperts3());
		DOM.getElementById("askExperts4").setInnerHTML(constants.askExperts4());
		DOM.getElementById("askExperts5").setInnerHTML(constants.askExperts5());
		DOM.getElementById("askExperts6").setInnerHTML(constants.askExperts6());
		DOM.getElementById("askExperts7").setInnerHTML(constants.askExperts7());
		DOM.getElementById("askExperts8").setInnerHTML(constants.askExperts8());
		DOM.getElementById("askExperts9").setInnerHTML(constants.askExperts9());
	}

}
