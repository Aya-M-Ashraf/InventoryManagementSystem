package com.inventory.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasDirectionalText;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.RegisterationPresenter;

public class Registeration extends Composite implements com.inventory.client.presenter.RegisterationPresenter.Display {

	private static RegisterationUiBinder uiBinder = GWT.create(RegisterationUiBinder.class);

	interface RegisterationUiBinder extends UiBinder<Widget, Registeration> {
	}

	public Registeration() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField 
	TextBox username;
	@UiField
	TextBox email;
	@UiField
	TextBox address;
	@UiField
	 PasswordTextBox password;
	@UiField 
	PasswordTextBox confirmPassword;
	@UiField
	Label errorpass;
	@UiField 
	Button register;
	@UiField 
	Label errorpassword;
	@UiField 
	Label erroraddress;
	@UiField
	Label errorusername;
	@UiField
	Label erroremail;
	

	
	
	
	public Registeration(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		register.setText(firstName);
	}

	
	
	@Override
	public HasClickHandlers getRegister() {
		// TODO Auto-generated method stub
		return register;
	}

	@Override
	public TextBox getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public TextBox getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public TextBox getAddress() {
		// TODO Auto-generated method stub
		return address;
	}

	@Override
	public TextBox getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public TextBox getConfirmPassword() {
		// TODO Auto-generated method stub
		return confirmPassword;
	}

	@Override
	public Widget asWidget(){
		return this;
	}

	@Override
	public Label getErrorpass() {
		// TODO Auto-generated method stub
		return errorpass;
	}



	@Override
	public Label getErrorPassword() {
		// TODO Auto-generated method stub
		return errorpassword;
	}



	@Override
	public Label getErrorEmail() {
		// TODO Auto-generated method stub
		return erroremail;
	}



	@Override
	public Label getErrorAddress() {
		// TODO Auto-generated method stub
		return erroraddress;
	}



	@Override
	public Label getErrorUsername() {
		// TODO Auto-generated method stub
		return errorusername;
	}
	
 
	
}
