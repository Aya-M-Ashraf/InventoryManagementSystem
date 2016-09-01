package com.inventory.client.presenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hidden;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.view.ManagerHome;
import com.inventory.shared.dto.InventoryDTO;
import com.inventory.shared.dto.ProductDTO;
import com.inventory.shared.dto.UserDTO;

public class ManagerHomePresenter implements Presenter {

	public interface Display {
		void setDataGridList(List<ProductDTO> myList);
		ArrayList<ProductDTO> getChangedDataGridList();
		HashSet<Integer> getChangedIds();
		HasClickHandlers getSaveChangesButton();
		HasClickHandlers getXmlButton();
		HasClickHandlers getUploadBtn();
		DialogBox getXmlDb();
		FileUpload getFileUpload();
		FormPanel getUploadForm();
		Hidden getUserHidden();
		void setAddedProduct(ProductDTO newProduct);
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final ManagerHome view;
	private final UserDTO user;

	public ManagerHomePresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, ManagerHome view, UserDTO user) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.view = view;
		this.user = user;
		this.view.setPresenter(this);

		rpcService.getAllProducts(new AsyncCallback<List<ProductDTO>>() {
			@Override
			public void onSuccess(List<ProductDTO> result) {
				ManagerHomePresenter.this.view.setDataGridList(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("*********** There is an Error :" + caught.getMessage());
			}
		});

		ManagerHomePresenter.this.view.getSaveChangesButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				saveEditedProducts();
			}

		});
		bind();
	}
	
	void bind(){
		FormPanel form=view.getUploadForm();
		form.setAction("FileUploadServelt");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		view.getUserHidden().setValue(user.getEmail());
		
		view.getXmlButton().addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			view.getXmlDb().center();
		}
	});
		
		view.getUploadBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				view.getUploadForm().submit();
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(ManagerHomePresenter.this.view.asWidget());
	}

	public void saveEditedProducts() {

		rpcService.saveEditedProducts(ManagerHomePresenter.this.view.getChangedDataGridList(),
				ManagerHomePresenter.this.view.getChangedIds(), new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Errrrrooooorrrrrrr !!!! " + caught.getMessage());
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("your changes Updated successfully ");
					}
				});

	}

	public void deleteProduct(ProductDTO product) {
		rpcService.deleteProduct(product, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("You removed your object successfully");
			}
		});
	}

	public void addProduct(ProductDTO newProduct, InventoryDTO inventoryDTO) {
		Window.alert("MH Presenter before add product");
		rpcService.addProduct(newProduct, inventoryDTO, new AsyncCallback<ProductDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ProductDTO result) {
				ManagerHomePresenter.this.view.setAddedProduct(result);
				Window.alert("MH Presenter add product success");
			}
		});
	}
}
