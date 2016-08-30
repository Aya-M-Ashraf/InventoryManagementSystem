package com.inventory.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.EditProfileOPresenter;;

public class EditProfileView extends Composite implements EditProfileOPresenter.Display{

	private static EditProfileViewUiBinder uiBinder = GWT.create(EditProfileViewUiBinder.class);

	interface EditProfileViewUiBinder extends UiBinder<Widget, EditProfileView> {
	}

	public EditProfileView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	TextBox email;
	
	@UiField
	PasswordTextBox oldPassword;
	
	@UiField
	PasswordTextBox newPassword;
	
	@UiField
	Button updateBtn; 
	
	@UiField
	Anchor backToSignIn;
	
	@UiField
	Label flashingTxt;

	@Override
	public TextBox getEmail() {
		return email;
	}

	@Override
	public PasswordTextBox getOldPassword() {
		return oldPassword;
	}

	@Override
	public PasswordTextBox getNewPassword() {
		return newPassword;
	}

	@Override
	public HasClickHandlers getUpdateBtn() {
		return updateBtn;
	}

	@Override
	public HasClickHandlers getBackToSignIn() {
		return backToSignIn;
	}

	@Override
	public Label getFlashingTxt() {
		return flashingTxt;
	}
}