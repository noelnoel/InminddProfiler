package com.inmindd.dcu.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inmindd.dcu.shared.RiskFactorScore;
import com.inmindd.dcu.shared.CognitiveOneInfo;
import com.inmindd.dcu.shared.CognitiveTwoInfo;
import com.inmindd.dcu.shared.DietInfo;
import com.inmindd.dcu.shared.FamilyHistoryInfo;
import com.inmindd.dcu.shared.FeelingsInfo;
import com.inmindd.dcu.shared.MedicalInfo;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.PhysicalActivityInfo;
import com.inmindd.dcu.shared.SmokeAlcoholInfo;
import com.inmindd.dcu.shared.SupportExperts;
import com.inmindd.dcu.shared.SupportFAQ;
import com.inmindd.dcu.shared.SupportGoal;
import com.inmindd.dcu.shared.SupportGoalUser;
import com.inmindd.dcu.shared.SupportRiskFactorInfos;
import com.inmindd.dcu.shared.User;

/**
 * The async counterpart of <code>Inmindd</code>.
 */
public interface InminddServiceAsync {
	void authenticateUser(String idUser,String password, AsyncCallback<User> callback)
			throws IllegalArgumentException;
	
	void registerUser(User user, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	
	void updatePatientInfo(Patient patient, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;	
	void queryPatientInfo(User user, AsyncCallback<Patient> callback)
				throws IllegalArgumentException;
	
	void updateFeelingsInfo(FeelingsInfo feelings, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;	
	void queryFeelingsInfo(User user, AsyncCallback<FeelingsInfo> callback)
			throws IllegalArgumentException;
	
	void updateMedicalHealth(MedicalInfo medical, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	void queryMedicalHealth(User user, AsyncCallback<MedicalInfo> callback)
			throws IllegalArgumentException;
	
	void updateFamilyHistory(FamilyHistoryInfo family, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	void queryFamilyHistory(User user, AsyncCallback<FamilyHistoryInfo> callback)
			throws IllegalArgumentException;
	
	
	void updatePhysicalActivity(PhysicalActivityInfo activity, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;	
	void queryPhysicalActivity(User user, AsyncCallback<PhysicalActivityInfo> callback)
			throws IllegalArgumentException;
	
	void updateCognitiveOne(CognitiveOneInfo cognitiveOne, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	void queryCognitiveOne(User user, AsyncCallback<CognitiveOneInfo> callback)
			throws IllegalArgumentException;
	
	void updateCognitiveTwo(CognitiveTwoInfo cognitiveTwo, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	void queryCognitiveTwo(User user, AsyncCallback<CognitiveTwoInfo> callback)
			throws IllegalArgumentException;
	
	void updateSmokeAlcohol(SmokeAlcoholInfo smokeAlco, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;	
	void querySmokeAlcohol(User user, AsyncCallback<SmokeAlcoholInfo> callback)
			throws IllegalArgumentException;
	
	void updateDiet(DietInfo diet, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	void queryDiet(User user, AsyncCallback<DietInfo> callback)
			throws IllegalArgumentException;
	
	void getLibraScore(User user, AsyncCallback<com.inmindd.dcu.shared.RiskFactorScore> callback)
			throws IllegalArgumentException;
	
	
	void getUserConnected(AsyncCallback<com.inmindd.dcu.shared.User> callback)
			throws IllegalArgumentException;
	
	void unsetUserConnected(AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;

	void updateSupportGoalUser(SupportGoalUser goal, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;

	void querySupportGoalUser(User user, AsyncCallback<ArrayList<SupportGoalUser>> callback)
			throws IllegalArgumentException;

	void querySupportGoals(int riskFactor, String lang, AsyncCallback<ArrayList<SupportGoal>> callback)
			throws IllegalArgumentException;
	
	void querySupportRiskFactorInfos(User user, int riskfactorId, AsyncCallback<SupportRiskFactorInfos> callback)
			throws IllegalArgumentException;
	
	void querySupportFAQ(String lang, AsyncCallback<ArrayList<SupportFAQ>> callback)
			throws IllegalArgumentException;
	
	void querySupportExperts(String lang, AsyncCallback<ArrayList<SupportExperts>> callback)
			throws IllegalArgumentException;
	
	void sendMail(String email, String body, AsyncCallback<Boolean> callback)  
			throws IllegalArgumentException;
}

