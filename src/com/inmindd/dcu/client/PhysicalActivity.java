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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.FamilyHistoryInfo;
import com.inmindd.dcu.shared.PhysicalActivityInfo;
import com.inmindd.dcu.shared.User;

public class PhysicalActivity {
	private FlowPanel physicalActivityPanel = new FlowPanel();
	
	private static final String LOGO_IMAGE_NAME = "images.jpeg";
	
	private InminddServiceAsync InminddServiceSvc;
	public static PhysicalActivity lastinstance;
	private VerticalPanel pnl = new VerticalPanel();
	private HorizontalPanel horizPanel = new HorizontalPanel();
	//private HorizontalPanel walkingPanel1 = new HorizontalPanel();
	private VerticalPanel walkingPanel1 = new VerticalPanel();
	private HorizontalPanel walkingPanel2 = new HorizontalPanel();
	//private VerticalPanel walkingPanel2 = new VerticalPanel();
	private VerticalPanel cyclingPanel1 = new VerticalPanel();
	private HorizontalPanel cyclingPanel2 = new HorizontalPanel();
	private VerticalPanel gardenPanel1 = new VerticalPanel();
	private HorizontalPanel gardenPanel2 = new HorizontalPanel();
	private VerticalPanel homePanel = new VerticalPanel();
	private VerticalPanel exercisePanel1 = new VerticalPanel();
	
	private HorizontalPanel exercisePanel2 = new HorizontalPanel();
	private VerticalPanel houseworkPanel1 = new VerticalPanel();
	private HorizontalPanel houseworkPanel2 = new HorizontalPanel();
	private ScrollPanel scroll = new ScrollPanel();
	
	private RadioButton standingBtn;
	private RadioButton manualBtn;
	private RadioButton sedBtn;
	private RadioButton heavyManualBtn;
	private RadioButton naBtn;
	private DataField walkingSummer;	
	private TextBox  walkingWinter;
	private DataField cyclingSummer;	
	private TextBox  cyclingWinter;
	private DataField gardeningSummer;	
	private TextBox  gardeningWinter;
	private DataField diy;	
	private DataField exerciseSummer;	
	private TextBox  exerciseWinter;
	private DataField houseworkSummer;	
	private TextBox  houseworkWinter;
	private DataField stairs;
	private DataField vig;
	private RadioButton sweatYes; 
	private RadioButton sweatNo; 
	private User user;
	private Login login;	
	PhysicalActivityInfo activity;
	
	private static InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	public PhysicalActivity() {
		lastinstance = this;
	}
	public static void clearInputs() {
		lastinstance.cyclingSummer.setText("");
		lastinstance.cyclingWinter.setText("");
		lastinstance.diy.setText("");
		lastinstance.exerciseSummer.setText("");
		lastinstance.exerciseWinter.setText("");
		lastinstance.gardeningSummer.setText("");
		lastinstance.gardeningWinter.setText("");
		lastinstance.heavyManualBtn.setValue(false);
		lastinstance.houseworkSummer.setText("");
		lastinstance.houseworkWinter.setText("");
		lastinstance.manualBtn.setValue(false);
		lastinstance.naBtn.setValue(false);
		lastinstance.sedBtn.setValue(false);
		lastinstance.standingBtn.setValue(false);
		lastinstance.sweatNo.setValue(false);
		lastinstance.sweatYes.setValue(false);
		lastinstance.walkingSummer.setText("");
		lastinstance.walkingWinter.setText("");
		lastinstance.stairs.setText("");
		lastinstance.vig.setText("");
	}
	public FlowPanel setupPhysicalActivityPanel(Login login) {		
		this.login = login;	
		HTMLPanel mainheader = new HTMLPanel("<h1>" +
				constants.physical() + "</h1>");
		physicalActivityPanel.add(mainheader);
		Button prev = new Button(constants.review());
		

		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getPhysicalActivityData();
			}
		});
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));
	
		HTMLPanel header = new HTMLPanel("<h3>" +
				constants.physical_act() + "</h3>");

		header.getElement().getStyle().setProperty("textDecoration", "underline");
		physicalActivityPanel.add(header);
		physicalActivityPanel.add(prev);
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		InlineLabel lbl = new InlineLabel(constants.physical_1());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		pnl.add(lbl);


		InlineLabel lbl2 = new InlineLabel(constants.physical_2());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");

		lbl2.setStyleName("flow");
		pnl.add(lbl2);
		physicalActivityPanel.add(pnl);


		InlineLabel sed = new InlineLabel(constants.sedentary());
		sed.getElement().getStyle().setProperty("marginLeft", "20px");
		sed.getElement().getStyle().setProperty("fontWeight", "bold");
		horizPanel.add(sed);
		sedBtn = new RadioButton("occupationBtn", "");

		horizPanel.add(sedBtn);
		horizPanel.add(getLogo(constants.sedent_popup()));

		InlineLabel standing = new InlineLabel(constants.standing());
		standing.getElement().getStyle().setProperty("fontWeight", "bold");
		standing.getElement().getStyle().setProperty("marginLeft", "80px");
		horizPanel.add(standing);
		standingBtn = new RadioButton("occupationBtn", "");
		horizPanel.add(standingBtn);
		horizPanel.add(getLogo(constants.standing_popup()));

		InlineLabel manual = new InlineLabel(constants.manual());
		manual.getElement().getStyle().setProperty("fontWeight", "bold");
		manual.getElement().getStyle().setProperty("marginLeft", "80px");
		horizPanel.add(manual);
		manualBtn = new RadioButton("occupationBtn", "");
		horizPanel.add(manualBtn);
		horizPanel.add(getLogo(constants.manual_popup()));


		
		InlineLabel heavyManual = new InlineLabel(constants.heavy_manual());
		heavyManual.getElement().getStyle().setProperty("fontWeight", "bold");
		heavyManual.getElement().getStyle().setProperty("marginLeft", "80px");
		horizPanel.add(heavyManual);
		heavyManualBtn = new RadioButton("occupationBtn", "");
		horizPanel.add(heavyManualBtn);
		horizPanel.add(getLogo(constants.heavy_manual_popup()));
		

		InlineLabel na = new InlineLabel(constants.na());
		na.getElement().getStyle().setProperty("fontWeight", "bold");
		na.getElement().getStyle().setProperty("marginLeft", "80px");
		horizPanel.add(na);
		naBtn = new RadioButton("occupationBtn", "");
		horizPanel.add(naBtn);
		
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));  
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));  

		physicalActivityPanel.add(horizPanel);
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));  
		InlineLabel lbl3= new InlineLabel(constants.typical_week());
		lbl3.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl3.getElement().getStyle().setProperty("textDecoration", "underline");
		physicalActivityPanel.add(lbl3);
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));  

		//Walking 
		addWalking();
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));  		

		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addCycling();
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));  
		addGardening();
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>")); 
		addHomeActivities();
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>")); 
		addPhysicalExercise();
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>")); 
		addHousework();
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>")); 

		 HorizontalPanel horiz = new HorizontalPanel();
		final HorizontalPanel vigor = new HorizontalPanel();
		InlineLabel lbl10 = new InlineLabel(constants.vigorous());
		lbl10.getElement().getStyle().setProperty("fontWeight", "bold");
		horiz.add(lbl10);
		sweatYes = new RadioButton("sweatGroup", constants.yes());
		// Listen for mouse events on the sweat yes button.
				sweatYes.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						vigor.setVisible(true);
					} 
					
		});
				
		sweatNo = new RadioButton("sweatGroup", constants.no());
		
		// Listen for mouse events on the sweat no button.
		sweatNo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vigor.setVisible(false);
			} 
			
		});
		horiz.add(sweatYes);
		horiz.add(sweatNo);
		physicalActivityPanel.add(horiz);
		InlineLabel label = new InlineLabel(constants.perform_vigorous());
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		vigor.add(label); 
	  
		vig = new DataField("", constants.hrs_week());
		vig.getElement().getStyle().setProperty("fontWeight", "bold");
		vig.getElement().getStyle().setProperty("marginLeft", "143px");
		
		vigor.add(vig);
		physicalActivityPanel.add(vigor);
		vigor.setVisible(false);
		// stairs climbed
		stairs = new DataField(constants.stairs(), constants.floors());
		stairs.getElement().getStyle().setProperty("fontWeight", "bold");
		physicalActivityPanel.add(stairs);

		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>")); 
		Button btn = new Button("submit");

		physicalActivityPanel.add(btn);

		// Listen for mouse events on the submit button.
		btn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (!checkQuestionResponses()) {
					return;
				
				}
				else updateActivityDB();
			}   		

		});

	

		scroll.setSize("100%", "70%");

		scroll.add(physicalActivityPanel);
		scroll.setAlwaysShowScrollBars(true);
		scroll.scrollToTop();
		FlowPanel physical = new FlowPanel();
		physical.add(scroll);;
		return physical;
	}


	private void addWalking() {
		
		InlineLabel lbl = new InlineLabel(constants.walking());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		
		walkingPanel1.add(lbl);
		
	   
	
		walkingSummer = new DataField(constants.summer(), constants.hrs_week());
		
		walkingSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		walkingSummer.getElement().getStyle().setProperty("marginLeft", "70px");
		walkingPanel1.add(walkingSummer);
		
		
		physicalActivityPanel.add(walkingPanel1);
		
		InlineLabel winter = new InlineLabel(constants.winter());
		InlineLabel suffix = new InlineLabel(constants.hrs_week());
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		
		walkingWinter = new TextBox();
		walkingWinter.setMaxLength(3);
		walkingWinter.setWidth("2em");
		walkingWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "75px");
		walkingWinter.getElement().getStyle().setProperty("marginLeft", "7px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		
		walkingPanel2.add(winter);
		walkingPanel2.add(walkingWinter);
		walkingPanel2.add(suffix);
		physicalActivityPanel.add(walkingPanel2);
		
	}
	
	private void addCycling(){
		
		InlineLabel cycling = new InlineLabel(constants.cycling());
		cycling.getElement().getStyle().setProperty("fontWeight", "bold");
		
		cyclingPanel1.add(cycling);
		
	    //cyclingPanel1.add(getLogo("Including cycling to work, shopping and leisure time"));
	  
		cyclingSummer = new DataField(constants.summer(), constants.hrs_week());
		cyclingSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		cyclingSummer.getElement().getStyle().setProperty("marginLeft", "80px");
		cyclingPanel1.add(cyclingSummer);
		physicalActivityPanel.add(cyclingPanel1);
		
		InlineLabel winter = new InlineLabel(constants.winter());
		InlineLabel suffix = new InlineLabel(constants.hrs_week());
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		cyclingWinter  = new TextBox();
		cyclingWinter.setMaxLength(3);
		cyclingWinter.setWidth("2em");
		cyclingWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "80px");
		cyclingWinter.getElement().getStyle().setProperty("marginLeft", "12px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		
		cyclingPanel2.add(winter);
		cyclingPanel2.add(cyclingWinter);
		cyclingPanel2.add(suffix);
		
		physicalActivityPanel.add(cyclingPanel2);
		
	}
	
	private void addGardening(){
		
		InlineLabel label = new InlineLabel(constants.gardening());
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		gardenPanel1.add(label);
		
	    //gardenPanel1.add(getLogo("Including cycling to work, shopping and leisure time"));
	  
		gardeningSummer = new DataField(constants.summer(), constants.hrs_week());
		gardeningSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		gardeningSummer.getElement().getStyle().setProperty("marginLeft", "75px");
		gardenPanel1.add(gardeningSummer);
		physicalActivityPanel.add(gardenPanel1);
		
		InlineLabel winter = new InlineLabel(constants.winter());
		InlineLabel suffix = new InlineLabel(constants.hrs_week());
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		gardeningWinter  = new TextBox();
		gardeningWinter.setMaxLength(3);
		gardeningWinter.setWidth("2em");
		gardeningWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "75px");
		gardeningWinter.getElement().getStyle().setProperty("marginLeft", "12px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		
		gardenPanel2.add(winter);
		gardenPanel2.add(gardeningWinter);
		gardenPanel2.add(suffix);
		
		physicalActivityPanel.add(gardenPanel2);
		
	}
	
	private void addHomeActivities(){
		
		InlineLabel label = new InlineLabel(constants.diy());
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		homePanel.add(label); 
	  
		diy = new DataField("", constants.hrs_week());
		diy.getElement().getStyle().setProperty("fontWeight", "bold");
		diy.getElement().getStyle().setProperty("marginLeft", "143px");
		homePanel.add(diy);
		physicalActivityPanel.add(homePanel);		
	}
	
	private void addPhysicalExercise() {
		
		InlineLabel label = new InlineLabel(constants.exercise());
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		exercisePanel1.add(label);
		
	
	
		
		
		
		exerciseSummer = new DataField(constants.summer(), constants.hrs_week());
		exerciseSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		exerciseSummer.getElement().getStyle().setProperty("marginLeft", "70px");
		exercisePanel1.add(exerciseSummer);
		
		InlineLabel winter = new InlineLabel(constants.winter());
		InlineLabel suffix = new InlineLabel(constants.hrs_week());
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		
		exerciseWinter  = new TextBox();
		exerciseWinter.setMaxLength(3);
		exerciseWinter.setWidth("2em");
		exerciseWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "70px");
		exerciseWinter.getElement().getStyle().setProperty("marginLeft", "12px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		
		exercisePanel2.add(winter);
		exercisePanel2.add(exerciseWinter);
		exercisePanel2.add(suffix);
		physicalActivityPanel.add(exercisePanel1);
		physicalActivityPanel.add(exercisePanel2);
		//physicalActivityPanel.add(exercisePanel3); 
		
	}
	
	private void addHousework() {
		
		InlineLabel label = new InlineLabel(constants.housework());
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		houseworkPanel1.add(label);
		
	    //houseworkPanel1.add(getLogo("such as cleaning, washing, cooking, child care etc."));
	  
		houseworkSummer = new DataField(constants.summer(), constants.hrs_week());
		houseworkSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		houseworkSummer.getElement().getStyle().setProperty("marginLeft", "60px");
		houseworkPanel1.add(houseworkSummer);
		physicalActivityPanel.add(houseworkPanel1);
		
		InlineLabel winter = new InlineLabel(constants.winter());
		InlineLabel suffix = new InlineLabel(constants.hrs_week());
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		houseworkWinter  = new TextBox();
		houseworkWinter.setMaxLength(3);
		houseworkWinter.setWidth("2em");
		houseworkWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "60px");
		houseworkWinter.getElement().getStyle().setProperty("marginLeft", "12px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		
		houseworkPanel2.add(winter);
		houseworkPanel2.add(houseworkWinter);
		houseworkPanel2.add(suffix);
		
		physicalActivityPanel.add(houseworkPanel2);
		
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
		popup.setPopupPosition(190,700);
		popup.setWidth("550px");
		popup.show();

	}
	
	private boolean checkQuestionResponses() {
		
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error);
			return false;
			
		}
		InlineLabel error;
		if (!(standingBtn.getValue() || manualBtn.getValue() || sedBtn.getValue() || heavyManualBtn.getValue() || naBtn.getValue()))  {
			error = new InlineLabel(constants.physical_error());
			showErrorPopupPanel(error);
			return false;
		}	
		checkHours(walkingSummer);
		checkHours(walkingWinter);
		checkHours(cyclingSummer);
		checkHours(cyclingWinter);
		checkHours(gardeningSummer);
		checkHours(gardeningWinter);
		checkHours(diy);		
		checkHours(exerciseSummer);
		checkHours(exerciseWinter);
		checkHours(houseworkSummer);
		checkHours(houseworkWinter);
		return true;
	}
	
	private boolean checkHours(DataField hours) {
		
		hours.getElement().getStyle().setProperty("color", "black");
		InlineLabel error;
		// get the hours entered
		double hoursActivity = 0;
		try {
			hoursActivity = Double.parseDouble(hours.getText());	
		}

		catch (Exception e)	{					
			//error = new InlineLabel("Invalid  hours. Please re-enter");
			//showErrorPopupPanel(error);
			//hours.getElement().getStyle().setProperty("color", "red");
			hours.setText("");				
			return false;

		}
		if ((hoursActivity < 0 || hoursActivity > 168)) {
			error = new InlineLabel("Invalid Hours.  Please re-enter.");
			showErrorPopupPanel(error);
			hours.getElement().getStyle().setProperty("color", "red");
			hours.setFocus();
			return false;

		}	
		return true;
	}
	
private boolean checkHours(TextBox hours) {
		
		hours.getElement().getStyle().setProperty("color", "black");
		InlineLabel error;
		// get the hours entered
		double hoursActivity = 0;
		try {
			hoursActivity = Double.parseDouble(hours.getText());	
		}

		catch (Exception e)	{					
			//error = new InlineLabel("Invalid  hours. Please re-enter");
			//showErrorPopupPanel(error);
			//hours.getElement().getStyle().setProperty("color", "red");
			hours.setText("");				
			return false;

		}
		if ((hoursActivity < 0 || hoursActivity > 168)) {
			error = new InlineLabel("Invalid Hours.  Please re-enter.");
			showErrorPopupPanel(error);
			hours.getElement().getStyle().setProperty("color", "red");
			hours.setFocus(true);
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

	private void updateActivityDB() {
	
	 callServiceSetup();
	 activity =  createActivityInfo();
	 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
		 @Override	 
       public void onSuccess(Boolean result) {
       		if ((result == false)){	            		
       			InlineLabel error = new InlineLabel("Physical Activity info not updated");
       			showErrorPopupPanel(error, "red");            			
       		}            		
       		else {
       			InlineLabel error = new InlineLabel("Physical Activity updated  -  Proceed to next Panel");
       			showErrorPopupPanel(error, "green");            			            			
       		}
            
         }
		@Override
		public void onFailure(Throwable caught) {
			InlineLabel error = new InlineLabel("Database update error");
			showErrorPopupPanel(error, "red");			
			
		}
	  };
	  
	  InminddServiceSvc.updatePhysicalActivity(activity, callback);
	}
	
	private PhysicalActivityInfo createActivityInfo() {
	
		activity = new PhysicalActivityInfo();
		activity.setUserId(login.getUserId());
		activity.setSummerWalkingHours(getValueAsInt(walkingSummer));
		activity.setWinterWalkingHours(getValueAsInt(walkingWinter));
		activity.setSummerCyclingHours(getValueAsInt(cyclingSummer));
		activity.setWinterCyclingHours(getValueAsInt(cyclingWinter));
		activity.setSummerGardenHours(getValueAsInt(gardeningSummer));
		activity.setWinterGardenHours(getValueAsInt(gardeningWinter));
		activity.setSummerPhysicalHours(getValueAsInt(exerciseSummer));
		activity.setWinterPhysicalHours(getValueAsInt(exerciseWinter));
		activity.setSummerHouseworkHours(getValueAsInt(houseworkSummer));
		activity.setWinterHouseworkHours(getValueAsInt(houseworkWinter));
		activity.setDiyHours(getValueAsInt(diy));
		activity.setFlightStairs(getValueAsInt(stairs));
		activity.setVigorousHours(getValueAsInt(vig));
		if (standingBtn.getValue()) {
			activity.setPhysicalWork("standing");
		}
		else if (manualBtn.getValue()) {
			activity.setPhysicalWork("manual");
		}
		else if (sedBtn.getValue()) {
			activity.setPhysicalWork("sedentary");
		}
		else if (heavyManualBtn.getValue()) {
			activity.setPhysicalWork("heavy");
		}
		else if (naBtn.getValue()) {
			activity.setPhysicalWork("na");
		}
		
		 if (sweatYes.getValue()) {
			 activity.setVigorous("yes");
		 }
		 
		 if (sweatNo.getValue()) {
			 activity.setVigorous("no");
		 }
		return activity;
	}
	
	private int getValueAsInt(DataField hours) {	
		
		// get the hours entered
		int hoursActivity = 0;
		try {
			hoursActivity = Integer.parseInt(hours.getText());	
		}

		catch (Exception e)	{					
						
			return 0;

		}
		return hoursActivity;
	}
	
private int getValueAsInt(TextBox hours) {
		
		
		
		// get the hours entered
		int hoursActivity = 0;
		try {
			hoursActivity = Integer.parseInt(hours.getText());	
		}

		catch (Exception e)	{					
						
			return 0;

		}
		return hoursActivity;
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
		popup.setPopupPosition(390,500);
		popup.setWidth("550px");
		popup.show();

	}
	 
	private Image getLogo(String logoTitle) {
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", "10px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle(logoTitle);
		
		return logo;
	}
	
	 private void getPhysicalActivityData() {				
		 User user = login.getUser();
		 if (user== null) {

			 InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			 showErrorPopupPanel(error, "red");
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<PhysicalActivityInfo> callback =  new AsyncCallback<PhysicalActivityInfo>(){

			 @Override	 
			 public void onSuccess(PhysicalActivityInfo physical) {
				 if (physical == null || physical.getUserId() == null){
					  
					 InlineLabel error = new InlineLabel("Physical Activity  Data not retrieved. No data available for this patient ");
					 showErrorPopupPanel(error, "red");            			
				 }            		
				 else {
					 InlineLabel error = new InlineLabel("Physical Activity data retrieved- Edit as necessary");
					 showErrorPopupPanel(error, "green");  
					 populatePanel(physical);
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 InlineLabel error = new InlineLabel("Physical Activity Database  error");
				 showErrorPopupPanel(error, "red");			

			 }
		 };

		 InminddServiceSvc.queryPhysicalActivity(user, callback);
		 return;
	 }
	 
	 private void populatePanel(PhysicalActivityInfo physical) {
		 if (physical.getPhysicalWork().equals("standing")) standingBtn.setValue(true); 
		 else if (physical.getPhysicalWork().equals("sedentary")) sedBtn.setValue(true);
		 else if (physical.getPhysicalWork().equals("manual")) manualBtn.setValue(true);
		 else if (physical.getPhysicalWork().equals("heavy")) heavyManualBtn.setValue(true);
		 else if (physical.getPhysicalWork().equals("na")) naBtn.setValue(true);
		 diy.setText(String.valueOf(physical.getDiyHours()));
		 walkingSummer.setText(String.valueOf(physical.getSummerWalkingHours()));
		 walkingWinter.setText(String.valueOf(physical.getWinterWalkingHours()));
		 cyclingSummer.setText(String.valueOf(physical.getSummerCyclingHours()));
		 cyclingWinter.setText(String.valueOf(physical.getWinterCyclingHours()));
		 gardeningSummer.setText(String.valueOf(physical.getSummerGardenHours()));
		 gardeningWinter.setText(String.valueOf(physical.getWinterGardenHours()));
		 exerciseSummer.setText(String.valueOf(physical.getSummerPhysicalHours()));
		 exerciseWinter.setText(String.valueOf(physical.getWinterPhysicalHours()));
		 houseworkSummer.setText(String.valueOf(physical.getSummerHouseworkHours()));
		 houseworkWinter.setText(String.valueOf(physical.getWinterHouseworkHours()));
		 if (physical.getVigorous().equals("yes")) sweatYes.setValue(true);
		 else if (physical.getVigorous().equals("no")) sweatNo.setValue(true);
		 stairs.setText(String.valueOf(physical.getFlightStairs()));
	 }
}

