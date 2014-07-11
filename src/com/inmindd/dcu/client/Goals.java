/**
 * Romain Beuque - 2014
 * romain.beuque@u-psud.fr
 */
package com.inmindd.dcu.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.SupportGoal;
import com.inmindd.dcu.shared.SupportGoalUser;
import com.inmindd.dcu.shared.User;

public class Goals implements EntryPoint {
	
	private InminddServiceAsync InminddServiceSvc;
	private static Goals lastInstance;
	private User user;
	private int riskFactor;

	@Override
	public void onModuleLoad() {
		lastInstance = this;
		globalize();
		Goals.exportClickGoals();
		callServiceSetup();
		String riskFactor = com.google.gwt.user.client.Window.Location.getParameter("riskFactor");
		if(riskFactor == null){
			this.riskFactor = -1;
		} else {
			this.riskFactor = Integer.parseInt(riskFactor);
		}
		
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Goals::getUser] \\ user null");
					Window.alert("please connect before check your goals");
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					setUser(user);
					loadGoalsRiskFactors();
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
	
	private void loadGoalsRiskFactors(){
		AsyncCallback<ArrayList<SupportGoal>> callback = new AsyncCallback<ArrayList<SupportGoal>>() {
			@Override
			public void onSuccess(ArrayList<SupportGoal> goals) {
				if (goals == null || goals.size() < 1) {
					System.out.println("[RB_Goals::getRisksFactors] \\ risksFactors null");
					Window.alert("please connect before check your goals");
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					String output = "[";
					boolean firstTime = true;
					for(SupportGoal goal : goals){
						if(!firstTime){ output += ","; }
						else { firstTime = false; }
						output += goal.toJSON();
					}
					output += "]";

					DOM.getElementById("goalsInputRPC").setAttribute("value",output);

					trigerJavascript();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_goals::getUser] \\ exception null");
				// TODO print error
			}
		};
		
		AsyncCallback<ArrayList<SupportGoalUser>> callbackGlobal = new AsyncCallback<ArrayList<SupportGoalUser>>() {
			@Override
			public void onSuccess(ArrayList<SupportGoalUser> goals) {
				if (goals == null || goals.size() < 1) {
					System.out.println("[RB_Goals::getRisksFactors] \\ risksFactors null");
					Window.alert("please connect before check your goals");
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					String output = "[";
					boolean firstTime = true;
					for(SupportGoalUser goal : goals){
						if(!firstTime){ output += ","; }
						else { firstTime = false; }
						output += goal.toJSON();
					}
					output += "]";

					DOM.getElementById("goalsInputRPC").setAttribute("value",output);

					trigerGlobalJavascript();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_goals::getUser] \\ exception null");
				// TODO print error
			}
		};

		if(this.riskFactor != -1){
			InminddServiceSvc.querySupportGoals(this.riskFactor, this.user.getLang(), callback);
		} else {
			InminddServiceSvc.querySupportGoalUser(this.user, callbackGlobal);
		}
	}
	
	public static native void trigerJavascript() /*-{
		$wnd.trigeredByGWT();
	 }-*/;
	
	public static native void trigerGlobalJavascript() /*-{
		$wnd.trigeredGlobalByGWT();
	 }-*/;
	
	public static void clickGoals(int goalNb, String comment) {
		SupportGoalUser goal = new SupportGoalUser(lastInstance.user.getUserId(), goalNb, comment.equals("") ? null : comment);

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean success) {
				if(!success){
					System.out.println("[RB_goals::sendGoal] \\ echec");
				}
				else {
					Window.alert("beau gosse");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_goals::sendGoal] \\ exception");
				// TODO print error
			}
		};

		lastInstance.InminddServiceSvc.updateSupportGoalUser(goal, callback);
	}
	
	public static native void exportClickGoals() /*-{
		$wnd.goalClick =
		$entry(@com.inmindd.dcu.client.Goals::clickGoals(ILjava/lang/String;));
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
		
		DOM.getElementById("goalsh1").setInnerHTML(constants.goal_0());
	}

}
