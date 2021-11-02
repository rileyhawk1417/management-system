package com.schoolAdmin.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.schoolAdmin.database.Mysql;
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
      choiceSelector();
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
      String fname = name_entry.getText();
      String details = detail_entry.getText();
      String unitsUsed = units_used_entry.getText();
      String unitsLeft = units_left_entry.getText();
       
      try {
         if (fname.isEmpty()  || details.isEmpty() || unitsUsed.isEmpty() || unitsLeft.isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Please enter all fields");

         } else {
            Mysql.insertValues(fname, details, unitsUsed, unitsLeft, "");
            AlertModule.showAlert(Alert.AlertType.INFORMATION, owner, "Record Added", "Record added successfully");
            // TODO cannot auto reload results after entry
            // TODO Reload is done manually after window closes
            // TableCtrl.reloadTable();;
            insert_update.getScene().getWindow().hide();

         }


      } catch (Exception e) {
         AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Something is broken :(");
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }

   public void updateRow(Window owner) {
      String fname = name_entry.getText();
      String details = detail_entry.getText();
      String unitsUsed = units_used_entry.getText();
      String unitsLeft = units_left_entry.getText();
       
      try {
         //TODO figure out function to check if txt is empty or not
         if (fname.isEmpty()  && details.isEmpty() && unitsUsed.isEmpty() && unitsLeft.isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Unchanged fields", "Select a field to update");

         } 
         else if (fname.isEmpty()) {
            // AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Unchanged fields", "Select a field to update");
            System.out.println("Field unchanged");
         } else if(details.isEmpty()){
            System.out.println("Field Unchanged");
         } else if(unitsUsed.isEmpty()){
            System.out.println("Field unchanged");
         } else if(unitsLeft.isEmpty()){
            System.out.println("Field unchanged");
           
         }
         
         
            else {
            Mysql.insertValues(fname, details, unitsUsed, unitsLeft, "");
            AlertModule.showAlert(Alert.AlertType.INFORMATION, owner, "Record Added", "Record added successfully");
            // TODO cannot auto reload results after entry
            // TODO Reload is done manually after window closes
            // TableCtrl.reloadTable();;
            insert_update.getScene().getWindow().hide();

         }


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
