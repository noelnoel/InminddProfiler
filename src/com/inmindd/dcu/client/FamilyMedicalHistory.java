package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.FamilyHistoryInfo;
import com.inmindd.dcu.shared.User;

public class FamilyMedicalHistory {
	
	
	private static final int DECK_HISTORY = 4;
	private static final int DECK_PHYSICAL = 5;
	
	private TabLayoutPanel content;
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
	private Login login;
	public static FamilyMedicalHistory lastinstance;
	
	private static InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	public FamilyMedicalHistory() {
		lastinstance = this;
	}

	public void setContent(TabLayoutPanel content) {
		this.content = content;
	}
	public static void clearInputs() {
		lastinstance.fatherCardioDontKnow.setValue(false);
		lastinstance.fatherCardioNo.setValue(false);
		lastinstance.fatherCardioYes.setValue(false);
		lastinstance.fatherDementiaDontKnow.setValue(false);
		lastinstance.fatherDementiaNo.setValue(false);
		lastinstance.fatherDementiaYes.setValue(false);
		lastinstance.fatherMellitusDontKnow.setValue(false);
		lastinstance.fatherMellitusNo.setValue(false);
		lastinstance.fatherMellitusYes.setValue(false);
		
		lastinstance.motherCardioDontKnow.setValue(false);
		lastinstance.motherCardioNo.setValue(false);
		lastinstance.motherCardioYes.setValue(false);
		lastinstance.motherDementiaDontKnow.setValue(false);
		lastinstance.motherDementiaNo.setValue(false);
		lastinstance.motherDementiaYes.setValue(false);
		lastinstance.motherMellitusDontKnow.setValue(false);
		lastinstance.motherMellitusNo.setValue(false);
		lastinstance.motherMellitusYes.setValue(false);
		
		lastinstance.siblingCardioDontKnow.setValue(false);
		lastinstance.siblingCardioNo.setValue(false);
		lastinstance.siblingCardioYes.setValue(false);
		lastinstance.siblingDementiaDontKnow.setValue(false);
		lastinstance.siblingDementiaNo.setValue(false);
		lastinstance.siblingDementiaYes.setValue(false);
		lastinstance.siblingMellitusDontKnow.setValue(false);
		lastinstance.siblingMellitusNo.setValue(false);
		lastinstance.siblingMellitusYes.setValue(false);
		
	}
	
	public FlowPanel setupFamilyMedicalHistoryPanel(Login login) {
		
		ScrollPanel scroll = new ScrollPanel();
		this.login = login;
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				constants.familymed()+ "</h1>");

		HTMLPanel header = new HTMLPanel("<h3>" +
				constants.familyquestions() + "</h3>");
		header.getElement().getStyle().setProperty("textDecoration", "underline");

		Button prev = new Button(constants.review());


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
		InlineLabel lbl3 = new InlineLabel(constants.history_8());
		lbl3.getElement().getStyle().setProperty("fontWeight", "bold");
		familyMedicalHistory.add(lbl3);
		HorizontalPanel sibling_diabetes = setupSiblingQuestionsThree();
		familyMedicalHistory.add(sibling_diabetes);
		familyMedicalHistory.add(new HTMLPanel("<span>  <br>  </span>"));
		Button btn = new Button(constants.submit());
		familyMedicalHistory.add(btn);
		
		
		
		scroll.setSize("100%", "65%");
		scroll.add(familyMedicalHistory);
		scroll.setAlwaysShowScrollBars(true);
		scroll.scrollToTop();
		FlowPanel family = new FlowPanel();
		family.add(scroll);			
		

		
		
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

		
		////return familyMedicalHistory;
		
		return family;
		
	}


	private boolean checkQuestionResponses() {
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel(constants.register());
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		if (!(motherDementiaYes.getValue() || motherDementiaNo.getValue() || motherDementiaDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(motherCardioYes.getValue() || motherCardioNo.getValue() || motherCardioDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(motherMellitusYes.getValue() || motherMellitusNo.getValue() || motherMellitusDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
			showErrorPopupPanel(error, "red");
			return false;
		}


		if (!(fatherDementiaYes.getValue() || fatherDementiaNo.getValue() || fatherDementiaDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(fatherCardioYes.getValue() || fatherCardioNo.getValue() || fatherCardioDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(fatherMellitusYes.getValue() || fatherMellitusNo.getValue() || fatherMellitusDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
			showErrorPopupPanel(error, "red");
			return false;
		}


		if (!(siblingDementiaYes.getValue() || siblingDementiaNo.getValue() || siblingDementiaDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(siblingCardioYes.getValue() || siblingCardioNo.getValue() || siblingCardioDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
			showErrorPopupPanel(error, "red");
			return false;
		}

		else if (!(siblingMellitusYes.getValue() || siblingMellitusNo.getValue() || siblingMellitusDontKnow.getValue())) {
			InlineLabel error = new InlineLabel(constants.family_med_error());
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
            			InlineLabel error = new InlineLabel(constants.data_not_updated());
            			showErrorPopupPanel(error, "red");            			            			
            		}
            		else {
            			//InlineLabel error = new InlineLabel("Family history Data  updated. proceed to next panel");
            			//showErrorPopupPanel(error, "green");  
            			content.selectTab(DECK_PHYSICAL);
            			content.getTabWidget(DECK_HISTORY).getElement().getStyle().setProperty("backgroundColor", "red");
            			
            		}
                 
              }
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel(constants.invalid_id_pass());
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

			 InlineLabel error  = new InlineLabel(constants.register());
			 showErrorPopupPanel(error, "red");
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<FamilyHistoryInfo> callback =  new AsyncCallback<FamilyHistoryInfo>(){

			 @Override	 
			 public void onSuccess(FamilyHistoryInfo history) {
				 if ((history == null || history.getUserId()== null)){	            		
					 InlineLabel error = new InlineLabel(constants.unable_to_retrieve());
					 showErrorPopupPanel(error, "red");            			
				 }            		
				 else {
					 InlineLabel error = new InlineLabel(constants.retrieved());
					 showErrorPopupPanel(error, "green");  
					 populatePanel(history);
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 InlineLabel error = new InlineLabel(constants.unable_retrieve_db());
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
		InlineLabel lbl = new InlineLabel(constants.history_1());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel(constants.dementia());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		//lbl2.getElement().getStyle().setProperty("marginLeft", "300%");
		
		lbl2.setStyleName("pos9");
		///String title = "Dementia is an umbrella term used to describe a number of conditions that are progressive and largely affect older people. Many different diseases can produce the symptoms of dementia. The most common types of dementia are Alzheimer's disease and Vascular Dementia. Other less common types include Lewy Bodies Disease (LBD), Frontotemporal dementia (FTD) and Korsakoff's Disease";
	
		family.add(lbl);
		family.add(lbl2);
		family.add(getInfoLogo(constants.dementia_popup()));
		HorizontalPanel horPanel = new HorizontalPanel();
		motherDementiaYes = new RadioButton("motherDementia", constants.yes());
		motherDementiaNo = new RadioButton("motherDementia", constants.no());
		motherDementiaDontKnow = new RadioButton("motherDementia", constants.dontknow());
		
		
		//motherDementiaYes.getElement().getStyle().setProperty("marginLeft", "238px");
		motherDementiaYes.setStyleName("pos10");
		//motherDementiaNo.getElement().getStyle().setProperty("marginLeft", "55px");
		motherDementiaNo.setStyleName("pos7");
		///motherDementiaDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		motherDementiaDontKnow.setStyleName("pos11");
		
		horPanel.add(motherDementiaYes);
		horPanel.add(motherDementiaNo);
		horPanel.add(motherDementiaDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private HorizontalPanel setupMotherQuestionsTwo() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel(constants.history_2());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel(constants.cardio());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.setStyleName("pos9");
		
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "325px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle(constants.heart_popup());
		
		family.add(lbl);
		family.add(lbl2);
		family.add(logo);
		HorizontalPanel horPanel = new HorizontalPanel();
		motherCardioYes = new RadioButton("motherCardio", constants.yes());
		motherCardioNo = new RadioButton("motherCardio", constants.no());
		motherCardioDontKnow = new RadioButton("motherCardio", constants.dontknow());
		
		
		//motherCardioYes.getElement().getStyle().setProperty("marginLeft", "148px");
		motherCardioYes.setStyleName("pos10");
		//motherCardioNo.getElement().getStyle().setProperty("marginLeft", "55px");
		motherCardioNo.setStyleName("pos7");
		//motherCardioDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		motherCardioDontKnow.setStyleName("pos11");
		
		horPanel.add(motherCardioYes);
		horPanel.add(motherCardioNo);
		horPanel.add(motherCardioDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	private HorizontalPanel setupMotherQuestionsThree() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel(constants.history_3());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel(constants.rf_diabetes());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		//lbl2.getElement().getStyle().setProperty("marginLeft", "13pc");
		lbl2.setStyleName("pos9");
		
		family.add(lbl);
		family.add(lbl2);
	
		HorizontalPanel horPanel = new HorizontalPanel();
		motherMellitusYes = new RadioButton("motherMellitus", constants.yes());
		motherMellitusNo = new RadioButton("motherMellitus", constants.no());
		motherMellitusDontKnow = new RadioButton("motherMellitus", constants.dontknow());
		
		
		//motherMellitusYes.getElement().getStyle().setProperty("marginLeft", "280px");
		motherMellitusYes.setStyleName("pos10");
		//motherMellitusNo.getElement().getStyle().setProperty("marginLeft", "55px");
		motherMellitusNo.setStyleName("pos7");
		//motherMellitusDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		motherMellitusDontKnow.setStyleName("pos11");
		
		horPanel.add(motherMellitusYes);
		horPanel.add(motherMellitusNo);
		horPanel.add(motherMellitusDontKnow);
		family.add(horPanel);
		
		return family;
	}
		
		private HorizontalPanel setupFatherQuestionsOne() {
			HorizontalPanel family = new HorizontalPanel();
			InlineLabel lbl = new InlineLabel(constants.history_10());
			lbl.getElement().getStyle().setProperty("fontWeight", "bold");
			InlineLabel lbl2 = new InlineLabel(constants.dementia());
			lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
			//lbl2.getElement().getStyle().setProperty("marginLeft", "195px");
			lbl2.setStyleName("pos9");
			Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
			logo.getElement().getStyle().setProperty("height", "25px");
			logo.getElement().getStyle().setProperty("marginLeft", "260px");
			logo.getElement().getStyle().setProperty("width", "25px");
			logo.setTitle(constants.dementia_popup());
			
			family.add(lbl);
			family.add(lbl2);
			family.add(logo);
			HorizontalPanel horPanel = new HorizontalPanel();
			fatherDementiaYes = new RadioButton("fatherDementia", constants.yes());
			fatherDementiaNo = new RadioButton("fatherDementia", constants.no());
			fatherDementiaDontKnow = new RadioButton("fatherDementia", constants.dontknow());
			
			
			//fatherDementiaYes.getElement().getStyle().setProperty("marginLeft", "238px");
			fatherDementiaYes.setStyleName("pos10");
			//fatherDementiaNo.getElement().getStyle().setProperty("marginLeft", "55px");
			fatherDementiaNo.setStyleName("pos7");
			//fatherDementiaDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");	
			fatherDementiaDontKnow.setStyleName("pos11");
			
			horPanel.add(fatherDementiaYes);
			horPanel.add(fatherDementiaNo);
			horPanel.add(fatherDementiaDontKnow);
			family.add(horPanel);
			
			return family;
		}
		
	
	
	private HorizontalPanel setupFatherQuestionsTwo() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel(constants.history_4());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel(constants.cardio());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.setStyleName("pos9");

		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "325px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle(constants.heart_popup());
		
		family.add(lbl);
		family.add(lbl2);
		family.add(logo);
		HorizontalPanel horPanel = new HorizontalPanel();
		fatherCardioYes = new RadioButton("fatherCardio", constants.yes());
		fatherCardioNo =  new RadioButton("fatherCardio", constants.no());
		fatherCardioDontKnow = new RadioButton("fatherCardio", constants.dontknow());
		
		
		//fatherCardioYes.getElement().getStyle().setProperty("marginLeft", "148px");
		fatherCardioYes.setStyleName("pos10");
		//fatherCardioNo.getElement().getStyle().setProperty("marginLeft", "55px");
		fatherCardioNo.setStyleName("pos7");
		//fatherCardioDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		fatherCardioDontKnow.setStyleName("pos11");
		horPanel.add(fatherCardioYes);
		horPanel.add(fatherCardioNo);
		horPanel.add(fatherCardioDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	private HorizontalPanel setupFatherQuestionsThree() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel(constants.history_5());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel(constants.rf_diabetes());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		//lbl2.getElement().getStyle().setProperty("marginLeft", "215px");
		lbl2.setStyleName("pos9");
		
		family.add(lbl);
		family.add(lbl2);
	
		HorizontalPanel horPanel = new HorizontalPanel();
		fatherMellitusYes = new RadioButton("fatherMellitus", constants.yes());
		fatherMellitusNo = new RadioButton("fatherMellitus", constants.no());
		fatherMellitusDontKnow = new RadioButton("fatherMellitus", constants.dontknow());
		
		
		//fatherMellitusYes.getElement().getStyle().setProperty("marginLeft", "280px");
		fatherMellitusYes.setStyleName("pos10");
		//fatherMellitusNo.getElement().getStyle().setProperty("marginLeft", "55px");
		fatherMellitusNo.setStyleName("pos7");
		
		//fatherMellitusDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		fatherMellitusDontKnow.setStyleName("pos11");
		horPanel.add(fatherMellitusYes);
		horPanel.add(fatherMellitusNo);
		horPanel.add(fatherMellitusDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private HorizontalPanel setupSiblingQuestionsOne() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel(constants.history_6());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel(constants.dementia());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		//lbl2.getElement().getStyle().setProperty("marginLeft", "130px");
		lbl2.setStyleName("pos9");
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "210px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle(constants.dementia_popup());
		
		family.add(lbl);
		family.add(lbl2);
		family.add(logo);
		HorizontalPanel horPanel = new HorizontalPanel();
		siblingDementiaYes = new RadioButton("siblingDementia", constants.yes());
		siblingDementiaNo = new RadioButton("siblingDementia", constants.no());
		siblingDementiaDontKnow = new RadioButton("siblingDementia", constants.dontknow());
		
		
		//siblingDementiaYes.getElement().getStyle().setProperty("marginLeft", "238px");
		siblingDementiaYes.setStyleName("pos10");
		//siblingDementiaNo.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingDementiaNo.setStyleName("pos7");
		//siblingDementiaDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingDementiaDontKnow.setStyleName("pos11");
		horPanel.add(siblingDementiaYes);
		horPanel.add(siblingDementiaNo);
		horPanel.add(siblingDementiaDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private HorizontalPanel setupSiblingQuestionsTwo() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel(constants.history_7());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel(constants.cardio());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.setStyleName("pos9");
		
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "263px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle(constants.heart_popup());
		
		family.add(lbl);
		family.add(lbl2);
		family.add(logo);
		HorizontalPanel horPanel = new HorizontalPanel();
		siblingCardioYes = new RadioButton("sibling", constants.yes());
		siblingCardioNo = new RadioButton("siblingCardio", constants.no());
		siblingCardioDontKnow = new RadioButton("siblingCardio", constants.dontknow());
		
		
		//siblingCardioYes.getElement().getStyle().setProperty("marginLeft", "146px");
		siblingCardioYes.setStyleName("pos10");
		//siblingCardioNo.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingCardioNo.setStyleName("pos7");
		//siblingCardioDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingCardioDontKnow.setStyleName("pos11");
		
		horPanel.add(siblingCardioYes);
		horPanel.add(siblingCardioNo);
		horPanel.add(siblingCardioDontKnow);
		family.add(horPanel);
		
		return family;
	}
	
	
	private HorizontalPanel setupSiblingQuestionsThree() {
		HorizontalPanel family = new HorizontalPanel();
		InlineLabel lbl = new InlineLabel(constants.history_9());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl2 = new InlineLabel(constants.rf_diabetes());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		//lbl2.getElement().getStyle().setProperty("marginLeft", "203px");
		lbl2.setStyleName("pos9");
		
		family.add(lbl);
		family.add(lbl2);
	
		HorizontalPanel horPanel = new HorizontalPanel();
		siblingMellitusYes = new RadioButton("siblingMellitus", constants.yes());
		siblingMellitusNo = new RadioButton("siblingMellitus", constants.no());
		siblingMellitusDontKnow = new RadioButton("siblingMellitus", constants.dontknow());
		
		
		//siblingMellitusYes.getElement().getStyle().setProperty("marginLeft", "277px");
		siblingMellitusYes.setStyleName("pos10");
		//siblingMellitusNo.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingMellitusNo.setStyleName("pos7");
		//siblingMellitusDontKnow.getElement().getStyle().setProperty("marginLeft", "55px");
		siblingMellitusDontKnow.setStyleName("pos11");
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
		popup.setPopupPosition(290,280);
		popup.setWidth("700px");
		popup.show();

	}
	private Image getInfoLogo(String title) {
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "260px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle(title);
		return logo;
	}
	
	
}

	
	