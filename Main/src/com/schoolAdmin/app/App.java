package com.schoolAdmin.app;

import java.sql.SQLException;

import com.schoolAdmin.database.Mysql;
import com.schoolAdmin.controllers.SceneCtrl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class App extends Application {

    Mysql database = new Mysql();

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../fxml/login_screen.fxml"));

        Scene loginScene = new Scene(root);
        SceneCtrl.switchScene(loginScene, true, "Welcome Screen", false);
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
