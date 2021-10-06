package com.schoolAdmin.scenes;

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

import com.schoolAdmin.psql.mysql;
import com.schoolAdmin.scenes.Records_Display;


public class Login extends TilePane {
    private TilePane TPane = new TilePane();

        String greetMsg = "Welcome to School Administration System";
       
        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();
       
        TextFlow bannerTxt = new TextFlow();
        Text bannerMsgText = new Text(greetMsg);

        
        
        public Login(){
            loginView();
        }
        
        private void loginView(){
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
        
    }
}
