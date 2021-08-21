package com.schoolAdmin.app;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.fxml.*;
import java.net.URL;

import com.schoolAdmin.controllers.DependancyInjector;
import com.schoolAdmin.psql.Postgres;
import com.schoolAdmin.scenes.Records_Display;
public class App extends Application {
    
    Postgres psql = new Postgres();
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
/*
TODO: Fix FML loader not sure why it wont load FXML file
* But controller is already invoked in the controller file
*/

        Parent root = FXMLLoader.load(getClass().getResource("../fxml/login_screen.fxml"));
        Scene loginScene = new Scene(root);
        
        /* * This is for Dependancy Injection probably will use it maybe....* /
/*
         Parent root = DependancyInjector.load("../fxml/login_screen.fxml");
         private void setupDepencancyInjector(){
             Callback<Class<?>, Object> controllerFactory = param -> {
                 LoginFXML eventBtn = LoginFXML().subKey();
                 return new Controller(eventBtn);
             };

             DependancyInjector.addInjectionMethod(Controller.class, controllerFactory);
         }
*/
        
        /*
            *TODO: Clear Clunky code 
            *TODO: Add keybindings to some actions
        */
        
        //For users who like to use their mouse



/*
        final EventHandler<KeyEvent> subKey = new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                final String user = userNameField.getText();
                final String pass =passwordField.getText();
                
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
*/

//  TODO: # Fix click mouse event for login button
        // final EventHandler<InputEvent> subClick = new EventHandler<InputEvent>(){
        //     public void handle(InputEvent e){
        //         final String user = userNameField.getText();
        //         final String pass =passwordField.getText();
                
        //         if(e.getEventType() .equals(MouseEvent.AT_TARGET)){ 
        //             try {
        //                 boolean val = psql.validate(user, pass);
        //                 if(val){
        //                     System.out.println("User Found");
        //                     // e.consume();             
        //            } else{
        //                System.out.println("Didnt find user");
        //            }
        //         } catch (SQLException e1) {
        //             // TODO Auto-generated catch block
        //             e1.printStackTrace();
        //         }


        //         } else{
        //             System.out.println("Failed");
        //         }
        //     }
        // };
        // loginBtn.addEventHandler(KeyEvent.KEY_PRESSED, subKey);

        //  Find a way to handle mouse click events
        // loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, subClick);

        
        stage.setScene(loginScene);

        stage.setResizable(true);
        stage.setTitle("Welcome Screen");
        stage.show();

    }
    
    public static void main(String[] args) {
        Postgres.main(args);
        launch();
    }
    public static void hideWindow(Stage stage) throws Exception{
       stage.getScene().getWindow().hide(); 
    }

}





