package com.inventory.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.event.EditProfileEvent;
import com.inventory.client.event.LogOutEvent;

public class EditProfileOPresenter implements Presenter{
	
	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final Display display;
	
	public interface Display{
		TextBox getEmail();
		PasswordTextBox getOldPassword();
		PasswordTextBox getNewPassword();
		HasClickHandlers getUpdateBtn();
		HasClickHandlers getBackToSignIn();
		Label getFlashingTxt();
		Widget asWidget();
	}
	
	
	public EditProfileOPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, Display display) {
		super();
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.display = display;
		bind();
	}
	
	public void bind(){
		display.getEmail().getElement().setAttribute("placeHolder", "Enter Your Email");
		display.getOldPassword().getElement().setAttribute("placeHolder", "Enter Your Old Password");
		display.getNewPassword().getElement().setAttribute("placeHolder", "Enter Your New Password");
		display.getFlashingTxt().getElement().getStyle().setColor("blue");
		display.getUpdateBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				toUpdateProfile();
			}
		});
		
		display.getBackToSignIn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogOutEvent());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
	
	public void toUpdateProfile(){
		rpcService.updateProfile(display.getEmail().getValue(), display.getOldPassword().getValue(), display.getNewPassword().getValue(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				display.getFlashingTxt().getElement().setInnerText("Your Profile hass been Updated !");
				eventBus.fireEvent(new EditProfileEvent());
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}

		});
	}

}
