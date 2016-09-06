package com.inventory.client.view;

import java.util.Date;

public interface DialogBoxOpener {
    void dialogBoxValidated (Number quantity,Date deliveryDate);
    void dialogBoxCancelled ();
}
