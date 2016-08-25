package com.inventory.client.view;

import java.util.List;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.ManagerHomePresenter;
import com.inventory.client.presenter.ManagerHomePresenter.Display;
import com.test.entity.Product;

public class ManagerHome extends Composite implements Display {

	private static ManagerHomeUiBinder uiBinder = GWT.create(ManagerHomeUiBinder.class);

	interface ManagerHomeUiBinder extends UiBinder<Widget, ManagerHome> {
	}

	private ManagerHomePresenter presenter;

	DataGrid<Product> productList;
	@UiField
	Button btn;

	public ManagerHome() {
		initWidget(uiBinder.createAndBindUi(this));

		productList = new DataGrid<Product>();
		productList.setSize("500px", "500px");

		Column<Product, String> productNameColumn = new Column<Product, String>(new EditTextCell()) {
			@Override
			public String getValue(Product object) {
				return object.getName();
			}
		};

		Column<Product, String> productWeightColumn = new Column<Product, String>(new EditTextCell()) {
			@Override
			public String getValue(Product object) {
				return Double.toString(object.getWeight());
			}
		};

		productNameColumn.setFieldUpdater(new FieldUpdater<Product, String>() {

			@Override
			public void update(int index, Product object, String value) {
				object.setName(value);
			}
		});

		productWeightColumn.setFieldUpdater(new FieldUpdater<Product, String>() {
			@Override
			public void update(int index, Product object, String value) {
				object.setWeight(Double.parseDouble(value));
			}
		});

		productList.setColumnWidth(productNameColumn, 40, Unit.PX);
		productList.setColumnWidth(productWeightColumn, 20, Unit.PX);

		productList.addColumn(productNameColumn, "Name");
		productList.addColumn(productWeightColumn, "Weight");
	}

	public void setPresenter(ManagerHomePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setDataGridList(List<Product> myList) {

		productList.setRowData(myList);
		Window.alert("in set dataGrid with list of size= " + myList.size());
		DockPanel dockPanel = new DockPanel();
		dockPanel.setSpacing(4);
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		dockPanel.add(productList,DockPanel.NORTH);
		
		RootPanel.get("dataGrid").add(dockPanel);
	

	}

}
