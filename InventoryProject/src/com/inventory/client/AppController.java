package com.inventory.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inventory.client.event.AllClientsEvent;
import com.inventory.client.event.AllClientsHandler;
import com.inventory.client.event.EditProfileEvent;
import com.inventory.client.event.EditProfileHandler;
import com.inventory.client.event.ForgetPasswordEvent;
import com.inventory.client.event.ForgetPasswordHandler;
import com.inventory.client.event.GetOrdersEvent;
import com.inventory.client.event.GetOrdersEventHandler;
import com.inventory.client.event.LogOutEvent;
import com.inventory.client.event.LogOutEventHandler;
import com.inventory.client.event.RegisterEvent;
import com.inventory.client.event.RegisterHandler;
import com.inventory.client.event.RegisterSignin;
import com.inventory.client.event.RegisterSigninHandler;
import com.inventory.client.event.ShowProductsEvent;
import com.inventory.client.event.ShowProductsHandler;
import com.inventory.client.event.SignInEvent;
import com.inventory.client.event.SignInEventHandler;
import com.inventory.client.presenter.ClientHomePresenter;
import com.inventory.client.presenter.AllClientsPresenter;
import com.inventory.client.presenter.EditProfileOPresenter;
import com.inventory.client.presenter.ForgetPasswordPresenter;
import com.inventory.client.presenter.ManagerHomePresenter;
import com.inventory.client.presenter.ManagingOrdersPresenter;
import com.inventory.client.presenter.OrdersOfXClientPresenter;
import com.inventory.client.presenter.Presenter;
import com.inventory.client.presenter.RegisterationPresenter;
import com.inventory.client.presenter.SignInPresenter;
import com.inventory.client.view.ClientHome;
import com.inventory.client.view.AllClientsView;
import com.inventory.client.view.EditProfileView;
import com.inventory.client.view.ForgetPasswordView;
import com.inventory.client.view.ManagerHome;
import com.inventory.client.view.ManagingOrders;
import com.inventory.client.view.OrdersOfXClientView;
import com.inventory.client.view.Registeration;
import com.inventory.client.view.SignInView;
import com.inventory.shared.dto.UserDTO;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private HasWidgets container;
	UserDTO signedInUser = new UserDTO();

	public AppController(GreetingServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(GetOrdersEvent.TYPE, new GetOrdersEventHandler() {

			@Override
			public void onGetOrders(GetOrdersEvent event) {
				History.newItem("ordersHistory");
				Presenter presenter = new OrdersOfXClientPresenter(eventBus, rpcService, new OrdersOfXClientView(),
						signedInUser.getId(), signedInUser);
				presenter.go(container);

			}
		});

		eventBus.addHandler(SignInEvent.TYPE, new SignInEventHandler() {
			@Override
			public void onSignIn(SignInEvent event) {
				if (event.getUser().getUserRole().getRole().equalsIgnoreCase("manager")) {
					Presenter presenter = new ManagerHomePresenter(eventBus, rpcService, new ManagerHome(),
							event.getUser());
					signedInUser = event.getUser();
					// Presenter presenter = new AllClientsPresenter(eventBus,
					// rpcService, new AllClientsView());
					presenter.go(container);
				} else {
					Presenter presenter = new ClientHomePresenter(eventBus, rpcService, new ClientHome(),
							event.getUser());
					signedInUser = event.getUser();
					// Presenter presenter = new AllClientsPresenter(eventBus,
					// rpcService, new AllClientsView());
					presenter.go(container);
				}

			}
		});

		eventBus.addHandler(LogOutEvent.TYPE, new LogOutEventHandler() {
			@Override
			public void onLogOut(LogOutEvent event) {
				Cookies.removeCookie("invSignName");
				Cookies.removeCookie("invSignPass");
				Window.Location.replace("http://localhost:8080/InventoryManagement/");
			}
		});

		eventBus.addHandler(ForgetPasswordEvent.TYPE, new ForgetPasswordHandler() {

			@Override
			public void onForgetPassword(ForgetPasswordEvent event) {
				History.newItem("forgetPassword");
			}
		});

		eventBus.addHandler(EditProfileEvent.TYPE, new EditProfileHandler() {

			@Override
			public void onEditProfile(EditProfileEvent editProfileEvent) {
				History.newItem("editProfile");
			}
		});
		eventBus.addHandler(RegisterEvent.TYPE, new RegisterHandler() {
			@Override
			public void onButtonClicked(RegisterEvent registerevent) {
				doButtonClickedThing();
			}

			private void doButtonClickedThing() {
				History.newItem("register");
			}
		});
		eventBus.addHandler(RegisterSignin.TYPE, new RegisterSigninHandler() {

			@Override
			public void onRegister(RegisterSignin registerSignin) {
				Presenter presenter = new SignInPresenter(eventBus, rpcService, new SignInView());
				presenter.go(container);
			}
		});
		eventBus.addHandler(AllClientsEvent.TYPE, new AllClientsHandler() {

			@Override
			public void onAllClients(AllClientsEvent allClientsEvent) {
				History.newItem("Clients");
				Presenter presenter = new AllClientsPresenter(eventBus, rpcService, new AllClientsView(), signedInUser);
				presenter.go(container);

			}
		});
		eventBus.addHandler(ShowProductsEvent.TYPE, new ShowProductsHandler() {

			@Override
			public void onShowProducts(ShowProductsEvent showProductsEvent) {
				History.newItem("Products");
				Presenter presenter = new ManagerHomePresenter(eventBus, rpcService, new ManagerHome(), signedInUser);
				presenter.go(container);
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
						} else {
							Window.alert("user is not found");
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());

					}
				});
			} else {
				Presenter presenter = new SignInPresenter(eventBus, rpcService, new SignInView());
				presenter.go(container);
			}

		} else {
			/* History.fireCurrentHistoryState(); */
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		Presenter presenter;
		if (token != null) {
			if (token.equals("forgetPassword")) {
				presenter = new ForgetPasswordPresenter(eventBus, rpcService, new ForgetPasswordView());
				presenter.go(container);
			}
			if (token.equals("editProfile")) {
				presenter = new EditProfileOPresenter(eventBus, rpcService, new EditProfileView());
				presenter.go(container);
			}
			if (token.equals("register")) {
				presenter = new RegisterationPresenter(eventBus, rpcService, new Registeration());
				presenter.go(container);
			}

			if (token.equals("Clients")) {
				presenter = new AllClientsPresenter(eventBus, rpcService, new AllClientsView(), signedInUser);
				presenter.go(container);
			}

			if (token.equals("clientProducts")) {
				presenter = new ClientHomePresenter(eventBus, rpcService, new ClientHome(), signedInUser);
				presenter.go(container);
			}

			if (token.equals("Products")) {
				presenter = new ManagerHomePresenter(eventBus, rpcService, new ManagerHome(), signedInUser);
				presenter.go(container);
			}
			if (token.equals("Orders")) {

				presenter = new ManagingOrdersPresenter(eventBus, rpcService, new ManagingOrders());
				presenter.go(container);
			}

			if (token.equals("Logout")) {
				Cookies.removeCookie("invSignName");
				Cookies.removeCookie("invSignPass");
				Window.Location.replace("http://localhost:8080/InventoryManagement/");
			}

		}
	}

}
