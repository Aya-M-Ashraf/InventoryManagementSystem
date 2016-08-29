package com.inventory.client;

import com.gargoylesoftware.htmlunit.Page;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inventory.client.event.ForgetPasswordEvent;
import com.inventory.client.event.ForgetPasswordHandler;
import com.inventory.client.event.LogOutEvent;
import com.inventory.client.event.LogOutEventHandler;
import com.inventory.client.event.SignInEvent;
import com.inventory.client.event.SignInEventHandler;
import com.inventory.client.presenter.ForgetPasswordPresenter;
import com.inventory.client.presenter.Presenter;
import com.inventory.client.presenter.SignInPresenter;
import com.inventory.client.presenter.WelcomePresenter;
import com.inventory.client.view.ForgetPasswordView;
import com.inventory.client.view.SignInView;
import com.inventory.client.view.WelcomeView;
import com.inventory.shared.dto.UserDTO;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private HasWidgets container;

	public AppController(GreetingServiceAsync rpcService, HandlerManager eventBus) {
		System.out.println("***************** in the constructor of the AppController");

		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(SignInEvent.TYPE, new SignInEventHandler() {

			@Override
			public void onSignIn(SignInEvent event) {
				Presenter presenter = new WelcomePresenter(eventBus, rpcService, new WelcomeView(), event.getUser());
				presenter.go(container);
			}
		});

		eventBus.addHandler(LogOutEvent.TYPE, new LogOutEventHandler() {
			@Override
			public void onLogOut(LogOutEvent event) {
				Cookies.removeCookie("invSignName");
				Cookies.removeCookie("invSignPass");
				Window.Location.replace("http://localhost:8085/InventoryManagement/");
			}
		});
		
		eventBus.addHandler(ForgetPasswordEvent.TYPE, new ForgetPasswordHandler() {
			
			@Override
			public void onForgetPassword(ForgetPasswordEvent event) {
				History.newItem("forgetPassword");
			}
		});
	}

	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			String mailCookie = Cookies.getCookie("invSignName");
			String passCookie = Cookies.getCookie("invSignPass");
			if (mailCookie != null) {
				UserDTO userDTO = new UserDTO();
				userDTO.setEmail(mailCookie);
				userDTO.setPassword(passCookie);
				rpcService.signIn(userDTO, new AsyncCallback<UserDTO>() {

					@Override
					public void onSuccess(UserDTO result) {
						if (result != null) {
							eventBus.fireEvent(new SignInEvent(result));
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
			} else {
				Presenter presenter = new SignInPresenter(eventBus, rpcService, new SignInView());
				presenter.go(container);
			}

		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			if (token.equals("forgetPassword")) {
				Presenter presenter = new ForgetPasswordPresenter(eventBus, rpcService, new ForgetPasswordView());
				presenter.go(container);
			}
		}
	}

}
