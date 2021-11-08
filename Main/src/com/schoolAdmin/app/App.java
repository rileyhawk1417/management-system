package com.schoolAdmin.app;

import java.sql.SQLException;

import com.schoolAdmin.controllers.misc.SceneCtrl;
import com.schoolAdmin.database.Mysql;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {

    Mysql database = new Mysql();
    SceneCtrl scene_switcher = new SceneCtrl();

    @Override
    public void start(Stage stage) throws Exception {
        // scene_switcher.rec_scene();
           scene_switcher.login_scene(); 
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
