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
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.CognitiveOneInfo;
import com.inmindd.dcu.shared.CognitiveTwoInfo;
import com.inmindd.dcu.shared.User;

public class CognitiveTwo {
	
	
	private static final int DECK_COGNITIVE2 = 7;
	private static final int DECK_SMOKE_ALCOHOL = 8;
	
	private User user;
	private Login login;
	
	private FlowPanel cognitiveTwoPanel;
	private TabLayoutPanel content;
	static  InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	private CognitiveTwoInfo cognitiveTwo;
	private InminddServiceAsync InminddServiceSvc;
	private ScrollPanel scroll = new ScrollPanel();
	private static final String  NEVER = constants.never_1();
	private static final String  NEVER_AFTER = constants.never_rarely();
	private static final String OFTEN = constants.often_1();
	private static final String OFTEN_AFTER = constants.often_always();
	private static final String  NEVER_MONTH = constants.never_2();
	private static final String OFTEN_MONTH = constants.often_2();
	private static final String  NEVER_MONTH_AFTER = "Never/Rarely ";
	private static final String OFTEN_MONTH_AFTER = "Often/Always  ";
	private static final String  NEVER_YEAR = constants.never_3();
	private static final String OFTEN_YEAR = constants.often_3();
	private static final String  NEVER_YEAR_AFTER = "Never/Rarely ";
	private static final String OFTEN_YEAR_AFTER = "Often/Always  ";
	private static final String IF_OFTEN = constants.activity_weekly();
	private static final String IF_OFTEN_MONTH = constants.activity_monthly();
	private static final String IF_OFTEN_YEAR = constants.activity_yearly();
	
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
	
	public void setContent(TabLayoutPanel content){
		this.content = content;
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
				constants.cognitive_p2() + "</h1>");
		Button prev = new Button(constants.review());


		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getCognitiveTwoData();
			}
		});
		HTMLPanel header = new HTMLPanel("<h3>" +
				constants.leisure_social() + "<h3>");
		header.getElement().getStyle().setProperty("textDecoration", "underline");
		
		InlineLabel lbl = new InlineLabel(constants.adult_life() 
		 + constants.paid_1());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");	
		
		InlineLabel lbl2 = new InlineLabel(constants.weekly_activities());
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
		InlineLabel lbl3 = new InlineLabel(constants.monthly_activities());
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
		InlineLabel lbl4 = new InlineLabel(constants.yearly_activities());
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
		Button btn = new Button(constants.submit());
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
	       			//InlineLabel error = new InlineLabel("Cognitive Activity Part Two  updated  -  Proceed to Smoking & Alcohol intake panel");
	       			//showErrorPopupPanel(error, "green"); 
	       			content.selectTab(DECK_SMOKE_ALCOHOL);
	       			content.getTabWidget(DECK_COGNITIVE2).getElement().getStyle().setProperty("backgroundColor", "red");
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
		readingLabel = new InlineLabel(constants.newspapers());
		readingLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		readingLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(readingLabel);

		readingNever = new RadioButton("readingButton", NEVER);
		readingNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//readingNever.getElement().getStyle().setProperty("marginLeft", "216px");	
		readingNever.setStyleName("pos1");
		readingOften = new RadioButton("readingButton", OFTEN);
		readingOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//readingOften.getElement().getStyle().setProperty("marginLeft", "50px");	
		readingOften.setStyleName("pos3");
	
		cognitiveTwoPanel.add(readingNever);
		//cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(readingOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenRead = new DataField(IF_OFTEN, constants.year());
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
		householdLabel = new InlineLabel(constants.household());
		householdLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		householdLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(householdLabel);

		householdNever = new RadioButton("householdButton", NEVER_AFTER);
		householdNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//householdNever.getElement().getStyle().setProperty("marginLeft", "110px");	
		householdNever.setStyleName("pos1");
		householdOften = new RadioButton("householdButton", OFTEN_AFTER);
		householdOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//householdOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		householdOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(householdNever);
		//cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(householdOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenHousehold = new DataField(IF_OFTEN, constants.year());
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
		drivingLabel = new InlineLabel(constants.driving());
		drivingLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		drivingLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(drivingLabel);

		drivingNever = new RadioButton("drivingButton", NEVER_AFTER);
		drivingNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//drivingNever.getElement().getStyle().setProperty("marginLeft", "337px");	
		drivingNever.setStyleName("pos1");
		drivingOften = new RadioButton("drivingButton", OFTEN_AFTER);
		drivingOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//drivingOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		drivingOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(drivingNever);
		//cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(drivingOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenDrive = new DataField(IF_OFTEN, constants.year());
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
		leisureLabel = new InlineLabel(constants.leisure_activities());
		leisureLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		leisureLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(leisureLabel);

		leisureNever = new RadioButton("leisureButton", NEVER_AFTER);
		leisureNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//leisureNever.getElement().getStyle().setProperty("marginLeft", "32px");	
		leisureNever.setStyleName("pos1");
		leisureOften = new RadioButton("leisureButton", OFTEN_AFTER);
		leisureOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//leisureOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		leisureOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(leisureNever);
		
		cognitiveTwoPanel.add(leisureOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenLeisure = new DataField(IF_OFTEN, constants.year());
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
		techLabel = new InlineLabel(constants.new_tech());
		techLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		techLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(techLabel);

		techNever = new RadioButton("techButton", NEVER_AFTER);
		techNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//techNever.getElement().getStyle().setProperty("marginLeft", "32px");	
		techNever.setStyleName("pos1");
		techOften = new RadioButton("techButton", OFTEN_AFTER);
		techOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//techOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		techOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(techNever);
		
		cognitiveTwoPanel.add(techOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenTech = new DataField(IF_OFTEN, constants.year());
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
		socialLabel = new InlineLabel(constants.social_activities());
		socialLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		socialLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(socialLabel);

		socialNever = new RadioButton("socialButton", NEVER_MONTH);
		socialNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//socialNever.getElement().getStyle().setProperty("marginLeft", "32px");	
		socialNever.setStyleName("pos4");
		socialOften = new RadioButton("socialButton", OFTEN_MONTH);
		socialOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//socialOften.getElement().getStyle().setProperty("marginLeft", "47px");	
		socialOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(socialNever);
		
		cognitiveTwoPanel.add(socialOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenSocial = new DataField(IF_OFTEN_MONTH, constants.year());
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
		cinemaLabel = new InlineLabel(constants.cinema());
		cinemaLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		cinemaLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(cinemaLabel);

		cinemaNever = new RadioButton("cinemaButton", constants.never_rarely());
		cinemaNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//cinemaNever.getElement().getStyle().setProperty("marginLeft", "446px");	
		cinemaNever.setStyleName("pos1");
		cinemaOften = new RadioButton("cinemaButton", constants.often_always());
		cinemaOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//cinemaOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		cinemaOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(cinemaNever);
		
		cognitiveTwoPanel.add(cinemaOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenCinema = new DataField(IF_OFTEN_MONTH, constants.year());
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
		gardeningLabel = new InlineLabel(constants.gardening_2());
		gardeningLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		gardeningLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(gardeningLabel);

		gardeningNever = new RadioButton("gardenButton", constants.never_rarely());
		gardeningNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//gardeningNever.getElement().getStyle().setProperty("marginLeft", "230px");	
		gardeningNever.setStyleName("pos1");
		gardeningOften = new RadioButton("gardenButton", constants.often_always());
		gardeningOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//gardeningOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		gardeningOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(gardeningNever);
		
		cognitiveTwoPanel.add(gardeningOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenGardening = new DataField(IF_OFTEN_MONTH, constants.year());
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
		careLabel = new InlineLabel(constants.children());
		careLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		careLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(careLabel);

		careNever = new RadioButton("careButton", constants.never_rarely());
		careNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//careNever.getElement().getStyle().setProperty("marginLeft", "332px");	
		careNever.setStyleName("pos1");
		careOften = new RadioButton("careButton", constants.often_always());
		careOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//careOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		careOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(careNever);
		
		cognitiveTwoPanel.add(careOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenCare = new DataField(IF_OFTEN_MONTH, constants.year());
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
		volunteerLabel  = new InlineLabel(constants.volunteer());
		volunteerLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		volunteerLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(volunteerLabel);

		volunteerNever = new RadioButton("volunteerButton", constants.never_rarely());
		volunteerNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//volunteerNever.getElement().getStyle().setProperty("marginLeft", "472px");	
		volunteerNever.setStyleName("pos1");
		volunteerOften = new RadioButton("volunteerButton", constants.often_always());
		volunteerOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//volunteerOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		volunteerOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(volunteerNever);
		
		cognitiveTwoPanel.add(volunteerOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenVolunteer = new DataField(IF_OFTEN_MONTH, constants.year());
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
		artisticLabel = new InlineLabel(constants.artistic());
		artisticLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		artisticLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(artisticLabel);

		artisticNever = new RadioButton("artisticButton", constants.never_rarely());
		artisticNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//artisticNever.getElement().getStyle().setProperty("marginLeft", "127px");	
		artisticNever.setStyleName("pos1");
		artisticOften = new RadioButton("artisticButton", constants.often_always());
		artisticOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//artisticOften.getElement().getStyle().setProperty("marginLeft", "325px");	
		artisticOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(artisticNever);
		
		cognitiveTwoPanel.add(artisticOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenArtistic = new DataField(IF_OFTEN_MONTH, constants.year());
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
		exhibLabel  = new InlineLabel(constants.exhibitions());
		exhibLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		exhibLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(exhibLabel);

		exhibNever = new RadioButton("exhibButton", NEVER_YEAR);
		exhibNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		// Never.getElement().getStyle().setProperty("marginLeft", "215px");
		exhibNever.setStyleName("pos1");
		
		exhibOften = new RadioButton("exhibButton", OFTEN_YEAR);
		exhibOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//exhibOften.getElement().getStyle().setProperty("marginLeft", "55px");	
		exhibOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(exhibNever);
		
		cognitiveTwoPanel.add(exhibOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenExhib = new DataField(IF_OFTEN_YEAR, constants.year());
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
		holidayLabel = new InlineLabel(constants.holidays());
		holidayLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		holidayLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(holidayLabel);

		holidayNever = new RadioButton("holidayButton", constants.never_rarely());
		holidayNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//holidayNever.getElement().getStyle().setProperty("marginLeft", "185px");	
		holidayNever.setStyleName("pos1");
		holidayOften = new RadioButton("holidayButton", constants.often_always());
		holidayOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//holidayOften.getElement().getStyle().setProperty("marginLeft", "320px");	
		holidayOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(holidayNever);
		
		cognitiveTwoPanel.add(holidayOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenHoliday = new DataField(IF_OFTEN_YEAR, constants.year());
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
		booksLabel  = new InlineLabel(constants.books());
		booksLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		booksLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(booksLabel);

		booksNever = new RadioButton("booksButton", constants.never_rarely());
		booksNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//booksNever.getElement().getStyle().setProperty("marginLeft", "350px");	
		booksNever.setStyleName("pos1");
		booksOften = new RadioButton("booksButton", constants.often_always());
		booksOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//booksOften.getElement().getStyle().setProperty("marginLeft", "320px");	
		booksOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(booksNever);
		
		cognitiveTwoPanel.add(booksOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenBooks = new DataField(IF_OFTEN_YEAR, constants.year());
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
		childrenLabel = new InlineLabel(constants.raised_children());
		childrenLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		
		childrenLabel.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0"); 
		childrenLabel.setStyleName("flow");
		
		pnl.add(childrenLabel);

		childYes= new RadioButton("raisedChild", constants.yes());
		childYes.getElement().getStyle().setProperty("fontWeight", "bold");	
		//childYes.getElement().getStyle().setProperty("marginLeft", "242px");	
		childYes.setStyleName("pos1");
		childNo = new RadioButton("raisedChild", constants.no());
		childNo.getElement().getStyle().setProperty("fontWeight", "bold");	
		//childNo.getElement().getStyle().setProperty("marginLeft", "50px");	
		childNo.setStyleName("pos3");
		
		pnl.add(childYes);
		
		pnl.add(childNo);
		
		numberChildren = new DataField(constants.number_children(), "");
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
		petsLabel = new InlineLabel(constants.pets());
		petsLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		petsLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(petsLabel);

		petsNever = new RadioButton("petsButton", constants.never_rarely());
		petsNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//petsNever.getElement().getStyle().setProperty("marginLeft", "102px");	
		petsNever.setStyleName("pos1");
		petsOften = new RadioButton("petsButton", constants.often_always());
		petsOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//petsOften.getElement().getStyle().setProperty("marginLeft", "320px");	
		petsOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(petsNever);
		
		cognitiveTwoPanel.add(petsOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenPets = new DataField(constants.activity_adult(), constants.year());
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
		bankLabel = new InlineLabel(constants.bank());
		bankLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		bankLabel.setStyleName("flow");
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveTwoPanel.add(bankLabel);

		bankNever = new RadioButton("bankButton", constants.never_rarely());
		bankNever.getElement().getStyle().setProperty("fontWeight", "bold");	
		//bankNever.getElement().getStyle().setProperty("marginLeft", "5px");	
		bankNever.setStyleName("pos1");
		bankOften = new RadioButton("bankButton", constants.often_always());
		bankOften.getElement().getStyle().setProperty("fontWeight", "bold");	
		//bankOften.getElement().getStyle().setProperty("marginLeft", "320px");	
		bankOften.setStyleName("pos3");
		
		cognitiveTwoPanel.add(bankNever);
		
		cognitiveTwoPanel.add(bankOften);
		cognitiveTwoPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		oftenBank = new DataField(constants.activity_adult(), constants.year());
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
		// check if all buttons selected and number of years is valid
		if (!checkButtonSelected(readingLabel, readingNever, readingOften))
			return false;	
		if (readingOften.getValue()) {
			if(!checkYears(oftenRead, "reading activity"))
				return false;
		}
			
		if (!checkButtonSelected(householdLabel, householdNever, householdOften))
			return false;
		
		if (householdOften.getValue()) {
			if(!checkYears(oftenHousehold, "household activity"))
				return false;
		}
		if (!checkButtonSelected(drivingLabel, drivingNever, drivingOften))
			return false;
	
		
		if (drivingOften.getValue()) {
			if(!checkYears(oftenDrive, "driving activity"))
				return false;
		}
		if (!checkButtonSelected(leisureLabel, leisureNever, leisureOften))
			return false;
		if (leisureOften.getValue()) {
			if(!checkYears(oftenLeisure, "lesiure activity"))
				return false;
		}
		if (!checkButtonSelected(techLabel, techNever, techOften))
			return false;
		
		if (techOften.getValue()) {
			if(!checkYears(oftenTech, "technology activity"))
				return false;
		}
		if (!checkButtonSelected(socialLabel, socialNever, socialOften))
			return false;
		if (socialOften.getValue()) {
			if(!checkYears(oftenSocial, "social activity"))
				return false;
		}
		if (!checkButtonSelected(cinemaLabel, cinemaNever, cinemaOften))
			return false;
		if (cinemaOften.getValue()) {
			if(!checkYears(oftenCinema, "cinema activity"))
				return false;
		}
		if (!checkButtonSelected(gardeningLabel, gardeningNever, gardeningOften))
			return false;
		if (gardeningOften.getValue()) {
			if(!checkYears(oftenGardening, "gardening activity"))
				return false;
		}
		if (!checkButtonSelected(careLabel, careNever, careOften))
			return false;
		
		if (careOften.getValue()) {
			if(!checkYears(oftenCare, "caring activity"))
				return false;
		}
		if (!checkButtonSelected(volunteerLabel, volunteerNever, volunteerOften))
			return false;
		
		if (volunteerOften.getValue()) {
			if(!checkYears(oftenVolunteer, "volunteering activity"))
				return false;
		}
		if (!checkButtonSelected(artisticLabel, artisticNever, artisticOften))
			return false;
		if (artisticOften.getValue()) {
			if(!checkYears(oftenArtistic, "artistic activity"))
				return false;
		}
		if (!checkButtonSelected(exhibLabel, exhibNever, exhibOften))
				return false;
		if (exhibOften.getValue()) {
			if(!checkYears(oftenExhib, "exhibition activity"))
				return false;
		}
		if (!checkButtonSelected(holidayLabel, holidayNever, holidayOften))
			return false;
		if (holidayOften.getValue()) {
			if(!checkYears(oftenHoliday, "holiday activity"))
				return false;
		}
		if (!checkButtonSelected(booksLabel, booksNever, booksOften))
			return false;
		if (booksOften.getValue()) {
			if(!checkYears(oftenBooks, "books activity"))
				return false;
		}
		if (!checkButtonSelected(childrenLabel, childYes, childNo))
			return false;
		if (!checkButtonSelected(petsLabel, petsNever, petsOften))
			return false;
		if (petsOften.getValue()) {
			if(!checkYears(oftenPets, "pets activity"))
				return false;
		}
		if (!checkButtonSelected(bankLabel, bankNever, bankOften))
			return false;
		if (bankOften.getValue()) {
			if(!checkYears(oftenBank, "banking activity"))
				return false;
		}
		// check user has entered number if children
		if (childYes.getValue()) {
			
			int numberChild = getValueAsInt(numberChildren);
			if (numberChild <= 0) {
				error = new InlineLabel("Please enter number of children raised");
				showErrorPopupPanel(error, "red");
				return false;
			}
			//return true;
		}		
		
		 
			return true;
	}

	private boolean checkYears(DataField field, String activity) {
		
			if (getValueAsInt(field) > 42) {
				InlineLabel error  = new InlineLabel("Value entered for " + activity + " cannot be greater than 42");
				showErrorPopupPanel(error, "red");
				return false;				
			}		
		
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

		error = new InlineLabel(constants.cognitive_2_error());			
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
		popup.setPopupPosition(190,500);
		popup.setWidth("650px");
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
					InlineLabel error = new InlineLabel(constants.retrieved());
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
		if(cognitiveTwo.getArtistic().equals("often")) {
			artisticOften.setValue(true);
			oftenArtistic.setText(Integer.toString(cognitiveTwo.getArtistic_years()));
			oftenArtistic.setVisible(true);
		}
		if(cognitiveTwo.getBank_account().equals("never"))
			bankNever.setValue(true);
		if(cognitiveTwo.getBank_account().equals("often")) {
			bankOften.setValue(true);
			oftenBank.setText(Integer.toString(cognitiveTwo.getBank_account_years()));
			oftenBank.setVisible(true);
			
		}
		if(cognitiveTwo.getBooks().equals("never"))
			booksNever.setValue(true);
		if(cognitiveTwo.getBooks().equals("often")) {
			booksOften.setValue(true);	
			oftenBooks.setText(Integer.toString(cognitiveTwo.getBooks_years()));
			oftenBooks.setVisible(true);
			
		}
		if(cognitiveTwo.getChildren().equals("never"))
			careNever.setValue(true);
		if(cognitiveTwo.getChildren().equals("often")) {
			careOften.setValue(true);		
			oftenCare.setText(Integer.toString(cognitiveTwo.getChildren_years()));
			oftenCare.setVisible(true);
		}
		if(cognitiveTwo.getCinema().equals("never"))
			cinemaNever.setValue(true);
		if(cognitiveTwo.getCinema().equals("often")) {
			cinemaOften.setValue(true);	
			oftenCinema.setText(Integer.toString(cognitiveTwo.getCinema_years()));
			oftenCinema.setVisible(true);
			
		}
		if(cognitiveTwo.getDriving().equals("never"))
			drivingNever.setValue(true);
		if(cognitiveTwo.getDriving().equals("often")) {
			drivingOften.setValue(true);
			oftenDrive.setText(Integer.toString(cognitiveTwo.getDriving_years()));
			oftenDrive.setVisible(true);
			
		}
		if(cognitiveTwo.getExhibitions().equals("never"))
			exhibNever.setValue(true);			
		
		if(cognitiveTwo.getExhibitions().equals("often")) {
			exhibOften.setValue(true);	
			oftenExhib.setText(Integer.toString(cognitiveTwo.getExhibitions_years()));
			oftenExhib.setVisible(true);			
		}
		
		if(cognitiveTwo.getGardening().equals("never"))
			gardeningNever.setValue(true);
		if(cognitiveTwo.getGardening().equals("often")) {
			gardeningOften.setValue(true);	
			oftenGardening.setText(Integer.toString(cognitiveTwo.getGardening_years()));
			oftenGardening.setVisible(true);
			
		}
		if(cognitiveTwo.getHolidays().equals("never"))
			holidayNever.setValue(true);
		if(cognitiveTwo.getHolidays().equals("often")) {
			holidayOften.setValue(true);
			oftenHoliday.setText(Integer.toString(cognitiveTwo.getHolidays_years()));
			oftenHoliday.setVisible(true);
		}
		
		if(cognitiveTwo.getHousehold().equals("never"))
			householdNever.setValue(true);
		if(cognitiveTwo.getHousehold().equals("often")) {
			householdOften.setValue(true);
			oftenHousehold.setText(Integer.toString(cognitiveTwo.getHousehold_years()));
			oftenHousehold.setVisible(true);
		}
		if(cognitiveTwo.getLeisure().equals("never"))
			leisureNever.setValue(true);
		if(cognitiveTwo.getLeisure().equals("often")) {
			leisureOften.setValue(true);		
			oftenLeisure.setText(Integer.toString(cognitiveTwo.getLeisure_years()));
			oftenLeisure.setVisible(true);
		}
		
		if(cognitiveTwo.getPets().equals("never"))
			petsNever.setValue(true);
		if(cognitiveTwo.getPets().equals("often")) {
			petsOften.setValue(true);
			oftenPets.setText(Integer.toString(cognitiveTwo.getPets_years()));
			oftenPets.setVisible(true);
			
		}
		
		if(cognitiveTwo.getReading().equals("never"))
			readingNever.setValue(true);
		if(cognitiveTwo.getReading().equals("often")) {
			readingOften.setValue(true);
			oftenRead.setText(Integer.toString(cognitiveTwo.getReading_years()));
			oftenRead.setVisible(true);
		}
		
		if(cognitiveTwo.getSocial().equals("never"))
			socialNever.setValue(true);
		if(cognitiveTwo.getSocial().equals("often")) {
			socialOften.setValue(true);
			oftenSocial.setText(Integer.toString(cognitiveTwo.getSocial_years()));
			oftenSocial.setVisible(true);
			
		}
		
		if(cognitiveTwo.getTechnology().equals("never"))
			techNever.setValue(true);
		if(cognitiveTwo.getTechnology().equals("often")) {
			techOften.setValue(true);
			oftenTech.setText(Integer.toString(cognitiveTwo.getTechnology_years()));
			oftenTech.setVisible(true);
		}
		
		if(cognitiveTwo.getVolunteering().equals("never"))
			volunteerNever.setValue(true);
		if(cognitiveTwo.getVolunteering().equals("often")) {
			volunteerOften.setValue(true);
			oftenVolunteer.setText(Integer.toString(cognitiveTwo.getVolunteering_years()));
			oftenVolunteer.setVisible(true);
			
		}
		
		if(cognitiveTwo.getRaised_children().equals("yes")) {
			childYes.setValue(true);
			numberChildren.setText(Integer.toString(cognitiveTwo.getNumber_children()));
			numberChildren.setVisible(true);
			
			
		}
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


