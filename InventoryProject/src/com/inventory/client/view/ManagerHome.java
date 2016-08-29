package com.inventory.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
import com.inventory.shared.dto.ProductDTO;
import com.test.entity.Product;

public class ManagerHome extends Composite implements Display {

	private static ManagerHomeUiBinder uiBinder = GWT.create(ManagerHomeUiBinder.class);

	interface ManagerHomeUiBinder extends UiBinder<Widget, ManagerHome> {
	}

	private ManagerHomePresenter presenter;
	private ArrayList<Integer> changedIDs = new ArrayList<>();

	DataGrid<ProductDTO> productList;
	@UiField
	Button btn;
	
	@UiField
	DockPanel dockPanel;

	public ManagerHome() {
		initWidget(uiBinder.createAndBindUi(this));

		productList = new DataGrid<ProductDTO>();
		productList.setSize("500px", "500px");

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

		productList.setColumnWidth(productNameColumn, 40, Unit.PX);
		productList.setColumnWidth(productWeightColumn, 20, Unit.PX);

		productList.addColumn(productNameColumn, "Name");
		productList.addColumn(productWeightColumn, "Weight");
	}

	public void setPresenter(ManagerHomePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setDataGridList(List<ProductDTO> myList) {

		productList.setRowData(myList);
	
		//DockPanel dockPanel = new DockPanel();
		dockPanel.setSpacing(4);
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		dockPanel.add(productList,DockPanel.NORTH);
	

	}


	@Override
	public HasClickHandlers getSaveChangesButton() {
		return btn;
	}

	@Override
	public ArrayList<ProductDTO> getChangedDataGridList() {
		ArrayList<ProductDTO> list = new ArrayList<>(productList.getVisibleItems());
		Window.alert(list.get(2).getName());
		return list;
	}

	@Override
	public ArrayList<Integer> getChangedIds() {
		return changedIDs;
	}

}
