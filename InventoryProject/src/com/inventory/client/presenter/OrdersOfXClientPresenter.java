package com.inventory.client.presenter;

import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasDirectionalText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.view.AllClientsView;
import com.inventory.client.view.OrdersOfXClientView;
import com.inventory.shared.dto.OrderDTO;

public class OrdersOfXClientPresenter implements Presenter {

	public interface Display {
		DataGrid<OrderDTO> getDataGridList();

		HasDirectionalText getLabel();

		HasDirectionalText getLabelUserName();

		void setDataGrid(List<OrderDTO> users);

		Widget asWidget();
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final OrdersOfXClientView view;

	public OrdersOfXClientPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService,
			OrdersOfXClientView view) {

		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.view = view;
	}

	public OrdersOfXClientPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, OrdersOfXClientView view,
			int id) {

		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.view = view;
		bind(id);
	}

	// ==============================================
	public void bind(int id) {

		rpcService.getUserName(id, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				OrdersOfXClientPresenter.this.view.getLabelUserName().setText("Client Name : " + result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		rpcService.getAllOrdersForXClient(id, new AsyncCallback<List<OrderDTO>>() {
			@Override
			public void onSuccess(List<OrderDTO> ordersList) {
				Window.alert("Orders Succes");
				OrdersOfXClientPresenter.this.view.getLabel().setText("Numbers of Orders  are : "

						+ ordersList.size());
				OrdersOfXClientPresenter.this.view.setDataGrid(ordersList);
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Failer");

			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(OrdersOfXClientPresenter.this.view.asWidget());

	}

}
