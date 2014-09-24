package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.client.MyHandler;
import com.inmindd.dcu.client.MyChangeHandler;
import com.inmindd.dcu.shared.CognitiveOneInfo;
import com.inmindd.dcu.shared.SmokeAlcoholInfo;
import com.inmindd.dcu.shared.User;

public class CognitiveOne {
	
	
	private static final int DECK_COGNITIVE1 = 6;
	private static final int DECK_COGNITIVE2 = 7;
	
	private FlowPanel cognitiveOnePanel;
	private TabLayoutPanel content;
	
	private static final String LOGO_IMAGE_NAME = "images.jpeg";
	private ScrollPanel scroll = new ScrollPanel();
	private TextBox yearsFormalEducation;
	private TextBox yearsNonformalEducation;
	private InlineLabel yearsEducation; 
	private InlineLabel yearsNonForEducation; 
	private Login login;
	private CognitiveOneInfo cognitiveOne;
	private InminddServiceAsync InminddServiceSvc;
	private TextBox managerYears;
	private TextBox managerSimulYears;
	private TextBox professionalYears;
	private TextBox professionalSimulYears;
	private TextBox technicianYears;
	private TextBox technicianSimulYears;
	private TextBox clericalYears;
	private TextBox clericalSimulYears;
	private TextBox serviceYears;
	private TextBox serviceSimulYears;	
	private TextBox agriculturalYears;
	private TextBox agriculturalSimulYears;
	private TextBox craftYears;
	private TextBox craftSimulYears;
	private TextBox plantYears;
	private TextBox plantSimulYears;
	private TextBox elementaryYears;
	private TextBox elementarySimulYears;
	private int leftMargin;
	
	public static CognitiveOne lastinstance;
	
	static  InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	public CognitiveOne() {
		lastinstance = this;
	}
	
	public void setContent(TabLayoutPanel content) {
		this.content = content;
	}
	
	public static void clearInputs() {
		lastinstance.agriculturalSimulYears.setText("");
		lastinstance.agriculturalYears.setText("");
		lastinstance.clericalSimulYears.setText("");
		lastinstance.clericalYears.setText("");
		lastinstance.craftSimulYears.setText("");
		lastinstance.craftYears.setText("");
		lastinstance.elementarySimulYears.setText("");
		lastinstance.elementaryYears.setText("");
		lastinstance.managerSimulYears.setText("");
		lastinstance.managerYears.setText("");
		lastinstance.plantSimulYears.setText("");
		lastinstance.plantYears.setText("");
		lastinstance.professionalSimulYears.setText("");
		lastinstance.professionalYears.setText("");
		lastinstance.serviceSimulYears.setText("");
		lastinstance.serviceYears.setText("");
		lastinstance.technicianSimulYears.setText("");
		lastinstance.technicianYears.setText("");		
		lastinstance.yearsFormalEducation.setText("");
		lastinstance.yearsNonformalEducation.setText("");
	
		
		
		
	}
	public FlowPanel setupCognitiveOnePanel(Login login) {
		this.login = login;
		HTMLPanel mainHeader = new HTMLPanel("<h1>" +
				
		constants.cognitive_p1() + "</h1>");
	
		HTMLPanel educ = new HTMLPanel("<h3>" +
				constants.education() + "</h3>");
		educ.getElement().getStyle().setProperty("textDecoration", "underline");

		Button prev = new Button(constants.review());


		// Listen for mouse events on the previous button.
		prev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getCognitiveOneData();
			}
		});
		
		cognitiveOnePanel = new FlowPanel();
		cognitiveOnePanel.add(mainHeader);
		cognitiveOnePanel.add(prev);
		cognitiveOnePanel.setWidth("100%");
		
		cognitiveOnePanel.add(educ);
		cognitiveOnePanel.add(addFormalEducation());
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		cognitiveOnePanel.add(addNonFormalEducation());
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		HTMLPanel occup = new HTMLPanel("<h3>" +
				constants.occupations() + "</h3>");
		occup.getElement().getStyle().setProperty("textDecoration", "underline");
		InlineLabel jobLabel = new InlineLabel(constants.type_job());
		jobLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		
		cognitiveOnePanel.add(occup);
		cognitiveOnePanel.add(jobLabel);
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		
		Button neverBtn = new Button("Never employed - submit data");
		cognitiveOnePanel.add(neverBtn);	

		// Listen for mouse events on the never employed button.
		neverBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {	
				checkResponses();
				
				// clear out any previously entered responses 
				lastinstance.agriculturalSimulYears.setText("");
				lastinstance.agriculturalYears.setText("");
				lastinstance.clericalSimulYears.setText("");
				lastinstance.clericalYears.setText("");
				lastinstance.craftSimulYears.setText("");
				lastinstance.craftYears.setText("");
				lastinstance.elementarySimulYears.setText("");
				lastinstance.elementaryYears.setText("");
				lastinstance.managerSimulYears.setText("");
				lastinstance.managerYears.setText("");
				lastinstance.plantSimulYears.setText("");
				lastinstance.plantYears.setText("");
				lastinstance.professionalSimulYears.setText("");
				lastinstance.professionalYears.setText("");
				lastinstance.serviceSimulYears.setText("");
				lastinstance.serviceYears.setText("");
				lastinstance.technicianSimulYears.setText("");
				lastinstance.technicianYears.setText("");		
				updateCognitiveOneDB();
			}   		

		});
		
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveOnePanel.add(addManagers());
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveOnePanel.add(addProfessionals());
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveOnePanel.add(addTechnicians());
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveOnePanel.add(addClerical());
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));
		cognitiveOnePanel.add(addService());
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));		
		cognitiveOnePanel.add(addAgriculture());
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));		
		cognitiveOnePanel.add(addCraft());
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));		
		cognitiveOnePanel.add(addPlant());
		
		cognitiveOnePanel.add(new HTMLPanel("<span>  <br>  </span>"));		
		cognitiveOnePanel.add(addElementary());
		
		Button btn = new Button(constants.submit());
		cognitiveOnePanel.add(btn);	

		// Listen for mouse events on the submit button.
		btn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				if (!checkResponses()) {
					return;
				}
				
				updateCognitiveOneDB();
			}   		

		});
		scroll.setSize("100%", "70%");

		scroll.add(cognitiveOnePanel);
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

	private void updateCognitiveOneDB() {
	
	 callServiceSetup();
	 cognitiveOne = createCognitiveOneInfo();
	 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
		 @Override	 
       public void onSuccess(Boolean result) {
       		if ((result == false)){	            		
       			InlineLabel error = new InlineLabel("Cognitive Activity part One  info not updated");
       			showErrorPopupPanel(error, "red");            			
       		}            		
       		else {
       			//InlineLabel error = new InlineLabel("Cognitive Activity Part One  updated  -  Proceed to Cognitive Activity 2");
       			//showErrorPopupPanel(error, "green");  
       			content.selectTab(DECK_COGNITIVE2);
       			content.getTabWidget(DECK_COGNITIVE1).getElement().getStyle().setProperty("backgroundColor", "red");
       		}
            
         }
		@Override
		public void onFailure(Throwable caught) {
			InlineLabel error = new InlineLabel("Database update error");
			showErrorPopupPanel(error, "red");			
			
		}
	  };
	  
	  InminddServiceSvc.updateCognitiveOne(cognitiveOne, callback);
	}
	
	
	private CognitiveOneInfo createCognitiveOneInfo() {
		cognitiveOne = new CognitiveOneInfo();
		User user = login.getUser();
		cognitiveOne.setUserId(user.getUserId());
		cognitiveOne.setFormalEducationYears(getValueAsDouble(yearsFormalEducation));
		cognitiveOne.setNonFormalEducationYears(getValueAsDouble(yearsNonformalEducation));
		cognitiveOne.setManager(getValueAsInt(managerYears));
		cognitiveOne.setManagerSimulYears(getValueAsInt(managerSimulYears));
		cognitiveOne.setProfessional(getValueAsInt(professionalYears));
		cognitiveOne.setProfessionalSimulYears(getValueAsInt(professionalSimulYears));
		
		cognitiveOne.setTechnician(getValueAsInt(technicianYears));
		cognitiveOne.setTechnicianSimulYears(getValueAsInt(technicianSimulYears));
		
		cognitiveOne.setClerical(getValueAsInt(clericalYears));
		cognitiveOne.setClericalSimulYears(getValueAsInt(clericalSimulYears));
		
		cognitiveOne.setService(getValueAsInt(serviceYears));		
		cognitiveOne.setServiceSimulYears(getValueAsInt(serviceSimulYears));
		
		cognitiveOne.setAgriculture(getValueAsInt(agriculturalYears));		
		cognitiveOne.setAgricultureSimulYears(getValueAsInt(agriculturalSimulYears));
		
		cognitiveOne.setCraft(getValueAsInt(craftYears));		
		cognitiveOne.setCraftSimulYears(getValueAsInt(craftSimulYears));
		
		cognitiveOne.setPlant(getValueAsInt(plantYears));		
		cognitiveOne.setPlantSimulYears(getValueAsInt(plantSimulYears));
		
		cognitiveOne.setElementary(getValueAsInt(elementaryYears));		
		cognitiveOne.setElementarySimulYears(getValueAsInt(elementarySimulYears));
		
		return cognitiveOne;
	}
	// just check years education
	private boolean checkResponses() {
		User user = login.getUser();
		if (user.getUserId() == null) {
			
			InlineLabel error  = new InlineLabel(constants.register());
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		yearsEducation.getElement().getStyle().setProperty("color", "black");
		yearsNonForEducation.getElement().getStyle().setProperty("color", "black");
		
		try {
			double years  = Double.parseDouble(yearsFormalEducation.getText());	
		}

		catch (Exception e)
		{
			InlineLabel error = new InlineLabel(constants.formal_error());	
			showErrorPopupPanel(error, "red");
			yearsEducation.getElement().getStyle().setProperty("color", "red");
			yearsNonForEducation.getElement().getStyle().setProperty("color", "red");
			return false;
		}
		
		try {
			double years  = Double.parseDouble(yearsNonformalEducation.getText());	
		}

		catch (Exception e)
		{
			InlineLabel error = new InlineLabel(constants.non_formal_error());	
			showErrorPopupPanel(error, "red");
			yearsEducation.getElement().getStyle().setProperty("color", "red");
			yearsNonForEducation.getElement().getStyle().setProperty("color", "red");
			return false;
		}
	
	
		return true;
	}
	private HorizontalPanel addFormalEducation() {
		
		HorizontalPanel cogoLine  = new HorizontalPanel();
		
		yearsFormalEducation = new TextBox();
		yearsFormalEducation.setMaxLength(4);
		yearsFormalEducation.setWidth("2em"); 
	 
		//yearsFormalEducation.getElement().getStyle().setProperty("marginLeft", "50px");
		yearsFormalEducation.setStyleName("pos7");
		yearsEducation = new InlineLabel(constants.formal_education());
		yearsEducation.getElement().getStyle().setProperty("fontWeight", "bold");		
		cogoLine.add(yearsEducation);
		
		cogoLine.add(yearsFormalEducation);
		InlineLabel years = new InlineLabel(constants.years());
		years.getElement().getStyle().setProperty("fontWeight", "bold");
		//years.getElement().getStyle().setProperty("marginLeft", "5px");
		years.setStyleName("pos8");
		cogoLine.add(years);
		return cogoLine;
		
		
		
	}
	
	private HorizontalPanel addNonFormalEducation() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		
		yearsNonformalEducation = new TextBox();
		yearsNonformalEducation.setMaxLength(4);
		
		yearsNonformalEducation.setWidth("2em"); 
		
		//yearsNonformalEducation.getElement().getStyle().setProperty("marginLeft", "348px");
		yearsNonformalEducation.setStyleName("pos7");
		cogoLine.add(yearsNonformalEducation);
		InlineLabel years = new InlineLabel(constants.years());
		years.getElement().getStyle().setProperty("fontWeight", "bold");
		//years.getElement().getStyle().setProperty("marginLeft", "5px");
		years.setStyleName("pos8");
		cogoLine.add(years);
		yearsNonForEducation = new InlineLabel(constants.non_formal());
		yearsNonForEducation.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.non_formal_title();
		cogoLine.add(yearsNonForEducation);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		
		
		return cogoLine;
		
	}
	
	
	private FlowPanel addManagers() {
		FlowPanel cogoLine  = new FlowPanel();
		managerYears = new TextBox();
		managerSimulYears = new TextBox();
		managerYears.setMaxLength(3);
		managerYears.setWidth("2em");
		
		
		//managerYears.getElement().getStyle().setProperty("marginLeft", "auto");
		managerYears.setStyleName("pos5");
		
		InlineLabel label1 = new InlineLabel(constants.managers());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("marginRight", "10px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.director();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(managerYears);
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		managerYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(managerYears) > 0)		
					showSimul(managerSimulYears);
			
				}
		});
		return cogoLine;
		
	}
	
	private HorizontalPanel addProfessionals() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		professionalYears = new TextBox();;
		professionalSimulYears = new TextBox();
		professionalYears.setMaxLength(3);
		professionalYears.setWidth("2em");

		
		professionalYears.getElement().getStyle().setProperty("marginLeft", "auto");
		professionalYears.setStyleName("pos5");
		InlineLabel label1 = new InlineLabel(constants.professionals());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.pro();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(professionalYears);
	
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		professionalYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(professionalYears) > 0)	
					showSimul(professionalSimulYears);			
				}
		});
		return cogoLine;
		
	}
	
	private HorizontalPanel addTechnicians() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		technicianSimulYears = new TextBox();
		technicianYears = new TextBox();
		technicianYears.setMaxLength(3);
		technicianYears.setWidth("2em");
		//technicianYears.getElement().getStyle().setProperty("marginLeft", "auto");
		technicianYears.setStyleName("pos5");
		InlineLabel label1 = new InlineLabel(constants.technicians());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.technicianexample();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(technicianYears);
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		technicianYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(technicianYears) > 0)	
					showSimul(technicianSimulYears);
			
				}
		});
		return cogoLine;
		
	}
	
	private HorizontalPanel addClerical() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		clericalSimulYears = new TextBox();
		clericalYears = new TextBox();
		clericalYears.setMaxLength(3);
		clericalYears.setWidth("2em");
		//clericalYears.getElement().getStyle().setProperty("marginLeft", "auto");
		clericalYears.setStyleName("pos5");
		InlineLabel label1 = new InlineLabel(constants.clerical_1());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.clericalexample();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(clericalYears);
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		clericalYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(clericalYears) > 0)	
					showSimul(clericalSimulYears);
			
				}
		});
		return cogoLine;
		
	}
	
	private HorizontalPanel addService() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		serviceSimulYears = new TextBox();
		serviceYears = new TextBox();
		serviceYears.setMaxLength(3);
		serviceYears.setWidth("2em");
		//serviceYears.getElement().getStyle().setProperty("marginLeft", "auto");
		serviceYears.setStyleName("pos5");
		InlineLabel label1 = new InlineLabel(constants.services_1());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.servicesexample();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(serviceYears);
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		serviceYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(serviceYears) > 0)	
					showSimul(serviceSimulYears);
			
				}
		});
		return cogoLine;
		
	}
	
	
	private HorizontalPanel addAgriculture() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		agriculturalSimulYears = new TextBox();
		agriculturalYears = new TextBox();
		agriculturalYears.setMaxLength(3);
		agriculturalYears.setWidth("2em");
		//agriculturalYears.getElement().getStyle().setProperty("marginLeft", "auto");
		agriculturalYears.setStyleName("pos5");
		InlineLabel label1 = new InlineLabel(constants.agriculture_1());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.agriculturalexample();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(agriculturalYears);
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		agriculturalYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(agriculturalYears) > 0)	
						showSimul(agriculturalSimulYears);
			
				}
		});
		return cogoLine;
		
	}
	
	private HorizontalPanel addCraft() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		craftSimulYears = new TextBox();
		craftYears = new TextBox();
		craftYears.setMaxLength(3);
		craftYears.setWidth("2em");
		//craftYears.getElement().getStyle().setProperty("marginLeft", "auto");
		craftYears.setStyleName("pos5");
		InlineLabel label1 = new InlineLabel(constants.craft_1());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.craftexample();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(craftYears);
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		craftYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(craftYears) > 0)	
					showSimul(craftSimulYears);
			
				}
		});
		return cogoLine;
		
	}
	
	private HorizontalPanel addPlant() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		plantSimulYears = new TextBox();
		plantYears = new TextBox();
		plantYears.setMaxLength(3);
		plantYears.setWidth("2em");
		//plantYears.getElement().getStyle().setProperty("marginLeft", "auto");
		plantYears.setStyleName("pos5");
		InlineLabel label1 = new InlineLabel(constants.plant_1());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.plantexample();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(plantYears);
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		plantYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(plantYears) > 0)	
					showSimul(plantSimulYears);
			
				}
		});
		return cogoLine;
		
	}
	
	private HorizontalPanel addElementary() {
		HorizontalPanel cogoLine  = new HorizontalPanel();
		elementarySimulYears = new TextBox();
		elementaryYears = new TextBox();
		elementaryYears.setMaxLength(3);
		elementaryYears.setWidth("2em");
		//elementaryYears.getElement().getStyle().setProperty("marginLeft", "auto");
		elementaryYears.setStyleName("pos5");
		InlineLabel label1 = new InlineLabel(constants.elementary_1());
		label1.getElement().getStyle().setProperty("marginLeft", "20px");
		label1.getElement().getStyle().setProperty("fontWeight", "bold");
		String title = constants.elementaryexample();
		cogoLine.add(label1);
		String leftMargin = "25px";
		cogoLine.add(getInfoLogo(title, leftMargin));
		cogoLine.add(elementaryYears);
		InlineLabel label2 = new InlineLabel(constants.years());
		label2.getElement().getStyle().setProperty("fontWeight", "bold");
		//label2.getElement().getStyle().setProperty("marginLeft", "5px");
		label2.setStyleName("pos6");
		cogoLine.add(label2);
		elementaryYears.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (getValueAsInt(elementaryYears) > 0)	
					showSimul(elementarySimulYears);
			
				}
		});
		return cogoLine;
		
	}
	
	
	private Image getInfoLogo(String title, String leftMargin) {
		Image logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME);
		logo.getElement().getStyle().setProperty("height", "25px");
		logo.getElement().getStyle().setProperty("marginLeft", leftMargin);
		logo.getElement().getStyle().setProperty("marginRight", "25px");
		logo.getElement().getStyle().setProperty("width", "25px");
		logo.setTitle(title);
		return logo;
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
		popup.setWidth("550px");
		popup.show();

	}
	
	private void showSimul(TextBox simulYears ) {
		
		
		final PopupPanel popup = new PopupPanel(true, true);			
		InlineLabel quest1 = new InlineLabel(constants.simul_job());
		
		FlowPanel vertPanel = new FlowPanel();
		quest1.getElement().getStyle().setProperty("color", "red");
		quest1.getElement().getStyle().setProperty("fontWeight", "bold");
		quest1.getElement().getStyle().setProperty("marginLeft", "25px");
		RadioButton Yes = new RadioButton("simul", constants.yes());
		Yes.getElement().getStyle().setProperty("marginLeft", "10px");
		RadioButton No = new RadioButton("simul", constants.no());
		No.getElement().getStyle().setProperty("marginLeft", "10px");
		vertPanel.add(quest1);
		vertPanel.add(Yes);
		vertPanel.add(No);
		popup.setWidget(vertPanel);
		//popup.setGlassEnabled(true);
		popup.setPopupPosition(400,700);
		
		popup.setWidth("550x");
		popup.show();
			
		MyHandler handler = new MyHandler();
		
		//simulYears = new TextBox();
		handler.setTextBox(simulYears);
		handler.setPop(popup);
		// Listen for mouse events on the yes button.
		Yes.addClickHandler(handler);
	
		// on the no button
		No.addClickHandler((new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				popup.clear();
			}
		
		}));
			
				
}
	private int getValueAsInt(TextBox box) {		
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


	private double getValueAsDouble(TextBox box) {		
		// get the text entered
		double input = 0;
		try {
			input = Double.parseDouble(box.getText());	
		}

		catch (Exception e)	{					
						
			return 0;

		}
		return input;
	}
	private void getCognitiveOneData() {				
		User user = login.getUser();
		if (user== null) {
	
			InlineLabel error  = new InlineLabel(constants.register());
			showErrorPopupPanel(error, "red");
			return;
	
		}
		callServiceSetup();
	
		AsyncCallback<CognitiveOneInfo> callback =  new AsyncCallback<CognitiveOneInfo>(){
	
			@Override	 
			public void onSuccess(CognitiveOneInfo cognitiveOne) {
				if ((cognitiveOne == null || cognitiveOne.getUserId() == null)){	            		
					InlineLabel error = new InlineLabel("Cognitive One Data not retrieved. No data available for this patient ");
					showErrorPopupPanel(error, "red");            			
				}            		
				else {
					InlineLabel error = new InlineLabel(constants.retrieved());
					showErrorPopupPanel(error, "green");  
					populatePanel(cognitiveOne);
	
				}
	
			}
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel("Cognitive One data Database error");
				showErrorPopupPanel(error, "red");			
	
			}
		};
	
		InminddServiceSvc.queryCognitiveOne(user, callback);
		return;
	}
	
	private void populatePanel(CognitiveOneInfo cognitiveOne) {
		
		managerYears.setText(Integer.toString(cognitiveOne.getManager()));
		managerSimulYears.setText(Integer.toString(cognitiveOne.getManagerSimulYears()));
		agriculturalSimulYears.setText(Integer.toString(cognitiveOne.getAgricultureSimulYears()));
		agriculturalYears.setText(Integer.toString(cognitiveOne.getAgriculture()));
		
		clericalSimulYears.setText(Integer.toString(cognitiveOne.getClericalSimulYears()));
		clericalYears.setText(Integer.toString(cognitiveOne.getClerical()));
		craftSimulYears.setText(Integer.toString(cognitiveOne.getClericalSimulYears()));
		craftYears.setText(Integer.toString(cognitiveOne.getCraft()));
		elementarySimulYears.setText(Integer.toString(cognitiveOne.getElementarySimulYears()));
		elementaryYears.setText(Integer.toString(cognitiveOne.getElementary()));	
		plantSimulYears.setText(Integer.toString(cognitiveOne.getPlantSimulYears()));
		plantYears.setText(Integer.toString(cognitiveOne.getPlant()));
		professionalSimulYears.setText(Integer.toString(cognitiveOne.getProfessionalSimulYears()));
		professionalYears.setText(Integer.toString(cognitiveOne.getProfessional()));
		serviceSimulYears.setText(Integer.toString(cognitiveOne.getServiceSimulYears()));
		serviceYears.setText(Integer.toString(cognitiveOne.getService()));
		technicianSimulYears.setText(Integer.toString(cognitiveOne.getTechnicianSimulYears()));
		technicianYears.setText(Integer.toString(cognitiveOne.getTechnician()));		
		yearsFormalEducation.setText(Double.toString(cognitiveOne.getFormalEducationYears()));
		yearsNonformalEducation.setText(Double.toString(cognitiveOne.getNonFormalEducationYears()));
	
		
	}

}
class MyHandler implements ClickHandler
	{ 
	    TextBox simulYears;
	    int value;
	    void setTextBox(TextBox box)
	    {
	        simulYears =box;
	        simulYears.setMaxLength(3);
	        simulYears.setWidth("2em");
	      
	    }	    
	    PopupPanel pop;
	    
	    static  InminddConstants constants = 
				   (InminddConstants)GWT.create(InminddConstants.class);
	    void setPop(PopupPanel pop)   {
	        this.pop = pop;
	    }

	  
	    @Override
		public void onClick(ClickEvent event) 
	    {
	    	PopupPanel popup = new PopupPanel(true, true);			
			InlineLabel quest2 = new InlineLabel(constants.simul_years());
			
			FlowPanel vertPanel = new FlowPanel();
			quest2.getElement().getStyle().setProperty("color", "red");
			quest2.getElement().getStyle().setProperty("fontWeight", "bold");
			quest2.getElement().getStyle().setProperty("marginLeft", "25px");
			
			vertPanel.add(quest2);
			vertPanel.add(simulYears);
			
			popup.setWidget(vertPanel);
			
			popup.setPopupPosition(400,700);
			
			popup.setWidth("555px");
			popup.show();
			MyChangeHandler handler = new MyChangeHandler();
			handler.setPop(popup);
			handler.setOuterPop(pop);
			simulYears.addChangeHandler(handler);   
	   
	    }	

	}


	
class MyChangeHandler implements ChangeHandler

{
	
	PopupPanel pop;
	PopupPanel outerPop;
    void setPop(PopupPanel pop)   {
        this.pop = pop;
    }

    void setOuterPop(PopupPanel outpop) {
    	this.outerPop = outpop;
    }
    
    @Override
	public void onChange(ChangeEvent event)   {    	
    pop.clear();  
    outerPop.clear();
    }
    
}
	


