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

public class RiskFactorInfos implements EntryPoint {
	
	private InminddServiceAsync InminddServiceSvc;
	private User user;
	private int riskFactor;

	@Override
	public void onModuleLoad() {
		callServiceSetup();
		String riskFactor = com.google.gwt.user.client.Window.Location.getParameter("riskfactor");
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
					getScore();
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
	
	private void getScore() {
		if (user == null) {
			// TODO print error
			System.out.println("[RB_RiskFactors::getScore] \\ user null");
			Window.alert("please connect before check your score");
			return;

		}


		AsyncCallback<RiskFactorScore> callback = new AsyncCallback<RiskFactorScore>() {

			@Override
			public void onSuccess(RiskFactorScore score) {
				if (score == null || score.getUserId().equals(null)) {
					System.out.println("[RB_RiskFactors::getScore] \\ score null");
					// TODO print error
				} else {
					DOM.getElementById("linkGoalButton").setAttribute("href","goals.html?riskFactor="+riskFactor);
					switch(riskFactor){
					case 1:
						if(score.getMidlifeHypertension() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/blood_pressure_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/blood_pressure_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 2:
						if(score.getDepression() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/mood_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/mood_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 4:
						if(score.getPhysicalInactivity() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/physical_exercise_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/physical_exercise_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 5:
						if(score.getSmoking() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/smoking_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/smoking_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 6:
						if(score.getAlcohol() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/drinking_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/drinking_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 7:
						if(score.getMidlifeObesity() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/obesity_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/obesity_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 8:
						if(score.getHighCognitiveActivity() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/coginitive_activity_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/coginitive_activity_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 9:
						if(score.getHealthyDiet() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/diet_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/diet_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 10:
						if(score.getCholesterol() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/cholesteral_amber.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/cholesteral_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;

					case 3:
						//special icon
						if(score.getDiabetes() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/diabetes-orange.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/diabetes-blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 11:
						//special icon
						if(score.getCoronaryHeartDisease() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/heart_disease_orange.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/heart_disease_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 12:
						//special icon
						if(score.getChronicKidneyDisease() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/kidney_disease_orange.png");
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/kidney_disease_blue.png");
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					}
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
