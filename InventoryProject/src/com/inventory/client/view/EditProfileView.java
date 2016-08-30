package com.inventory.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditProfileView extends Composite {

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
}