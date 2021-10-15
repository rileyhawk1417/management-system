package com.schoolAdmin.app;

import java.sql.SQLException;

import com.schoolAdmin.database.Mysql;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

    Mysql database = new Mysql();

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../fxml/login_screen.fxml"));
       

        Scene loginScene = new Scene(root);

        stage.setScene(loginScene);

        stage.setResizable(true);
        stage.setTitle("Welcome Screen");
        stage.show();

    }

    public static void main(String[] args) throws SQLException {
        Mysql.main(args);
        launch();
    }

    public static void hideWindow(Stage stage) throws Exception {
        stage.getScene().getWindow().hide();
    }

    public static void closeApp() {
        Platform.exit();
        System.exit(0);
    }

}
