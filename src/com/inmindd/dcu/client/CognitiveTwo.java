package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.CognitiveOneInfo;
import com.inmindd.dcu.shared.CognitiveTwoInfo;
import com.inmindd.dcu.shared.User;

public class CognitiveTwo {
	
	private User user;
	private Login login;
	
	private FlowPanel cognitiveTwoPanel;
	
	private CognitiveTwoInfo cognitiveTwo;
	private InminddServiceAsync InminddServiceSvc;
	private ScrollPanel scroll = new ScrollPanel();
	private static final String  NEVER = "Never/Rarely (i.e less than or equal to 2 times/week) ";
	private static final String  NEVER_AFTER = "Never/Rarely ";
	private static final String OFTEN = "Often/Always (i.e more than or equal to 3 times/week)  ";
	private static final String OFTEN_AFTER = "Often/Always ";
	private static final String  NEVER_MONTH = "Never/Rarely (i.e less than or equal to 2 times/month) ";
	private static final String OFTEN_MONTH = "Often/Always (i.e more than or equal to 3 times/month)  ";
	private static final String  NEVER_MONTH_AFTER = "Never/Rarely ";
	private static final String OFTEN_MONTH_AFTER = "Often/Always  ";
	private static final String  NEVER_YEAR = "Never/Rarely (i.e less than or equal to 2 times/year) ";
	private static final String OFTEN_YEAR = "Often/Always (i.e more than or equal to 3 times/year)  ";
	private static final String  NEVER_YEAR_AFTER = "Never/Rarely ";
	private static final String OFTEN_YEAR_AFTER = "Often/Always  ";
	private static final String IF_OFTEN = "Please estimate the number of years over your adult life" +
"	(i.e from 18 years onwards) that you engaged in this activity on a weekly basis ";
	private static final String IF_OFTEN_MONTH = "Please estimate the number of years over your adult life" +
			"	(i.e from 18 years onwards) that you engaged in this activity on a monthly basis ";
	private static final String IF_OFTEN_YEAR = "Please estimate the number of years over your adult life" +
			"	(i.e from 18 years onwards) that you engaged in this activity on a yearly basis ";
	
	// buttons
	
	private RadioButton readingNever;
	private RadioButton readingOften;
	private RadioButton householdNever;
	private RadioButton householdOften;
	private RadioButton drivingNever;
	private RadioButton drivingOften;
	private RadioButton leisureNever;
	private RadioButton leisureOften;
	private RadioButton techNever;	
	private RadioButton techOften;
	private RadioButton socialNever;	
	private RadioButton socialOften;
	private RadioButton cinemaNever;	
	private RadioButton cinemaOften;
	private RadioButton gardeningNever;	
	private RadioButton gardeningOften;
	private RadioButton careNever;	
	private RadioButton careOften;
	private RadioButton volunteerNever;
	private RadioButton volunteerOften;
	private RadioButton artisticNever;
	private RadioButton artisticOften;
	private RadioButton exhibNever;
	private RadioButton exhibOften;
	private RadioButton holidayNever;
	private RadioButton holidayOften;
	private RadioButton booksNever;
	private RadioButton booksOften;
	private RadioButton petsNever;
	private RadioButton petsOften;
	private RadioButton bankNever;
	private RadioButton bankOften;
	private RadioButton childYes;
	private RadioButton childNo;
	
	// labels
	private InlineLabel readingLabel;
	private InlineLabel householdLabel;
	private InlineLabel drivingLabel;
	private InlineLabel leisureLabel;
	private InlineLabel techLabel;
	private InlineLabel socialLabel;
	private InlineLabel cinemaLabel;
	private InlineLabel gardeningLabel;
	private InlineLabel careLabel;
	private InlineLabel volunteerLabel;
	private InlineLabel artisticLabel;
	private InlineLabel exhibLabel;
	private InlineLabel holidayLabel;
	private InlineLabel booksLabel;
	private InlineLabel childrenLabel;
	private InlineLabel petsLabel;
	private InlineLabel bankLabel;
	
	
	private InlineLabel error;
	
	// datafields
	private DataField oftenRead;
	private DataField oftenHousehold;
	private DataField oftenDrive;
	private DataField oftenLeisure;
	private DataField oftenTech;
	private DataField oftenSocial;
	private DataField oftenCinema;
	private DataField oftenGardening;
	private DataField oftenCare;
	private DataField oftenVolunteer;
	private DataField oftenArtistic;
	private DataField oftenExhib;
	private DataField oftenHoliday;
	private DataField oftenBooks;
	private DataField numberChildren;
	private DataField oftenPets;
	private DataField oftenBank;
	
	
	public static CognitiveTwo  lastinstance;
	
	public CognitiveTwo() {
		lastinstance = this;
	}
	
	public static void clearInputs() {
		lastinstance.artisticNever.setValue(false);
		lastinstance.artisticOften.setValue(false);
		lastinstance.bankNever.setValue(false);
		lastinstance.bankOften.setValue(false);
		lastinstance.booksNever.setValue(false);
		lastinstance.booksOften.setValue(false);
		lastinstance.careNever.setValue(false);
		lastinstance.careOften.setValue(false);
		lastinstance.childNo.setValue(false);
		lastinstance.childYes.setValue(false);
		lastinstance.cinemaNever.setValue(false);
		lastinstance.cinemaOften.setValue(false);
		lastinstance.drivingNever.setValue(false);
		lastinstance.drivingOften.setValue(false);
		lastinstance.exhibNever.setValue(false);
		lastinstance.exhibOften.setValue(false);
		lastinstance.gardeningNever.setValue(false);
		lastinstance.gardeningOften.setValue(false);
		lastinstance.holidayNever.setValue(false);
		lastinstance.holidayOften.setValue(false);
		lastinstance.householdNever.setValue(false);
		lastinstance.householdOften.setValue(false);
		lastinstance.leisureNever.setValue(false);
		lastinstance.leisureOften.setValue(false);
		lastinstance.numberChildren.setText("");
		lastinstance.oftenArtistic.setText("");
		lastinstance.oftenBank.setText("");
		lastinstance.oftenBooks.setText("");
		lastinstance.oftenCare.setText("");
		lastinstance.oftenCinema.setText("");
		lastinstance.oftenDrive.setText("");
		lastinstance.oftenExhib.setText("");
		lastinstance.oftenGardening.setText("");
		lastinstance.oftenHoliday.setText("");
		lastinstance.oftenHousehold.setText("");
		lastinstance.oftenLeisure.setText("");
		lastinstance.oftenPets.setText("");
		lastinstance.oftenRead.setText("");
		lastinstance.oftenSocial.setText("");
		lastinstance.oftenTech.setText("");
		lastinstance.oftenVolunteer.setText("");
		lastinstance.petsNever.setValue(false);
		lastinstance.petsOften.setValue(false);
		lastinstance.readingNever.setValue(false);
		lastinstance.readingOften.setValue(false);
		lastinstance.socialNever.setValue(false);
		lastinstance.socialOften.setValue(false);
		lastinstance.techNever.setValue(false);
		lastinstance.techOften.setValue(false);
		lastinstance.volunteerNever.setValue(false);
		lastinstance.volunteerOften.setValue(false);
				
	}
	
	public FlowPanel setupCognitiveTwoPanel(Login login) {
		this.login = login;
		cognitiveTwoPanel = new FlowPanel();
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				"About Your cognitive activities part 2</h1>");
		Button prev = new Button("Retrieve previous data ?");


		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getCognitiveTwoData();
			}
		});
		HTMLPanel header = new HTMLPanel("<h3>" +
				"Social & Leisure Activities</h3>");
		header.getElement().getStyle().setProperty("textDecoration", "underline");
		
		InlineLabel lbl = new InlineLabel("The following questions are about activities that you have carried out throughout your adult life (i.e from 18 years onwards). " 
				+ " Please exclude all paid activities when responding to these questions.");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");	
		
		InlineLabel lbl2 = new InlineLabel("During your adult life, have you engaged in the following activities on a weekly basis ? ");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl2.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");  
		lbl2.setStyleName("flow");	
		
		
		
		cognitiveTwoPanel.add(mainHeader);
		cognitiveTwoPanel.add(prev);
		cognitiveTwoPanel.add(header);
		cognitiveTwoPanel.add(lbl);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(lbl2);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		addReadingNewspapers();
		
		addHouseholdActivities();
		addDriving();
		addLeisure();
		addTech();
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		InlineLabel lbl3 = new InlineLabel("During your adult life, have you engaged in the following activities on a monthly basis ? ");
		lbl3.getElement().getStyle().setProperty("fontWeight", "bold");	
		lbl3.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");  
		lbl3.setStyleName("flow");	
		cognitiveTwoPanel.add(lbl3);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		addSocial();
		addCinema();
		addGardening();
		addCare();
		addVolunteering();
		addArtistic();
		
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		InlineLabel lbl4 = new InlineLabel("During your adult life, have you engaged in the following activities on a yearly basis ? ");
		lbl4.getElement().getStyle().setProperty("fontWeight", "bold");	
		lbl4.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");  
		lbl4.setStyleName("flow");	
		cognitiveTwoPanel.add(lbl4);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addExhib();
		addHolidays();
		addBooks();
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addRaisedChildren();
		addPets();
		addBank();
		Button btn = new Button("submit");
		cognitiveTwoPanel.add(btn);
		
	

		// Listen for mouse events on the submit button.
		btn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (!validateInput()) {
					return;
				}
				updateCognitiveTwoDB();
			}   		

		});
		scroll.setSize("100%", "70%");

		scroll.add(cognitiveTwoPanel);
		scroll.setAlwaysShowScrollBars(true);
		scroll.scrollToTop();
		FlowPanel cognitive = new FlowPanel();
		cognitive.add(scroll);
		
		return cognitive;
		
	}
	
	
	private boolean callServiceSetup() {
		// set up rpc call

		InminddServiceSvc = (InminddServiceAsync) GWT.create(InminddService.class);
		ServiceDefTarget target = (ServiceDefTarget) InminddServiceSvc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "Inmindd";
		target.setServiceEntryPoint(moduleRelativeURL);	    			
		return true;

	}
	private void updateCognitiveTwoDB() {
		
		
		
		 callServiceSetup();
		 cognitiveTwo = createCognitiveTwoInfo();
		 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
			 @Override	 
	       public void onSuccess(Boolean result) {
	       		if ((result == false)){	            		
	       			InlineLabel error = new InlineLabel("Cognitive Activity part Two  info not updated");
	       			showErrorPopupPanel(error, "red");            			
	       		}            		
	       		else {
	       			InlineLabel error = new InlineLabel("Cognitive Activity Part Two  updated  -  Proceed to next Panel");
	       			showErrorPopupPanel(error, "green");            			            			
	       		}
	            
	         }
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel("Database update error");
				showErrorPopupPanel(error, "red");			
				
			}
		  };
		  
		  InminddServiceSvc.updateCognitiveTwo(cognitiveTwo, callback);
		}
	
		private CognitiveTwoInfo createCognitiveTwoInfo() {
			cognitiveTwo = new CognitiveTwoInfo();
			
			User user = login.getUser();
			cognitiveTwo.setUserId(user.getUserId());
			
			if (readingOften.getValue()) {
				cognitiveTwo.setReading("often");
				cognitiveTwo.setReading_years(getValueAsInt(oftenRead));
			}
			else cognitiveTwo.setReading("never");
			
			if (householdOften.getValue()) {
				cognitiveTwo.setHousehold("often");
				cognitiveTwo.setHousehold_years(getValueAsInt(oftenHousehold));
			}
			else cognitiveTwo.setHousehold("never");
			
			if (drivingOften.getValue()) {
				cognitiveTwo.setDriving("often");
				cognitiveTwo.setDriving_years(getValueAsInt(oftenDrive));
			}
			else cognitiveTwo.setDriving("never");
			
			if (leisureOften.getValue()) {
				cognitiveTwo.setLeisure("often");
				cognitiveTwo.setLeisure_years(getValueAsInt(oftenLeisure));
			}
			else cognitiveTwo.setLeisure("never");
						
			if (techOften.getValue()) {
				cognitiveTwo.setTechnology("often");
				cognitiveTwo.setTechnology_years(getValueAsInt(oftenTech));
			}
			
			else cognitiveTwo.setTechnology("never");
						
			
			if (socialOften.getValue()) {
				cognitiveTwo.setSocial("often");
				cognitiveTwo.setSocial_years(getValueAsInt(oftenSocial));
			}
			else cognitiveTwo.setSocial("never");
						
			if (cinemaOften.getValue()) {
				cognitiveTwo.setCinema("often");
				cognitiveTwo.setCinema_years(getValueAsInt(oftenCinema));	
			}
			else cognitiveTwo.setCinema("never");
			
			if (gardeningOften.getValue()) {
				cognitiveTwo.setGardening("often");
				cognitiveTwo.setGardening_years(getValueAsInt(oftenGardening));
			}
			
			else cognitiveTwo.setGardening("never");
			
			if (careOften.getValue()) {
				cognitiveTwo.setChildren("often");
				cognitiveTwo.setChildren_years(getValueAsInt(oftenCare));
			}
			else cognitiveTwo.setChildren("never");
									
			if (volunteerOften.getValue()) {
				cognitiveTwo.setVolunteering("often");
				cognitiveTwo.setVolunteering_years(getValueAsInt(oftenVolunteer));
			}
			else cognitiveTwo.setVolunteering("never");	
			
			if (artisticOften.getValue()) {
				cognitiveTwo.setArtistic("often");
				cognitiveTwo.setArtistic_years(getValueAsInt(oftenArtistic));
			}
			else cognitiveTwo.setArtistic("never");	
			
			if (exhibOften.getValue())  {
				cognitiveTwo.setExhibitions("often");
				cognitiveTwo.setExhibitions_years(getValueAsInt(oftenExhib));
			}
			else cognitiveTwo.setExhibitions("never");
			

			if (exhibOften.getValue())  {
				cognitiveTwo.setExhibitions("often");
				cognitiveTwo.setExhibitions_years(getValueAsInt(oftenExhib));
			}
			else cognitiveTwo.setExhibitions("never");

			if (holidayOften.getValue())  {
				cognitiveTwo.setHolidays("often");
				cognitiveTwo.setHolidays_years(getValueAsInt(oftenHoliday));
			}
			else cognitiveTwo.setHolidays("never");
			
			if (booksOften.getValue()) {
				cognitiveTwo.setBooks("often");
				cognitiveTwo.setBooks_years(getValueAsInt(oftenBooks));
			}
			else cognitiveTwo.setBooks("never");
			
		
			if (childYes.getValue()) {		
					cognitiveTwo.setRaised_children("yes");
					cognitiveTwo.setNumber_children(getValueAsInt(numberChildren));
			}
			else cognitiveTwo.setRaised_children("no");
			
			if (petsOften.getValue()) {
				cognitiveTwo.setPets("often");
				cognitiveTwo.setPets_years(getValueAsInt(oftenPets));
			}
			else cognitiveTwo.setPets("never");
			
			if (bankOften.getValue()) {
				cognitiveTwo.setBank_account("often");
				cognitiveTwo.setBank_account_years(getValueAsInt(oftenBank));
			}
			else cognitiveTwo.setBank_account("never");
			
			return cognitiveTwo;
		}
		
	
	private void addReadingNewspapers() {
		readingLabel = new InlineLabel("Reading newspapers and magazines:	");
		readingLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		readingLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(readingLabel);

		readingNever = new RadioButton("readingButton", NEVER);
		readingNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		readingNever.getElement().getStyle().setProperty("marginLeft", "216px");	
		
		readingOften = new RadioButton("readingButton", OFTEN);
		readingOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		readingOften.getElement().getStyle().setProperty("marginLeft", "50px");	
		
		
		cognitiveTwoPanel.add(readingNever);
		//cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(readingOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenRead = new DataField(IF_OFTEN, "years");
		oftenRead.setVisible(false);
		oftenRead.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenRead.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenRead.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenRead);
		readingOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenRead.setVisible(true);
				oftenRead.setFocus();
			}   		

		});
		
		readingNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenRead.setVisible(false);
				oftenRead.setText("");
			}   
		});
		oftenRead.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenRead.setVisible(false);
			}
		});
	}
	
	private void addHouseholdActivities() {
		householdLabel = new InlineLabel("Household activities (cooking, washing, ironing etc.)	");
		householdLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		householdLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(householdLabel);

		householdNever = new RadioButton("householdButton", NEVER_AFTER);
		householdNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		householdNever.getElement().getStyle().setProperty("marginLeft", "110px");	
		
		householdOften = new RadioButton("householdButton", OFTEN_AFTER);
		householdOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		householdOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(householdNever);
		//cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(householdOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenHousehold = new DataField(IF_OFTEN, "years");
		oftenHousehold.setVisible(false);
		oftenHousehold.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenHousehold.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenHousehold.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenHousehold);
		cognitiveTwoPanel.add(oftenHousehold);
		householdOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenHousehold.setVisible(true);
				oftenHousehold.setFocus();
			}   		

		});
	
		
		householdNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenHousehold.setVisible(false);
				oftenHousehold.setText("");;
			}   
		});
		oftenHousehold.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenHousehold.setVisible(false);
			}
		});
	}
	
	private void addDriving() {
		drivingLabel = new InlineLabel("Driving (not biking)");
		drivingLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		drivingLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(drivingLabel);

		drivingNever = new RadioButton("drivingButton", NEVER_AFTER);
		drivingNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		drivingNever.getElement().getStyle().setProperty("marginLeft", "337px");	
		
		drivingOften = new RadioButton("drivingButton", OFTEN_AFTER);
		drivingOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		drivingOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(drivingNever);
		//cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(drivingOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenDrive = new DataField(IF_OFTEN, "years");
		oftenDrive.setVisible(false);
		oftenDrive.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenDrive.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenDrive.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenDrive);
		drivingOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenDrive.setVisible(true);
				oftenDrive.setFocus();
			}   		

		});
		drivingNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenDrive.setVisible(false);
				oftenDrive.setText("");
			}   
		});
		oftenDrive.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenDrive.setVisible(false);
			}
		});
		
	}
	
	private void addLeisure() {
		leisureLabel = new InlineLabel("Leisure activities (sports, hunting, dancing, cards, bowling etc.)	");
		leisureLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		leisureLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(leisureLabel);

		leisureNever = new RadioButton("leisureButton", NEVER_AFTER);
		leisureNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		leisureNever.getElement().getStyle().setProperty("marginLeft", "32px");	
		
		leisureOften = new RadioButton("leisureButton", OFTEN_AFTER);
		leisureOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		leisureOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(leisureNever);
		
		cognitiveTwoPanel.add(leisureOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenLeisure = new DataField(IF_OFTEN, "years");
		oftenLeisure.setVisible(false);
		oftenLeisure.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenLeisure.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenLeisure.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenLeisure);
		leisureOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenLeisure.setVisible(true);
				oftenLeisure.setFocus();
			}   		

		});
		leisureNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenLeisure.setVisible(false);
				oftenLeisure.setText("");
				
			}   
		});
		oftenLeisure.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenLeisure.setVisible(false);
			}
		});
		
	}
	
	private void addTech() {
		techLabel = new InlineLabel("Using new technologies (digital camera, computer, internet etc.)	");
		techLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		techLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(techLabel);

		techNever = new RadioButton("techButton", NEVER_AFTER);
		techNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		techNever.getElement().getStyle().setProperty("marginLeft", "32px");	
		
		techOften = new RadioButton("techButton", OFTEN_AFTER);
		techOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		techOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(techNever);
		
		cognitiveTwoPanel.add(techOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenTech = new DataField(IF_OFTEN, "years");
		oftenTech.setVisible(false);
		oftenTech.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenTech.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenTech.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenTech);
		techOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenTech.setVisible(true);
				oftenTech.setFocus();
			}   		

		});
		techNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenTech.setVisible(false);
				oftenTech.setText("");
			}   
		});
		oftenTech.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenTech.setVisible(false);
			}
		});
	}
	
	private void addSocial() {
		socialLabel = new InlineLabel("Social activities (parties, going out with friends, local community events etc.)");
		socialLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		socialLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(socialLabel);

		socialNever = new RadioButton("socialButton", NEVER_MONTH);
		socialNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		socialNever.getElement().getStyle().setProperty("marginLeft", "32px");	
		
		socialOften = new RadioButton("socialButton", OFTEN_MONTH);
		socialOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		socialOften.getElement().getStyle().setProperty("marginLeft", "47px");	
		
		
		cognitiveTwoPanel.add(socialNever);
		
		cognitiveTwoPanel.add(socialOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenSocial = new DataField(IF_OFTEN_MONTH, "years");
		oftenSocial.setVisible(false);
		oftenSocial.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenSocial.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenSocial.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenSocial);
		socialOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenSocial.setVisible(true);
				oftenSocial.setFocus();
			}   		

		});
		socialNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenSocial.setVisible(false);
				oftenSocial.setText("");
			}   
		});
		oftenSocial.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenSocial.setVisible(false);
			}
		});
	}
	
	private void addCinema() {
		cinemaLabel = new InlineLabel("Cinema, theater.");
		cinemaLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		cinemaLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(cinemaLabel);

		cinemaNever = new RadioButton("cinemaButton", NEVER_MONTH_AFTER);
		cinemaNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		cinemaNever.getElement().getStyle().setProperty("marginLeft", "446px");	
		
		cinemaOften = new RadioButton("cinemaButton", OFTEN_MONTH_AFTER);
		cinemaOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		cinemaOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(cinemaNever);
		
		cognitiveTwoPanel.add(cinemaOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenCinema = new DataField(IF_OFTEN_MONTH, "years");
		oftenCinema.setVisible(false);
		oftenCinema.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenCinema.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenCinema.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenCinema);
		
		cinemaOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenCinema.setVisible(true);
				oftenCinema.setFocus();
			}  
		});
		
		cinemaNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenCinema.setVisible(false);
				oftenCinema.setText("");
			}   
		});
		oftenCinema.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenCinema.setVisible(false);
			}
		});
	}
	
	
	private void addGardening() {
		gardeningLabel = new InlineLabel("Gardening, handicraft, knitting, embroidery, etc.	");
		gardeningLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		gardeningLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(gardeningLabel);

		gardeningNever = new RadioButton("gardenButton", NEVER_MONTH_AFTER);
		gardeningNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		gardeningNever.getElement().getStyle().setProperty("marginLeft", "230px");	
		
		gardeningOften = new RadioButton("gardenButton", OFTEN_MONTH_AFTER);
		gardeningOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		gardeningOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(gardeningNever);
		
		cognitiveTwoPanel.add(gardeningOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenGardening = new DataField(IF_OFTEN_MONTH, "years");
		oftenGardening.setVisible(false);
		oftenGardening.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenGardening.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenGardening.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenGardening);
		gardeningOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenGardening.setVisible(true);
				oftenGardening.setFocus();
			}   
		});
		
		gardeningNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenGardening.setVisible(false);
				oftenGardening.setText("");
			}   
		});
		oftenGardening.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenGardening.setVisible(false);
			}
		});
	}
	
	private void addCare() {
		careLabel = new InlineLabel("Taking care of children or elderly	");
		careLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		careLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(careLabel);

		careNever = new RadioButton("careButton", NEVER_MONTH_AFTER);
		careNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		careNever.getElement().getStyle().setProperty("marginLeft", "332px");	
		
		careOften = new RadioButton("careButton", OFTEN_MONTH_AFTER);
		careOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		careOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(careNever);
		
		cognitiveTwoPanel.add(careOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenCare = new DataField(IF_OFTEN_MONTH, "years");
		oftenCare.setVisible(false);
		oftenCare.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenCare.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenCare.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenCare);
		
		careOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenCare.setVisible(true);
				oftenCare.setFocus();
			}  
		});
		
		careNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenCare.setVisible(false);
				oftenCare.setText("");
			}   
		});
		oftenCare.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenCare.setVisible(false);
			}
		});
		
	}
	
	private void addVolunteering() {
		volunteerLabel  = new InlineLabel("Volunteering");
		volunteerLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		volunteerLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(volunteerLabel);

		volunteerNever = new RadioButton("volunteerButton", NEVER_MONTH_AFTER);
		volunteerNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		volunteerNever.getElement().getStyle().setProperty("marginLeft", "472px");	
		
		volunteerOften = new RadioButton("volunteerButton", OFTEN_MONTH_AFTER);
		volunteerOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		volunteerOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(volunteerNever);
		
		cognitiveTwoPanel.add(volunteerOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenVolunteer = new DataField(IF_OFTEN_MONTH, "years");
		oftenVolunteer.setVisible(false);
		oftenVolunteer.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenVolunteer.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenVolunteer.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenVolunteer);
		volunteerOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenVolunteer.setVisible(true);
				oftenVolunteer.setFocus();
			}   
		});
		volunteerNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenVolunteer.setVisible(false);
				oftenVolunteer.setText("");
			}   
		});
		oftenVolunteer.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenVolunteer.setVisible(false);
			}
		});
		
	}
	
	private void addArtistic() {
		artisticLabel = new InlineLabel("Artistic activities (playing an instrument, painting, writing, etc.)	");
		artisticLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		artisticLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(artisticLabel);

		artisticNever = new RadioButton("artisticButton", NEVER_MONTH_AFTER);
		artisticNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		artisticNever.getElement().getStyle().setProperty("marginLeft", "127px");	
		
		artisticOften = new RadioButton("artisticButton", OFTEN_MONTH_AFTER);
		artisticOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		artisticOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		
		
		cognitiveTwoPanel.add(artisticNever);
		
		cognitiveTwoPanel.add(artisticOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenArtistic = new DataField(IF_OFTEN_MONTH, "years");
		oftenArtistic.setVisible(false);
		oftenArtistic.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenArtistic.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenArtistic.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenArtistic);
		artisticOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenArtistic.setVisible(true);
				oftenArtistic.setFocus();
			}   
		});
		artisticNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenArtistic.setVisible(false);
				oftenArtistic.setText("");
			}   
		});
		oftenArtistic.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenArtistic.setVisible(false);
			}
		});
		
	}
	
	private void addExhib() {
		exhibLabel  = new InlineLabel("Exhibitions, concerts, conferences	");
		exhibLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		exhibLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(exhibLabel);

		exhibNever = new RadioButton("exhibButton", NEVER_YEAR);
		exhibNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		exhibNever.getElement().getStyle().setProperty("marginLeft", "215px");	
		
		exhibOften = new RadioButton("exhibButton", OFTEN_YEAR);
		exhibOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		exhibOften.getElement().getStyle().setProperty("marginLeft", "55px");	
		
		
		cognitiveTwoPanel.add(exhibNever);
		
		cognitiveTwoPanel.add(exhibOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenExhib = new DataField(IF_OFTEN_YEAR, "years");
		oftenExhib.setVisible(false);
		oftenExhib.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenExhib.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenExhib.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenExhib);
		
		exhibOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenExhib.setVisible(true);
				oftenExhib.setFocus();
			}   
		});
		
		exhibNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenExhib.setVisible(false);
				oftenExhib.setText("");
			}   
		});
		oftenExhib.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenExhib.setVisible(false);
			}
		});
		
	}
	
	
	private void addHolidays() {
		holidayLabel = new InlineLabel("Holidays (lasting at least several days)	");
		holidayLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		holidayLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(holidayLabel);

		holidayNever = new RadioButton("holidayButton", NEVER_YEAR_AFTER);
		holidayNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		holidayNever.getElement().getStyle().setProperty("marginLeft", "185px");	
		
		holidayOften = new RadioButton("holidayButton", OFTEN_YEAR_AFTER);
		holidayOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		holidayOften.getElement().getStyle().setProperty("marginLeft", "320px");	
		
		
		cognitiveTwoPanel.add(holidayNever);
		
		cognitiveTwoPanel.add(holidayOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenHoliday = new DataField(IF_OFTEN_YEAR, "years");
		oftenHoliday.setVisible(false);
		oftenHoliday.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenHoliday.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenHoliday.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenHoliday);
		holidayOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenHoliday.setVisible(true);
				oftenHoliday.setFocus();
			}  
			});
		holidayNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenHoliday.setVisible(false);
				oftenHoliday.setText("");
			}   
		});
		oftenHoliday.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenHoliday.setVisible(false);
			}
		});
		
	}
	
	private void addBooks() {
		booksLabel  = new InlineLabel("Reading books");
		booksLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		booksLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(booksLabel);

		booksNever = new RadioButton("booksButton", NEVER_YEAR_AFTER);
		booksNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		booksNever.getElement().getStyle().setProperty("marginLeft", "350px");	
		
		booksOften = new RadioButton("booksButton", OFTEN_YEAR_AFTER);
		booksOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		booksOften.getElement().getStyle().setProperty("marginLeft", "320px");	
		
		
		cognitiveTwoPanel.add(booksNever);
		
		cognitiveTwoPanel.add(booksOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenBooks = new DataField(IF_OFTEN_YEAR, "years");
		oftenBooks.setVisible(false);
		oftenBooks.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenBooks.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenBooks.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenBooks);
		
		booksOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenBooks.setVisible(true);
				oftenBooks.setFocus();
			}
		});
		
		booksNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenBooks.setVisible(false);
				oftenBooks.setText("");
			}   
		});
		oftenBooks.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenBooks.setVisible(false);
			}
		});
		
	}
	
	private void addRaisedChildren() {
		
		HorizontalPanel pnl = new HorizontalPanel();
		childrenLabel = new InlineLabel("Have you raised any children ?");
		childrenLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		
		childrenLabel.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0"); 
		childrenLabel.setStyleName("flow");
		
		pnl.add(childrenLabel);

		childYes= new RadioButton("raisedChild", "Yes");
		childYes.getElement().getStyle().setProperty("fontWeight", "bold");	
		childYes.getElement().getStyle().setProperty("marginLeft", "242px");	
		
		childNo = new RadioButton("raisedChild", "No");
		childNo.getElement().getStyle().setProperty("fontWeight", "bold");	
		childNo.getElement().getStyle().setProperty("marginLeft", "50px");	
		
		
		pnl.add(childYes);
		
		pnl.add(childNo);
		//cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		numberChildren = new DataField("If yes, please indicate number of children ", "");
		numberChildren.setVisible(false);
		numberChildren.getElement().getStyle().setProperty("fontWeight", "bold");	
		numberChildren.getElement().getStyle().setProperty("marginLeft", "25px");
		numberChildren.getElement().getStyle().setProperty("color", "blue");
		pnl.add(numberChildren);
		cognitiveTwoPanel.add(pnl);
		childYes.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				numberChildren.setVisible(true);
				numberChildren.setFocus();
			}  
		});
		
		childNo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				numberChildren.setVisible(false);
				numberChildren.setText("");
			}   
		});
		numberChildren.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				numberChildren.setVisible(false);
			}
		});
		
	}
	
	private void addPets() {
		petsLabel = new InlineLabel("During your adult life, have you taken care of pets ?");
		petsLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		petsLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(petsLabel);

		petsNever = new RadioButton("petsButton", NEVER_YEAR_AFTER);
		petsNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		petsNever.getElement().getStyle().setProperty("marginLeft", "102px");	
		
		petsOften = new RadioButton("petsButton", OFTEN_YEAR_AFTER);
		petsOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		petsOften.getElement().getStyle().setProperty("marginLeft", "320px");	
		
		
		cognitiveTwoPanel.add(petsNever);
		
		cognitiveTwoPanel.add(petsOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenPets = new DataField("Please estimate the number of years over your adult life" +
				"(i.e from 18 years onwards) that you engaged in this activity", "years");
		oftenPets.setVisible(false);
		oftenPets.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenPets.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenPets.getElement().getStyle().setProperty("color", "blue");
		cognitiveTwoPanel.add(oftenPets);
	
		petsOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenPets.setVisible(true);
				oftenPets.setFocus();
			}   		

		});
		petsNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenPets.setVisible(false);
				oftenPets.setText("");
			}   
		});
		oftenPets.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenPets.setVisible(false);
			}
		});
		
		
	}
	
	private void addBank() {
		bankLabel = new InlineLabel("During your adult life, have you managed your own bank account ?");
		bankLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		bankLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(bankLabel);

		bankNever = new RadioButton("bankButton", NEVER_YEAR_AFTER);
		bankNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		bankNever.getElement().getStyle().setProperty("marginLeft", "5px");	
		
		bankOften = new RadioButton("bankButton", OFTEN_YEAR_AFTER);
		bankOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		bankOften.getElement().getStyle().setProperty("marginLeft", "320px");	
		
		
		cognitiveTwoPanel.add(bankNever);
		
		cognitiveTwoPanel.add(bankOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenBank = new DataField("Please estimate the number of years over your adult life" +
				"(i.e from 18 years onwards) that you engaged in this activity", "years");
		oftenBank.setVisible(false);
		oftenBank.getElement().getStyle().setProperty("fontWeight", "bold");	
		oftenBank.getElement().getStyle().setProperty("marginLeft", "255px");
		oftenBank.getElement().getStyle().setProperty("color", "blue");
		
		cognitiveTwoPanel.add(oftenBank);
		
		bankOften.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenBank.setVisible(true);
				oftenBank.setFocus();
			}   		

		});
		bankNever.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				oftenBank.setVisible(false);
				oftenBank.setText("");
			}   
		});
		oftenBank.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				oftenBank.setVisible(false);
			}
		});
		
	}
	
	private boolean validateInput() {
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		// check if all buttons selected
		if (!checkButtonSelected(readingLabel, readingNever, readingOften))
			return false;
		if (!checkButtonSelected(householdLabel, householdNever, householdOften))
			return false;
		if (!checkButtonSelected(drivingLabel, drivingNever, drivingOften))
			return false;
		if (!checkButtonSelected(leisureLabel, leisureNever, leisureOften))
			return false;
		if (!checkButtonSelected(techLabel, techNever, techOften))
			return false;
		if (!checkButtonSelected(socialLabel, socialNever, socialOften))
			return false;
		if (!checkButtonSelected(cinemaLabel, cinemaNever, cinemaOften))
			return false;
		if (!checkButtonSelected(gardeningLabel, gardeningNever, gardeningOften))
			return false;
		if (!checkButtonSelected(careLabel, careNever, careOften))
			return false;
		if (!checkButtonSelected(volunteerLabel, volunteerNever, volunteerOften))
			return false;
		if (!checkButtonSelected(artisticLabel, artisticNever, artisticOften))
			return false;
		if (!checkButtonSelected(exhibLabel, exhibNever, exhibOften))
				return false;
		if (!checkButtonSelected(holidayLabel, holidayNever, holidayOften))
			return false;
		if (!checkButtonSelected(booksLabel, booksNever, booksOften))
			return false;
		if (!checkButtonSelected(childrenLabel, childYes, childNo))
			return false;
		if (!checkButtonSelected(petsLabel, petsOften, petsNever))
			return false;
		if (!checkButtonSelected(bankLabel, bankNever, bankOften))
			return false;
		
		
		
		else 
			return true;
	}

	private boolean checkButtonSelected(InlineLabel label, RadioButton ... buttons) {
		label.getElement().getStyle().setProperty("color", "black");	
		int numButtons = buttons.length;  // how many are there ?
		for (int i = 0; i < numButtons; i++) {
			if (buttons[i].getValue()) {
				return true;
			}
		}		

		error = new InlineLabel("Please select a question  button indicated above");			
		showErrorPopupPanel(error, "red");
		label.getElement().getStyle().setProperty("color", "red");			  
		return false;		

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
		///popup.setGlassEnabled(true);
		popup.setPopupPosition(190,700);
		popup.setWidth("550px");
		popup.show();

	}
	
	private int getValueAsInt(DataField box) {		
		// get the text entered
		int input = 0;
		try {
			input = Integer.parseInt(box.getText());	
		}

		catch (Exception e)	{					
						
			return 0;

		}
		return input;
	}
		
	
	
	private void getCognitiveTwoData() {				
		User user = login.getUser();
		if (user== null) {
	
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error, "red");
			return;
	
		}
		callServiceSetup();
	
		AsyncCallback<CognitiveTwoInfo> callback =  new AsyncCallback<CognitiveTwoInfo>(){
	
			@Override	 
			public void onSuccess(CognitiveTwoInfo cognitiveTwo) {
				if ((cognitiveTwo == null || cognitiveTwo.getUserId() == null)){	            		
					InlineLabel error = new InlineLabel("Cognitive Two Data not retrieved. No data available for this patient ");
					showErrorPopupPanel(error, "red");            			
				}            		
				else {
					InlineLabel error = new InlineLabel("Cognitive Two data retrieved- Edit as necessary");
					showErrorPopupPanel(error, "green");  
					populatePanel(cognitiveTwo);
	
				}
	
			}
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel("Cognitive Two data Database error");
				showErrorPopupPanel(error, "red");			
	
			}
		};
	
		InminddServiceSvc.queryCognitiveTwo(user, callback);
		return;
	}
	
	private void populatePanel(CognitiveTwoInfo cognitiveTwo) {
		
		if(cognitiveTwo.getArtistic().equals("never"))
			artisticNever.setValue(true);
		if(cognitiveTwo.getArtistic().equals("often"))
			artisticOften.setValue(true);
		if(cognitiveTwo.getBank_account().equals("never"))
			bankNever.setValue(true);
		if(cognitiveTwo.getBank_account().equals("often"))
			bankOften.setValue(true);		
		if(cognitiveTwo.getBooks().equals("never"))
			booksNever.setValue(true);
		if(cognitiveTwo.getBooks().equals("often"))
			booksOften.setValue(true);		
		if(cognitiveTwo.getChildren().equals("never"))
			careNever.setValue(true);
		if(cognitiveTwo.getChildren().equals("often"))
			careOften.setValue(true);		
		if(cognitiveTwo.getCinema().equals("never"))
			cinemaNever.setValue(true);
		if(cognitiveTwo.getCinema().equals("often"))
			cinemaOften.setValue(true);		
		if(cognitiveTwo.getDriving().equals("never"))
			drivingNever.setValue(true);
		if(cognitiveTwo.getDriving().equals("often"))
			drivingOften.setValue(true);		
		if(cognitiveTwo.getExhibitions().equals("never"))
			exhibNever.setValue(true);
		if(cognitiveTwo.getExhibitions().equals("often"))
			exhibOften.setValue(true);		
		if(cognitiveTwo.getGardening().equals("never"))
			gardeningNever.setValue(true);
		if(cognitiveTwo.getGardening().equals("often"))
			gardeningOften.setValue(true);		
		if(cognitiveTwo.getHolidays().equals("never"))
			holidayNever.setValue(true);
		if(cognitiveTwo.getGardening().equals("often"))
			holidayOften.setValue(true);
		
		if(cognitiveTwo.getHousehold().equals("never"))
			householdNever.setValue(true);
		if(cognitiveTwo.getHousehold().equals("often"))
			householdOften.setValue(true);
		
		if(cognitiveTwo.getLeisure().equals("never"))
			leisureNever.setValue(true);
		if(cognitiveTwo.getLeisure().equals("often"))
			leisureOften.setValue(true);
		
		if(cognitiveTwo.getPets().equals("never"))
			petsNever.setValue(true);
		if(cognitiveTwo.getPets().equals("often"))
			petsOften.setValue(true);
		
		if(cognitiveTwo.getReading().equals("never"))
			readingNever.setValue(true);
		if(cognitiveTwo.getReading().equals("often"))
			readingOften.setValue(true);
		
		if(cognitiveTwo.getSocial().equals("never"))
			socialNever.setValue(true);
		if(cognitiveTwo.getSocial().equals("often"))
			socialOften.setValue(true);
		
		if(cognitiveTwo.getTechnology().equals("never"))
			techNever.setValue(true);
		if(cognitiveTwo.getTechnology().equals("often"))
			techOften.setValue(true);
		
		if(cognitiveTwo.getVolunteering().equals("never"))
			volunteerNever.setValue(true);
		if(cognitiveTwo.getVolunteering().equals("often"))
			volunteerOften.setValue(true);
		
		if(cognitiveTwo.getRaised_children().equals("yes"))
			childYes.setValue(true);
		if(cognitiveTwo.getRaised_children().equals("no"))
			childNo.setValue(true);
		
		
		numberChildren.setText(Integer.toString(cognitiveTwo.getNumber_children()));
		oftenArtistic.setText(Integer.toString(cognitiveTwo.getArtistic_years()));
		oftenBank.setText(Integer.toString(cognitiveTwo.getBank_account_years()));
		oftenBooks.setText(Integer.toString(cognitiveTwo.getBooks_years()));
		oftenCare.setText(Integer.toString(cognitiveTwo.getChildren_years()));
		oftenCinema.setText(Integer.toString(cognitiveTwo.getCinema_years()));
		oftenDrive.setText(Integer.toString(cognitiveTwo.getDriving_years()));
		oftenExhib.setText(Integer.toString(cognitiveTwo.getExhibitions_years()));
		oftenGardening.setText(Integer.toString(cognitiveTwo.getGardening_years()));
		oftenHoliday.setText(Integer.toString(cognitiveTwo.getHolidays_years()));
		oftenHousehold.setText(Integer.toString(cognitiveTwo.getHousehold_years()));
		oftenLeisure.setText(Integer.toString(cognitiveTwo.getLeisure_years()));
		oftenPets.setText(Integer.toString(cognitiveTwo.getPets_years()));
		oftenRead.setText(Integer.toString(cognitiveTwo.getReading_years()));
		oftenSocial.setText(Integer.toString(cognitiveTwo.getSocial_years()));
		oftenTech.setText(Integer.toString(cognitiveTwo.getTechnology_years()));
		oftenVolunteer.setText(Integer.toString(cognitiveTwo.getVolunteering_years()));
		
	
		
	}

}

