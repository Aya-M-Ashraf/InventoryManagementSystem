package com.inventory.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.event.ForgetPasswordEvent;
import com.inventory.client.event.LogOutEvent;

public class ForgetPasswordPresenter implements Presenter {

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final Display display;

	public interface Display {
		TextBox getEmail();
		Label getFilpText();
		HasClickHandlers getSendBtn();
		HasClickHandlers getBackToSignIn();
		Widget asWidget();
	}

	public ForgetPasswordPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, Display display) {
		super();
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.display = display;
		bind();
	}

	public void bind() {
		display.getFilpText().getElement().getStyle().setColor("blue");
		display.getEmail().getElement().setAttribute("placeHolder", "Enter Your Email");
		display.getSendBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				onForgetPassword();
			}
		});
		display.getBackToSignIn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogOutEvent());
			}
		});
	}

	private void onForgetPassword() {
		rpcService.forgetPassword(display.getEmail().getValue(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				display.getFilpText().getElement().setInnerText("The new password has been sent to your e-mail address !");
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
