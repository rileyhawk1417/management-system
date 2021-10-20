package com.schoolAdmin.controllers;

import javafx.stage.*;
import javafx.scene.*;

public class SceneCtrl {

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
}
