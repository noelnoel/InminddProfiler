package com.inmindd.dcu.client;


	


	import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

	/**
	 * Widget that demonstrates how to build a GWT Composite.
	 * 
	 * It consists of a Label (question) and a TextBox (answer) in a
	 * HorizontalPanel.
	 * 
	 * In Ch11's Direction example we move away from the explicit setting of
	 * direction to determining direction through estimators (e.g. if there are more
	 * RTL words than LTR the diretion of Composite and components would be RTL)
	 * 
	 */
	public class DataField extends Composite implements HasText, HasDirection {

		// The UI elements we will manipulate
		private InlineLabel theQuestion;
		private InlineLabel theSuffix;
		private TextBox theAnswer;
		private FlowPanel panel;
		private InlineLabel error;

		protected Direction dir = Direction.DEFAULT;

		/**
		 * Construct the widget
		 */
		public DataField(String question) {
			panel = new FlowPanel();			
			panel.setWidth("100%");
			theQuestion = new InlineLabel(question);
			theAnswer = new TextBox();
			theAnswer.setMaxLength(7);
			theAnswer.setWidth("2em");
			//theAnswer.addStyleName("htmlPanel");
		    theSuffix = new InlineLabel("");
			buildDisplay();

			initWidget(panel);		
		}
		
		public DataField(String question, String suffix) {
			panel = new FlowPanel();			
			panel.setWidth("100%");
			theQuestion = new InlineLabel(question);
			theQuestion.getElement().getStyle().setProperty("minWidth", "189px");
			theQuestion.getElement().getStyle().setProperty("maxWidth", "189px");
			
			theSuffix = new InlineLabel(suffix);
			theSuffix.getElement().getStyle().setProperty("marginLeft", "5px");
			theAnswer = new TextBox();
			theAnswer.setMaxLength(3);
			theAnswer.setWidth("2em");
	
		
	/*		theAnswer.addKeyDownHandler(new KeyDownHandler() {

			    @Override
			    public void onKeyDown(KeyDownEvent event) {
			     if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			    	 System.out.println("enter");
			                 Window.alert("enter!");
			           }
			    }
			});
			*/
		  
			buildDisplay();

			initWidget(panel);		
		}

		private void buildDisplay() {
			panel.clear();
			theQuestion.setText(theQuestion.getText(), dir);
			theAnswer.setDirection(dir);
			if (dir.equals(Direction.RTL)) {
				panel.add(theAnswer);
				panel.add(theQuestion);
			} else {
				panel.add(theQuestion);
				panel.add(theAnswer);
				panel.add(theSuffix);
			}
		}

		// Utility method to get the answer text
		public String getText() {
			String answer = "";
			if (theAnswer != null)
				answer = theAnswer.getText();
			return answer;
		}
		
		// Utility method to get the answer text as an integer
		public double  getDoubleValue() {
			double answer;
			try {
				answer  = Double.parseDouble(theAnswer.getText());			}

			catch (Exception e)
			{
				//error = new InlineLabel("Invalid or non existant  input - Please re-enter");	
				//showErrorPopupPanel(error);
				theAnswer.getElement().getStyle().setProperty("color", "red");	
				return -1;
			}	
			return answer;
		}


		// Utility method to get the question text
		public String getQuestion() {
			String question = "";
			if (theQuestion != null)
				question = theQuestion.getText();
			return question;
		}

		// Utility method to set the answer text
		public void setText(String text) {
			if (theAnswer != null)
				theAnswer.setText(text);
		}

		// Utility method to set the question text
		public void setQuestion(String text) {
			if (theQuestion != null)
				theQuestion.setText(text);
		}

		
		// Utility method to set the focus
				public void setFocus() {
					theAnswer.setFocus(true);
				}
				
				
		public void setDirection(Direction direction) {
			this.dir = direction;
			buildDisplay();
		}

		public Direction getDirection() {
			return dir;
		}

		public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
			return addDomHandler(handler, MouseOverEvent.getType());
		}

		public HandlerRegistration addValueChangeHandler(
				ValueChangeHandler<String> valueChangedHandler) {
			return theAnswer.addValueChangeHandler(valueChangedHandler);
		}
		
		
		private void showErrorPopupPanel(InlineLabel error) {
			PopupPanel popup = new PopupPanel(true, true);			

			popup.setTitle("Error");
			VerticalPanel vertPanel = new VerticalPanel();
			error.getElement().getStyle().setProperty("color", "red");
			error.getElement().getStyle().setProperty("fontWeight", "bold");
			error.getElement().getStyle().setProperty("marginLeft", "25px");


			vertPanel.add(error);
			popup.setWidget(vertPanel);
			popup.setGlassEnabled(true);
			popup.setPopupPosition(190,576);
			popup.setWidth("450px");
			popup.show();

		}
		
	}


