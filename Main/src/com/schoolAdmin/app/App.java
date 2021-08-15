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

public class App extends Application {

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
        String a = userNameField.getText();
        String b = passwordField.getText();
        loginBtn.setOnAction(action -> {
            // System.out.println(userNameField.getText() + " " +passwordField.getText());
                  Postgres.authenticate(a, b);
        });
        
        //Gets Username and password when enter key is pressed on password field
        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e){
                if(e.getCode().equals(KeyCode.ENTER)){
           // System.out.println(userNameField.getText() + " " +passwordField.getText());
                  Postgres.authenticate(a, b);
        }      
            }
        });

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





