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
import com.inmindd.dcu.shared.DietInfo;
import com.inmindd.dcu.shared.User;

public class Diet {
	private FlowPanel dietPanel;
	private DietInfo diet;
	private RadioButton culFatYes;
	private RadioButton culFatNo;
	private RadioButton oilOne;
	private RadioButton oilTwo;
	private RadioButton oilFour;
	private RadioButton vegZero;
	private RadioButton vegOne;
	private RadioButton vegTwo;
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
	private RadioButton pastaOne;
	private RadioButton pastaTwo;
	
	private static Diet lastinstance;
	public static void clearAllInputs() {
		lastinstance.nutsZero.setValue(false);
		
	}
	
	private ScrollPanel scroll = new ScrollPanel();
	private User user;
	private Login login;
	private InminddServiceAsync InminddServiceSvc;
	public Diet() {	
		lastinstance = this;
	}
	
public FlowPanel setupDietPanel(Login login) {
		this.login = login;
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				"Information about your Diet</h1>");
		
	
		HTMLPanel lbl1 = new HTMLPanel("<h3>" +
				"Please answer the following questions.</h3>");
		lbl1.getElement().getStyle().setProperty("textDecoration", "underline");
		
	
	
		dietPanel = new FlowPanel();
		dietPanel.add(mainHeader);
		
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
	        checkUser();
	        updateDietDB();
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
	private void checkUser() {
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			showErrorPopupPanel(error, "red");
			return;
			
		}
	
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
		if (oilOne.getValue()) {
			diet.setRapeSeedOil(1);
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
		if (vegOne.getValue()) {
			diet.setVegetableServings(3);
		}
		if (vegTwo.getValue()) {
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
		
		if (pastaOne.getValue() ) {
			diet.setSauce(1);
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
		dietPanel.add(addLabel("Do you use olive oil or rape seed oil as your main culinary fat?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		culFatYes =  new RadioButton("cul","Yes");
		culFatYes.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(culFatYes);
		culFatNo = new RadioButton("cul","No");
		culFatNo.getElement().getStyle().setProperty("marginLeft","20px");
		pnl1.add(culFatNo);
		dietPanel.add(pnl1);
	}
	
	private void addOilConsume() {		
		dietPanel.add(addLabel("How much olive oil or rape seed oil do you consume in a given day (including oil used for frying, salads, out-of-house meals, etc.) ? "));
		HorizontalPanel pnl1 = new HorizontalPanel();
		oilOne = new RadioButton("consume","0 - 1 tablespoons");
		oilOne.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(oilOne);
		oilTwo = new RadioButton("consume","2 - 3 tablespoons");
		oilTwo.getElement().getStyle().setProperty("marginLeft","20px");
		pnl1.add(oilTwo);
		
		oilFour = new RadioButton("consume","4+ tablespoons");
		oilFour.getElement().getStyle().setProperty("marginLeft","20px");
		pnl1.add(oilFour);
		dietPanel.add(pnl1);
		
	}
	
	private void addVegetables() {		
		dietPanel.add(addLabel("How many vegetable servings do you consume per day (1 serving = 200g) ? "));
		HorizontalPanel pnl1 = new HorizontalPanel();
		vegZero = new RadioButton("veg","0-2");
		vegZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(vegZero);
		vegOne = new RadioButton("veg","3-4");
		vegOne.getElement().getStyle().setProperty("marginLeft","112px");
		pnl1.add(vegOne);
		
		vegTwo = new RadioButton("veg","5 or more");
		vegTwo.getElement().getStyle().setProperty("marginLeft","113px");
		pnl1.add(vegTwo);
		dietPanel.add(pnl1);
		
	}
	
	private void addFruit() {		
		dietPanel.add(addLabel("How many fruit units (including natural fruit juices) do you consume per day ? "));
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
		
		fruitThree = new RadioButton("fruit","3 or more");
		fruitThree.getElement().getStyle().setProperty("marginLeft","113px");
		pnl1.add(fruitThree);
		dietPanel.add(pnl1);
		
	}
	
	private void addMeat() {		
		dietPanel.add(addLabel("How many servings of red meat, hamburger or meat products (ham, sausage etc.) do you consume per day ( 1 serving = 100-150g) ?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		meatZero = new RadioButton("meat","Less than 1");
		meatZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(meatZero);
		meatOne = new RadioButton("meat","1 or more");
		meatOne.getElement().getStyle().setProperty("marginLeft","53px");
		pnl1.add(meatOne);			
		dietPanel.add(pnl1);
		
	}
	
	private void addButter() {		
		dietPanel.add(addLabel("How many servings of butter, margarine or cream do you consume per day ( 1 serving = 12g) ?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		butterZero = new RadioButton("butter","Less than 1");
		butterZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(butterZero);
		butterOne = new RadioButton("butter","1 or more");
		butterOne.getElement().getStyle().setProperty("marginLeft","53px");
		pnl1.add(butterOne);		
		dietPanel.add(pnl1);
		
	}
	private void addBeverages() {		
		dietPanel.add(addLabel("How many sweet or carbonated beverages do you drink per day ?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		beverageZero = new RadioButton("sweet","Less than 1");
		beverageZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(beverageZero);
		beverageOne = new RadioButton("sweet","1 or more");
		beverageOne.getElement().getStyle().setProperty("marginLeft","53px");
		pnl1.add(beverageOne);			
		dietPanel.add(pnl1);
		
	}
	
	private void addWine() {		
		dietPanel.add(addLabel("How much wine do you drink per week ?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		wineZero = new RadioButton("wine","0 - 6 glasses");
		wineZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(wineZero);
		wineSeven = new RadioButton("wine","7 or more glasses");
		wineSeven.getElement().getStyle().setProperty("marginLeft","46px");
		pnl1.add(wineSeven);		
		dietPanel.add(pnl1);
		
	}
	
	private void addLegumes() {		
		dietPanel.add(addLabel("How many servings of legumes (that is beans, peas or lentils) do you consume per week ( 1 serving = 150g) ?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		legumesZero = new RadioButton("legumes","0 - 2");
		legumesZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(legumesZero);
		legumesThree = new RadioButton("legumes","3 or more");
		legumesThree.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(legumesThree);			
		dietPanel.add(pnl1);
		
	}
	
	private void addFish() {		
		dietPanel.add(addLabel("How many servings of fish or shellfish do you consume per week ? ( 1 serving = 100-150g of fish or 4-5 units or 200g of shellfish)"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		fishZero = new RadioButton("fish","0 - 2");
		fishZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(fishZero);
		fishThree = new RadioButton("fish","3 or more");
		fishThree.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(fishThree);		
		dietPanel.add(pnl1);
		
	}
	
	private void addCake() {		
		dietPanel.add(addLabel("How many times per week do you consume commercial sweets or pastries (not homemade) such as cakes, cookies, biscuits or custard ?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		cakeZero = new RadioButton("cake","0 - 2");
		cakeZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(cakeZero);
		cakeThree = new RadioButton("cake","3 or more");
		cakeThree.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(cakeThree);		
		dietPanel.add(pnl1);
		
	}
	
	private void addNuts() {		
		dietPanel.add(addLabel("How many servings of nuts (including peanuts) do you consume per week ( 1 serving = 30g) ?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		nutsZero = new RadioButton("nuts","0 - 2");
		nutsZero.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(nutsZero);
		nutsThree = new RadioButton("nuts","3 or more");
		nutsThree.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(nutsThree);		
		dietPanel.add(pnl1);
		
	}
	
	private void addChicken() {		
		dietPanel.add(addLabel("Do you preferentially consume chicken, turkey or rabbit meat instead of veal, pork, hamburger or sausage ?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		chickenYes = new RadioButton("chicken","Yes");
		chickenYes.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(chickenYes);
		chickenNo = new RadioButton("chicken","No");
		chickenNo.getElement().getStyle().setProperty("marginLeft","95px");
		pnl1.add(chickenNo);		
		dietPanel.add(pnl1);
		
	}
	
	private void addPasta() {		
		dietPanel.add(addLabel("How many times per week do you consume vegetables, pasta, rice or"
				+ " other dishes seasoned with a tomato sauce made with onion, leek or garlic and olive oil?"));
		HorizontalPanel pnl1 = new HorizontalPanel();
		pastaOne = new RadioButton("pasta","0 - 1");
		pastaOne.getElement().getStyle().setProperty("marginLeft","220px");
		pnl1.add(pastaOne);
		pastaTwo = new RadioButton("pasta","2 or more");
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
		//popup.setGlassEnabled(true);
		popup.setPopupPosition(190,700);
		popup.setWidth("550px");
		popup.show();

	}
}
