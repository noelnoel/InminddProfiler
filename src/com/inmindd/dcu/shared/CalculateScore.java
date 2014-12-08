package com.inmindd.dcu.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.appengine.api.utils.SystemProperty;

public class CalculateScore {
	
	private Connection conn;
	private Statement statement = null;    
	private ResultSet resultSet = null;
	private Patient patient = new Patient();
	private FeelingsInfo feelings = new FeelingsInfo();
	private MedicalInfo medical = new MedicalInfo(); 
	private SmokeAlcoholInfo smoke = new SmokeAlcoholInfo();
	private DietInfo  diet = new DietInfo();
	private CognitiveOneInfo cognitiveOne = new CognitiveOneInfo();
	private CognitiveTwoInfo cognitiveTwo = new CognitiveTwoInfo();
	private PhysicalActivityInfo physical = new PhysicalActivityInfo();
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
			
			query = "select * from diet_info where patient_id = " + userId;
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery(query);
			populateDiet(result);	
			
			query = "select * from cognitive_one_info where patient_id = " + userId;
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery(query);
			populateCogOne(result);	
			
			
			query = "select * from cognitive_two_info where patient_id = " + userId;
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery(query);
			populateCogTwo(result);	
			
			
			query = "select * from physical_activities_info where patient_id = " + userId;
			
			pstmt = conn.prepareStatement("");
			result = pstmt.executeQuery(query);
			populatePhysical(result);	
			
			conn.close();
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
			physical(rf);
			alcohol(rf);
			heartDisease(rf);
			kidneyDisease(rf);
			diabetes(rf);
			hypertension(rf);
			smoking(rf);
			cesd(rf);
			bmi(rf);
			mmol(rf);
			diet(rf);
			cri(rf);  // calculate cognitive reserve index
			
		}
		catch (Exception e) {
			rf = null;
			return rf;
		}
		return rf;
	}
	
	private void physical(RiskFactorScore rf) {
		
		String  workType = physical.getPhysicalWork();
		double hrsWeekCycling = physical.getSummerCyclingHours() + physical.getWinterCyclingHours();
		double hrsWeekPhysical = physical.getSummerPhysicalHours() + physical.getWinterPhysicalHours();
		if ((hrsWeekCycling + hrsWeekPhysical) / 2.0  >= 3.5) {
			rf.setPhysicalInactivity(0);
			
		}
		if ((hrsWeekCycling + hrsWeekPhysical) / 2.0  == 0 && !(workType.equals("manual") || workType.equals("heavy"))) {
			rf.setPhysicalInactivity(5.9);
			return;
		}
		
		if (((hrsWeekCycling  + hrsWeekPhysical) / 2.0  <= 3.50) && (workType.equals("sedentary") || workType.equals("na"))) {
			rf.setPhysicalInactivity(5.9);
			return;
		}
		
		else  {
			rf.setPhysicalInactivity(0);
			
		}
	}
	private void cri(RiskFactorScore rf) {
		double criEducation = 0; 
		double criWork = 0;
		double criLeisure = 0;

		int participantAge = patient.getAge();

		/****************************************
		 * Calculate Education Cog Reserve Index
		 ****************************************/
		//Calculate education model value
		double eduSlope = -0.164;
		double eduIntercept = 21.169;
		double eduModelValue = participantAge * eduSlope + eduIntercept;
		
		// Calculate EduCRI 
		double yearsEducation = cognitiveOne.getFormalEducationYears() + cognitiveOne.getNonFormalEducationYears();
		double eduSampleSD = 4.75;
		criEducation = ((yearsEducation  - eduModelValue) / eduSampleSD); // (years - model value) / s.d 
		
		/***********************************
		 * Calculate Working Activity CRI 
		 ***********************************/
		//work vairables 
		double workingYears = 0;
		int[] maxWorkingYears = new int [10];
		int numberWorkLevel = 0;
		
		// Calculate Max Working Years
		if (cognitiveOne.getManager() > 0) {
			workingYears += cognitiveOne.getManager() * 5;
			maxWorkingYears[0] = cognitiveOne.getManager() * 5;	
			numberWorkLevel++;
		}
		if(cognitiveOne.getProfessional() > 0 ) {
			workingYears += cognitiveOne.getProfessional() * 4;
			maxWorkingYears[1] = cognitiveOne.getProfessional() * 4;
			numberWorkLevel++;
		}
		if ( cognitiveOne.getTechnician() > 0) {
			workingYears += cognitiveOne.getTechnician() * 3;
			maxWorkingYears[2] = cognitiveOne.getTechnician() * 3;
			numberWorkLevel++;
		}
		if ( cognitiveOne.getClerical() > 0) {
			workingYears += cognitiveOne.getClerical() * 3;
			maxWorkingYears[3] = cognitiveOne.getClerical() * 3;
			numberWorkLevel++;
		}
		if ( cognitiveOne.getCraft() > 0) {
			workingYears += cognitiveOne.getCraft() * 2;
			maxWorkingYears[4] = cognitiveOne.getCraft() * 2;
			numberWorkLevel++;
		}
		if ( cognitiveOne.getAgriculture() > 0) {
			workingYears += cognitiveOne.getAgriculture() * 2;
			maxWorkingYears[5] = cognitiveOne.getAgriculture() * 2;
			numberWorkLevel++;
		}
		if ( cognitiveOne.getService() > 0) {
			workingYears += cognitiveOne.getService() * 2;
			maxWorkingYears[6] = cognitiveOne.getService() * 2;
			numberWorkLevel++;
		}
		if ( cognitiveOne.getElementary() > 0) {
			workingYears += cognitiveOne.getElementary() * 1;
			maxWorkingYears[7] = cognitiveOne.getElementary() * 2;
			numberWorkLevel++;
		}
		if ( cognitiveOne.getPlant() > 0) {
			workingYears += cognitiveOne.getPlant() * 1;
			maxWorkingYears[8] = cognitiveOne.getPlant() * 1;
			numberWorkLevel++;
		}
		
		int max = getMaxValue(maxWorkingYears);
		if (numberWorkLevel > 1) {
			numberWorkLevel--;
		}
		else numberWorkLevel = 1;
		double workingActivity = (workingYears - max) / numberWorkLevel;
		
		//Calculate Working Model Value 
		double workSlope = 1.124;
		double workIntercept = -2.082;
		double workModelValue = participantAge * workSlope + workIntercept;
		
		//Calculate Working Cognitive Reserve Index
		double workActSampeSD = 40.21979;
		criWork = (workingActivity + max - workModelValue) / workActSampeSD;  
		
		/**************************************
		 * Calculate Leisure CRI
		 ***************************************/
		// Leisure variables 
		double leisureYears = 0;
		
		leisureYears += cognitiveTwo.getArtistic_years();
		leisureYears += cognitiveTwo.getBank_account_years();
		leisureYears += cognitiveTwo.getBooks_years();
		leisureYears += cognitiveTwo.getChildren_years();
		leisureYears += cognitiveTwo.getCinema_years();
		leisureYears += cognitiveTwo.getDriving_years();
		leisureYears += cognitiveTwo.getExhibitions_years();
		leisureYears += cognitiveTwo.getGardening_years();
		leisureYears += cognitiveTwo.getHolidays_years();
		leisureYears += cognitiveTwo.getHousehold_years();
		leisureYears += cognitiveTwo.getLeisure_years();
		leisureYears += cognitiveTwo.getPets_years();
		leisureYears += cognitiveTwo.getReading_years();
		leisureYears += cognitiveTwo.getSocial_years();
		leisureYears += cognitiveTwo.getTechnology_years();
		leisureYears += cognitiveTwo.getVolunteering_years();
		
		leisureYears += (cognitiveTwo.getNumber_children() * 5) +10;

		//Calculate Working Model Value 
		double leisureSlope = 3.754;
		double leisureIntercept = 2.680;
		double leisureModelValue = participantAge * leisureSlope + leisureIntercept;
		
		//Calculate Leisure CRI 
		double leisureTimeSampleSD = 80.24101;
		criLeisure = (leisureYears - leisureModelValue) / leisureTimeSampleSD;
				
		/*********************************
		 * Calculate Final CRI
		 ********************************/
		criEducation = criEducation * 15 + 100;
		criWork = criWork * 15 + 100;
		criLeisure = criLeisure * 15 + 100;
		
		// the final cognitive reserve index score
		double criFinal = ((((criEducation + criWork + criLeisure) / 3) - 100) / 11.3277) * 15 + 100;
		
		if (criFinal >= 100) 
			rf.setHighCognitiveActivity(0);
		else if (criFinal < 100)
			rf.setHighCognitiveActivity(17.1);
	}

	
	
	private void diet(RiskFactorScore rf ) {
		int dietScore = 0;
		
		if (diet.getCulinaryFat() == 1) 
			dietScore++;
		
		if (diet.getRapeSeedOil() == 4) 
			dietScore++;
		
		if (diet.getVegetableServings() > 2) 
			dietScore++;
		
		if (diet.getFruit() == 3)
			dietScore++;
		
		if(diet.getRedMeat() < 1)
			dietScore++;
		
		if (diet.getButter() < 1)
			dietScore++;
		
		if (diet.getBeverages() < 1)
			dietScore++;
		
		if	(diet.getWine() >= 7)
			dietScore++;
		
		if (diet.getLegumes() >= 3)
			dietScore++;
		
		if (diet.getFish() >= 3)
			dietScore++;
		
		if (diet.getNuts() >= 3)
			dietScore++;
		
		if (diet.getSweets() < 3)
			dietScore++;
			
		if (diet.getChicken() == 1)
			dietScore++;
		
		if (diet.getSauce() >= 2)
			dietScore++;
		
		if (dietScore < 11)
			rf.setHealthyDiet(9.1);
	}
	
	
	private void mmol(RiskFactorScore rf){
		double cholNetherlands;
		double cholOthers;
		double chol = medical.getMmol();
		// netherlands calulation;
		if (chol >= 6.5) {
			rf.setCholesterolNetherlands(7.5);
			
		}
		if (chol >= 5.0) {
			rf.setCholesterolOthers(7.5);
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
		if (heightMeters > 0 && weightKilos == 0 && weightStone > 0) { // someone using metric & imperial. convert weight to metric
			weightKilos = ((weightStone * 14) + weightLbs) * 0.453;
		}
		
		if (weightKilos > 0 && heightMeters == 0 && heightFeet > 0) { //again. Convert feet / inches to metric
			heightMeters = ((heightFeet * 12 + heightInches) * 2.54) / 100;
		}
		
		if (heightMeters > 0 && weightKilos > 0){
			bmi = (weightKilos / heightMeters) / heightMeters; 
		//	double roundOffBmi = Math.round(bmi * 100.0) / 100.0;
			if (bmi  > 30) {
				rf.setMidlifeObesity(8.6);
			} else {
				rf.setMidlifeObesity(0);
			}
			return;			
		}
		
		if (heightFeet > 0 && weightStone > 0) {
			bmiWeightLbs = ((weightStone * 14) + weightLbs) * 703;
			bmiHeightInches = ((heightFeet * 12) + heightInches) *((heightFeet * 12) + heightInches);
			if (bmiHeightInches > 0)
				bmi = bmiWeightLbs / bmiHeightInches;
			if (bmi  > 30) {
				rf.setMidlifeObesity(8.6);
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
			rf.setDepression(11.2);
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
			rf.setSmoking(8);  // raise score by factor 1.5 if current smoker
		}
		else {
			rf.setSmoking(0);
		}
	}
	private void hypertension(RiskFactorScore rf){
		String hypertension = medical.getHyper();
		if (hypertension != null && hypertension.equals("yes")){
			rf.setMidlifeHypertension(8.6);  // raise score if suffering from hypertension
		}
		else {
			rf.setMidlifeHypertension(0);
		}
	}
	
	private void diabetes(RiskFactorScore rf){
		String diabetes = medical.getMellitus();
		if (diabetes != null && diabetes.equals("yes")){
			rf.setDiabetes(7); // raise score if suffering from diabetes
		}
		else {
			rf.setDiabetes(0);
		}
	}
	private void kidneyDisease(RiskFactorScore rf) { 
		String kidney = medical.getKidney();
		if (kidney != null && kidney.equals("yes")) {
			rf.setChronicKidneyDisease(5.9);
		}
		else {
			rf.setChronicKidneyDisease(0);
		}
		
	}
	private void heartDisease(RiskFactorScore rf) {
		String coronary = medical.getCvd();
		
		if (coronary != null && coronary.equals("yes")) {
			rf.setCoronaryHeartDisease(5.9);
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
				rf.setAlcohol(0);
			}
			else if (gender.equals("male")  && (num_drinks.startsWith("18+"))){
				rf.setAlcohol(5.3);			 
			}

			else if (gender.equals("female") &&    (num_drinks.startsWith("7") || num_drinks.startsWith("8"))){
				rf.setAlcohol(0);
			}
			else if (gender.equals("female")  && (num_drinks.startsWith("12") || num_drinks.startsWith("18+"))){
				rf.setAlcohol(5.3);			 
			}
		}

		else {
			if (gender.equals("male") &&    !(num_drinks.startsWith("21+"))){
				rf.setAlcohol(0);
			}
			else if (gender.equals("male")  && (num_drinks.startsWith("21+"))){
				rf.setAlcohol(5.3);			 
			}

			else if (gender.equals("female") &&    (num_drinks.startsWith("7") || num_drinks.startsWith("8"))){
				rf.setAlcohol(0);
			}
			else if (gender.equals("female")  && (num_drinks.startsWith("14") || num_drinks.startsWith("21+"))){
				rf.setAlcohol(5.3);			 
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
	
	private void populateDiet(ResultSet result) {
		try {
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
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void populateCogOne(ResultSet result) {
		try {
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
				
					return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void populateCogTwo(ResultSet result) {
		try {
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
				
				
					return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void populatePhysical(ResultSet result) {
		try {
			while (result.last()) {
				
				physical.setUserId(result.getString(1));
				physical.setDiyHours(result.getInt(3));
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
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// getting the maximum value
	public static int getMaxValue(int[] array){  
	      int maxValue = array[0];  
	      for(int i=1;i < array.length;i++){  
	      if(array[i] > maxValue){  
	      maxValue = array[i];  

	         }  
	     }  
	     return maxValue;  
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
