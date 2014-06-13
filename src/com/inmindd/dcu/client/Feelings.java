package com.inmindd.dcu.client;

import java.util.ArrayList;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inmindd.dcu.shared.FeelingsInfo;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.User;

public class Feelings {
	
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
		
		public Feelings () {		
			
		}
	


		public DockLayoutPanel setupCesdPanel (Login login) {
			this.login= login;
	
			cesdPanel = new DockLayoutPanel(Unit.EM);
			cesdPanel.setHeight("90%");
			
			questions.getElement().getStyle().setProperty("width", "48%");
			
			answers.getElement().getStyle().setProperty("width", "40%");
		
			scroll.setSize("700", "500");
			HTMLPanel mainHeader = new HTMLPanel("<h1>" +
					"About Your mood</h1>");
			HTMLPanel header = new HTMLPanel("<h3>" +
					"We would next like you to tell us  about your mood in the last week." + "<br>" +
					"Below are a set of statements."  +
					 " For each statement, please indicate how often you have felt this way. </h3>");
				
			HTMLPanel week = new HTMLPanel("<p style='margin-left:65%;'>" +
					"<b><u>During the Past Week</u></b></p>");
			HTMLPanel csdPanel = new HTMLPanel( "<pre style='margin:0px' >" +
					"                          Some or a <br>" +            
					"Rarely or none of        little of the        Occasionally or a            Most or all of <br>" +
					"the time (less than      time (1-2          moderate amount of time        the time (5-7<br>" +
					"1 day)                     days)               (3-4 days)                    days) <br> </pre><hr>");
		
			HTMLPanel line = new HTMLPanel("<hr>");
		
			
			csdPanel.getElement().getStyle().setProperty("marginLeft", "48%");
			csdPanel.getElement().getStyle().setProperty("fontWeight", "bold");
			cesdPanel.addNorth(mainHeader, 5);
			cesdPanel.addNorth(header,6);
			
			cesdPanel.addNorth(week,3);
		    cesdPanel.addNorth(csdPanel,6);
		  //  cesdPanel.addNorth(prev,3);
		    cesdPanel.addNorth(line,1);
		
		    Button prev = new Button("Retrieve previous data ?");
			

			// Listen for mouse events on the previous data button.
			prev.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					getFeelingsData();
				}
			});
			
			
		    pnl.add(prev);  // get previous data button 
		    pnl.add(new HTMLPanel("<span>  <br>  </span>"));
		    setQuestionsAndAnswers("1. I was bothered by things that usually don't bother me.", 1);
		    setQuestionsAndAnswers("2. I did not feel like eating, my appetite was poor.",2);
		    setQuestionsAndAnswers("3. I felt that I could not shake off the blues, even with help from my  family and friends.",3);

		    setQuestionsAndAnswers("4. I felt I was just as good as other people.", 4);
		    setQuestionsAndAnswers("5.I had trouble keeping my mind on what I was doing.",5 );
		    setQuestionsAndAnswers("6. I felt depressed.", 6);
		    setQuestionsAndAnswers("7. I felt that everything I did was an effort.", 7);
		    setQuestionsAndAnswers("8. I felt hopeful about the future.", 8);
		    setQuestionsAndAnswers("9. I thought my life had been a failure.", 9);
		    setQuestionsAndAnswers("10 I felt fearful.", 10);
		    setQuestionsAndAnswers("11. My sleep was restless.", 11);
		    setQuestionsAndAnswers("12. I was happy.", 12);
		    setQuestionsAndAnswers("13. I talked less than usual.", 13);
		    setQuestionsAndAnswers("14. I felt lonely.", 14);
		    setQuestionsAndAnswers("15. People were unfriendly.", 15);
		    setQuestionsAndAnswers("16. I enjoyed life.", 16);
		    setQuestionsAndAnswers("17. I had crying spells.", 17);
		    setQuestionsAndAnswers("18. I felt sad.", 18);
		    setQuestionsAndAnswers("19. I felt that people disliked me.", 19);
		    setQuestionsAndAnswers("20. I could not get \"going\".", 20);
		    pnl.add(getDepressed());		
		    pnl.add(new HTMLPanel("<span>  <br>  </span>"));
		    pnl.add(getDepressionTreatment());
		    pnl.add(new HTMLPanel("<span>  <br>  </span>"));

		    Button btn = new Button("submit");
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
		    		return; 
		    	}
		     
		    });
		    
		    scroll.setSize("100%", "70%");
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

		some.getElement().getStyle().setProperty("marginLeft", "148px");
		occasional.getElement().getStyle().setProperty("marginLeft", "185px");
		most.getElement().getStyle().setProperty("marginLeft", "190px");
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
		InlineLabel lbl = new InlineLabel("Have you ever been diagnosed by a doctor or other health professional with depressed mood or depression ?");
		lbl.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");
		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("780px");
		vPanel.add(lbl);
		depressedYes = new RadioButton("depress", "Yes");
		depressedYes.getElement().getStyle().setProperty("marginLeft", "90px");
		depressedYes.getElement().getStyle().setProperty("backgroundColor", "#c0c0c0");
		depressedNo = new RadioButton("depress", "No");
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
		InlineLabel lbl = new InlineLabel("Have you ever been treated for a depressed mood or depression e.g. with medication or psychotherapy ?");

		lbl.getElement().getStyle().setProperty("fontWeight", "bold");
		vPanel.setWidth("780px");
		vPanel.add(lbl);
		treatYes = new RadioButton("depressTreatment", "Yes");
		treatYes.getElement().getStyle().setProperty("marginLeft", "90px");
		treatNo = new RadioButton("depressTreatment", "No");
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
			
			InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
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
			String question = "Please select a button for Question Number " + index;
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
		error = new InlineLabel("Please select one of the Depressed  buttons");	
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
		error = new InlineLabel("Please select one of the Treatment  buttons");	
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
		
		popup.setPopupPosition(190,730);
		popup.setWidth("550px");
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
            			InlineLabel error = new InlineLabel("Error Feelings Info not updated");
            			showErrorPopupPanel(error, "red");            			
            		}            		
            		else {
            			InlineLabel error = new InlineLabel("Feelings Info updated  -  Proceed to next Panel");
            			showErrorPopupPanel(error, "green");            			            			
            		}
                 
              }
			@Override
			public void onFailure(Throwable caught) {
				InlineLabel error = new InlineLabel("Feelings Info update failed");
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

			 InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			 showErrorPopupPanel(error, "red");
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<FeelingsInfo> callback =  new AsyncCallback<FeelingsInfo>(){

			 @Override	 
			 public void onSuccess(FeelingsInfo feelings) {
				 if ((feelings == null)){	            		
					 InlineLabel error = new InlineLabel("Invalid User Id or Password  - please reenter  or check your caps lock ");
					 showErrorPopupPanel(error, "red");            			
				 }            		
				 else {
					 InlineLabel error = new InlineLabel("Feelings data retrieved- Edit as necessary");
					 showErrorPopupPanel(error, "green");  
					 populatePanel(feelings);
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 InlineLabel error = new InlineLabel("Invalid User Id or Password  - please reenter or check your caps lock");
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
