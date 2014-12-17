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
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

//import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
//import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
//import com.googlecode.gwt.crypto.bouncycastle.digests.SHA1Digest;
//import com.googlecode.gwt.crypto.client.TripleDesCipher;
import com.inmindd.dcu.shared.Crypto;
import com.inmindd.dcu.shared.RiskFactorScore;
import com.inmindd.dcu.shared.Patient;
import com.inmindd.dcu.shared.User;


/*
 * @Author Noel O'Kelly DCU School of Computing
 * Login or Register User with  RPC calls to update Mysql user table
 * 
 */
public class Login  {

	private  User user = new User();
	private  Patient patient = new Patient();
  
	static InminddConstants constants = 
			   (InminddConstants)GWT.create(InminddConstants.class);
	
	/**
	 * rpc call
	 */
	private InminddServiceAsync InminddServiceSvc;
	
	/**Main panel of the login view*/
    private VerticalPanel mainpanel = new VerticalPanel();
	
	private TabLayoutPanel content;
	
	
	private HTML secondHeadline = new HTML("<h1>" + constants.register() +" </h1>");
	
	/**Decorator panel for the login form*/
    private DecoratorPanel decPanel = new DecoratorPanel();
    private DecoratorPanel decPanelForgot = new DecoratorPanel();
    
    
    /**Grid for login form elements*/
    private FlexTable loginLayout = new FlexTable(); 
    private FlexTable forgotLayout = new FlexTable();
  
	
    
    private InlineLabel loginHead = new InlineLabel(constants.login());
    private InlineLabel loginRegister = new InlineLabel(constants.register_heading());
    private InlineLabel loginReset = new InlineLabel(constants.reset_password());
  

  
	private String userIdLabel = constants.user();
    private String passwordLabel = constants.password();
    private String passwordRepeat  = constants.repeat_password();
    private String mothersMaiden  = constants.maiden_name();
    private String favColour  = constants.fav_colour();
    private String emailAddress = constants.emailAddress();
   
    private TextBox userId = new TextBox();
  
  
    private PasswordTextBox password = new PasswordTextBox();
    
    private TextBox userIdReg = new TextBox();
    private TextBox userForgotIdReg = new TextBox();
    private TextBox userEmailAddress = new TextBox();
    
    private PasswordTextBox passwordReg = new PasswordTextBox();
    private PasswordTextBox passwordRegRepeat = new PasswordTextBox();
    private PasswordTextBox motherBox = new PasswordTextBox();
    private PasswordTextBox colourBox = new PasswordTextBox();
    
    private PasswordTextBox passwordForgotReg = new PasswordTextBox();
    private PasswordTextBox passwordForgotRegRepeat = new PasswordTextBox();
    private PasswordTextBox motherForgotBox = new PasswordTextBox();
    private PasswordTextBox colourForgotBox = new PasswordTextBox();
  
    private Button loginbutton = new Button(constants.login());    
    private Button registerbutton = new Button(constants.register_heading());  
    private Button forgotPasswordButton = new Button(constants.forgot_password());    
    private Button resetPasswordButton = new Button(constants.reset_password());    
  
    private String hashedPassword;
    private String hashedMaidenName;
    private String hashedFavColour;

    
    private Boolean duplicate = false;
    private int idUser;
    
    public Login() {
    	
    }
    
    public void setContent(TabLayoutPanel content) {
    	this.content = content;
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
		
    	
    	motherBox.setMaxLength(30);
		motherBox.setWidth("6em");
    	
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
        cellFormatter.setColSpan(0, 0, 3);
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
        loginLayout.setWidget(2, 2, forgotPasswordButton);
        
        //Add the loginbutton to the form
        loginLayout.setWidget(3, 0, loginbutton);
       
        cellFormatter.setColSpan(3, 0, 3);
        cellFormatter.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
        
        
        
        loginLayout.setHTML(4, 0, "");   
        
        loginLayout.setHTML(5, 0, "");
        loginLayout.setWidget(6, 0, this.loginRegister);
        cellFormatter.setColSpan(6, 0, 3);
        cellFormatter.setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
     
        
       
       
        password.setWidth("150px");
        loginLayout.setHTML(7, 0, this.userIdLabel);
        loginLayout.setWidget(7, 1, userIdReg);
        loginLayout.setHTML(8, 0, passwordLabel);
        loginLayout.setWidget(8, 1, passwordReg);
       
        loginLayout.setHTML(9, 0, passwordRepeat);
        loginLayout.setWidget(9, 1, passwordRegRepeat);
        motherBox.setWidth("145px");
        loginLayout.setHTML(10, 0, mothersMaiden);
        loginLayout.setWidget(10, 1, motherBox);
        
        motherBox.setWidth("145px");
        loginLayout.setHTML(11, 0, favColour);
        loginLayout.setWidget(11, 1, colourBox);
    
        motherBox.setWidth("145px");
        loginLayout.setHTML(12, 0, emailAddress);
        loginLayout.setWidget(12, 1, userEmailAddress);
    
        loginLayout.setWidget(13, 0, registerbutton);
        
        cellFormatter.setColSpan(13, 0, 3);
        cellFormatter.setHorizontalAlignment(13, 0, HasHorizontalAlignment.ALIGN_CENTER);
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
        			//  generate digest of the password
        			hashedPassword = Crypto.getSHA1for((passwordReg.getText()));
        			// generate digest of mother's maiden name
        			hashedMaidenName = Crypto.getSHA1for((motherBox.getText()));
        			// generate digest of favorite colour
        			hashedFavColour = Crypto.getSHA1for((colourBox.getText()));
        			//Check is the email address field entered
        			if((userEmailAddress.getText()!=null) ||!userEmailAddress.getText().equals(""))//First check if it's blank
        			{
        				String unVaildatedEmailAddress = userEmailAddress.getText();
        				if(isEmailValid(unVaildatedEmailAddress))
        				{	Window.alert(userEmailAddress.getText());
        					String userId = userIdReg.getText();
        					AsyncCallback<Boolean> cback = new AsyncCallback<Boolean>()
							{

								@Override
								public void onFailure(Throwable caught)
								{
									// TODO Do we log these somewhere?
									
								}

								@Override
								public void onSuccess(Boolean result)
								{
									// TODO Nothing much really, we just want confirmation, do we log these somewhere?
									
								}
							};
        					InminddServiceSvc.addUserEmail(userId, unVaildatedEmailAddress, cback);
        				}
        			}
        			callServiceSetup();
        			createUser(userIdReg.getText());
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
	    		hashedPassword = Crypto.getSHA1for((password.getText()));
	    		

	    		AsyncCallback<User> callback = new AsyncCallback<User>() {

	    			public void onSuccess(User user) {
	    				if ((user == null)){	            		
	    					InlineLabel error = new InlineLabel(constants.invalid_id());
	    					showErrorPopupPanel(error, "red");
	    					setUser(user);
	    				}

	    				else {
	    				//	InlineLabel error = new InlineLabel("You are now logged on to InMINDD. Please proceed to the About You panel");
	    				//	showErrorPopupPanel(error, "green");	            			
	    					setUser(user);	
	    					InminddServiceSvc.updateUserLastLogin(user.getUserId(), new AsyncCallback<Boolean>()
							{

								@Override
								public void onFailure(Throwable caught)
								{
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(Boolean result)
								{
									// TODO Auto-generated method stub
									
								}
							}); //Update the users last login time
	    					
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
	    					//userId.setText("");
	    					password.setText("");
	    					//getScore();   //uncomment this to calc score at login
	    					content.selectTab(1);
	    					content.getTabWidget(0).getElement().getStyle().setProperty("backgroundColor", "red");
	    					
	    					//Update the users last login time on the database
	    				}

	    			}
	    			@Override
	    			public void onFailure(Throwable caught) {
	    				if (caught.getMessage().equals("Blocked")) {
	    					InlineLabel error = new InlineLabel(constants.access_blocked());
		    				showErrorPopupPanel(error, "red");
		    				return;
	    				}
	    				InlineLabel error = new InlineLabel(constants.invalid_id());
	    				showErrorPopupPanel(error, "red");
	    				setUser(user);

	    			}
	    		};

	    		InminddServiceSvc.authenticateUser(userId.getText(),hashedPassword, callback);
	    	}

	    });
	    // Listen for mouse events on the forgot password   button.
	    forgotPasswordButton.addClickHandler(new ClickHandler() {
	    	@Override
	    	public void onClick(ClickEvent event) {
	    		forgotPassword();
	    	}
	    });
	    
	    return mainpanel;

	}    
	
	/***
	 * This method will examin what is entered in the email text box on the registration form
	 * From here it will determine if what is entered is actually an email address (i.e. a String of the form <some text>@<domain>.<tld>
	 * Vaildation is done by the regualr expression for email addresses given by RFC 2822
	 * @param emailAddress The string to vaildate
	 * @return a boolean indicating whether or not he email address is vaild
	 */
	private boolean isEmailValid(String emailAddress)
	{

		String regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		return emailAddress.matches(regexp);
	}
	
	private void forgotPassword() {
		
			loginReset.getElement().getStyle().setProperty("textDecoration", "underline");
			loginReset.getElement().getStyle().setProperty("fontWeight", "bold");
			
	        FlexCellFormatter cellFormatter = forgotLayout.getFlexCellFormatter();
	     

	        //Add the loginbutton to the form
	        forgotLayout.setWidget(0, 0, loginReset);
	       
	        cellFormatter.setColSpan(0, 0, 3);
	        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	        
	        
	        
	        forgotLayout.setHTML(1, 0, "");   
	        
	        forgotLayout.setHTML(2, 0, "");
	    
	        forgotLayout.setHTML(3, 0, this.userIdLabel);
	        forgotLayout.setWidget(3, 1, userForgotIdReg);
	        motherForgotBox.setWidth("145px");
	        forgotLayout.setHTML(4, 0, mothersMaiden);
	        forgotLayout.setWidget(4, 1, motherForgotBox);
	        
	        colourForgotBox.setWidth("145px");
	        forgotLayout.setHTML(5, 0, favColour);
	        forgotLayout.setWidget(5, 1, colourForgotBox);
	       
	       
	        password.setWidth("150px");
	       
	        forgotLayout.setHTML(6, 0, passwordLabel);
	        forgotLayout.setWidget(6, 1, passwordForgotReg);
	       
	        forgotLayout.setHTML(7, 0, passwordRepeat);
	        forgotLayout.setWidget(7, 1, passwordForgotRegRepeat);
	       
	    
	    
	        forgotLayout.setWidget(12, 0, resetPasswordButton);
	        
	        cellFormatter.setColSpan(12, 0, 3);
	        cellFormatter.setHorizontalAlignment(12, 0, HasHorizontalAlignment.ALIGN_CENTER);
	        forgotLayout.setCellSpacing(6);
	     

	       
	        password.setWidth("150px");
	        // Wrap the content in a DecoratorPanel
	        
	        decPanelForgot.setWidget(forgotLayout);     
	        if (!decPanelForgot.isVisible()) {
	        	decPanelForgot.setVisible(true);
	        }
	        decPanel.setVisible(false);	     
		
	        mainpanel.add(decPanelForgot);
	      
	        // Listen for mouse events on the forgot password   button.
		    resetPasswordButton.addClickHandler(new ClickHandler() {
		    	@Override
		    	public void onClick(ClickEvent event) {
		    		if (validateForgotUser()) {
	        			//  generate digest of the password
	        			hashedPassword = Crypto.getSHA1for((passwordForgotReg.getText()));
	        			// generate digest of mother's maiden name
	        			hashedMaidenName = Crypto.getSHA1for((motherForgotBox.getText()));
	        			// generate digest of favorite colour
	        			hashedFavColour = Crypto.getSHA1for((colourForgotBox.getText()));
	        			
	        			callServiceSetup();
	        			createUser(userForgotIdReg.getText());
	        			AsyncCallback<Boolean> callback = new AuthenticationHandlerReset<Boolean>();

	        			InminddServiceSvc.resetPassword(user, callback);      
		    		}
		    	}
		    });
		    
		
	}
	
	private void createUser(String userId) {		
		user.setUserId(userId);
		
		user.setPassword(hashedPassword);
		user.setMaidenName(hashedMaidenName);
		user.setFavoriteColour(hashedFavColour);
		return;
		
	}
	
	private boolean validateUser() {
		if(!validateUserId(userIdReg.getText())) {			
			return false;
		} 
		
		if (userIdReg.getText().equals("")) {
			InlineLabel error = new InlineLabel(constants.enter_id());
			showErrorPopupPanel(error, "red");
			return false;
		}
	
		String rep = passwordRegRepeat.getText();
		String psw = passwordReg.getText();
		if (rep.equals("") && psw.equals("")) {			
			InlineLabel error = new InlineLabel(constants.enter_password());
			showErrorPopupPanel(error, "red");
			return false;
			
		}
		if (!(passwordReg.getText().equals(rep))) {
			InlineLabel error = new InlineLabel(constants.passwords_differ());
			showErrorPopupPanel(error, "red");
			return false;
		}
		
		if (motherBox.getText().equals("")) {
			InlineLabel error = new InlineLabel(constants.maiden_name());
			showErrorPopupPanel(error, "red");
			return false;
		}
	
		if (colourBox.getText().equals("")) {
			InlineLabel error = new InlineLabel(constants.fav_colour());
			showErrorPopupPanel(error, "red");
			return false;
		}
		return true;
		
	}
		private boolean validateForgotUser() {
			if(!validateUserId(userForgotIdReg.getText())) {			
				return false;
			} 
			
			if (userForgotIdReg.getText().equals("")) {
				InlineLabel error = new InlineLabel(constants.enter_id());
				showErrorPopupPanel(error, "red");
				return false;
			}
		
			String rep = passwordForgotRegRepeat.getText();
			String psw = passwordForgotReg.getText();
			if (rep.equals("") && psw.equals("")) {			
				InlineLabel error = new InlineLabel(constants.enter_password());
				showErrorPopupPanel(error, "red");
				return false;
				
			}
			if (!(passwordForgotReg.getText().equals(rep))) {
				InlineLabel error = new InlineLabel(constants.miss_match());
				showErrorPopupPanel(error, "red");
				return false;
			}
			
			if (motherForgotBox.getText().equals("")) {
				InlineLabel error = new InlineLabel(constants.maiden_name());
				showErrorPopupPanel(error, "red");
				return false;
			}
		
			if (colourForgotBox.getText().equals("")) {
				InlineLabel error = new InlineLabel(constants.fav_colour());
				showErrorPopupPanel(error, "red");
				return false;
			}
			
		
			
		// encrypt the password
	/*	TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(GWT_DES_KEY);
		String encryptedPassword = null;


		try {
			encryptedPassword =  cipher.encrypt(passwordReg.getText());
		} catch (DataLengthException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
		}
*/
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
    
    public VerticalPanel getMainPanel() {
		return mainpanel;
	}
  
  
		
  private class AuthenticationHandlerReg<t> implements AsyncCallback<Boolean> {
    	
    	@Override
		public void onFailure(Throwable ex) {
    		RootPanel.get().add(new HTML("RPC failed"));
    	}
    	@Override
		public void onSuccess(Boolean result){
    		if (!result){
    		
    			InlineLabel error = new InlineLabel(constants.reg_error());
    			showErrorPopupPanel(error, "red");
    		}
    		
    		else {
    			//InlineLabel error = new InlineLabel(constants.registration());
    			//showErrorPopupPanel(error, "green");
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
				passwordReg.setText("");
				passwordRegRepeat.setText("");
				//userIdReg.setText("");
				motherBox.setText("");
				colourBox.setText("");
				content.selectTab(1);
    		}
    	}
  }
  
  private class AuthenticationHandlerReset<t> implements AsyncCallback<Boolean> {
  	
  	@Override
		public void onFailure(Throwable ex) {
  		RootPanel.get().add(new HTML("RPC failed"));
  	}
  	@Override
		public void onSuccess(Boolean result){
  		if (!result){
  		
  			InlineLabel error = new InlineLabel(constants.reset_password_fail());
  			showErrorPopupPanel(error, "red");
  		}
  		
  		else {
  			InlineLabel error = new InlineLabel(constants.reset());
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
				passwordReg.setText("");
				passwordRegRepeat.setText("");
				//userIdReg.setText("");
				motherBox.setText("");
				colourBox.setText("");
				decPanel.setVisible(true);
				decPanelForgot.setVisible(false);
				passwordForgotReg.setText("");
				passwordForgotRegRepeat.setText("");
				//userIdReg.setText("");
				motherForgotBox.setText("");
				colourForgotBox.setText("");
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
			InlineLabel error  = new InlineLabel(constants.invalid_id());	
			showErrorPopupPanel(error,"red");
				
			return false;
		}
		if (!(id.startsWith("11") || id.startsWith("22") || id.startsWith("33") || id.startsWith("44"))) // valid country code ??
		{
			InlineLabel error  = new InlineLabel(constants.caps_lock());	
			showErrorPopupPanel(error,"red");
				
			return false;
		}
		
		String practice = id.substring(2, 4);
		if (Integer.parseInt(practice) < 0 || Integer.parseInt(practice) > 20) {
			
			InlineLabel error  = new InlineLabel(constants.practice_code_error());	
			showErrorPopupPanel(error,"red");
				
			return false;
			
		}
		
		if(id.length() != 7){
			InlineLabel error  = new InlineLabel(constants.invalid_id());	
			showErrorPopupPanel(error,"red");
				
			return false;
		}
		//checkAlreadyRegistered(id);
		return true;
			
	}
	// not used 
	private Boolean checkAlreadyRegistered(String id) {
		 callServiceSetup();
		 //final Boolean dup;
		 AsyncCallback<Boolean> callback =  new AsyncCallback<Boolean>(){

			 @Override	 
			 public void onSuccess(Boolean duplicate) {
				 if (duplicate){	            		
					 InlineLabel error = new InlineLabel(constants.reg_error());
					 showErrorPopupPanel(error, "red"); 
					
					
				 }            		
				 else {		
					duplicate = false;
					
				 }

			 }
			 @Override
			 public void onFailure(Throwable caught) {
				 duplicate = true;

			 }
		 };

		 InminddServiceSvc.duplicateUser(id, callback);
		 
		return duplicate;
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
		popup.setPopupPosition(420,240);
		popup.setWidth("750px");
		popup.show();

	}
    
    private void getScore() {				
		 String userId =  user.getUserId();
		 if (user== null) {

			 InlineLabel error  = new InlineLabel(constants.register());
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

