package com.schoolAdmin.app;

import java.sql.SQLException;

import com.schoolAdmin.controllers.misc.SceneCtrl;
import com.schoolAdmin.database.Sqlite;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;

public class App extends Application {

    Stage stage = new Stage();
    Sqlite database = new Sqlite();
    SceneCtrl scene_switcher = new SceneCtrl();

    @Override
    public void start(Stage stage) throws Exception {
        // scene_switcher.rec_scene();
           scene_switcher.decision_scene(); 
    }

    public static void main(String[] args) throws SQLException {
        Sqlite.main(args);
        launch();
    }

    public static void hideWindow(Stage stage) throws Exception {
        stage.getScene().getWindow().hide();
    }

    public void manual_pdf(){
        PDFViewer pdf_ = new PDFViewer();
        try {
            pdf_.loadPDF(getClass().getResource("/resources/pdf/sample.pdf"));
            Scene pdf_manual = new Scene(pdf_);
            stage.setScene(pdf_manual);
            stage.show();
        } catch (PDFException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void closeApp() {
        Platform.exit();
        System.exit(0);
    }

}
