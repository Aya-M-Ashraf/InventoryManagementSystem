package com.inventory.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.WelcomePresenter;

public class WelcomeView extends Composite implements WelcomePresenter.Display {

	private static WelcomeViewUiBinder uiBinder = GWT.create(WelcomeViewUiBinder.class);

	interface WelcomeViewUiBinder extends UiBinder<Widget, WelcomeView> {
	}

	public WelcomeView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	WelcomePresenter welcomePresenter;
	
	@UiField
	Button logOutBtn;

	@UiField
	TextBox username;

	@UiField
	TextBox theId;

	@UiField
	TextBox email;

	@UiField
	TextBox address;

	@Override
	public HasValue<String> getUsername() {
		return username;
	}

	@Override
	public HasValue<String> getId() {
		return theId;
	}

	@Override
	public HasValue<String> getEmail() {
		return email;
	}

	@Override
	public HasValue<String> getAddress() {
		return address;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getLogOutBtn() {
		return logOutBtn;
	}

}
