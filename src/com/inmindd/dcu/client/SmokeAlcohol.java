package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.CognitiveOneInfo;
import com.inmindd.dcu.shared.FamilyHistoryInfo;
import com.inmindd.dcu.shared.SmokeAlcoholInfo;
import com.inmindd.dcu.shared.User;

public class SmokeAlcohol {
	FlowPanel smokeAlcoholPanel;
	private SmokeAlcoholInfo smokeAlco;
	private ScrollPanel scroll = new ScrollPanel();
	private RadioButton currentSmoker;
	private DataField smokePerDay;
	private RadioButton formerSmoker;
	private PlaceholderTextBox currentYearStart;
	private PlaceholderTextBox formerYearStart;
	private PlaceholderTextBox formerYearStop;
	private DataField formerSmokePerDay;
	private RadioButton neverSmoked;
	private InlineLabel startSmoke;
	private final ListBox drinksIrish = new ListBox();
	private final ListBox drinksNonIrish = new ListBox();;
	private ListBox drinksFrequency;
	private User user;
	private Login login;
	public static SmokeAlcohol lastinstance;
	private InminddServiceAsync InminddServiceSvc;
	public SmokeAlcohol() {
		lastinstance = this;
	}
	
	public static void clearInputs() {
		
		lastinstance.currentSmoker.setValue(false);
		lastinstance.currentYearStart.setText("");
		lastinstance.drinksIrish.setSelectedIndex(0);
		lastinstance.drinksNonIrish.setSelectedIndex(0);
		lastinstance.drinksFrequency.setSelectedIndex(0);
		lastinstance.formerSmokePerDay.setText("");
		lastinstance.formerSmoker.setValue(false);
		lastinstance.formerYearStart.setText("");
		lastinstance.formerYearStop.setText("");
		lastinstance.neverSmoked.setValue(false);
		lastinstance.smokePerDay.setText("");
	
		
	}
	
	public FlowPanel  setupSmokeAlcoholPanel(Login login) {
		
		this.login = login;
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				"About your Smoking habits and Alcohol consumption</h1>");
		Button prev = new Button("Retrieve previous data ?");


		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getSmokeAlcoholData();
			}
		});

	   // Smoking questions
		HTMLPanel header = new HTMLPanel("<h3>" +
				"These next questions are about your smoking habits</h3>");
		header.getElement().getStyle().setProperty("textDecoration", "underline");
		
		
		
		InlineLabel lbl = new InlineLabel("Please indicate which of the following best describes you ? ");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel = new FlowPanel();
		smokeAlcoholPanel.add(mainHeader);
		smokeAlcoholPanel.add(prev);
		smokeAlcoholPanel.add(header);
	
		smokeAlcoholPanel.add(lbl);
		
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		InlineLabel smoker = new InlineLabel("Current smoker ");
		
		smoker.getElement().getStyle().setProperty("fontWeight", "bold");
		currentSmoker = new RadioButton("smokerStatusButton", "");
		currentSmoker.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				currentYearStart.setFocus(true);
				currentYearStart.setText("");
				smokePerDay.setText("");
				
			}   		
		});
		
		startSmoke = new InlineLabel("Roughly when did you start smoking? ");
		startSmoke.getElement().getStyle().setProperty("marginLeft", "25px");	
		startSmoke.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel.add(smoker);
		smokeAlcoholPanel.add(currentSmoker);
		smokeAlcoholPanel.add(startSmoke);
		currentYearStart = new PlaceholderTextBox();
		currentYearStart.setMaxLength(4);
		currentYearStart.setWidth("3em");
		currentYearStart.setPlaceholder("yyyy");
		smokeAlcoholPanel.add(currentYearStart);
		smokePerDay = new DataField("Please indicate the number of cigarettes, cigarillos, cigars, pipes or" +
				" any other tobacco products that you typically smoke per day ", "per day");

		smokePerDay.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel.add(smokePerDay);
		
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		
		InlineLabel lbl3 = new InlineLabel("Former smoker ");
		smokeAlcoholPanel.add(lbl3);
		lbl3.getElement().getStyle().setProperty("fontWeight", "bold");
		formerSmoker = new RadioButton("smokerStatusButton", "");
		formerSmoker.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				formerYearStart.setFocus(true);
				formerYearStart.setText("");
				formerYearStop.setText("");
				formerSmokePerDay.setText("");
			}   		
		});
		smokeAlcoholPanel.add(formerSmoker);
		InlineLabel lbl4 = new InlineLabel("Roughly when did you start smoking? ");
		lbl4.getElement().getStyle().setProperty("marginLeft", "25px");	
		lbl4.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel.add(lbl4);
		formerYearStart = new PlaceholderTextBox();
		formerYearStart.setMaxLength(4);
		formerYearStart.setWidth("3em");
		formerYearStart.setPlaceholder("yyyy");
		smokeAlcoholPanel.add(formerYearStart);
		
		InlineLabel lbl5 = new InlineLabel("Roughly when did you stop smoking ? ");
		//lbl5.setStyleName("flow");
		lbl5.getElement().getStyle().setProperty("marginLeft", "25px");	
		lbl5.getElement().getStyle().setProperty("fontWeight", "bold");
	
		smokeAlcoholPanel.add(lbl5);
		formerYearStop = new PlaceholderTextBox();
		formerYearStop.setMaxLength(4);
		formerYearStop.setWidth("3em");
		formerYearStop.setPlaceholder("yyyy");
		smokeAlcoholPanel.add(formerYearStop);
		
		formerSmokePerDay = new DataField("Please indicate the number of cigarettes, cigarillos, cigars, pipes or" +
				" any other tobacco products that you would have typically smoked per day ", "per day");
		formerSmokePerDay.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel.add(formerSmokePerDay);
		
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		InlineLabel lbl6 = new InlineLabel("Have never smoked");
		smokeAlcoholPanel.add(lbl6);
		lbl6.getElement().getStyle().setProperty("fontWeight", "bold");
		neverSmoked = new RadioButton("smokerStatusButton", "");
		
		smokeAlcoholPanel.add(neverSmoked);
		
		// Alcohol Consumption
		
		HorizontalPanel hor1 = new HorizontalPanel();
		InlineLabel lbl7 = new InlineLabel("How often do you have a drink containing alcohol ?");
		hor1.add(lbl7);
		lbl7.getElement().getStyle().setProperty("fontWeight", "bold");
		
		//smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		HTMLPanel alcoholHeader = new HTMLPanel("<h3>" +
				"These next questions are about your alcohol consumption</h3>");
		alcoholHeader.getElement().getStyle().setProperty("textDecoration", "underline");
		smokeAlcoholPanel.add(alcoholHeader);
		
		smokeAlcoholPanel.add(addDrinks());
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		smokeAlcoholPanel.add(addGrid());
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
	    smokeAlcoholPanel.add(addDrinkQuantity());
	    smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
	    Button btn = new Button("submit");
		smokeAlcoholPanel.add(btn);
		
		
		

		// Listen for mouse events on the submit button.
		btn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				if (!validateInput()) {
					return;
				}
				
				updateSmokingAlcoholDB();
			}   		

		});
		scroll.setSize("100%", "70%");
		scroll.add(smokeAlcoholPanel);
		scroll.setAlwaysShowScrollBars(true);
		scroll.scrollToTop();
		FlowPanel smokeAlcohol = new FlowPanel();
		smokeAlcohol.add(scroll);
		
		return smokeAlcohol;
	
		
	}
	
	
	private boolean callServiceSetup() {
		// set up rpc call

		InminddServiceSvc = (InminddServiceAsync) GWT.create(InminddService.class);
		ServiceDefTarget target = (ServiceDefTarget) InminddServiceSvc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "Inmindd";
		target.setServiceEntryPoint(moduleRelativeURL);	    			
		return true;

	}
	private void updateSmokingAlcoholDB() {
		
		 callServiceSetup();
		 smokeAlco  = createSmokeAlco();
		 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
			 @Override	 
	       public void onSuccess(Boolean result) {
	       		if ((result == false)){	            		
	       			InlineLabel error = new InlineLabel("Smoking & Alcohol  info not updated");
	       			showErrorPopupPanel(error, "red");            			
	       		}            		
	       		else {
	       			InlineLabel error = new InlineLabel("Smoking & Alcohol  updated  -  Proceed to next Panel");
	       			showErrorPopupPanel(error, "green");            			            			
	       		}
	            
	         }
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel("Database update error");
				showErrorPopupPanel(error, "red");			
				
			}
		  };
		  
		  InminddServiceSvc.updateSmokeAlcohol(smokeAlco, callback);
		}
		
		
	private SmokeAlcoholInfo createSmokeAlco() {
		String smokerType = "";
		smokeAlco = new SmokeAlcoholInfo();
		User user = login.getUser();
		smokeAlco.setUserId(user.getUserId());
		if (currentSmoker.getValue()) {
			smokerType= "current";
		}
		else if (formerSmoker.getValue()) {
			smokerType = "former";
		}
		else if (neverSmoked.getValue()) {
			smokerType = "never";
		}
		smokeAlco.setSmoker_type(smokerType);

		if (smokerType.equals("current")) {
			smokeAlco.setCurrent_year_start(getValueAsInt(currentYearStart));
			smokeAlco.setCurrent_num_smoke(getValueAsInt(smokePerDay));
		}


		if (smokerType.equals("former")) {
			smokeAlco.setFormer_year_start(getValueAsInt(formerYearStart));
			smokeAlco.setFormer_year_stop(getValueAsInt(formerYearStop));
			smokeAlco.setFormer_num_smoke(getValueAsInt(formerSmokePerDay));
		}
		
		//held as description, not as integers
		int index = drinksFrequency.getSelectedIndex();
		smokeAlco.setDrinks_freq(drinksFrequency.getItemText(index));
		// ditto
		index = drinksIrish.getSelectedIndex();
		if (index > 0) {
			smokeAlco.setNum_drinks(drinksIrish.getItemText(index)); // for ireland
		}
		index = drinksNonIrish.getSelectedIndex();
		if (index > 0) {
			smokeAlco.setNum_drinks(drinksNonIrish.getItemText(index)); // other countries
		}
		
		return smokeAlco;
	}
	
	private FlowPanel addDrinks() {
		FlowPanel drinkFreq = new FlowPanel();
		drinksFrequency =  new ListBox();
		InlineLabel theSelection = new InlineLabel("How often do you have a drink containing alcohol ? ");
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		drinksFrequency.addItem("Please select one");
        drinksFrequency.addItem("Never");
        drinksFrequency.addItem("Monthly or less");
        drinksFrequency.addItem("2-4 times per month");
        drinksFrequency.addItem("2-3 times per week");
        drinksFrequency.addItem("4 or more times per week");
       
        drinkFreq.add(theSelection);
        drinkFreq.add(drinksFrequency);
        
        drinkFreq.setWidth("100%");
	
        
		return drinkFreq;
	}
	
	private FlowPanel addDrinkQuantity() {
		FlowPanel drinkFreq = new FlowPanel();

		drinksNonIrish.getElement().getStyle().setProperty("marginLeft", "25px");	
		drinksNonIrish.getElement().getStyle().setProperty("marginLeft", "25px");	
		InlineLabel theSelection = new InlineLabel("Using the above table, how many standard drinks do you have" +
				"  in A TYPICAL WEEK when you are drinking ?");
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		drinksIrish.addItem("Please select one - If in Ireland");
		//User user = login.getUser();

		//String userId = user.getUserId();


		drinksIrish.addItem("7 or less");
		drinksIrish.addItem("8 - 11");
		drinksIrish.addItem("12 - 17");
		drinksIrish.addItem("18+");

		drinksIrish.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				drinksNonIrish.setVisible(true);
			}
		});



		drinksNonIrish.addItem("Please select one - If not in Ireland");
		drinksNonIrish.addItem("7 or less");
		drinksNonIrish.addItem("8 - 14");
		drinksNonIrish.addItem("14 - 20");
		drinksNonIrish.addItem("21+");


		drinksNonIrish.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				drinksIrish.setVisible(true);
			}
		});

		drinkFreq.add(theSelection);
		drinkFreq.add(drinksIrish); 
		drinkFreq.add(drinksNonIrish);

		drinkFreq.setWidth("100%");


		return drinkFreq;
	}
	
	private Grid addGrid() {
		Grid g = new Grid(7, 3);

		// Put some values in the grid cells.
		g.setBorderWidth(8);	      
		g.setHTML(0, 0, "<b>BEVERAGE</b>");
		g.setHTML(0, 1, "<b>SERVING SIZE DESCRIPTION</b>");
		g.setHTML(0, 2, "<b>SERVING SIZE</b>");
		g.setText(1, 0, "Beer");
		g.setText(1, 1, "Half pint");
		g.setText(1, 2, "284ml");
		g.setText(2, 0, "Cider");
		g.setText(2, 1, "Half pint");
		g.setText(2, 2, "284ml");
		g.setText(3, 0, "Alcopops");
		g.setText(3, 1, "Long neck bottle");
		g.setText(3, 2, "275ml");
		g.setText(4, 0, "Wine");
		g.setText(4, 1, "Small glass");
		g.setText(4, 2, "100ml");
		g.setText(5, 0, "Liquers");
		g.setText(5, 1, "Glass");
		g.setText(5, 2, "71ml");
		g.setText(6, 0, "Spirits");
		g.setText(6, 1, "Single measure");
		g.setText(6, 2, "35.5ml");
		smokeAlcoholPanel.add(g);
		// You can use the CellFormatter to affect the layout of the grid's cells.
		g.getCellFormatter().setWidth(0, 0, "128px");
		
		return g;
	}
	
	
	private boolean validateInput() {
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		drinksIrish.getElement().getStyle().setProperty("color", "black");
		drinksNonIrish.getElement().getStyle().setProperty("color", "black");
		drinksFrequency.getElement().getStyle().setProperty("color", "black");
		currentYearStart.getElement().getStyle().setProperty("color", "blackd");
		smokePerDay.getElement().getStyle().setProperty("color", "black");
		formerYearStart.getElement().getStyle().setProperty("color", "black");
		formerYearStop.getElement().getStyle().setProperty("color", "black");
		formerSmokePerDay.getElement().getStyle().setProperty("color", "black");
		
		// check if all buttons selected
		if (!(currentSmoker.getValue() || formerSmoker.getValue() || neverSmoked.getValue())) {
			InlineLabel error = new InlineLabel("Please select a button to indicate your Smoker status");
			showErrorPopupPanel(error, "red");
			return false;
		}		
		
		if (drinksFrequency.getSelectedIndex() <= 0) {
			
			InlineLabel error = new InlineLabel("Please indicate the frequncy of drinks per week");
			showErrorPopupPanel(error, "red");
			drinksFrequency.getElement().getStyle().setProperty("color", "red");
			return false;			   
		}
		
		if (drinksIrish.getSelectedIndex() <= 0 && drinksNonIrish.getSelectedIndex() <= 0) {			
			InlineLabel error = new InlineLabel("Please indicate the number of standard drinks per week");
			showErrorPopupPanel(error, "red");
			drinksIrish.getElement().getStyle().setProperty("color", "red");
			return false;			   
		}

		if (currentSmoker.getValue()) {
			if (currentYearStart.getValue() == "" || smokePerDay.getDoubleValue() == 0) {
				InlineLabel error = new InlineLabel("Please input current smoking data");
				showErrorPopupPanel(error, "red");
				currentYearStart.getElement().getStyle().setProperty("color", "red");
				smokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}			
		}
		
		if (formerSmoker.getValue()) {
			if (formerYearStart.getValue() == "" || formerYearStop.getValue() == "" || formerSmokePerDay.getDoubleValue() == 0) {
				InlineLabel error = new InlineLabel("Please input former smoking data");
				showErrorPopupPanel(error, "red");
				formerYearStart.getElement().getStyle().setProperty("color", "red");
				formerYearStop.getElement().getStyle().setProperty("color", "red");
				formerSmokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}			
		}
		return true;

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
	public class PlaceholderTextBox extends TextBox
	
	{
	    public void setPlaceholder(String placeholder)
	    {
	        InputElement inputElement = getElement().cast();
	        inputElement.setAttribute("placeholder", placeholder);
	    }
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
	
	private int getValueAsInt(PlaceholderTextBox box) {		
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
	
	 private void getSmokeAlcoholData() {				
		 User user = login.getUser();
		 if (user== null) {

			 InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			 showErrorPopupPanel(error, "red");
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<SmokeAlcoholInfo> callback =  new AsyncCallback<SmokeAlcoholInfo>(){

			 @Override	 
			 public void onSuccess(SmokeAlcoholInfo smokeAlcohol) {
				 if ((smokeAlcohol == null || smokeAlcohol.getUserId() == null)){	            		
					 InlineLabel error = new InlineLabel("Smoke Alcohol Data not retrieved. No data available for this patient ");
					 showErrorPopupPanel(error, "red");            			
				 }            		
				 else {
					 InlineLabel error = new InlineLabel("Smoke Alcohol data retrieved- Edit as necessary");
					 showErrorPopupPanel(error, "green");  
					 populatePanel(smokeAlcohol);
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 InlineLabel error = new InlineLabel("Smoke Alcohol data Database error");
				 showErrorPopupPanel(error, "red");			

			 }
		 };

		 InminddServiceSvc.querySmokeAlcohol(user, callback);
		 return;
	 }
	 private void populatePanel(SmokeAlcoholInfo smokeAlcohol) {
		 drinksNonIrish.setVisible(true);
		 drinksIrish.setVisible(true);
		 if (smokeAlcohol.getSmoker_type().equals("current")){currentSmoker.setValue(true);}
		 if (smokeAlcohol.getSmoker_type().equals("former")){formerSmoker.setValue(true);}
		 if (smokeAlcohol.getSmoker_type().equals("never")){neverSmoked.setValue(true);}
		 smokePerDay.setText(Integer.toString(smokeAlcohol.getCurrent_num_smoke()));
		 formerSmokePerDay.setText(Integer.toString(smokeAlcohol.getFormer_num_smoke()));
		 currentYearStart.setText(Integer.toString(smokeAlcohol.getCurrent_year_start())); 
		 formerYearStart.setText(Integer.toString(smokeAlcohol.getFormer_year_start())); 
		 formerYearStop.setText(Integer.toString(smokeAlcohol.getFormer_year_stop())); 
		 String drinksFreq = smokeAlcohol.getDrinks_freq();
		 int itemCount = drinksFrequency.getItemCount();
		 for (int index = 0; index < itemCount; index++) {
			 if ( drinksFrequency.getItemText(index).equals(drinksFreq)) {
				 drinksFrequency.setSelectedIndex(index);
				 break;
			 }
		 }
		 drinksIrish.setValue(0, smokeAlcohol.getNum_drinks());
		 if (smokeAlcohol.getUserId().startsWith("11")) {  // irish participant 
			 String drinks_num  = smokeAlcohol.getNum_drinks();
			 itemCount = drinksIrish.getItemCount();
			 for (int index = 0; index < itemCount; index++) {
				 if ( drinksIrish.getItemText(index).equals(drinks_num)) {
					 drinksIrish.setSelectedIndex(index);
					 break;
				 }
			 }
		 }
		 
		 if (!(smokeAlcohol.getUserId().startsWith("11"))) {  // non- irish participant different standard units
			 String drinks_freq  = smokeAlcohol.getNum_drinks();
			 itemCount = drinksNonIrish.getItemCount();
			 for (int index = 0; index < itemCount; index++) {
				 if ( drinksNonIrish.getItemText(index).equals(drinks_freq)) {
					 drinksNonIrish.setSelectedIndex(index);
					 break;
				 }
			 }
		 }
	 }
}