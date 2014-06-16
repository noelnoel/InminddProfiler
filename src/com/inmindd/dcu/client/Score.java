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
import com.inmindd.dcu.shared.RiskFactorScore;
import com.inmindd.dcu.shared.User;


public class Score implements EntryPoint {

	private InminddServiceAsync InminddServiceSvc;
	private User user;

	@Override
	public void onModuleLoad() {
		callServiceSetup();
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Score::getScore] \\ user null");
					Window.alert("please connect before check your score");
					// TODO print error
				} else {
					//DOM.getElementById("scoreInputRPC").setInnerText(user.toString());
					setUser(user);
					getScore();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Score::getScore] \\ exception null");
				// TODO print error
			}
		};

		InminddServiceSvc.getUserConnected(callback);
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

	private void getScore() {
		if (user == null) {
			// TODO print error
			System.out.println("[RB_Score::getScore] \\ user null");
			Window.alert("please connect before check your score");
			return;

		}
		

		AsyncCallback<RiskFactorScore> callback = new AsyncCallback<RiskFactorScore>() {

			@Override
			public void onSuccess(RiskFactorScore score) {
				if (score == null || score.getUserId().equals(null)) {
					System.out.println("[RB_Score::getScore] \\ score null");
					// TODO print error
				} else {
					// TODO retrieve result   scoreInputRPC
					
					/*data = {"blood_pressure":8.6,
		"cholesteral":0, //7.5
		"coginitive_activity":17.1,
		"diabetes":7,
		"diet":0, //9.1
		"drinking":5.3,
		"heart_disease":5.9,
		"kidney_disease":5.9,
		"mood":0, //11.2
		"obesity":0, //8.6
		"physical_exercise":0, //5.9
		"smoking":0 //8.0
	}*/
					String output = "{ \"blood_pressure\":" + score.getMidlifeHypertension() +
					 ",\"cholesteral\":" + score.getCholesterolOthers() +
					 ", \"coginitive_activity\":" + score.getHighCognitiveActivity() +
					 ",\"diabetes\":" + score.getDiabetes() +
					 ",\"diet\":" + score.getHealthyDiet() +
					 ",\"drinking\":" + score.getAlcohol() +
					 ",\"heart_disease\":" + score.getCoronaryHeartDisease() +
					 ",\"kidney_disease\":" + score.getChronicKidneyDisease() +
					 ",\"mood\":" + score.getDepression() +
					 ", \"obesity\":" + score.getMidlifeObesity() +
					 ", \"physical_exercise\":" + score.getPhysicalInactivity() +
					 ",\"smoking\":" + score.getSmoking() +
					 "}";
					
					DOM.getElementById("scoreInputRPC").setAttribute("value",output);

					trigerJavascript();
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Score::getScore] \\ exception null");
				// TODO print error
			}
		};

		InminddServiceSvc.getLibraScore(user, callback);
		return;
	}

}
