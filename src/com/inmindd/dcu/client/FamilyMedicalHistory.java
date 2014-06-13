package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.FamilyHistoryInfo;
import com.inmindd.dcu.shared.User;

public class FamilyMedicalHistory {
	private FlowPanel familyMedicalHistory;
	private static final String LOGO_IMAGE_NAME = "images.jpeg";
	
	private RadioButton motherDementiaYes;
	private RadioButton motherDementiaNo;
	private RadioButton motherDementiaDontKnow;
	
	private RadioButton motherCardioYes;
	private RadioButton motherCardioNo;
	private RadioButton motherCardioDontKnow;
	
	private RadioButton motherMellitusYes;
	private RadioButton motherMellitusNo;
	private RadioButton motherMellitusDontKnow;
	
	private RadioButton fatherDementiaYes;
	private RadioButton fatherDementiaNo;
	private RadioButton fatherDementiaDontKnow;
	
	private RadioButton fatherCardioYes;
	private RadioButton fatherCardioNo;
	private RadioButton fatherCardioDontKnow;
	
	private RadioButton fatherMellitusYes;
	private RadioButton fatherMellitusNo;
	private RadioButton fatherMellitusDontKnow;
	

	private RadioButton siblingDementiaYes;
	private RadioButton siblingDementiaNo;
	private RadioButton siblingDementiaDontKnow;
	
	private RadioButton siblingCardioYes;
	private RadioButton siblingCardioNo;
	private RadioButton siblingCardioDontKnow;
	
	private RadioButton siblingMellitusYes;
	private RadioButton siblingMellitusNo;
	private RadioButton siblingMellitusDontKnow;
	private InminddServiceAsync InminddServiceSvc;
	private User user;
	private Login login;
	public void PatientPanel(){

	}

	public FamilyMedicalHistory() {
		// TODO Auto-generated constructor stub
	}

public FlowPanel setupFamilyMedicalHistoryPanel(Login login) {
	this.login = login;
	HTMLPanel mainHeader = new HTMLPanel("<h1>" +
			"About Your family medical history</h1>");
	
		HTMLPanel header = new HTMLPanel("<h3>" +
			"We would like to ask you some questions about your family medical history</h3>");
		header.getElement().getStyle().setProperty("textDecoration", "underline");
		
		Button prev = new Button("Retrieve previous data ?");
		

		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getHistoryData();
			}
		});
		   
	    familyMedicalHistory = new FlowPanel();
	  
	    
	    // Create questions about mother	
		familyMedicalHistory.add(mainHeader);
		familyMedicalHistory.add(header);
		familyMedicalHistory.add(prev);
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		HorizontalPanel mother_dementia = setupMotherQuestionsOne();
		familyMedicalHistory.add(mother_dementia);
		HorizontalPanel mother_cardio = setupMotherQuestionsTwo();
		familyMedicalHistory.add(mother_cardio);
		HorizontalPanel mother_diabetes = setupMotherQuestionsThree();
		familyMedicalHistory.add(mother_diabetes);
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		
		// Questions about father
		HorizontalPanel father_dementia =  setupFatherQuestionsOne();
		familyMedicalHistory.add(father_dementia);

		HorizontalPanel father_cardio =  setupFatherQuestionsTwo();
		familyMedicalHistory.add(father_cardio);
		
		HorizontalPanel father_diabetes = setupFatherQuestionsThree();
		familyMedicalHistory.add(father_diabetes);
		
		// Questions about siblings
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		
		HorizontalPanel sibling_dementia =  setupSiblingQuestionsOne();
		familyMedicalHistory.add(sibling_dementia);

		HorizontalPanel sibling_cardio =  setupSiblingQuestionsTwo();
		familyMedicalHistory.add(sibling_cardio);

		HorizontalPanel sibling_diabetes = setupSiblingQuestionsThree();
		familyMedicalHistory.add(sibling_diabetes);
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		Button btn = new Button("submit");
		familyMedicalHistory.add(btn);
		    
		    // Listen for mouse events on the submit button.
		    btn.addClickHandler(new ClickHandler() {
		    	public void onClick(ClickEvent event) {
		    		if (!checkQuestionResponses()) {
		    			return;
		    			
		    		}
		    		else {
		    			updateFamilyHistory();
		    		}
		    	}
		    		
		     
		    });
		
		
		return familyMedicalHistory;
}


	private boolean checkQuestionResponses() {
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		if (!(motherDementiaYes.getValue() || motherDementiaNo.getValue() || motherDementiaDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  mother's dementia question");
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(motherCardioYes.getValue() || motherCardioNo.getValue() || motherCardioDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  mother's Cardiovascular questions");
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(motherMellitusYes.getValue() || motherMellitusNo.getValue() || motherMellitusDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  mother's Diabetes question");
			showErrorPopupPanel(error, "red");
			return false;
		}


		if (!(fatherDementiaYes.getValue() || fatherDementiaNo.getValue() || fatherDementiaDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  father's dementia question");
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(fatherCardioYes.getValue() || fatherCardioNo.getValue() || fatherCardioDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  father's Cardiovascular questions");
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(fatherMellitusYes.getValue() || fatherMellitusNo.getValue() || fatherMellitusDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  father's Diabetes question");
			showErrorPopupPanel(error, "red");
			return false;
		}


		if (!(siblingDementiaYes.getValue() || siblingDementiaNo.getValue() || siblingDementiaDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  sibling's dementia question");
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(siblingCardioYes.getValue() || siblingCardioNo.getValue() || siblingCardioDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  sibling's Cardiovascular questions");
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(siblingMellitusYes.getValue() || siblingMellitusNo.getValue() || siblingMellitusDontKnow.getValue())) {
			InlineLabel error = new InlineLabel("Please select button regarding  sibling's Diabetes question");
			showErrorPopupPanel(error, "red");
			return false;
		}

		return true;
	}
	
	
	 private boolean callServiceSetup() {
			// set up rpc call
	
			InminddServiceSvc = (InminddServiceAsync) GWT.create(InminddService.class);
			ServiceDefTarget target = (ServiceDefTarget) InminddServiceSvc;
			String moduleRelativeURL = GWT.getModuleBaseURL() + "Inmindd";
			target.setServiceEntryPoint(moduleRelativeURL);	    			
			return true;

	 }
	 
	 private void updateFamilyHistory() {
		 FamilyHistoryInfo history = createHistory();
		 callServiceSetup();
		 
		 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
			 @Override	 
            public void onSuccess(Boolean result) {
            		if ((result == false)){	            		
            			InlineLabel error = new InlineLabel("Family History Data not updated !!!!!");
            			showErrorPopupPanel(error, "red");            			            			
            		}
            		else {
            			InlineLabel error = new InlineLabel("family History Data  updated. proceed to next panel");
            			showErrorPopupPanel(error, "green");  
            			Window.Location.reload();  // This is where the page relooad is triggered
            		}
                 
              }
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel("Invalid User Id or Password  - please reenter");
    			showErrorPopupPanel(error, "red");			
				
			}
		  };
		  
    	  InminddServiceSvc.updateFamilyHistory(history, callback);
	 }
    	 
	private FamilyHistoryInfo createHistory() {
		FamilyHistoryInfo history = new FamilyHistoryInfo();
		history.setUserId(login.getUserId());
		if (motherDementiaYes.getValue()) {
			history.setMotherDementia("yes");
		}
		
		else if (motherDementiaNo.getValue()) {
			history.setMotherDementia("no");
		}
		
		else {
			 history.setMotherDementia("dont");
		}
		
		if (motherCardioYes.getValue()) {
			history.setMotherCvd("yes");
		}
		
		else if (motherCardioNo.getValue()) {
			history.setMotherCvd("no");
		}
		
		else {
			 history.setMotherCvd("dont"); 			 
		}
		
		if (motherMellitusYes.getValue()) {
			history.setMotherDiabetes("yes");
		}
		
		else if (motherMellitusNo.getValue()) {
			history.setMotherDiabetes("no");
		}
		
		else {
			 history.setMotherDiabetes("dont"); 
			 
		}
		
		
		if (fatherDementiaYes.getValue()) {
			history.setFatherDementia("yes");
		}
		
		else if (fatherDementiaNo.getValue()) {
			history.setFatherDementia("no");
		}
		
		else {
			 history.setFatherDementia("dont");
		}
		
		if (fatherCardioYes.getValue()) {
			history.setFatherCvd("yes");
		}
		
		else if (fatherCardioNo.getValue()) {
			history.setFatherCvd("no");
		}
		
		else {
			 history.setFatherCvd("dont"); 			 
		}
		
		if (fatherMellitusYes.getValue()) {
			history.setFatherDiabetes("yes");
		}
		
		else if (fatherMellitusNo.getValue()) {
			history.setFatherDiabetes("no");
		}
		
		else {
			 history.setFatherDiabetes("dont"); 
			 
		}
		
		if (siblingDementiaYes.getValue()) {
			history.setSiblingDementia("yes");
		}
		
		else if (siblingDementiaNo.getValue()) {
			history.setSiblingDementia("no");
		}
		
		else {
			 history.setSiblingDementia("dont");
		}
		
		if (siblingCardioYes.getValue()) {
			history.setSiblingCvd("yes");
		}
		
		else if (siblingCardioNo.getValue()) {
			history.setSiblingCvd("no");
		}
		
		else {
			 history.setSiblingCvd("dont"); 			 
		}
		
		if (siblingMellitusYes.getValue()) {
			history.setSiblingDiabetes("yes");
		}
		
		else if (siblingMellitusNo.getValue()) {
			history.setSiblingDiabetes("no");
		}
		
		else {
			 history.setSiblingDiabetes("dont"); 
			 
		}
		return history;
	}
	
	 private void getHistoryData() {				
		 User user = login.getUser();
		 if (user== null) {

			 InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			 showErrorPopupPanel(error, "red");
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<FamilyHistoryInfo> callback =  new AsyncCallback<FamilyHistoryInfo>(){

			 @Override	 
			 public void onSuccess(FamilyHistoryInfo history) {
				 if ((history == null || history.getUserId().equals(null))){	            		
					 InlineLabel error = new InlineLabel("History Data not retrieved. No data available for this patient ");
					 showErrorPopupPanel(error, "red");            			
				 }            		
				 else {
					 InlineLabel error = new InlineLabel("History data retrieved- Edit as necessary");
					 showErrorPopupPanel(error, "green");  
					 populatePanel(history);
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 InlineLabel error = new InlineLabel("History data Database error");
				 showErrorPopupPanel(error, "red");			

			 }
		 };

		 InminddServiceSvc.queryFamilyHistory(user, callback);
		 return;
	 }
	 private void populatePanel(FamilyHistoryInfo history) {
		 if (history.getMotherDementia().equals("yes")){motherDementiaYes.setValue(true);}
		 if (history.getMotherDementia().equals("no")){motherDementiaNo.setValue(true);}
		 if (history.getMotherDementia().equals("dont")){motherDementiaDontKnow.setValue(true);}
		 if (history.getMotherCvd().equals("yes")){motherCardioYes.setValue(true);}
		 if (history.getMotherCvd().equals("no")){motherCardioNo.setValue(true);}
		 if (history.getMotherCvd().equals("dont")){motherCardioDontKnow.setValue(true);}
		 if (history.getMotherDiabetes().equals("yes")){motherMellitusYes.setValue(true);}
		 if (history.getMotherDiabetes().equals("no")){motherMellitusNo.setValue(true);}
		 if (history.getMotherDiabetes().equals("dont")){motherMellitusDontKnow.setValue(true);}	
		
		 if (history.getFatherDementia().equals("yes")){fatherDementiaYes.setValue(true);}
		 if (history.getFatherDementia().equals("no")){fatherDementiaNo.setValue(true);}
		 if (history.getFatherDementia().equals("dont")){fatherDementiaDontKnow.setValue(true);}
		 if (history.getFatherCvd().equals("yes")){fatherCardioYes.setValue(true);}
		 if (history.getFatherCvd().equals("no")){fatherCardioNo.setValue(true);}
		 if (history.getFatherCvd().equals("dont")){fatherCardioDontKnow.setValue(true);}
		 if (history.getFatherDiabetes().equals("yes")){fatherMellitusYes.setValue(true);}
		 if (history.getFatherDiabetes().equals("no")){fatherMellitusNo.setValue(true);}
		 if (history.getFatherDiabetes().equals("dont")){fatherMellitusDontKnow.setValue(true);}	
		 
		 if (history.getSiblingDementia().equals("yes")){siblingDementiaYes.setValue(true);}
		 if (history.getSiblingDementia().equals("no")){siblingDementiaNo.setValue(true);}
		 if (history.getSiblingDementia().equals("dont")){siblingDementiaDontKnow.setValue(true);}
		 if (history.getSiblingCvd().equals("yes")){siblingCardioYes.setValue(true);}
		 if (history.getSiblingCvd().equals("no")){siblingCardioNo.setValue(true);}
		 if (history.getSiblingCvd().equals("dont")){siblingCardioDontKnow.setValue(true);}
		 if (history.getSiblingDiabetes().equals("yes")){siblingMellitusYes.setValue(true);}
		 if (history.getSiblingDiabetes().equals("no")){siblingMellitusNo.setValue(true);}
		 if (history.getSiblingDiabetes().equals("dont")){siblingMellitusDontKnow.setValue(true);}	
			 
	 }
	 
	private HorizontalPanel setupMotherQuestionsOne() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel("Was your mother");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel("Dementia");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("marginLeft", "300%");
		
		
		String title = "Dementia is an umbrella term used to describe a number of conditions that are progressive and largely affect older people. Many different diseases can produce the symptoms of dementia. The most common types of dementia are Alzheimer's disease and Vascular Dementia. Other less common types include Lewy Bodies Disease (LBD), Frontotemporal dementia (FTD) and Korsakoff's Disease";
	
		family.add(lbl);
		family.add(lbl2);
		family.add(getInfoLogo(title));
		HorizontalPanel horPanel = new HorizontalPanel();
		motherDementiaYes = new RadioButton("motherDementia", "YES");
		motherDementiaNo = new RadioButton("motherDementia", "NO");
		motherDementiaDontKnow = new RadioButton("motherDementia", "DON'T KNOW");
		
		
		motherDementiaYes.getElement().getStyle().setProperty("marginLeft", "238px");
		motherDementiaNo.getElement().getStyle().setProperty("marginLeft", "55px");
		motherDementiaDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		
		horPanel.add(motherDementiaYes);
		horPanel.add(motherDementiaNo);
		horPanel.add(motherDementiaDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private HorizontalPanel setupMotherQuestionsTwo() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel("ever told by a doctor");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel("Cardiovascular disease");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("marginLeft", "160px");
		
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "10px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle("This includes any of the following conditions : heart attack, angina, heart failure mini-strokes & stroke");
		
		family.add(lbl);
		family.add(lbl2);
		family.add(logo);
		HorizontalPanel horPanel = new HorizontalPanel();
		motherCardioYes = new RadioButton("motherCardio", "YES");
		motherCardioNo = new RadioButton("motherCardio", "NO");
		motherCardioDontKnow = new RadioButton("motherCardio", "DON'T KNOW");
		
		
		motherCardioYes.getElement().getStyle().setProperty("marginLeft", "148px");
		motherCardioNo.getElement().getStyle().setProperty("marginLeft", "55px");
		motherCardioDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		
		horPanel.add(motherCardioYes);
		horPanel.add(motherCardioNo);
		horPanel.add(motherCardioDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	private HorizontalPanel setupMotherQuestionsThree() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel("that she had :");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel("Diabetes ");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("marginLeft", "208px");
	
		
		family.add(lbl);
		family.add(lbl2);
	
		HorizontalPanel horPanel = new HorizontalPanel();
		motherMellitusYes = new RadioButton("motherMellitus", "YES");
		motherMellitusNo = new RadioButton("motherMellitus", "NO");
		motherMellitusDontKnow = new RadioButton("motherMellitus", "DON'T KNOW");
		
		
		motherMellitusYes.getElement().getStyle().setProperty("marginLeft", "280px");
		motherMellitusNo.getElement().getStyle().setProperty("marginLeft", "55px");
		motherMellitusDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		
		horPanel.add(motherMellitusYes);
		horPanel.add(motherMellitusNo);
		horPanel.add(motherMellitusDontKnow);
		family.add(horPanel);
		
		return family;
	}
		
		private HorizontalPanel setupFatherQuestionsOne() {
			HorizontalPanel family = new HorizontalPanel();
			InlineLabel lbl = new InlineLabel("Was your father");
			lbl.getElement().getStyle().setProperty("fontWeight", "bold");
			InlineLabel lbl2 = new InlineLabel("Dementia");
			lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
			lbl2.getElement().getStyle().setProperty("marginLeft", "195px");
			
			Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
			logo.getElement().getStyle().setProperty("height", "25px");
			logo.getElement().getStyle().setProperty("marginLeft", "10px");
			logo.getElement().getStyle().setProperty("width", "25px");
			logo.setTitle("Dementia is an umbrella term used to describe a number of conditions that are progressive and largely affect older people. Many different diseases can produce the symptoms of dementia. The most common types of dementia are Alzheimer's disease and Vascular Dementia. Other less common types include Lewy Bodies Disease (LBD), Frontotemporal dementia (FTD) and Korsakoff's Disease");
			
			family.add(lbl);
			family.add(lbl2);
			family.add(logo);
			HorizontalPanel horPanel = new HorizontalPanel();
			fatherDementiaYes = new RadioButton("fatherDementia", "YES");
			fatherDementiaNo = new RadioButton("fatherDementia", "NO");
			fatherDementiaDontKnow = new RadioButton("fatherDementia", "DON'T KNOW");
			
			
			fatherDementiaYes.getElement().getStyle().setProperty("marginLeft", "238px");
			fatherDementiaNo.getElement().getStyle().setProperty("marginLeft", "55px");
			fatherDementiaDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
			
			horPanel.add(fatherDementiaYes);
			horPanel.add(fatherDementiaNo);
			horPanel.add(fatherDementiaDontKnow);
			family.add(horPanel);
			
			return family;
		}
		
	
	
	private HorizontalPanel setupFatherQuestionsTwo() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel("ever told by a doctor");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel("Cardiovascular disease");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("marginLeft", "160px");
		
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "10px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle("This includes any of the following conditions : heart attack, angina, heart failure mini-strokes & stroke");
		
		family.add(lbl);
		family.add(lbl2);
		family.add(logo);
		HorizontalPanel horPanel = new HorizontalPanel();
		fatherCardioYes = new RadioButton("fatherCardio", "YES");
		fatherCardioNo =  new RadioButton("fatherCardio", "NO");
		fatherCardioDontKnow = new RadioButton("fatherCardio", "DON'T KNOW");
		
		
		fatherCardioYes.getElement().getStyle().setProperty("marginLeft", "148px");
		fatherCardioNo.getElement().getStyle().setProperty("marginLeft", "55px");
		fatherCardioDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		
		horPanel.add(fatherCardioYes);
		horPanel.add(fatherCardioNo);
		horPanel.add(fatherCardioDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	private HorizontalPanel setupFatherQuestionsThree() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel("that he had :");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel("Diabetes");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("marginLeft", "215px");
	
		
		family.add(lbl);
		family.add(lbl2);
	
		HorizontalPanel horPanel = new HorizontalPanel();
		fatherMellitusYes = new RadioButton("fatherMellitus", "YES");
		fatherMellitusNo = new RadioButton("fatherMellitus", "NO");
		fatherMellitusDontKnow = new RadioButton("fatherMellitus", "DON'T KNOW");
		
		
		fatherMellitusYes.getElement().getStyle().setProperty("marginLeft", "280px");
		fatherMellitusNo.getElement().getStyle().setProperty("marginLeft", "55px");
		fatherMellitusDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		
		horPanel.add(fatherMellitusYes);
		horPanel.add(fatherMellitusNo);
		horPanel.add(fatherMellitusDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private HorizontalPanel setupSiblingQuestionsOne() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel("Were any of your siblings");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel("Dementia");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("marginLeft", "130px");
		
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "10px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle("Dementia is an umbrella term used to describe a number of conditions that are progressive and largely affect older people. Many different diseases can produce the symptoms of dementia. The most common types of dementia are Alzheimer's disease and Vascular Dementia. Other less common types include Lewy Bodies Disease (LBD), Frontotemporal dementia (FTD) and Korsakoff's Disease");
		
		family.add(lbl);
		family.add(lbl2);
		family.add(logo);
		HorizontalPanel horPanel = new HorizontalPanel();
		siblingDementiaYes = new RadioButton("siblingDementia", "YES");
		siblingDementiaNo = new RadioButton("siblingDementia", "NO");
		siblingDementiaDontKnow = new RadioButton("dementiaButton", "DON'T KNOW");
		
		
		siblingDementiaYes.getElement().getStyle().setProperty("marginLeft", "238px");
		siblingDementiaNo.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingDementiaDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		
		horPanel.add(siblingDementiaYes);
		horPanel.add(siblingDementiaNo);
		horPanel.add(siblingDementiaDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private HorizontalPanel setupSiblingQuestionsTwo() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel("ever told by a doctor");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel("Cardiovascular disease");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("marginLeft", "160px");
		
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "10px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle("This includes any of the following conditions: heart attack, angina, heart failure mini-strokes & stroke");
		
		family.add(lbl);
		family.add(lbl2);
		family.add(logo);
		HorizontalPanel horPanel = new HorizontalPanel();
		siblingCardioYes = new RadioButton("siblingCardio", "YES");
		siblingCardioNo = new RadioButton("siblingCardio", "NO");
		siblingCardioDontKnow = new RadioButton("siblingCardio", "DON'T KNOW");
		
		
		siblingCardioYes.getElement().getStyle().setProperty("marginLeft", "146px");
		siblingCardioNo.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingCardioDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		
		horPanel.add(siblingCardioYes);
		horPanel.add(siblingCardioNo);
		horPanel.add(siblingCardioDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private HorizontalPanel setupSiblingQuestionsThree() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel("that s/he had :");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel("Diabetes");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("marginLeft", "203px");
	
		
		family.add(lbl);
		family.add(lbl2);
	
		HorizontalPanel horPanel = new HorizontalPanel();
		siblingMellitusYes = new RadioButton("siblingMellitus", "YES");
		siblingMellitusNo = new RadioButton("siblingMellitus", "NO");
		siblingMellitusDontKnow = new RadioButton("siblingMellitus", "DON'T KNOW");
		
		
		siblingMellitusYes.getElement().getStyle().setProperty("marginLeft", "277px");
		siblingMellitusNo.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingMellitusDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		
		horPanel.add(siblingMellitusYes);
		horPanel.add(siblingMellitusNo);
		horPanel.add(siblingMellitusDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private void showErrorPopupPanel(InlineLabel error, String colour) {
		PopupPanel popup = new PopupPanel(true, true);			

		popup.setTitle("Error");
		VerticalPanel vertPanel = new VerticalPanel();
		error.getElement().getStyle().setProperty("color", colour);
		error.getElement().getStyle().setProperty("fontWeight", "bold");
		error.getElement().getStyle().setProperty("marginLeft", "25px");
		vertPanel.add(error);
		popup.setWidget(vertPanel);
		//popup.setGlassEnabled(true);
		popup.setPopupPosition(190,680);
		popup.setWidth("550px");
		popup.show();

	}
	private Image getInfoLogo(String title) {
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "200px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle(title);
		return logo;
	}
	
	
}

	
	