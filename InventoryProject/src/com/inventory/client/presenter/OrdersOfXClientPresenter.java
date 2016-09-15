package com.inventory.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.inventory.client.event.AllClientsEvent;
import com.inventory.client.event.LogOutEvent;
import com.inventory.client.event.ShowProductsEvent;
import com.inventory.client.view.AllClientsView;
import com.inventory.client.view.OrdersOfXClientView;
import com.inventory.shared.dto.OrderDTO;
import com.inventory.shared.dto.UserDTO;

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
	private final UserDTO userDto;

	public OrdersOfXClientPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, OrdersOfXClientView view,
			UserDTO userDTO) {

		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.view = view;
		userDto = userDTO;
	}

	public OrdersOfXClientPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, OrdersOfXClientView view,
			int id, UserDTO userDTO) {

		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.view = view;
		userDto = userDTO;
		bind(id);
	}

	// ==============================================
	public void bind(int id) {
	/*	rpcService.getUserName(id, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				OrdersOfXClientPresenter.this.view.getLabelUserName().setText("Client Name : " + result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed to get username");

			}
		});
*/
		rpcService.getAllOrdersForXClient(id, new AsyncCallback<List<OrderDTO>>() {
			@Override
			public void onSuccess(List<OrderDTO> ordersList) {
				OrdersOfXClientPresenter.this.view.getLabel().setText("Numbers of Orders  are : "

						+ ordersList.size());
				OrdersOfXClientPresenter.this.view.setDataGrid(ordersList);
				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed to get orders");
			}
		});
		view.getProductsLink().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ShowProductsEvent(userDto));
			}
		});
		view.getLogout().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogOutEvent());

			}
		});
		view.getClientsLink().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AllClientsEvent(userDto));

			}
		});

	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(OrdersOfXClientPresenter.this.view.asWidget());


	}

}