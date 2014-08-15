package com.inmindd.dcu.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.inmindd.dcu.shared.RiskFactorScore;
import com.inmindd.dcu.shared.SupportRiskFactorInfos;
import com.inmindd.dcu.shared.User;

public class Admin implements EntryPoint {
	private InminddServiceAsync InminddServiceSvc;
	private User user;
	private AsyncCallback<Boolean> callbackAdmin;
	private AsyncCallback<ArrayList<String>> callbackUsersList;
	private static Admin lastInstance;
	private static User userQuery;
	private static AsyncCallback<RiskFactorScore> callbackScore;
	private ArrayList<String> userList;
	private InminddConstants constants;
	private ArrayList<SupportRiskFactorInfos> infosRiskFactors;

	@Override
	public void onModuleLoad() {
		lastInstance = this;
		exportClickUser();
		globalize();
		callServiceSetup();

		Element elem3 = DOM.getElementById("emailButton");
		DOM.sinkEvents(elem3, Event.ONCLICK);
		EventListener eventRegister = new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				getScores();
			}
		};
		DOM.setEventListener(elem3, eventRegister);
		
		callbackAdmin = new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean admin) {
				if(!admin){
					System.out.println("[RB_Admin::getAdmin] \\ user not admin");
					Window.alert(constants.supportMessageAdmin());
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html");
				} else {
					InminddServiceSvc.queryAllUsers(callbackUsersList);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Admin::getAdmin] \\ exception null");
				Window.alert(constants.supportMessageInternalError());
			}
		};
		
		callbackUsersList = new AsyncCallback<ArrayList<String>>() {
			@Override
			public void onSuccess(ArrayList<String> users) {
				if(users == null || users.size() <= 0){
					System.out.println("[RB_Admin::getUsersList] \\ aucuns users");
					Window.alert(constants.supportMessageNoUsers());
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html");
				} else {
					String output = "[";
					boolean firstTime = true;
					for(String u : users){
						if(!firstTime){ output += ","; }
						else { firstTime = false; }
						output += "\"" + u + "\"";
					}
					output += "]";
					
					userList = users; 

					DOM.getElementById("userInputRPC").setAttribute("value",output);
					trigerJavascript();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Admin::getAdmin] \\ exception null");
				Window.alert(constants.supportMessageInternalError());
			}
		};
		
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user == null) {
					System.out.println("[RB_Admin::getUser] \\ user null");
					Window.alert(constants.errorNotLoggedIn());
					Window.Location.assign(GWT.getHostPageBaseURL() + "index.html?page=support");
				} else {
					//DOM.getElementById("scoreInputRPC").setInnerText(user.toString());
					setUser(user);
					trigerUserIDJavascript(user.getUserId());
					InminddServiceSvc.isAdministrator(callbackAdmin);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Admin::getUser] \\ exception null");
				// TODO print error
			}
		};

		InminddServiceSvc.getUserConnected(callback);
	}

	public static native void trigerJavascript() /*-{
		$wnd.trigeredUserByGWT();
     }-*/;

	public static native void trigerJavascriptScore() /*-{
		$wnd.trigeredByGWT();
     }-*/;

	public static native void trigerJavascriptMailing() /*-{
		$wnd.trigeredMailingByGWT();
     }-*/;
	
	public static void clickUser(String userId) {
		userQuery = new User();
		userQuery.setUserId(userId);
		
		callbackScore = new AsyncCallback<RiskFactorScore>() {

			@Override
			public void onSuccess(RiskFactorScore score) {
				if (score == null || score.getUserId().equals(null)) {
					System.out.println("[RB_Admin::getScore] \\ score null");
					// TODO print error
				} else {
					String output = "{ \"blood_pressure\": { \"id\":1, \"score\":" + score.getMidlifeHypertension() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(0).getImage_url() +"\"" +
							"},\"cholesteral\": { \"id\":10, \"score\":" + score.getCholesterol() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(9).getImage_url() +"\"" +
							"}, \"cognitive_activity\": { \"id\":8, \"score\":" + score.getHighCognitiveActivity() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(7).getImage_url() +"\"" +
							"},\"diabetes\": { \"id\":3, \"score\":" + score.getDiabetes() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(2).getImage_url() +"\"" +
							"},\"diet\": { \"id\":9, \"score\":" + score.getHealthyDiet() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(8).getImage_url() +"\"" +
							"},\"drinking\": { \"id\":6, \"score\":" + score.getAlcohol() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(5).getImage_url() +"\"" +
							"},\"heart_disease\": { \"id\":11, \"score\":" + score.getCoronaryHeartDisease() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(10).getImage_url() +"\"" +
							"},\"kidney_disease\": { \"id\":12, \"score\":" + score.getChronicKidneyDisease() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(11).getImage_url() +"\"" +
							"},\"mood\": { \"id\":2, \"score\":" + score.getDepression() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(1).getImage_url() +"\"" +
							"}, \"obesity\": { \"id\":7, \"score\":" + score.getMidlifeObesity() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(6).getImage_url() +"\"" +
							"}, \"physical_exercise\": { \"id\":4, \"score\":" + score.getPhysicalInactivity() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(3).getImage_url() +"\"" +
							"},\"smoking\": { \"id\":5, \"score\":" + score.getSmoking() + ", \"imageUrl\": \"" + lastInstance.infosRiskFactors.get(4).getImage_url() +"\"" +
							"}}";

					DOM.getElementById("scoreInputRPC").setAttribute("value",output);
					trigerJavascriptScore();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Admin::getScore] \\ exception null");
				// TODO print error
			}
		};
		
		AsyncCallback<ArrayList<SupportRiskFactorInfos>> callback = new AsyncCallback<ArrayList<SupportRiskFactorInfos>>() {
			@Override
			public void onSuccess(ArrayList<SupportRiskFactorInfos> riskFactorsInfos) {
				if (riskFactorsInfos == null || riskFactorsInfos.size() == 0) {
					System.out.println("[RB_Score::getInfos] \\ infos null");
					// TODO print error
				} else {
					lastInstance.infosRiskFactors = riskFactorsInfos;
					lastInstance.InminddServiceSvc.getLibraScore(userQuery, callbackScore);
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Score::getInfos] \\ exception null");
				// TODO print error
			}
		};

		lastInstance.InminddServiceSvc.queryAllSupportRiskFactorInfos(lastInstance.user, callback);

		return;
	}
	
	public static native void exportClickUser() /*-{
		$wnd.userIdClick =
		$entry(@com.inmindd.dcu.client.Admin::clickUser(Ljava/lang/String;));
	}-*/;

	private void setUser(User user) {
		this.user = user;
	}
	
	private void getScores(){
		AsyncCallback<RiskFactorScore> callback = new AsyncCallback<RiskFactorScore>() {
			@Override
			public void onSuccess(RiskFactorScore score) {
				if (score == null || score.getUserId().equals(null)) {
					System.out.println("[RB_Admin::getScores] \\ score null");
				} else {
					scores.add(score);
					
					if(scores.size() == userList.size()){
						// DO THE JS CaLL
						String output = "";
						for (RiskFactorScore scoreOver : scores){
							boolean first = true;
							
							output += scoreOver.getUserId() + ";";
							
							if(scoreOver.getMidlifeHypertension() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_pressure();
							}
							if(scoreOver.getDepression() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_mood();
							}
							if(scoreOver.getPhysicalInactivity() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_activity();
							}
							if(scoreOver.getSmoking() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_smoking();
							}
							if(scoreOver.getAlcohol() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_alchool();
							}
							if(scoreOver.getMidlifeObesity() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_obesity();
							}
							if(scoreOver.getHighCognitiveActivity() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_cognitive();
							}
							if(scoreOver.getHealthyDiet() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_diet();
							}
							if(scoreOver.getCholesterol() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_cholesterol();
							}
							if(scoreOver.getDiabetes() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_diabetes();
							}
							if(scoreOver.getCoronaryHeartDisease() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_heart();
							}
							if(scoreOver.getChronicKidneyDisease() != 0){
								if(!first){ output += ","; }
								first = false;
								output += lastInstance.constants.rf_kidney();
							}
							
							output += "|";
						}
						DOM.getElementById("mailingInputRPC").setAttribute("value",output);
						DOM.getElementById("loadingPanel").setAttribute("style", "display:none");
						trigerJavascriptMailing();
					}
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("[RB_Admin::getScores] \\ exception null");
			}
		};
		
		boolean start = true;
		scores = new ArrayList<RiskFactorScore>();
		for (String userId : userList) {
			User userFake = new User();
			userFake.setUserId(userId);
			InminddServiceSvc.getLibraScore(userFake, callback);
			if(start){
				DOM.getElementById("loadingPanel").setAttribute("style", "text-align:center;margin-top:150px;");
				DOM.getElementById("choosePanel").setAttribute("style", "display:none");
				start = false;
			}
		}
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
		DOM.getElementById("menu-support-goals").setInnerHTML(constants.goal_0());
		DOM.getElementById("menu-support-logout").setInnerHTML(constants.logout());
		DOM.getElementById("eu-advert-message").setInnerHTML(constants.euFunding());
		DOM.getElementById("menu-support-apps").setInnerHTML(constants.menu_support_apps());
		DOM.getElementById("menu-inmindd").setInnerHTML(constants.menu_inmindd());
		DOM.getElementById("menu-contact").setInnerHTML(constants.menu_contact());
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
	}


}
