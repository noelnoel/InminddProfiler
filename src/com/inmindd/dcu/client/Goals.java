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
		Goals.exportClickGoals();
		callServiceSetup();
		String riskFactor = com.google.gwt.user.client.Window.Location.getParameter("riskFactor");
		this.riskFactor = Integer.parseInt(riskFactor);
		
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Goals::getUser] \\ user null");
					Window.alert("please connect before check your goals");
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

		InminddServiceSvc.querySupportGoals(this.riskFactor, callback);
	}
	
	public static native void trigerJavascript() /*-{
		$wnd.trigeredByGWT();
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

}
