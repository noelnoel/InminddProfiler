package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DefaultLocalizedNames;
//import com.google.gwt.user.client.Element;
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
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.dom.client.Element;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.User;

public class PatientInfo {
	
	TabLayoutPanel content;
	private FlowPanel patient;
	private FlowPanel patientPanel;
	private ScrollPanel scroll = new ScrollPanel();
	public DataField age;

	public RadioButton male;
	public RadioButton female;
	public  FlowPanel sex = new FlowPanel();
	private InlineLabel error;
	public ListBox country = new ListBox();
	public ListBox marital = new ListBox();
	public ListBox livingArrangements = new ListBox();
	public TextBox otherLivingArrangements = new TextBox();
	public ListBox educationLevelIE = new ListBox();
	public ListBox educationLevelSC = new ListBox();
	public ListBox educationLevelNL = new ListBox();
	public ListBox educationLevelFR = new ListBox();
	public final ListBox countryEducated = new ListBox();
	
	public ListBox occupation = new ListBox();
	public ListBox employmentStatus = new ListBox();
	public TextBox otherEmploymentStatus;
	private User user;
	private Login login;
	public static PatientInfo lastinstance;

	private InminddServiceAsync InminddServiceSvc;
	
	static final int DECK_LOGIN = 0;
	static final int DECK_PATIENT = 1;
	static final int DECK_CESD = 2;	
	static final int DECK_MEDICAL = 3;	
	static final int DECK_HISTORY = 4;
	static final int DECK_PHYSICAL = 5;
	static final int DECK_COGNITIVE1 = 6;
	static final int DECK_COGNITIVE2 = 7;
	static final int DECK_SMOKE_ALCOHOL = 8;
	static final int DECK_DIET = 9;
	
	static InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	public  PatientInfo(){
		lastinstance = this;
	}
	
	public void setContent(TabLayoutPanel content) {
		this.content = content;
	}
	
	public static void clearInputs() {
		lastinstance.female.setValue(false);
		lastinstance.male.setValue(false);
		lastinstance.age.setText("");
		lastinstance.country.setSelectedIndex(0);
		lastinstance.livingArrangements.setSelectedIndex(0);
		lastinstance.otherLivingArrangements.setText("");
		lastinstance.otherEmploymentStatus.setText("");
		lastinstance.educationLevelIE.setSelectedIndex(0);
		lastinstance.educationLevelSC.setSelectedIndex(0);
		lastinstance.educationLevelNL.setSelectedIndex(0);
		lastinstance.educationLevelFR.setSelectedIndex(0);
		lastinstance.countryEducated.setSelectedIndex(0);
		lastinstance.occupation.setSelectedIndex(0);
		lastinstance.employmentStatus.setSelectedIndex(0);
		lastinstance.marital.setSelectedIndex(0);
	}
	public FlowPanel setupPatientPanel(Login login ) {
		this.login = login;
		String text = constants.patient();
	   	HTMLPanel mainHeader = new HTMLPanel("<h1>" + text + "</h1>");
				
		text = constants.yourself();
		HTMLPanel header = new HTMLPanel("<h3>" + text + "</h3>");
		header.getElement().getStyle().setProperty("textDecoration", "underline");	
		
		patientPanel = new FlowPanel();	
	
		patientPanel.setWidth("100%");
		patientPanel.add(mainHeader);
		patientPanel.add(header);
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		FlowPanel flp = new FlowPanel();
		Button prev = new Button(constants.review());
		
	
		// Listen for mouse events on the review button.
		prev.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getPatientData();
			}
		});
		    
		patientPanel.add(prev);
		text = constants.age();
		String text_2 = constants.year();
		patientPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		age = new DataField(text, text_2);
		
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
		
		patientPanel.add(getCountryEducated());
		InlineLabel theSelection = new InlineLabel(constants.educationlevel());
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		patientPanel.add(theSelection);
		patientPanel.add(getEducationIE());
		patientPanel.add(getEducationSC());
		patientPanel.add(getEducationNL());
		patientPanel.add(getEducationFR());
		
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
		
		InlineLabel theQuestion = new InlineLabel(constants.sex());
		theQuestion.getElement().getStyle().setProperty("fontWeight", "bold");
	//	theQuestion.getElement().getStyle().setProperty("marginLeft", "10px");
		male = new RadioButton("radioGroup", constants.male());
	    female = new RadioButton("radioGroup", constants.female());
	   
	    sex.add(theQuestion);
	    sex.add(male);
	    sex.add(female);
	    sex.setWidth("100%");	   
	    return sex;
		
	}
	
	private FlowPanel  getCountryOfBirth() {
		FlowPanel countryBirth  = new FlowPanel();
		
		InlineLabel theSelection = new InlineLabel(constants.cob());
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		DefaultLocalizedNames loc = new DefaultLocalizedNames(); 
		country.addItem(constants.select_one());
        // for (int i=0; i<loc.getSortedRegionCodes().length; i++) { 
       //          String code = loc.getSortedRegionCodes()[i]; 
        //         country.addItem(loc.getRegionName(code), code); 

       //  } 
		country.addItem("Scotland: Schotland, Ecosse");
		country.addItem("Ireland:  Ierland, Irelande");
		country.addItem("The Netherlands: Nederland, Pays-Bas");
		country.addItem("France: Frankrijk, France");
		country.addItem("Other: Anders, Autre");
		
         countryBirth.add(theSelection);
         countryBirth.add(country);
         countryBirth.setWidth("100%");
 	
 	     return countryBirth;
	}

	private FlowPanel getMaritalStatus() {
		FlowPanel maritalStatus = new FlowPanel();		
		InlineLabel theSelection = new InlineLabel(constants.marital());
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		marital.addItem(constants.select_one());
        marital.addItem(constants.single());
        marital.addItem(constants.married());
        marital.addItem(constants.civil());
        marital.addItem(constants.cohab());
        marital.addItem(constants.remarried());
        marital.addItem(constants.marr());
        marital.addItem(constants.divorced());
        marital.addItem(constants.seperated());
        marital.addItem(constants.widowed());
        maritalStatus.add(theSelection);
        maritalStatus.add(marital);
        
        maritalStatus.setWidth("100%");
        
		return maritalStatus;
	}
	
	private FlowPanel getLivingArrangements() {
		FlowPanel living = new FlowPanel();
		
		InlineLabel theSelection = new InlineLabel(constants.living());
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
	   
	    otherLivingArrangements.getElement().getStyle().setProperty("marginLeft", "10px");
	    final InlineLabel please = new InlineLabel(constants.please());
	    please.getElement().getStyle().setProperty("marginLeft", "20px");
	    please.getElement().getStyle().setProperty("fontWeight", "bold");
	    please.setVisible(false);
	    otherLivingArrangements.setVisible(false);
		livingArrangements.addItem(constants.select_one());
		livingArrangements.addItem(constants.livingarrangement1());
		livingArrangements.addItem(constants.livingarrangement2());
		livingArrangements.addItem(constants.livingarrangement3());
		livingArrangements.addItem(constants.livingarrangement4());		
		livingArrangements.addItem(constants.livingarrangement5());		
		
	      
		living.add(theSelection);
		living.add(livingArrangements);
		living.add(please);
		living.add(otherLivingArrangements);
		living.setWidth("100%");
	    livingArrangements.addChangeHandler(new ChangeHandler() {
	    	public void onChange(ChangeEvent event) {
	    		int selectedIndex = livingArrangements.getSelectedIndex();
	    		if (selectedIndex == 5){
	    			please.setVisible(true);
	    			otherLivingArrangements.setVisible(true);
	    			otherLivingArrangements.setVisibleLength(35);
	    			otherLivingArrangements.setFocus(true);
	    		}
	    		else if (selectedIndex != 5){
	    			
	    			please.setVisible(false);
	    			otherLivingArrangements.setVisible(false);
	    			
	    		}
	    	}
	    });
		return living;
	} 
	
	private FlowPanel getCountryEducated() {

		FlowPanel country = new FlowPanel();
		InlineLabel theSelection = new InlineLabel("Please select country in which you are based");
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");

		countryEducated.addItem(constants.select_one());
		countryEducated.addItem("Ireland");
		countryEducated.addItem("Scotland");
		countryEducated.addItem("Netherlands");
		countryEducated.addItem("France");
		country.add(theSelection);
		country.add(countryEducated);
		countryEducated.addChangeHandler(new ChangeHandler() 
			{ public void onChange(ChangeEvent event)
			{int selectedIndex = countryEducated.getSelectedIndex();
			if (selectedIndex == 2)
			{
				educationLevelSC.setVisible(true);
				educationLevelIE.setVisible(false);
				educationLevelNL.setVisible(false);
				educationLevelFR.setVisible(false);
			}
			if (selectedIndex == 1)	{
				educationLevelIE.setVisible(true);
				educationLevelSC.setVisible(false);
				educationLevelNL.setVisible(false);
				educationLevelFR.setVisible(false);
			}
	
			if (selectedIndex == 3)	{
				educationLevelNL.setVisible(true);
				educationLevelSC.setVisible(false);
				educationLevelIE.setVisible(false);
				educationLevelFR.setVisible(false);
			}
			if (selectedIndex == 4)	{
				educationLevelFR.setVisible(true);
				educationLevelSC.setVisible(false);
				educationLevelIE.setVisible(false);
				educationLevelNL.setVisible(false);
			}
			}
			});

		return country;
	}

	private FlowPanel getEducationIE() {
		
		
		FlowPanel education = new FlowPanel();
		
		//InlineLabel theSelection = new InlineLabel(constants.educationlevel());
		//theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		
		educationLevelIE.addItem(constants.select_one());
		educationLevelIE.addItem("No formal education or training");
		educationLevelIE.addItem("Primary Education");
		educationLevelIE.addItem("Lower Secondary");
		educationLevelIE.addItem("Upper Secondary");
		educationLevelIE.addItem("Technical or Vocational");
		educationLevelIE.addItem("Advanced Certificate/Completed Apprenticeship");
		educationLevelIE.addItem("Higher Certificate");
		educationLevelIE.addItem("Ordinary degree or higher diploma");
		educationLevelIE.addItem("Honour Bachelor degree/professional qualification or both");
		educationLevelIE.addItem("Postgraduate diploma or degree");
		educationLevelIE.addItem("Doctorate (PhD) or higher");
		educationLevelIE.setVisible(false);  
		//education.add(theSelection);
		
		Element.as(educationLevelIE.getElement().getChild(2)).setTitle("NFQ Levels 1 or 2"
				+ "\nFETAC Level 1 or 2 Certificate");
		Element.as(educationLevelIE.getElement().getChild(3)).setTitle("NFQ Levels 3"
				+ "\nJunior/Intermediate/Group Certificate"
				+ "\nFETAC Level 3 Certificate"
				+ "\nFAS Introductory Skills Certificate"
				+ "\nNCVA Foundation Certificate or equivalent");
		Element.as(educationLevelIE.getElement().getChild(4)).setTitle("NFQ Levels 4 or 5"
				+ "\nLeaving Certificate (including Applied and Vocational Programmes");
		Element.as(educationLevelIE.getElement().getChild(5)).setTitle("NFQ Levels 4 or 5"				
				+ "\nFETAC Level 4/5 Certificate"
				+ "\nNCVA Level 1/2"
				+ "\nFAS Specific Skills Certificate"
				+ "\nTeagasc Cert in Agriculture"
				+ "\nCERT Craft Cert or equivalent");
		Element.as(educationLevelIE.getElement().getChild(6)).setTitle("NFQ Level 6"				
				+ "\nFETAC Advanced Certificate"
				+ "\nNCVA Level 3"
				+ "\nFAS National Craft Cert"
				+ "\nTeagasc Farming Certificate"
				+ "\nCERT Professional Cooking Cert or equivalent");
		Element.as(educationLevelIE.getElement().getChild(6)).setTitle("NFQ Level 6"				
				+ "\nFETAC Advanced Certificate"
				+ "\nNCVA Level 3"
				+ "\nFAS National Craft Cert"
				+ "\nTeagasc Farming Certificate"
				+ "\nCERT Professional Cooking Cert or equivalent");
		Element.as(educationLevelIE.getElement().getChild(7)).setTitle("NFQ Level 6"
				+ "\nNCEA.HETAC National Cert or equivalent");
		Element.as(educationLevelIE.getElement().getChild(8)).setTitle("NFQ Level 7");
		Element.as(educationLevelIE.getElement().getChild(9)).setTitle("NFQ Level 8");
		Element.as(educationLevelIE.getElement().getChild(10)).setTitle("NFQ Level 9"
				+ "\nPostgraduate Diploma, Masters degree or equivalent");
		Element.as(educationLevelIE.getElement().getChild(11)).setTitle("NFQ Level 10");
	
		
		
		education.add(educationLevelIE);
			
		education.setWidth("100%");

		        
		return education;
	} 
       
private FlowPanel getEducationSC() {
		
		
		FlowPanel education = new FlowPanel();
		
		InlineLabel theSelection = new InlineLabel(constants.educationlevel());
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		
		educationLevelSC.addItem(constants.select_one());
		educationLevelSC.addItem("No formal education or training");
		educationLevelSC.addItem("Primary Education");
		educationLevelSC.addItem("O Grade, Standard Grade [Includes School Leaving Certificate, GCSE, GCE o Level, CSE, NQ Access 3 Cluster. Intermediate 1, Intermediate 2, Senior Certiciate or equivalent]");
		educationLevelSC.addItem("GNVQ/GSVQ Foundation or Intermediate, SVQ Level 1, SVQ Level 2, SCOTVEC/National Certificate, Module,City and Guilds Craft, Diploma or equivalent");
		educationLevelSC.addItem("Higher Grade, Advanced Higher, CSYS, A Level, AS Level,Advanced Senior Certificate or equivalent");
		educationLevelSC.addItem("GNVQ/GSVQ Advanced, SVQ Level 3,ONC, OND, SCOTVEC National Diploma, City and Guilds Advanced Craft, RSA Advanced or equivalent");
		educationLevelSC.addItem("Post school pre-higher education: HNC,HND,SVQ Level 4, RSA Higher Diploma or equivalent");
		educationLevelSC.addItem("First Degree, Higher degree, SVQ level 5 or equivalent");
		educationLevelSC.addItem("Professional qualifications e.g teaching, accountancy");
		educationLevelSC.addItem("Other school qualifications not already mentioned");
		educationLevelSC.addItem("Other post- but pre-Higher Education qualifications not already mentioned");
		educationLevelSC.addItem("Other higher education qualifications not already mentioned");
		educationLevelSC.setVisible(false);  
		//education.add(theSelection);
		education.add(educationLevelSC);
			
		education.setWidth("100%");

		        
		return education;
	} 
private FlowPanel getEducationNL() {
	
	
	FlowPanel education = new FlowPanel();
	
	//InlineLabel theSelection = new InlineLabel(constants.educationlevel());
	//theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
	
	educationLevelNL.addItem(constants.select_one());
	educationLevelNL.addItem("Geen formeel onderwijs of opleiding");
	educationLevelNL.addItem("Basisschool");
	educationLevelNL.addItem("MAVO of VMBO");
	educationLevelNL.addItem("HAVO");
	educationLevelNL.addItem("VWO");
	educationLevelNL.addItem("Lager beroepsonderwijs");
	educationLevelNL.addItem("Middelbaar beroepsonderwijs");
	educationLevelNL.addItem("HBO");
	educationLevelNL.addItem("Universiteit");
	
	educationLevelNL.setVisible(false);  
	//education.add(theSelection);
	Element.as(educationLevelNL.getElement().getChild(3)).setTitle("Basisberoepsgerichte leerweg"
			+ "\nKaderberoepsgerichte leerweg"
			+ "\nGemengde leerweg"
			+ "\nTheoretische leerweg");
	Element.as(educationLevelNL.getElement().getChild(7)).setTitle("Bachelor"
			+ "\nMaster");
	Element.as(educationLevelNL.getElement().getChild(8)).setTitle("Bachelor"
			+ "\nMaster"
			+ "\nDoctoraat of hoger");
	education.add(educationLevelNL);
		
	education.setWidth("100%");

	        
	return education;
} 
private FlowPanel getEducationFR() {
	
	
	FlowPanel education = new FlowPanel();
	
	//InlineLabel theSelection = new InlineLabel(constants.educationlevel());
	//theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
	
	educationLevelFR.addItem(constants.select_one());
	educationLevelFR.addItem("Aucun éducation formelle ou  formation");
	educationLevelFR.addItem("Enseignement primaire ");
	educationLevelFR.addItem("BEPC, BEP,  CAP");
	educationLevelFR.addItem("Formation technique et professionnelle (ex : brevet de technicien sup : BTS)");
	educationLevelFR.addItem("Baccalauréat général");
	educationLevelFR.addItem("Diplôme d’enseignement supérieur");
	educationLevelFR.addItem("Diplôme de second cycle (DEUG, Licence, Maîtrise)");
	
	educationLevelFR.setVisible(false);  
	//education.add(theSelection);
	education.add(educationLevelFR);
		
	education.setWidth("100%");

	        
	return education;
} 
	private FlowPanel getOccupation() {
		FlowPanel occupationPanel = new FlowPanel();
	
		InlineLabel theSelection = new InlineLabel(constants.occupation());
		
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
	
		occupation.addItem(constants.select_one());
		occupation.addItem(constants.manager());        
		occupation.addItem(constants.professional());
		occupation.addItem(constants.technician());		
		occupation.addItem(constants.clerical());
		occupation.addItem(constants.services());
		occupation.addItem(constants.agricultural());
		occupation.addItem(constants.craft());		
		occupation.addItem(constants.plant());
		occupation.addItem(constants.elementary());
		
		
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(1)).setTitle(constants.managerexample());
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(2)).setTitle(constants.professionalexample());
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(3)).setTitle(constants.technicianexample());
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(4)).setTitle(constants.clericalexample());
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(5)).setTitle(constants.servicesexample());
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(6)).setTitle(constants.agriculturalexample());
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(7)).setTitle(constants.craftexample());
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(8)).setTitle(constants.plantexample());
		com.google.gwt.dom.client.Element.as(occupation.getElement().getChild(9)).setTitle(constants.elementaryexample());
		
		occupationPanel.add(theSelection);
		occupationPanel.add(occupation);
			
		occupationPanel.setWidth("100%");
	
		return occupationPanel;
	} 
       
         
	private FlowPanel getEmployment() {
		FlowPanel employment = new FlowPanel();
	
		InlineLabel theSelection = new InlineLabel(constants.employment());
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		otherEmploymentStatus = new TextBox();
		otherEmploymentStatus.getElement().getStyle().setProperty("marginLeft", "10px");
		final InlineLabel please = new InlineLabel(constants.please());
		please.getElement().getStyle().setProperty("marginLeft", "20px");
		please.getElement().getStyle().setProperty("fontWeight", "bold");
		please.setVisible(false);
		otherEmploymentStatus.setVisible(false);
	
		employmentStatus.addItem(constants.select_one());
		employmentStatus.addItem(constants.paid());
		employmentStatus.addItem(constants.looking());
		employmentStatus.addItem(constants.unemployed());
		employmentStatus.addItem(constants.student());
		employmentStatus.addItem(constants.family());
		employmentStatus.addItem(constants.retired());
		employmentStatus.addItem(constants.unable());
		employmentStatus.addItem(constants.other());
		employment.add(theSelection);
		employment.add(employmentStatus);
		employment.add(please);
		employment.add(otherEmploymentStatus);
			
		//employmentStatus.setWidth("100%"); 
		employmentStatus.addChangeHandler(new ChangeHandler() {
	    	public void onChange(ChangeEvent event) {
	    		int selectedIndex = employmentStatus.getSelectedIndex();
	    		if (selectedIndex == 8){
	    			please.setVisible(true);
	    			otherEmploymentStatus.setVisible(true);
	    			otherEmploymentStatus.setVisibleLength(35);
	    			otherEmploymentStatus.setFocus(true);
	    		}
	    		else if (selectedIndex != 8){
	    			
	    			please.setVisible(false);
	    			otherEmploymentStatus.setVisible(false);
	    			
	    		}
	    	}
	    });
	
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
		error = new InlineLabel(constants.forty_60());	

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
		error = new InlineLabel(constants.gender_error());	
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
		int index = countryEducated.getSelectedIndex();
		
		if (index == 2 )  { // scotland 
			educationLevelSC.getElement().getStyle().setProperty("color", "black");	
			error = new InlineLabel("Please select your education level");
			if (educationLevelSC.getSelectedIndex() <= 0) {
				showErrorPopupPanel(error);
				educationLevelSC.getElement().getStyle().setProperty("color", "red");
				return false;		   
			}
			else return true;
		}
		if (index == 1 )  { // ireland 
			educationLevelIE.getElement().getStyle().setProperty("color", "black");	
			error = new InlineLabel("Please select your education level");
			if (educationLevelIE.getSelectedIndex() <= 0) {
				showErrorPopupPanel(error);
				educationLevelIE.getElement().getStyle().setProperty("color", "red");
				return false;		   
			}
			else return true;
		}
		
		if (index == 3 )  { // netherland 
			educationLevelNL.getElement().getStyle().setProperty("color", "black");	
			error = new InlineLabel("Please select your education level");
			if (educationLevelNL.getSelectedIndex() <= 0) {
				showErrorPopupPanel(error);
				educationLevelNL.getElement().getStyle().setProperty("color", "red");
				return false;		   
			}
			else return true;
		}
		
		if (index == 4 )  { // france 
			educationLevelFR.getElement().getStyle().setProperty("color", "black");	
			error = new InlineLabel("Please select your education level");
			if (educationLevelFR.getSelectedIndex() <= 0) {
				showErrorPopupPanel(error);
				educationLevelFR.getElement().getStyle().setProperty("color", "red");
				return false;		   
			}
			else return true;
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
		 lastinstance = this;
		 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
			 @Override	 
            public void onSuccess(Boolean result) {
            		if ((result == false)){	            		
            			InlineLabel error = new InlineLabel("Patient Data not updated !!!!!");
            			showErrorPopupPanel(error, "red");            			            			
            		}
            		else {
            			//InlineLabel error = new InlineLabel("Patient Data  updated. proceed to next panel");
            			//showErrorPopupPanel(error, "green");  
            			content.selectTab(DECK_CESD);
            			content.getTabWidget(DECK_PATIENT).getElement().getStyle().setProperty("backgroundColor", "red");
            			
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
		 int indexCountry  = country.getSelectedIndex();
		 patient.setCountryOfBirth(country.getItemText(indexCountry));
		 int index = marital.getSelectedIndex();
		 patient.setMaritalStatus(marital.getItemText(index));
		 index = livingArrangements.getSelectedIndex();
		 if (index == 5) {
			 patient.setLivingArrangements(otherLivingArrangements.getText());
		 }
		 else if(index != 5) {
			 patient.setLivingArrangements(livingArrangements.getItemText(index));
		 }
		 index = occupation.getSelectedIndex();
		 patient.setOccupationalGroup(occupation.getItemText(index));
		 int indexCountryEducated  = countryEducated.getSelectedIndex();
		 if (indexCountryEducated == 2) {
			 index = educationLevelSC.getSelectedIndex();
			 patient.setEducationLevel(educationLevelSC.getItemText(index));
		 }
		 if (indexCountryEducated == 1) {
			 index = educationLevelIE.getSelectedIndex();
			 patient.setEducationLevel(educationLevelIE.getItemText(index));
		 }
		 if (indexCountryEducated == 3) {
			 index = educationLevelNL.getSelectedIndex();
			 patient.setEducationLevel(educationLevelNL.getItemText(index));
		 }
		 if (indexCountryEducated == 4) {
			 index = educationLevelSC.getSelectedIndex();
			 patient.setEducationLevel(educationLevelFR.getItemText(index));
		 }
		 index = employmentStatus.getSelectedIndex();
		 if (index == 8) {
			 patient.setEmploymentStatus(otherEmploymentStatus.getText());
		 }
		 else if(index != 8) {
			 patient.setEmploymentStatus(employmentStatus.getItemText(index));
		 }
		
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
		 
		 
		 educationLevelSC.setVisible(false);
		 educationLevelIE.setVisible(false);
		 educationLevelNL.setVisible(false);
		 educationLevelFR.setVisible(false);
		 
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
		 if (index == 6)  { // has to be "other"
			 //otherLivingArrangements = new TextBox();
			 otherLivingArrangements.getElement().getStyle().setProperty("marginLeft", "10px");
			 final InlineLabel please = new InlineLabel(constants.please());
			 please.getElement().getStyle().setProperty("marginLeft", "20px");
			 please.getElement().getStyle().setProperty("fontWeight", "bold");
			 please.setVisible(false);
			 otherLivingArrangements.setVisible(true);
			 otherLivingArrangements.setText(patientValue);
			 otherLivingArrangements.setStyleName("pos12");
			 //patientPanel.add(otherLivingArrangements);
		 }
		 User user = login.getUser();
		 if (user.getUserId().startsWith("11")) { // ireland
			 countryEducated.setSelectedIndex(1);
			 patientValue = patient.getEducationLevel();
			 itemCount = educationLevelIE.getItemCount();
			 for (index = 0; index < itemCount; index++) {
				 if ( educationLevelIE.getItemText(index).equals(patientValue)) {
					 educationLevelIE.setSelectedIndex(index);
					 
					 educationLevelIE.setVisible(true);
					 break;
				 }
			 }
		 }
		 else if (user.getUserId().startsWith("22")) { //scotland
			 countryEducated.setSelectedIndex(2);
				 patientValue = patient.getEducationLevel();
				 itemCount = educationLevelSC.getItemCount();
				 for (index = 0; index < itemCount; index++) {
					 if ( educationLevelSC.getItemText(index).equals(patientValue)) {
						 educationLevelSC.setSelectedIndex(index);
						 educationLevelSC.setVisible(true);
						 break;
					 }
				 }
			 }
		 else if (user.getUserId().startsWith("33")) {  //the netherlands
			 countryEducated.setSelectedIndex(3);
			 patientValue = patient.getEducationLevel();
			 itemCount = educationLevelNL.getItemCount();
			 for (index = 0; index < itemCount; index++) {
				 if ( educationLevelNL.getItemText(index).equals(patientValue)) {
					 educationLevelNL.setSelectedIndex(index);
					 educationLevelNL.setVisible(true);
					 break;
				 }
			 }
		 }
		 else if (user.getUserId().startsWith("44")) {  //france
			 countryEducated.setSelectedIndex(4);
			 patientValue = patient.getEducationLevel();
			 itemCount = educationLevelFR.getItemCount();
			 for (index = 0; index < itemCount; index++) {
				 if ( educationLevelFR.getItemText(index).equals(patientValue)) {
					 educationLevelFR.setSelectedIndex(index);
					 educationLevelFR.setVisible(true);
					 break;
				 }
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
			popup.setPopupPosition(600,545);
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
			popup.setPopupPosition(600,550);
			popup.setWidth("550px");
			popup.show();

		}
}
