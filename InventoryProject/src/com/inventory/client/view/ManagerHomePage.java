package com.inventory.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.test.entity.User;

public class ManagerHomePage extends Composite {

	private static ManagerHomePageUiBinder uiBinder = GWT.create(ManagerHomePageUiBinder.class);

	interface ManagerHomePageUiBinder extends UiBinder<Widget, ManagerHomePage> {
	}

	public ManagerHomePage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField(provided = true)
	DataGrid<User> contactList;
	@UiField
	Button commitButton;
	@UiField
	Button redrawButton;

}
