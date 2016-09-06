package com.inventory.client.presenter;

import java.util.ArrayList;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.shared.dto.OrderDTO;

public class ManagingOrdersPresenter implements Presenter {

	public interface Display {
		DataGrid<OrderDTO> getOrderList();

		Column<OrderDTO, String> getAcceptOrderColumn();

		Column<OrderDTO, String> getRejectOrderColumn();

		Widget asWidget();
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final Display display;

	public ManagingOrdersPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, Display display) {
		super();
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.display = display;
		bind();
	}

	void bind() {

		rpcService.getAllOrdersForManager(new AsyncCallback<ArrayList<OrderDTO>>() {

			@Override
			public void onSuccess(ArrayList<OrderDTO> result) {
				display.getOrderList().setRowData(result);
				display.getOrderList().redraw();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("1st RPC Failed");
			}
		});

		display.getAcceptOrderColumn().setFieldUpdater(new FieldUpdater<OrderDTO, String>() {

			@Override
			public void update(int index, OrderDTO orderDTO, String value) {
				rpcService.changeOrderStatus(orderDTO, value, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						rpcService.getAllOrdersForManager(new AsyncCallback<ArrayList<OrderDTO>>() {

							@Override
							public void onSuccess(ArrayList<OrderDTO> result) {
								display.getOrderList().setRowData(result);
								display.getOrderList().redraw();
								Window.alert("order accepted");
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("3rd RPC Failed");
							}
						});

					}

					@Override
					public void onFailure(Throwable caught) {	
						Window.alert("2nd RPC Failed");
					}
				});
			}
		});
		
		display.getRejectOrderColumn().setFieldUpdater(new FieldUpdater<OrderDTO, String>() {
			
			@Override
			public void update(int index, OrderDTO orderDTO, String value) {
				rpcService.changeOrderStatus(orderDTO, value, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						rpcService.getAllOrdersForManager(new AsyncCallback<ArrayList<OrderDTO>>() {
							
							@Override
							public void onSuccess(ArrayList<OrderDTO> result) {
								display.getOrderList().setRowData(result);
								display.getOrderList().redraw();
								Window.alert("order rejected");								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("rejected 2nd rpc failed");
								
							}
						});
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("rejected 1st rpc failed");	
						
					}
				});
				
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
