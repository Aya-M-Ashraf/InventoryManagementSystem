package com.inventory.client.view;

import java.util.List;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasDirectionalText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.shared.dto.OrderDTO;

public class OrdersOfXClientView extends Composite
		implements com.inventory.client.presenter.OrdersOfXClientPresenter.Display {

	private static OrdersOfXClientUiBinder uiBinder = GWT.create(OrdersOfXClientUiBinder.class);

	interface OrdersOfXClientUiBinder extends UiBinder<Widget, OrdersOfXClientView> {
	}

	DataGrid<OrderDTO> dataGrid;
	@UiField
	Label label;
	@UiField
	Label clientName;

	public OrdersOfXClientView() {
		System.out.println("In construcotr of   client view");
		constructDataGrid();
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public DataGrid<OrderDTO> getDataGridList() {

		return dataGrid;
	}

	public void constructDataGrid() {

		dataGrid = new DataGrid<OrderDTO>();
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		dataGrid.setSize("1000px", "1000px");
		// =======================Orders =======================================

		TextColumn<OrderDTO> date = new TextColumn<OrderDTO>() {
			@Override
			public String getValue(OrderDTO object) {
				return object.getOrderDate().toString();
			}
		};
		dataGrid.addColumn(date, " Order Date");
		// =======================Names =======================================

		TextColumn<OrderDTO> id = new TextColumn<OrderDTO>() {
			@Override
			public String getValue(OrderDTO object) {
				return object.getProduct().getName();
			}
		};
		dataGrid.addColumn(id, " Product Name");
		// =======================Quantity
		// =======================================
		Column<OrderDTO, Number> quantity = new Column<OrderDTO, Number>(new NumberCell()) {
			@Override
			public Integer getValue(OrderDTO object) {

				return object.getQuantity();
			}
		};

		dataGrid.addColumn(quantity, "Quantity");

		// =======================Status =======================================
		TextColumn<OrderDTO> name = new TextColumn<OrderDTO>() {
			@Override
			public String getValue(OrderDTO object) {
				return object.getOrderStatus().getStatus();
			}
		};
		dataGrid.addColumn(name, "Order Status");

		// =======================Email =======================================

	}

	@Override
	public void setDataGrid(List<OrderDTO> users) {
		dataGrid.setRowData(users);
		dataGrid.setRowCount(users.size(), true);
		dataGrid.setWidth("100%");

		RootPanel.get("divTest").add(dataGrid);
	}

	@Override
	public HasDirectionalText getLabel() {
		// TODO Auto-generated method stub
		return label;
	}

	@Override
	public HasDirectionalText getLabelUserName() {
		// TODO Auto-generated method stub
		return clientName;
	}

}
