/**
 * Romain Beuque - 2014
 * romain.beuque@u-psud.fr
 */
package com.inmindd.dcu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.InlineLabel;
import com.inmindd.dcu.shared.FamilyHistoryInfo;
import com.inmindd.dcu.shared.RiskFactorScore;
import com.inmindd.dcu.shared.SupportGoalUser;
import com.inmindd.dcu.shared.User;

public class Goals implements EntryPoint {
	
	private InminddServiceAsync InminddServiceSvc;
	private static Goals lastInstance;
	private User user;

	@Override
	public void onModuleLoad() {
		lastInstance = this;
		Goals.exportClickGoals();
		callServiceSetup();
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Goals::getUser] \\ user null");
					Window.alert("please connect before check your goals");
					// TODO print error
				} else {
					setUser(user);
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
		$entry(@com.inmindd.dcu.client.Goals::clickGoals(ILjava/lang/String;)(i,s));
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
