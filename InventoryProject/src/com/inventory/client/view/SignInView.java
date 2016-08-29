package com.inventory.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.SignInPresenter;

public class SignInView extends Composite implements SignInPresenter.Display {

	private static SignInViewUiBinder uiBinder = GWT.create(SignInViewUiBinder.class);

	interface SignInViewUiBinder extends UiBinder<Widget, SignInView> {
	}

	public SignInView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	TextBox inputEmail;

	@UiField
	TextBox inputPassword;

	@UiField
	Button signInBtn;

	@UiField
	Anchor forgetPassword;

	@UiField
	Label errorLabel;

	@UiField
	CheckBox rememberMe;

	@Override
	public TextBox getEmail() {
		return inputEmail;
	}

	@Override
	public TextBox getPassword() {
		return inputPassword;
	}

	@Override
	public HasClickHandlers getSignInBtn() {
		return signInBtn;
	}

	@Override
	public HasClickHandlers getForgetAnchor() {
		return forgetPassword;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public Label getErrorLabel() {
		return errorLabel;
	}

	@Override
	public CheckBox getRemeberMe() {
		return rememberMe;
	}

}