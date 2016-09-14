	package com.inventory.client.presenter;

	import com.google.gwt.event.dom.client.ClickEvent;
	import com.google.gwt.event.dom.client.ClickHandler;
	import com.google.gwt.event.dom.client.HasClickHandlers;
	import com.google.gwt.event.shared.HandlerManager;
	import com.google.gwt.user.client.Window;
	import com.google.gwt.user.client.rpc.AsyncCallback;
	import com.google.gwt.user.client.ui.HasDirectionalText;
	import com.google.gwt.user.client.ui.HasWidgets;
	import com.google.gwt.user.client.ui.Label;
	import com.google.gwt.user.client.ui.Widget;
	import com.inventory.client.view.Registeration;
	import com.inventory.shared.dto.UserDTO;
	import com.inventory.shared.dto.UserRoleDTO;
	import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.event.ForgetPasswordEvent;
import com.inventory.client.event.LogOutEvent;
import com.inventory.client.event.RegisterEvent;
import com.inventory.client.event.RegisterSignin;
import com.inventory.client.event.SignInEvent;
import com.google.gwt.user.client.ui.TextBox;

	public class RegisterationPresenter implements Presenter{

		public interface Display {
			HasClickHandlers getRegister();
			TextBox getUsername();
			TextBox getEmail();
			TextBox getAddress();
			TextBox getPassword();
			TextBox  getConfirmPassword();
			Label getErrorpass();
			Label getErrorPassword();
			Label getErrorEmail();
			Label getErrorAddress();
			Label getErrorUsername();
			
			Widget asWidget();
		}

		private final HandlerManager eventBus;
		private final GreetingServiceAsync rpcService;
		private final Display view;
	    
		public RegisterationPresenter (HandlerManager eventBus , GreetingServiceAsync rpcService,Display view){
			this.eventBus = eventBus;
			this.rpcService = rpcService;
			this.view = view;
			bind();
		}
		

		
		public void bind (){
			
			view.getRegister().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					if(view.getAddress().getValue().equals("")){
						view.getErrorAddress().setVisible(true);
					}else 
						view.getErrorAddress().setVisible(false);
			 
					if(view.getEmail().getValue().equals("")){
						view.getErrorEmail().setVisible(true);
					}else 
						view.getErrorEmail().setVisible(false);
					

					if(view.getUsername().getValue().equals("")){
						view.getErrorUsername().setVisible(true);
					}else 
						view.getErrorUsername().setVisible(false);
					
					if(view.getPassword().getValue().equals("")){
						view.getErrorPassword().setVisible(true);
					}else 
						view.getErrorPassword().setVisible(false);
					
					if(!view.getPassword().getValue().equals(view.getConfirmPassword(). getValue())){
						view.getErrorpass().setVisible(true);
					}else {
						view.getErrorpass().setVisible(false);
						
					}
					if(!view.getUsername().getValue().equals("")&&!view.getAddress().getValue().equals("") && !view.getEmail().getValue().equals("")&& !view.getPassword().getValue().equals("")&& !view.getConfirmPassword().getValue().equals("") && view.getPassword().getValue().equals(view.getConfirmPassword().getValue()) ){
						UserDTO userdto = new UserDTO();
						userdto.setAddress(view.getAddress().getValue());
						userdto.setEmail(view.getEmail().getValue());
						userdto.setPassword(view.getPassword().getValue());
						userdto.setUsername(view.getUsername().getValue());
						UserRoleDTO userroledto = new UserRoleDTO();
						userroledto.setId(1);
						userdto.setUserRole(userroledto);
						rpcService.addUser(userdto, new AsyncCallback<Void>() {
							
							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
								Window.alert("user entered");
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Window.alert("Failed");
							}
						});
						
								eventBus.fireEvent(new RegisterSignin());
			
					}
					
				}
			});
			
		}
		@Override
		public void go(HasWidgets container) {
			// TODO Auto-generated method stub
			container.clear();
			container.add(this.view.asWidget());
		}
	}

