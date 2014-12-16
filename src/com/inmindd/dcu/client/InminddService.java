package com.inmindd.dcu.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inmindd.dcu.shared.CognitiveOneInfo;
import com.inmindd.dcu.shared.CognitiveTwoInfo;
import com.inmindd.dcu.shared.DietInfo;
import com.inmindd.dcu.shared.FamilyHistoryInfo;
import com.inmindd.dcu.shared.FeelingsInfo;
import com.inmindd.dcu.shared.MedicalInfo;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.PhysicalActivityInfo;
import com.inmindd.dcu.shared.SmokeAlcoholInfo;
import com.inmindd.dcu.shared.SupportApps;
import com.inmindd.dcu.shared.SupportExperts;
import com.inmindd.dcu.shared.SupportFAQ;
import com.inmindd.dcu.shared.SupportGoal;
import com.inmindd.dcu.shared.SupportGoalUser;
import com.inmindd.dcu.shared.SupportRiskFactorInfos;
import com.inmindd.dcu.shared.User;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("Inmindd")
public interface InminddService extends RemoteService {
	public User authenticateUser(String idUser, String password) throws IllegalArgumentException;
	public User authenticateUserSupportEnvironement(String idUser, String password) throws IllegalArgumentException;
	public Boolean registerUser(User user)throws IllegalArgumentException;
	public Boolean duplicateUser(String id) throws IllegalArgumentException;
	public Boolean resetPassword(User user) throws IllegalArgumentException;

	public Boolean setRandomiseUserStatus(User user) throws IllegalArgumentException;
	public String getRandomisedGroup(User user)throws IllegalArgumentException;
	

	
	public Boolean updatePatientInfo(Patient patient)	throws IllegalArgumentException;
	public Patient queryPatientInfo(User user) 		throws IllegalArgumentException;
	
	public Boolean updateFeelingsInfo(FeelingsInfo feelings)	throws IllegalArgumentException;
	public FeelingsInfo queryFeelingsInfo(User user) 			throws IllegalArgumentException;
	
	public Boolean updateMedicalHealth(MedicalInfo medical)	throws IllegalArgumentException;
	public MedicalInfo queryMedicalHealth(User user) 			throws IllegalArgumentException;	
	
	public Boolean updateFamilyHistory(FamilyHistoryInfo history)	throws IllegalArgumentException;
	public FamilyHistoryInfo queryFamilyHistory(User user) 			throws IllegalArgumentException;
	
	public Boolean updatePhysicalActivity(PhysicalActivityInfo activity)	throws IllegalArgumentException;
	public PhysicalActivityInfo queryPhysicalActivity(User user) 			throws IllegalArgumentException;
	
	public Boolean updateCognitiveOne(CognitiveOneInfo cognitiveOne)	throws IllegalArgumentException;
	public CognitiveOneInfo queryCognitiveOne(User user) 			throws IllegalArgumentException;
	
	public Boolean updateCognitiveTwo(CognitiveTwoInfo cognitiveTwo)	throws IllegalArgumentException;
	public CognitiveTwoInfo queryCognitiveTwo(User user) 			throws IllegalArgumentException;
	public Boolean updateSmokeAlcohol(SmokeAlcoholInfo smokeAlco)	throws IllegalArgumentException;
	public SmokeAlcoholInfo querySmokeAlcohol(User user) 			throws IllegalArgumentException;
	
	public Boolean updateDiet(DietInfo diet)	throws IllegalArgumentException;
	public DietInfo queryDiet(User user) 			throws IllegalArgumentException;
	public com.inmindd.dcu.shared.RiskFactorScore getLibraScore(User user) 			throws IllegalArgumentException;
	
	public User getUserConnected() throws IllegalArgumentException;
	public Boolean unsetUserConnected() throws IllegalArgumentException;
	public Boolean updateSupportGoalUser(SupportGoalUser history)	throws IllegalArgumentException;
	public SupportRiskFactorInfos querySupportRiskFactorInfos(User user, int riskfactorId) throws IllegalArgumentException;
	public ArrayList<SupportRiskFactorInfos> queryAllSupportRiskFactorInfos(User user) throws IllegalArgumentException;
	public ArrayList<SupportGoalUser> querySupportGoalUser(User user) 			throws IllegalArgumentException;
	public ArrayList<SupportGoal> querySupportGoals(int riskFactor, String lang)		throws IllegalArgumentException;
	public ArrayList<SupportFAQ> querySupportFAQ(String lang)		throws IllegalArgumentException;
	public ArrayList<SupportExperts> querySupportExperts(String lang)		throws IllegalArgumentException;
	public Boolean sendMail(String email, String lang, String body)  	throws IllegalArgumentException;
	public ArrayList<SupportApps> querySupportApps(String lang)			throws IllegalArgumentException;
	public boolean isAdministrator() throws IllegalArgumentException;
	public ArrayList<String> queryAllUsers() throws IllegalArgumentException;
	
	public Boolean addUserEmail(String userId, String emailAddr) throws IllegalArgumentException;
	public Boolean updateUserLastLogin(String userId) throws IllegalArgumentException;
	public Boolean updateUserMail(String userId, String emailAddress) throws IllegalArgumentException;
	public Boolean deleteUserMail(String userId) throws IllegalArgumentException;
	
}
