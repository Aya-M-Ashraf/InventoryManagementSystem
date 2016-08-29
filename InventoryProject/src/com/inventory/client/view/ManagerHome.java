package com.inventory.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.presenter.ManagerHomePresenter;
import com.inventory.client.presenter.ManagerHomePresenter.Display;
import com.inventory.shared.dto.ProductDTO;

public class ManagerHome extends Composite implements Display {

	private static ManagerHomeUiBinder uiBinder = GWT.create(ManagerHomeUiBinder.class);

	interface ManagerHomeUiBinder extends UiBinder<Widget, ManagerHome> {
	}

	private ManagerHomePresenter presenter;
	private HashSet<Integer> changedIDs = new HashSet<>();

	DataGrid<ProductDTO> productList;
	@UiField
	Button btn;

	@UiField
	DockPanel dockPanel;

	public ManagerHome() {
		initWidget(uiBinder.createAndBindUi(this));

		productList = new DataGrid<ProductDTO>();
		productList.setSize("1000px", "500px");

		// columns***********************************************

		Column<ProductDTO, String> productNameColumn = new Column<ProductDTO, String>(new EditTextCell()) {
			@Override
			public String getValue(ProductDTO object) {
				return object.getName();
			}
		};
		Column<ProductDTO, String> productWeightColumn = new Column<ProductDTO, String>(new EditTextCell()) {
			@Override
			public String getValue(ProductDTO object) {
				return Double.toString(object.getWeight());
			}
		};
		Column<ProductDTO, Date> dateColumn = new Column<ProductDTO, Date>(new DatePickerCell()) {
			@Override
			public Date getValue(ProductDTO object) {
				return object.getExpiryDate();
			}
		};
		Column<ProductDTO, String> productQuantityColumn = new Column<ProductDTO, String>(new EditTextCell()) {
			@Override
			public String getValue(ProductDTO object) {
				return Integer.toString(object.getQuantity());
			}
		};
		Column<ProductDTO, String> productThresholdColumn = new Column<ProductDTO, String>(new EditTextCell()) {
			@Override
			public String getValue(ProductDTO object) {
				return Integer.toString(object.getThreshold());
			}
		};
		Column<ProductDTO, String> activeProductButtonsColumn = new Column<ProductDTO, String>(new ButtonCell()) {

			@Override
			public String getValue(ProductDTO object) {
				if (object.getStatus() == 0)
					return "Activate";
				else
					return "Deactivate";
			}
		};
		
		Column<ProductDTO, String> deleteProductButtonsColumn = new Column<ProductDTO, String>(new ButtonCell()) {

			@Override
			public String getValue(ProductDTO object) {
				return "Delete";
			}
		};
		Column<ProductDTO, String> thresholdAlarmColumn = new Column<ProductDTO, String>(new ImageCell()) {

			@Override
			public String getValue(ProductDTO object) {
				if (object.getThresholdAlarm() == 0)
					return "";
				else
					return "http://www.healthsafetytest.co.uk/guide/wp-content/uploads/2012/03/photodune-941549-blank-danger-and-hazard-triangle-warning-sign-isolated-macro-xs.jpg";
			}
		};
		Column<ProductDTO, String> expiryAlarmColumn = new Column<ProductDTO, String>(new ImageCell()) {

			@Override
			public String getValue(ProductDTO object) {
				if (object.getExpiryAlarm() == 0)
					return "";
				else
					return "http://www.healthsafetytest.co.uk/guide/wp-content/uploads/2012/03/photodune-941549-blank-danger-and-hazard-triangle-warning-sign-isolated-macro-xs.jpg";
			}
		};

		// fieldUpdaters******************************************
		productNameColumn.setFieldUpdater(new FieldUpdater<ProductDTO, String>() {
			@Override
			public void update(int index, ProductDTO object, String value) {
				object.setName(value);
				changedIDs.add(index);
			}
		});
		productWeightColumn.setFieldUpdater(new FieldUpdater<ProductDTO, String>() {
			@Override
			public void update(int index, ProductDTO object, String value) {
				object.setWeight(Double.parseDouble(value));
				changedIDs.add(index);
			}
		});
		dateColumn.setFieldUpdater(new FieldUpdater<ProductDTO, Date>() {

			@Override
			public void update(int index, ProductDTO object, Date value) {
				object.setExpiryDate(value);
				changedIDs.add(index);

			}
		});
		productQuantityColumn.setFieldUpdater(new FieldUpdater<ProductDTO, String>() {
			@Override
			public void update(int index, ProductDTO object, String value) {
				object.setQuantity(Integer.parseInt(value));
				changedIDs.add(index);
			}
		});
		productThresholdColumn.setFieldUpdater(new FieldUpdater<ProductDTO, String>() {
			@Override
			public void update(int index, ProductDTO object, String value) {
				object.setThreshold(Integer.parseInt(value));
			}
		});
		activeProductButtonsColumn.setFieldUpdater(new FieldUpdater<ProductDTO, String>() {
			@Override
			public void update(int index, ProductDTO object, String value) {
				if (value.equals("Activate")) {
					object.setStatus((byte) 1);
				} else if (value.equals("Deactivate")) {
					object.setStatus((byte) 0);
				}
				productList.redraw();
				changedIDs.add(index);
			}
		});
		deleteProductButtonsColumn.setFieldUpdater(new FieldUpdater<ProductDTO, String>() {
			@Override
			public void update(int index, ProductDTO object, String value) {
				presenter.deleteProduct(object);
				ArrayList<ProductDTO> list = new ArrayList<>(productList.getVisibleItems());
				list.remove(object);
				productList.setRowData(list);
				productList.redraw();
				changedIDs.add(index);
			}
		});

		// dataGrid***********************************************

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
	public void setDataGridList(List<ProductDTO> myList) {

		productList.setRowData(myList);
		dockPanel.setSpacing(4);
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		dockPanel.add(productList, DockPanel.NORTH);
	}

	@Override
	public HasClickHandlers getSaveChangesButton() {
		return btn;
	}

	@Override
	public ArrayList<ProductDTO> getChangedDataGridList() {
		ArrayList<ProductDTO> list = new ArrayList<>(productList.getVisibleItems());
		return list;
	}

	@Override
	public HashSet<Integer> getChangedIds() {
		return changedIDs;
	}
	
}
