package com.inmindd.dcu.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.appengine.api.utils.SystemProperty;
import com.inmindd.dcu.client.PatientInfo;
import com.inmindd.dcu.shared.FeelingsInfo;
import com.inmindd.dcu.shared.MedicalInfo;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.SmokeAlcoholInfo;
import com.inmindd.dcu.shared.User;

public class CalculateScore {
	
	private Connection conn;
	private Statement statement = null;    
	private ResultSet resultSet = null;
	private Patient patient = new Patient();
	private FeelingsInfo feelings = new FeelingsInfo();
	private MedicalInfo medical = new MedicalInfo(); 
	private SmokeAlcoholInfo smoke = new SmokeAlcoholInfo();
	private String userId;
	
	
	public RiskFactorScore calcScore(RiskFactorScore rf, User user ) {
		
		initDBConnection();
		/*
		 * get the inputs from the database
		 */
		PreparedStatement pstmt = null;
		ResultSet result = null;
		userId = user.getUserId();
		try {
			
			String query = "select * from patient_info where patient_id = " + userId;
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery(query);
			populatePatient(result);
			query = "select * from feelings_info where patient_id = " + userId;
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery(query);
		
			populateFeelings(result);
		
			query = "select * from medical_info where patient_id = " + userId;
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery(query);
			populateMedical(result);
			//resultSet = statement.executeQuery("select * from smoking_alcohol_info where patient_id = " + userId);
			query = "select * from smoking_alcohol_info where patient_id = " + userId;
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery(query);
			populateSmokingAl(result);			
		} catch (SQLException e) {			
			e.printStackTrace();
		}			
		rf = score(rf);  // does the actual calculations based on the inputs
		
		return rf;
	}
	
	private RiskFactorScore score(RiskFactorScore rf) {
		try {
			rf.setUserId(userId);
			rf.setAge(patient.getAge());
			rf.setGender(patient.getGender());
			/*
			 *  calculate the score for each risk factor
			 */
			alcohol(rf);
			heartDisease(rf);
			kidneyDisease(rf);
			diabetes(rf);
			hypertension(rf);
			smoking(rf);
			cesd(rf);
			bmi(rf);
			mmol(rf);
		}
		catch (Exception e) {
			rf = null;
			return rf;
		}
		return rf;
	}
	
	private void mmol(RiskFactorScore rf){
		double cholNetherlands;
		double cholOthers;
		double chol = medical.getMmol();
		// netherlands calulation;
		if (chol >= 6.5) {
			rf.setCholesterolNetherlands(1.4);
			
		}
		if (chol >= 5.0) {
			rf.setCholesterolOthers(1.4);
			return;
		}
		else {
			rf.setCholesterolNetherlands(0);
			rf.setCholesterolOthers(0);			
		}
	}
	private void bmi(RiskFactorScore rf) {
		double bmi = 0;
		double bmiWeightLbs = 0;
		double bmiHeightInches = 0;
		double heightMeters = (medical.getCent() / 100.0);
		double heightFeet = medical.getFeet();
		double heightInches = medical.getInches();
		double weightKilos = medical.getKilo();
		double weightStone = medical.getStone();
		double weightLbs = medical.getLbs();
		if (heightMeters > 0 && weightKilos > 0){
			bmi = (weightKilos / heightMeters) / heightMeters; 
		//	double roundOffBmi = Math.round(bmi * 100.0) / 100.0;
			if (bmi  > 30) {
				rf.setMidlifeObesity(1.6);
			} else {
				rf.setMidlifeObesity(0);
			}
			return;			
		}
		
		if (heightFeet > 0 && weightStone > 0) {
			bmiWeightLbs = ((weightStone * 14) + weightLbs) * 703;
			bmiHeightInches = ((heightFeet * 12) + heightInches) *((heightFeet * 12) + heightInches);
			bmi = bmiWeightLbs / bmiHeightInches;
			if (bmi  > 30) {
				rf.setMidlifeObesity(1.6);
			} else {
				rf.setMidlifeObesity(0);
			}
			return;	
			
		}
	}
	
	private void cesd(RiskFactorScore rf) {
		int cesdScore = 0;
		String ces = feelings.getCes1();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes2();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes3();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes5();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes6();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes7();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes9();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes10();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes11();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes13();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes14();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes15();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes17();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes18();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes19();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes20();		 
		cesdScore += getNegativePoints(ces);
		ces = feelings.getCes4();		 
		cesdScore += getPositivePoints(ces);
		ces = feelings.getCes8();		 
		cesdScore += getPositivePoints(ces);
		ces = feelings.getCes12();		 
		cesdScore += getPositivePoints(ces);
		ces = feelings.getCes16();		 
		cesdScore += getPositivePoints(ces);
		if (cesdScore > 16) {
			rf.setDepression(2.1);
		}
		else {
			rf.setDepression(0);
		}
		return;
		
	
	}
	
	private int getNegativePoints(String ces) {
		int point = 0;
		if (ces != null && ces.equals("rarely"))
			point = 0;
		else if (ces != null && ces.equals("some"))
			point = 1;
		else if (ces != null && ces.equals("occasional"))
			point = 2;
		else if (ces != null && ces.equals("most"))
			point = 3;
		
		return point;
	}
	
	private int getPositivePoints(String ces) {
		int point = 0;
		if (ces != null && ces.equals("rarely"))
			point = 3;
		else if (ces != null && ces.equals("some"))
			point = 2;
		else if (ces != null && ces.equals("occasional"))
			point = 1;
		else if (ces != null && ces.equals("most"))
			point = 0;
		
		return point;
	}
	private void smoking(RiskFactorScore rf){
		String smoker_type = smoke.getSmoker_type();
		if (smoker_type != null && smoker_type.equals("current")){
			rf.setSmoking(1.5);  // raise score by factor 1.5 if current smoker
		}
		else {
			rf.setSmoking(0);
		}
	}
	private void hypertension(RiskFactorScore rf){
		String hypertension = medical.getHyper();
		if (hypertension != null && hypertension.equals("yes")){
			rf.setMidlifeHypertension(1.6);  // raise score if suffering from hypertension
		}
		else {
			rf.setMidlifeHypertension(0);
		}
	}
	
	private void diabetes(RiskFactorScore rf){
		String diabetes = medical.getMellitus();
		if (diabetes != null && diabetes.equals("yes")){
			rf.setDiabetes(1.3); // raise score if suffering from diabetes
		}
		else {
			rf.setDiabetes(0);
		}
	}
	private void kidneyDisease(RiskFactorScore rf) { 
		String kidney = medical.getKidney();
		if (kidney != null && kidney.equals("yes")) {
			rf.setChronicKidneyDisease(1.1);
		}
		else {
			rf.setChronicKidneyDisease(0);
		}
		
	}
	private void heartDisease(RiskFactorScore rf) {
		String coronary = medical.getCvd();
		
		if (coronary != null && coronary.equals("yes")) {
			rf.setCoronaryHeartDisease(1.1);
		}
		else {
			rf.setCoronaryHeartDisease(0.0);
		}
		
	}
	
	private void alcohol(RiskFactorScore rf) {
		String gender = patient.getGender();
		if (gender == null)
			return;
		String num_drinks = smoke.getNum_drinks();
		if (userId.startsWith("11") ) {
			if (gender.equals("male") &&    !(num_drinks.startsWith("18+"))){
				rf.setAlcohol(-1);
			}
			else if (gender.equals("male")  && (num_drinks.startsWith("18+"))){
				rf.setAlcohol(0);			 
			}

			else if (gender.equals("female") &&    (num_drinks.startsWith("7") || num_drinks.startsWith("8"))){
				rf.setAlcohol(-1);
			}
			else if (gender.equals("female")  && (num_drinks.startsWith("12") || num_drinks.startsWith("18+"))){
				rf.setAlcohol(0);			 
			}
		}

		else {
			if (gender.equals("male") &&    !(num_drinks.startsWith("21+"))){
				rf.setAlcohol(-1);
			}
			else if (gender.equals("male")  && (num_drinks.startsWith("21+)"))){
				rf.setAlcohol(0);			 
			}

			else if (gender.equals("female") &&    (num_drinks.startsWith("7") || num_drinks.startsWith("8"))){
				rf.setAlcohol(-1);
			}
			else if (gender.equals("female")  && (num_drinks.startsWith("14") || num_drinks.startsWith("21+"))){
				rf.setAlcohol(0);			 
			}

		}

	}
	
	private void populateSmokingAl(ResultSet result){
		try {
			while(result.last()){
				smoke.setSmoker_type(result.getString("smoker_type"));	
				smoke.setNum_drinks(result.getString("num_drinks"));
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	private void populateMedical(ResultSet resultSet){
		try {
			while(resultSet.last()){
				medical.setCent(resultSet.getInt("cent"));
				medical.setFeet(resultSet.getInt("feet"));
				medical.setInches(resultSet.getInt("inches"));
				medical.setKilo(resultSet.getInt("kilo"));
				medical.setStone(resultSet.getInt("stone"));
				medical.setLbs(resultSet.getInt("lbs"));
				medical.setMmol(resultSet.getDouble("mmol"));
				medical.setSystolic(resultSet.getInt("systolic"));
				medical.setDiastolic(resultSet.getInt("diastolic"));
				medical.setChol(resultSet.getString("chol"));
				medical.setHighChol(resultSet.getString("highchol"));
				medical.setLifeStyleChange(resultSet.getString("lifestylechange"));
				medical.setMedChol(resultSet.getString("medchol"));
				medical.setCvd(resultSet.getString("cvd"));
				medical.setHyper(resultSet.getString("hyper"));
				medical.setBloodPressure(resultSet.getString("bloodpressure"));
				medical.setMellitus(resultSet.getString("mellitus"));
				medical.setDiabetesTreat(resultSet.getString("diabetestreatment"));
				medical.setSugar(resultSet.getString("sugar"));
				medical.setKidney(resultSet.getString("kidney"));
				return;			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void populateFeelings(ResultSet resultSet) {
		
			try {
				while(resultSet.last()) {
					feelings.setCes1(resultSet.getString("ces1"));	
					feelings.setCes2(resultSet.getString("ces2"));
					feelings.setCes3(resultSet.getString("ces3"));	
					feelings.setCes4(resultSet.getString("ces4"));	
					feelings.setCes5(resultSet.getString("ces5"));
					feelings.setCes6(resultSet.getString("ces6"));	
					feelings.setCes7(resultSet.getString("ces7"));
					feelings.setCes8(resultSet.getString("ces8"));	
					feelings.setCes9(resultSet.getString("ces9"));
					feelings.setCes10(resultSet.getString("ces10"));
					feelings.setCes11(resultSet.getString("ces11"));
					feelings.setCes12(resultSet.getString("ces12"));
					feelings.setCes13(resultSet.getString("ces13"));
					feelings.setCes14(resultSet.getString("ces14"));
					feelings.setCes15(resultSet.getString("ces15"));
					feelings.setCes16(resultSet.getString("ces16"));
					feelings.setCes17(resultSet.getString("ces17"));
					feelings.setCes18(resultSet.getString("ces18"));
					feelings.setCes19(resultSet.getString("ces19"));
					feelings.setCes20(resultSet.getString("ces20"));
					feelings.setDepression(resultSet.getString("depression"));
					feelings.setTreated(resultSet.getString("treated"));
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	private void populatePatient(ResultSet resultSet) {
		try {
			while (resultSet.last()) {
				patient.setAge(resultSet.getInt("age"));
				patient.setGender(resultSet.getString("gender"));
				patient.setCountryOfBirth(resultSet.getString("country_of_birth"));
				patient.setMaritalStatus(resultSet.getString("marital_status"));
				
				patient.setLivingArrangements(resultSet.getString("living_arrangements"));
				//patient.setNumYearsEducation(resultSet.getInt("num_years_education"));
				patient.setOccupationalGroup(resultSet.getString("occupational_group"));
				patient.setEmploymentStatus(resultSet.getString("employment_status"));
				return;	
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
	
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

	
	
}
