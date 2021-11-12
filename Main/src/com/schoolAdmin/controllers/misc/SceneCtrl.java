package com.schoolAdmin.controllers.misc;

import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

/*
* Similar to a cheat script
* To avoid repetition in several files
*/
public class SceneCtrl {
    // TODO not seeing resources folder
    String admin_login = "/resources/fxml/admin/login_screen.fxml";
    String cashier_login = "/resources/fxml/cashier/login_screen.fxml";
    String decision_screen = "/resources/fxml/misc/login_choice.fxml";
    String add_screen = "/resources/fxml/admin/insert_screen.fxml";
    String admin_records = "/resources/fxml/admin/records_view.fxml";
    String cashier_records = "/resources/fxml/cashier/records_view.fxml";
    String greeting_screen = "/resources/fxml/greeting_banner.fxml";
    String bulk_delete_screen = "/resources/fxml/admin/delete_by_name.fxml";
    String about_screen = "/resources/fxml/misc/about.fxml";

    public static void switchScene(Scene scene, boolean truth, String title, boolean option) {
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(truth);
        stage.setTitle(title);
        if (option == true) {
            stage.show();
        } else if (option == false) {
            stage.showAndWait();
        } else {
            System.out.println("Invalid Option");
        }

        // stage.show();

    }
    public void decision_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(decision_screen));
            Scene login = new Scene(root);
            switchScene(login, true, "Select Login Type", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void admin_login_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(admin_login));
            Scene login = new Scene(root);
            switchScene(login, true, "Admin Login", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void cashier_login_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(cashier_login));
            Scene login = new Scene(root);
            switchScene(login, true, "Employee Login", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void admin_rec_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(admin_records));
            Scene records = new Scene(root);
            switchScene(records, true, "View Inventory ", true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void cashier_rec_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(cashier_records));
            Scene records = new Scene(root);
            switchScene(records, true, "View Inventory ", true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void add_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(add_screen));
            Scene add = new Scene(root);
            switchScene(add, true, "Add Record", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void bulk_delete_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(bulk_delete_screen));
            Scene bulk_delete = new Scene(root);
            switchScene(bulk_delete, true, "Delete Records By Name", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void about_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(about_screen));
            Scene about = new Scene(root);
            switchScene(about, true, "About Screen", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
