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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
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
	private Hyperlink logout;
	private Hyperlink clientsLink;
	private Hyperlink productsLink;
	private Hyperlink ordersLink;
	private Hyperlink reportsLink;
	


	public Hyperlink getLogout() {
		return logout;
	}

	public void setLogout(Hyperlink logout) {
		this.logout = logout;
	}

	public Hyperlink getClientsLink() {
		return clientsLink;
	}

	public void setClientsLink(Hyperlink clientsLink) {
		this.clientsLink = clientsLink;
	}

	public Hyperlink getProductsLink() {
		return productsLink;
	}

	public void setProductsLink(Hyperlink productsLink) {
		this.productsLink = productsLink;
	}

	public Hyperlink getOrdersLink() {
		return ordersLink;
	}

	public void setOrdersLink(Hyperlink ordersLink) {
		this.ordersLink = ordersLink;
	}

	public Hyperlink getReportsLink() {
		return reportsLink;
	}

	public void setReportsLink(Hyperlink reportsLink) {
		this.reportsLink = reportsLink;
	}

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
		dockPanel.add(orderList, DockPanel.CENTER);
		buildPageHeaderl();
	}
	public void buildPageHeaderl() {
		HorizontalPanel hyperLinks = new HorizontalPanel();
		productsLink = new Hyperlink("Products", "Products");
		clientsLink = new Hyperlink("Clients", "Clients");
		ordersLink = new Hyperlink("Orders", "Orders");
		reportsLink = new Hyperlink("Reports", "Reports");

		logout = new Hyperlink("Logout", "Logout");
		hyperLinks.add(productsLink);
		hyperLinks.add(clientsLink);
		hyperLinks.add(ordersLink);
		hyperLinks.add(reportsLink);
		hyperLinks.add(logout);

		productsLink.getElement().getStyle().setProperty("padding", "30px");
		productsLink.getElement().getStyle().setProperty("fontSize", "150%");
		
		clientsLink.getElement().getStyle().setProperty("padding", "30px");
		clientsLink.getElement().getStyle().setProperty("fontSize", "150%");		

		ordersLink.getElement().getStyle().setProperty("padding", "30px");
		ordersLink.getElement().getStyle().setProperty("fontSize", "150%");
		
		reportsLink.getElement().getStyle().setProperty("padding", "30px");
		reportsLink.getElement().getStyle().setProperty("fontSize", "150%");	
			
		logout.getElement().getStyle().setProperty("padding", "30px");
		logout.getElement().getStyle().setProperty("fontSize", "150%");
		logout.getElement().setInnerHTML("<a style='color:#511323;' >Logout</a>");
		
		Image image = new Image();
		image.setUrl("http://www.haystackinfotech.com/images/product/inventory.jpg");
		image.setPixelSize(1400, 300);
		
		VerticalPanel header = new VerticalPanel();

		header.add(image);
		header.add(hyperLinks);

		dockPanel.add(header, DockPanel.NORTH);

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
