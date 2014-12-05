package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.SmokeAlcoholInfo;
import com.inmindd.dcu.shared.User;

public class SmokeAlcohol {
	
	
	private static final int DECK_SMOKE_ALCOHOL = 8;
	private static final int DECK_DIET = 9;
	FlowPanel smokeAlcoholPanel;
	private TabLayoutPanel content;
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
	private  ListBox drinks = new ListBox();
	
	private ListBox drinksFrequency;
	private ListBox countryResident = new ListBox();
	private ListBox drinksBandIE = new ListBox();
	private ListBox drinksBandOther = new ListBox();
	private InlineLabel weeklyDrink = new InlineLabel();
	private User user;
	private Login login;
	private  Image logo = new Image();
	public static SmokeAlcohol lastinstance;
	private InminddServiceAsync InminddServiceSvc;
	private static final String LOGO_IMAGE_NAME = "Standard_drink_in_Ireland.png";
	private static Button btn;;
	public SmokeAlcohol() {
		lastinstance = this;
	}
	
	public void setContent(TabLayoutPanel content) {
		this.content = content;
	}
	static  InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	public static void clearInputs() {		
		lastinstance.currentSmoker.setValue(false);
		lastinstance.currentYearStart.setText("");
		lastinstance.drinks.setSelectedIndex(0);		
		lastinstance.drinksFrequency.setSelectedIndex(0);
		lastinstance.formerSmokePerDay.setText("");
		lastinstance.formerSmoker.setValue(false);
		lastinstance.formerYearStart.setText("");
		lastinstance.formerYearStop.setText("");
		lastinstance.neverSmoked.setValue(false);
		lastinstance.smokePerDay.setText("");
		lastinstance.logo.setVisible(false);
		lastinstance.countryResident.setSelectedIndex(0);
		lastinstance.drinksBandIE.setSelectedIndex(0);
		lastinstance.drinksBandOther.setSelectedIndex(0);
		
	}
	
	public FlowPanel  setupSmokeAlcoholPanel(Login login) {
		
		this.login = login;
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				constants.smoke_alcohol()+ "</h1>");
		Button prev = new Button(constants.review());


		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getSmokeAlcoholData();
			}
		});

	   // Smoking questions
		HTMLPanel header = new HTMLPanel("<h3>" +
				constants.habits()  + "</h3>");
		header.getElement().getStyle().setProperty("textDecoration", "underline");
		
		
		
		InlineLabel lbl = new InlineLabel(constants.describes());
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel = new FlowPanel();
		smokeAlcoholPanel.add(mainHeader);
		smokeAlcoholPanel.add(prev);
		smokeAlcoholPanel.add(header);
	
		smokeAlcoholPanel.add(lbl);
		
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		InlineLabel smoker = new InlineLabel(constants.current());
		
		smoker.getElement().getStyle().setProperty("fontWeight", "bold");
		currentSmoker = new RadioButton("smokerStatusButton", "");
		currentSmoker.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				currentYearStart.setFocus(true);
				currentYearStart.setText("");
				smokePerDay.setText("");
				
			}   		
		});
		
		startSmoke = new InlineLabel(constants.start_smoking());
		startSmoke.getElement().getStyle().setProperty("marginLeft", "25px");	
		startSmoke.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel.add(currentSmoker);
		smokeAlcoholPanel.add(smoker);
		
		smokeAlcoholPanel.add(startSmoke);
		currentYearStart = new PlaceholderTextBox();
		currentYearStart.setMaxLength(4);
		currentYearStart.setWidth("3em");
		currentYearStart.setPlaceholder(constants.yyyy());
		smokeAlcoholPanel.add(currentYearStart);
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		smokePerDay = new DataField(constants.current_smoke_per_day(), constants.per_day());

		smokePerDay.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel.add(smokePerDay);
		
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		
		InlineLabel lbl3 = new InlineLabel(constants.former_smoker());
		
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
		smokeAlcoholPanel.add(lbl3);
		InlineLabel lbl4 = new InlineLabel(constants.start_smoking());
		lbl4.getElement().getStyle().setProperty("marginLeft", "25px");	
		lbl4.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel.add(lbl4);
		formerYearStart = new PlaceholderTextBox();
		formerYearStart.setMaxLength(4);
		formerYearStart.setWidth("3em");
		formerYearStart.setPlaceholder(constants.yyyy());
		smokeAlcoholPanel.add(formerYearStart);
		
		InlineLabel lbl5 = new InlineLabel(constants.stop_smoking());
		//lbl5.setStyleName("flow");
		lbl5.getElement().getStyle().setProperty("marginLeft", "25px");	
		lbl5.getElement().getStyle().setProperty("fontWeight", "bold");
	
		smokeAlcoholPanel.add(lbl5);
		formerYearStop = new PlaceholderTextBox();
		formerYearStop.setMaxLength(4);
		formerYearStop.setWidth("3em");
		formerYearStop.setPlaceholder(constants.yyyy());
		smokeAlcoholPanel.add(formerYearStop);
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		formerSmokePerDay = new DataField(constants.former_smoke_per_day(), constants.per_day());
		formerSmokePerDay.getElement().getStyle().setProperty("fontWeight", "bold");
		smokeAlcoholPanel.add(formerSmokePerDay);
		
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		InlineLabel lbl6 = new InlineLabel(constants.never_smoked());
	
		lbl6.getElement().getStyle().setProperty("fontWeight", "bold");
		neverSmoked = new RadioButton("smokerStatusButton", "");
		
		smokeAlcoholPanel.add(neverSmoked);
		smokeAlcoholPanel.add(lbl6);
		
		// Alcohol Consumption
		
		HorizontalPanel hor1 = new HorizontalPanel();
		InlineLabel lbl7 = new InlineLabel(constants.how_often_drink());
		
		lbl7.getElement().getStyle().setProperty("fontWeight", "bold");
		hor1.add(lbl7);
		
		
		
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		HTMLPanel alcoholHeader = new HTMLPanel("<h3>" +
				constants.alcohol_consumption() + "</h3>");
		alcoholHeader.getElement().getStyle().setProperty("textDecoration", "underline");
		smokeAlcoholPanel.add(alcoholHeader);
		smokeAlcoholPanel.add(addDrinks());
		smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		setDrinksBandIE();
		setDrinksBandOther();
		smokeAlcoholPanel.add(getCountryResidence());
		
		
	    btn = new Button(constants.submit());
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
	       			InlineLabel error = new InlineLabel(constants.data_not_updated());
	       			showErrorPopupPanel(error, "red");            			
	       		}            		
	       		else {
	       			//InlineLabel error = new InlineLabel(constants.smoke_complete()); Deleted smoke_complete
	       			//showErrorPopupPanel(error, "green");   
	       			content.selectTab(DECK_DIET);
	       			content.getTabWidget(DECK_SMOKE_ALCOHOL).getElement().getStyle().setProperty("backgroundColor", "red");
	       		}
	            
	         }
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel(constants.data_not_updated());
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
		index = drinks.getSelectedIndex();
		if (index > 0) {
			smokeAlco.setNum_drinks(drinks.getItemText(index)); // for ireland
		}
	
		
		return smokeAlco;
	}
	
	private FlowPanel addDrinks() {
		FlowPanel drinkFreq = new FlowPanel();
		drinksFrequency =  new ListBox();
		InlineLabel theSelection = new InlineLabel(constants.how_often_drink());
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		drinksFrequency.addItem(constants.select_one());
        drinksFrequency.addItem(constants.never());
        drinksFrequency.addItem(constants.monthly());
        drinksFrequency.addItem(constants.two_to_four());
        drinksFrequency.addItem(constants.few_times_per_wk());
        drinksFrequency.addItem(constants.plus_times_per_wk());
       
        drinkFreq.add(theSelection);
        drinkFreq.add(drinksFrequency);
        
        drinkFreq.setWidth("100%");
	
        
		return drinkFreq;
	}
	
	private FlowPanel addDrinkBand() {
		FlowPanel drinkFreq = new FlowPanel();

		drinks.getElement().getStyle().setProperty("marginLeft", "25px");	
		drinks.getElement().getStyle().setProperty("marginLeft", "25px");	
		InlineLabel theSelection = new InlineLabel(constants.typical_drinks());
		theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
		

		drinks.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				drinks.setVisible(true);
			}
		});



		drinks.addItem(constants.select_one());
		drinks.addItem("7 or less");
		drinks.addItem("8 - 14");
		drinks.addItem("14 - 20");
		drinks.addItem("21+");


		drinks.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				drinks.setVisible(true);
			}
		});

		drinkFreq.add(theSelection);
		drinkFreq.add(drinks); 
		drinkFreq.add(drinks);

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
			
			InlineLabel error  = new InlineLabel(constants.register());
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		if (countryResident.getSelectedIndex() == 1) {  //Ireland
				drinks = drinksBandIE;
		}
		else if (!(countryResident.getSelectedIndex() == 1)) {
				drinks = drinksBandOther;
		}
		drinks.getElement().getStyle().setProperty("color", "black");
		drinks.getElement().getStyle().setProperty("color", "black");
		drinksFrequency.getElement().getStyle().setProperty("color", "black");
		currentYearStart.getElement().getStyle().setProperty("color", "blackd");
		smokePerDay.getElement().getStyle().setProperty("color", "black");
		formerYearStart.getElement().getStyle().setProperty("color", "black");
		formerYearStop.getElement().getStyle().setProperty("color", "black");
		formerSmokePerDay.getElement().getStyle().setProperty("color", "black");
		
		// check if all buttons selected
		if (!(currentSmoker.getValue() || formerSmoker.getValue() || neverSmoked.getValue())) {
			InlineLabel error = new InlineLabel(constants.smoking_status());
			showErrorPopupPanel(error, "red");
			return false;
		}		

		if (currentSmoker.getValue()) {
			if (currentYearStart.getValue() == "") {
				InlineLabel error = new InlineLabel(constants.smoking_error());
				showErrorPopupPanel(error, "red");
				currentYearStart.getElement().getStyle().setProperty("color", "red");				
				return false;
			}	
			
			if (smokePerDay.getDoubleValue() <= 0) {
				InlineLabel error = new InlineLabel(constants.current_smoke_per_day());
				showErrorPopupPanel(error, "red");
			
				smokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}
			if ((getValueAsInt(currentYearStart) < 1964) ||  getValueAsInt(currentYearStart) > 2015) {
				InlineLabel error = new InlineLabel(constants.smoking_error());
				showErrorPopupPanel(error, "red");
				currentYearStart.getElement().getStyle().setProperty("color", "red");
				smokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}
		}
		
		if (formerSmoker.getValue()) {
			if (formerYearStart.getValue() == "" ) {
				InlineLabel error = new InlineLabel(constants.smoking_error());
				showErrorPopupPanel(error, "red");
				formerYearStart.getElement().getStyle().setProperty("color", "red");
				formerYearStop.getElement().getStyle().setProperty("color", "red");
				formerSmokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}			
			if (formerYearStop.getValue() == "") {
				InlineLabel error = new InlineLabel(constants.stop_smoking());
				showErrorPopupPanel(error, "red");
				formerYearStart.getElement().getStyle().setProperty("color", "red");
				formerYearStop.getElement().getStyle().setProperty("color", "red");
				formerSmokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}		
			if ((getValueAsInt(formerYearStart) < 1964) ||  getValueAsInt(formerYearStart) > 2015  ) {
				InlineLabel error = new InlineLabel(constants.stop_smoking());
				showErrorPopupPanel(error, "red");
				formerYearStart.getElement().getStyle().setProperty("color", "red");
				formerSmokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}
			
			if ((getValueAsInt(formerSmokePerDay) <= 0)) {
				InlineLabel error = new InlineLabel(constants.former_smoke_per_day());
				showErrorPopupPanel(error, "red");
				formerYearStart.getElement().getStyle().setProperty("color", "red");
				formerSmokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}
			if ((getValueAsInt(formerYearStop) < 1964) ||  getValueAsInt(formerYearStop) > 2015 || getValueAsInt(formerYearStop) == 0) {
				InlineLabel error = new InlineLabel(constants.stop_smoking());
				showErrorPopupPanel(error, "red");
				formerYearStop.getElement().getStyle().setProperty("color", "red");
				formerSmokePerDay.getElement().getStyle().setProperty("color", "red");
				return false;
			}
		}
		if (drinksFrequency.getSelectedIndex() <= 0) {
			
			InlineLabel error = new InlineLabel(constants.how_often_drink());
			showErrorPopupPanel(error, "red");
			drinksFrequency.getElement().getStyle().setProperty("color", "red");
			return false;			   
		}
	
		if (drinks.getSelectedIndex() <= 0) {			
			InlineLabel error = new InlineLabel(constants.units_per_week());
			showErrorPopupPanel(error, "red");
			drinks.getElement().getStyle().setProperty("color", "red");
			return false;			   
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
		popup.setPopupPosition(190,500);
		popup.setWidth("700px");
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

			 InlineLabel error  = new InlineLabel(constants.register());
			 showErrorPopupPanel(error, "red");
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<SmokeAlcoholInfo> callback =  new AsyncCallback<SmokeAlcoholInfo>(){

			 @Override	 
			 public void onSuccess(SmokeAlcoholInfo smokeAlcohol) {
				 if ((smokeAlcohol == null || smokeAlcohol.getUserId() == null)){	            		
					 InlineLabel error = new InlineLabel(constants.unable_to_retrieve());
					 showErrorPopupPanel(error, "red");            			
				 }            		
				 else {
					 InlineLabel error = new InlineLabel(constants.retrieved());
					 showErrorPopupPanel(error, "green");  
					 populatePanel(smokeAlcohol);
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 InlineLabel error = new InlineLabel(constants.unable_retrieve_db());
				 showErrorPopupPanel(error, "red");			

			 }
		 };

		 InminddServiceSvc.querySmokeAlcohol(user, callback);
		 return;
	 }
	 
	 private void populatePanel(SmokeAlcoholInfo smokeAlcohol) {
		 drinks.setVisible(true);
		// smokeAlcoholPanel.remove(drinksBandIE);
		// smokeAlcoholPanel.remove(drinksBandOther);
		// smokeAlcoholPanel.add(drinksBandIE);
		// smokeAlcoholPanel.add(drinksBandOther);
		
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
		 //drinks.setValue(0, smokeAlcohol.getNum_drinks());
		 if (smokeAlcohol.getUserId().startsWith("11")) {  // irish participant 
			 String drinkUnits  = smokeAlcohol.getNum_drinks();
			 itemCount = drinksBandIE.getItemCount();
			 for (int index = 1; index <= itemCount; index++) {
				 if ( drinksBandIE.getItemText(index).equals(drinkUnits)) {
					 drinksBandIE.setSelectedIndex(index);
					 drinksBandIE.setVisible(true);
					 drinks = drinksBandIE;
					 drinks.setVisible(true);
					 break;
				 }
			 }
		 }
		 
		 if (!(smokeAlcohol.getUserId().startsWith("11"))) {  // other countries 
			 String drinkUnits  = smokeAlcohol.getNum_drinks();
			 itemCount = drinksBandOther.getItemCount();
			 for (int index = 1; index < itemCount; index++) {
				 if ( drinksBandOther.getItemText(index).equals(drinkUnits)) {
					 drinksBandOther.setSelectedIndex(index);
					 drinksBandOther.setVisible(true);
					 break;
				 }
			 }
		 }
		 
		 weeklyDrink.setVisible(true);
	 }
	 
	
	 private FlowPanel getCountryResidence() {

			FlowPanel country = new FlowPanel();
			InlineLabel theSelection = new InlineLabel(constants.alcohol_country());
			theSelection.getElement().getStyle().setProperty("fontWeight", "bold");
			
			countryResident.addItem(constants.select_one());			
			countryResident.addItem("Ireland:  Ierland, Irelande");
			countryResident.addItem("Scotland: Schotland, Ecosse");
			countryResident.addItem("The Netherlands: Nederland, Pays-Bas");
			countryResident.addItem("France: Frankrijk, France");
			country.add(theSelection);
			country.add(countryResident);
			
			countryResident.addChangeHandler(new ChangeHandler() 
				{ public void onChange(ChangeEvent event)
				{int selectedIndex = countryResident.getSelectedIndex();
				smokeAlcoholPanel.remove(logo);
				smokeAlcoholPanel.remove(btn);
				smokeAlcoholPanel.remove(drinksBandIE);
				smokeAlcoholPanel.remove(drinksBandOther);
				if (selectedIndex == 2) { // 		scotland
	
					displayAlcohol("Scottish_drinks.PNG");
	
				}
				
				if (selectedIndex == 1)	{ 		//ireland is different, we have a different drop down list box
				
					logo = getLogo("Standard_drink_in_Ireland.png");
					logo.setVisible(true);
					smokeAlcoholPanel.add(logo);
					
					smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
					smokeAlcoholPanel.add(weeklyDrink);
				
					smokeAlcoholPanel.add(drinksBandIE);
					
					smokeAlcoholPanel.add(btn);
					
					drinksBandIE.setVisible(true);
					weeklyDrink.setVisible(true);
	
	
				}
	
				if (selectedIndex == 3)	{  		// netherland
					displayAlcohol("Dutch_alcohol.png");
	
				}
				if (selectedIndex == 4)	{ 		// france
					displayAlcohol("French_drinks.PNG");
	
				}
				}
				});

			return country;
		}
	 
	 private void displayAlcohol(String logoName) {
		 logo = getLogo(logoName);
			logo.setVisible(true);
			smokeAlcoholPanel.add(logo);
			
			smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
			smokeAlcoholPanel.add(weeklyDrink);
			smokeAlcoholPanel.add(drinksBandOther);
		
			//smokeAlcoholPanel.add(new HTMLPanel("<span>  <br>  </span>"));
			smokeAlcoholPanel.add(btn);
			drinksBandOther.setVisible(true);
			
			weeklyDrink.setVisible(true);
	 }

	 private FlowPanel setDrinksBandIE() {
			
			
			FlowPanel bands = new FlowPanel();
			
			weeklyDrink.setText(constants.typical_drinks());
			weeklyDrink.getElement().getStyle().setProperty("fontWeight", "bold");
			
			drinksBandIE.addItem(constants.select_one());
			drinksBandIE.addItem(constants.seven_less());
			drinksBandIE.addItem("8 - 11");
			drinksBandIE.addItem("12 - 17");
			drinksBandIE.addItem("18+");
			drinksBandIE.setVisible(false);  
			weeklyDrink.setVisible(false);
			bands.add(weeklyDrink);
			bands.add(drinksBandIE);
				
			bands.setWidth("100%");

			        
			return bands;
		} 
	       
	 private FlowPanel setDrinksBandOther() {
			
			
			FlowPanel bands = new FlowPanel();
			
			weeklyDrink.setText(constants.typical_drinks());
			weeklyDrink.getElement().getStyle().setProperty("fontWeight", "bold");
			
			drinksBandOther.addItem(constants.select_one());
			drinksBandOther.addItem(constants.seven_less());
			drinksBandOther.addItem("8 - 14");
			drinksBandOther.addItem("14 - 20");
			drinksBandOther.addItem("21+");

			drinksBandOther.setVisible(false);  
			weeklyDrink.setVisible(false);
			bands.add(weeklyDrink);
			bands.add(drinksBandOther);
				
			bands.setWidth("100%");

			        
			return bands;
		} 

		/**
		 * Here we set up the logo by creating a new Alcohol table Image widget, 
		 */
		private Image getLogo(String logoName){
			// Create the logo image and prevent being able to drag it to browser location bar
			// by overriding its onBrowserEvent method.
			logo = new Image(GWT.getModuleBaseURL() + "../" + logoName){
				@Override
				public void onBrowserEvent(Event evt){
					// Comment out the next line to be able to drag logo to the browser location
					// bar; leave it in to prevent the default browser action.
					evt.preventDefault();
					
					// Play nice with the event system by bubbling the event upwards
					super.onBrowserEvent(evt);
				}
			
			};
			logo.setWidth("550px");
			logo.setHeight("450px");	
			logo.setVisible(false);
			return logo;
		}
}
