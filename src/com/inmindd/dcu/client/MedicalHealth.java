package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
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
import com.inmindd.dcu.shared.MedicalInfo;
import com.inmindd.dcu.shared.User;

public class MedicalHealth {
	
	
	public static MedicalHealth lastinstance;
	
	private static final int DECK_MEDICAL = 3;	
	private static final int DECK_HISTORY = 4;
	
	
	
	public static void clearInputs() {
		lastinstance.heightCent.setText("");
		lastinstance.heightFeet.setText("");
		lastinstance.heightInches.setText("");
		lastinstance.bloodPressureNo.setValue(false);
		lastinstance.bloodPressureYes.setValue(false);
		lastinstance.cholNo.setValue(false);
		lastinstance.cholYes.setValue(false);
		lastinstance.diastolic.setText("");
		lastinstance.systolic.setText("");
		lastinstance.heartDiseaseDontKnow.setValue(false);
		lastinstance.heartDiseaseNo.setValue(false);
		lastinstance.heartDiseaseYes.setValue(false);
		lastinstance.hyperDontKnow.setValue(false);
		lastinstance.hyperNo.setValue(false);
		lastinstance.hyperYes.setValue(false);
		lastinstance.hyperMedNever.setValue(false);
		lastinstance.hyperMedYes.setValue(false);
		lastinstance.hyperMedPast.setValue(false);
		lastinstance.hyperMedDontKnow.setValue(false);
		lastinstance.kidneyDontKnow.setValue(false);
		lastinstance.kidneyNo.setValue(false);
		lastinstance.kidneyYes.setValue(false);
		lastinstance.lifestyleDontKnow.setValue(false);
		lastinstance.lifestyleNo.setValue(false);
		lastinstance.lifestyleYes.setValue(false);
		lastinstance.medicationDontKnow.setValue(false);
		lastinstance.medicationNever.setValue(false);
		lastinstance.medicationPast.setValue(false);
		lastinstance.medicationYes.setValue(false);
		lastinstance.mellitusDontKnow.setValue(false);
		lastinstance.mellitusNo.setValue(false);
		lastinstance.mellitusYes.setValue(false);
		lastinstance.mellitusTreatmentDontKnow.setValue(false);
		lastinstance.mellitusTreatmentNo.setValue(false);
		lastinstance.mellitusTreatmentYes.setValue(false);
		lastinstance.sugarDontKnow.setValue(false);
		lastinstance.sugarNo.setValue(false);
		lastinstance.sugarYes.setValue(false);
		lastinstance.totalCholDontKnow.setValue(false);
		lastinstance.totalCholNo.setValue(false);
		lastinstance.totalCholYes.setValue(false);
		lastinstance.systolic.setText("");
		lastinstance.weightKilos.setText("");;
		lastinstance.weightLbs.setText("");
		lastinstance.weightStone.setText("");
		lastinstance.mmol.setText("");
		
	}
	
	private Login login;
	private TabLayoutPanel content;
	private FlowPanel medicalHealthPanel = new FlowPanel();
	private ScrollPanel scroll = new ScrollPanel();
	private FlowPanel medical;
	private DataField heightCent;
	private double heightInputCents;
	private double heightInputFeet;
	private double heightInputInches;
	private DataField heightFeet;
	private DataField heightInches;
	private DataField weightKilos;
	private DataField weightStone;
	private DataField weightLbs; 
	private double weightInputKilos;
	private double weightInputStone;
	private double weightInputLbs;
	private double 	diastolicInput;
	private double systolicInput;
	private DockPanel dockChol;
  
    private DataField mmol;
  
    private DockPanel dockBlood; 
    private FlowPanel bloodReadingsPanel;
    private TextBox systolic = new TextBox();
    private TextBox diastolic = new TextBox();
    
    private InlineLabel cholLabel;
    private RadioButton cholYes;
    private RadioButton cholNo;
    
    private InlineLabel totalCholLabel;
    private RadioButton totalCholYes;
    private RadioButton totalCholNo;
    private RadioButton totalCholDontKnow;
    
    private InlineLabel lifestyleLabel;
    private RadioButton lifestyleYes;
    private RadioButton lifestyleNo;
    private RadioButton lifestyleDontKnow;
    
    private InlineLabel medicationLabel;
    private RadioButton medicationYes;
    private RadioButton medicationNever;
    private RadioButton medicationPast;
    private RadioButton medicationDontKnow;
    
    private InlineLabel heartDiseaseLabel;
    private RadioButton heartDiseaseYes;
    private RadioButton heartDiseaseNo;
    private RadioButton heartDiseaseDontKnow;  
    
    private InlineLabel hyperLabel;
    private RadioButton hyperYes;
    private RadioButton hyperNo;
    private RadioButton hyperDontKnow;
    
    
    private InlineLabel bloodPressureLabel;
    private RadioButton bloodPressureYes;
    private RadioButton bloodPressureNo;
    
    private InlineLabel hyperMedLabel;
    private RadioButton hyperMedYes;
    private RadioButton hyperMedNever;
    private RadioButton hyperMedPast;
    private RadioButton hyperMedDontKnow;
    
    private InlineLabel mellitusLabel;
    private RadioButton mellitusYes;
    private RadioButton mellitusNo;  
    private RadioButton mellitusDontKnow;
    
    private InlineLabel mellitusTreatmentLabel;
    private RadioButton mellitusTreatmentYes;
    private RadioButton mellitusTreatmentNo;
    private RadioButton mellitusTreatmentDontKnow;
    
    private InlineLabel sugarLabel;
    private RadioButton sugarYes;
    private RadioButton sugarNo;
    private RadioButton sugarDontKnow; 
    
    private InlineLabel kidneyLabel;
    private RadioButton kidneyYes;
    private RadioButton kidneyNo;
    private RadioButton kidneyDontKnow;
  
    
	private InminddServiceAsync InminddServiceSvc;
  
	
	private final static double MIN_HEIGHT_CENTIMETERS = 147.3;
	private final static double MAX_HEIGHT_CENTIMETERS = 200;	
	private final static double MIN_HEIGHT_INCHES = 58;
	private final static double MAX_HEIGHT_INCHES = 75; 
	private final static double MIN_WEIGHT_KILOS = 45.5;
	private final static double MAX_WEIGHT_KILOS = 161.4; 
	private final static double MIN_MMOL = 1;
	private final static double MAX_MMOL = 10; 
	private final static double MIN_SYSTOLIC = 40;
	private final static double MAX_SYSTOLIC = 190;
	private final static double MIN_DIASTOLIC = 40;
	private final static double MAX_DIASTOLIC = 100; 
	private final static double MIN_WEIGHT_LBS = 100;
	private final static double MAX_WEIGHT_LBS = 355; 
	
	private InlineLabel error;
	private  MedicalInfo medicalData;  
	private static InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	public void setContent(TabLayoutPanel content) {
		this.content = content;
	}
	public MedicalHealth() {
		lastinstance = this;
	}
	
	public FlowPanel setupMedicalHealthPanel(Login login) {
		this.login = login;
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				constants.medical() + "</h1>");
		HTMLPanel header = new HTMLPanel("<h3>" +
				constants.health() + " </h3>");

		medicalHealthPanel.add(mainHeader);
		medicalHealthPanel.add(header);

		Button prev = new Button(constants.review());

		// Listen for mouse events on the previous data button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getMedicalData();
			}
		});
		medicalHealthPanel.add(prev);
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));

		heightCent = new DataField(constants.height());
		heightCent.getElement().getStyle().setProperty("fontWeight", "bold");
		heightCent.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				
				double height = heightCent.getDoubleValue();
				String heightText = heightCent.getText();
				if (heightText == "") {
					heightFeet.setFocus();
				}

				else if ((height >=  MIN_HEIGHT_CENTIMETERS) && (height <=  MAX_HEIGHT_CENTIMETERS)) {
					heightInputCents = height;
					heightInputFeet = 0;
					heightInputInches = 0;
					heightFeet.setText("");
					heightInches.setText("");
					weightKilos.setFocus();
				}

				else {
					error = new InlineLabel(constants.invalid_height());
					showErrorPopupPanel(error,"red");
					heightCent.setFocus();
					heightCent.setText("");
				}
			}
		});

		
		//String user = login.getUserId();
		heightFeet = new DataField(constants.feet());

		heightFeet.getElement().getStyle().setProperty("marginLeft", "52px");
		heightFeet.getElement().getStyle().setProperty("fontWeight", "bold");

		heightInches = new DataField(constants.inches());
		heightInches.getElement().getStyle().setProperty("marginLeft", "20px");
		heightInches.getElement().getStyle().setProperty("fontWeight", "bold");
	
		heightFeet.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double feet = heightFeet.getDoubleValue();	    	

				if ((feet >=  4)  && (feet <8)) {
					heightInputFeet = feet;
				}		    	
				else {
					error = new InlineLabel(constants.invalid_height());
					showErrorPopupPanel(error,"red");
					heightFeet.setFocus();
					heightFeet.setText("");
					heightInches.setText("");
				}
			}
		});


		heightInches.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double feet = heightFeet.getDoubleValue();
				double inches = heightInches.getDoubleValue();
				if (inches < 0) {
					inches = 0;
				}

				double totalInches = (feet * 12 ) + inches;	  
				if ((totalInches  >=  MIN_HEIGHT_INCHES) && (totalInches <=  MAX_HEIGHT_INCHES)) {
					heightInputFeet = feet;
					heightInputInches = inches;
					heightInputCents = 0;
					weightKilos.setFocus();
				}

				else {
					error = new InlineLabel(constants.invalid_height());
					showErrorPopupPanel(error,"red");
					heightFeet.setFocus();
					heightFeet.setText("");
					heightInches.setText("");
				}
			}
		});


		HorizontalPanel heightPanel = new HorizontalPanel();
		heightPanel.add(heightCent);
		//heightPanel.add(tb);

		heightPanel.add(heightFeet);
		heightPanel.add(heightInches);
		medicalHealthPanel.add(heightPanel);	
		

		medicalHealthPanel.setWidth("100%");		

		weightKilos = new DataField(constants.weight());
		weightKilos.getElement().getStyle().setProperty("fontWeight", "bold");

		weightStone = new DataField(constants.stones());
		weightStone.getElement().getStyle().setProperty("marginLeft", "63px");
		weightStone.getElement().getStyle().setProperty("fontWeight", "bold");

		weightLbs = new DataField(constants.lbs());
		weightLbs.getElement().getStyle().setProperty("marginLeft", "33px");
		weightLbs.getElement().getStyle().setProperty("fontWeight", "bold");

		weightKilos.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double ededweight = weightKilos.getDoubleValue();
				String weightText = weightKilos.getText();
				if (weightText == "" || ededweight == -1) {
					weightStone.setFocus();
					return;
				}

				else if ((ededweight >=  MIN_WEIGHT_KILOS) && (ededweight <=  MAX_WEIGHT_KILOS)) {	
					weightInputStone = 0;
					weightInputLbs = 0;
					weightInputKilos = ededweight;
					cholYes.setFocus(true);
				}

				else {
					error = new InlineLabel(constants.invalid_weight());
				
					
					
					showErrorPopupPanel(error, "red");
					weightKilos.setFocus();
					weightKilos.setText("");

				}
			}
		});


		weightStone.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double stone = weightStone.getDoubleValue();	    	

				if ((stone >=  (MIN_WEIGHT_LBS/14))  && (stone <= (MAX_WEIGHT_LBS/15))) {  
					weightInputStone = stone;
				}

				else {
					error = new InlineLabel(constants.invalid_weight());
					showErrorPopupPanel(error,"red");
					weightStone.setFocus();
					weightStone.setText("");
					weightStone.setText("");
				}
			}
		});

		weightLbs.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double stone = weightStone.getDoubleValue();
				double lbs = weightLbs.getDoubleValue();		    	
				double totalLbs = (stone * 14 ) + lbs;			    	

				if ((totalLbs  >=  MIN_WEIGHT_LBS) && (totalLbs <=  MAX_WEIGHT_LBS)) {
					weightInputStone = stone;
					weightInputLbs = lbs;
					weightInputKilos = 0;
					weightStone.setFocus();
				}		    	
				else {
					error = new InlineLabel(constants.invalid_height());
					showErrorPopupPanel(error,"red");
					weightLbs.setFocus();
					weightStone.setText("");
					weightLbs.setText("");
				}
			}
		});

		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		HorizontalPanel weightPanel = new HorizontalPanel();

		weightPanel.add(weightKilos);
		weightPanel.add(weightStone);
		weightPanel.add(weightLbs);
		medicalHealthPanel.add(weightPanel);
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getCholesterol());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getHighTotalCholesterol());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getLifeStyleChanges());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getMedication());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getHeartDisease());		
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getHypertension());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getBloodPressure());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));	
		medicalHealthPanel.add(getBloodReadings());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getHypertensionMed());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getMellitus());
		medicalHealthPanel.add(getMellitusNote());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getDiabetesTreatment());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getSugarLevel());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		medicalHealthPanel.add(getKidneyDisease());
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cholYes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mmol.setVisible(true);

			}
		});

		cholNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mmol.setVisible(false);
				mmol.setText("");
			}
		});

		bloodPressureYes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bloodReadingsPanel.setVisible(true);
			}
		});

		bloodPressureNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bloodReadingsPanel.setVisible(false);

			}
		});

		Button btn = new Button(constants.submit());
		// Listen for mouse events on the submit button.
		btn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {


				if (!validateInput()) {
					return;
				}

				updateMedicalDB();
				return;  // write to server here
			}

		});
		medicalHealthPanel.add(btn);

		scroll.setSize("100%", "70%");
		scroll.add(medicalHealthPanel);
		scroll.setAlwaysShowScrollBars(true);
		medical = new FlowPanel();
		medical.add(scroll);

		return medical;

	}

	private  DockPanel getCholesterol() {
		dockChol =  new DockPanel();
		HorizontalPanel hPanel = new HorizontalPanel();
		HorizontalPanel hPanel2 = new HorizontalPanel();
		cholLabel = new InlineLabel(constants.cholesterol());

		cholLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		hPanel.setWidth("750x");
		hPanel2.setWidth("550x");
		hPanel.add(cholLabel);
		cholYes = new RadioButton("chol", constants.yes());
		cholYes.getElement().getStyle().setProperty("marginLeft", "303px");
		cholNo = new RadioButton("chol", constants.dontknow());
		cholNo.getElement().getStyle().setProperty("marginLeft", "10px");
		mmol = new DataField(constants.enterchol(), "mmol/l");
		mmol.getElement().getStyle().setProperty("fontWeight", "bold");
		mmol.getElement().getStyle().setProperty("marginLeft", "20px");
		mmol.getElement().getStyle().setProperty("backgroundColor", "cyan");
		mmol.setVisible(false);
		mmol.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double mmolInput = mmol.getDoubleValue();

				if (!((mmolInput >=  MIN_MMOL) && (mmolInput <=  MAX_MMOL))) {
					error = new InlineLabel(constants.mmol_error());
					showErrorPopupPanel(error,"red");
					mmol.setFocus();


				}	    	

			}
		});
		hPanel2.add(cholYes);
		hPanel2.add(cholNo);
		hPanel2.add(mmol);
		dockChol.add(hPanel, DockPanel.WEST);
		dockChol.add(hPanel2, DockPanel.WEST);
		return dockChol;


	}

	private  DockPanel getHighTotalCholesterol() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		totalCholLabel = new InlineLabel(constants.told());
		totalCholLabel.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_2 = new InlineLabel(constants.highchol());

		lbl_2.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.add(totalCholLabel);

		vPanel.add(lbl_2);

		vPanel.setWidth("580px");
		totalCholYes = new RadioButton("highchol", constants.yes());
		totalCholYes.getElement().getStyle().setProperty("marginLeft", "10px");
		totalCholNo = new RadioButton("highchol", constants.no());
		totalCholNo.getElement().getStyle().setProperty("marginLeft", "10px");
		totalCholDontKnow = new RadioButton("highchol", constants.cantremember());
		totalCholDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		dock.add(vPanel,DockPanel.WEST);

		dock.add(totalCholDontKnow,DockPanel.EAST);
		dock.add(totalCholNo,DockPanel.EAST);
		dock.add(totalCholYes,DockPanel.EAST);
		
		return dock;
	}
	
	

	private  DockPanel getLifeStyleChanges() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();


		lifestyleLabel = new InlineLabel(constants.lifestyle());
		lifestyleLabel.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_2 = new InlineLabel(constants.address());
		lbl_2.getElement().getStyle().setProperty("fontWeight", "bold");

		vPanel.add(lifestyleLabel);

		vPanel.add(lbl_2);

		vPanel.setWidth("580px");
		lifestyleYes = new RadioButton("lifestyle", constants.yes());
		lifestyleYes.getElement().getStyle().setProperty("marginLeft", "10px");
		lifestyleNo = new RadioButton("lifestyle", constants.no());
		lifestyleNo.getElement().getStyle().setProperty("marginLeft", "10px");
		lifestyleDontKnow = new RadioButton("lifestyle", constants.dontknow());
		lifestyleDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		dock.add(vPanel,DockPanel.WEST);

		dock.add(lifestyleDontKnow,DockPanel.EAST);
		dock.add(lifestyleNo,DockPanel.EAST);
		dock.add(lifestyleYes,DockPanel.EAST);
		return dock;	  

	}

	private  DockPanel getMedication() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		medicationLabel = new InlineLabel(constants.medication());
		medicationLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(medicationLabel);
		medicationYes = new RadioButton("medic", constants.yescurrent());
		medicationYes.getElement().getStyle().setProperty("marginLeft", "10px");
		medicationPast = new RadioButton("medic", constants.yespast());
		medicationPast.getElement().getStyle().setProperty("marginLeft", "10px");
		medicationNever = new RadioButton("medic", constants.never());
		medicationNever.getElement().getStyle().setProperty("marginLeft", "10px");
		medicationDontKnow = new RadioButton("medic", constants.dontknow());
		medicationDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		dock.add(vPanel, DockPanel.WEST);
		dock.add(medicationDontKnow, DockPanel.EAST);
		dock.add(medicationNever, DockPanel.EAST);
		dock.add(medicationPast, DockPanel.EAST);
		dock.add(medicationYes, DockPanel.EAST);		 
		return dock;	  

	}

	private  DockPanel getHeartDisease() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();


		heartDiseaseLabel  = new InlineLabel(constants.cvd_1());
		heartDiseaseLabel.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_2 = new InlineLabel(constants.cvd_2());
		lbl_2.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_3 = new InlineLabel(constants.includes_1());
		lbl_3.getElement().getStyle().setProperty("fontWeight", "bold");
		InlineLabel lbl_4 = new InlineLabel(constants.includes_2());
		lbl_4.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.add(heartDiseaseLabel);		
		vPanel.add(lbl_2);
		vPanel.add(lbl_3);
		vPanel.add(lbl_4);

		vPanel.setWidth("580px");
		heartDiseaseYes = new RadioButton("heart", constants.yes());
		heartDiseaseYes.getElement().getStyle().setProperty("marginLeft", "10px");
		heartDiseaseNo = new RadioButton("heart", constants.no());
		heartDiseaseNo.getElement().getStyle().setProperty("marginLeft", "10px");
		heartDiseaseDontKnow = new RadioButton("heart", constants.dontknow());
		heartDiseaseDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		dock.add(vPanel,DockPanel.WEST); 
		dock.add(heartDiseaseDontKnow,DockPanel.EAST);
		dock.add(heartDiseaseNo,DockPanel.EAST);
		dock.add(heartDiseaseYes,DockPanel.EAST);
		return dock;	  

	}

	private  DockPanel getHypertension() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		hyperLabel = new InlineLabel(constants.highblood());
		hyperLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(hyperLabel);
		hyperYes = new RadioButton("hyper", constants.yes());
		hyperYes.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperNo = new RadioButton("hyper", constants.no());
		hyperNo.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperDontKnow = new RadioButton("hyper", constants.dontknow());
		hyperDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		dock.add(vPanel, DockPanel.WEST);
		dock.add(hyperDontKnow, DockPanel.EAST);
		dock.add(hyperNo, DockPanel.EAST);
		dock.add(hyperYes, DockPanel.EAST);
		return dock;


	}

	private  DockPanel getHypertensionMed() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		hyperMedLabel  = new InlineLabel(constants.medbp());
		hyperMedLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(hyperMedLabel);
		hyperMedYes = new RadioButton("hypermed", constants.yescurrent());
		hyperMedYes.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperMedPast = new RadioButton("hypermed", constants.yespast());
		hyperMedPast.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperMedNever = new RadioButton("hypermed", constants.never());
		hyperMedNever.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperMedDontKnow = new RadioButton("hypermed", constants.dontknow());
		hyperMedDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		dock.add(vPanel, DockPanel.WEST);
		dock.add(hyperMedDontKnow, DockPanel.EAST);
		dock.add(hyperMedNever, DockPanel.EAST);
		dock.add(hyperMedPast, DockPanel.EAST);
		dock.add(hyperMedYes, DockPanel.EAST);	
		return dock;		
	}

	private  DockPanel getMellitus() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		mellitusLabel = new InlineLabel(constants.tolddiabetes());
		mellitusLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		vPanel.add(mellitusLabel);

		vPanel.setWidth("580px");
		mellitusYes = new RadioButton("mellitus", constants.yes());
		mellitusYes.getElement().getStyle().setProperty("marginLeft", "10px");

		mellitusNo = new RadioButton("mellitus", constants.no());
		mellitusNo.getElement().getStyle().setProperty("marginLeft", "10px");
		mellitusDontKnow = new RadioButton("mellitus", constants.dontknow());
		mellitusDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		
		dock.add(vPanel,DockPanel.WEST);

		dock.add(mellitusDontKnow , DockPanel.EAST);
		dock.add(mellitusNo,DockPanel.EAST);
		dock.add(mellitusYes,DockPanel.EAST);
	
		
		return dock;	  

	}
    private VerticalPanel getMellitusNote() {
    	VerticalPanel note = new VerticalPanel();
    	InlineLabel mellitusNote = new InlineLabel(constants.preg());
    	mellitusNote.getElement().getStyle().setProperty("fontWeight", "bold");
    	note.add(mellitusNote);
    	return note;
    }
    
	private  DockPanel getDiabetesTreatment() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		mellitusTreatmentLabel = new InlineLabel(constants.diabetestreat());
		mellitusTreatmentLabel .getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(mellitusTreatmentLabel );
		mellitusTreatmentYes = new RadioButton("diabetes", constants.yes());
		mellitusTreatmentYes.getElement().getStyle().setProperty("marginLeft", "10px");
		mellitusTreatmentNo = new RadioButton("diabetes", constants.no());
		mellitusTreatmentNo.getElement().getStyle().setProperty("marginLeft", "10px");
		mellitusTreatmentDontKnow = new RadioButton("diabetes", constants.dontknow());
		mellitusTreatmentDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		dock.add(vPanel, DockPanel.WEST);
		dock.add(mellitusTreatmentDontKnow, DockPanel.EAST);
		dock.add(mellitusTreatmentNo, DockPanel.EAST);
		dock.add(mellitusTreatmentYes, DockPanel.EAST);
		return dock;		
	}

	private  DockPanel getSugarLevel() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		sugarLabel = new InlineLabel(constants.toldurine());
		sugarLabel.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_2 = new InlineLabel(constants.toldurine_2());
		lbl_2.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.add(sugarLabel);

		vPanel.add(lbl_2);

		vPanel.setWidth("580px");
		sugarYes = new RadioButton("sugar", constants.yes());
		sugarYes.getElement().getStyle().setProperty("marginLeft", "10px");
		sugarNo = new RadioButton("sugar", constants.no());
		sugarNo.getElement().getStyle().setProperty("marginLeft", "10px");
		sugarDontKnow = new RadioButton("sugar", constants.dontknow());
		sugarDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		dock.add(vPanel,DockPanel.WEST);

		dock.add(sugarDontKnow, DockPanel.EAST);
		dock.add(sugarNo,DockPanel.EAST);
		dock.add(sugarYes,DockPanel.EAST);
		return dock;	  

	}

	private  DockPanel getBloodPressure() {

		dockBlood = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		bloodPressureLabel = new InlineLabel(constants.bp());
		bloodPressureLabel .getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(bloodPressureLabel );
		bloodPressureYes = new RadioButton("blood", constants.yes());
		bloodPressureYes.getElement().getStyle().setProperty("marginLeft", "10px");
		bloodPressureNo = new RadioButton("blood", constants.no());
		bloodPressureNo.getElement().getStyle().setProperty("marginLeft", "10px");
		dockBlood.add(vPanel, DockPanel.WEST);


		dockBlood.add(bloodPressureNo, DockPanel.EAST);
		dockBlood.add(bloodPressureYes, DockPanel.EAST);
		return dockBlood;


	}

	private FlowPanel getBloodReadings() {

		bloodReadingsPanel = new FlowPanel();
		FlowPanel firstPanel  = new FlowPanel();
		InlineLabel lbl1 = new InlineLabel(constants.recentbp());
		lbl1.getElement().getStyle().setProperty("fontWeight", "bold");

		//firstPanel.setWidth("580px");
		firstPanel.add(lbl1);
		FlowPanel secondPanel = new FlowPanel();

		InlineLabel lbl2 = new InlineLabel(constants.recentlastyear());
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl3 = new InlineLabel(constants.systolic());

		lbl3.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl3.getElement().getStyle().setProperty("marginLeft", "200px");
		lbl3.getElement().getStyle().setProperty("backgroundColor", "cyan");
		systolic.setMaxLength(3);
		systolic.setWidth("2em");
		systolic.getElement().getStyle().setProperty("marginLeft", "20px");
		InlineLabel lbl4 = new InlineLabel("mm Hg");

		lbl4.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl4.getElement().getStyle().setProperty("marginLeft", "20px");
		lbl4.getElement().getStyle().setProperty("backgroundColor", "cyan");
		
		InlineLabel lbl7 = new InlineLabel(" / ");

		lbl7.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl7.getElement().getStyle().setProperty("marginLeft", "20px");
		//lbl7.getElement().getStyle().setProperty("backgroundColor", "cyan");
		secondPanel.add(lbl2);
		secondPanel.add(lbl3);

		secondPanel.add(systolic);
		secondPanel.add(lbl4);
		secondPanel.add(lbl7);

		InlineLabel lbl5 = new InlineLabel(constants.diastolic());

		lbl5.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl5.getElement().getStyle().setProperty("marginLeft", "60px");
		lbl5.getElement().getStyle().setProperty("backgroundColor", "cyan");
		diastolic.setMaxLength(3);
		diastolic.setWidth("2em");
		diastolic.getElement().getStyle().setProperty("marginLeft", "20px");
		InlineLabel lbl6 = new InlineLabel("mm Hg");

		lbl6.getElement().getStyle().setProperty("fontWeight", "bold");
		lbl6.getElement().getStyle().setProperty("marginLeft", "20px");
		lbl6.getElement().getStyle().setProperty("backgroundColor", "cyan");
		secondPanel.add(lbl5);


		secondPanel.add(diastolic);
		secondPanel.add(lbl6);
		bloodReadingsPanel.add(firstPanel);
		bloodReadingsPanel.add(secondPanel);
		bloodReadingsPanel.setVisible(false);

		systolic.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				validateSystolic();    	

			}
		});

		diastolic.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				validateDiastolic();}
			});

		return bloodReadingsPanel;
		}

		private  DockPanel getKidneyDisease() {
			DockPanel dock = new DockPanel();
			VerticalPanel vPanel = new VerticalPanel();
			kidneyLabel = new InlineLabel(constants.toldkidney());
			kidneyLabel.getElement().getStyle().setProperty("fontWeight", "bold");			
			vPanel.add(kidneyLabel);				
			vPanel.setWidth("580px");
			kidneyYes = new RadioButton("kidney", constants.yes());
			kidneyYes.getElement().getStyle().setProperty("marginLeft", "10px");
			kidneyNo = new RadioButton("kidney", constants.no());
			kidneyNo.getElement().getStyle().setProperty("marginLeft", "10px");
			kidneyDontKnow = new RadioButton("kidney", constants.dontknow());
			kidneyDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
			
			dock.add(vPanel,DockPanel.WEST);	
			dock.add(kidneyDontKnow,DockPanel.EAST);
			dock.add(kidneyNo,DockPanel.EAST);
			dock.add(kidneyYes,DockPanel.EAST);
			return dock;	  

		}

		private boolean validateInput() {
			
			User user = login.getUser();
			if (user.getUserId() == null) {
				
				InlineLabel error  = new InlineLabel(constants.register());
				showErrorPopupPanel(error,"red");
				return false;
				
			}
			if (!validateMmol())
				return false;
			if (!validateSystolic())
				return false;
			if (!validateDiastolic())
				return false;
			if (!validateHeight())
				return false;
			if (!validateWeight())
				return false;
			// check if all buttons selected
			if (!checkButtonSelected(cholLabel, cholYes, cholNo))
				return false;
			if (! checkButtonSelected(totalCholLabel, totalCholYes, totalCholNo, totalCholDontKnow))
				return false;
			if (! checkButtonSelected(lifestyleLabel, lifestyleYes, lifestyleNo, lifestyleDontKnow))
				return false;	   
			if (! checkButtonSelected(medicationLabel, medicationYes, medicationNever, medicationPast, medicationDontKnow))
				return false;
			if (! checkButtonSelected(heartDiseaseLabel, heartDiseaseYes, heartDiseaseNo, heartDiseaseDontKnow))
				return false;
			if (! checkButtonSelected(hyperLabel, hyperYes, hyperNo, hyperDontKnow))
				return false;
			if (! checkButtonSelected(bloodPressureLabel, bloodPressureYes, bloodPressureNo))
				return false;
			if (! checkButtonSelected(hyperMedLabel, hyperMedYes, hyperMedNever, hyperMedPast, hyperMedDontKnow))
				return false;	    
			if (! checkButtonSelected(mellitusLabel, mellitusYes, mellitusNo, mellitusDontKnow))
				return false;	
			if (! checkButtonSelected(mellitusTreatmentLabel, mellitusTreatmentYes, mellitusTreatmentNo, mellitusTreatmentDontKnow))
				return false;
			if (! checkButtonSelected(sugarLabel, sugarYes, sugarNo, sugarDontKnow))
				return false;
			if (! checkButtonSelected(kidneyLabel, kidneyYes, kidneyNo, kidneyDontKnow))
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

			error = new InlineLabel(constants.feelings_error_2());			
			showErrorPopupPanel(error,"red");
			label.getElement().getStyle().setProperty("color", "red");			  
			return false;		

		}

		
		private boolean validateMmol() {

			if (cholYes.getValue()) {
				double mmolInput  = 0;
				try {
					mmolInput  = Double.parseDouble(mmol.getText());	
				}

				catch (Exception e)	{					
					error = new InlineLabel(constants.mmol_error());
					showErrorPopupPanel(error,"red");
					mmol.setFocus();				
					return false;

				}
				if (!((mmolInput >=  MIN_MMOL) && (mmolInput <=  MAX_MMOL))) {
					error = new InlineLabel(constants.mmol_range_error());
					showErrorPopupPanel(error,"red");
					mmol.setFocus();
					return false;

				}	
			}
			return true;
		}
		
		
		private boolean validateHeight() {

			double heightFeetInches = 0;
			if (heightInputCents >= MIN_HEIGHT_CENTIMETERS && heightInputCents <= MAX_HEIGHT_CENTIMETERS) {
				heightInputFeet = 0;
				heightInputInches = 0;
				return true;
			}
			else { heightFeetInches = heightInputFeet * 12 + heightInputInches;	
			if (!(heightFeetInches >= MIN_HEIGHT_INCHES && heightFeetInches <= MAX_HEIGHT_INCHES)) {
			
					error = new InlineLabel(constants.invalid_height());
					showErrorPopupPanel(error,"red");
					heightCent.setFocus();
					return false;

				}	
			}
			return true;
		}
		
		private boolean validateWeight() {

			double weightStoneLbs = 0;
			if (weightInputKilos >= MIN_WEIGHT_KILOS && weightInputKilos <= MAX_WEIGHT_KILOS) {
				weightInputStone = 0;
				weightInputLbs = 0;
				return true;
			}
			else { weightStoneLbs = weightInputStone * 14 + weightInputLbs;	
			if (!(weightStoneLbs >= MIN_WEIGHT_LBS && weightStoneLbs <= MAX_WEIGHT_LBS)) {
			
					error = new InlineLabel(constants.invalid_weight());
					showErrorPopupPanel(error,"red");
					weightKilos.setFocus();
					return false;

				}	
			}
			return true;
		}
		

		private boolean validateSystolic() {

			if (bloodPressureYes.getValue()) {
				
				try {
					systolicInput  = Double.parseDouble(systolic.getText());	
				}

				catch (Exception e)	{					
					error = new InlineLabel(constants.systolic_error());
					showErrorPopupPanel(error, "red");
					systolic.setFocus(true);				
					return false;

				}
				if (!((systolicInput >=  MIN_SYSTOLIC) && (systolicInput <=  MAX_SYSTOLIC))) {
					error = new InlineLabel(constants.systolic_range());
					showErrorPopupPanel(error,"red");
					systolic.setFocus(true);
					return false;

				}	
			}
			return true;
		}
		
		private boolean validateDiastolic() {
			if (bloodPressureYes.getValue()) {;
				try {
					diastolicInput  = Double.parseDouble(diastolic.getText());	
				}
				catch (Exception e)
				{	error = new InlineLabel(constants.diastolic_error());
					showErrorPopupPanel(error, "red");
					systolic.setFocus(true);
					return false;

				}
				if (!((diastolicInput >=  MIN_DIASTOLIC) && (diastolicInput <=  MAX_DIASTOLIC))) {
					error = new InlineLabel(constants.diastolic_range());
					showErrorPopupPanel(error, "red");
					diastolic.setFocus(true);
					return false;

				}	
			}
			return true;
			
		} private boolean callServiceSetup() {
			// set up rpc call
	
			InminddServiceSvc = (InminddServiceAsync) GWT.create(InminddService.class);
			ServiceDefTarget target = (ServiceDefTarget) InminddServiceSvc;
			String moduleRelativeURL = GWT.getModuleBaseURL() + "Inmindd";
			target.setServiceEntryPoint(moduleRelativeURL);	    			
			return true;

	 }
		 private void getMedicalData() {				
			 User user = login.getUser();
			 if (user== null) {

				 InlineLabel error  = new InlineLabel(constants.register());
				 showErrorPopupPanel(error, "red");
				 return;

			 }
			 callServiceSetup();

			 AsyncCallback<MedicalInfo> callback =  new AsyncCallback<MedicalInfo>(){

				 @Override	 
				 public void onSuccess(MedicalInfo medical) {
					 if ((medical ==null)){	            		
						 InlineLabel error = new InlineLabel(constants.unable_to_retrieve());
						 showErrorPopupPanel(error, "red");            			
					 }            		
					 else {
						 InlineLabel error = new InlineLabel(constants.retrieved());
						 showErrorPopupPanel(error, "green");  
						 populatePanel(medical);
						
					 }

				 }
				 @Override
				 public void onFailure(Throwable caught) {
					 InlineLabel error = new InlineLabel(constants.unable_to_retrieve());
					 showErrorPopupPanel(error, "red");			

				 }
			 };

			 InminddServiceSvc.queryMedicalHealth(user, callback);
			 return;
		 }
		
		 
		 /**
		  * Display the retrieved medical values on the panel
		  * @param medical
		  */
		 private void populatePanel(MedicalInfo medical) {
			 int input  = (int) medical.getCent();
			 double doubleInput = 0;
			 heightCent.setText(Integer.toString(input));
			 heightInputCents = input;
			 input  = (int) medical.getFeet();
			 heightInputFeet = input;
			 heightFeet.setText(Integer.toString(input));
			 input  = (int) medical.getInches();
			 heightInputInches = input;

			 heightInches.setText(Integer.toString(input));
			 input  = (int) medical.getKilo();
			 weightInputKilos = input;
			 weightKilos.setText(Integer.toString(input));
			 input  = (int) medical.getStone();
			 weightInputStone = input;
			 weightStone.setText(Integer.toString(input));
			 input  = (int) medical.getLbs();
			 weightInputLbs = input;
			 weightLbs.setText(Integer.toString(input));
			 doubleInput = (double)medical.getMmol();
			 mmol.setText(Double.toString(doubleInput));
			 String button = medical.getChol();
			 if (button.equals("yes")) {
				 cholYes.setValue(true);
			 }
			 else cholNo.setValue(true);

			 button = medical.getHighChol();
			 if (button.equals("yes")) {
				 totalCholYes.setValue(true);
			 }
			 else if (button.equals("no"))
			 {totalCholNo.setValue(true);}

			 else totalCholDontKnow.setValue(true); 

			 button = medical.getLifeStyleChange();
			 if (button.equals("yes")) {
				 lifestyleYes.setValue(true);
			 }
			 else if (button.equals("no"))
			 {lifestyleNo.setValue(true);}

			 else lifestyleDontKnow.setValue(true); 

			 button = medical.getMedChol();
			 if (button.equals("currently")) {
				 medicationYes.setValue(true);
			 }
			 else if (button.equals("never"))
			 {medicationNever.setValue(true);}

			 else if (button.equals("past")) {
				 medicationPast.setValue(true); 
			 }
			 else if (button.equals("dont")) {
				 medicationDontKnow.setValue(true); 
			 }
			 
			 button = medical.getCvd();
			 if (button.equals("yes")) {
				 heartDiseaseYes.setValue(true);
			 }
			 else if (button.equals("no")) {
				 heartDiseaseNo.setValue(true);
			 }			 
			 else {
				 heartDiseaseDontKnow.setValue(true);
			 }
			 
			 button = medical.getHyper();
			 if (button.equals("yes")) {
				 hyperYes.setValue(true);
			 }
			 else if (button.equals("no")) {
				 hyperNo.setValue(true);
			 }
			 
			 else {
				 hyperDontKnow.setValue(true);
			 }
			 
			 button = medical.getBloodPressure();
			 if (button.equals("yes")) {
				 bloodPressureYes.setValue(true);
			 }
			 else if (button.equals("no")) {
				 bloodPressureNo.setValue(true);
			 }
			 
			 button = medical.getMedBlood();
			 if (button.equals("currently")) {
				 hyperMedYes.setValue(true);
			 }
			 else if (button.equals("never"))
			 {hyperMedNever.setValue(true);}

			 else if (button.equals("past")) {
				 hyperMedPast.setValue(true); 
			 }
			 else if (button.equals("dont")) {
				 hyperMedDontKnow.setValue(true); 
			 }
			 
			 button = medical.getMellitus();
			 if (button.equals("yes")) {
				 mellitusYes.setValue(true);
			 }
			 else if (button.equals("no")) {
				 mellitusNo.setValue(true);
			 }			 
			 else {
				 mellitusDontKnow.setValue(true);
			 }
			 
			 button = medical.getDiabetesTreat();
			 if (button.equals("yes")) {
				 mellitusTreatmentYes.setValue(true);
			 }
			 else if (button.equals("no")) {
				 mellitusTreatmentNo.setValue(true);
			 }			 
			 else {
				 mellitusTreatmentDontKnow.setValue(true);
			 }
			 
			 
			 button = medical.getSugar();
			 if (button.equals("yes")) {
				 sugarYes.setValue(true);
			 }
			 else if (button.equals("no")) {
				 sugarNo.setValue(true);
			 }			 
			 else {
				 sugarDontKnow.setValue(true);
			 }
			 
			 button = medical.getKidney();
			 if (button.equals("yes")) {
				 kidneyYes.setValue(true);
			 }
			 else if (button.equals("no")) {
				 kidneyNo.setValue(true);
			 }			 
			 else {
				 kidneyDontKnow.setValue(true);
			 }
			 
			 
			 return;
		 }
		 private void updateMedicalDB() {
		
			 callServiceSetup();
			 medicalData =  createMedicalInfo();
			 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
				 @Override	 
	            public void onSuccess(Boolean result) {
	            		if ((result == false)){	            		
	            			InlineLabel error = new InlineLabel(constants.data_not_updated());
	            			showErrorPopupPanel(error, "red");            			
	            		}            		
	            		else {
	            			////InlineLabel error = new InlineLabel(constants.med_complete());
	            			//showErrorPopupPanel(error, "green");  
	            			content.selectTab(DECK_HISTORY);
	            			content.getTabWidget(DECK_MEDICAL).getElement().getStyle().setProperty("backgroundColor", "red");
	            		}
	                 
	              }
				@Override
				public void onFailure(Throwable caught) {
					InlineLabel error = new InlineLabel(constants.data_not_updated());
	    			showErrorPopupPanel(error, "red");			
					
				}
			  };
			  
	    	  InminddServiceSvc.updateMedicalHealth(medicalData, callback);
		 }
	    	 
		private MedicalInfo createMedicalInfo() {
			 medicalData  = new MedicalInfo();
			 medicalData.setUserId(login.getUserId());
			 medicalData.setCent(heightInputCents);
			 medicalData.setFeet(heightInputFeet);
			 medicalData.setInches(heightInputInches);
			 medicalData.setKilo(weightInputKilos);
			 medicalData.setStone(weightInputStone);
			 medicalData.setLbs(weightInputLbs);
			 medicalData.setMmol(mmol.getDoubleValue());
			 medicalData.setSystolic(systolicInput);
			 medicalData.setDiastolic(diastolicInput);
			 
			 /**
			  * Check each button selected and create appropriate 
			  * answer in MedicalInfo
			  */
			 if (cholYes.getValue())
				 medicalData.setChol("yes");
			 else if (cholNo.getValue())
				 medicalData.setChol("dont");
			 
			 if (totalCholYes.getValue())
				 medicalData.setHighChol("yes");
			 else if (totalCholNo.getValue())
				 medicalData.setHighChol("no");
			 else if (totalCholDontKnow.getValue())
				 medicalData.setHighChol("dont");
			 
			 if (lifestyleYes.getValue())
				 medicalData.setLifeStyleChange("yes");
			 else if (lifestyleNo.getValue())
				 medicalData.setLifeStyleChange("no");
			 else if (lifestyleDontKnow.getValue())
				 medicalData.setLifeStyleChange("dont");
			 
			 
			 if (medicationYes.getValue())
				 medicalData.setMedChol("currently");
			 else if (medicationPast.getValue())
				 medicalData.setMedChol("past");
			 else if (medicationNever.getValue())
				 medicalData.setMedChol("never");
			 else if (medicationDontKnow.getValue())
					 medicalData.setMedChol("dont");
			 
			 if (heartDiseaseYes.getValue())
				 medicalData.setCvd("yes");
			 else if (heartDiseaseNo.getValue())
				 medicalData.setCvd("no");
			 else if (heartDiseaseDontKnow.getValue())
				 medicalData.setCvd("dont");
			 
			 if (hyperYes.getValue())				 
				 medicalData.setHyper("yes");
			 else if (hyperNo.getValue())
				 medicalData.setHyper("no");
			 else if (hyperDontKnow.getValue())
				 medicalData.setHyper("dont");
			 
			 if (bloodPressureYes.getValue())				 
				 medicalData.setBloodPressure("yes");
			 else if (bloodPressureNo.getValue())
				 medicalData.setBloodPressure("no");
			 
			 if (hyperMedYes.getValue())				 
				 medicalData.setMedBlood("currently");
			 else if (hyperMedNever.getValue())
				 medicalData.setMedBlood("never");
			 else if (hyperMedPast.getValue())
				 medicalData.setMedBlood("past");
			 else if (hyperMedDontKnow.getValue())
				 medicalData.setMedBlood("dont");
			 
			 if (mellitusYes.getValue())				 
				 medicalData.setMellitus("yes");
			 else if (mellitusNo.getValue())
				 medicalData.setMellitus("no");
			
			 else if (mellitusDontKnow.getValue())
				 medicalData.setMellitus("dont");
			 
			 if (mellitusTreatmentYes.getValue())				 
				 medicalData.setDiabetesTreat("yes");
			 else if (mellitusTreatmentNo.getValue())
				 medicalData.setDiabetesTreat("no");
			 else if (mellitusTreatmentDontKnow.getValue())
				 medicalData.setDiabetesTreat("dont");
			 
			 if (sugarYes.getValue())				 
				 medicalData.setSugar("yes");
			 else if (sugarNo.getValue())
				 medicalData.setSugar("no");
			 else if (sugarDontKnow.getValue())
				 medicalData.setSugar("dont");
				
			 if (kidneyYes.getValue())				 
				 medicalData.setKidney("yes");
			 else if (kidneyNo.getValue())
				 medicalData.setKidney("no");
			 else if (kidneyDontKnow.getValue())
				 medicalData.setKidney("dont");
			 return medicalData;
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
			popup.setPopupPosition(190,550);
			popup.setWidth("750px");
			popup.show();

		}

	}
