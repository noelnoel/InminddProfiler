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
			Window.alert(constants.supportMessageErrorLink());
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
					trigerUserIDJavascript(user.getUserId());
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

	public static native void trigerUserIDJavascript(String userID) /*-{
		$wnd.trigeredUserIDByGWT(userID);
     }-*/;
	
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
					Window.alert(constants.supportMessageNoContentLanguage());
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
	
	private void getScore() 
	{
		if (user == null) 
		{
			// TODO print error
			System.out.println("[RB_RiskFactors::getScore] \\ user null");
			Window.alert(constants.errorNotLoggedIn());
			Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
			return;

		}

		AsyncCallback<RiskFactorScore> callback = new AsyncCallback<RiskFactorScore>() 
		{
			@Override
			public void onSuccess(RiskFactorScore score) {
				if (score == null || score.getUserId().equals(null)) {
					System.out.println("[RB_RiskFactors::getScore] \\ score null");
					// TODO print error
				} else {
					DOM.getElementById("linkGoalButton").setAttribute("href","goals.html?riskFactor="+riskFactor);
					DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
					DOM.getElementById("infosPanel").setAttribute("style", "");
					boolean keepThisUp = true;
					boolean manageWell = false;
					switch(riskFactor){
					case 1:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_pressure());
						if(score.getMidlifeHypertension() != 0){
							keepThisUp = false;
						}
						break;
					case 2:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_mood());
						if(score.getDepression() != 0){
							keepThisUp = false;
						}
						break;
					case 4:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_activity());
						if(score.getPhysicalInactivity() != 0){
							keepThisUp = false;
						}
						break;
					case 5:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_smoking());
						if(score.getSmoking() != 0){
							keepThisUp = false;
						}
						break;
					case 6:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_alchool());
						if(score.getAlcohol() != 0){
							keepThisUp = false;
						}
						break;
					case 7:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_obesity());
						if(score.getMidlifeObesity() != 0){
							keepThisUp = false;
						}
						break;
					case 8:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_cognitive());
						if(score.getHighCognitiveActivity() != 0){
							keepThisUp = false;
						}
						break;
					case 9:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_diet());
						if(score.getHealthyDiet() != 0){
							keepThisUp = false;
						}
						break;
					case 10:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_cholesterol());
						if(score.getCholesterol() != 0){
							keepThisUp = false;
						}
						break;

					case 3:
						//special icon
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_diabetes());
						if(score.getDiabetes() != 0){
							keepThisUp = false;
							manageWell = true;
						}
						break;
					case 11:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_heart());
						//special icon
						if(score.getCoronaryHeartDisease() != 0){
							keepThisUp = false;
							manageWell = true;
						}
						break;
					case 12:
						DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4() + " " + constants.rf_kidney());
						//special icon
						if(score.getChronicKidneyDisease() != 0){
							keepThisUp = false;
							manageWell = true;
						}
						break;
					}
					
					if(!keepThisUp)
					{
						String url = riskFactorInfos.getImage_url();
						if(!manageWell){
							// room for improvement
							DOM.getElementById("goalButton").setAttribute("style","");
						} else {
							//manage well
							url = riskFactorInfos.getImage_url().replaceAll("amber", "orange");
						}
						//amber
						DOM.getElementById("image-amber").setAttribute("src",url);
						DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_improv());
					} else {
						//keep this up
						DOM.getElementById("image-amber").setAttribute("src", riskFactorInfos.getImage_url().replaceAll("amber", "blue"));
						DOM.getElementById("riskFactorMainInfos").setInnerHTML(riskFactorInfos.getDesc_keep());
					}
					
					DOM.getElementById("image-amber").setAttribute("style","height:200px;width:200px;");
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
		DOM.getElementById("menu-support-blog").setAttribute("href", constants.blog_link());
		DOM.getElementById("menu-support-goals").setInnerHTML(constants.goal_0());
		DOM.getElementById("menu-support-logout").setInnerHTML(constants.logout());
		DOM.getElementById("eu-advert-message").setInnerHTML(constants.euFunding());
		DOM.getElementById("menu-support-apps").setInnerHTML(constants.menu_support_apps());
		DOM.getElementById("menu-inmindd").setInnerHTML(constants.menu_inmindd());
		DOM.getElementById("menu-contact").setInnerHTML(constants.menu_contact());
		DOM.getElementById("landing-loading").setInnerHTML(constants.landing_loading());
		
		DOM.getElementById("goal-2").setInnerHTML(constants.goal_2());
		DOM.getElementById("linkGoalButton").setInnerHTML(constants.goal_4());
		DOM.getElementById("back").setInnerHTML(constants.back());
	}


}
