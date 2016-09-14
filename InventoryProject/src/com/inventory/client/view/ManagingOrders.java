package com.inventory.client.view;

import java.util.Date;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.ManagingOrdersPresenter;
import com.inventory.shared.dto.OrderDTO;

public class ManagingOrders extends Composite implements ManagingOrdersPresenter.Display {

	private static ManagingOrdersUiBinder uiBinder = GWT.create(ManagingOrdersUiBinder.class);

	interface ManagingOrdersUiBinder extends UiBinder<Widget, ManagingOrders> {}
	
	@UiField
	DockPanel dockPanel;
	DataGrid<OrderDTO> orderList;
	Column<OrderDTO, String> acceptOrderColumn;
	Column<OrderDTO, String> rejectOrderColumn;

	public ManagingOrders() {
		initWidget(uiBinder.createAndBindUi(this));
		orderList = new DataGrid<OrderDTO>();
		orderList.setSize("1300px", "500px");

		Column<OrderDTO, String> productNameColumn = new Column<OrderDTO, String>(new TextCell()) {
			@Override
			public String getValue(OrderDTO object) {
				return object.getProduct().getName();
			}
		};

		Column<OrderDTO, String> quantityColumn = new Column<OrderDTO, String>(new TextCell()) {
			@Override
			public String getValue(OrderDTO object) {
				return Integer.toString(object.getQuantity());
			}
		};

		Column<OrderDTO, String> totalWeightColumn = new Column<OrderDTO, String>(new TextCell()) {
			@Override
			public String getValue(OrderDTO object) {
				return Double.toString(object.getTotalWeight());
			}
		};

		Column<OrderDTO, Date> orderDateColumn = new Column<OrderDTO, Date>(new DateCell()) {
			@Override
			public Date getValue(OrderDTO object) {
				return object.getOrderDate();
			}
		};

		Column<OrderDTO, Date> deliveryDateColumn = new Column<OrderDTO, Date>(new DateCell()) {
			@Override
			public Date getValue(OrderDTO object) {
				return object.getDeliveryDate();
			}
		};

		Column<OrderDTO, String> orderStatusColumn = new Column<OrderDTO, String>(new TextCell()) {
			@Override
			public String getValue(OrderDTO object) {
				return object.getOrderStatus().getStatus();
			}
		};

		Column<OrderDTO, String> usernameColumn = new Column<OrderDTO, String>(new TextCell()) {
			@Override
			public String getValue(OrderDTO object) {
				return object.getUserDto().getUsername();
			}
		};

		acceptOrderColumn = new Column<OrderDTO, String>(new ButtonCell()) {
			@Override
			public String getValue(OrderDTO object) {
				return "Accept Order";
			}
		};

		rejectOrderColumn = new Column<OrderDTO, String>(new ButtonCell()) {
			@Override
			public String getValue(OrderDTO object) {
				return "Reject Order";
			}
		};

		// DataGrid Styling

		orderList.setColumnWidth(productNameColumn, 50, Unit.PX);
		orderList.setColumnWidth(quantityColumn, 50, Unit.PX);
		orderList.setColumnWidth(totalWeightColumn, 50, Unit.PX);
		orderList.setColumnWidth(orderDateColumn, 50, Unit.PX);
		orderList.setColumnWidth(deliveryDateColumn, 50, Unit.PX);
		orderList.setColumnWidth(orderStatusColumn, 50, Unit.PX);
		orderList.setColumnWidth(usernameColumn, 50, Unit.PX);
		orderList.setColumnWidth(acceptOrderColumn, 50, Unit.PX);
		orderList.setColumnWidth(rejectOrderColumn, 50, Unit.PX);

		orderList.addColumn(productNameColumn, "Product Name");
		orderList.addColumn(quantityColumn, "Quantity");
		orderList.addColumn(totalWeightColumn, "Total Weight");
		orderList.addColumn(orderDateColumn, "Order Date");
		orderList.addColumn(deliveryDateColumn, "Delivery Date");
		orderList.addColumn(orderStatusColumn, "Order Status");
		orderList.addColumn(usernameColumn, "Username");
		orderList.addColumn(acceptOrderColumn, "Accept");
		orderList.addColumn(rejectOrderColumn, "Reject");
		dockPanel.setSpacing(4);
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		dockPanel.add(orderList, DockPanel.NORTH);
		
	}

	@Override
	public DataGrid<OrderDTO> getOrderList() {
		return orderList;
	}

	@Override
	public Column<OrderDTO, String> getAcceptOrderColumn() {
		return acceptOrderColumn;
	}

	@Override
	public Column<OrderDTO, String> getRejectOrderColumn() {
		return rejectOrderColumn;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
