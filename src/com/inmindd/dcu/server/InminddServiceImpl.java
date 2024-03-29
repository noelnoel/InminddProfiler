package com.inmindd.dcu.server;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inmindd.dcu.client.InminddService;
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
import com.inmindd.dcu.shared.SupportApps;
import com.inmindd.dcu.shared.SupportExperts;
import com.inmindd.dcu.shared.SupportFAQ;
import com.inmindd.dcu.shared.SupportGoal;
import com.inmindd.dcu.shared.SupportGoalUser;
import com.inmindd.dcu.shared.SupportRiskFactorInfos;
import com.inmindd.dcu.shared.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/*mail purpose*/
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;






import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */

@SuppressWarnings("serial")
public class InminddServiceImpl extends RemoteServiceServlet implements InminddService {
	private User user;	
	//private final static byte[] GWT_DES_KEY = new byte[] { -110, 121, -65, 22, -60, 61, -22, -60, 21, -122, 41, -89, -89, -68, -8, 41, -119, -51, -12, -36, 19, -8, -17, 47 };

	
	// autherisation key for Glasgow randomisation wev service
	
	//private final static String AUTH_KEY = "2E5E03C0-F32E-4934-AF92-D5BEA12C195E";
	//private String decryptedPassword;
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
	public String randGroup;
	
	/***************************************
	 *  Login Methods
	 ***************************************/
	@Override
	public User authenticateUser(String idUser, String password) throws IllegalArgumentException {	
		//open database connection
		int i = 1;
		initDBConnection();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM inmindd.user where userId = ?;");
			pstmt.setString(1, idUser);
			result = pstmt.executeQuery();
			
			while (result.next()) {
				if (result.getString(2).equals(password)) {
					//System.out.println(result.getString(4));
					user = new User();
					user.setUserId(result.getString(1));
					
					
					if(result.getString("randomised_group") != null && result.getString("randomised_group").equals("Intervention"))
					{
						//we authorize login for Intervention group
						getThreadLocalRequest().getSession().setAttribute("current_user", user);
						 updateUserLastLogin(idUser);
							
					} 
					else if(result.getString("randomised_group") != null && result.getString("randomised_group").equals("Control"))
					{
						Date d = getDateRandomised(idUser);
						if(d==null)
						{
							if((result.getInt("controlGroupAuthorized") == 1))
							{
								getThreadLocalRequest().getSession().setAttribute("current_user", user);
								updateUserLastLogin(idUser);
							}
						}
						else
						{
							int monthsSinceReg = getMonthsSinceRandomisation(d);
							
							if((monthsSinceReg>=5) )
							{
								getThreadLocalRequest().getSession().setAttribute("current_user", user);
								updateUserLastLogin(idUser);
							}
							else if((result.getInt("controlGroupAuthorized") == 1))
							{
								getThreadLocalRequest().getSession().setAttribute("current_user", user);
								updateUserLastLogin(idUser);
							}
						}
						
					}
					conn.close();
					return user;
						
				}
			}
			
			//Only executes if there is no user with that id 
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
	
	private static int getMonthsSinceRandomisation(Date registrationDate)
	{
		if(registrationDate==null)
		{
			return 0;
		}
		
		Date today = new Date();
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(registrationDate);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(today);
		
		int differenceinYears = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int differenceInMonths = (differenceinYears*12)+(endCalendar.get(Calendar.MONTH)-startCalendar.get(Calendar.MONTH));
		return differenceInMonths;
	}
	
	@Override
	public Boolean duplicateUser(String id) throws IllegalArgumentException {	
		//open database connection
		initDBConnection();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = conn.prepareStatement("SELECT * FROM user where userId = ?;");
			pstmt.setString(1, id);
			result = pstmt.executeQuery();
			
			while (result.next()) {
				if (result.getString(1).equals(id)) {
					
					return true;  // already exists
				}
				else {
					return false;
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} 

		return false;
	}

	@Override
	public User authenticateUserSupportEnvironement(String idUser, String password) throws IllegalArgumentException {	
		//open database connection
		initDBConnection();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	    
			pstmt = conn.prepareStatement("SELECT * FROM user where userId = ? AND passwordhash = ?;");
			pstmt.setString(1, idUser);
			pstmt.setString(2, password);
			result = pstmt.executeQuery();
			
			while (result.next()) {
					user = new User();
					user.setUserId(result.getString(1));
					if(result.getString("randomised_group") != null && result.getString("randomised_group").equals("Intervention")){
						//we authorize login for Intervention group
						getThreadLocalRequest().getSession().setAttribute("current_user", user);
						 updateUserLastLogin(idUser);
					} 
					else if(result.getString("randomised_group") != null && result.getString("randomised_group").equals("Control"))
					{
						int monthsSinceRandomise = getMonthsSinceRandomisation(getDateRandomised(user.getUserId()));
						if(monthsSinceRandomise>=5)
						{
							getThreadLocalRequest().getSession().setAttribute("current_user", user);
							updateUserLastLogin(idUser);
						}
					}
					else if(result.getInt("controlGroupAuthorized") == 1) {
						//we authorize login for Control group if they have been authorized by the researchers after their second visit 6 months after
						getThreadLocalRequest().getSession().setAttribute("current_user", user);
						 updateUserLastLogin(idUser);
					} else if(result.getInt("controlGroupAuthorized")== 0){
						user = null;
						getThreadLocalRequest().getSession().setAttribute("current_user", null);
						throw new IllegalArgumentException("You have to wait up to 6 months for entering the support environment.");
					} else if(result.getString("randomised_group") == null){
						//edge case where a user has not been randomised but still tries to access the support environment
						user = null;
						getThreadLocalRequest().getSession().setAttribute("current_user", null);
						throw new IllegalArgumentException("User not yet randomised");
					}	
					conn.close();
					return user;
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
	public Boolean resetPassword(User user) throws IllegalArgumentException {	
		//open database connection
		initDBConnection();
		String id = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM user where userId = ?;");
			pstmt.setString(1, id);
			result = pstmt.executeQuery();
			
			while (result.next()) {
				if (result.getString(1).equals(id)) {
					String hashMaidenName = result.getString(8);
					String hashFavColour = result.getString(9);
					if (hashMaidenName.equals(user.getMaidenName()) && hashFavColour.equals(user.getFavoriteColour())) {  // maiden name and colour correct; reset password
						updatePassword(user);
						return true;  // password reset
					}
					return false;
				}
				else {
					return false;
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} 

		return false;
	}

	
	private Boolean updatePassword(User user ) {

		//open database connection
		initDBConnection();
		// Verify that the input is valid. 
		String idUser = user.getUserId();
		try {	          

			String passhash = user.getPassword();
			// create the java mysql update preparedstatement
			String query = "update ignore user set passwordhash =  ? where userId =" + idUser;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, passhash);

			// execute the java preparedstatement
			preparedStmt.executeUpdate();
		}
		catch (SQLException e) {
			user = null;
			//return user;
			return false;
		}
		return true;
	}

	/******************************
	 * Profiler Related Methods
	 ******************************/
	@Override
	public Boolean registerUser(User user) throws IllegalArgumentException {
			String userId = user.getUserId();
			if (duplicateUser(userId))  
			{
				return false;// this user already in database
			}
				
			//open database connection
			initDBConnection();
			// Verify that the input is valid. 
			String passwordHash = user.getPassword();
			String maidenNameHash = user.getMaidenName();
			String favColourHash = user.getFavoriteColour();
			long time = System.currentTimeMillis();
			
			java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  
			String insert = "insert  into user (userId,passwordhash,timestamp, maiden_name_hash, favorite_colour_hash)  values(?,?,?,?,?)";			
			
			
			try {
				PreparedStatement updateUserInfo = conn.prepareStatement(insert);
				updateUserInfo.setString(1, userId);				
				updateUserInfo.setString(2, passwordHash);	
				updateUserInfo.setTimestamp(3, timestamp);
				updateUserInfo.setString(4, maidenNameHash);
				updateUserInfo.setString(5, favColourHash);
				
				updateUserInfo.executeUpdate();
				conn.close();
			} catch (SQLException e) {
	
				return false;
			}
	
			return true;
		
	}


	private Boolean updateUser(User user , String randNo, String randTreatmentGroup) {

		//open database connection
		initDBConnection();
		// Verify that the input is valid. 
		String idUser = user.getUserId();
		try {	          

			int randInt = Integer.parseInt(randNo);
			// create the java mysql update preparedstatement
			String query = "update ignore user set randomised_group =  ? where userId =" + idUser;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString   (1, randTreatmentGroup);

			// execute the java preparedstatement
			preparedStmt.executeUpdate();

			query = "update ignore user set randomised_number =  ? where userId =" + idUser;
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, randInt);
			preparedStmt.executeUpdate();

		}
		catch (SQLException e) {
			user = null;
			//return user;
			return false;
		}
		return true;
	}

		
	public Boolean setRandomiseUserStatus(User user) {

		//open database connection
		initDBConnection();
		// Verify that the input is valid. 
		String idUser = user.getUserId();
		int randomiserStatus = 1;  //ready for randomisation status
		ResultSet result = null;
		try {	  
			
		
			// dummy test user. Don't send to Glasgow for randomisation.
			if (idUser.endsWith("00000"))  { 
				randomiserStatus = 2;
				String query = "update ignore user set randomised =  ? where userId =" + idUser;;
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, randomiserStatus);
				preparedStmt.executeUpdate();
				
				query = "update ignore user set randomised_group =  ? where userId =" + idUser;;
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, "Intervention");
				preparedStmt.executeUpdate();
				
				query = "update ignore user set randomised_number =  ? where userId =" + idUser;;
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, 0);
				preparedStmt.executeUpdate();
				return true;
				
			}	
			// check not already randomised		        
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user where userId = ?;");
				pstmt.setString(1, idUser);
				result = pstmt.executeQuery();			
				while (result.next()) {
					if (result.getInt(5) == 2) {					
						conn.close();
						return true;
					}
				}
				
			// create the java mysql update preparedstatement
			String query = "update ignore user set randomised =  ? where userId =" + idUser;;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, randomiserStatus);
			preparedStmt.executeUpdate();
		}
		catch (SQLException e) {
			user = null;
			//return user;
			return false;
		}
			
		return true;
	}
	
	
	public String getRandomisedGroup(User user) {

		//open database connection
		initDBConnection();
		randGroup = null;
		String idUser = user.getUserId();		
		
		ResultSet result = null;
		try {	   			
			// check randomiser status		        
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user where userId = ?;");
				pstmt.setString(1, idUser);
				try {
					Thread.sleep(10000);
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				result = pstmt.executeQuery();			
				while (result.next()) {
					randGroup = result.getString(6);
					if (result.getInt(5) == 2) {	
						conn.close();
						return randGroup;
					}
				}			
		}
		catch (SQLException e) {
			user = null;	
			return randGroup;
		}	
		return randGroup;
	}
	
	
	@Override
	public Boolean updatePatientInfo(Patient patient) throws IllegalArgumentException 
	{
		//open database connection
		initDBConnection();
		
		if (createPatientInfo(patient)) 
		{
			try 
			{
				conn.close();
				return true;
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else 
		{
			try 
			{
				conn.close();
				return false;
			}
			catch (SQLException e) 
			{
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

			while (result.last())
			{
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
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;		
		}
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
		
		double diyHours = activity.getDiyHours();
		double summerWalkingHours = activity.getSummerWalkingHours();
		double winterWalkingHours = activity.getWinterWalkingHours();
		double summerCyclingHours = activity.getSummerCyclingHours();
		double winterCyclingHours = activity.getWinterCyclingHours();
		double summerGardenHours = activity.getSummerGardenHours();
		double winterGardenHours = activity.getWinterGardenHours();
		double summerPhysicalHours = activity.getSummerPhysicalHours();
		double winterPhysicalHours = activity.getWinterPhysicalHours();
		double summerHouseworkHours = activity.getSummerHouseworkHours();
		double winterHouseworkHours = activity.getWinterHouseworkHours();
		double vigorousHours = activity.getVigorousHours();
		double flightStairs = activity.getFlightStairs();
		String workActivity = activity.getPhysicalWork();
		String vigorous = activity.getVigorous();
		String insert = "insert  into physical_activities_info(patient_id,timestamp,diy_hours,  summer_walking_hours, winter_walking_hours, summer_cycling_hours, winter_cycling_hours, summer_garden_hours, winter_garden_hours,"
				+ "summer_physical_hours, winter_physical_hours,summer_housework_hours, winter_housework_hours, flight_stairs, vigorous_hours, physical_activity_work, vigorous)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement updateActivitiesInfo = (PreparedStatement) conn.prepareStatement(insert);
			updateActivitiesInfo.setString(1, patient_id);
			updateActivitiesInfo.setTimestamp(2,timestamp);
			updateActivitiesInfo.setDouble(3, diyHours);
			updateActivitiesInfo.setDouble(4, summerWalkingHours);
			updateActivitiesInfo.setDouble(5, winterWalkingHours);
			updateActivitiesInfo.setDouble(6, summerCyclingHours);
			updateActivitiesInfo.setDouble(7, winterCyclingHours);
			updateActivitiesInfo.setDouble(8, summerGardenHours);
			updateActivitiesInfo.setDouble(9, winterGardenHours);
			updateActivitiesInfo.setDouble(10, summerPhysicalHours);			
			updateActivitiesInfo.setDouble(11, winterPhysicalHours);
			updateActivitiesInfo.setDouble(12, summerHouseworkHours);			
			updateActivitiesInfo.setDouble(13, winterHouseworkHours);
			updateActivitiesInfo.setDouble(14, flightStairs);	
			updateActivitiesInfo.setDouble(15, vigorousHours);
			
			updateActivitiesInfo.setString(16, workActivity);
			updateActivitiesInfo.setString(17, vigorous);
			
			updateActivitiesInfo.executeUpdate();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;		
		}
		return true;	
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
				physical.setDiyHours(result.getDouble(3));
				physical.setSummerWalkingHours(result.getDouble(4));
				physical.setWinterWalkingHours(result.getDouble(5));
				physical.setSummerCyclingHours(result.getDouble(6));
				physical.setWinterCyclingHours(result.getDouble(7));
				physical.setSummerGardenHours(result.getDouble(8));
				physical.setWinterGardenHours(result.getDouble(9));
				physical.setSummerPhysicalHours(result.getDouble(10));
				physical.setWinterPhysicalHours(result.getDouble(11));
				physical.setSummerHouseworkHours(result.getDouble(12));
				physical.setWinterHouseworkHours(result.getDouble(13));
				physical.setFlightStairs(result.getDouble(14));
				physical.setVigorousHours(result.getDouble(15));
				physical.setPhysicalWork(result.getString(16));
				physical.setVigorous(result.getString(17));
				conn.close();
				return true;
				
			}
			
			conn.close();
			return false;
		}
		catch (SQLException e) 
		{
			user = null;
			return false;
		}	
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
		String insert = "insert  into cognitive_one_info (patient_id, timestamp,formal_education_years,non_formal_education_years, manager, manager_simul_years,"
				+ "professional, professional_simul_years, technician, technician_simul_years, clerical, clerical_simul_years,"
				+ "service, service_simul_years, agriculture, agriculture_simul_years, "
				+ "craft, craft_simul_years, plant, plant_simul_years, elementary, elementary_simul_years)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
		try {
			PreparedStatement updateCognitiveOneInfo = (PreparedStatement) conn.prepareStatement(insert);
			updateCognitiveOneInfo.setString(1, patient_id);
			updateCognitiveOneInfo.setTimestamp(2, timestamp);
			updateCognitiveOneInfo.setDouble(3,  formal_education_years);
			updateCognitiveOneInfo.setDouble(4, non_formal_education_years);
			updateCognitiveOneInfo.setInt(5, manager);
			updateCognitiveOneInfo.setInt(6, manager_simul_years);
			updateCognitiveOneInfo.setInt(7, professional);
			updateCognitiveOneInfo.setInt(8, professional_simul_years);
			updateCognitiveOneInfo.setInt(9, technician);
			updateCognitiveOneInfo.setInt(10, technician_simul_years);
			updateCognitiveOneInfo.setInt(11, clerical);
			updateCognitiveOneInfo.setInt(12, clerical_simul_years);
			updateCognitiveOneInfo.setInt(13, service);
			updateCognitiveOneInfo.setInt(14, service_simul_years);			
			updateCognitiveOneInfo.setInt(15, agriculture);
			updateCognitiveOneInfo.setInt(16, agriculture_simul_years);
			updateCognitiveOneInfo.setInt(17, craft);
			updateCognitiveOneInfo.setInt(18, craft_simul_years);
			updateCognitiveOneInfo.setInt(19, plant);
			updateCognitiveOneInfo.setInt(20, plant_simul_years);
			updateCognitiveOneInfo.setInt(21, elementary);
			updateCognitiveOneInfo.setInt(22, elementary_simul_years);
			
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
		catch (SQLException e) 
		{
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
				cognitiveTwo.setHousehold_years(result.getInt(3));
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
		catch (SQLException e) 
		{
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
	
	public RiskFactorScore getLibraScore(User user) 
	{
		//open database connection
		initDBConnection();
		RiskFactorScore score = new RiskFactorScore();
		CalculateScore calcScore = new CalculateScore();
		calcScore.calcScore(score, user, conn);
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
		
		  try 
		  {
		     if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) 
		      {
		    	  // Load the class that provides the new "jdbc:google:mysql://" prefix.
			      Class.forName("com.mysql.jdbc.GoogleDriver");
			      
			      //Live
			      String url = "jdbc:google:mysql://inmindd-v3:inmindd-db/inmindd?user=root";
				  
			      //Test   
			      //String url = "jdbc:google:mysql://inmindd-v3:staging/inmindd?user=root";
			      
			      conn = DriverManager.getConnection(url);
			      Statement db = conn.createStatement();
			      db.execute("use inmindd;");
		      } 
		      else 
		      {  
		    	  //running application locally in development mode 
		    	  //Live URL
		    	  String url = "jdbc:mysql://173.194.249.69:3306/";
		    	  String password = "noknoknok";
		    	  
		    	  //Test URL
		    	  //String url = "jdbc:mysql://173.194.242.136:3306/";
		    	  //String password = "inminddtest";
		    	  
		    	  String dbName = "inmindd";
		    	  String driver = "com.mysql.jdbc.Driver";
		    	  String userName = "root";
		    	
		    	  try 
		    	  {
		  			Class.forName(driver).newInstance();
		  			conn = DriverManager.getConnection(url+dbName,userName,password);
		    	  } 
		    	  catch (Exception e) 
		    	  {
		  			e.printStackTrace();
		    	  } 
		      }
		  } 
		  catch (Exception e) 
		  {
		      e.printStackTrace();
		      return;
		  }
	}

	@Override
	public User getUserConnected() throws IllegalArgumentException {
		User userConnected = new User();
		userConnected = (User)getThreadLocalRequest().getSession().getAttribute("current_user");
		if (userConnected != null)
		{
			
		}
		return (User)getThreadLocalRequest().getSession().getAttribute("current_user");
	}
	
	@Override
	public Boolean unsetUserConnected() throws IllegalArgumentException {
		getThreadLocalRequest().getSession().setAttribute("current_user", null);
		return true;
	}
	
	@Override
	public SupportRiskFactorInfos querySupportRiskFactorInfos(User user, int riskfactorId) {
		SupportRiskFactorInfos infos = new SupportRiskFactorInfos();
		//open database connection
		initDBConnection();

		if (getSupportRiskFactorInfos(user, riskfactorId, infos)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return infos;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/****************************************
	 * Support environment methods  
	 * ************************************/
	
	/**************************
	 * @param user
	 * @param riskfactorId
	 * @param infos
	 * @return
	 */
	private boolean getSupportRiskFactorInfos(User user, int riskfactorId, SupportRiskFactorInfos infos) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT  * FROM `inmindd`.`support_riskfactors` WHERE id = ? AND lang = ?;");
			pstmt.setInt(1, riskfactorId);
			pstmt.setString(2, user.getLang());
			result = pstmt.executeQuery();

			while (result.next()) {
				infos.setId(result.getInt("id"));
				infos.setLang(result.getString("lang"));
				infos.setName(result.getString("name"));
				infos.setImage_url(result.getString("image_url"));
				infos.setDesc_keep(result.getString("desc_keep"));
				infos.setDesc_improv(result.getString("desc_improv"));
				infos.setSources(result.getString("sources"));
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
	public ArrayList<SupportRiskFactorInfos> queryAllSupportRiskFactorInfos(User user) {
		ArrayList<SupportRiskFactorInfos> infos = new ArrayList<SupportRiskFactorInfos>();
		//open database connection
		initDBConnection();

		if (getAllSupportRiskFactorInfos(user, infos)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return infos;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	private boolean getAllSupportRiskFactorInfos(User user, ArrayList<SupportRiskFactorInfos> infos) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT  * FROM `inmindd`.`support_riskfactors` WHERE lang = ? ORDER BY id ASC;");
			pstmt.setString(1, user.getLang());
			result = pstmt.executeQuery();

			while (result.next()) {
				SupportRiskFactorInfos info = new SupportRiskFactorInfos();
				info.setId(result.getInt("id"));
				info.setLang(result.getString("lang"));
				info.setName(result.getString("name"));
				info.setImage_url(result.getString("image_url"));
				info.setDesc_keep(result.getString("desc_keep"));
				info.setDesc_improv(result.getString("desc_improv"));
				info.setSources(result.getString("sources"));
				infos.add(info);
			}
			conn.close();
			return infos.size() == 0 ? false : true;
		}
		catch (SQLException e) {
			user = null;
			//return user;
			return false;
		}

	}

	@Override
	public Boolean updateSupportGoalUser(SupportGoalUser goal) throws IllegalArgumentException 
	{
		//open database connection
		initDBConnection();

		if (createSupportGoalUser(goal)) 
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return true;
		}
		else 
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return false;
		}
	}


	private boolean createSupportGoalUser(SupportGoalUser goal) 
	{
		String patient_id = goal.getId_user();
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);  

		String insert = "INSERT INTO `support_goals_users` (`id_goal`, `id_user`, `timestamp`, `comment`) VALUES (?, ?, ?, ?);";
		//changeEmailGroup(patient_id); //Update the user to the engaging email group
		
		//Check if the goal was already chosen by the patient and if it is don't rewrite it to database
		boolean goalChosen = goalChosenAlready(patient_id, goal.getId_goal(), goal.getComment());
		if(!goalChosen)
		{
			try 
			{
				PreparedStatement statement = (PreparedStatement) conn.prepareStatement(insert);
				statement.setInt(1, goal.getId_goal());
				statement.setString(2, patient_id);
				statement.setTimestamp(3,timestamp);
				statement.setString(4, goal.getComment());
				statement.executeUpdate();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				return false;	
			}
		}
		return true;
	
	}
	
	/**
	 * Check if a goal exists in the db before adding it for a user
	 * Also checks if it is a custom goal and if the custom goal isn't present it will allow its addition
	 * 
	 * @param user Patient object
	 * @param goalId Id of goal you are checking to see if they have already chosen
	 * @return boolean whether goal exists in db or not
	 */
	private boolean goalChosenAlready(String currentUser, int goalId, String goalComment)
	{
		String check = "SELECT * FROM `support_goals_users` WHERE id_goal = ? AND id_user = ?";
		boolean alreadyChosen = false;
		ResultSet goalInfo = null;
		
		try 
		{
			PreparedStatement goalPresentCheck = (PreparedStatement) conn.prepareStatement(check);
			goalPresentCheck.setInt(1, goalId);
			goalPresentCheck.setString(2, currentUser);
			goalInfo = goalPresentCheck.executeQuery();
			
			//if there is a result set
			if (goalInfo.isBeforeFirst())
			{	//iterate through it
				while(goalInfo.next())
				{	//if the comment is null then it has been chosen 
					String customText = goalInfo.getString("comment");
					if(customText == null)
					{
						alreadyChosen = true;
					}//or if the comments equal it has been chosen and they are not null
					else if(customText != null)
					{
						if(customText.equalsIgnoreCase(goalComment))
						{
							alreadyChosen = true;
						}
					}
				}
			}
			else
			{
				alreadyChosen = false;
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return alreadyChosen;
	}

	
	@Override
	public ArrayList<SupportGoalUser> querySupportGoalUser(User user) {
		//open database connection
		ArrayList<SupportGoalUser> goals = new ArrayList<SupportGoalUser>();
		initDBConnection();

		if (getSupportGoalUser(user, goals)) {
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

	private boolean getSupportGoalUser(User user, ArrayList<SupportGoalUser> goals) {
		String idUser = user.getUserId();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT  s.*, g.*, r.`name` AS `riskfactor_name`, r.`image_url` AS `riskfactor_image_url` FROM `inmindd`.`support_goals_users` AS `s`, `inmindd`.`support_goals` AS `g`, `inmindd`.`support_riskfactors` AS `r` WHERE s.id_goal = g.id AND g.id_riskfactor = r.id AND g.`lang` = r.`lang` AND s.`id_user` = ? AND g.`lang` = ?;");
			pstmt.setString(1, idUser);
			pstmt.setString(2, user.getLang());
			result = pstmt.executeQuery();

			while (result.next()) {
				SupportGoalUser goalUser = new SupportGoalUser();
				SupportGoal goal = new SupportGoal(result.getInt("id"), result.getInt("goal_nb"), result.getString("goal_name"), result.getString("description"), result.getString("image_url"));
				if(goal.getName() == "null"){
					goal.setName("");
				}
				if(goal.getText() == "null"){
					goal.setText("");
				}
				goalUser.setId_goal(result.getInt(1));
				goalUser.setId_user(result.getString(2));
				goalUser.setTimestamp(result.getTimestamp(3).toString());
				goalUser.setComment(result.getString(4));
				if(goalUser.getComment() == "null"){
					goalUser.setComment("");
				}
				goalUser.setRiskfactor_name(result.getString("riskfactor_name"));
				goalUser.setRiskfactor_image_url(result.getString("riskfactor_image_url"));
				goalUser.setGoal(goal);
				goals.add(goalUser);
			}
			conn.close();
			return (goals.size() > 0 ? true : false);
		}
		catch (SQLException e) {
			user = null;
			//return user;
			return false;
		}

	}

	@Override
	public ArrayList<SupportGoal> querySupportGoals(int riskFactor, String lang)
			throws IllegalArgumentException {
		//open database connection
		ArrayList<SupportGoal> goals = new ArrayList<SupportGoal>();
		initDBConnection();

		if (getSupportGoals(riskFactor, lang, goals)) {
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


	private boolean getSupportGoals(int riskFactor, String lang, ArrayList<SupportGoal> goalsList) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM `support_goals` WHERE `id_riskfactor` = ? AND `lang` = ?;");
			pstmt.setInt(1, riskFactor);
			pstmt.setString(2, lang);
			result = pstmt.executeQuery();

			while (result.next()) {
				SupportGoal data = new SupportGoal(result.getInt("id"), result.getInt("goal_nb"), result.getString("goal_name"), result.getString("description"), result.getString("image_url")); 
				goalsList.add(data);
			}
			conn.close();
			return (goalsList.size() > 0);
		}
		catch (SQLException e) {
			user = null;
			return false;
		}
	}
	
	
	@Override
	public ArrayList<SupportFAQ> querySupportFAQ(String lang)
			throws IllegalArgumentException {
		//open database connection
		ArrayList<SupportFAQ> faqs = new ArrayList<SupportFAQ>();
		initDBConnection();

		if (getSupportFAQ(lang, faqs)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return faqs;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return faqs;
		}
	}


	private boolean getSupportFAQ(String lang, ArrayList<SupportFAQ> faqList) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM `support_faq` WHERE `lang` = ?;");
			pstmt.setString(1, lang);
			result = pstmt.executeQuery();

			while (result.next()) {
				SupportFAQ f = new SupportFAQ(result.getInt("id"), result.getString("lang"), result.getString("question"), result.getString("answer")); 
				faqList.add(f);
			}
			conn.close();
			return (faqList.size() > 0);
		}
		catch (SQLException e) {
			user = null;
			return false;
		}
	}
	
	
	@Override
	public ArrayList<SupportExperts> querySupportExperts(String lang)
			throws IllegalArgumentException {
		//open database connection
		ArrayList<SupportExperts> experts = new ArrayList<SupportExperts>();
		initDBConnection();

		if (getSupportExperts(lang, experts)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return experts;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return experts;
		}
	}


	private boolean getSupportExperts(String lang, ArrayList<SupportExperts> expertList) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM `support_experts` WHERE `lang` = ? ORDER BY `country`;");
			pstmt.setString(1, lang);
			result = pstmt.executeQuery();

			while (result.next()) {
				SupportExperts f = new SupportExperts(result.getInt("id"), result.getString("lang"), result.getString("country"), result.getString("image_url"), result.getString("description")); 
				expertList.add(f);
			}
			conn.close();
			return (expertList.size() > 0);
		}
		catch (SQLException e) {
			user = null;
			return false;
		}
	}
	
	
	@Override
	public ArrayList<SupportApps> querySupportApps(String lang)
			throws IllegalArgumentException {
		//open database connection
		ArrayList<SupportApps> apps = new ArrayList<SupportApps>();
		initDBConnection();

		if (getSupportApps(lang, apps)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return apps;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return apps;
		}
	}


	private boolean getSupportApps(String lang, ArrayList<SupportApps> appsList) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT * FROM `support_apps` WHERE `lang` = ? ORDER BY `category`;");
			pstmt.setString(1, lang);
			result = pstmt.executeQuery();

			while (result.next()) {
				SupportApps f = new SupportApps(result.getInt("id"), result.getString("lang"), result.getString("name"), result.getString("logo_url"), result.getString("category"), result.getString("description")); 
				appsList.add(f);
			}
			conn.close();
			return (appsList.size() > 0);
		}
		catch (SQLException e) {
			user = null;
			return false;
		}
	}
	
	
	@Override
	public ArrayList<String> queryAllUsers()
			throws IllegalArgumentException {
		//open database connection
		ArrayList<String> users = new ArrayList<String>();
		initDBConnection();

		if (getAllUsers(users)) 
		{
			try
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return users;
		}
		else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return users;
		}
	}


	private boolean getAllUsers(ArrayList<String> userList) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		User user = getUserConnected();
		try {	          
			pstmt = conn.prepareStatement("SELECT userId FROM `user` WHERE `userId` LIKE ?;");
			pstmt.setString(1, user.getUserId().substring(0, 2) + "%");
			result = pstmt.executeQuery();

			while (result.next()) {
				userList.add(result.getString("userId"));
			}
			conn.close();
			return (userList.size() > 0);
		}
		catch (SQLException e) {
			user = null;
			return false;
		}
	}
	
	@Override
	public boolean isAdministrator() throws IllegalArgumentException {
		initDBConnection();
		User user = getUserConnected();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {	          
			pstmt = conn.prepareStatement("SELECT administrator FROM `user` WHERE `userId` = ?;");
			pstmt.setString(1, user.getUserId());
			result = pstmt.executeQuery();

			while (result.next()) {
				if(result.getInt("administrator") == 1){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return true;
				} else {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return false;
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
			return false;
		}
	}
	
	/***
	 * This method will add a users email address to the USER_MAIL table
	 * This method is called when a user registers and provides an email address
	 * @param userId The Id of the user 
	 * @param encryptedEmailAddr The encrypted email address
	 * @return A boolean indicating success or failure
	 * @throws IllegalArgumentException
	 */
	public Boolean addUserEmail(String userId, String encryptedEmailAddr) throws IllegalArgumentException
	{
		initDBConnection();
		String enc = EmailEncryption.encrypt(encryptedEmailAddr);
		String todayAsMySqlDatetime = this.getDateAsMySQLDateTime(new Date());
		String insertStatement = "INSERT INTO USER_MAIL (userId, email, lastLogin) VALUES (?,?,?);";
		PreparedStatement prep;
		try
		{
			prep = conn.prepareStatement(insertStatement);
			prep.setString(1, userId);
			prep.setString(2, enc);
			prep.setString(3, todayAsMySqlDatetime);
			//Returns false even when updated successfully ..weird
			boolean result =  prep.execute();
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			return result;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return false;
		}
	}
	
	/***
	 * This will update a users email address, it wil be called from the admin panel in the event a user wants to change theiir email address
	 * @param userId The id of the user to change
	 * @param emailAddress The new email address of the user
	 * @return a boolean indicating success or failure
	 */
	public Boolean updateUserMail(String userId, String emailAddress)
	{
		
		String encrypted = EmailEncryption.encrypt(emailAddress);
		if(userHasEmail(userId))
		{
			initDBConnection();
			String updateStat = "UPDATE USER_MAIL SET email=? WHERE userId=?";
			PreparedStatement prep;
			try
			{
				prep=conn.prepareStatement(updateStat);
				prep.setString(1, encrypted);
				prep.setString(2,userId);
				boolean result=prep.execute();
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
				return result;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				try
				{
					conn.close();
				}
				catch(SQLException ee)
				{
					ee.printStackTrace();
				}
				return false;
			}
		}
		else
		{
			initDBConnection();
			String updateStat = "INSERT INTO USER_MAIL (userId,email,emailGroup, lastSentEmail) VALUES(?,?,2,0); ";
			PreparedStatement prep;
			try
			{
				prep=conn.prepareStatement(updateStat);
				prep.setString(1, userId);
				prep.setString(2,encrypted);
				boolean result=prep.execute();
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
				return result;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				try
				{
					conn.close();
				}
				catch(SQLException ee)
				{
					ee.printStackTrace();
				}
				return false;
			}
		}
		
	}
	
	
	public Boolean userHasEmail(String userId)
	{
		initDBConnection();
		String updateStat = "SELECT * FROM USER_MAIL WHERE userId=?";
		PreparedStatement prep;
		try
		{
			prep=conn.prepareStatement(updateStat);
			prep.setString(1,userId);
			ResultSet result=prep.executeQuery();
			boolean ret = false;
			while(result.next())
			{
				ret = true;
			}
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			return ret;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return false;
		}
	}
	
	/***
	 * This method will remove a user from the User mail table, they will not be able to recieve emails when this is called
	 * @param userId
	 * @return a boolean indicating success or failure
	 */
	public Boolean deleteUserMail(String userId)
	{
		initDBConnection();
		String delStat = "DELETE FROM USER_MAIL WHERE userId=?";
		PreparedStatement prep;
		try
		{
			prep=conn.prepareStatement(delStat);
			prep.setString(1, userId);
			boolean result=prep.execute();
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			return result;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return false;
		}
	}
	
	
	
	/***
	 * This method will update a users last login in the email table. The value will be updated to the date and time of the server when the method is called
	 * @param userId The id of the user to update
	 * @return A boolean indicating success or failure
	 */
	public Boolean updateUserLastLogin(String userId) throws IllegalArgumentException
	{
		if(userHasEmail(userId))
		{
			//Before update, check the last login
			UserMail user = this.getUserMail(userId);
			changeEmailGroup(user.getUserId());
			initDBConnection();
			String todaysDate = this.getDateAsMySQLDateTime(new Date());
			String updateStatement = "UPDATE USER_MAIL SET lastLogin=? WHERE userId=?;";
			PreparedStatement prep;
			try
			{
				prep = conn.prepareStatement(updateStatement);
				prep.setString(1, todaysDate);
				prep.setString(2, userId);
				boolean result = prep.execute();
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				return result;

			}
			catch(SQLException e)
			{
				e.printStackTrace();
				try
				{
					conn.close();
				}
				catch(SQLException ee)
				{
					ee.printStackTrace();
				}
				return false;
			}
		}
		return true;
		
	}
	
	private UserMail getUserMail(String userId)
	{
		UserMail user = null;
		initDBConnection();
		String selStatement = "SELECT * FROM USER_MAIL WHERE userId=?;";
		PreparedStatement prep;
		try
		{
			prep = conn.prepareStatement(selStatement);
			prep.setString(1, userId);
			ResultSet result = prep.executeQuery();
			while(result.next())
			{
				String id = result.getString("userId");
				Date randomized = getDateRandomised(id);
				String email = result.getString("email");
				Date lastLogin = result.getDate("lastLogin");
				int emailGroup = result.getInt("emailGroup");
				int lastEmail = result.getInt("lastSentEmail");
				int randNumber = getRandomizedGroupForUser(id);
				user = new UserMail(id, email, lastLogin, emailGroup, lastEmail, randomized, randNumber);
			}
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return user;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return user;
		}
	}


	@Override
	public Boolean sendMail(String email, String lang, String body)
			throws IllegalArgumentException {
		//open database connection
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress("okellynoel@gmail.com", "Inmindd Support Environment"));
		    if(lang.equals("en")){
		    	msg.addRecipient(Message.RecipientType.TO,
		   		     new InternetAddress("maria.pierce@dcu.ie", "Maria Pierce"));
		    	msg.addRecipient(Message.RecipientType.TO,
			   		     new InternetAddress("Muriel.redmond@dcu.ie", "Muriel Redmond"));
		    } else if(lang.equals("fr")){
		    	msg.addRecipient(Message.RecipientType.TO,
		   		     new InternetAddress("valeria.manera@unice.fr", "Valeria Manera"));
		    } else if (lang.equals("nl")){
		    	msg.addRecipient(Message.RecipientType.TO,
		   		     new InternetAddress("kay.deckers@maastrichtuniversity.nl", "Kay Deckers"));
		    } else if(lang.equals("sc")){
		    	msg.addRecipient(Message.RecipientType.TO,
		   		     new InternetAddress("susan.browne@glasgow.ac.uk", "Susan Browne"));
		    }
		    msg.setSubject("[ask-the-experts] new question");

		    RegExp regExp = RegExp.compile("[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", "i");
			MatchResult matcher = regExp.exec(email);
			boolean matchFound = (matcher != null); // equivalent to regExp.test(inputStr); 

			if (!matchFound) {
				throw new AddressException("Invalid email address");
			}

		    Address[] addresses = new InternetAddress[1];
		    addresses[0] = new InternetAddress(email, "Patient");
			msg.setReplyTo(addresses);
		    msg.setText("Message from: " + email + "\n\n" + body);
		    Transport.send(msg);
		    return true;
		} catch (AddressException e) {
			System.out.println("address exception"+ e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid email address");
		} catch (MessagingException e) {
			System.out.println("messaging exception"+ e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("encoding exception"+ e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/***
	 * This method will convert a java date object into a String representation of the mysql datetime object
	 * @param date The date to be converted
	 * @return a String representing the mysql datetimte version of the date
	 */
	private String getDateAsMySQLDateTime(Date date)
	{
		SimpleDateFormat mySqlFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return mySqlFormatter.format(date);
	}

	/***
	 * Will return the list of users and emial addresses from the email table
	 * @return
	 */
	public ArrayList<UserMail> getUserMailList() 
	{
		initDBConnection();
		String selStatement = "SELECT * FROM USER_MAIL;";
		ArrayList<UserMail> list = new ArrayList<UserMail>();
		PreparedStatement prep;
		try
		{
			prep = conn.prepareStatement(selStatement);
			ResultSet result = prep.executeQuery();
			while(result.next())
			{
				String id = result.getString("userId");
				Date randomized = getDateRandomised(id);
				String email = result.getString("email");
				Date lastLogin = result.getDate("lastLogin");
				int emailGroup = result.getInt("emailGroup");
				int lastEmail = result.getInt("lastSentEmail");
				int randNumber = getRandomizedGroupForUser(id);
				list.add(new UserMail(id, email, lastLogin, emailGroup, lastEmail, randomized, randNumber));
			}
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return list;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return list;
		}
	}
	
	public int getRandomizedGroupForUser(String userId)
	{
		initDBConnection();
		String selStatement = "SELECT randomised_group FROM user WHERE userID=?;";
		PreparedStatement prep;
		try
		{
			prep = conn.prepareStatement(selStatement);
			prep.setString(1, userId);
			ResultSet result = prep.executeQuery();
			while(result.next())
			{
				String group = result.getString(1);
				
				if(group == null)
				{
					return EmailGroupConstants.RANDOMIZED_DONT_EMAIL;
				}
				else if(group.equalsIgnoreCase("Control"))
				{
					return EmailGroupConstants.RANDOMIZED_DONT_EMAIL;
				}
				else if(group.equalsIgnoreCase("Intervention"))
				{
					return EmailGroupConstants.INTERVENTION_GROUP;
				}
				
			}
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return -1;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return -1;
			
					
		}
	}
	
	/*public Date getDateRegisteredForUser(String userId)
	{
		initDBConnection();
		String selStatement = "SELECT timestamp FROM user WHERE userID=?;";
		PreparedStatement prep;
		try
		{
			prep = conn.prepareStatement(selStatement);
			prep.setString(1, userId);
			ResultSet result = prep.executeQuery();
			while(result.next())
			{
				Date d = result.getDate(1);
				return d;
			}
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return null;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return null;
		}
	}*/
	public Date getDateRandomised(String userId)
	{
		initDBConnection();
		String selStatement = "SELECT date_randomised FROM user WHERE userID=?;";
		PreparedStatement prep;
		try
		{
			prep = conn.prepareStatement(selStatement);
			prep.setString(1, userId);
			ResultSet result = prep.executeQuery();
			while(result.next())
			{
				Date d = result.getDate("date_randomised");
				
				return d;
			}
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return null;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return null;
		}
	}
	/***
	 * Will return a given email from the email table
	 * @param month the month the email should be sent
	 * @param emailGroup the emailgroup of the user
	 * @param lang the language of the email
	 * @return
	 */
	public ArrayList<EmailDetails> getEmail(int month, int emailGroup, String lang) {
		initDBConnection();
		String selStatement = "SELECT * FROM EMAILS_TO_SEND "
							+ "WHERE monthToSend=? "
							+ "and (emailGroup=? OR emailGroup=0) "
							+ "and lang=?;";
		PreparedStatement prep;
		ArrayList<EmailDetails> det = new ArrayList<EmailDetails>();
		try
		{
			prep = conn.prepareStatement(selStatement);
			prep.setString(1, month+"");
			prep.setString(2, emailGroup+"");
			prep.setString(3, lang);
			ResultSet result = prep.executeQuery();
			while(result.next())
			{
				String subject = result.getString("subject");
				String text = result.getString("Text_content");
				String lng = result.getString("lang");
				int mnth = result.getInt("monthToSend");
				int emailG = result.getInt("emailGroup");
				String plnTxt = result.getString("Plain_Text");
				det.add(new EmailDetails(subject, text, emailG, mnth, lng, plnTxt));
				
			}
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return det;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return det;
		}
	}


	public void updateLastSentEmail(String userId, int i) 
	{
		initDBConnection();
		String selStatement = "UPDATE USER_MAIL SET lastSentEmail=? WHERE userId=?;";
		PreparedStatement prep;
		try
		{
			prep = conn.prepareStatement(selStatement);
			prep.setString(1, i+"");
			prep.setString(2, userId);
			prep.execute();
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			
		}
		
	}
	
	
	/***
	 * This method will take an inactive user of the inmindd system and change them to an active user
	 * @param userId the user's id to change
	 * @return
	 */
	public Boolean changeEmailGroup(String userId)
	{
		initDBConnection();
		String selStatement = "UPDATE USER_MAIL SET emailGroup=1 WHERE userId=?;";
		PreparedStatement prep;
		try
		{
			int emailGroup = getUserEmailGroup(userId);
			if(emailGroup == EmailGroupConstants.USER_NOT_ENGAGED)
			{
				
			}
			prep = conn.prepareStatement(selStatement);
			prep.setString(1, userId);
			boolean result = prep.execute();
				
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return result;
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return false;
			
		}
	}
	
	public int getUserEmailGroup(String userId)
	{
		initDBConnection();
		String selStatement = "SELECT emailGroup from USER_MAIL WHERE userId=?";
		PreparedStatement prep;
		try
		{
			prep = conn.prepareStatement(selStatement);
			prep.setString(1, userId);
			ResultSet result = prep.executeQuery();
			while(result.next())
			{
				int group = result.getInt(1);
				return group;
				
			}
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return -1;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
			return -1;
		}
	}
	
	
	
	
}

