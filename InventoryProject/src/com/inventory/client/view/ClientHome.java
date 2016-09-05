package com.inventory.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.ClientHomePresenter;
import com.inventory.client.presenter.ClientHomePresenter.Display;
import com.inventory.shared.dto.ProductDTO;

public class ClientHome extends Composite implements Display, DialogBoxOpener {

	private static ClientHomeUiBinder uiBinder = GWT.create(ClientHomeUiBinder.class);

	interface ClientHomeUiBinder extends UiBinder<Widget, ClientHome> {
	}

	private ClientHomePresenter presenter;
	private HashSet<Integer> changedIDs = new HashSet<>();

	private OrderDialogBox myDialogBox = new OrderDialogBox();

	DataGrid<ProductDTO> productList;

	@UiField
	DockPanel myDockPanel;

	public ClientHome() {
		initWidget(uiBinder.createAndBindUi(this));
		productList = new DataGrid<ProductDTO>();
		productList.setSize("1300px", "500px");

		// columns***********************************************

		Column<ProductDTO, String> productNameColumn = new Column<ProductDTO, String>(new TextCell()) {
			@Override
			public String getValue(ProductDTO object) {
				return object.getName();
			}
		};
		Column<ProductDTO, Number> productWeightColumn = new Column<ProductDTO, Number>(new NumberCell()) {
			@Override
			public Number getValue(ProductDTO object) {
				return object.getWeight();
			}
		};
		Column<ProductDTO, Date> dateColumn = new Column<ProductDTO, Date>(new DateCell()) {
			@Override
			public Date getValue(ProductDTO object) {
				return object.getExpiryDate();
			}
		};
		Column<ProductDTO, String> productQuantityColumn = new Column<ProductDTO, String>(new TextCell()) {
			@Override
			public String getValue(ProductDTO object) {
				return Integer.toString(object.getInventory().getQuantity());
			}
		};
		Column<ProductDTO, String> productQuantityForOrderColumn = new Column<ProductDTO, String>(new TextCell()) {
			@Override
			public String getValue(ProductDTO object) {
				return Integer.toString(object.getInventory().getQuantityForOrder());
			}
		};

		Column<ProductDTO, String> orderProductButtonsColumn = new Column<ProductDTO, String>(new ButtonCell()) {

			@Override
			public String getValue(ProductDTO object) {
				return "Order";
			}
		};

		// fieldUpdaters******************************************
		orderProductButtonsColumn.setFieldUpdater(new FieldUpdater<ProductDTO, String>() {
			@Override
			public void update(int index, ProductDTO object, String value) {
				openDialogBox(object);
			}

		});

		// dataGrid***********************************************

		productList.setColumnWidth(productNameColumn, 50, Unit.PX);
		productList.setColumnWidth(productWeightColumn, 40, Unit.PX);
		productList.setColumnWidth(dateColumn, 60, Unit.PX);
		productList.setColumnWidth(productQuantityColumn, 40, Unit.PX);
		productList.setColumnWidth(productQuantityForOrderColumn, 30, Unit.PX);
		productList.setColumnWidth(orderProductButtonsColumn, 50, Unit.PX);

		productList.addColumn(productNameColumn, "Name");
		productList.addColumn(productWeightColumn, "Weight");
		productList.addColumn(dateColumn, "Expiry Date");
		productList.addColumn(productQuantityColumn, "Quantity");
		productList.addColumn(productQuantityForOrderColumn, "Quantity For Order");
		productList.addColumn(orderProductButtonsColumn, "Make an Order");

	}

	public void setPresenter(ClientHomePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setDataGridList(List<ProductDTO> myList) {
		productList.setRowData(myList);
		myDockPanel.setSpacing(4);
		myDockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		myDockPanel.add(productList, DockPanel.CENTER);
		Image image = new Image();
		image.setUrl("http://www.haystackinfotech.com/images/product/inventory.jpg");
		image.setPixelSize(1400, 300);
		myDockPanel.add(image, DockPanel.NORTH);
	}

	@Override
	public ArrayList<ProductDTO> getChangedDataGridList() {
		ArrayList<ProductDTO> list = new ArrayList<>(productList.getVisibleItems());
		return list;
	}

	@Override
	public void dialogBoxCancelled() {
		// TODO Auto-generated method stub

	}

	private void openDialogBox(ProductDTO product) {
		presenter.setOrderedProduct(product);
		myDialogBox.showDialogBox(this);
	}

	@Override
	public void dialogBoxValidated(Number quantity, Date deliveryDate) {
		/* Window.alert("quantity: "+quantity+" date: "+ deliveryDate); */
		presenter.makeOrder(quantity, deliveryDate);

	}

}
