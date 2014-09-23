/**
 * 
 */
package com.inmindd.dcu.client;

/*
 * Entry point to the Inmindd Profler Web Application
 * 
 * @Author Noel O'Kelly DCU School of Computing
 */


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.User;
import com.google.gwt.user.client.ui.TextBox;






import org.gwtwidgets.client.*;
import org.gwtwidgets.client.ui.ProgressBar;


/**
 * 
 * This is the code for GWT In Action's Chapter 3 main example.
 * It demonstrates the use of a number of panels and widgets, 
 * and a number of ways of adding widgets to the application.
 * 
 * There are 3 "pages" - home, products and contact that you 
 * can move between and are also navigatable using the browser's
 * forward and back buttons.
 * 
 * A WindowClosing handler is also added in the history handling to 
 * inform you if you have hit the back button so much that you 
 * are about to leave the application.
 *
 */
public class InminddProfiler implements EntryPoint, ValueChangeHandler<String> {

	
	
	/**
	 * Numerical values to reference the tabs the content pages are held in.
	 */
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
	
	/**
	 * Strings representing the history tokens we will use to indicate which tab content
	 * the user is viewing.
	 */
	static final String TOKEN_PATIENT = constants.about_you();
	static final String TOKEN_CESD = constants.about_mood();	
	static final String TOKEN_MEDICAL= constants.medical();
	static final String TOKEN_HISTORY = constants.family_medical();
	static final String TOKEN_PHYSICAL = constants.physical();
	static final String TOKEN_COGNITIVE1 = constants.cog_active_1();
	static final String TOKEN_COGNITIVE2 = constants.cog_active_2();
	static final String TOKEN_SMOKE_ALCOHOL = constants.smoking_drink();
	static final String TOKEN_DIET = constants.diet_tab();
	static final String TOKEN_LOGIN = "Login";
	
	
	
	private User user;
	
	/**
	 * The filename of our logo image
	 */
	private static final String LOGO_IMAGE_NAME = "inmindd.jpg";
	
	/**
	 * panel for user validation
	 */
	ScrollPanel scroll = new ScrollPanel();
	//FlowPanel val = new FlowPanel();
	/**
	 * Login Panel
	 */
	Login login;
	/**
	 * A popup panel that will be displayed if the search button is selected. 
	 */
	 PopupPanel searchRequest;
	
	/**
	 * An enumeration covering the pages that will be involved in the application
	 * and the history.
	 * 
	 * Each enumerated item contain 2 pieces of information: a number that
	 * relates to the deck card number, and a String that relates to the history token.
	 * 
	 * This is just our way of associating history token to tab panel index
	 * 
	 */
	enum Pages { LOGIN(DECK_LOGIN, TOKEN_LOGIN),
		PATIENT(DECK_PATIENT, TOKEN_PATIENT), CESD(DECK_CESD, TOKEN_CESD),  MEDICAL(DECK_MEDICAL, TOKEN_MEDICAL)
			, FAMILY_HISTORY(DECK_HISTORY, TOKEN_HISTORY), PHYSICAL(DECK_PHYSICAL, TOKEN_PHYSICAL)
			, COGNITIVE_1(DECK_COGNITIVE1, TOKEN_COGNITIVE1), COGNITIVE_2(DECK_COGNITIVE2, TOKEN_COGNITIVE2), 
			SMOKE_ALCOHOL(DECK_SMOKE_ALCOHOL, TOKEN_SMOKE_ALCOHOL),  DIET(DECK_DIET, TOKEN_DIET); 
		
		// Holds the card number in the deck this enumeration relates to. 
		private int val;
		// Holds the history token value this enumeration relates to.
		private String text;
		
		// Simple method to get the card number in the deck this enumeration relates to.
		int getVal(){return val;}
		// Simple method to get the history token this enumeration relates to.
		String getText(){return text;}

		// Enumeration constructor that stores the card number and history token for this enumeration.
		Pages(int val, String text) {
			this.val = val;
			this.text = text;
		};
	}

	/**
	 * Returns the HTML content of an existing DOM element on the HTML page.
	 * 
	 * Should be careful with these type of methods if you are going to use the data
	 * later to ensure people are not injecting scripts into your code.
	 * In our example, we control the HTML that the data is retrieved from.
	 * 
	 * @param id The id of the DOM element we wish to get the content for.
	 * @return The HTML content of the DOM element.
	 */
	private String getContent(String id) {
		// Initialize the return string.
		String toReturn = "";
	
		// Find the DOM element by the id passed in.
		Element element = DOM.getElementById(id);
		
		// Make sure we've found the DOM element and then manipulate it.
		if (element!=null){
		
			// Get the inner HTML content of the DOM element.
			toReturn = DOM.getInnerHTML(element);

		
			// Set the inner value of the DOM element to an empty string
			// if we don't do this, then it is still displayed on the screen.
			DOM.setInnerText(element, "");
		
			// Comment the following two lines of code out to not use SafeHTML to create the response.
			// If we use it, then this makes sure the HTML we have from the HTML page is sanitized against 
			// any XSS attacks.  In this example's case, the hyperlink in contacts page is sanitized, i.e.
			// you cannot click on it.  
			// This can be seen as overkill in this case, but security should always be at the heart of
			// your development (it is too large a topic for us to cover within the GWT in Action book).
			SafeHtml sfHtml = SimpleHtmlSanitizer.sanitizeHtml(toReturn);
			toReturn = sfHtml.asString();
		} else {
			// If we can't find the content then let's just put an error message in the content
			// (You can test this by changing the id of the DOM elements in the HTML page - you probably need
			// to clear your browser's cache to see the impact of any changes you make).
			toReturn = "Unable to find "+id+" content in HTML page";
		}
		return toReturn;
	}

	/**
	 * This TabLayoutPanel will hold the application's "pages" of content.
	 */
	TabLayoutPanel content;

	/**
	 * The image logo. for INmindd
	 */
	Image logo;
	
	Image euFlag;
	

	
	/*
	 * Here we set up the logo by creating a new Image widget, and prevent the 
	 * default browser action from occuring on it.
	 */
	private void insertLogo(){
		// Create the logo image and prevent being able to drag it to browser location bar
		// by overriding its onBrowserEvent method.
		logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME){
			@Override
			public void onBrowserEvent(Event evt){
				// Comment out the next line to be able to drag logo to the browser location
				// bar; leave it in to prevent the default browser action.
				evt.preventDefault();
				
				// Play nice with the event system by bubbling the event upwards
				super.onBrowserEvent(evt);
			}
		
		};
		logo.setWidth("100px");
		logo.setHeight("45px");	
		euFlag =  new Image(GWT.getModuleBaseURL() + "../" + "EU_flag.jpg");
		euFlag.setWidth("75px");
		euFlag.setHeight("60px");	
		euFlag.setStyleName("pos10");
			
	}
	
	public void startProfiler() {
		
		// Create the user interface
  		setUpGui();		
  		// Set up history management
  		setUpHistoryManagement();
  		// Set up all the event handling required for the application.
  		setUpEventHandling();	
	}

	/**
	 * Here we set up the event handling that we will drive user interaction.
	 * 
	 * 1.  A SelectionHandler for when a new tab is selected.
	 * 2.  A ClickHandler for if the search button is clicked.
	 * 3.  Some Mouse handlers and ClickHandler if the feedback tab is interacted with.
	 * 
	 * You don't have to follow this style of programming and put all your event handling code
	 * into one method, we do it here as it makes sense and helps us examine particular aspects
	 * of code in one place (however, by doing it this way instead of, for example adding handlers
	 * directly after defining widgets, means we should check each widget is not null before 
	 * adding the handler - we won't as by inspection we know all widgets are instantiated elsewhere 
	 * before this method is called; but you should be aware of these type of dependencies in your
	 * own code). 
	 * 
	 */
	private void setUpEventHandling(){
		
		/**
		 *  If a tab is selected then we want to add a new history item to the History object.
		 *  (this effectively changes the token in the URL, which is detected and handled by 
		 *  GWT's History sub-system.
		 */
		content.addSelectionHandler(new SelectionHandler<Integer>(){
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				// Determine the tab that has been selected by interrogating the event object.
				Integer tabSelected = event.getSelectedItem();
				
				// Create a new history item for this tab (using data retrieved from Pages enumeration)
				History.newItem(Pages.values()[tabSelected].getText());
			}
		});	
		
	
	}
	
	
	/**
	 * The HTMLPanels that will hold the content we want to display in the TabPanel.
	 */
	
	FlowPanel patientPanel;
	FlowPanel medPanel;
	FlowPanel med2Panel;
	HTMLPanel csd;
	HTMLPanel csd2;
	DockLayoutPanel cesdPanel;
	DockLayoutPanel cesdPanel2;
	FlowPanel historyPanel; 
	//ScrollPanel historyPanel;
	FlowPanel activityPanel;
	FlowPanel cognitiveOnePanel;
	FlowPanel cognitiveTwoPanel;
	FlowPanel smokeAlcoholPanel;
	FlowPanel dietPanel;
	VerticalPanel loginPanel; 
	/**
	 * We'll build the tab panel's content from the HTML that is already in the HTML
	 * page.
	 */
	private void buildTabContent(){

		// Create the login Panel
		login = new Login();
		loginPanel = login.setupLoginPanel();// Create the login Panel

	
		// Create the Patient Info Panel

		PatientInfo patient = new PatientInfo();		
		patientPanel = patient.setupPatientPanel(login);
		
		patientPanel.addStyleName("paddedTextBox");


		// Create the Patient Feelings Panel

		Feelings cesd = new Feelings();
		csd = new HTMLPanel(getContent(Pages.CESD.getText()));	
		cesdPanel = cesd.setupCesdPanel(login);		
		csd.add(cesdPanel);
		
		
		// Create the Medical Health Panel		
		MedicalHealth medical = new MedicalHealth();
		medPanel = medical.setupMedicalHealthPanel(login);
				
		// Create the Family medical history panel
		FamilyMedicalHistory history = new FamilyMedicalHistory();
		
		historyPanel =history.setupFamilyMedicalHistoryPanel(login);
		
		// Create the physical activity panel
		
		PhysicalActivity physicalPanel  = new PhysicalActivity();
		activityPanel = physicalPanel.setupPhysicalActivityPanel(login);
		
		// create the cognitive 1 panel
		CognitiveOne cognitiveOne = new CognitiveOne();
		cognitiveOnePanel = cognitiveOne.setupCognitiveOnePanel(login);
		
		// create the cognitive 2 panel
		CognitiveTwo cognitiveTwo = new CognitiveTwo();
		cognitiveTwoPanel = cognitiveTwo.setupCognitiveTwoPanel(login);
				
		// create the Smoking & Alcohol Panel
		SmokeAlcohol smokeAlcohol = new SmokeAlcohol();
		smokeAlcoholPanel = smokeAlcohol.setupSmokeAlcoholPanel(login);
		// Create the Diet Panel
		Diet diet = new Diet();
		dietPanel = diet.setupDietPanel(login);
		
		
		// set the style of HTMLPanels
		
		csd.addStyleName("htmlPanel");
		
		cesdPanel.addStyleName("htmlPanel");
		
		medPanel.addStyleName("paddedTextBox");
		
		// Create the tab panel widget
		//content.add(progress);
		content = new TabLayoutPanel(15, Unit.PCT);
		
		
		


		// Add the content we have just created to the tab panel widget
		content.add(loginPanel, Pages.LOGIN.getText());
	
		content.add(patientPanel, Pages.PATIENT.getText());
	
		content.add(cesdPanel, Pages.CESD.getText());
		content.add(medPanel, Pages.MEDICAL.getText());		
		content.add(historyPanel, Pages.FAMILY_HISTORY.getText());
		content.add(activityPanel, Pages.PHYSICAL.getText());
		content.add(cognitiveOnePanel, Pages.COGNITIVE_1.getText());
		content.add(cognitiveTwoPanel, Pages.COGNITIVE_2.getText());
		content.add(smokeAlcoholPanel, Pages.SMOKE_ALCOHOL.getText());
		content.add(dietPanel, Pages.DIET.getText());
		content.getElement().getStyle().setProperty("fontWeight", "normal");
		
		// Indicate that we should show the HOME tab initially.
		content.selectTab(DECK_LOGIN);
		login.setContent(content);
		patient.setContent(content);
		cesd.setContent(content);
		medical.setContent(content);
		history.setContent(content);
		physicalPanel.setContent(content);
		cognitiveOne.setContent(content);
		cognitiveTwo.setContent(content);
		smokeAlcohol.setContent(content);
		diet.setContent(content);
		
	}	
	
	/**
	 * Style the tab panel using methods in the UIObject class.
	 */
	private void styleTabPanelUsingUIObject(){
	
		content.setHeight("900px");  // don;t change this
	
	}
	
	
	

	/**
	 * Sets up the GUI components used in the application
	 * 
	 * 1. A TabPanel that holds "page" content from the original HTML page
	 * 2. A search button that is from the original HTML page
	 * 3. An image for the logo
	 * 4. A feedback bar.
	 * 
	 */
	private void setUpGui() {
		// Build the TabPanel content from existing HTML page text
		buildTabContent();

		// Insert a logo into a defined slot in the HTML page
		insertLogo();

		// Style the TabPanel using methods from the UIObject it inherits
		styleTabPanelUsingUIObject();	

		content.setSize("100%",  "130%");
	
		Label welLabel = new Label(constants.welcome());
		Label euLabel = new Label(constants.euFunding());
		euLabel.getElement().getStyle().setProperty("fontWeight", "normal");
		// Add the logo to the DOM element with id of "logo"
		RootPanel logoSlot = RootPanel.get("logo");
		if (logoSlot!=null)logoSlot.add(logo);
		logoSlot.add(euFlag);
		RootPanel euSlot = RootPanel.get("euSlot");
		if (euSlot!=null)euSlot.add(euLabel, 100,65);
	
		RootPanel welcomeSlot = RootPanel.get("welcome");
		if (welcomeSlot != null) welcomeSlot.add(welLabel);
		
		// Add the TabPanel to the DOM element with the id of "content"
		RootPanel contentSlot = RootPanel.get("content");

		if (contentSlot!=null) contentSlot.add(content);
		 
		RootLayoutPanel rootLayoutPanel = RootLayoutPanel.get();
		
		rootLayoutPanel.add(content);


	}

	
	
	/**
	 * This is the entry point method which will create the GUI and set up the History handling.
	 */
	

	/**
	 * Set up the History management for the application.
	 */
	public void setUpHistoryManagement(){
		// Make this class your history manager (see onValueChange method)
		History.addValueChangeHandler(this);
		// Handle any existing history token
		History.fireCurrentHistoryState();
		// Trap user hitting back button too many times.
		/*Window.addWindowClosingHandler(new ClosingHandler(){
			@Override
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("Ran out of history.  Now leaving application, is that OK?");
			}
		});*/
	}

	
	 @Override
		public void onModuleLoad() {
			
		 
	// Create the user interface
		setUpGui();		
	// Set up history management
		setUpHistoryManagement();
		//Set up all the event handling required for the application.
		setUpEventHandling();	
}
	
	/**
	 * This is the function that handles history change events.
	 * 
	 * When the history token is changed in the URL, GWT fires a
	 * ValueChangeEvent that is handled in this method (since we called
	 * History.addValueChangeHandler(this) in the onModuleLoad method).
	 * 
	 * The history token is the part of the URL that follows the hash symbol.
	 * For example http://www.someurl.se/MyApp.html#home has the token "home".
	 */
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
	// Put this back !!!!
		 User user = login.getUser();
				 if (user.getUserId() == null ) {

					// InlineLabel error  = new InlineLabel("You must first log in or register with InMindd");
					// showErrorPopupPanel(error);
					 showLogin();
					 return;

				 } 
	
		// Get the token from the event
		String page = event.getValue().trim();
		// Check if the token is null or empty
		
		if ((page == null) || (page.equals("")))
			showLogin();
		
		else if (page.equals(Pages.PATIENT.getText()))
			showPatientInfo();	
		else if (page.equals(Pages.CESD.getText()))
			showFeelings();
		else if (page.equals(Pages.MEDICAL.getText()))
			showMedical();
		else if (page.equals(Pages.FAMILY_HISTORY.getText()))
			showFamilyMedicalHistory();
		else if (page.equals(Pages.PHYSICAL.getText()))
			showPhysicalActivity();
		else if (page.equals(Pages.COGNITIVE_1.getText()))
			showCognitiveOne();
		else if (page.equals(Pages.COGNITIVE_2.getText()))
			showCognitiveTwo();
		else if (page.equals(Pages.SMOKE_ALCOHOL.getText()))
			showSmokeAlcohol();
		else if (page.equals(Pages.DIET.getText()))
			showDiet();
		else if (page.equals(Pages.LOGIN.getText()))
			showLogin();
		else
			showLogin();
	}
	
	
	/**
	 * Show the feelings page - i.e. place a new label on the current screen
	 */
	private void showFeelings() {
		content.selectTab(Pages.CESD.getVal());
		
	}

	/**
	 * Show the  - patient details
	 */
	private void showPatientInfo() {
		
		content.selectTab(Pages.PATIENT.getVal());
			
		
	}

	/**
	 * Show the medical health page
	 */
	private void showMedical() {
		content.selectTab(Pages.MEDICAL.getVal());		
		
	}
	
	/**
	 * Show the family history page
	 */
	private void showFamilyMedicalHistory() {
		content.selectTab(Pages.FAMILY_HISTORY.getVal());
	
	}
	
	/**
	 * Show the physical activity
	 */
	private void showPhysicalActivity() {
		content.selectTab(Pages.PHYSICAL.getVal());
	
	}
	
	/**
	 * Show the cognitive activity page 1
	 */
	private void showCognitiveOne() {
		content.selectTab(Pages.COGNITIVE_1.getVal());
	
	}

	
	/**
	 * Show the cognitive activity page 2
	 */
	private void showCognitiveTwo() {
		content.selectTab(Pages.COGNITIVE_2.getVal());
	
	}
	
	/**
	 * Show the smoking alcohol page
	 */
	private void showSmokeAlcohol() {
		content.selectTab(Pages.SMOKE_ALCOHOL.getVal());
	
	}

	
	/**
	 * Show the diet page
	 */
	private void showDiet() {
		content.selectTab(Pages.DIET.getVal());
	
	}
	
	/**
	 * Show the login page
	 */
	private void showLogin() {
		content.selectTab(Pages.LOGIN.getVal());
	
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
		popup.setPopupPosition(470,240);
		popup.setWidth("550px");
		popup.show();

	}

}
