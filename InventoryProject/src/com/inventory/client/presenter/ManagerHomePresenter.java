package com.inventory.client.presenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
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

		DataGrid<ProductDTO> getProductDataGrid();

		HasClickHandlers getDownloadBtn();

		FormPanel getDownloadForm();
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final ManagerHome view;
	private final UserDTO user;

	public ManagerHomePresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, ManagerHome view,
			UserDTO user) {
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

	void bind() {
		FormPanel form = view.getUploadForm();
		form.setAction("FileUploadServelt");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		view.getDownloadForm().setAction("FileUploadServelt");
		view.getDownloadForm().setMethod(FormPanel.METHOD_GET);

		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				// String fileNameFake = view.getFileUpload().getFilename();
				// String[] For_split_Fake = fileNameFake.split("\\");
				//
				String path = user.getEmail() + "\\" + view.getFileUpload().getFilename();

				rpcService.addProductByXML(path, new AsyncCallback<ArrayList<ProductDTO>>() {

					@Override
					public void onSuccess(ArrayList<ProductDTO> result) {
						view.getXmlDb().hide();
						ArrayList<ProductDTO> list = new ArrayList<>(view.getProductDataGrid().getVisibleItems());
						list.addAll(result);
						view.getProductDataGrid().setRowData(list);
						view.getProductDataGrid().redraw();
					}

					@Override
					public void onFailure(Throwable caught) {
						view.getXmlDb().hide();
						Window.alert("product failed");
					}
				});

			}
		});

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

		view.getDownloadBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				view.getDownloadForm().submit();
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(ManagerHomePresenter.this.view.asWidget());
	}

	public void saveEditedProducts() {
		ArrayList<ProductDTO> changedProducts = ManagerHomePresenter.this.view.getChangedDataGridList();
		Boolean canInsert = true;
		for (ProductDTO changedProduct : changedProducts) {
			if (hasInvalidData(changedProduct)) {
				canInsert = false;
			}
		}
		if (canInsert) {
			rpcService.saveEditedProducts(changedProducts, ManagerHomePresenter.this.view.getChangedIds(),
					new AsyncCallback<Void>() {
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
	}

	private boolean hasInvalidData(ProductDTO changedProduct) {
		if (changedProduct.getName().trim().equals("")) {
			ManagerHomePresenter.this.view.setErrorMsg("Product name can't be empty!");
			return true;
		} 
		else if(changedProduct.getWeight()<=0.0){
			ManagerHomePresenter.this.view.setErrorMsg(changedProduct.getName()  + " : Invalid product weight!");
			return true;
		} 
		else if(changedProduct.getThreshold()<0){
			ManagerHomePresenter.this.view.setErrorMsg(changedProduct.getName()   + " : Invalid product threshold!");
			return true;
		} 
		else if(changedProduct.getInventory().getQuantity()<0){
			ManagerHomePresenter.this.view.setErrorMsg(changedProduct.getName()   + " : Invalid product quantity!");
			return true;
		} 
		else if(changedProduct.getInventory().getQuantityForOrder()<=0){
			ManagerHomePresenter.this.view.setErrorMsg(changedProduct.getName()   + " : Invalid product quantity for order!");
			return true;
		} 
		else{
			ManagerHomePresenter.this.view.setErrorMsg("");
			return false;
		}
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
		rpcService.addProduct(newProduct, inventoryDTO, new AsyncCallback<ProductDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ProductDTO result) {
				ArrayList<ProductDTO> list = new ArrayList<>(ManagerHomePresenter.this.view.getChangedDataGridList());
				list.add(result);
				ManagerHomePresenter.this.view.setDataGridList(list);
			}
		});
	}

	public UserDTO getUser() {
		return user;
	}
}
