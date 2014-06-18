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
	private HorizontalPanel walkingPanel1 = new HorizontalPanel();
	private HorizontalPanel walkingPanel2 = new HorizontalPanel();
	private HorizontalPanel cyclingPanel1 = new HorizontalPanel();
	private HorizontalPanel cyclingPanel2 = new HorizontalPanel();
	private HorizontalPanel gardenPanel1 = new HorizontalPanel();
	private HorizontalPanel gardenPanel2 = new HorizontalPanel();
	private HorizontalPanel homePanel = new HorizontalPanel();
	private HorizontalPanel exercisePanel1 = new HorizontalPanel();
	private HorizontalPanel exercisePanel2 = new HorizontalPanel();
	private HorizontalPanel houseworkPanel1 = new HorizontalPanel();
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
	private RadioButton sweatYes; 
	private RadioButton sweatNo; 
	private User user;
	private Login login;	
	PhysicalActivityInfo activity;
	
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
	}
	public FlowPanel setupPhysicalActivityPanel(Login login) {		
		this.login = login;	
		HTMLPanel mainheader = new HTMLPanel("<h1>" +
				"About Your Physical Activity</h1>");
		physicalActivityPanel.add(mainheader);
		Button prev = new Button("Retrieve previous data ?");
		

		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getPhysicalActivityData();
			}
		});
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));
	
		HTMLPanel header = new HTMLPanel("<h3>" +
				"The next questions are about your physical activity</h3>");

		header.getElement().getStyle().setProperty("textDecoration", "underline");
		physicalActivityPanel.add(header);
		physicalActivityPanel.add(prev);
		physicalActivityPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		InlineLabel lbl = new InlineLabel("First, we would like to know the type and amount of physical activity involved in your work.");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		pnl.add(lbl);


		InlineLabel lbl2 = new InlineLabel("From the five possibilities, please indicate which best corresponds with your present occupation :");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");

		lbl2.setStyleName("flow");
		pnl.add(lbl2);
		physicalActivityPanel.add(pnl);


		InlineLabel sed = new InlineLabel("Sedentary Occupation");
		sed.getElement().getStyle().setProperty("marginLeft", "20px");
		sed.getElement().getStyle().setProperty("fontWeight", "bold");
		horizPanel.add(sed);
		sedBtn = new RadioButton("occupationBtn", "");

		horizPanel.add(sedBtn);
		horizPanel.add(getLogo("You spend most of your time sitting (such as in an office)"));

		InlineLabel standing = new InlineLabel("Standing Occupation");
		standing.getElement().getStyle().setProperty("fontWeight", "bold");
		standing.getElement().getStyle().setProperty("marginLeft", "80px");
		horizPanel.add(standing);
		standingBtn = new RadioButton("occupationBtn", "");
		horizPanel.add(standingBtn);
		horizPanel.add(getLogo("You spend most of your time standing or walking. However, your work does not require intense physical effort (e.g shop assistant,  hairdresser,  guard,etc.)"));

		InlineLabel manual = new InlineLabel("Manual work");
		manual.getElement().getStyle().setProperty("fontWeight", "bold");
		manual.getElement().getStyle().setProperty("marginLeft", "80px");
		horizPanel.add(manual);
		manualBtn = new RadioButton("occupationBtn", "");
		horizPanel.add(manualBtn);
		horizPanel.add(getLogo("This involves some physical effort including handling of heavy objects and use of tools (e.g plumber,  electrician,  carpenter"));


		
		InlineLabel heavyManual = new InlineLabel("Heavy manual work");
		heavyManual.getElement().getStyle().setProperty("fontWeight", "bold");
		heavyManual.getElement().getStyle().setProperty("marginLeft", "80px");
		horizPanel.add(heavyManual);
		heavyManualBtn = new RadioButton("occupationBtn", "");
		horizPanel.add(heavyManualBtn);
		horizPanel.add(getLogo("This implies very vigorous physical effort including handling of very heavy objects (e.g docker,  miner,  bricklayer,  construction worker, etc.)"));
		

		InlineLabel na = new InlineLabel("Not applicable");
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
		InlineLabel lbl3= new InlineLabel("In a typical week during the past year, how many hours did you spend per week on each of the following activities :");
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
		InlineLabel lbl10 = new InlineLabel("In a typical week during the past year, did you engage in any of these activities" +
				" vigorously enough to cause sweating or faster heartbeat ?");
		lbl10.getElement().getStyle().setProperty("fontWeight", "bold");
		horiz.add(lbl10);
		sweatYes = new RadioButton("sweatGroup", "Yes");
		sweatNo = new RadioButton("sweatGroup", "No");
		horiz.add(sweatYes);
		horiz.add(sweatNo);
		physicalActivityPanel.add(horiz);
		// stairs climbed
		stairs = new DataField("In a typical week during the past year, how many flights of stairs did " +
				" you climb per day? ", "floors per day");
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
		
		InlineLabel lbl = new InlineLabel("Walking, including walking to work, shopping and leisure time");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		
		walkingPanel1.add(lbl);
		
	   // walkingPanel1.add(getLogo("Including walking to work, shopping and leisure time"));
	
		walkingSummer = new DataField("In Summer ", "hrs/week");
		
		walkingSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		walkingSummer.getElement().getStyle().setProperty("marginLeft", "70px");
		walkingPanel1.add(walkingSummer);
		
		
		physicalActivityPanel.add(walkingPanel1);
		
		InlineLabel winter = new InlineLabel("In Winter");
		InlineLabel suffix = new InlineLabel("hrs/Week");
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		walkingWinter = new TextBox();
		walkingWinter.setMaxLength(3);
		walkingWinter.setWidth("2em");
		walkingWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "485px");
		walkingWinter.getElement().getStyle().setProperty("marginLeft", "16px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		
		walkingPanel2.add(winter);
		walkingPanel2.add(walkingWinter);
		walkingPanel2.add(suffix);
		physicalActivityPanel.add(walkingPanel2);
		
	}
	
	private void addCycling(){
		
		InlineLabel cycling = new InlineLabel("Cycling,including cycling to work, shopping and leisure time");
		cycling.getElement().getStyle().setProperty("fontWeight", "bold");
		
		cyclingPanel1.add(cycling);
		
	    //cyclingPanel1.add(getLogo("Including cycling to work, shopping and leisure time"));
	  
		cyclingSummer = new DataField("In Summer ", "hrs/week");
		cyclingSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		cyclingSummer.getElement().getStyle().setProperty("marginLeft", "80px");
		cyclingPanel1.add(cyclingSummer);
		physicalActivityPanel.add(cyclingPanel1);
		
		InlineLabel winter = new InlineLabel("In Winter");
		InlineLabel suffix = new InlineLabel("hrs/Week");
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		cyclingWinter  = new TextBox();
		cyclingWinter.setMaxLength(3);
		cyclingWinter.setWidth("2em");
		cyclingWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "485px");
		cyclingWinter.getElement().getStyle().setProperty("marginLeft", "17px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		
		cyclingPanel2.add(winter);
		cyclingPanel2.add(cyclingWinter);
		cyclingPanel2.add(suffix);
		
		physicalActivityPanel.add(cyclingPanel2);
		
	}
	
	private void addGardening(){
		
		InlineLabel label = new InlineLabel("Gardening");
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		gardenPanel1.add(label);
		
	    //gardenPanel1.add(getLogo("Including cycling to work, shopping and leisure time"));
	  
		gardeningSummer = new DataField("In Summer ", "hrs/week");
		gardeningSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		gardeningSummer.getElement().getStyle().setProperty("marginLeft", "417px");
		gardenPanel1.add(gardeningSummer);
		physicalActivityPanel.add(gardenPanel1);
		
		InlineLabel winter = new InlineLabel("In Winter");
		InlineLabel suffix = new InlineLabel("hrs/Week");
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		gardeningWinter  = new TextBox();
		gardeningWinter.setMaxLength(3);
		gardeningWinter.setWidth("2em");
		gardeningWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "484px");
		gardeningWinter.getElement().getStyle().setProperty("marginLeft", "18px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		
		gardenPanel2.add(winter);
		gardenPanel2.add(gardeningWinter);
		gardenPanel2.add(suffix);
		
		physicalActivityPanel.add(gardenPanel2);
		
	}
	
	private void addHomeActivities(){
		
		InlineLabel label = new InlineLabel("Do-it-yourself activities at home");
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		homePanel.add(label); 
	  
		diy = new DataField("", "hrs/week");
		diy.getElement().getStyle().setProperty("fontWeight", "bold");
		diy.getElement().getStyle().setProperty("marginLeft", "345px");
		homePanel.add(diy);
		physicalActivityPanel.add(homePanel);		
	}
	
	private void addPhysicalExercise() {
		
		InlineLabel label = new InlineLabel("Physical exercise,");
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		exercisePanel1.add(label);
		
	   // exercisePanel1.add(getLogo("Such as fitness, aerobics,  swimming,  jogging,  tennis, etc."));
	  
		exerciseSummer = new DataField("In Summer ", "hrs/week");
		exerciseSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		exerciseSummer.getElement().getStyle().setProperty("marginLeft", "360px");
		exercisePanel1.add(exerciseSummer);
		physicalActivityPanel.add(exercisePanel1);
		InlineLabel label2 = new InlineLabel(" such as fitness, aerobics,  swimming,  jogging,  tennis, etc.");
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel winter = new InlineLabel("In Winter");
		InlineLabel suffix = new InlineLabel("hrs/Week");
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		exerciseWinter  = new TextBox();
		exerciseWinter.setMaxLength(3);
		exerciseWinter.setWidth("2em");
		exerciseWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "93px");
		exerciseWinter.getElement().getStyle().setProperty("marginLeft", "17px");
		suffix.getElement().getStyle().setProperty("marginLeft", "5px");
		exercisePanel2.add(label2);
		exercisePanel2.add(winter);
		exercisePanel2.add(exerciseWinter);
		exercisePanel2.add(suffix);
		
		physicalActivityPanel.add(exercisePanel2);
		
	}
	
	private void addHousework() {
		
		InlineLabel label = new InlineLabel("Housework,such as cleaning, washing, cooking, child care etc.");
		label.getElement().getStyle().setProperty("fontWeight", "bold");
		
		houseworkPanel1.add(label);
		
	    //houseworkPanel1.add(getLogo("such as cleaning, washing, cooking, child care etc."));
	  
		houseworkSummer = new DataField("In Summer ", "hrs/week");
		houseworkSummer.getElement().getStyle().setProperty("fontWeight", "bold");
		houseworkSummer.getElement().getStyle().setProperty("marginLeft", "60px");
		houseworkPanel1.add(houseworkSummer);
		physicalActivityPanel.add(houseworkPanel1);
		
		InlineLabel winter = new InlineLabel("In Winter");
		InlineLabel suffix = new InlineLabel("hrs/Week");
		suffix.getElement().getStyle().setProperty("fontWeight", "bold");
		houseworkWinter  = new TextBox();
		houseworkWinter.setMaxLength(3);
		houseworkWinter.setWidth("2em");
		houseworkWinter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("fontWeight", "bold");
		winter.getElement().getStyle().setProperty("marginLeft", "485px");
		houseworkWinter.getElement().getStyle().setProperty("marginLeft", "17px");
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
			error = new InlineLabel("Please select button regarding  your occupation");
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
		popup.setPopupPosition(190,700);
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

