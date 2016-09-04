package com.inventory.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasDirectionalText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.shared.dto.UserDTO;

public class AllClientsView extends Composite implements com.inventory.client.presenter.AllClientsPresenter.Display {

	private static AllClientsViewUiBinder uiBinder = GWT.create(AllClientsViewUiBinder.class);

	interface AllClientsViewUiBinder extends UiBinder<Widget, AllClientsView> {
	}

	DataGrid<UserDTO> dataGrid;
	@UiField
	Label label;
	@UiField
	Label info;
	
	public AllClientsView() {
		System.out.println("In construcotr of   client view");
		constructDataGrid();
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public DataGrid<UserDTO> getDataGridList() {

		return dataGrid;
	}

	public void constructDataGrid() {

		dataGrid = new DataGrid<UserDTO>();
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		dataGrid.setSize("1000px ", "1000px");

		// =======================Orders ======================================
		// ======================================================
		/*
		 * Column<UserDTO, Number> index = new Column<UserDTO, Number>(new
		 * NumberCell()) {
		 * 
		 * @Override public Integer getValue(UserDTO object) { return
		 * object.getId() ; } }; dataGrid.addColumn(index, "Index");
		 * 
		 */
		// =======================Names =======================================
		TextColumn<UserDTO> name = new TextColumn<UserDTO>() {
			@Override
			public String getValue(UserDTO object) {
				return object.getUsername();
			}
		};
		dataGrid.addColumn(name, "Name");

		// =======================Email =======================================
		TextColumn<UserDTO> email = new TextColumn<UserDTO>() {
			@Override
			public String getValue(UserDTO object) {
				return object.getEmail();
			}
		};
		dataGrid.addColumn(email, "Email");

		// =======================Address======================================
		TextColumn<UserDTO> address = new TextColumn<UserDTO>() {
			@Override
			public String getValue(UserDTO object) {
				return object.getAddress();
			}
		};
		dataGrid.addColumn(address, "Address");

		// ====================================================

	}

	@Override
	public void setDataGrid(List<UserDTO> users) {

		dataGrid.setRowData(users);
		dataGrid.setRowCount(users.size(), true);
		dataGrid.setWidth("100%");

		// dataGrid.getElement().getStyle().setBackgroundColor("BurlyWood");//Header
		// Background

		dataGrid.setColumnWidth(2, "200px");
		RootPanel.get("divTest").add(dataGrid);

	}

	@Override
	public HasDirectionalText getLabel() {
		// TODO Auto-generated method stub
		return label;
	}

}
