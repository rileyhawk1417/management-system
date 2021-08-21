package com.schoolAdmin.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.*;
import javafx.event.*;
//import java.awt.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.stage.Stage;

import com.schoolAdmin.app.App;
import com.schoolAdmin.modals.AlertModule;
import com.schoolAdmin.psql.Postgres;
import com.schoolAdmin.scenes.Records_Display;

import javafx.scene.layout.*;
import javafx.scene.text.*;

public class LoginFXML{
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

    Postgres psql = new Postgres();
    Stage stage = new Stage();
    Records_Display records = new Records_Display();
    Scene scene2 = new Scene(records, 800, 800);
    App primaryScene = new App(); 
    // boolean val=psql.validate(userField.getText(), passField.getText());

    // @FXML
    // private void confirmAction(ActionEvent event) throws IOException {
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/login_screen.fxml"));
    //     Parent root = loader.load();

    //     Stage stage = new Stage();
    //     stage.setScene(new Scene(root));
    //     stage.setTitle("FXML Window");
    //     stage.show();
    // }

        final EventHandler<KeyEvent> submit = new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                final String user = userField.getText();
                final String pass =passField.getText();
                
                if(e.getCode().equals(KeyCode.ENTER)){ 
                    try {
                        boolean val = psql.validate(user, pass);
                        if(val){
                            System.out.println("User Found");
                            stage.setTitle("Screen 2");
                            stage.setScene(scene2);
                            // e.consume();             
                   } else{
                       System.out.println("Didnt find user");
                   }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                  //System.out.println("Retrieved text"); 
                //It prints retrieved but after printing fail.
                } else{
                    System.out.println("Failed");
                }
            }
        };


        @FXML
        protected void subKey(ActionEvent event){
            Window owner = subBtn.getScene().getWindow();
            if(userField.getText().isEmpty()){
                AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "User Name is required");
                String userName = userField.getText();
                System.out.println(userName);
                return;
            }
            if(passField.getText().isEmpty()){
                AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Password is required");
                String pass = passField.getText();
                System.out.println(pass);
                return;
                
            }
            boolean auth;
            try {
                auth = Authenticate();
                if(auth){   
                    AlertModule.showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful", "Welcome");
                    records.getScene().getWindow();

                    stage.setScene(scene2);
                    // Stage sample = (Stage) records.getScene().getWindow();
                    // stage.setScene(new Scene(new Pane()));
                    // stage.setScene(scene2);
                    // stage.setTitle("Records Window");
                    // stage.show();
                    // primaryScene.hideWindow(stage);
                }
                else {
                    System.out.println("False");
                AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Incorrect Details");
               
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

            // subBtn.addEventHandler(KeyEvent.KEY_PRESSED, submit);            

/* 
TODO: Having trouble loading FXML file whats wrong?
TODO: It ran before so why not running now?
* Are some FXML meant to be called in Main App?
*/
    public void initialize(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/login_screen.fxml"));
        // loader.setController(this);
        // loader.setRoot(this);
        try{
                System.out.println("Fxml Loaded from App.java");
        } catch(Exception e){
            System.out.println(e.getMessage() + "Failed to import FXML");
        }

    }


    private boolean Authenticate() throws Exception{

        Postgres psql = new Postgres();
        boolean val=psql.validate(userField.getText(), passField.getText());
        if(val){
            System.out.println(val + "User Authenticated");
            return true;
        } else {
            System.out.println("Failed To Authenticate");
            return false;
        }

    }
}
