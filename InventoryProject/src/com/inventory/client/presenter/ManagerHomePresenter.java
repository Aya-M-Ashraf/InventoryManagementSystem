package com.inventory.client.presenter;

import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.view.ManagerHome;
import com.test.entity.Product;


public class ManagerHomePresenter implements Presenter {

	public interface Display {
		DataGrid<Product> getDataGrid();
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
				ManagerHomePresenter.this.view.getDataGrid().addColumn(new TextColumn<Product>() {

					@Override
					public String getValue(Product object) {
						return object.getName();
					}
				});
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
	}

}
