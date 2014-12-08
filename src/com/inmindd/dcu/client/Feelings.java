package com.inmindd.dcu.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.FeelingsInfo;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.User;

public class Feelings {
		private TabLayoutPanel content;
		private DockLayoutPanel cesdPanel;	
		private FlowPanel answers = new FlowPanel();
		private FlowPanel questions = new FlowPanel();
		private ScrollPanel scroll = new ScrollPanel();
		private FlowPanel pnl = new FlowPanel();
		private ArrayList<RadioButton> radioButtonsArray = new ArrayList<RadioButton>();
		private ArrayList<HorizontalPanel> panelsArray = new ArrayList<HorizontalPanel>();
		private RadioButton depressedYes;
		private RadioButton depressedNo;
		private RadioButton treatYes;
		private RadioButton treatNo;
		private DockPanel dock1;
		private DockPanel dock2;
		private InlineLabel error;
		private Login login;
		private static User user;
		private InminddServiceAsync InminddServiceSvc;

		
		private static final int DECK_CESD = 2;	
		private static final int DECK_MEDICAL = 3;	
		
		private static InminddConstants constants = 
				   (InminddConstants)GWT.create(InminddConstants.class);
		
		
		public static Feelings lastinstance;
		// save reference for clearing fields if user logs out.
		
		public Feelings() {			
			lastinstance = this;
		}
		
		public void setContent(TabLayoutPanel content) {
			this.content = content;
		}
		public static void clearInputs() {
			lastinstance.treatNo.setValue(false);
			lastinstance.treatYes.setValue(false);
			lastinstance.depressedYes.setValue(false);
			lastinstance.depressedNo.setValue(false);
			Iterator<RadioButton> btn = lastinstance.radioButtonsArray.iterator();
			while (btn.hasNext()) {
				btn.next().setValue(false);
			}
		}
		

		public DockLayoutPanel setupCesdPanel (Login login) {
			this.login= login;	
			cesdPanel = new DockLayoutPanel(Unit.EM);
			cesdPanel.setHeight("90%");
			cesdPanel.setWidth("98%");
			questions.getElement().getStyle().setProperty("width", "48%");
			
			answers.getElement().getStyle().setProperty("width", "40%");
			
			scroll.setSize("40px", "40px");
			HTMLPanel mainHeader = new HTMLPanel("<h1>" +
					constants.feeling() + "</h1>");
			HTMLPanel header = new HTMLPanel("<h3>" +
					constants.lastweek() + "<br>" +
					constants.statement() + " </h3>");
				
			HTMLPanel week = new HTMLPanel("<span style='margin-left:65%;'>" +
					"<b><u>" + constants.pastweek() + "</u></b></span>");
			/*HTMLPanel csdPanel = new HTMLPanel( "<pre style='margin:0px'  >" +
					"                         " + constants.some() + " <br>" +            
					constants.rarely() +  "        " +        constants.little() + constants.occasionally() + constants.most() + "<br>" +
					constants.time()   +    constants.time_1() +  constants.moderate() + constants.fivedays() + "<br>" +
					constants.oneday() + constants.days() + constants.threedays()  + constants.day() + " <br> </pre><hr>"); */
		
			HTMLPanel line = new HTMLPanel("<hr>");
			
			VerticalPanel times = new VerticalPanel();
			InlineLabel so = new InlineLabel(constants.some_new());
			
			so.getElement().getStyle().setProperty("marginLeft", "870px");
			so.getElement().getStyle().setProperty("fontWeight", "bold");
			
			times.add(so);
			
			HorizontalPanel lineOne = new HorizontalPanel();
			InlineLabel rare = new InlineLabel(constants.rarely_new());
			
			rare.getElement().getStyle().setProperty("marginLeft", "680px");
			rare.getElement().getStyle().setProperty("fontWeight", "bold");
			
			lineOne.add(rare);
			InlineLabel little = new InlineLabel(constants.little_new());
			
			little.getElement().getStyle().setProperty("marginLeft", "75px");
			little.getElement().getStyle().setProperty("fontWeight", "bold");
			
			
			lineOne.add(little);
			InlineLabel occasion = new InlineLabel(constants.occasionally_new());
			
			occasion.getElement().getStyle().setProperty("marginLeft", "100px");
			occasion.getElement().getStyle().setProperty("fontWeight", "bold");
			lineOne.add(occasion);
			InlineLabel most = new InlineLabel(constants.most_new());
			
			most.getElement().getStyle().setProperty("marginLeft", "65px");
			most.getElement().getStyle().setProperty("fontWeight", "bold");			
			
			lineOne.add(most);			
			times.add(lineOne);
			
			HorizontalPanel lineTwo = new HorizontalPanel();
			InlineLabel time = new InlineLabel(constants.time());
			
			time.getElement().getStyle().setProperty("marginLeft", "680px");
			time.getElement().getStyle().setProperty("fontWeight", "bold");
			
			lineTwo.add(time);
			InlineLabel time_1 = new InlineLabel(constants.time_1());
			
			time_1.getElement().getStyle().setProperty("marginLeft", "65px");
			time_1.getElement().getStyle().setProperty("fontWeight", "bold");
			
			
			lineTwo.add(time_1);
			InlineLabel moderate = new InlineLabel(constants.moderate());
			
			moderate.getElement().getStyle().setProperty("marginLeft", "60px");
			moderate.getElement().getStyle().setProperty("fontWeight", "bold");
			lineTwo.add(moderate);
			
			InlineLabel five = new InlineLabel(constants.fivedays());
			
			five.getElement().getStyle().setProperty("marginLeft", "32px");
			five.getElement().getStyle().setProperty("fontWeight", "bold");
			
			
			lineTwo.add(five);
			times.add(lineTwo);
			
			HorizontalPanel lineThree = new HorizontalPanel();
			InlineLabel oneday = new InlineLabel(constants.oneday());
			
			oneday.getElement().getStyle().setProperty("marginLeft", "680px");
			oneday.getElement().getStyle().setProperty("fontWeight", "bold");
			
			lineThree.add(oneday);
			InlineLabel day = new InlineLabel(constants.days());
			
			day.getElement().getStyle().setProperty("marginLeft", "160px");
			day.getElement().getStyle().setProperty("fontWeight", "bold");
			
			
			lineThree.add(day);
			 
			InlineLabel threedays  = new InlineLabel(constants.threedays());
			
			threedays.getElement().getStyle().setProperty("marginLeft", "140px");
			threedays.getElement().getStyle().setProperty("fontWeight", "bold");
			lineThree.add(threedays);
			
			InlineLabel days = new InlineLabel(constants.days());
			
			days.getElement().getStyle().setProperty("marginLeft", "90px");
			days.getElement().getStyle().setProperty("fontWeight", "bold");
			
			
			lineThree.add(days);
			times.add(lineThree);
			
			//csdPanel.getElement().getStyle().setProperty("marginLeft", "48%");
			//csdPanel.getElement().getStyle().setProperty("fontWeight", "bold");
			//csdPanel.getElement().getStyle().setProperty("fontType", "italic");
			cesdPanel.addNorth(mainHeader, 5);
			cesdPanel.addNorth(header,6);
			
			cesdPanel.addNorth(week,3);
			cesdPanel.addNorth(times,7); 
			
		   // cesdPanel.addNorth(csdPanel,6);
		   
		  //  cesdPanel.addNorth(prev,3);
		    cesdPanel.addNorth(line,1);
		
		    Button prev = new Button(constants.review());
			

			// Listen for mouse events on the previous data button.
			prev.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					getFeelingsData();
				}
			});
			
			
		    pnl.add(prev);  // get previous data button 
		    pnl.add(new HTMLPanel("<span>  <br>  </span>"));
		    setQuestionsAndAnswers(constants.ces1(), 1);
		    setQuestionsAndAnswers(constants.ces2(),2);
		    setQuestionsAndAnswers(constants.ces3(),3);

		    setQuestionsAndAnswers(constants.ces4(), 4);
		    setQuestionsAndAnswers(constants.ces5(),5 );
		    setQuestionsAndAnswers(constants.ces6(), 6);
		    setQuestionsAndAnswers(constants.ces7(), 7);
		    setQuestionsAndAnswers(constants.ces8(), 8);
		    setQuestionsAndAnswers(constants.ces9(), 9);
		    setQuestionsAndAnswers(constants.ces10(), 10);
		    setQuestionsAndAnswers(constants.ces11(), 11);
		    setQuestionsAndAnswers(constants.ces12(), 12);
		    setQuestionsAndAnswers(constants.ces13(), 13);
		    setQuestionsAndAnswers(constants.ces14(), 14);
		    setQuestionsAndAnswers(constants.ces15(), 15);
		    setQuestionsAndAnswers(constants.ces16(), 16);
		    setQuestionsAndAnswers(constants.ces17(), 17);
		    setQuestionsAndAnswers(constants.ces18(), 18);
		    setQuestionsAndAnswers(constants.ces19(), 19);
		    setQuestionsAndAnswers(constants.ces20(), 20);
		    pnl.add(getDepressed());		
		    pnl.add(new HTMLPanel("<span>  <br>  </span>"));
		    pnl.add(getDepressionTreatment());
		    pnl.add(new HTMLPanel("<span>  <br>  </span>"));

		    Button btn = new Button(constants.submit());
		    pnl.add(btn);
		    
		    // Listen for mouse events on the submit button.
		    btn.addClickHandler(new ClickHandler() {
		    	public void onClick(ClickEvent event) {
		    		
		    	
		    		for ( int i = 1; i <= 20; i++) {
		    			if (!checkButtons(i)) {
		    				return;
		    			}
		    		}
		    		
		    		if (!checkDepressed()) {
		    			return;
		    		}
		    		
		    		if (!checkTreatment()) {
		    			return;
		    		}  
		    		
		    		createFeelings();
		    		updateFeelingsDB();  // write to server here
		    		content.selectTab(DECK_MEDICAL);
		    		return; 
		    	}
		     
		    });
		    
		    scroll.setSize("100%", "65%");
		    scroll.add(pnl);
		    scroll.setAlwaysShowScrollBars(true);
		    scroll.scrollToTop();
		
		    cesdPanel.add(scroll);




		    return cesdPanel;
		}
	private void setQuestionsAndAnswers(String question, int answerButton) {
			
			HorizontalPanel lineQuestionAnswer  = new HorizontalPanel();
		    SimplePanel sp = new SimplePanel();
		    sp.getElement().getStyle().setProperty("minWidth", "730px");  
		    sp.getElement().getStyle().setProperty("maxWidth", "730px"); 
		  
		    InlineLabel lbl = new InlineLabel(question);
		    lbl.getElement().getStyle().setProperty("fontWeight", "bold");
			if ((answerButton % 2 ) == 1) {
		    lbl.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");  
			}		   
		    HorizontalPanel hp = new HorizontalPanel();
			
			addToAnswers(hp, answerButton);
			if ((answerButton % 2 ) == 1) {
				hp.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");
			}
			sp.add(lbl);
		    lineQuestionAnswer.add(sp);
		    lineQuestionAnswer.add(hp);
		    pnl.add(lineQuestionAnswer);
		    pnl.add(new HTMLPanel("<span>  <br>  </span>"));
		    // keep track of the questions for validation at submit
		    panelsArray.add(lineQuestionAnswer);
		
	}
  
	private void addToAnswers(HorizontalPanel horPanel, int answerButton) {

		String radiogroup = "radiogroup" + answerButton;
		RadioButton rare = new RadioButton(radiogroup, "");
		RadioButton some = new RadioButton(radiogroup, "");
		RadioButton occasional = new RadioButton(radiogroup, "");
		RadioButton most = new RadioButton(radiogroup, "");
		// save in array for validation and persistence 
		radioButtonsArray.add(rare);
		radioButtonsArray.add(some);
		radioButtonsArray.add(occasional);
		radioButtonsArray.add(most);

		some.getElement().getStyle().setProperty("marginLeft", "138px");
		occasional.getElement().getStyle().setProperty("marginLeft", "160px");
		most.getElement().getStyle().setProperty("marginLeft", "125px");
		horPanel.setCellHeight(rare, "10px");
		horPanel.add(rare);
		horPanel.add(some);
		horPanel.add(occasional);
		horPanel.add(most);

		answers.add(horPanel);

	}

	private  DockPanel getDepressed() {
		dock1 = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		InlineLabel lbl = new InlineLabel(constants.ces21());
		lbl.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("780px");
		vPanel.add(lbl);
		depressedYes = new RadioButton("depress", constants.yes());
		depressedYes.getElement().getStyle().setProperty("marginLeft", "90px");
		depressedYes.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");
		depressedNo = new RadioButton("depress", constants.no());
		depressedNo.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");
		depressedNo.getElement().getStyle().setProperty("marginLeft", "20px");
		dock1.add(vPanel, DockPanel.WEST);

		dock1.add(depressedNo, DockPanel.EAST);
		dock1.add(depressedYes, DockPanel.EAST);
		return dock1;	  

	}

	private  DockPanel getDepressionTreatment() {

		dock2 = new DockPanel();
		VerticalPanel vPanel = new VerticalPanel();
		InlineLabel lbl = new InlineLabel(constants.ces22());

		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("780px");
		vPanel.add(lbl);
		treatYes = new RadioButton("depressTreatment", constants.yes());
		treatYes.getElement().getStyle().setProperty("marginLeft", "90px");
		treatNo = new RadioButton("depressTreatment", constants.no());
		treatNo.getElement().getStyle().setProperty("marginLeft", "20px");
		dock2.add(vPanel, DockPanel.WEST);

		dock2.add(treatNo, DockPanel.EAST);
		dock2.add(treatYes, DockPanel.EAST);
		return dock2;	  

	} 

	private boolean checkButtons(int index) {
			User user = login.getUser()
	;
		if (user.getUserId()== null) {
			
			InlineLabel error  = new InlineLabel(constants.register());
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		HorizontalPanel hr = null; 

		InlineLabel error;


		hr = panelsArray.get(index-1);
		hr.getElement().getStyle().setProperty("color", "black");	

		RadioButton most = radioButtonsArray.get((index * 4) - 1);
		RadioButton occasional = radioButtonsArray.get((index * 4) - 2);
		RadioButton some = radioButtonsArray.get((index * 4) - 3);
		RadioButton rare = radioButtonsArray.get((index * 4) - 4);

		if ((most.getValue() == false) && (some.getValue() == false) && (occasional.getValue() == false) 
				&& (rare.getValue() == false)) {
			String question = constants.feelings_error_1() + index;
			error = new InlineLabel(question);
			showErrorPopupPanel(error, "red");
			hr.getElement().getStyle().setProperty("backgroundColor", "#ffffff");  
			hr.getElement().getStyle().setProperty("color", "red");
			return false;
		}

		return true;
	}

	private boolean checkDepressed() {

		// either one checked
		dock1.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel(constants.feelings_error_2());	
		if ( !(depressedYes.getValue() || depressedNo.getValue())) {
			showErrorPopupPanel(error, "red");
			dock1.getElement().getStyle().setProperty("color", "red");			  
			return false;
		}
		return true;

	}

	private boolean checkTreatment() {

		// either one checked
		dock2.getElement().getStyle().setProperty("color", "black");	
		error = new InlineLabel(constants.feelings_error_2());	
		if ( !(treatYes.getValue() || treatNo.getValue())) {
			showErrorPopupPanel(error, "red");
			dock2.getElement().getStyle().setProperty("color", "red");			  
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
		
		popup.setPopupPosition(150,330);
		popup.setWidth("700px");
		popup.show();
	}
	
	 private boolean callServiceSetup() {
			// set up rpc call
	
			InminddServiceSvc = (InminddServiceAsync) GWT.create(InminddService.class);
			ServiceDefTarget target = (ServiceDefTarget) InminddServiceSvc;
			String moduleRelativeURL = GWT.getModuleBaseURL() + "Inmindd";
			target.setServiceEntryPoint(moduleRelativeURL);	    			
			return true;

	 }
	 
	 private void updateFeelingsDB() {
		 FeelingsInfo feelings = createFeelings();
		 callServiceSetup();
		 
		 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){
			 @Override	 
            public void onSuccess(Boolean result) {
            		if ((result == false)){	            		
            			InlineLabel error = new InlineLabel(constants.feel_update_fail());
            			showErrorPopupPanel(error, "red");            			
            		}            		
            		else {
            			//InlineLabel error = new InlineLabel(constants.mood_complete());
            			//showErrorPopupPanel(error, "green");  
            			content.getTabWidget(DECK_CESD).getElement().getStyle().setProperty("backgroundColor", "red");
            		}
                 
              }
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel(constants.feel_update_fail());
    			showErrorPopupPanel(error, "red");			
				
			}
		  };
		  
    	  InminddServiceSvc.updateFeelingsInfo(feelings, callback);  // call server side update
	 }
    	 
	
	
	private FeelingsInfo createFeelings() {
		int index = 1;
		FeelingsInfo feelings = new FeelingsInfo();
		User user = login.getUser();
		feelings.setUserId(user.getUserId());
		if (depressedYes.getValue()) 
			feelings.setDepression("yes");
		else 
			feelings.setDepression("no");
	
		if (treatYes.getValue()) 
			feelings.setTreated("yes");
		else 
			feelings.setTreated("no");
		
		feelings.setCes1(getButtonChecked(index++));
		feelings.setCes2(getButtonChecked(index++));
		feelings.setCes3(getButtonChecked(index++));
		feelings.setCes4(getButtonChecked(index++));
		feelings.setCes5(getButtonChecked(index++));
		feelings.setCes6(getButtonChecked(index++));
		feelings.setCes7(getButtonChecked(index++));
		feelings.setCes8(getButtonChecked(index++));
		feelings.setCes9(getButtonChecked(index++));
		feelings.setCes10(getButtonChecked(index++));
		feelings.setCes11(getButtonChecked(index++));
		feelings.setCes12(getButtonChecked(index++));
		feelings.setCes13(getButtonChecked(index++));
		feelings.setCes14(getButtonChecked(index++));
		feelings.setCes15(getButtonChecked(index++));
		feelings.setCes16(getButtonChecked(index++));
		feelings.setCes17(getButtonChecked(index++));
		feelings.setCes18(getButtonChecked(index++));
		feelings.setCes19(getButtonChecked(index++));
		feelings.setCes20(getButtonChecked(index++));
		
		
		
		return feelings;
	}
	
	
	private String getButtonChecked(int index) {
		RadioButton most = radioButtonsArray.get((index * 4) - 1);
		RadioButton occasional = radioButtonsArray.get((index * 4) - 2);
		RadioButton some = radioButtonsArray.get((index * 4) - 3);
		RadioButton rare = radioButtonsArray.get((index * 4) - 4);
		if (most.getValue())
			return "most";
		if (occasional.getValue())
			return "occasional";
		if (some.getValue())
			return "some";
		if (rare.getValue())
			return "rarely";
		else
			return "none";
		
	}
	

	 private void getFeelingsData() {				
		 User user = login.getUser();
		 if (user.getUserId() == null) {

			 InlineLabel error  = new InlineLabel(constants.register());
			 showErrorPopupPanel(error, "red");
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<FeelingsInfo> callback =  new AsyncCallback<FeelingsInfo>(){

			 @Override	 
			 public void onSuccess(FeelingsInfo feelings) {
				 if ((feelings == null)){	            		
					 InlineLabel error = new InlineLabel(constants.invalid_id_pass());
					 showErrorPopupPanel(error, "red");            			
				 }            		
				 else {
					 InlineLabel error = new InlineLabel(constants.retrieved());
					 showErrorPopupPanel(error, "green");  
					 populatePanel(feelings);
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 InlineLabel error = new InlineLabel(constants.invalid_id_pass());
				 showErrorPopupPanel(error, "red");			

			 }
		 };

		 InminddServiceSvc.queryFeelingsInfo(user, callback);
		 return;
	 }

	 private void populatePanel(FeelingsInfo feelings) {
		 int index = 1;
		 setButtonChecked(feelings.getCes1(), index++);
		 setButtonChecked(feelings.getCes2(), index++);
		 setButtonChecked(feelings.getCes3(), index++);
		 setButtonChecked(feelings.getCes4(), index++);
		 setButtonChecked(feelings.getCes5(), index++);
		 setButtonChecked(feelings.getCes6(), index++);
		 setButtonChecked(feelings.getCes7(), index++);
		 setButtonChecked(feelings.getCes8(), index++);
		 setButtonChecked(feelings.getCes9(), index++);
		 setButtonChecked(feelings.getCes10(), index++);
		 setButtonChecked(feelings.getCes11(), index++);
		 setButtonChecked(feelings.getCes12(), index++);
		 setButtonChecked(feelings.getCes13(), index++);
		 setButtonChecked(feelings.getCes14(), index++);
		 setButtonChecked(feelings.getCes15(), index++);
		 setButtonChecked(feelings.getCes16(), index++);
		 setButtonChecked(feelings.getCes17(), index++);
		 setButtonChecked(feelings.getCes18(), index++);
		 setButtonChecked(feelings.getCes19(), index++);
		 setButtonChecked(feelings.getCes20(), index++);	 
		 if (feelings.getDepression().equals("yes"))
			 depressedYes.setValue(true);
		 else 
			 depressedNo.setValue(true);
		 if (feelings.getTreated().equals("yes"))
			 treatYes.setValue(true);
		 else 
			 treatNo.setValue(true);
		 return;
	 }

	 private void setButtonChecked (String ces, int index) {
		 if (ces.equals("most")) {
			 radioButtonsArray.get((index * 4) - 1).setValue(true);

		 }if (ces.equals("occasional")) {
			 radioButtonsArray.get((index * 4) - 2).setValue(true);
		 }

		 if (ces.equals("some")) {
			 radioButtonsArray.get((index * 4) - 3).setValue(true);
		 }
		 if (ces.equals("rarely")) {
			 radioButtonsArray.get((index * 4) - 4).setValue(true);
		 }
		 return;

	 }
}
