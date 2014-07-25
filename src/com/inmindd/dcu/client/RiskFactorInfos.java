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
import com.inmindd.dcu.shared.SupportRiskFactorInfos;
import com.inmindd.dcu.shared.User;
import com.sun.java.swing.plaf.windows.resources.windows;

public class RiskFactorInfos implements EntryPoint {
	
	private InminddServiceAsync InminddServiceSvc;
	private User user;
	private int riskFactor;
	private SupportRiskFactorInfos riskFactorInfos;

	@Override
	public void onModuleLoad() {
		globalize();
		callServiceSetup();
		String riskFactor = com.google.gwt.user.client.Window.Location.getParameter("riskfactor");
		if(riskFactor == null){
			Window.alert("Error");
			return;
		}
		this.riskFactor = Integer.parseInt(riskFactor);
		
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Goals::getUser] \\ user null");
					Window.alert(constants.errorNotLoggedIn());
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					setUser(user);
					getRiskFactorsInfos();
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
	
	private void getRiskFactorsInfos()
	{
		if (user == null) {
			// TODO print error
			System.out.println("[RB_RiskFactors::getRiskFactorsInfos] \\ user null");
			Window.alert(constants.errorNotLoggedIn());
			Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
			return;

		}

		AsyncCallback<SupportRiskFactorInfos> callback = new AsyncCallback<SupportRiskFactorInfos>() {

			@Override
			public void onSuccess(SupportRiskFactorInfos infos) {
				if (infos == null) {
					System.out.println("[RB_RiskFactors::getRiskFactorsInfos] \\ score null");
					Window.alert("No available content available in this language");
				} else {
					riskFactorInfos = infos;
					getScore();
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Score::getScore] \\ exception null");
				// TODO print error
			}
		};

		InminddServiceSvc.querySupportRiskFactorInfos(user, this.riskFactor, callback);
		return;
	}
	
	private void getScore() {
		if (user == null) {
			// TODO print error
			System.out.println("[RB_RiskFactors::getScore] \\ user null");
			Window.alert(constants.errorNotLoggedIn());
			Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
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
					DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
					DOM.getElementById("infosPanel").setAttribute("style", "");
					DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + riskFactorInfos.getName());
					switch(riskFactor){
					case 1:
						if(score.getMidlifeHypertension() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/blood_pressure_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/blood_pressure_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 2:
						if(score.getDepression() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/mood_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/mood_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 4:
						if(score.getPhysicalInactivity() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/physical_exercise_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/physical_exercise_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 5:
						if(score.getSmoking() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/smoking_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/smoking_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 6:
						if(score.getAlcohol() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/drinking_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/drinking_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 7:
						if(score.getMidlifeObesity() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/obesity_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/obesity_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 8:
						if(score.getHighCognitiveActivity() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/cognitive_activity_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/cognitive_activity_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 9:
						if(score.getHealthyDiet() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/diet_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/diet_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 10:
						if(score.getCholesterol() != 0){
							DOM.getElementById("goalButton").setAttribute("style","");
							DOM.getElementById("image-amber").setAttribute("src","images/libra/cholesteral_amber.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/cholesteral_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;

					case 3:
						//special icon
						if(score.getDiabetes() != 0){
							DOM.getElementById("image-amber").setAttribute("src","images/libra/diabetes-orange.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/diabetes-blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 11:
						//special icon
						if(score.getCoronaryHeartDisease() != 0){
							DOM.getElementById("image-amber").setAttribute("src","images/libra/heart_disease_orange.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/heart_disease_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					case 12:
						//special icon
						if(score.getChronicKidneyDisease() != 0){
							DOM.getElementById("image-amber").setAttribute("src","images/libra/kidney_disease_orange.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
						} else {
							DOM.getElementById("image-amber").setAttribute("src","images/libra/kidney_disease_blue.png");
							DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
						}
						DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
						break;
					}
					DOM.getElementById("sources").setInnerHTML(riskFactorInfos.getSources());
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
	
	private InminddConstants constants;
	private void globalize(){
		constants = 
				   (InminddConstants)GWT.create(InminddConstants.class);
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
		
		DOM.getElementById("goal-2").setInnerHTML(constants.goal_2());
		DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4());
		DOM.getElementById("back").setInnerHTML(constants.back());
	}


}
