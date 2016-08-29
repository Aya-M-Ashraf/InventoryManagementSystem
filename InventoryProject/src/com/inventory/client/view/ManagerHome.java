package com.inventory.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.ManagerHomePresenter;
import com.inventory.client.presenter.ManagerHomePresenter.Display;
import com.sun.java.swing.plaf.windows.resources.windows;
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

		// columns***********************************************

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
		Column<Product, Date> dateColumn = new Column<Product, Date>(new DatePickerCell()) {

			@Override
			public Date getValue(Product object) {
				return object.getExpiryDate();
			}
		};

		Column<Product, String> productQuantityColumn = new Column<Product, String>(new EditTextCell()) {
			@Override
			public String getValue(Product object) {
				return Integer.toString(object.getQuantity());
			}
		};

		Column<Product, String> productThresholdColumn = new Column<Product, String>(new EditTextCell()) {
			@Override
			public String getValue(Product object) {
				return Integer.toString(object.getThreshold());
			}
		};
		Column<Product, String> activeProductButtonsColumn = new Column<Product, String>(new ButtonCell()) {

			@Override
			public String getValue(Product object) {
				if (object.getStatus() == 0)
					return "Activate";
				else
					return "Deactivate";
			}
		};
		Column<Product, String> deleteProductButtonsColumn = new Column<Product, String>(new ButtonCell()) {

			@Override
			public String getValue(Product object) {
				return "Delete";
			}
		};
		Column<Product, String> thresholdAlarmColumn = new Column<Product, String>(new ImageCell()) {

			@Override
			public String getValue(Product object) {
				if (object.getThresholdAlarm() == 0)
					return "";
				else
					return "http://www.healthsafetytest.co.uk/guide/wp-content/uploads/2012/03/photodune-941549-blank-danger-and-hazard-triangle-warning-sign-isolated-macro-xs.jpg";
			}
		};
		Column<Product, String> expiryAlarmColumn = new Column<Product, String>(new ImageCell()) {

			@Override
			public String getValue(Product object) {
				if (object.getExpiryAlarm() == 0)
					return "";
				else
					return "http://www.healthsafetytest.co.uk/guide/wp-content/uploads/2012/03/photodune-941549-blank-danger-and-hazard-triangle-warning-sign-isolated-macro-xs.jpg";
			}
		};
		// fieldUpdaters******************************************
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
		dateColumn.setFieldUpdater(new FieldUpdater<Product, Date>() {

			@Override
			public void update(int index, Product object, Date value) {
				object.setExpiryDate(value);

			}
		});

		productQuantityColumn.setFieldUpdater(new FieldUpdater<Product, String>() {
			@Override
			public void update(int index, Product object, String value) {
				object.setQuantity(Integer.parseInt(value));
			}
		});

		productThresholdColumn.setFieldUpdater(new FieldUpdater<Product, String>() {
			@Override
			public void update(int index, Product object, String value) {
				object.setThreshold(Integer.parseInt(value));
			}
		});
		activeProductButtonsColumn.setFieldUpdater(new FieldUpdater<Product, String>() {

			@Override
			public void update(int index, Product object, String value) {
				/*
				 * CON.remove(object); dataGrid.setRowData(CON);
				 */
				if (value.equals("Activate")) {
					object.setStatus((byte) 1);
				} else if (value.equals("Deactivate")) {
					object.setStatus((byte) 0);
				}
				productList.redraw();

			}
		});
		deleteProductButtonsColumn.setFieldUpdater(new FieldUpdater<Product, String>() {

			@Override
			public void update(int index, Product object, String value) {
				/*
				 * CON.remove(object); dataGrid.setRowData(CON);
				 * dataGrid.redraw();
				 */
				Window.alert("You removed: " + object.getName());
			}
		});

		// dataGrid***********************************************
		productList = new DataGrid<Product>();
		productList.setSize("1000px", "500px");

		productList.setColumnWidth(productNameColumn, 40, Unit.PX);
		productList.setColumnWidth(productWeightColumn, 40, Unit.PX);
		productList.setColumnWidth(dateColumn, 50, Unit.PX);
		productList.setColumnWidth(productQuantityColumn, 30, Unit.PX);
		productList.setColumnWidth(productThresholdColumn, 30, Unit.PX);
		productList.setColumnWidth(activeProductButtonsColumn, 40, Unit.PX);
		productList.setColumnWidth(thresholdAlarmColumn, 40, Unit.PX);
		productList.setColumnWidth(expiryAlarmColumn, 40, Unit.PX);
		productList.setColumnWidth(deleteProductButtonsColumn, 30, Unit.PX);

		productList.addColumn(productNameColumn, "Name");
		productList.addColumn(productWeightColumn, "Weight");
		productList.addColumn(dateColumn, "Expiry Date");
		productList.addColumn(productQuantityColumn, "Quantity");
		productList.addColumn(productThresholdColumn, "Threshold");
		productList.addColumn(activeProductButtonsColumn, "Activation");
		productList.addColumn(thresholdAlarmColumn, "Threshold Warning");
		productList.addColumn(expiryAlarmColumn, "Expiry Warning");
		productList.addColumn(deleteProductButtonsColumn, "");
		
		
	}

	public void setPresenter(ManagerHomePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setDataGridList(List<Product> myList) {

		productList.setRowData(myList);

		DockPanel dockPanel = new DockPanel();
		dockPanel.setSpacing(4);
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		ScrollPanel scrollPanel = new ScrollPanel(productList);
		// scrollPanel.setSize("100px", "100px");
		dockPanel.add(scrollPanel, DockPanel.NORTH);
		RootPanel.get("dataGrid").add(dockPanel);

	}


}
