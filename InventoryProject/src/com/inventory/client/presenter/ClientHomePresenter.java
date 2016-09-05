package com.inventory.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.view.ClientHome;
import com.inventory.shared.dto.InventoryDTO;
import com.inventory.shared.dto.OrderDTO;
import com.inventory.shared.dto.ProductDTO;
import com.inventory.shared.dto.UserDTO;

public class ClientHomePresenter implements Presenter {

	public interface Display {
		void setDataGridList(List<ProductDTO> myList);
		ArrayList<ProductDTO> getChangedDataGridList();
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final ClientHome view;
	private final UserDTO user;
	private ProductDTO orderedProduct;

	public ClientHomePresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, ClientHome view,
			UserDTO user) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.view = view;
		this.user = user;
		this.view.setPresenter(this);
		
		Window.alert("Hello, "+user.getUsername()+"role: "+ user.getUserRole().getRole());

		rpcService.getAllActiveProducts(new AsyncCallback<List<ProductDTO>>() {
			@Override
			public void onSuccess(List<ProductDTO> result) {
				ClientHomePresenter.this.view.setDataGridList(result);
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
		container.add(ClientHomePresenter.this.view.asWidget());
	}


	public UserDTO getUser() {
		return user;
	}

	public void setOrderedProduct(ProductDTO product) {
		orderedProduct = product;
		
	}

	public void makeOrder(Number quantity, Date deliveryDate) {
		OrderDTO order = new OrderDTO();
		order.setDeliveryDate(deliveryDate);
		order.setOrderDate(new Date());
		order.setQuantity((int)quantity);
		order.setTotalWeight((int)quantity*(orderedProduct.getWeight()));
		order.setProduct(orderedProduct);
		rpcService.makeOrder(order,user,new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
		});
	}


}