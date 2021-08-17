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

import com.schoolAdmin.scenes.Records_Display;

import org.w3c.dom.events.MouseEvent;

public class App extends Application {

    Postgres psql = new Postgres();

    @Override
    public void start(Stage stage) {
        String greetMsg = "Welcome to School Administration System";

        TilePane TPane = new TilePane();
        
        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();
       
        TextFlow bannerTxt = new TextFlow();
        Text bannerMsgText = new Text(greetMsg);

        bannerTxt.getChildren().addAll(bannerMsgText);        
        bannerTxt.setTextAlignment(TextAlignment.CENTER);

        /**Labels Section**/
        Label userLabel = new Label("User Name");
        userLabel.setLabelFor(userNameField);
        userLabel.setMnemonicParsing(true);
       
        Label passwordLabel = new Label("Password"); 
        passwordLabel.setLabelFor(passwordField);
        passwordLabel.setMnemonicParsing(true);


        Button loginBtn = new Button("Login");
        loginBtn.setWrapText(true);
        loginBtn.setText("_Enter");
        
        /*
            *TODO: Clear Clunky code 
            *TODO: Add keybindings to some actions
        */
        
        //For users who like to use their mouse

        Records_Display layout1 = new Records_Display();
        Scene scene2 = new Scene(layout1, 840, 550);
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




            //global event listener for enter key to submit
            TPane.addEventHandler(KeyEvent.KEY_PRESSED, subKey);

          






        /**Tile Pane Prefs**/
        TPane.setPrefColumns(1);
        TPane.setPrefRows(3);
        TPane.setTileAlignment(Pos.CENTER);        
        TPane.setPrefTileHeight(25);
        TPane.setPrefTileWidth(200);
        TPane.setPadding(new Insets(10, 10, 0, 0));
        TPane.setHgap(10.0);
        TPane.setVgap(8.0);        
        TPane.getChildren().addAll(bannerTxt, userLabel,userNameField, passwordLabel,passwordField, loginBtn);
        
       /*   TODO: #Find a way to use tile pane and stack pane instead of vbox and hbox
            TODO: # Or just use tile pane only without dealing with vbox and hbox
       */ 
        HBox hBox = new HBox(TPane);
        hBox.setAlignment(Pos.CENTER);
       VBox vBox = new VBox(hBox);
       vBox.setAlignment(Pos.CENTER);
        
       /**Stage Prefs**/ 
       Scene scene = new Scene(vBox, 840, 550);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Welcome Screen");
        stage.show();
    }

    public static void main(String[] args) {
        Postgres.main(args);
        launch();
    }

}





