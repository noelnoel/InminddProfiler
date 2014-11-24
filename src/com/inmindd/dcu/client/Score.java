/**
 * Romain Beuque - 2014
 * romain.beuque@u-psud.fr
 */
package com.inmindd.dcu.client;

import java.util.ArrayList;

//import com.google.appengine.api.datastore.ReadPolicy.Consistency;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.RiskFactorScore;
import com.inmindd.dcu.shared.SupportRiskFactorInfos;
import com.inmindd.dcu.shared.User;


public class Score implements EntryPoint {

	private InminddServiceAsync InminddServiceSvc;
	private ArrayList<SupportRiskFactorInfos> infosRiskFactors;
	private User user;
	private InminddConstants constants;

	@Override
	public void onModuleLoad() {
		globalize();
		callServiceSetup();
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Score::getScore] \\ user null");
					Window.alert(constants.errorNotLoggedIn());
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
					// TODO print error
				} else {
					//DOM.getElementById("scoreInputRPC").setInnerText(user.toString());
					setUser(user);
					trigerUserIDJavascript(user.getUserId());
					getInfos();
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
	
	private void getInfos(){
		if (user == null) {
			// TODO print error
			System.out.println("[RB_Score::getScore] \\ user null");
			Window.alert(constants.errorNotLoggedIn());
			return;
		}


		AsyncCallback<ArrayList<SupportRiskFactorInfos>> callback = new AsyncCallback<ArrayList<SupportRiskFactorInfos>>() {

			@Override
			public void onSuccess(ArrayList<SupportRiskFactorInfos> riskFactorsInfos) {
				if (riskFactorsInfos == null || riskFactorsInfos.size() == 0) {
					System.out.println("[RB_Score::getInfos] \\ infos null");
					// TODO print error
				} else {
					infosRiskFactors = riskFactorsInfos;
					getScore();
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Score::getInfos] \\ exception null");
				// TODO print error
			}
		};

		InminddServiceSvc.queryAllSupportRiskFactorInfos(user, callback);
		return;
	}

	private void getScore() {
		if (user == null) {
			// TODO print error
			System.out.println("[RB_Score::getScore] \\ user null");
			Window.alert(constants.errorNotLoggedIn());
			return;
		}


		AsyncCallback<RiskFactorScore> callback = new AsyncCallback<RiskFactorScore>() {

			@Override
			public void onSuccess(RiskFactorScore score) {
				if (score == null || score.getUserId().equals(null)) {
					System.out.println("[RB_Score::getScore] \\ score null");
					Window.alert(constants.supportMessageScoreFailed());
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
					String output = "{ \"blood_pressure\": { \"id\":1, \"score\":" + score.getMidlifeHypertension() + ", \"imageUrl\": \"" + infosRiskFactors.get(0).getImage_url() +"\"" +
							"},\"cholesterol\": { \"id\":10, \"score\":" + score.getCholesterol() + ", \"imageUrl\": \"" + infosRiskFactors.get(9).getImage_url() +"\"" +
							"}, \"cognitive_activity\": { \"id\":8, \"score\":" + score.getHighCognitiveActivity() + ", \"imageUrl\": \"" + infosRiskFactors.get(7).getImage_url() +"\"" +
							"},\"diabetes\": { \"id\":3, \"score\":" + score.getDiabetes() + ", \"imageUrl\": \"" + infosRiskFactors.get(2).getImage_url() +"\"" +
							"},\"diet\": { \"id\":9, \"score\":" + score.getHealthyDiet() + ", \"imageUrl\": \"" + infosRiskFactors.get(8).getImage_url() +"\"" +
							"},\"drinking\": { \"id\":6, \"score\":" + score.getAlcohol() + ", \"imageUrl\": \"" + infosRiskFactors.get(5).getImage_url() +"\"" +
							"},\"heart_disease\": { \"id\":11, \"score\":" + score.getCoronaryHeartDisease() + ", \"imageUrl\": \"" + infosRiskFactors.get(10).getImage_url() +"\"" +
							"},\"kidney_disease\": { \"id\":12, \"score\":" + score.getChronicKidneyDisease() + ", \"imageUrl\": \"" + infosRiskFactors.get(11).getImage_url() +"\"" +
							"},\"mood\": { \"id\":2, \"score\":" + score.getDepression() + ", \"imageUrl\": \"" + infosRiskFactors.get(1).getImage_url() +"\"" +
							"}, \"obesity\": { \"id\":7, \"score\":" + score.getMidlifeObesity() + ", \"imageUrl\": \"" + infosRiskFactors.get(6).getImage_url() +"\"" +
							"}, \"physical_exercise\": { \"id\":4, \"score\":" + score.getPhysicalInactivity() + ", \"imageUrl\": \"" + infosRiskFactors.get(3).getImage_url() +"\"" +
							"},\"smoking\": { \"id\":5, \"score\":" + score.getSmoking() + ", \"imageUrl\": \"" + infosRiskFactors.get(4).getImage_url() +"\"" +
							"}}";

					DOM.getElementById("scoreInputRPC").setAttribute("value",output);

					trigerJavascript();
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Score::getScore] \\ exception null");
				Window.alert(constants.supportMessageScoreFailed());
			}
		};

		InminddServiceSvc.getLibraScore(user, callback);
		return;
	}

	public static native void trigerUserIDJavascript(String userID) /*-{
		$wnd.trigeredUserIDByGWT(userID);
     }-*/;
	
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
		DOM.getElementById("keepthisup").setInnerHTML(constants.keepthisup());
		DOM.getElementById("rfi").setInnerHTML(constants.rfi());
		DOM.getElementById("rmw").setInnerHTML(constants.rmw());
		DOM.getElementById("rf-cognitive").setInnerHTML(constants.rf_cognitive());
		DOM.getElementById("rf-mood").setInnerHTML(constants.rf_mood());
		DOM.getElementById("rf-diet").setInnerHTML(constants.rf_diet());
		DOM.getElementById("rf-pressure").setInnerHTML(constants.rf_pressure());
		DOM.getElementById("rf-obesity").setInnerHTML(constants.rf_obesity());
		DOM.getElementById("rf-smoking").setInnerHTML(constants.rf_smoking());
		DOM.getElementById("rf-cholesterol").setInnerHTML(constants.rf_cholesterol());
		DOM.getElementById("rf-diabetes").setInnerHTML(constants.rf_diabetes());
		DOM.getElementById("rf-kidney").setInnerHTML(constants.rf_kidney());
		DOM.getElementById("rf-heart").setInnerHTML(constants.rf_heart());
		DOM.getElementById("rf-activity").setInnerHTML(constants.rf_activity());
		DOM.getElementById("rf-alchool").setInnerHTML(constants.rf_alchool());
		
		DOM.getElementById("rf-cognitive-1").setInnerHTML(constants.rf_cognitive());
		DOM.getElementById("rf-mood-1").setInnerHTML(constants.rf_mood());
		DOM.getElementById("rf-diet-1").setInnerHTML(constants.rf_diet());
		DOM.getElementById("rf-pressure-1").setInnerHTML(constants.rf_pressure());
		DOM.getElementById("rf-obesity-1").setInnerHTML(constants.rf_obesity());
		DOM.getElementById("rf-smoking-1").setInnerHTML(constants.rf_smoking());
		DOM.getElementById("rf-cholesterol-1").setInnerHTML(constants.rf_cholesterol());
		DOM.getElementById("rf-activity-1").setInnerHTML(constants.rf_activity());
		DOM.getElementById("rf-alchool-1").setInnerHTML(constants.rf_alchool());

		DOM.getElementById("score-1").setInnerHTML(constants.score_1());
		DOM.getElementById("score-2").setInnerHTML(constants.score_2());
		DOM.getElementById("score-3").setInnerHTML(constants.score_3());
		DOM.getElementById("score-4").setInnerHTML(constants.score_4());
		DOM.getElementById("score-5").setInnerHTML(constants.score_5());
		DOM.getElementById("score-6").setInnerHTML(constants.score_6());
		DOM.getElementById("score-7").setInnerHTML(constants.score_7());
		DOM.getElementById("score-8").setInnerHTML(constants.score_8());
		DOM.getElementById("score-9").setInnerHTML(constants.score_9());
		DOM.getElementById("score-10").setInnerHTML(constants.score_10());
		DOM.getElementById("score-11").setInnerHTML(constants.score_11());
		DOM.getElementById("score-12").setInnerHTML(constants.score_12());
		DOM.getElementById("score-13").setInnerHTML(constants.score_13());
		DOM.getElementById("score-14").setInnerHTML(constants.score_14());
		DOM.getElementById("score-15").setInnerHTML(constants.score_15());
		DOM.getElementById("score-16").setInnerHTML(constants.score_16());
		DOM.getElementById("score-17").setInnerHTML(constants.score_17());
		DOM.getElementById("score-18").setInnerHTML(constants.score_18());
		DOM.getElementById("score-18-1").setInnerHTML(constants.score_18());
		DOM.getElementById("score-18-2").setInnerHTML(constants.score_18());
		DOM.getElementById("score-19").setInnerHTML(constants.score_19());
		DOM.getElementById("score-20").setInnerHTML(constants.score_20());
		DOM.getElementById("score-21").setInnerHTML(constants.score_21());
		DOM.getElementById("score-23").setInnerHTML(constants.score_23());
		DOM.getElementById("score-24").setInnerHTML(constants.score_24());
		DOM.getElementById("score-28").setInnerHTML(constants.score_28());
		DOM.getElementById("score-29").setInnerHTML(constants.score_29());
		DOM.getElementById("score-30").setInnerHTML(constants.score_30());
		DOM.getElementById("privacy-policy").setInnerHTML(constants.privacy_policy());
	}

}
