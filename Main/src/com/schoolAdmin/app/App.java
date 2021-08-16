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
       final String user = userNameField.getText();
       final String pass =passwordField.getText();
       
    //    private void authenticate(final Node keyNode){
    //        final EventHandler<KeyEvent> submit = new EventHandler<KeyEvent>(){
    //            public void enter_Key(final KeyEvent keyEvent){
    //                if(keyEvent.getCode() == KeyCode.ENTER){
    //                    (KeyEvent.getEventType() == KeyEvent.KEY_PRESSED);

    //                    keyEvent.consume();
    //                }
    //            }
    //        };

    //        passwordField.setOnKeyPressed(submit);
    //    }
       
    //    loginBtn.setOnAction(action -> {
    //         // System.out.println(userNameField.getText() + " " +passwordField.getText());
    //               Postgres.validate(user, pass);
    //     });
        
        final EventHandler<KeyEvent> submit = new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                if(e.getCode().equals(KeyCode.ENTER)){
            
            
                  try {
                    psql.validate(user, pass);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                  System.out.println("Retrieved text"); 
                } else{
                    System.out.println("Failed");
                }
            }
        };

        passwordField.setOnKeyPressed(submit);

        //Gets Username and password when enter key is pressed on password field
        // passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() throws SQLException{
        //     @Override
        //     public void handle(KeyEvent e){
        //         if(e.getCode().equals(KeyCode.ENTER)){
          
        //      user=userNameField.getText();
        //      pass=passwordField.getText(); 
        //           Postgres.validate(user, pass);
        //           System.out.println(user+pass);
        // }      
        //     }
        // });

        // public void login(ActionEvent event) throws SQLException{
        //     if(usernameField.getText().isEmpty()){

        //     } 
        // }
//TODO: Rewrite functions and clear clutter

        // public void submitAction(ActionEvent submit) throws SQLException{

        //     boolean flag = psql.validate(user, pass);
    
        //     if(!flag){
        //         System.out.println("Failed to authenticate");
        //     } else{
        //         System.out.println("Authentication Successful");
        //     }
        // }
        

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





