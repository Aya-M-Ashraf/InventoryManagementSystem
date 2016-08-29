package com.inventory.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.ForgetPasswordPresenter;

public class ForgetPasswordView extends Composite implements ForgetPasswordPresenter.Display {

	private static ForgetPasswordViewUiBinder uiBinder = GWT.create(ForgetPasswordViewUiBinder.class);

	interface ForgetPasswordViewUiBinder extends UiBinder<Widget, ForgetPasswordView> {
	}

	public ForgetPasswordView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	TextBox email;
	
	@UiField
	Label flipText;
	
	@UiField
	Anchor backToSignIn;

	@UiField
	Button sendBtn;
	

	@Override
	public TextBox getEmail() {
		return email;
	}
	
	@Override
	public Label getFilpText() {
		return flipText; 
	}
	
	@Override
	public HasClickHandlers getSendBtn() {
		return sendBtn;
	}

	@Override
	public HasClickHandlers getBackToSignIn() {
		return backToSignIn;
	}
}
