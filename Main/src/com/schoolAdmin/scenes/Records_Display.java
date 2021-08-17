package com.schoolAdmin.scenes;

import javafx.application.Application;
import javafx.application.Platform;
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

public class Records_Display extends VBox {

    private GridPane gp = new GridPane();
    
    private Text msg = new Text("Hello");

    private Button quit = new Button("Quit");



    public Records_Display(){
        student_records();
    }

    private void student_records(){
        VBox gpwrap = new VBox();

        quit.setOnAction(this::cancel);
        // Button.setButtonData(quit, Button.)

        gpwrap.setAlignment(Pos.CENTER);
        gp.add(msg, 0, 0);
        gp.add(quit, 1, 2);
        gpwrap.getChildren().add(gp);

        this.getChildren().add(gpwrap);
        System.out.println(System.getProperty("user.name"));
    }

    private void cancel(ActionEvent e){ 
        System.out.println("Exit Successfully");
        Platform.exit(); }

}
