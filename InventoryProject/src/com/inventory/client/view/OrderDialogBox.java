package com.inventory.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.inventory.shared.dto.ProductDTO;

public class OrderDialogBox extends DialogBox  {
    private DialogBoxOpener opener = null;
    private final Button cancelButton = new Button("Cancel");
    private final Button validButton = new Button("Ok");
    private final DateBox myDateBox = new DateBox();
    private final IntegerBox quantityBox = new IntegerBox() ;

    public OrderDialogBox () {
            cancelButton.addClickHandler(new ClickHandler () {
                    @Override
                    public void onClick(final ClickEvent event) {
                            hide();
                            if (opener!=null)
                                    opener.dialogBoxCancelled();
                    }
            });

            validButton.addClickHandler(new ClickHandler () {
                    @Override
                    public void onClick(final ClickEvent event) {
                            hide();
                            if (opener!=null)
                                    opener.dialogBoxValidated(quantityBox.getValue(),myDateBox.getValue());
                    }
            });
            
            DockPanel mainPanel = new DockPanel(); 
            mainPanel.setSize("300px", "200px");
            
            Label title = new Label("Make Order");
            title.getElement().getStyle().setProperty("font-weight", "bold"); 
            title.getElement().getStyle().setProperty("text-align", "center");
            title.getElement().getStyle().setProperty("font-size", "large");
            mainPanel.add(title,DockPanel.NORTH);
            
            HorizontalPanel panel = new HorizontalPanel();
            panel.setSpacing(20);
            panel.add(validButton);
            panel.add(cancelButton);
            panel.getElement().getStyle().setProperty("align-content", "center");
            mainPanel.add(panel,DockPanel.SOUTH);   
     
            VerticalPanel eastPanel = new VerticalPanel();
            eastPanel.setSpacing(20);
            eastPanel.add(quantityBox);
            eastPanel.add(myDateBox);
            mainPanel.add(eastPanel,DockPanel.EAST);
            
            VerticalPanel westPanel = new VerticalPanel();
            westPanel.setSpacing(20);
            westPanel.add(new Label("Quantity: "));
            westPanel.add(new Label("Delievry Date: "));
            mainPanel.add(westPanel,DockPanel.WEST);
            
            setWidget(mainPanel);
    }

    public void showDialogBox (final DialogBoxOpener opener) {
            this.opener = opener;
            // Show the DialogBox
            center ();
    }
}