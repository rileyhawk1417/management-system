package com.schoolAdmin.controllers.admin;

import java.net.URL;
import java.util.ResourceBundle;

import com.schoolAdmin.database.Sqlite;
import com.schoolAdmin.modals.AlertModule;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class Insert_Update implements Initializable {
   Sqlite sqlite = new Sqlite();

   @FXML
   private Button cancel_insert;

   @FXML
   private Button insert_update;

   @FXML
   private TextField id_entry;

   @FXML
   private TextField name_entry;

   @FXML
   private TextField detail_entry;

   @FXML
   private TextField units_used_entry;

   @FXML
   private TextField units_left_entry;

   @FXML
   private TextField unit_price_entry;

   @FXML
   private ChoiceBox<String> choice;

   String options[] = { "True", "False" };
   String checkBox = "";

   ObservableList<String> selection = FXCollections.observableArrayList(options);
   // choice.getSelectionModel().select(Selection);

   @Override
   public void initialize(URL url, ResourceBundle resource) {
      choice.setValue("True");
      choice.setItems(selection);

   }

   @FXML
   private void insert_rec(ActionEvent event) {
      Window owner = insert_update.getScene().getWindow();
      // choiceSelector();
      grabTxt(owner);
   }

   public String choiceSelector() {
      choice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
         public void changed(ObservableValue ov, Number val, Number new_val) {
            System.out.println(choice.getValue());
            checkBox = choice.getValue();
         }

      });
      return checkBox;
   }

   public void grabTxt(Window owner) {
      try {
         if (name_entry.getText().isEmpty() || units_used_entry.getText().isEmpty()
               || units_left_entry.getText().isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Input Error", "Please enter all fields");

         } else {
            sqlite.insertValues(name_entry.getText(), detail_entry.getText(), units_used_entry.getText(),
                  units_left_entry.getText(), unit_price_entry.getText(), checkBox);
            AlertModule.showAlert(Alert.AlertType.INFORMATION, owner, "Record Added", "Record added successfully");
            // TODO cannot auto reload results after entry
            // TODO Reload is done manually after window closes
            // TableCtrl.reloadTable();;
            insert_update.getScene().getWindow().hide();

         }

         // choiceSelector();

      } catch (Exception e) {
         AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Something is broken :(");
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }

   @FXML
   private void cancel(ActionEvent event) {
      cancel_insert.getScene().getWindow().hide();
   }

}
