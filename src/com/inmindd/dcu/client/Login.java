package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.googlecode.gwt.crypto.bouncycastle.digests.SHA1Digest;
import com.inmindd.dcu.shared.RiskFactorScore;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.User;


public class Login  {

	private  User user = new User();;
	private  Patient patient = new Patient();
	//private final static byte[] GWT_DES_KEY = new byte[] { -110, 121, -65, 22, -60, 61, -22, -60, 21, -122, 41, -89, -89, -68, -8, 41, -119, -51, -12, -36, 19, -8, -17, 47 };
  
	
	
	/**
	 * rpc call
	 */
	private InminddServiceAsync InminddServiceSvc;
	
	/**Main panel of the login view*/
    private VerticalPanel mainpanel = new VerticalPanel();
	
	
	
	
	private HTML secondHeadline = new HTML("<h1>Please Logon or Register with the Inminnd Application</h1>");
	
	/**Decorator panel for the login form*/
    private DecoratorPanel decPanel = new DecoratorPanel();
    
    
    /**Grid for login form elements*/
    private FlexTable loginLayout = new FlexTable(); 
  
	
    
    private InlineLabel loginHead = new InlineLabel("Login");
    private InlineLabel loginRegister = new InlineLabel("Register");

  
	private String userIdLabel = "User Id: ";
    private String passwordLabel = "Password: ";
    private String passwordRepeat  = "Repeat Password:";
    //private String country  = "Select a country";
   // private String practice  = "Enter practice code";
   // private ListBox countryCode = new ListBox();
    private TextBox userId = new TextBox();
    private int idUser;
    private PasswordTextBox password = new PasswordTextBox();
    
    private TextBox userIdReg = new TextBox();
    private PasswordTextBox passwordReg = new PasswordTextBox();
    private PasswordTextBox passwordRegRepeat = new PasswordTextBox();
    private TextBox practiceBox = new TextBox();
  
    private Button loginbutton = new Button("Login");    
    private Button registerbutton = new Button("Register");    
  
    private String hashedPassword;
    
    
    public Login() {
    	
    }
    
    
    public void setUser(User user) {
    	this.user = user;
    }
    
    public User getUser() {
    	return this.user;
    }
    
    public Patient getPatient() {
    	return this.patient;
    }
	public VerticalPanel setupLoginPanel() {
		
    	
    	practiceBox.setMaxLength(2);
		practiceBox.setWidth("2em");
    	
    	int windowHeight = Window.getClientHeight();
		int windowWidth = Window.getClientWidth();
		
		loginHead.getElement().getStyle().setProperty("textDecoration", "underline");
		loginHead.getElement().getStyle().setProperty("fontWeight", "bold");
		loginRegister.getElement().getStyle().setProperty("textDecoration", "underline");
		loginRegister.getElement().getStyle().setProperty("fontWeight", "bold");
        loginLayout.setCellSpacing(6);
        FlexCellFormatter cellFormatter = loginLayout.getFlexCellFormatter();

        // Add a title to the form
       // loginLayout.setHTML(0, 0, this.headline);
       loginLayout.setWidget(0,0,loginHead);
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        // Add username and password fields
        userIdReg.setMaxLength(7);
		userIdReg.setWidth("6em");
		userId.setMaxLength(7);
		userId.setWidth("6em");
		
		
        password.setWidth("150px");
        loginLayout.setHTML(1, 0, this.userIdLabel);
        loginLayout.setWidget(1, 1, userId);
        loginLayout.setHTML(2, 0, passwordLabel);
        loginLayout.setWidget(2, 1, password);
        
        //Add the loginbutton to the form
        loginLayout.setWidget(3, 0, loginbutton);
        cellFormatter.setColSpan(3, 0, 2);
        cellFormatter.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
        
        
        
        loginLayout.setHTML(4, 0, "");   
        
        loginLayout.setHTML(5, 0, "");
        loginLayout.setWidget(6, 0, this.loginRegister);
        cellFormatter.setColSpan(6, 0, 2);
        cellFormatter.setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
     
        
       
       
        password.setWidth("150px");
        loginLayout.setHTML(7, 0, this.userIdLabel);
        loginLayout.setWidget(7, 1, userIdReg);
        loginLayout.setHTML(8, 0, passwordLabel);
        loginLayout.setWidget(8, 1, passwordReg);
       
        loginLayout.setHTML(9, 0, passwordRepeat);
        loginLayout.setWidget(9, 1, passwordRegRepeat);
    
        loginLayout.setWidget(10, 0, registerbutton);
        
        cellFormatter.setColSpan(10, 0, 2);
        cellFormatter.setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_CENTER);
        loginLayout.setCellSpacing(6);
     

       
        password.setWidth("150px");
      

        // Wrap the content in a DecoratorPanel
       
        decPanel.setWidget(loginLayout);      
       
        mainpanel.add(secondHeadline);
        mainpanel.setWidth(windowWidth/1 + "px");
		mainpanel.setHeight(windowHeight*0.6 + "px");      
        mainpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainpanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
     
	
        mainpanel.add(decPanel);
      
        
    	
		 // Listen for mouse events on the registration  button.
        registerbutton.addClickHandler(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent event) {
        		if (validateUser()) {
        			callServiceSetup();
        			createUser();
        			AsyncCallback<Boolean> callback = new AuthenticationHandlerReg<Boolean>();

        			InminddServiceSvc.registerUser(user, callback);
        		}
        	}
        });
	    
	    // Listen for mouse events on the login button.
	    loginbutton.addClickHandler(new ClickHandler() {
	    	@Override
	    	public void onClick(ClickEvent event) {


	    		callServiceSetup();


	    		//  generate digest of the password
	    		hashedPassword = getSHA1for((password.getText()));
	    		// AsyncCallback<User> callback = new AuthenticationHandler<User>();

	    		AsyncCallback<User> callback = new AsyncCallback<User>() {

	    			AsyncCallback<User> callbackUser = new AsyncCallback<User>() {
	    				@Override
	    				public void onSuccess(User user) {
	    					if (user == null) {
	    						System.out.println("[login::getUserConnected] \\ user null");
	    						Window.alert("please connect without errors");
	    					} else {
	    						//Window.alert("ok "+ user.toString());
	    					}
	    				}

	    				@Override
	    				public void onFailure(Throwable caught) {
	    					System.out.println("[login::getUserConnected] \\ exception null");
	    					// TODO print error
	    				}
	    			};

	    			public void onSuccess(User user) {
	    				if ((user == null)){	            		
	    					InlineLabel error = new InlineLabel("Invalid User Id or Password  - please reenter. Check Caps lock");
	    					showErrorPopupPanel(error, "red");
	    					setUser(user);
	    				}

	    				else {
	    					InlineLabel error = new InlineLabel("You are now logged on to Inmindd. Please proceed to input panels");
	    					showErrorPopupPanel(error, "green");	            			
	    					setUser(user);	
	    					InminddServiceSvc.getUserConnected(callbackUser);
	    					// Clear screens of previous input
	    					if(PatientInfo.lastinstance != null)
	    						PatientInfo.clearInputs();	
	    					if (Feelings.lastinstance != null)
	    						Feelings.clearInputs();
	    					if (MedicalHealth.lastinstance != null)
	    						MedicalHealth.clearInputs();
	    					if (FamilyMedicalHistory.lastinstance != null)
	    						FamilyMedicalHistory.clearInputs();
	    					if (PhysicalActivity.lastinstance != null)
	    						PhysicalActivity.clearInputs();
	    					if (CognitiveOne.lastinstance != null)
	    						CognitiveOne.clearInputs();
	    					if (CognitiveTwo.lastinstance != null)
	    						CognitiveTwo.clearInputs();
	    					if (SmokeAlcohol.lastinstance != null)
	    						SmokeAlcohol.clearInputs();
	    					if (Diet.lastinstance != null)
	    						Diet.clearInputs();

	    					getScore();   

	    				}

	    			}
	    			@Override
	    			public void onFailure(Throwable caught) {
	    				InlineLabel error = new InlineLabel("Invalid User Id or Password  - please reenter. Check your Caps lock");
	    				showErrorPopupPanel(error, "red");
	    				setUser(user);

	    			}
	    		};

	    		InminddServiceSvc.authenticateUser(userId.getText(),hashedPassword, callback);
	    	}

	    });

	    return mainpanel;

	}
	
	private void createUser() {
		
		
		user.setUserId(userIdReg.getText());
		
		user.setPassword(hashedPassword);
		return;
		
	}
	
	private boolean validateUser() {
		if(!validateUserId(userIdReg.getText())) {
			InlineLabel error = new InlineLabel("Please enter a valid , numeric, User Id");
			showErrorPopupPanel(error, "red");
			return false;
		} 
		
		if (userIdReg.getText().equals("")) {
			InlineLabel error = new InlineLabel("Please enter User Id");
			showErrorPopupPanel(error, "red");
			return false;
		}
	
		String rep = passwordRegRepeat.getText();
		String psw = passwordReg.getText();
		if (rep.equals("") && psw.equals("")) {			
			InlineLabel error = new InlineLabel("Please enter a valid password");
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		if (!(passwordReg.getText().equals(rep))) {
			InlineLabel error = new InlineLabel("Passwords don't match, please re-enter");
			showErrorPopupPanel(error, "red");
			return false;
		}
		
		
	/*	if (countryCode.getSelectedIndex() <= 0) {			
			InlineLabel error = new InlineLabel("Please select a country code");		
			showErrorPopupPanel(error, "red");			
			return false;
		}
		*/
		
		//  generate digest of the password
		hashedPassword = getSHA1for((passwordReg.getText()));
		// encrypt the password
		//TripleDesCipher cipher = new TripleDesCipher();
		//cipher.setKey(GWT_DES_KEY);

		
		//
		//try {
		//  encryptedPassword =  cipher.encrypt(passwordReg.getText());
		//} catch (DataLengthException e) {
		////  e.printStackTrace();
		//} catch (IllegalStateException e) {
		//  e.printStackTrace();
		//} catch (InvalidCipherTextException e) {
		//  e.printStackTrace();
	//	}
		   
		return true;
	}
	
	String getSHA1for(String text) {
		  SHA1Digest sd = new SHA1Digest();
		  byte[] bs = text.getBytes();
		  sd.update(bs, 0, bs.length);
		  byte[] result = new byte[20];
		  sd.doFinal(result, 0);
		  return byteArrayToHexString(result);
		}

		String byteArrayToHexString(final byte[] b) {
		  final StringBuffer sb = new StringBuffer(b.length * 2);
		  for (int i = 0, len = b.length; i < len; i++) {
		    int v = b[i] & 0xff;
		    if (v < 16) sb.append('0');
		    sb.append(Integer.toHexString(v));
		  }
		  return sb.toString();
		}
		
    private boolean callServiceSetup() {
    			// set up rpc call
		
    			InminddServiceSvc = (InminddServiceAsync) GWT.create(InminddService.class);
    			ServiceDefTarget target = (ServiceDefTarget) InminddServiceSvc;
    			String moduleRelativeURL = GWT.getModuleBaseURL() + "Inmindd";
    			target.setServiceEntryPoint(moduleRelativeURL);	    			
    			return true;

    }
    
    public VerticalPanel getMainPanel() {
		return mainpanel;
	}
    
    private class AuthenticationHandler<Boolean> implements AsyncCallback<User> {
    	
    	@Override
		public void onFailure(Throwable ex) {
    		RootPanel.get().add(new HTML("RPC failed"));
    	}
    	@Override
		public void onSuccess(User user){
    		if (!(user != null)){			
    			
    			
    			InlineLabel error = new InlineLabel("Database Error - check entered fields");
    			showErrorPopupPanel(error, "red");
    		}
    		
    		else {
    			InlineLabel error = new InlineLabel("Congratulations, you are now registered with Inmindd");
    			// Clear screens of previous input
				
    		}
    	}
    	
    	
    }
  
		
  private class AuthenticationHandlerReg<t> implements AsyncCallback<Boolean> {
    	
    	@Override
		public void onFailure(Throwable ex) {
    		RootPanel.get().add(new HTML("RPC failed"));
    	}
    	@Override
		public void onSuccess(Boolean result){
    		if (!result){
    		
    			InlineLabel error = new InlineLabel("Database Error - check entered fields");
    			showErrorPopupPanel(error, "red");
    		}
    		
    		else {
    			InlineLabel error = new InlineLabel("Congratulations, you are now registered with Inmindd. Please proceed to input panels");
    			showErrorPopupPanel(error, "green");
    			if(PatientInfo.lastinstance != null)
					PatientInfo.clearInputs();	
				if (Feelings.lastinstance != null)
					Feelings.clearInputs();
				if (MedicalHealth.lastinstance != null)
					MedicalHealth.clearInputs();
				if (FamilyMedicalHistory.lastinstance != null)
					FamilyMedicalHistory.clearInputs();
				if (PhysicalActivity.lastinstance != null)
					PhysicalActivity.clearInputs();
				if (CognitiveOne.lastinstance != null)
					CognitiveOne.clearInputs();
				if (CognitiveTwo.lastinstance != null)
					CognitiveTwo.clearInputs();
				if (SmokeAlcohol.lastinstance != null)
					SmokeAlcohol.clearInputs();
				if (Diet.lastinstance != null)
					Diet.clearInputs();
    		}
    	}
  }
    public String  getUserId() {
    	String userId = "";
    	if (user != null) {
    		userId = user.getUserId();
    		
    	}
    	return userId;
    }
	private boolean  validateUserId(String id) {
		// make sure the entered string represents an integer
		try {
			idUser  = Integer.parseInt(id);	
		
		}

		catch (Exception e)
		{
			InlineLabel error  = new InlineLabel("Invalid User Id - Please re-enter. Check your caps lock");	
			showErrorPopupPanel(error,"red");
				
			return false;
		}
		
		return true;
	}
    private void showErrorPopupPanel(InlineLabel error, String color) {
		PopupPanel popup = new PopupPanel(true, true);			

		popup.setTitle("Error");
		VerticalPanel vertPanel = new VerticalPanel();
		error.getElement().getStyle().setProperty("color", color);
		error.getElement().getStyle().setProperty("fontWeight", "bold");
		error.getElement().getStyle().setProperty("marginLeft", "25px");


		vertPanel.add(error);
		popup.setWidget(vertPanel);
		//popup.setGlassEnabled(true);
		popup.setPopupPosition(450,676);
		popup.setWidth("650px");
		popup.show();

	}
    
    private void getScore() {				
		  String userId =  user.getUserId();
		 if (user== null) {

			 InlineLabel error  = new InlineLabel("You must first log in or register with InMindd - go to Login panel");
			 showErrorPopupPanel(error, "red");
			 return;

		 }
		 callServiceSetup();

		 AsyncCallback<RiskFactorScore> callback =  new AsyncCallback<RiskFactorScore>(){

			 @Override	 
			 public void onSuccess(RiskFactorScore score) {
				 if ((score == null)){	            		
				//	 InlineLabel error = new InlineLabel("Score data not retrieved ");
				//	 showErrorPopupPanel(error, "red");            			
				 }            		
				 else {
				//	 InlineLabel error = new InlineLabel("Libra Score retrieved");
				//	 showErrorPopupPanel(error, "green");  
					
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
			//	 InlineLabel error = new InlineLabel("Libra  score not retrieved");
			//	 showErrorPopupPanel(error, "red");			

			 }
		 };

		 InminddServiceSvc.getLibraScore(user, callback);
		 return;
	 }
	

}
