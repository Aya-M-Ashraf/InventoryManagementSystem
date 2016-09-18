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
import com.inventory.client.event.GetOrdersEvent;
import com.inventory.client.event.LogOutEvent;
import com.inventory.client.view.AllClientsView;
import com.inventory.shared.dto.UserDTO;

public class AllClientsPresenter implements Presenter {

	public interface Display {
		DataGrid<UserDTO> getDataGridList();

		void setDataGrid(List<UserDTO> users);

		HasDirectionalText getLabel();

		Widget asWidget();
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final AllClientsView view;
	private final UserDTO userDto;
	final SingleSelectionModel<UserDTO> selectionModel = new SingleSelectionModel<UserDTO>();

	public AllClientsPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, AllClientsView view,
			UserDTO userDTO) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.view = view;
		userDto = userDTO;
		bind();
	}

	// That contain rpc Methods and constructed during constructor
	public void bind() {
		rpcService.getAllUsers(new AsyncCallback<List<UserDTO>>() {

			@Override
			public void onSuccess(List<UserDTO> clientsList) {
				String count = Integer.toString(clientsList.size());

				AllClientsPresenter.this.view.getLabel().setText("Numbers of Clients : " + count);

				AllClientsPresenter.this.view.setDataGrid(clientsList);

				AllClientsPresenter.this.view.getDataGridList().setSelectionModel(selectionModel);
				SelectionChangeEvent.Handler myHandler = new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						// UserDTO selected =
						// selectionModel.getSelectedObject();
						if (selectionModel.getSelectedObject() != null) {
							fireSelectionEvent(selectionModel.getSelectedObject().getId());
						}
					}
				};

				selectionModel.addSelectionChangeHandler(myHandler);

				view.getLogout().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						eventBus.fireEvent(new LogOutEvent());
					}
				});

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failure");

			}
		});

	}

	@Override
	public void go(HasWidgets container) {

		container.clear();
		container.add(AllClientsPresenter.this.view.asWidget());

	}

	private void fireSelectionEvent(int id) {
		GetOrdersEvent event = new GetOrdersEvent(id, userDto);
		eventBus.fireEvent(event);
	}

}