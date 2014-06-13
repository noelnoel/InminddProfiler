package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DefaultLocalizedNames;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.User;

public class PatientInfo {
	private FlowPanel patient;
	private FlowPanel patientPanel;
	private ScrollPanel scroll = new ScrollPanel();
	private DataField age;
	private RadioButton male;
	private RadioButton female;
	private FlowPanel sex = new FlowPanel();
	private InlineLabel error;
	private ListBox country = new ListBox();
	private ListBox marital = new ListBox();
	private ListBox livingArrangements = new ListBox();
	private ListBox educationLevel = new ListBox();
	
	private ListBox occupation = new ListBox();
	private ListBox employmentStatus = new ListBox();
	private User user;
	private Login login;
	private Patient currentPatient;
	
	private InminddServiceAsync InminddServiceSvc;
	public void PatientPanel(){
		
	}
	
	public FlowPanel setupPatientPanel(Login login ) {
		this.login = login;
	   	HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				"About You</h1>");
		
		HTMLPanel header = new HTMLPanel("<h3>" +
				"We would like to ask you for some background information about yourself</h3>");
		header.getElement().getStyle().setProperty("textDecoration", "underline");
	
		 
	   
		Label lbl = new Label("We would like to ask you some background information about yourself");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		
		patientPanel = new FlowPanel();
	
	
		patientPanel.setWidth("100%");
		patientPanel.add(mainHeader);
		patientPanel.add(header);
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		FlowPanel flp = new FlowPanel();
		Button prev = new Button("Retrieve previous data ?");
		

		// Listen for mouse events on the preious button.
		prev.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getPatientData();
			}
		});
		    
		patientPanel.add(prev);
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		age = new DataField("What age are you ? ");
		
		age.getElement().getStyle().setProperty("fontWeight", "bold");
		flp.add(age);
		flp.add(new HTMLPanel("<span>  <br>  </span>"));
		flp.add(getGender());
		patientPanel.add(flp);
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		patientPanel.add(getCountryOfBirth());
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		patientPanel.add(getMaritalStatus());
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		patientPanel.add( getLivingArrangements());
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		patientPanel.add(getEducation());
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		patientPanel.add(getOccupation());
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		patientPanel.add(getEmployment());
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		Button btn = new Button("submit");
		
		 // Listen for mouse events on the submit button.
	    btn.addClickHandler(new ClickHandler() {
	      @Override
		public void onClick(ClickEvent event) {
	        checkInput();
	      }
	    });

	 
		patientPanel.add(btn);
		
		scroll.setSize("100%", "70%");
		scroll.add(patientPanel);
		scroll.setAlwaysShowScrollBars(true);
		patient = new FlowPanel();
		patient.add(scroll);
	    // check if patient info already exists in database
		
			return patient;
		
	}
	
	
	private FlowPanel getGender() {
		
		InlineLabel theQuestion = new InlineLabel("What is your sex ?");
		theQuestion.getElement().getStyle().setProperty("fontWeight", "bold");
	//	theQuestion.getElement().getStyle().setProperty("marginLeft", "10px");
		male = new RadioButton("radioGroup", "Male");
	    female = new RadioButton("radioGroup", "Female");
	   
	    sex.add(theQuestion);
	    sex.add(male);
	    sex.add(female);
	    sex.setWidth("100%");	   
	    return sex;
		
	}
	
	private FlowPanel  getCountryOfBirth() {
		FlowPanel countryBirth  = new FlowPanel();
		
		InlineLabel theSelection = new InlineLabel("What is your country of birth ? ");
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		DefaultLocalizedNames loc = new DefaultLocalizedNames(); 
		country.addItem("Please select one");
         for (int i=0; i<loc.getSortedRegionCodes().length; i++) { 
                 String code = loc.getSortedRegionCodes()[i]; 
                 country.addItem(loc.getRegionName(code), code); 

         } 
         countryBirth.add(theSelection);
         countryBirth.add(country);
         countryBirth.setWidth("100%");
 	
 	     return countryBirth;
	}

	private FlowPanel getMaritalStatus() {
		FlowPanel maritalStatus = new FlowPanel();		
		InlineLabel theSelection = new InlineLabel("What is your martial status ? ");
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		marital.addItem("Please select one");
        marital.addItem("Single (Never married)");
        marital.addItem("Married (First marriage)");
        marital.addItem("In a civil partnership)");
        marital.addItem("Cohabiting with a partner");
        marital.addItem("Remarried (Following widowhood)");
        marital.addItem("Married (Following divorce/annulment)");
        marital.addItem("Divorced");
        marital.addItem("Separated");
        marital.addItem("Widowed");
        maritalStatus.add(theSelection);
        maritalStatus.add(marital);
        
        maritalStatus.setWidth("100%");
        
		return maritalStatus;
	}
	
	private FlowPanel getLivingArrangements() {
		FlowPanel living = new FlowPanel();
		
		InlineLabel theSelection = new InlineLabel("Which of the following best describes your current living arrangements ? ");
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
	
		livingArrangements.addItem("Please select one");
		livingArrangements.addItem("Living alone");
		livingArrangements.addItem("Living with family members(i.e. spouse/partner and/or children/grandchildren)");
		livingArrangements.addItem("Living with another relative (other than spouse/partner or children/grandchildren");
		livingArrangements.addItem("Living with unrelated people only, apart from spouse/partner");		
		livingArrangements.addItem("Other, Please specifiy :");		
		
	      
		living.add(theSelection);
		living.add(livingArrangements);
		
		living.setWidth("100%");
	
		return living;
	} 
	
	private FlowPanel getEducation() {
		FlowPanel education = new FlowPanel();
		
		InlineLabel theSelection = new InlineLabel("What is the highest level of education that you have completed to date ? ");
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		
		educationLevel.addItem("Please select one");
		educationLevel.addItem("No formal education or training");
		educationLevel.addItem("Primary Education");
		educationLevel.addItem("Lower Secondary");
		educationLevel.addItem("Upper Secondary");
		educationLevel.addItem("Technical or Vocational");
		educationLevel.addItem("Higher Certificate");
		educationLevel.addItem("Ordinary degree or higher diploma");
		educationLevel.addItem("Honour Bachelor degree/professional qualification or both");
		educationLevel.addItem("Postgraduate diploma or degree");
		educationLevel.addItem("Doctorate (PhD) or higher");
		   
		education.add(theSelection);
		education.add(educationLevel);
			
		education.setWidth("100%");

		        
		return education;
	} 
       
	private FlowPanel getOccupation() {
		FlowPanel occupationPanel = new FlowPanel();
	
		InlineLabel theSelection = new InlineLabel("Which of the following best describes your current occupation ? ");
		
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
	
		occupation.addItem("Please select one");
		occupation.addItem("Manager");
        
		occupation.addItem("Professional");
		occupation.addItem("Technician");		
		occupation.addItem("Clerical");
		occupation.addItem("Services");
		occupation.addItem("Skilled Agricultural");
		occupation.addItem("Craft");		
		occupation.addItem("Plant");
		occupation.addItem("Elementary");
		
		
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(1)).setTitle("e.g managing director, senior government official, hotel or restaurant manager, etc.");
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(2)).setTitle("e.g medical doctor, teacher, engineer, artist, accountant, etc.");
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(3)).setTitle("e.g engineering technician, photographer, ICT operations technician, etc.");
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(4)).setTitle("e.g receptionist, office supervisor, clerical worker, etc.");
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(5)).setTitle("e.g shopkeeper, chef, child care worker, hairdresser, waitress, etc.");
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(6)).setTitle("e.g forestry worker, vegetable grower, farmer, etc.");
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(7)).setTitle("e.g carpenter, electrician, builder, jewellery maker, baker, plumber, etc.");
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(8)).setTitle("e.g machine operator, van driver, etc.");
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(9)).setTitle("e.g cleaner, farm labourer, building construction labourer, etc.");
		
		occupationPanel.add(theSelection);
		occupationPanel.add(occupation);
			
		occupationPanel.setWidth("100%");
	
		return occupationPanel;
	} 
       
         
	private FlowPanel getEmployment() {
		FlowPanel employment = new FlowPanel();
	
		InlineLabel theSelection = new InlineLabel("Which of the following best describes your current main employment status?");
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
	
		employmentStatus.addItem("Please select one");
		employmentStatus.addItem("Paid employment");
		employmentStatus.addItem("Looking for first regular job");
		employmentStatus.addItem("Unemployed");
		employmentStatus.addItem("Student");
		employmentStatus.addItem("Looking after home & family");
		employmentStatus.addItem("Retired from employment");
		employmentStatus.addItem("Unable to  work  due  to  permanent illness or  disability");
		employmentStatus.addItem("Other, Please specify");
		employment.add(theSelection);
		employment.add(employmentStatus);
			
		employment.setWidth("100%");
	
		return employment;
	} 
       
	/**
	 * Validate the fields input by the  participant
	 * 
	 */
	private void checkInput() {
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error);
			return;			
		}
		if (!checkAge())
			return;
		if (!checkGender()) 
			return;
		if (!checkCountryOfBirth())
			return;
		if (!checkMaritalStatus())
			return;
		if (!checkLivingArrangements())
			return;
	
		if (!checkEducationLevel())
			return;
		if (!checkOccupation())
			return;
		if (!checkEmploymentStatus())
			return;
		updatePatientDB();
	}  

	private boolean  checkAge() {
		int patientAge;
		try {
			patientAge  = Integer.parseInt(age.getText());	
		}

		catch (Exception e)
		{
			error = new InlineLabel("Invalid or non existant  input - Please re-enter");	
			showErrorPopupPanel(error);
			age.getElement().getStyle().setProperty("color", "red");	
			return false;
		}
		error = new InlineLabel("Please re-enter your age which  should be between 40 & 60");	

		age.getElement().getStyle().setProperty("color", "black");		 
		if (patientAge < 40 || patientAge > 60) {			   
			showErrorPopupPanel(error);
			age.getElement().getStyle().setProperty("color", "red");	 
			return false;		  			
		}

		return  true;
	}

	

	private boolean checkGender() {
		// either one checked
		sex.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel("Please select one of the gender buttons");	
		if ( !(male.getValue() || female.getValue())) {
			showErrorPopupPanel(error);
			sex.getElement().getStyle().setProperty("color", "red");			  
			return false;
		}
		return true;
	}

	private boolean checkCountryOfBirth() {
		country.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel("Please select your country of birth");
		if (country.getSelectedIndex() <= 0) {
			showErrorPopupPanel(error);
			country.getElement().getStyle().setProperty("color", "red");
			return false;			   
		}
		return  true;
	}

	private boolean checkMaritalStatus() {
		marital.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel("Please select your marital status");
		if (marital.getSelectedIndex() <= 0) {
			showErrorPopupPanel(error);
			marital.getElement().getStyle().setProperty("color", "red");
			return false;		   
		}
		return true;
	}
	
	private boolean checkLivingArrangements() {
		livingArrangements.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel("Please select your living arrangements");
		if (livingArrangements.getSelectedIndex() <= 0) {
			showErrorPopupPanel(error);
			livingArrangements.getElement().getStyle().setProperty("color", "red");
			return false;		   
		}
		return true;
	}
	
	private boolean checkOccupation() {
		occupation.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel("Please select your current occupation");
		if (occupation.getSelectedIndex() <= 0) {
			showErrorPopupPanel(error);
			occupation.getElement().getStyle().setProperty("color", "red");
			return false;		   
		}
		return true;
	}

	private boolean checkEducationLevel() {
		educationLevel.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel("Please select your education level");
		if (educationLevel.getSelectedIndex() <= 0) {
			showErrorPopupPanel(error);
			educationLevel.getElement().getStyle().setProperty("color", "red");
			return false;		   
		}
		return true;
	}

	
	private boolean checkEmploymentStatus() {
		employmentStatus.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel("Please select your employment status");
		if (employmentStatus.getSelectedIndex() <= 0) {
			showErrorPopupPanel(error);
			employmentStatus.getElement().getStyle().setProperty("color", "red");
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
	 
	 private void updatePatientDB() {
		 Patient patient = createPatient();
		 callServiceSetup();
		 
		 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
			 @Override	 
            public void onSuccess(Boolean result) {
            		if ((result == false)){	            		
            			InlineLabel error = new InlineLabel("Patient Data not updated !!!!!");
            			showErrorPopupPanel(error, "red");            			            			
            		}
            		else {
            			InlineLabel error = new InlineLabel("Patient Data  updated. proceed to next panel");
            			showErrorPopupPanel(error, "green");  
            			Window.Location.reload();  // This is where the page relooad is triggered
            		}
                 
              }
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel("Invalid User Id or Password  - please reenter");
    			showErrorPopupPanel(error);			
				
			}
		  };
		  
    	  InminddServiceSvc.updatePatientInfo(patient, callback);
	 }
    	 
	
	

	 private Patient createPatient() {
		 Patient patient = new Patient();
		 User user = login.getUser();
		 patient.setUserId(user.getUserId());
		 patient.setAge(Integer.parseInt(age.getText()));
		
		 if (male.getValue()) 
			 patient.setGender("male");
		 else patient.setGender("female");
		 int index = country.getSelectedIndex();
		 patient.setCountryOfBirth(country.getItemText(index));
		 index = marital.getSelectedIndex();
		 patient.setMaritalStatus(marital.getItemText(index));
		 index = livingArrangements.getSelectedIndex();
		 patient.setLivingArrangements(livingArrangements.getItemText(index));
		 index = occupation.getSelectedIndex();
		 patient.setOccupationalGroup(occupation.getItemText(index));
		 index = educationLevel.getSelectedIndex();
		 patient.setEducationLevel(educationLevel.getItemText(index));
		 index = employmentStatus.getSelectedIndex();
		 patient.setEmploymentStatus(employmentStatus.getItemText(index));
		 return  patient;
	 }
	 
	 private void getPatientData() {				
		 User user = login.getUser();
		 if (user== null) {

			 InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			 showErrorPopupPanel(error);
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<Patient> callback =  new AsyncCallback<Patient>(){

			 @Override	 
			 public void onSuccess(Patient patient) {
				 if ((patient.getAge() == 0)){	            		
					 InlineLabel error = new InlineLabel("Patient Data not retrieved. No data available for this patient ");
					 showErrorPopupPanel(error);            			
				 }            		
				 else {
					 InlineLabel error = new InlineLabel("Patient data retrieved- Edit as necessary");
					 showErrorPopupPanel(error, "green");  
					 populatePanel(patient);
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 InlineLabel error = new InlineLabel("Invalid User Id or Password  - please reenter or check your caps lock");
				 showErrorPopupPanel(error);			

			 }
		 };

		 InminddServiceSvc.queryPatientInfo(user, callback);
		 return;
	 }

	 
	 private void populatePanel(Patient patient) {
		 
		 int index = 0;
		 String patientValue = "";
		 int itemCount = 0;
		 age.setText(Integer.toString(patient.getAge()));
		
		 
		
		 if (patient.getGender().equals("male")) {
			 male.setValue(true);
		 }
		 else {
			 female.setValue(true);		
		 }
		 patientValue = patient.getCountryOfBirth();
		 itemCount = country.getItemCount();
		 for (index = 0; index < itemCount; index++) {
			 if ( country.getItemText(index).equals(patientValue)) {
				 country.setSelectedIndex(index);
				 break;
			 }
		 }
		
		 patientValue = patient.getMaritalStatus();
		 itemCount = marital.getItemCount();
		 for (index = 0; index < itemCount; index++) {
			 if ( marital.getItemText(index).equals(patientValue)) {
				 marital.setSelectedIndex(index);
				 break;
			 }
		 }
		 
		 patientValue = patient.getLivingArrangements();
		 itemCount = livingArrangements.getItemCount();
		 for (index = 0; index < itemCount; index++) {
			 if ( livingArrangements.getItemText(index).equals(patientValue)) {
				 livingArrangements.setSelectedIndex(index);
				 break;
			 }
		 }
		 
		 patientValue = patient.getEducationLevel();
		 itemCount = educationLevel.getItemCount();
		 for (index = 0; index < itemCount; index++) {
			 if ( educationLevel.getItemText(index).equals(patientValue)) {
				 educationLevel.setSelectedIndex(index);
				 break;
			 }
		 }
		 
		 patientValue = patient.getOccupationalGroup();
		 itemCount = occupation.getItemCount();
		 for (index = 0; index < itemCount; index++) {
			 if ( occupation.getItemText(index).equals(patientValue)) {
				 occupation.setSelectedIndex(index);
				 break;
			 }
		 }
		 
		 patientValue = patient.getEmploymentStatus();
		 itemCount = employmentStatus.getItemCount();
		 for (index = 0; index < itemCount; index++) {
			 if ( employmentStatus.getItemText(index).equals(patientValue)) {
				 employmentStatus.setSelectedIndex(index);
				 break;
			 }
		 }
			 
	 }
	 
 public void clearPanel() {


	 Window.Location.reload();
 }
		
		 
		 
	

	 
	 private void showErrorPopupPanel(InlineLabel error) {
			PopupPanel popup = new PopupPanel(true, true);			

			popup.setTitle("Error");
			VerticalPanel vertPanel = new VerticalPanel();
			error.getElement().getStyle().setProperty("color", "red");
			error.getElement().getStyle().setProperty("fontWeight", "bold");
			error.getElement().getStyle().setProperty("marginLeft", "25px");


			vertPanel.add(error);
			popup.setWidget(vertPanel);
			//popup.setGlassEnabled(true);
			popup.setPopupPosition(190,645);
			popup.setWidth("550px");
			popup.show();

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
			popup.setPopupPosition(190,645);
			popup.setWidth("550px");
			popup.show();

		}
}
