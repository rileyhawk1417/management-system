package com.schoolAdmin.modals;

import javafx.scene.control.Alert;
import javafx.stage.Window;
/*
* *This module is responsible for pop up messages
*/
public class AlertModule {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String msg){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.initOwner(owner);
        alert.showAndWait();
    }    
}
