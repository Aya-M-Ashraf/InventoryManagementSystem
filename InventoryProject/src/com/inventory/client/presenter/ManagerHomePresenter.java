package com.inventory.client.presenter;

import java.util.List;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.view.ManagerHome;
import com.test.entity.Product;

public class ManagerHomePresenter implements Presenter {

	public interface Display {
		void setDataGridList(List<Product> myList);
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final ManagerHome view;

	public ManagerHomePresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, ManagerHome view) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.view = view;
		this.view.setPresenter(this);

		rpcService.getAllProducts(new AsyncCallback<List<Product>>() {
			@Override
			public void onSuccess(List<Product> result) {
				ManagerHomePresenter.this.view.setDataGridList(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("*********** There is an Error :" + caught.getMessage());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(ManagerHomePresenter.this.view.asWidget());
	}

}
