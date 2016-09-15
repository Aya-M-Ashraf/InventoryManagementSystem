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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasDirectionalText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
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
	@UiField
	HTMLPanel myHtmlPanel;

	private Hyperlink productsLink;
	private Hyperlink logout;
	private Hyperlink clientsLink;
	private Hyperlink ordersLink;
	private Hyperlink reportsLink;

	public void setHeader(){
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
		myHtmlPanel.add(header,"imageDiv1");

	}


	public Hyperlink getProductsLink() {
		return productsLink;
	}

	public void setProductsLink(Hyperlink productsLink) {
		this.productsLink = productsLink;
	}

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

	public OrdersOfXClientView() {
		
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
		myHtmlPanel.add(dataGrid,"grid");
		Window.alert("end of set datagrid");
		setHeader();
		
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