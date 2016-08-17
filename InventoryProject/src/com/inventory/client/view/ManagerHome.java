package com.inventory.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.ManagerHomePresenter;
import com.inventory.client.presenter.ManagerHomePresenter.Display;
import com.test.entity.Product;


public class ManagerHome extends Composite implements Display {

	private static ManagerHomeUiBinder uiBinder = GWT.create(ManagerHomeUiBinder.class);

	interface ManagerHomeUiBinder extends UiBinder<Widget, ManagerHome> {
	}

	public ManagerHome() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private ManagerHomePresenter presenter;
	@UiField(provided = true)
	DataGrid<Product> productList;
	@UiField
	Button commitButton;
	@UiField
	Button redrawButton;

	public void setPresenter(ManagerHomePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public DataGrid<Product> getDataGrid() {
		// TODO Auto-generated method stub
		return productList;
	}



}
