package com.schoolAdmin.controllers.misc;

import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;

import javafx.fxml.FXMLLoader;


public class SceneCtrl {
    //TODO not seeing resources folder
  public  static String login_screen = "/resources/fxml/login_screen.fxml";
   public static String add_screen = "/resources/fxml/insert_screen.fxml";
   public static String records_screen = "/resources/fxml/records_view.fxml";
    public static String greeting_screen = "/resources/fxml/greeting_banner.fxml";


    public static void switchScene(Scene scene, boolean truth, String title, boolean option){
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(truth);
        stage.setTitle(title);
        if(option == true){
            stage.show();
        } else if(option == false){
            stage.showAndWait();
        } else{
            System.out.println("Invalid Option");
        }

        // stage.show();

    }

    public void login_scene(){
        try { 
        Parent root = FXMLLoader.load(getClass().getResource(login_screen));
        Scene login = new Scene(root);
        switchScene(login, true, "Login Screen", false);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
    }
    
    public void rec_scene(){ 
    try {
        Parent root = FXMLLoader.load(getClass().getResource(records_screen));
        Scene records = new Scene(root);
        switchScene(records, true, "View Records ", false);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
    }
    
    public void add_scene(){
    try {
        Parent root = FXMLLoader.load(getClass().getResource(add_screen));
        Scene add = new Scene(root);
        switchScene(add, true, "Add Records", false);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
    }
}
