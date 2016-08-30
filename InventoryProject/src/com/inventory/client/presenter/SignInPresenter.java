package com.inventory.client.presenter;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.event.EditProfileEvent;
import com.inventory.client.event.ForgetPasswordEvent;
import com.inventory.client.event.SignInEvent;
//import com.test.entity.User;
import com.inventory.shared.dto.UserDTO;

public class SignInPresenter implements Presenter {

	public interface Display {
		TextBox getEmail();

		TextBox getPassword();

		HasClickHandlers getSignInBtn();

		HasClickHandlers getForgetAnchor();

		Label getErrorLabel();

		CheckBox getRemeberMe();
		
		HasClickHandlers getEditProfile();

		Widget asWidget();
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final Display display;

	public SignInPresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, Display display) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.display = display;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	UserDTO user = new UserDTO();

	public void bind() {
		display.getSignInBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSignIn();
			}
		});
		display.getForgetAnchor().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ForgetPasswordEvent());
			}
		});
		
		display.getEditProfile().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditProfileEvent());
			}
		});
		
		display.getErrorLabel().setVisible(false);
		display.getEmail().getElement().setAttribute("placeHolder", "Enter Your E-mail");
		display.getPassword().getElement().setAttribute("placeHolder", "Enter Your Password");
	}

	private void doSignIn() {
		user.setEmail(display.getEmail().getValue());
		user.setPassword(display.getPassword().getValue());

		rpcService.signIn(user, new AsyncCallback<UserDTO>() {

			@Override
			public void onSuccess(UserDTO result) {
				if (result != null) {
					if (display.getRemeberMe().getValue()) {
						String mail = result.getEmail();
						String[] For_split_email = mail.split("@");

						int cookieTimeout = 10 * 365 * 24 * 60 * 60;
						String cookieValueMail = For_split_email[0] + "@" + For_split_email[1];
						String cookieNameMail = "invSignName";
						String cookieValuePass = result.getPassword();
						String cookieNamePass = "invSignPass";
						Date expires = new Date((new Date()).getTime() + cookieTimeout);
						Cookies.setCookie(cookieNameMail, cookieValueMail, expires);
						Cookies.setCookie(cookieNamePass, cookieValuePass, expires);
					}
					eventBus.fireEvent(new SignInEvent(result));
				} else {
					display.getErrorLabel().setVisible(true);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});

	}

}
