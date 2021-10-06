package com.schoolAdmin.controllers;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.event.*;

import java.lang.System.Logger;
//import java.awt.TextField;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.stage.Stage;

import com.schoolAdmin.app.App;
import com.schoolAdmin.modals.AlertModule;
import com.schoolAdmin.modals.TableModel;
import com.schoolAdmin.psql.mysql;

public class TableCtrl implements Initializable {

  @FXML
  TableView<TableModel> psqlTable;

  @FXML
  TableColumn<TableModel, String> idColmn;

  @FXML
  TableColumn<TableModel, String> itemName;

  @FXML
  TableColumn<TableModel, String> description;

  @FXML
  TableColumn<TableModel, String> unitsConsumed;

  @FXML
  TableColumn<TableModel, String> unitsLeft;

  @FXML
  TableColumn<TableModel, String> restock;

  @FXML
  private Label sumOwingLabel;

  @FXML
  private Button printQuery;

  @FXML
  private Button subQuery;

  @FXML
  private Button refresh;

  @FXML
  private Button exit;

  @FXML
  Text sumOwing;

  TableModel content;
  ObservableList<TableModel> list1;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    assert psqlTable != null : "Failed to load psqlTable";

    idColmn.setCellValueFactory(new PropertyValueFactory<TableModel, String>("idCol"));
    itemName.setCellValueFactory(new PropertyValueFactory<TableModel, String>("item_name"));
    description.setCellValueFactory(new PropertyValueFactory<TableModel, String>("desc"));
    unitsConsumed.setCellValueFactory(new PropertyValueFactory<TableModel, String>("units_used"));
    unitsLeft.setCellValueFactory(new PropertyValueFactory<TableModel, String>("units_left"));
    restock.setCellValueFactory(new PropertyValueFactory<TableModel, String>("restock"));

    try {
      // loadTable();
      list1 = loadTable();
    } catch (Exception e) {
      e.printStackTrace();
    }
    psqlTable.refresh();
    psqlTable.setItems(list1);
  }

  
  public static ObservableList<TableModel> loadTable() {

    ObservableList<TableModel> oblist = FXCollections.observableArrayList();
    oblist.removeAll();
    try {
      Connection con = mysql.connector();

      ResultSet res = con.createStatement().executeQuery("SELECT * FROM ace_hardware");
    
      while (res.next()) {

        oblist.add(new TableModel(
          res.getString("id"),
          res.getString("name"), 
          res.getString("detail"),
          res.getString("units_used"), 
          res.getString("units_left"), 
          res.getString("restock")));
        }
        System.out.println(oblist);

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      System.out.println(e.getMessage());
      System.out.println("Error loading Table");
    
    }
    return oblist;
  }

  @FXML
  private void exitBtn(ActionEvent event){
   App.closeApp(); 
  }

  // @FXML
  // public static void printBtn(ActionEvent event){}
  // @FXML
  // public static void searchBtn(ActionEvent event){}
  @FXML
  private void reloadBtn(ActionEvent event){
    loadTable();
  }
}
