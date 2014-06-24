package com.inmindd.dcu.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;
import com.inmindd.dcu.client.InminddService;
import com.inmindd.dcu.client.PhysicalActivity;
import com.inmindd.dcu.client.SmokeAlcohol;
import com.inmindd.dcu.shared.CalculateScore;
import com.inmindd.dcu.shared.CognitiveOneInfo;
import com.inmindd.dcu.shared.CognitiveTwoInfo;
import com.inmindd.dcu.shared.DietInfo;
import com.inmindd.dcu.shared.FamilyHistoryInfo;
import com.inmindd.dcu.shared.FeelingsInfo;
import com.inmindd.dcu.shared.MedicalInfo;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.PhysicalActivityInfo;
import com.inmindd.dcu.shared.RiskFactorScore;
import com.inmindd.dcu.shared.SmokeAlcoholInfo;
import com.inmindd.dcu.shared.SupportGoal;
import com.inmindd.dcu.shared.SupportGoalUser;
import com.inmindd.dcu.shared.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class InminddServiceImpl extends RemoteServiceServlet implements InminddService {
	private User user;	
	private final static byte[] GWT_DES_KEY = new byte[] { -110, 121, -65, 22, -60, 61, -22, -60, 21, -122, 41, -89, -89, -68, -8, 41, -119, -51, -12, -36, 19, -8, -17, 47 };
	private   String decryptedPassword;
	protected Connection conn; 
	private Patient patient;
	private FeelingsInfo feelings;
	private MedicalInfo medical;
	private FamilyHistoryInfo history;
	private PhysicalActivityInfo physical;
	private SmokeAlcoholInfo smokeAlcohol;
	private CognitiveTwoInfo cognitiveTwo;
	private CognitiveOneInfo cognitiveOne;
	private DietInfo diet;
	private SupportGoalUser goal;

	@Override
	public User authenticateUser(String idUser, String password) throws IllegalArgumentException {	
		//open database connection
		initDBConnection();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM user where userId = " + idUser);
			result = pstmt.executeQuery("SELECT * FROM user where userId = " + idUser);
			while (result.next()) {
				if (result.getString(2).equals(password)) {
					//System.out.println(result.getString(4));
					user = new User();
					user.setUserId(result.getString(1));
				//	user.setCountryCode(result.getString(2));
				//	user.setPractice(result.getString(3));
					conn.close();
					getThreadLocalRequest().getSession().setAttribute("current_user", user);
					return user;
				}

			}
			user = null;
			getThreadLocalRequest().getSession().setAttribute("current_user", null);
			conn.close();
			return user;


		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			// Cleanup
			//  result.close();
			//   pstmt.close();
			//c//onn.close();
		}

		return user;
	}

	@Override
	public Boolean registerUser(User user) throws IllegalArgumentException {

		//open database connection
		initDBConnection();
		// Verify that the input is valid. 
		String passwordhash = user.getPassword();

		String userId = user.getUserId();
	/*	String countryCode = user.getCountryCode();
		String practice = user.getPractice(); */
		long time = System.currentTimeMillis();
		
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  
		String insert = "insert  into user (userId,passwordhash,timestamp)  values(?,?,?)";
			

		try {
			PreparedStatement updatePatientInfo = conn.prepareStatement(insert);
			updatePatientInfo.setString(1, userId);
			
			updatePatientInfo.setString(2, passwordhash);

			updatePatientInfo.setTimestamp(3, timestamp);
			updatePatientInfo.executeUpdate();
			conn.close();
		} catch (SQLException e) {

			return false;
		}

		return true;
	}

	
	@Override
	public Boolean updatePatientInfo(Patient patient) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		
		if (createPatientInfo(patient)) {
			
				return  true;
			
		
		}
		else {
			try {
				conn.close();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}

	private boolean createPatientInfo(Patient patient) {
		String patient_id = patient.getUserId();
		int age = patient.getAge();
		String gender = patient.getGender();
		String country_of_birth = patient.getCountryOfBirth();
		String marital_status = patient.getMaritalStatus();
		String living_arrangements = patient.getLivingArrangements();
		
		String occupational_group = patient.getOccupationalGroup();
		String employment_status = patient.getEmploymentStatus();
		String education_level = patient.getEducationLevel();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  
		String insert = "insert  into patient_info (patient_id, timestamp, age,gender,country_of_birth,"
				+ "marital_status,living_arrangements, occupational_group,employment_status, education_level)"
				+ " values(?,?,?,?,?,?,?,?,?,?) on duplicate key update age = values(age), gender = values(gender),"
				+ "country_of_birth = values(country_of_birth),marital_status = values(marital_status),"
				+ "living_arrangements = values(living_arrangements),"
				+ "occupational_group = values(occupational_group), employment_status = values(employment_status),"
				+ "education_level = values(education_level)";
		try {
			PreparedStatement updatePatientInfo = (PreparedStatement) conn.prepareStatement(insert);
			updatePatientInfo.setString(1, patient_id);
			updatePatientInfo.setTimestamp(2,  timestamp);
			updatePatientInfo.setInt(3,  age);
			updatePatientInfo.setString(4, gender);
			updatePatientInfo.setString(5, country_of_birth);
			updatePatientInfo.setString(6, marital_status);
			updatePatientInfo.setString(7, living_arrangements);
			
			updatePatientInfo.setString(8,occupational_group);
			updatePatientInfo.setString(9, employment_status);
			updatePatientInfo.setString(10, education_level);
			updatePatientInfo.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Patient queryPatientInfo(User user) {
		//open database connection
		patient = new Patient();
		initDBConnection();

		if (getPatientInfo(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  patient;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return patient;
		}
	}
	
	
	private boolean getPatientInfo(User user) {
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM patient_info where patient_id = " + idUser);
			result = pstmt.executeQuery("SELECT * FROM patient_info where patient_id = " + idUser);
			
			while (result.last()) {
				patient.setUserId(result.getString(1));
				patient.setCountryOfBirth(result.getString(5));
				patient.setAge(result.getInt(3));
				patient.setGender(result.getString(4));
				patient.setMaritalStatus(result.getString(6));
				patient.setLivingArrangements(result.getString(7));
				patient.setOccupationalGroup(result.getString(8));
				patient.setEmploymentStatus(result.getString(9));
				
				patient.setEducationLevel(result.getString(10));
			
				conn.close();
				return true;
				
			}
			
			conn.close();
			return false;
			}
		catch (SQLException e) {
			user = null;
			
			//return user;
		return false;
		}
			
	}
	
	
	@Override
	public Boolean updateFeelingsInfo(FeelingsInfo feelings) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		
		if (createFeelings(feelings)) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
	
	protected boolean createFeelings( FeelingsInfo info) {
		String patient_id = info.getUserId();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  
		
		String ces1 = info.getCes1();
		String ces2 = info.getCes2();
		String ces3 = info.getCes3();
		String ces4 = info.getCes4();
		String ces5 = info.getCes5();
		String ces6 = info.getCes6();
		String ces7 = info.getCes7();
		String ces8 = info.getCes8();
		String ces9 = info.getCes9();
		String ces10 = info.getCes10();
		String ces11 = info.getCes11();
		String ces12 = info.getCes12();
		String ces13 = info.getCes13();
		String ces14 = info.getCes14();
		String ces15 = info.getCes15();
		String ces16 = info.getCes16();
		String ces17 = info.getCes17();
		String ces18 = info.getCes18();
		String ces19 = info.getCes19();
		String ces20 = info.getCes20();
		String depression = info.getDepression();
		String treated = info.getTreated();
	    
	    
		String insert = "insert  into feelings_info (patient_id,timestamp,ces1,ces2,ces3,ces4,ces5,ces6,ces7,"
				+ "ces8,ces9,ces10,ces11,ces12,ces13,ces14,ces15,ces16,ces17,ces18,ces19,ces20,depression,treated)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
		try {
			PreparedStatement updateFeelingInfo = (PreparedStatement) conn.prepareStatement(insert);
			updateFeelingInfo.setString (1, patient_id);
			updateFeelingInfo.setTimestamp(2,  timestamp);	
			updateFeelingInfo.setString(3,  ces1);
			updateFeelingInfo.setString(4,  ces2);
			updateFeelingInfo.setString(5,  ces3);
			updateFeelingInfo.setString(6,  ces4);
			updateFeelingInfo.setString(7,  ces5);
			updateFeelingInfo.setString(8,  ces6);
			updateFeelingInfo.setString(9,  ces7);
			updateFeelingInfo.setString(10,  ces8);
			updateFeelingInfo.setString(11,  ces9);
			updateFeelingInfo.setString(12,  ces10);
			updateFeelingInfo.setString(13,  ces11);
			updateFeelingInfo.setString(14,  ces12);
			updateFeelingInfo.setString(15,  ces13);
			updateFeelingInfo.setString(16,  ces14);
			updateFeelingInfo.setString(17,  ces15);
			updateFeelingInfo.setString(18,  ces16);
			updateFeelingInfo.setString(19,  ces17);
			updateFeelingInfo.setString(20,  ces18);
			updateFeelingInfo.setString(21,  ces19);
			updateFeelingInfo.setString(22,  ces20);
			updateFeelingInfo.setString(23,  depression);
			updateFeelingInfo.setString(24,  treated);	
			
			
			updateFeelingInfo.executeUpdate();
			conn.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		
			return false;
		}
			
	}
	
	public FeelingsInfo queryFeelingsInfo(User user) {
		
		feelings = new FeelingsInfo();
		//open database connection
		initDBConnection();

		if (!getFeelingsInfo(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return  feelings;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return feelings;
		}
	}
	
	private boolean getFeelingsInfo(User user){

		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery("SELECT * FROM feelings_info where patient_id = " + idUser);

			while (result.last()) {
				feelings.setUserId(result.getString(1));
				feelings.setCes1(result.getString(3));
				feelings.setCes2(result.getString(4));
				feelings.setCes3(result.getString(5));
				feelings.setCes4(result.getString(6));
				feelings.setCes5(result.getString(7));
				feelings.setCes6(result.getString(8));
				feelings.setCes7(result.getString(9));
				feelings.setCes8(result.getString(10));
				feelings.setCes9(result.getString(11));
				feelings.setCes10(result.getString(12));
				feelings.setCes11(result.getString(13));
				feelings.setCes12(result.getString(14));
				feelings.setCes13(result.getString(15));
				feelings.setCes14(result.getString(16));
				feelings.setCes15(result.getString(17));
				feelings.setCes16(result.getString(18));
				feelings.setCes17(result.getString(19));
				feelings.setCes18(result.getString(20));
				feelings.setCes19(result.getString(21));
				feelings.setCes20(result.getString(22));
				feelings.setDepression(result.getString(23));
				feelings.setTreated(result.getString(24));
				conn.close();
				return true;

			}

			conn.close();
			return false;
		}
		catch (SQLException e) {
			user = null;		
			return false;
		}


	}

	
	@Override
	public Boolean updateMedicalHealth(MedicalInfo medical) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		if (createMedical(medical)) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
	private boolean createMedical(MedicalInfo medical) {
		String patient_id = medical.getUserId();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  

		double cent = medical.getCent();
		double feet = medical.getFeet();
		double inches = medical.getInches();
		double kilo = medical.getKilo();
		double stone = medical.getStone();
		double lbs = medical.getLbs();
		double mmol = medical.getMmol();
		double systolic = medical.getSystolic();
		double diastolic = medical.getDiastolic();

		String chol = medical.getChol();
		String highChol = medical.getHighChol();
		String lifeStyleChange = medical.getLifeStyleChange();
		String medChol = medical.getMedChol();
		String cvd = medical.getCvd();
		String hyper = medical.getHyper();
		String bloodPressure = medical.getBloodPressure();
		String medBlood = medical.getMedBlood();
		String mellitus = medical.getMellitus();
		String diabetesTreatment = medical.getDiabetesTreat();
		String sugar = medical.getSugar();
		String kidney = medical.getKidney();


		String insert = "insert  into medical_info (patient_id,timestamp, cent, feet,inches,kilo,stone,lbs,mmol,systolic,diastolic,"
				+ "chol, highChol,lifeStyleChange,medChol,cvd,hyper,bloodPressure,medBlood,mellitus,diabetesTreatment,sugar,kidney)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement updateMedicalInfo = (PreparedStatement) conn.prepareStatement(insert);
			updateMedicalInfo.setString(1, patient_id);
			updateMedicalInfo.setTimestamp(2,timestamp);
			updateMedicalInfo.setDouble(3,cent);
			updateMedicalInfo.setDouble(4,feet);
			updateMedicalInfo.setDouble(5,inches);
			updateMedicalInfo.setDouble(6,kilo);
			updateMedicalInfo.setDouble(7,stone);
			updateMedicalInfo.setDouble(8,lbs);
			updateMedicalInfo.setDouble(9,mmol);
			updateMedicalInfo.setDouble(10,systolic);
			updateMedicalInfo.setDouble(11,diastolic);
			updateMedicalInfo.setString(12,chol);
			updateMedicalInfo.setString(13,highChol);
			updateMedicalInfo.setString(14,lifeStyleChange);
			updateMedicalInfo.setString(15,medChol);
			updateMedicalInfo.setString(16,cvd);
			updateMedicalInfo.setString(17,hyper);
			updateMedicalInfo.setString(18,bloodPressure);
			updateMedicalInfo.setString(19,medBlood);
			updateMedicalInfo.setString(20,mellitus);
			updateMedicalInfo.setString(21,diabetesTreatment);
			updateMedicalInfo.setString(22,sugar);	
			updateMedicalInfo.setString(23,kidney);	

			updateMedicalInfo.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public MedicalInfo queryMedicalHealth(User user) {
		
		medical = new MedicalInfo();
		//open database connection
		initDBConnection();

		if (getMedicalInfo(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return  medical;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			medical = null;
			return medical;
		}
	}
	
	
	private boolean getMedicalInfo(User user){

		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery("SELECT * FROM medical_info where patient_id = " + idUser);

			while (result.last()) {
				medical.setCent((result.getDouble(3)));
			    medical.setFeet((result.getDouble(4)));
			    medical.setInches((result.getDouble(5)));
			    medical.setKilo((result.getDouble(6)));
			    medical.setStone((result.getDouble(7)));
			    medical.setLbs((result.getDouble(8)));
			    medical.setMmol((result.getDouble(9)));
			    medical.setSystolic((result.getDouble(10)));
			    medical.setDiastolic((result.getDouble(11)));
			    medical.setChol(result.getString(12));
			   
			    medical.setHighChol(result.getString(13));
			    medical.setLifeStyleChange(result.getString(14));
			    medical.setMedChol(result.getString(15));
			    medical.setCvd(result.getString(16));
			    medical.setHyper(result.getString(17));
			    medical.setBloodPressure(result.getString(18));
			    medical.setMedBlood(result.getString(19));
			    medical.setMellitus(result.getString(20));
			    medical.setDiabetesTreat(result.getString(21));
			    medical.setSugar(result.getString(22));
			    medical.setKidney(result.getString(23));
				conn.close();
				return true;

			}

			conn.close();
			return false;
		}
		catch (SQLException e) {
			user = null;		
			return false;
		}


	}
	
	@Override
	public Boolean updateFamilyHistory(FamilyHistoryInfo history) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		
		if (createHistory(history)) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
	
	
	public FamilyHistoryInfo queryFamilyHistory(User user) {
		
		history  = new FamilyHistoryInfo();
		//open database connection
		initDBConnection();

		if (getHistory(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return history;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			history = null;
			return history;
		}
	}
	

	private boolean getHistory(User user){

		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery("SELECT * FROM family_history_info where patient_id = " + idUser);

			while (result.last()) {
				history.setUserId(result.getString(1));
				history.setMotherDementia(result.getString(3));
				history.setMotherCvd(result.getString(4));
				history.setMotherDiabetes(result.getString(5));
				history.setFatherDementia(result.getString(6));
				history.setFatherCvd(result.getString(7));
				history.setFatherDiabetes(result.getString(8));
				history.setSiblingDementia(result.getString(9));
				history.setSiblingCvd(result.getString(10));
				history.setSiblingDiabetes(result.getString(11));
				conn.close();
				return true;

			}

			conn.close();
			return false;
		}
		catch (SQLException e) {
			user = null;		
			return false;
		}


	}
	private boolean createHistory(FamilyHistoryInfo history) {
		String patient_id = history.getUserId();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  
		
		String motherDementia = history.getMotherDementia();
		String motherCvd = history.getMotherCvd();
		String motherDiabetes = history.getMotherDiabetes();
		String fatherDementia = history.getFatherDementia();
		String fatherCvd = history.getFatherCvd();
		String fatherDiabetes = history.getFatherDiabetes();
		String siblingDementia = history.getSiblingDementia();
		String siblingCvd = history.getSiblingCvd();
		String siblingDiabetes = history.getSiblingDiabetes();
		
		String insert = "insert  into family_history_info (patient_id,timestamp, mother_dementia, mother_cvd, mother_diabetes, father_dementia, father_cvd, father_diabetes,"
				+ "sibling_dementia, sibling_cvd, sibling_diabetes)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement updateHistoryInfo = (PreparedStatement) conn.prepareStatement(insert);
			updateHistoryInfo.setString(1, patient_id);
			updateHistoryInfo.setTimestamp(2,timestamp);
			updateHistoryInfo.setString(3,motherDementia);
			updateHistoryInfo.setString(4,motherCvd);
			updateHistoryInfo.setString(5,motherDiabetes);
			
			updateHistoryInfo.setString(6,fatherDementia);
			updateHistoryInfo.setString(7,fatherCvd);
			updateHistoryInfo.setString(8,fatherDiabetes);
			
			updateHistoryInfo.setString(9,siblingDementia);
			updateHistoryInfo.setString(10,siblingCvd);
			updateHistoryInfo.setString(11,siblingDiabetes);
			

			updateHistoryInfo.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;		}

		return true;	
		
	}
	
	
	@Override
	public Boolean updatePhysicalActivity(PhysicalActivityInfo activity) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		
		if (createActivity(activity)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return false;
		}
	}

	
	private boolean createActivity(PhysicalActivityInfo activity) {
		String patient_id = activity.getUserId();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  
		
		int diyHours = activity.getDiyHours();
		int summerWalkingHours = activity.getSummerWalkingHours();
		int winterWalkingHours = activity.getWinterWalkingHours();
		int summerCyclingHours = activity.getSummerCyclingHours();
		int winterCyclingHours = activity.getWinterCyclingHours();
		int summerGardenHours = activity.getSummerGardenHours();
		int winterGardenHours = activity.getWinterGardenHours();
		int summerPhysicalHours = activity.getSummerPhysicalHours();
		int winterPhysicalHours = activity.getWinterPhysicalHours();
		int summerHouseworkHours = activity.getSummerHouseworkHours();
		int winterHouseworkHours = activity.getWinterHouseworkHours();
		int flightStairs = activity.getFlightStairs();
		String workActivity = activity.getPhysicalWork();
		String vigorous = activity.getVigorous();
		String insert = "insert  into physical_activities_info(patient_id,timestamp,diy_hours,  summer_walking_hours, winter_walking_hours, summer_cycling_hours, winter_cycling_hours, summer_garden_hours, winter_garden_hours,"
				+ "summer_physical_hours, winter_physical_hours,summer_housework_hours, winter_housework_hours, flight_stairs, physical_activity_work, vigorous)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement updateActivitiesInfo = (PreparedStatement) conn.prepareStatement(insert);
			updateActivitiesInfo.setString(1, patient_id);
			updateActivitiesInfo.setTimestamp(2,timestamp);
			updateActivitiesInfo.setInt(3, diyHours);
			updateActivitiesInfo.setInt(4, summerWalkingHours);
			updateActivitiesInfo.setInt(5, winterWalkingHours);
			updateActivitiesInfo.setInt(6, summerCyclingHours);
			updateActivitiesInfo.setInt(7, winterCyclingHours);
			updateActivitiesInfo.setInt(8, summerGardenHours);
			updateActivitiesInfo.setInt(9, winterGardenHours);
			updateActivitiesInfo.setInt(10, summerPhysicalHours);			
			updateActivitiesInfo.setInt(11, winterPhysicalHours);
			updateActivitiesInfo.setInt(12, summerHouseworkHours);			
			updateActivitiesInfo.setInt(13, winterHouseworkHours);
			updateActivitiesInfo.setInt(14, flightStairs);
			updateActivitiesInfo.setString(15, workActivity);
			updateActivitiesInfo.setString(16, vigorous);
			
			
			

			updateActivitiesInfo.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;		}

		return true;	
		
	}
	
	
	
	@Override
	public Boolean updateCognitiveOne(CognitiveOneInfo cognitiveOne) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		
		if (createCognitiveOne(cognitiveOne)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return false;
		}
	}

	private boolean createCognitiveOne(CognitiveOneInfo cognitiveOne) {
		String patient_id = cognitiveOne.getUserId();
		double formal_education_years = cognitiveOne.getFormalEducationYears();
		double non_formal_education_years = cognitiveOne.getNonFormalEducationYears();
		int manager = cognitiveOne.getManager();
		int manager_simul_years = cognitiveOne.getManagerSimulYears();
		int professional = cognitiveOne.getProfessional();
		int professional_simul_years = cognitiveOne.getProfessionalSimulYears();
		int technician = cognitiveOne.getTechnician();
		int technician_simul_years = cognitiveOne.getTechnicianSimulYears();
		int clerical = cognitiveOne.getClerical();
		int clerical_simul_years = cognitiveOne.getClericalSimulYears();		
		int service = cognitiveOne.getService();
		int service_simul_years = cognitiveOne.getServiceSimulYears();
		int agriculture = cognitiveOne.getAgriculture();
		int agriculture_simul_years = cognitiveOne.getAgricultureSimulYears();
		int craft = cognitiveOne.getCraft();
		int craft_simul_years = cognitiveOne.getCraftSimulYears();
		int plant = cognitiveOne.getPlant();
		int plant_simul_years = cognitiveOne.getPlantSimulYears();
		int elementary = cognitiveOne.getElementary();
		int elementary_simul_years = cognitiveOne.getElementarySimulYears();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time); 
		String insert = "insert  into cognitive_one_info (patient_id, formal_education_years,non_formal_education_years, manager, manager_simul_years,"
				+ "professional, professional_simul_years, technician, technician_simul_years, clerical, clerical_simul_years,"
				+ "service, service_simul_years, agriculture, agriculture_simul_years, "
				+ "craft, craft_simul_years, plant, plant_simul_years, elementary, elementary_simul_years)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
		try {
			PreparedStatement updateCognitiveOneInfo = (PreparedStatement) conn.prepareStatement(insert);
			updateCognitiveOneInfo.setString(1, patient_id);
			updateCognitiveOneInfo.setDouble(2,  formal_education_years);
			updateCognitiveOneInfo.setDouble(3, non_formal_education_years);
			updateCognitiveOneInfo.setInt(4, manager);
			updateCognitiveOneInfo.setInt(5, manager_simul_years);
			updateCognitiveOneInfo.setInt(6, professional);
			updateCognitiveOneInfo.setInt(7, professional_simul_years);
			updateCognitiveOneInfo.setInt(8, technician);
			updateCognitiveOneInfo.setInt(9, technician_simul_years);
			updateCognitiveOneInfo.setInt(10, clerical);
			updateCognitiveOneInfo.setInt(11, clerical_simul_years);
			updateCognitiveOneInfo.setInt(12, service);
			updateCognitiveOneInfo.setInt(13, service_simul_years);			
			updateCognitiveOneInfo.setInt(14, agriculture);
			updateCognitiveOneInfo.setInt(15, agriculture_simul_years);
			updateCognitiveOneInfo.setInt(16, craft);
			updateCognitiveOneInfo.setInt(17, craft_simul_years);
			updateCognitiveOneInfo.setInt(18, plant);
			updateCognitiveOneInfo.setInt(19, plant_simul_years);
			updateCognitiveOneInfo.setInt(20, elementary);
			updateCognitiveOneInfo.setInt(21, elementary_simul_years);
			
			updateCognitiveOneInfo.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public CognitiveOneInfo queryCognitiveOne(User user) {
		//open database connection
		cognitiveOne  = new CognitiveOneInfo();
		initDBConnection();

		if (getCognitiveOneData(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return  cognitiveOne;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return cognitiveOne;
		}
	}
	
	
	private boolean getCognitiveOneData(User user) {

		
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery("SELECT * FROM cognitive_one_info where patient_id = " + idUser);			
			while (result.last()) {
				cognitiveOne.setUserId(result.getString(1));
				cognitiveOne.setFormalEducationYears(result.getDouble(3));
				cognitiveOne.setNonFormalEducationYears(result.getDouble(4));
				cognitiveOne.setManager(result.getInt(5));
				cognitiveOne.setManagerSimulYears(result.getInt(6));
				cognitiveOne.setProfessional(result.getInt(7));
				cognitiveOne.setProfessionalSimulYears(result.getInt(8));
				cognitiveOne.setTechnician(result.getInt(9));
				cognitiveOne.setTechnicianSimulYears(result.getInt(10));
				cognitiveOne.setClerical(result.getInt(11));
				cognitiveOne.setClericalSimulYears(result.getInt(12));
				cognitiveOne.setService(result.getInt(13));
				cognitiveOne.setServiceSimulYears(result.getInt(14));
				cognitiveOne.setAgriculture(result.getInt(15));
				cognitiveOne.setAgricultureSimulYears(result.getInt(16));
				cognitiveOne.setCraft(result.getInt(17));
				cognitiveOne.setCraftSimulYears(result.getInt(18));
				cognitiveOne.setPlant(result.getInt(19));
				cognitiveOne.setPlantSimulYears(result.getInt(20));
				cognitiveOne.setElementary(result.getInt(21));
				cognitiveOne.setElementarySimulYears(result.getInt(22));
				
				conn.close();
				return true;
				
			}
			
			conn.close();
			return false;
			}
		catch (SQLException e) {
			user = null;
			
			
		return false;
		}
			
	}
	
	@Override
	public Boolean updateCognitiveTwo(CognitiveTwoInfo cognitiveTwo) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		
		if (createCognitiveTwo(cognitiveTwo)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return false;
		}
	}

	private boolean createCognitiveTwo(CognitiveTwoInfo cognitiveTwo) {
		String patient_id = cognitiveTwo.getUserId();
		int reading_years = cognitiveTwo.getReading_years();
		int household_years = cognitiveTwo.getHousehold_years();
		int driving_years = cognitiveTwo.getDriving_years();
		int leisure_years = cognitiveTwo.getLeisure_years();
		int technology_years = cognitiveTwo.getTechnology_years();
		int social_years = cognitiveTwo.getSocial_years();
		int cinema_years = cognitiveTwo.getCinema_years();
		int gardening_years = cognitiveTwo.getGardening_years();
		int children_years = cognitiveTwo.getChildren_years();
		int volunteering_years = cognitiveTwo.getVolunteering_years();
		int artistic_years = cognitiveTwo.getArtistic_years();
		int exhibitions_years = cognitiveTwo.getExhibitions_years();
		int holidays_years = cognitiveTwo.getHolidays_years();
		int books_years = cognitiveTwo.getBooks_years();
		int number_children = cognitiveTwo.getNumber_children();
		int pets_years = cognitiveTwo.getPets_years();
		int bank_account_years = cognitiveTwo.getBank_account_years();
		
		String reading = cognitiveTwo.getReading();
		String household = cognitiveTwo.getHousehold();
		String driving = cognitiveTwo.getDriving();
		String leisure = cognitiveTwo.getLeisure();
		String technology = cognitiveTwo.getTechnology();
		String social = cognitiveTwo.getSocial();
		String cinema = cognitiveTwo.getCinema();
		String gardening = cognitiveTwo.getGardening();
		String children = cognitiveTwo.getChildren();
		String volunteering = cognitiveTwo.getVolunteering();
		String artistic = cognitiveTwo.getArtistic();
		String exhibitions = cognitiveTwo.getExhibitions();
		String holidays = cognitiveTwo.getHolidays();
		String books = cognitiveTwo.getBooks();
		String raised_children = cognitiveTwo.getRaised_children();
		String pets = cognitiveTwo.getPets();
		String bank_account = cognitiveTwo.getBank_account();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time); 
		
		String insert = "insert  into cognitive_two_info (patient_id,  reading_years, household_years, driving_years , leisure_years,"
				+ "technology_years,social_years,cinema_years,gardening_years,children_years,volunteering_years ,"
				+ "artistic_years,  exhibitions_years, holidays_years,books_years, number_children, pets_years, bank_account_years,"
				+ "reading,household, driving,leisure,technology,social,cinema,gardening,children,"
				+ "volunteering, artistic, exhibitions,holidays, books, raised_children, pets,bank_account, timestamp)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
		try {
			PreparedStatement updateCognitiveTwoInfo = (PreparedStatement) conn.prepareStatement(insert);
			updateCognitiveTwoInfo.setString(1, patient_id);
			updateCognitiveTwoInfo.setInt(2,  reading_years);
			updateCognitiveTwoInfo.setInt(3,  household_years);
			updateCognitiveTwoInfo.setInt(4,  driving_years);
			updateCognitiveTwoInfo.setInt(5, leisure_years);
			updateCognitiveTwoInfo.setInt(6, technology_years);
			updateCognitiveTwoInfo.setInt(7, social_years);
			updateCognitiveTwoInfo.setInt(8, cinema_years);
			updateCognitiveTwoInfo.setInt(9, gardening_years);
			updateCognitiveTwoInfo.setInt(10, children_years);
			updateCognitiveTwoInfo.setInt(11, volunteering_years);
			updateCognitiveTwoInfo.setInt(12,  artistic_years);
			updateCognitiveTwoInfo.setInt(13, exhibitions_years);			
			updateCognitiveTwoInfo.setInt(14,  holidays_years);
			updateCognitiveTwoInfo.setInt(15, books_years);
			updateCognitiveTwoInfo.setInt(16, number_children);
			updateCognitiveTwoInfo.setInt(17, pets_years);
			updateCognitiveTwoInfo.setInt(18, bank_account_years);
			updateCognitiveTwoInfo.setString(19, reading);
			updateCognitiveTwoInfo.setString(20, household);
			updateCognitiveTwoInfo.setString(21, driving);
			updateCognitiveTwoInfo.setString(22, leisure);
			updateCognitiveTwoInfo.setString(23, technology);
			updateCognitiveTwoInfo.setString(24, social);
			updateCognitiveTwoInfo.setString(25, cinema);
			updateCognitiveTwoInfo.setString(26, gardening);
			updateCognitiveTwoInfo.setString(27, children);
			updateCognitiveTwoInfo.setString(28, volunteering);
			updateCognitiveTwoInfo.setString(29, artistic);
			updateCognitiveTwoInfo.setString(30, exhibitions);
			updateCognitiveTwoInfo.setString(31, holidays);
			updateCognitiveTwoInfo.setString(32, books);
			updateCognitiveTwoInfo.setString(33, raised_children);
			updateCognitiveTwoInfo.setString(34, pets);
			updateCognitiveTwoInfo.setString(35, bank_account);
			updateCognitiveTwoInfo.setTimestamp(36, timestamp);
			
			updateCognitiveTwoInfo.executeUpdate();
			
		
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false; 
	}

	public CognitiveTwoInfo queryCognitiveTwo(User user) {
		//open database connection
		cognitiveTwo  = new CognitiveTwoInfo();
		initDBConnection();

		if (getCognitiveTwoData(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return  cognitiveTwo;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return cognitiveTwo;
		}
	}
	
	
	private boolean getCognitiveTwoData(User user) {

		
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery("SELECT * FROM cognitive_two_info where patient_id = " + idUser);			
			while (result.last()) {
				cognitiveTwo.setUserId(result.getString(1));
				cognitiveTwo.setReading_years(result.getInt(2));				
				cognitiveTwo.setHolidays_years(result.getInt(3));
				cognitiveTwo.setDriving_years(result.getInt(4));
				cognitiveTwo.setLeisure_years(result.getInt(5));
				cognitiveTwo.setTechnology_years(result.getInt(6));
				cognitiveTwo.setSocial_years(result.getInt(7));
				cognitiveTwo.setCinema_years(result.getInt(8));
				cognitiveTwo.setGardening_years(result.getInt(9));
				cognitiveTwo.setChildren_years(result.getInt(10));
				cognitiveTwo.setVolunteering_years(result.getInt(11));
				cognitiveTwo.setArtistic_years(result.getInt(12));
				cognitiveTwo.setExhibitions_years(result.getInt(13));
				cognitiveTwo.setHolidays_years(result.getInt(14));
				cognitiveTwo.setBooks_years(result.getInt(15));
				cognitiveTwo.setNumber_children(result.getInt(16));
				cognitiveTwo.setPets_years(result.getInt(17));
				cognitiveTwo.setBank_account_years(result.getInt(18));
				cognitiveTwo.setReading(result.getString(19));
				cognitiveTwo.setHousehold(result.getString(20));
				cognitiveTwo.setDriving(result.getString(21));
				cognitiveTwo.setLeisure(result.getString(22));
				cognitiveTwo.setTechnology(result.getString(23));
				cognitiveTwo.setSocial(result.getString(24));
				cognitiveTwo.setCinema(result.getString(25));
				cognitiveTwo.setGardening(result.getString(26));
				cognitiveTwo.setChildren(result.getString(27));
				cognitiveTwo.setVolunteering(result.getString(28));
				cognitiveTwo.setArtistic(result.getString(29));
				cognitiveTwo.setExhibitions(result.getString(30));
				cognitiveTwo.setHolidays(result.getString(31));
				cognitiveTwo.setBooks(result.getString(32));
				cognitiveTwo.setRaised_children(result.getString(33));
				cognitiveTwo.setPets(result.getString(34));
				cognitiveTwo.setBank_account(result.getString(35));
				
				conn.close();
				return true;
				
			}
			
			conn.close();
			return false;
			}
		catch (SQLException e) {
			user = null;
			
			
		return false;
		}
			
	}
	

	
	@Override
	public Boolean updateSmokeAlcohol(SmokeAlcoholInfo smokeAlco) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		
		if (createSmokeAlco(smokeAlco)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return false;
		}
	}

	
	private boolean createSmokeAlco(SmokeAlcoholInfo info){
		String patient_id = info.getUserId();
		String smoker_type = info.getSmoker_type();
		int current_year_start = info.getCurrent_year_start();
		int current_num_smoke = info.getCurrent_num_smoke();
		int former_year_start = info.getFormer_year_start();
		int former_year_stop = info.getFormer_year_stop();
		int former_num_smoke = info.getFormer_num_smoke();
		String drinks_freq = info.getDrinks_freq();
		String num_drinks = info.getNum_drinks();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time); 

		String insert = "insert  into smoking_alcohol_info (patient_id, timestamp,smoker_type, current_year_start, current_num_smoke,"

				+ "former_year_start, former_year_stop, former_num_smoke, drinks_freq, num_drinks)"

				+ " values(?,?,?,?,?,?,?,?,?,?)";

			

		try {
			PreparedStatement updateSmokeAl = (PreparedStatement) conn.prepareStatement(insert);
			updateSmokeAl.setString(1, patient_id);
			updateSmokeAl.setTimestamp(2, timestamp);
			updateSmokeAl.setString(3,smoker_type);
			updateSmokeAl.setInt(4,current_year_start);
			updateSmokeAl.setInt(5, current_num_smoke);
			updateSmokeAl.setInt(6, former_year_start);
			updateSmokeAl.setInt(7, former_year_stop);
			updateSmokeAl.setInt(8, former_num_smoke);
			updateSmokeAl.setString(9, drinks_freq);
			updateSmokeAl.setString(10, num_drinks);

			updateSmokeAl.executeUpdate();
			return true;
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}		
	}
	
	
	public SmokeAlcoholInfo querySmokeAlcohol(User user) {
		//open database connection
		smokeAlcohol = new SmokeAlcoholInfo();
		initDBConnection();

		if (getSmokeAlcoholData(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return  smokeAlcohol;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return smokeAlcohol;
		}
	}
	
	
	private boolean getSmokeAlcoholData(User user) {
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM smoking_alcohol_info where patient_id = " + idUser);
			result = pstmt.executeQuery("SELECT * FROM smoking_alcohol_info where patient_id = " + idUser);
			
			while (result.last()) {
				smokeAlcohol.setUserId(result.getString(1));
				smokeAlcohol.setSmoker_type(result.getString(3));
				smokeAlcohol.setCurrent_year_start(result.getInt(4));
				smokeAlcohol.setCurrent_num_smoke(result.getInt(5));
				smokeAlcohol.setFormer_year_start(result.getInt(6));
				smokeAlcohol.setFormer_year_stop(result.getInt(7));
				smokeAlcohol.setFormer_num_smoke(result.getInt(8));
				smokeAlcohol.setDrinks_freq(result.getString(9));
				smokeAlcohol.setNum_drinks(result.getString(10));
				conn.close();
				return true;
				
			}
			
			conn.close();
			return false;
			}
		catch (SQLException e) {
			user = null;
			
			//return user;
		return false;
		}
			
	}
	@Override
	public Boolean updateDiet(DietInfo diet) throws IllegalArgumentException {
		//open database connection
		initDBConnection();
		
		if (createDiet(diet)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return false;
		}
	}
	
	private boolean createDiet(DietInfo diet) {
		
		String patient_id = diet.getUserId();
		
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time); 
		int culinary_fat = diet.getCulinaryFat();
		int oil_consume = diet.getRapeSeedOil();
		int vegetable_servings = diet.getVegetableServings();
		int fruit_units = diet.getFruit();
		int red_meat = diet.getRedMeat();
		int butter = diet.getButter();
		int carbonated_beverages = diet.getBeverages();
		int wine_week = diet.getWine();
		int legumes_week = diet.getLegumes();
		int fish_week = diet.getFish();
		int nuts_week = diet.getNuts();
		int prefer_chicken = diet.getChicken();
		int sauce_week = diet.getSauce();
		int sweets_week = diet.getSweets();
		String insert = "insert  into diet_info (patient_id, timestamp,culinary_fat,oil_consume, vegetable_servings,fruit_units, red_meat, " +


				 "butter, carbonated_beverages,wine_week, legumes_week,fish_week, sweets_week,nuts_week,prefer_chicken,sauce_week)"

				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			

		try {
			PreparedStatement updateDiet = (PreparedStatement) conn.prepareStatement(insert);
			updateDiet.setString(1, patient_id);
			updateDiet.setTimestamp(2, timestamp);
			updateDiet.setInt(3,culinary_fat);
			updateDiet.setInt(4,oil_consume);
			updateDiet.setInt(5, vegetable_servings);
			updateDiet.setInt(6, fruit_units);
			updateDiet.setInt(7, red_meat);
			updateDiet.setInt(8, butter);
			updateDiet.setInt(9, carbonated_beverages);
			updateDiet.setInt(10, wine_week);
			updateDiet.setInt(11, legumes_week);
			updateDiet.setInt(12, fish_week);
			updateDiet.setInt(13,sweets_week);
			updateDiet.setInt(14, nuts_week);
			updateDiet.setInt(15, prefer_chicken);
			updateDiet.setInt(16, sauce_week);			

			updateDiet.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}				
	}

	public DietInfo queryDiet(User user) {
		
		diet  = new DietInfo();
		//open database connection
		initDBConnection();

		if (getDietData(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return  diet;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return diet;
		}
	}
	
	
	private boolean getDietData(User user) {

		
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery("SELECT * FROM diet_info where patient_id = " + idUser);			
			while (result.last()) {
				diet.setUserId(result.getString(1));
				diet.setCulinaryFat(result.getInt(3));
				diet.setRapeSeedOil(result.getInt(4));
				diet.setVegetableServings(result.getInt(5));
				diet.setFruit(result.getInt(6));
				diet.setRedMeat(result.getInt(7));
				diet.setButter(result.getInt(8));
				diet.setBeverages(result.getInt(9));
				diet.setWine(result.getInt(10));
				diet.setLegumes(result.getInt(11));
				diet.setFish(result.getInt(12));
				diet.setSweets(result.getInt(13));
				diet.setNuts(result.getInt(14));
				diet.setChicken(result.getInt(15));
				diet.setSauce(result.getInt(16));
				conn.close();
				return true;
				
			}
			
			conn.close();
			return false;
			}
		catch (SQLException e) {
			user = null;
			
			
		return false;
		}
			
	}
	public RiskFactorScore getLibraScore(User user) {
		//open database connection
		RiskFactorScore score = new RiskFactorScore();
		CalculateScore calcScore = new CalculateScore();
		calcScore.calcScore(score, user);
		return  score;
		
	}
	
	
	/*private boolean getScore(User user) {
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM patient_info where patient_id = " + idUser);
			result = pstmt.executeQuery("SELECT * FROM patient_info where patient_id = " + idUser);
			
			while (result.last()) {
				patient.setUserId(result.getString(1));
				patient.setCountryOfBirth(result.getString(5));
				patient.setAge(result.getInt(3));
				patient.setGender(result.getString(4));
				patient.setMaritalStatus(result.getString(6));
				patient.setLivingArrangements(result.getString(7));
				patient.setOccupationalGroup(result.getString(8));
				patient.setEmploymentStatus(result.getString(9));
				
				patient.setEducationLevel(result.getString(10));
			
				conn.close();
				return true;
				
			}
			
			conn.close();
			return false;
			}
		catch (SQLException e) {
			user = null;
			
			//return user;
		return false;
		}
			
	}
	*/
	public void initDBConnection() {		
		// use Google driver for mysql when running in production mode
		
		  try {
		      if (SystemProperty.environment.value() ==
		          SystemProperty.Environment.Value.Production) {
		        // Load the class that provides the new "jdbc:google:mysql://" prefix.
		        Class.forName("com.mysql.jdbc.GoogleDriver");
		        String url = "jdbc:google:mysql://inmindd-v3:inmindd-db/inmindd?user=root";
		        conn = DriverManager.getConnection(url);
		      } else {  //running application locally in development mode 
		    	String url = "jdbc:mysql://173.194.249.69:3306/";
		  		String dbName = "inmindd";
		  		String driver = "com.mysql.jdbc.Driver";
		  		String userName = "root";
		  		String password = "noknoknok";
		  		try {
		  			Class.forName(driver).newInstance();
		  			conn = DriverManager.getConnection(url+dbName,userName,password);

		  		} catch (Exception e) {
		  			e.printStackTrace();
		  		} 
		  		
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		      return;
		    }

	}

	public PhysicalActivityInfo queryPhysicalActivity(User user) {
		//open database connection
		physical = new PhysicalActivityInfo();
		initDBConnection();

		if (getPhysicalActivityInfo(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return  physical;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return physical;
		}
	}
	
	
	private boolean getPhysicalActivityInfo(User user) {
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM physical_activities_info where patient_id = " + idUser);
			result = pstmt.executeQuery("SELECT * FROM physical_activities_info where patient_id = " + idUser);
			
			while (result.last()) {
				physical.setUserId(result.getString(1));
				physical.setDiyHours(result.getInt(3));
				physical.setSummerWalkingHours(result.getInt(4));
				physical.setWinterWalkingHours(result.getInt(5));
				physical.setSummerCyclingHours(result.getInt(6));
				physical.setWinterCyclingHours(result.getInt(7));
				physical.setSummerGardenHours(result.getInt(8));
				physical.setWinterGardenHours(result.getInt(9));
				physical.setSummerPhysicalHours(result.getInt(10));
				physical.setWinterPhysicalHours(result.getInt(11));
				physical.setSummerHouseworkHours(result.getInt(12));
				physical.setWinterHouseworkHours(result.getInt(13));
				physical.setFlightStairs(result.getInt(14));
				
				physical.setPhysicalWork(result.getString(15));
				physical.setVigorous(result.getString(16));
				conn.close();
				return true;
				
			}
			
			conn.close();
			return false;
			}
		catch (SQLException e) {
			user = null;
			
			//return user;
		return false;
		}
			
	}

	@Override
	public User getUserConnected() throws IllegalArgumentException {
		return (User)getThreadLocalRequest().getSession().getAttribute("current_user");
	}

	@Override
	public Boolean updateSupportGoalUser(SupportGoalUser goal) throws IllegalArgumentException {
		//open database connection
		initDBConnection();

		if (createSupportGoalUser(goal)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}


	private boolean createSupportGoalUser(SupportGoalUser goal) {
		String patient_id = goal.getId_user();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  

		String insert = "INSERT INTO `support_goals_users` (`id_goal`, `id_user`, `timestamp`, `comment`) VALUES (?, ?, ?, ?);";

		try {
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(insert);
			statement.setInt(1, goal.getId_goal());
			statement.setString(2, patient_id);
			statement.setTimestamp(3,timestamp);
			statement.setString(4, goal.getComment());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

		return true;	

	}

	public SupportGoalUser querySupportGoalUser(User user) {
		//open database connection
		goal = new SupportGoalUser();
		initDBConnection();

		if (getSupportGoalUser(user)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return goal;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return goal;
		}
	}

	private boolean getSupportGoalUser(User user) {
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM `support_goals_users` WHERE `id_user` = " + idUser);
			result = pstmt.executeQuery("SELECT * FROM `support_goals_users` WHERE `id_user` = " + idUser);

			while (result.last()) {
				goal.setId_goal(result.getInt(1));
				goal.setId_user(result.getString(2));
				goal.setTimestamp(result.getTimestamp(3).toString());
				goal.setComment(result.getString(4));
				conn.close();
				return true;
			}
			conn.close();
			return false;
		}
		catch (SQLException e) {
			user = null;
			//return user;
			return false;
		}

	}

	@Override
	public ArrayList<SupportGoal> querySupportGoals(int riskFactor)
			throws IllegalArgumentException {
		//open database connection
		ArrayList<SupportGoal> goals = new ArrayList<SupportGoal>();
		initDBConnection();

		if (getSupportGoals(riskFactor, goals)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return goals;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return goals;
		}
	}


	private boolean getSupportGoals(int riskFactor, ArrayList<SupportGoal> goalsList) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM `support_goals` WHERE `id_riskfactor` = "+riskFactor+";");
			result = pstmt.executeQuery("SELECT * FROM `support_goals`;");

			while (result.next()) {
				SupportGoal data = new SupportGoal(result.getInt("id"), result.getInt("goal_nb"), result.getString("goal_name"), result.getString("description"), result.getString("image_url")); 
				goalsList.add(data);
				return true;
			}
			conn.close();
			return (goalsList.size() > 0);
		}
		catch (SQLException e) {
			user = null;
			return false;
		}
	}




}
