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
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.CognitiveTwoInfo;
import com.inmindd.dcu.shared.DietInfo;
import com.inmindd.dcu.shared.User;

public class Diet {
	private FlowPanel dietPanel;
	private DietInfo diet;
	private RadioButton culFatYes;
	private RadioButton culFatNo;
	private RadioButton oilZero;
	private RadioButton oilTwo;
	private RadioButton oilFour;
	private RadioButton vegZero;
	private RadioButton vegThree;
	private RadioButton vegFive;
	private RadioButton fruitZero;
	private RadioButton fruitOne;
	private RadioButton fruitTwo;
	private RadioButton fruitThree;
	private RadioButton meatZero;
	private RadioButton meatOne;
	private RadioButton butterZero;
	private RadioButton butterOne;
	private RadioButton beverageZero;
	private RadioButton beverageOne;
	private RadioButton wineZero;
	private RadioButton wineSeven;
	private RadioButton legumesZero;
	private RadioButton legumesThree;	
	private RadioButton fishZero;
	private RadioButton fishThree;
	private RadioButton cakeZero;
	private RadioButton cakeThree;
	private RadioButton nutsZero;
	private RadioButton nutsThree;
	private RadioButton chickenYes;
	private RadioButton chickenNo;
	private RadioButton pastaZero;
	private RadioButton pastaTwo;
	
	private ScrollPanel scroll = new ScrollPanel();
	private User user;
	private Login login;
	private InminddServiceAsync InminddServiceSvc;
	public static Diet lastinstance;
	
	static  InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	public Diet() {	
		lastinstance = this;
	}
	
	public static void clearInputs() {
		
		lastinstance.beverageOne.setValue(false);
		lastinstance.beverageZero.setValue(false);
		lastinstance.butterOne.setValue(false);
		lastinstance.butterZero.setValue(false);
		lastinstance.cakeThree.setValue(false);
		lastinstance.cakeZero.setValue(false);
		lastinstance.chickenNo.setValue(false);
		lastinstance.chickenYes.setValue(false);
		lastinstance.culFatNo.setValue(false);
		lastinstance.culFatYes.setValue(false);
		lastinstance.fishThree.setValue(false);
		lastinstance.fishZero.setValue(false);
		lastinstance.fruitOne.setValue(false);
		lastinstance.fruitTwo.setValue(false);
		lastinstance.fruitThree.setValue(false);
		lastinstance.fruitZero.setValue(false);
		lastinstance.legumesThree.setValue(false);
		lastinstance.legumesZero.setValue(false);
		lastinstance.meatOne.setValue(false);
		lastinstance.meatZero.setValue(false);
		lastinstance.nutsThree.setValue(false);
		lastinstance.nutsZero.setValue(false);
		lastinstance.oilFour.setValue(false);
		lastinstance.oilZero.setValue(false);
		lastinstance.oilTwo.setValue(false);
		lastinstance.pastaZero.setValue(false);
		lastinstance.pastaTwo.setValue(false);
		lastinstance.vegThree.setValue(false);
		lastinstance.vegFive.setValue(false);;
		lastinstance.vegZero.setValue(false);
		lastinstance.wineSeven.setValue(false);
		lastinstance.wineZero.setValue(false);
		
		
	}
	
	
	public FlowPanel setupDietPanel(Login login) {
		this.login = login;
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				constants.diet() + "</h1>");
		Button prev = new Button(constants.review());


		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getDietData();
			}
		});
	
		HTMLPanel lbl1 = new HTMLPanel("<h3>" +
				constants.diet_questions() + "</h3>");
		lbl1.getElement().getStyle().setProperty("textDecoration", "underline");
		
	
	
		dietPanel = new FlowPanel();
		dietPanel.add(mainHeader);
		dietPanel.add(prev);
		dietPanel.setWidth("100%");
		
		dietPanel.add(lbl1);
		
		// rape
		addCulinaryFat();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addOilConsume();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addVegetables();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addFruit();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addMeat();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addButter();	  
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addBeverages();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addWine();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addLegumes();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addFish();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addCake();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addNuts();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addChicken();
		dietPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		addPasta();
		
		Button btn = new Button("submit");
		dietPanel.add(btn);
		

		 // Listen for mouse events on the submit button.
	    btn.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		if (checkUser()) {
	    			if (validateInput()) {
	    			updateDietDB();
	    			}
	    		}
	    	}
	     
	    });
		scroll.setSize("100%", "70%");
		scroll.add(dietPanel);
		scroll.setAlwaysShowScrollBars(true);
		scroll.scrollToTop();
		FlowPanel diet = new FlowPanel();
		diet.add(scroll);
			
		return diet;
	  

}
	
	private boolean checkUser() {
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error, "red");
			return  false;
			
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
	private void updateDietDB() {
		
			 callServiceSetup();
			 diet  = createDietInfo();
			 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
				 @Override	 
		       public void onSuccess(Boolean result) {
		       		if ((result == false)){	            		
		       			InlineLabel error = new InlineLabel("Diet  info not updated");
		       			showErrorPopupPanel(error, "red");            			
		       		}            		
		       		else {
		       			InlineLabel error = new InlineLabel("Diet  updated  -  Proceed to next Panel");
		       			showErrorPopupPanel(error, "green");            			            			
		       		}
		            
		         }
				@Override
				public void onFailure(Throwable caught) {
					InlineLabel error = new InlineLabel("Database update error");
					showErrorPopupPanel(error, "red");			
					
				}
			  };
			  
			  InminddServiceSvc.updateDiet(diet, callback);
	}
		

	private boolean validateInput() {
		if (!(culFatYes.getValue() || culFatNo.getValue())) {
			InlineLabel error = new InlineLabel("Please answer culinary Fat question");
   			showErrorPopupPanel(error, "red"); 
   			culFatYes.getElement().getStyle().setProperty("color","red");
   			culFatNo.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(oilZero.getValue() || oilTwo.getValue() | oilFour.getValue())) {
			InlineLabel error = new InlineLabel("Please answer olive oil  question");
   			showErrorPopupPanel(error, "red"); 
   			oilZero.getElement().getStyle().setProperty("color","red");
   			oilTwo.getElement().getStyle().setProperty("color","red");
   			oilFour.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(vegZero.getValue() || vegThree.getValue() || vegFive.getValue())) {
			InlineLabel error = new InlineLabel("Please answer vegetable servings  question");
   			showErrorPopupPanel(error, "red"); 
   			vegZero.getElement().getStyle().setProperty("color","red");
   			vegThree.getElement().getStyle().setProperty("color","red");
   			vegFive.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(fruitZero.getValue() || fruitOne.getValue() || fruitTwo.getValue() || fruitThree.getValue())) {
			InlineLabel error = new InlineLabel("Please answer fruit  servings  question");
   			showErrorPopupPanel(error, "red"); 
   			fruitZero.getElement().getStyle().setProperty("color","red");
   			fruitOne.getElement().getStyle().setProperty("color","red");
   			fruitTwo.getElement().getStyle().setProperty("color","red");
   			fruitThree.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(meatZero.getValue() || meatOne.getValue())) {
			InlineLabel error = new InlineLabel("Please answer meat question");
   			showErrorPopupPanel(error, "red"); 
   			meatZero.getElement().getStyle().setProperty("color","red");
   			meatOne.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(butterZero.getValue() || butterOne.getValue())) {
			InlineLabel error = new InlineLabel("Please answer butter question");
   			showErrorPopupPanel(error, "red"); 
   			butterZero.getElement().getStyle().setProperty("color","red");
   			butterOne.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(beverageZero.getValue() || beverageOne.getValue())) {
			InlineLabel error = new InlineLabel("Please answer sweet beverages question");
   			showErrorPopupPanel(error, "red"); 
   			beverageZero.getElement().getStyle().setProperty("color","red");
   			beverageOne.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(wineZero.getValue() || wineSeven.getValue())) {
			InlineLabel error = new InlineLabel("Please answer wine consumption question");
   			showErrorPopupPanel(error, "red"); 
   			wineZero.getElement().getStyle().setProperty("color","red");
   			wineSeven.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(legumesZero.getValue() || legumesThree.getValue())) {
			InlineLabel error = new InlineLabel("Please answer legumes  question");
   			showErrorPopupPanel(error, "red"); 
   			legumesZero.getElement().getStyle().setProperty("color","red");
   			legumesThree.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(fishZero.getValue() || fishThree.getValue())) {
			InlineLabel error = new InlineLabel("Please answer fish consumption question");
   			showErrorPopupPanel(error, "red"); 
   			fishZero.getElement().getStyle().setProperty("color","red");
   			fishThree.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(cakeZero.getValue() || cakeThree.getValue())) {
			InlineLabel error = new InlineLabel("Please answer cakes question");
   			showErrorPopupPanel(error, "red"); 
   			cakeZero.getElement().getStyle().setProperty("color","red");
   			cakeThree.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		if (!(nutsZero.getValue() || nutsThree.getValue())) {
			InlineLabel error = new InlineLabel("Please answer nuts question");
   			showErrorPopupPanel(error, "red"); 
   			nutsZero.getElement().getStyle().setProperty("color","red");
   			nutsThree.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		
		if (!(chickenYes.getValue() || chickenNo.getValue())) {
			InlineLabel error = new InlineLabel("Please answer chicken  question");
   			showErrorPopupPanel(error, "red"); 
   			chickenYes.getElement().getStyle().setProperty("color","red");
   			chickenNo.getElement().getStyle().setProperty("color","red");
   			return false;
		}		

		if (!(pastaZero.getValue() || pastaTwo.getValue())) {
			InlineLabel error = new InlineLabel("Please answer pasta sauce / question");
   			showErrorPopupPanel(error, "red"); 
   			pastaZero.getElement().getStyle().setProperty("color","red");
   			pastaTwo.getElement().getStyle().setProperty("color","red");
   			return false;
		}
		return true;
		
	}	
		
	private DietInfo createDietInfo() {
		
		diet = new DietInfo();
		User user = login.getUser();
		diet.setUserId(user.getUserId());
		if (culFatYes.getValue()) {
			diet.setCulinaryFat(1);
		}
		if (culFatNo.getValue()) {
			diet.setCulinaryFat(0);
		}
		if (oilZero.getValue()) {
			diet.setRapeSeedOil(0);
		}
		if (oilTwo.getValue()) {
			diet.setRapeSeedOil(2);
		}
		if (oilFour.getValue()) {
			diet.setRapeSeedOil(4);
		}
		
		if (vegZero.getValue()) {
			diet.setVegetableServings(0);
		}
		if (vegThree.getValue()) {
			diet.setVegetableServings(3);
		}
		if (vegFive.getValue()) {
			diet.setVegetableServings(5);
		}
		
		if (fruitZero.getValue()) {
			diet.setFruit(0);
		}
		if (fruitOne.getValue()) {
			diet.setFruit(1);
		}
		if (fruitTwo.getValue()) {
			diet.setFruit(2);
		}
		if (fruitThree.getValue()) {
			diet.setFruit(3);
		}
		
		if (meatZero.getValue() ) {
			diet.setRedMeat(0);
		}
		if (meatOne.getValue() ) {
			diet.setRedMeat(1);
		}
		
		if (butterZero.getValue() ) {
			diet.setButter(0);
		}
		if (butterOne.getValue() ) {
			diet.setButter(1);
		}
		if (beverageZero.getValue() ) {
			diet.setBeverages(0);
		}
		if (beverageOne.getValue() ) {
			diet.setBeverages(1);
		}
		if (wineZero.getValue() ) {
			diet.setWine(0);
		}
		if (wineSeven.getValue() ) {
			diet.setWine(7);
		}
		
		if (legumesZero.getValue() ) {
			diet.setLegumes(0);
		}
		if (legumesThree.getValue() ) {
			diet.setLegumes(3);
		}
		if (fishZero.getValue() ) {
			diet.setFish(0);
		}
		if (fishThree.getValue() ) {
			diet.setFish(3);
		}
		if (cakeZero.getValue() ) {
			diet.setSweets(0);
		}
		if (cakeThree.getValue() ) {
			diet.setSweets(3);
		}
		if (nutsZero.getValue() ) {
			diet.setNuts(0);
		}
		if (nutsThree.getValue() ) {
			diet.setNuts(3);
		}
		if (chickenYes.getValue() ) {
			diet.setChicken(1);
		}
		if (chickenNo.getValue() ) {
			diet.setChicken(0);
		}
		
		if (pastaZero.getValue() ) {
			diet.setSauce(0);
		}
		if (pastaTwo.getValue() ) {
			diet.setSauce(2);
		}
		return diet;
	}
	
	
	
	private InlineLabel addLabel(String label) {
		InlineLabel lbl = new InlineLabel(label);
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl.getElement().getStyle().setProperty("marginLeft", "10px");
		
		
		return lbl;
	}
	
	private void addCulinaryFat() {
		dietPanel.add(addLabel(constants.culinary_fat()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		culFatYes =  new RadioButton("cul",constants.yes());
		culFatYes.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(culFatYes);
		culFatNo = new RadioButton("cul",constants.no());
		culFatNo.getElement().getStyle().setProperty("marginLeft","20px");
		pnl1.add(culFatNo);
		dietPanel.add(pnl1);
	}
	
	private void addOilConsume() {		
		dietPanel.add(addLabel(constants.oil()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		oilZero = new RadioButton("consume",constants.tablespoon_1());
		oilZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(oilZero);
		oilTwo = new RadioButton("consume",constants.tablespoon_2());
		oilTwo.getElement().getStyle().setProperty("marginLeft","20px");
		pnl1.add(oilTwo);
		
		oilFour = new RadioButton("consume",constants.tablespoon_4());
		oilFour.getElement().getStyle().setProperty("marginLeft","20px");
		pnl1.add(oilFour);
		dietPanel.add(pnl1);
		
	}
	
	private void addVegetables() {		
		dietPanel.add(addLabel(constants.veg_servings()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		vegZero = new RadioButton("veg","0-2");
		vegZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(vegZero);
		vegThree = new RadioButton("veg","3-4");
		vegThree.getElement().getStyle().setProperty("marginLeft","112px");
		pnl1.add(vegThree);
		
		vegFive = new RadioButton("veg",constants.five());
		vegFive.getElement().getStyle().setProperty("marginLeft","113px");
		pnl1.add(vegFive);
		dietPanel.add(pnl1);
		
	}
	
	private void addFruit() {		
		dietPanel.add(addLabel(constants.fruit_units()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		fruitZero = new RadioButton("fruit","0");
		fruitZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(fruitZero);
		fruitOne = new RadioButton("fruit","1");
		fruitOne.getElement().getStyle().setProperty("marginLeft","112px");
		pnl1.add(fruitOne);
		
		fruitTwo = new RadioButton("fruit","2");
		fruitTwo.getElement().getStyle().setProperty("marginLeft","113px");
		pnl1.add(fruitTwo);
		
		fruitThree = new RadioButton("fruit",constants.three());
		fruitThree.getElement().getStyle().setProperty("marginLeft","113px");
		pnl1.add(fruitThree);
		dietPanel.add(pnl1);
		
	}
	
	private void addMeat() {		
		dietPanel.add(addLabel(constants.meat()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		meatZero = new RadioButton("meat",constants.less_1());
		meatZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(meatZero);
		meatOne = new RadioButton("meat",constants.more_1());
		meatOne.getElement().getStyle().setProperty("marginLeft","53px");
		pnl1.add(meatOne);			
		dietPanel.add(pnl1);
		
	}
	
	private void addButter() {		
		dietPanel.add(addLabel(constants.butter()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		butterZero = new RadioButton("butter",constants.less_1());
		butterZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(butterZero);
		butterOne = new RadioButton("butter",constants.more_1());
		butterOne.getElement().getStyle().setProperty("marginLeft","53px");
		pnl1.add(butterOne);		
		dietPanel.add(pnl1);
		
	}
	private void addBeverages() {		
		dietPanel.add(addLabel(constants.sweet_beverages()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		beverageZero = new RadioButton("sweet",constants.less_1());
		beverageZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(beverageZero);
		beverageOne = new RadioButton("sweet",constants.more_1());
		beverageOne.getElement().getStyle().setProperty("marginLeft","53px");
		pnl1.add(beverageOne);			
		dietPanel.add(pnl1);
		
	}
	
	private void addWine() {		
		dietPanel.add(addLabel(constants.wine()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		wineZero = new RadioButton("wine",constants.six_glasses());
		wineZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(wineZero);
		wineSeven = new RadioButton("wine",constants.seven_glasses());
		wineSeven.getElement().getStyle().setProperty("marginLeft","46px");
		pnl1.add(wineSeven);		
		dietPanel.add(pnl1);
		
	}
	
	private void addLegumes() {		
		dietPanel.add(addLabel(constants.legumes()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		legumesZero = new RadioButton("legumes","0 - 2");
		legumesZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(legumesZero);
		legumesThree = new RadioButton("legumes",constants.three());
		legumesThree.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(legumesThree);			
		dietPanel.add(pnl1);
		
	}
	
	private void addFish() {		
		dietPanel.add(addLabel(constants.fish()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		fishZero = new RadioButton("fish","0 - 2");
		fishZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(fishZero);
		fishThree = new RadioButton("fish",constants.three());
		fishThree.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(fishThree);		
		dietPanel.add(pnl1);
		
	}
	
	private void addCake() {		
		dietPanel.add(addLabel(constants.cakes()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		cakeZero = new RadioButton("cake","0 - 2");
		cakeZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(cakeZero);
		cakeThree = new RadioButton("cake",constants.three());
		cakeThree.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(cakeThree);		
		dietPanel.add(pnl1);
		
	}
	
	private void addNuts() {		
		dietPanel.add(addLabel(constants.nuts()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		nutsZero = new RadioButton("nuts","0 - 2");
		nutsZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(nutsZero);
		nutsThree = new RadioButton("nuts",constants.three());
		nutsThree.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(nutsThree);		
		dietPanel.add(pnl1);
		
	}
	
	private void addChicken() {		
		dietPanel.add(addLabel(constants.prefer_chicken()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		chickenYes = new RadioButton("chicken",constants.yes());
		chickenYes.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(chickenYes);
		chickenNo = new RadioButton("chicken",constants.no());
		chickenNo.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(chickenNo);		
		dietPanel.add(pnl1);
		
	}
	
	private void addPasta() {		
		dietPanel.add(addLabel(constants.sauce()));
		HorizontalPanel pnl1 = new HorizontalPanel();
		pastaZero = new RadioButton("pasta","0 - 1");
		pastaZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(pastaZero);
		pastaTwo = new RadioButton("pasta",constants.two());
		pastaTwo.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(pastaTwo);		
		dietPanel.add(pnl1);
		
	}private void showErrorPopupPanel(InlineLabel error, String colour) {
		PopupPanel popup = new PopupPanel(true, true);			

		popup.setTitle("Error");
		VerticalPanel vertPanel = new VerticalPanel();
		error.getElement().getStyle().setProperty("color",colour);
		error.getElement().getStyle().setProperty("fontWeight", "bold");
		error.getElement().getStyle().setProperty("marginLeft", "25px");


		vertPanel.add(error);
		popup.setWidget(vertPanel);
		
		popup.setPopupPosition(190,720);
		popup.setWidth("550px");
		popup.show();

	}
	
	
	private void getDietData() {				
		User user = login.getUser();
		if (user== null) {
	
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error, "red");
			return;
	
		}
		callServiceSetup();
	
		AsyncCallback<DietInfo> callback =  new AsyncCallback<DietInfo>(){
	
			@Override	 
			public void onSuccess(DietInfo diet) {
				if ((diet == null || diet.getUserId() == null)){	            		
					InlineLabel error = new InlineLabel("Diet Data not retrieved. No data available for this patient ");
					showErrorPopupPanel(error, "red");            			
				}            		
				else {
					InlineLabel error = new InlineLabel("Diet data retrieved- Edit as necessary");
					showErrorPopupPanel(error, "green");  
					populatePanel(diet);
	
				}
	
			}
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel("Diet data Database error");
				showErrorPopupPanel(error, "red");			
	
			}
		};
	
		InminddServiceSvc.queryDiet(user, callback);
		return;
	}
	
	private void populatePanel(DietInfo diet) {
		if (diet.getCulinaryFat() == 1) {
			culFatYes.setValue(true);		
		}
		
		if (diet.getCulinaryFat() == 0) {
			culFatNo.setValue(true);
		}
		
		if (diet.getRapeSeedOil() == 0) {
			oilZero.setValue(true);
		}
		if (diet.getRapeSeedOil() == 2) {
			oilTwo.setValue(true);
		}
		if (diet.getRapeSeedOil() == 4) {
			oilFour.setValue(true);
		}
		
		if (diet.getVegetableServings() == 0) {
			vegZero.setValue(true);
		}
		if (diet.getVegetableServings() == 3) {
			vegThree.setValue(true);
		}
		if (diet.getVegetableServings() == 5) {
			vegFive.setValue(true);
		}
		if (diet.getFruit() == 0) {
			fruitZero.setValue(true);
		}
		if (diet.getFruit() == 1) {
			fruitOne.setValue(true);
		}
		if (diet.getFruit() == 2) {
			fruitTwo.setValue(true);
		}
		if (diet.getFruit() == 3) {
			fruitThree.setValue(true);
		}
		
		if (diet.getRedMeat() == 0) {
			meatZero.setValue(true);
		}
		if (diet.getRedMeat() == 1) {
			meatOne.setValue(true);
		}		
		
		if (diet.getButter() == 0) {
			butterZero.setValue(true);
		}
		if (diet.getButter() == 1) {
			butterOne.setValue(true);
		}
		if (diet.getBeverages() == 0) {
			beverageZero.setValue(true);
		}
		if (diet.getBeverages() == 1) {
			beverageOne.setValue(true);
		}
		
		if (diet.getWine() == 0) {
			wineZero.setValue(true);
		}
		if (diet.getWine() == 7) {
			wineSeven.setValue(true);
		}
		
		if (diet.getLegumes() == 0) {
			legumesZero.setValue(true);
		}

		if (diet.getLegumes() == 3) {
			legumesThree.setValue(true);
		}
		if (diet.getFish() == 0) {
			fishZero.setValue(true);
		}

		if (diet.getFish() == 3) {
			fishThree.setValue(true);
		}
		
		if (diet.getSweets() == 0) {
			cakeZero.setValue(true);
		}

		if (diet.getFish() == 3) {
			cakeThree.setValue(true);
		}
		
		if (diet.getNuts() == 0) {
			nutsZero.setValue(true);
		}

		if (diet.getNuts() == 3) {
			nutsThree.setValue(true);
		}
		
		if (diet.getChicken() == 0) {
			chickenNo.setValue(true);
		}

		if (diet.getChicken() == 1) {
			chickenYes.setValue(true);
		}
		
		if (diet.getSauce() == 0) {
			pastaZero.setValue(true);
		}

		if (diet.getSauce() == 2) {
			pastaTwo.setValue(true);
		}
	}

}

	

