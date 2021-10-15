package com.schoolAdmin.controllers;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.schoolAdmin.database.mysql;
import com.schoolAdmin.modals.AlertModule;
import com.schoolAdmin.scenes.Records_Display;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginFXML {
    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    @FXML
    private Button subBtn;

    AnchorPane records_viewPane = new AnchorPane();

    Scene layout2 = new Scene(records_viewPane, 800, 800);

    mysql database = new mysql();
    Stage stage = new Stage();
    Records_Display records = new Records_Display();
    Scene scene2 = new Scene(records, 800, 800);

    // * This is just a reminder for when dealing with events
    // final EventHandler<KeyEvent> submit = new EventHandler<KeyEvent>() {
    //     public void handle(KeyEvent e) {
    //         final String user = userField.getText();
    //         final String pass = passField.getText();

    //         if (e.getCode().equals(KeyCode.ENTER)) {
    //             try {
    //                 boolean val = database.validate(user, pass);
    //                 if (val) {
    //                     System.out.println("User Found");
    //                 } else {
    //                     System.out.println("Didnt find user");
    //                 }
    //             } catch (SQLException e1) {
    //                 // TODO Auto-generated catch block
    //                 e1.printStackTrace();
    //             }
    //         } else {
    //             System.out.println("Failed");
    //         }
    //     }
    // };

    @FXML
    private void subKey(ActionEvent event) {
        Window owner = subBtn.getScene().getWindow();
        if (userField.getText().isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "User Name is required");
            return;
        }
        if (passField.getText().isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Password is required");
            return;

        }
        boolean auth;
        try {
            auth = Authenticate();
            if (auth) {
                System.out.println("User Found");

                AlertModule.showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful", "Welcome");
                subBtn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("../fxml/records_view.fxml"));
                Scene layout3 = new Scene(root);
                stage.setScene(layout3);
                stage.setResizable(true);
                stage.setTitle("Records");
                stage.show();
            } else {
                System.out.println("False");
                AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Incorrect Details");
                System.out.println("Didnt find user");

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void initialize() {
        // Doesnt do much system loads either way
        try {
            System.out.println("Fxml Loaded from App.java");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Failed to import FXML");
        }

    }

    private boolean Authenticate() throws Exception {

        boolean val = database.validate(userField.getText(), passField.getText());
        if (val) {
            System.out.println("User Authenticated");
            return true;
        } else {
            System.out.println("Failed To Authenticate");
            return false;
        }

    }
}
