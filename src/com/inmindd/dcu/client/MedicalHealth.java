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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inmindd.dcu.shared.MedicalInfo;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.User;

public class MedicalHealth {
	
	private User user;
	private Login login;
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
	
	public MedicalHealth() {
	
	}
	
	public FlowPanel setupMedicalHealthPanel(Login login) {
		this.login = login;
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				"About Your medical health</h1>");
		HTMLPanel header = new HTMLPanel("<h3>" +
				"The following questions are about your health </h3>");

		medicalHealthPanel.add(mainHeader);
		medicalHealthPanel.add(header);

		Button prev = new Button("Retrieve previous data ?");

		// Listen for mouse events on the previous data button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getMedicalData();
			}
		});
		medicalHealthPanel.add(prev);
		medicalHealthPanel.add(new HTMLPanel("<span>  <br>  </span>"));

		heightCent = new DataField("What is your height in cm ? ");
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
					error = new InlineLabel("Height entered is outside range. Please reenter if incorrect.");
					showErrorPopupPanel(error,"red");
					heightCent.setFocus();
					heightCent.setText("");
				}
			}
		});

		heightFeet = new DataField("OR in feet ");

		heightFeet.getElement().getStyle().setProperty("marginLeft", "52px");
		heightFeet.getElement().getStyle().setProperty("fontWeight", "bold");

		heightInches = new DataField("inches ");
		heightInches.getElement().getStyle().setProperty("marginLeft", "20px");
		heightInches.getElement().getStyle().setProperty("fontWeight", "bold");


		heightFeet.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double feet = heightFeet.getDoubleValue();	    	

				if ((feet >=  4)  && (feet <8)) {

				}		    	
				else {
					error = new InlineLabel("Height entered is outside range. Please reenter if incorrect.");
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

				double totalInches = (feet * 12 ) + inches;	  
				if ((totalInches  >=  MIN_HEIGHT_INCHES) && (totalInches <=  MAX_HEIGHT_INCHES)) {
					heightInputFeet = feet;
					heightInputInches = inches;
					heightInputCents = 0;
					weightKilos.setFocus();
				}

				else {
					error = new InlineLabel("Height entered is outside range. Please reenter if incorrect.");
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

		weightKilos = new DataField("What is your  weight in kgs ? ");
		weightKilos.getElement().getStyle().setProperty("fontWeight", "bold");

		weightStone = new DataField("OR in stones ");
		weightStone.getElement().getStyle().setProperty("marginLeft", "63px");
		weightStone.getElement().getStyle().setProperty("fontWeight", "bold");

		weightLbs = new DataField("lbs ");
		weightLbs.getElement().getStyle().setProperty("marginLeft", "33px");
		weightLbs.getElement().getStyle().setProperty("fontWeight", "bold");

		weightKilos.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double weight = weightKilos.getDoubleValue();
				String weightText = weightKilos.getText();
				if (weightText == "" || weight == -1) {
					weightStone.setFocus();
					return;
				}

				else if ((weight >=  MIN_WEIGHT_KILOS) && (weight <=  MAX_WEIGHT_KILOS)) {	
					weightInputStone = 0;
					weightInputLbs = 0;
					weightInputKilos = weight;
					cholYes.setFocus(true);
				}

				else {
					error = new InlineLabel("Weight entered is outside range. Please reenter if incorrect.");
					showErrorPopupPanel(error,"red");
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

				}

				else {
					error = new InlineLabel("Weight entered is outside range. Please reenter if incorrect.");
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
					error = new InlineLabel("Height entered is outside range. Please reenter if incorrect.");
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

		Button btn = new Button("submit");
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
		cholLabel = new InlineLabel("Do you know your total cholesterol levels?");

		cholLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		hPanel.setWidth("750x");
		hPanel2.setWidth("550x");
		hPanel.add(cholLabel);
		cholYes = new RadioButton("chol", "YES");
		cholYes.getElement().getStyle().setProperty("marginLeft", "303px");
		cholNo = new RadioButton("chol", "I DON'T KNOW");
		cholNo.getElement().getStyle().setProperty("marginLeft", "10px");
		mmol = new DataField("Please enter it", "mmol");
		mmol.getElement().getStyle().setProperty("fontWeight", "bold");
		mmol.getElement().getStyle().setProperty("marginLeft", "20px");
		mmol.getElement().getStyle().setProperty("backgroundColor", "cyan");
		mmol.setVisible(false);
		mmol.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				double mmolInput = mmol.getDoubleValue();

				if (!((mmolInput >=  MIN_MMOL) && (mmolInput <=  MAX_MMOL))) {
					error = new InlineLabel("MMOL entered is outside range. Please reenter if incorrect.");
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
		totalCholLabel = new InlineLabel("Have you ever been told by a doctor that you have ");
		totalCholLabel.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_2 = new InlineLabel(" high total cholesterol? ");

		lbl_2.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.add(totalCholLabel);

		vPanel.add(lbl_2);

		vPanel.setWidth("580px");
		totalCholYes = new RadioButton("highchol", "YES");
		totalCholYes.getElement().getStyle().setProperty("marginLeft", "10px");
		totalCholNo = new RadioButton("highchol", "NO");
		totalCholNo.getElement().getStyle().setProperty("marginLeft", "10px");
		totalCholDontKnow = new RadioButton("highchol", "I DON'T KNOW / CAN'T REMEMBER");
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


		lifestyleLabel = new InlineLabel("Are you currently receiving advice about lifestyle changes ");
		lifestyleLabel.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_2 = new InlineLabel("to address total cholesterol? ");
		lbl_2.getElement().getStyle().setProperty("fontWeight", "bold");

		vPanel.add(lifestyleLabel);

		vPanel.add(lbl_2);

		vPanel.setWidth("580px");
		lifestyleYes = new RadioButton("lifestyle", "YES");
		lifestyleYes.getElement().getStyle().setProperty("marginLeft", "10px");
		lifestyleNo = new RadioButton("lifestyle", "NO");
		lifestyleNo.getElement().getStyle().setProperty("marginLeft", "10px");
		lifestyleDontKnow = new RadioButton("lifestyle", "I DON'T KNOW");
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
		medicationLabel = new InlineLabel("Do you take medication for high total cholesterol level?");
		medicationLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(medicationLabel);
		medicationYes = new RadioButton("medic", "YES CURRENTLY");
		medicationYes.getElement().getStyle().setProperty("marginLeft", "10px");
		medicationPast = new RadioButton("medic", "YES, IN THE PAST BUT NOT CURRENTLY");
		medicationPast.getElement().getStyle().setProperty("marginLeft", "10px");
		medicationNever = new RadioButton("medic", "NEVER");
		medicationNever.getElement().getStyle().setProperty("marginLeft", "10px");
		medicationDontKnow = new RadioButton("medic", "I DON'T KNOW");
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


		heartDiseaseLabel  = new InlineLabel("Have you ever been told by a doctor or other health professional");
		heartDiseaseLabel.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_2 = new InlineLabel("that you have cardiovascular / heart disease");
		lbl_2.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_3 = new InlineLabel("	(which includes the following conditions: heart attack, angina, heart failure, stroke, including mini-stroke)?");
		lbl_3.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.add(heartDiseaseLabel);		
		vPanel.add(lbl_2);
		vPanel.add(lbl_3);

		vPanel.setWidth("580px");
		heartDiseaseYes = new RadioButton("heart", "YES");
		heartDiseaseYes.getElement().getStyle().setProperty("marginLeft", "10px");
		heartDiseaseNo = new RadioButton("heart", "NO");
		heartDiseaseNo.getElement().getStyle().setProperty("marginLeft", "10px");
		heartDiseaseDontKnow = new RadioButton("lifestyle", "I DON'T KNOW");
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
		hyperLabel = new InlineLabel("Have you ever been told by a doctor that you have high blood pressure?");
		hyperLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(hyperLabel);
		hyperYes = new RadioButton("hyper", "YES");
		hyperYes.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperNo = new RadioButton("hyper", "NO");
		hyperNo.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperDontKnow = new RadioButton("hyper", "I DON'T KNOW");
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
		hyperMedLabel  = new InlineLabel("Do you take medication for high blood pressure?");
		hyperMedLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(hyperMedLabel);
		hyperMedYes = new RadioButton("hypermed", "YES CURRENTLY");
		hyperMedYes.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperMedPast = new RadioButton("hypermed", "YES, IN THE PAST BUT NOT CURRENTLY");
		hyperMedPast.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperMedNever = new RadioButton("hypermed", "NEVER");
		hyperMedNever.getElement().getStyle().setProperty("marginLeft", "10px");
		hyperMedDontKnow = new RadioButton("hypermed", "I DON'T KNOW");
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
		mellitusLabel = new InlineLabel("Have you ever been told by a doctor that you have diabetes? ");
		mellitusLabel.getElement().getStyle().setProperty("fontWeight", "bold");	
		vPanel.add(mellitusLabel);

		vPanel.setWidth("580px");
		mellitusYes = new RadioButton("mellitus", "YES");
		mellitusYes.getElement().getStyle().setProperty("marginLeft", "10px");

		mellitusNo = new RadioButton("mellitus", "NO");
		mellitusNo.getElement().getStyle().setProperty("marginLeft", "10px");
		mellitusDontKnow = new RadioButton("mellitus", "I DON'T KNOW / CAN'T REMEMBER");
		mellitusDontKnow.getElement().getStyle().setProperty("marginLeft", "10px");
		
		dock.add(vPanel,DockPanel.WEST);

		dock.add(mellitusDontKnow , DockPanel.EAST);
		dock.add(mellitusNo,DockPanel.EAST);
		dock.add(mellitusYes,DockPanel.EAST);
	
		
		return dock;	  

	}
    private VerticalPanel getMellitusNote() {
    	VerticalPanel note = new VerticalPanel();
    	InlineLabel mellitusNote = new InlineLabel("(Please note that does not include gestational diabetes, a type of diabetes " +
    	"that effects women in pregnancy)");
    	mellitusNote.getElement().getStyle().setProperty("fontWeight", "bold");
    	note.add(mellitusNote);
    	return note;
    }
    
	private  DockPanel getDiabetesTreatment() {
		DockPanel dock = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		mellitusTreatmentLabel = new InlineLabel("Are you currently receiving treatment for diabetes?");
		mellitusTreatmentLabel .getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(mellitusTreatmentLabel );
		mellitusTreatmentYes = new RadioButton("diabetes", "YES");
		mellitusTreatmentYes.getElement().getStyle().setProperty("marginLeft", "10px");
		mellitusTreatmentNo = new RadioButton("diabetes", "NO");
		mellitusTreatmentNo.getElement().getStyle().setProperty("marginLeft", "10px");
		mellitusTreatmentDontKnow = new RadioButton("diabetes", "I DON'T KNOW");
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
		sugarLabel = new InlineLabel("Have you ever been told by a doctor");
		sugarLabel.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl_2 = new InlineLabel("that you have high sugar levels in your blood or in your urine?");
		lbl_2.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.add(sugarLabel);

		vPanel.add(lbl_2);

		vPanel.setWidth("580px");
		sugarYes = new RadioButton("sugar", "YES");
		sugarYes.getElement().getStyle().setProperty("marginLeft", "10px");
		sugarNo = new RadioButton("sugar", "NO");
		sugarNo.getElement().getStyle().setProperty("marginLeft", "10px");
		sugarDontKnow = new RadioButton("sugar", "I DON'T KNOW");
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
		bloodPressureLabel = new InlineLabel("Do you know what your blood pressure is ?");
		bloodPressureLabel .getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("580px");
		vPanel.add(bloodPressureLabel );
		bloodPressureYes = new RadioButton("blood", "YES");
		bloodPressureYes.getElement().getStyle().setProperty("marginLeft", "10px");
		bloodPressureNo = new RadioButton("blood", "NO");
		bloodPressureNo.getElement().getStyle().setProperty("marginLeft", "10px");
		dockBlood.add(vPanel, DockPanel.WEST);


		dockBlood.add(bloodPressureNo, DockPanel.EAST);
		dockBlood.add(bloodPressureYes, DockPanel.EAST);
		return dockBlood;


	}

	private FlowPanel getBloodReadings() {

		bloodReadingsPanel = new FlowPanel();
		FlowPanel firstPanel  = new FlowPanel();
		InlineLabel lbl1 = new InlineLabel("Please record your most recent blood pressure.");
		lbl1.getElement().getStyle().setProperty("fontWeight", "bold");

		//firstPanel.setWidth("580px");
		firstPanel.add(lbl1);
		FlowPanel secondPanel = new FlowPanel();

		InlineLabel lbl2 = new InlineLabel("Only include those that have been taken in the last year : ");
		lbl2.getElement().getStyle().setProperty("fontWeight", "bold");

		InlineLabel lbl3 = new InlineLabel("Systolic blood pressure.");

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

		InlineLabel lbl5 = new InlineLabel("Diastolic blood pressure.");

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
			kidneyLabel = new InlineLabel("Have you ever been told by a doctor that you have a chronic kidney disease?");
			kidneyLabel.getElement().getStyle().setProperty("fontWeight", "bold");			
			vPanel.add(kidneyLabel);				
			vPanel.setWidth("580px");
			kidneyYes = new RadioButton("kidney", "YES");
			kidneyYes.getElement().getStyle().setProperty("marginLeft", "10px");
			kidneyNo = new RadioButton("kidney", "NO");
			kidneyNo.getElement().getStyle().setProperty("marginLeft", "10px");
			kidneyDontKnow = new RadioButton("kidney", "I DON'T KNOW");
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
				
				InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
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
			if (! checkButtonSelected(hyperLabel, hyperYes, hyperNo))
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

			error = new InlineLabel("Please select the question  button indicated above");			
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
					error = new InlineLabel("MMol reading entered is non numeric. Please re-enter");
					showErrorPopupPanel(error,"red");
					systolic.setFocus(true);				
					return false;

				}
				if (!((mmolInput >=  MIN_MMOL) && (mmolInput <=  MAX_MMOL))) {
					error = new InlineLabel("MMol reading entered is outside range. Please reenter if incorrect.");
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
			
					error = new InlineLabel("Invalid Height Input- please re-enter.");
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
			
					error = new InlineLabel("Invalid Weight Input- please re-enter.");
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
					error = new InlineLabel("Systolic reading entered is non numeric. Please re-enter");
					showErrorPopupPanel(error, "red");
					systolic.setFocus(true);				
					return false;

				}
				if (!((systolicInput >=  MIN_SYSTOLIC) && (systolicInput <=  MAX_SYSTOLIC))) {
					error = new InlineLabel("Systolic reading entered is outside range. Please reenter if incorrect.");
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
				{	error = new InlineLabel("Diastolic is  non numeric. Please re-enter");
					showErrorPopupPanel(error, "red");
					systolic.setFocus(true);
					return false;

				}
				if (!((diastolicInput >=  MIN_DIASTOLIC) && (diastolicInput <=  MAX_DIASTOLIC))) {
					error = new InlineLabel("Diastolic reading entered is outside range. Please reenter if incorrect.");
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

				 InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
				 showErrorPopupPanel(error, "red");
				 return;

			 }
			 callServiceSetup();

			 AsyncCallback<MedicalInfo> callback =  new AsyncCallback<MedicalInfo>(){

				 @Override	 
				 public void onSuccess(MedicalInfo medical) {
					 if ((medical ==null)){	            		
						 InlineLabel error = new InlineLabel("Medical Data not retrieved. No data available for this patient ");
						 showErrorPopupPanel(error, "red");            			
					 }            		
					 else {
						 InlineLabel error = new InlineLabel("Medical data  retrieved- Edit as necessary");
						 showErrorPopupPanel(error, "green");  
						 populatePanel(medical);
						
					 }

				 }
				 @Override
				 public void onFailure(Throwable caught) {
					 InlineLabel error = new InlineLabel("Medical Info not retrieved");
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
	            			InlineLabel error = new InlineLabel("Medical health info not updated");
	            			showErrorPopupPanel(error, "red");            			
	            		}            		
	            		else {
	            			InlineLabel error = new InlineLabel("Medical health Info updated  -  Proceed to next Panel");
	            			showErrorPopupPanel(error, "green");            			            			
	            		}
	                 
	              }
				@Override
				public void onFailure(Throwable caught) {
					InlineLabel error = new InlineLabel("Database update error");
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
			popup.setPopupPosition(190,700);
			popup.setWidth("550px");
			popup.show();

		}

	}
