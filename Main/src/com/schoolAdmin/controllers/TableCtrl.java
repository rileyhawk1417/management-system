package com.schoolAdmin.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.*;
import javafx.event.*;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.Icon;
import com.schoolAdmin.app.App;
import com.schoolAdmin.modals.AlertModule;
import com.schoolAdmin.modals.TableModel;
import com.schoolAdmin.database.mysql;

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
  private TextField searchBar;
  
  @FXML
  private Button printQuery;

  @FXML
  private Button subQuery;
  
  @FXML
  private Button refresh;

  @FXML
  private Button exit;
  
  
  TableModel content;
  ObservableList<TableModel> records;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

    assert psqlTable != null : "Failed to load databaseTable";

    idColmn.setCellValueFactory(new PropertyValueFactory<TableModel, String>("idCol"));
    itemName.setCellValueFactory(new PropertyValueFactory<TableModel, String>("item_name"));
    description.setCellValueFactory(new PropertyValueFactory<TableModel, String>("desc"));
    unitsConsumed.setCellValueFactory(new PropertyValueFactory<TableModel, String>("units_used"));
    unitsLeft.setCellValueFactory(new PropertyValueFactory<TableModel, String>("units_left"));
    restock.setCellValueFactory(new PropertyValueFactory<TableModel, String>("restock"));

    try { 
      records = loadTable();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //Set Records
    psqlTable.setItems(records);
  }

  
  public static ObservableList<TableModel> loadTable() {
    
    ObservableList<TableModel> loadList = FXCollections.observableArrayList();
    
    try {
      Connection con = mysql.connector();
      
      ResultSet res = con.createStatement().executeQuery("SELECT * FROM ace_hardware");
      
      while (res.next()) {

        loadList.add(new TableModel(
          res.getString("id"),
          res.getString("name"), 
          res.getString("detail"),
          res.getString("units_used"), 
          res.getString("units_left"), 
          res.getString("restock")));
        }
        System.out.println(loadList);

      } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      System.out.println(e.getMessage());
      System.out.println("Error loading Table");
    
    }
    return loadList;
  }
    
  public static ObservableList<TableModel> searchDB(String query) {
    ObservableList<TableModel> queryList = FXCollections.observableArrayList();
        String search = "select * from ace_hardware WHERE name LIKE '"+query+"%'"; 
        try (Connection conn = mysql.connector();

            PreparedStatement pstmt = conn.prepareStatement(search);) {
            ResultSet res = pstmt.executeQuery();

                try{
                while(res.next()){
                              queryList.add(new TableModel(
                                res.getString("id"),
                                res.getString("name"), 
                                res.getString("detail"),
                                res.getString("units_used"), 
                                res.getString("units_left"), 
                                res.getString("restock")));
                    System.out.println(res.getString("id") + res.getString("name") + res.getString("detail")); 
                }
                if(!res.next()){
                    System.out.println("Search failed");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Nothing found");
            }
        } catch (SQLException e) {
          e.printStackTrace(); 
            // TODO: handle exception
        }
        return queryList;
    }

  
  @FXML
  private void exitBtn(ActionEvent event){
   App.closeApp(); 
  }

  @FXML
  private void printBtn(ActionEvent event) throws IOException{
    Window owner = psqlTable.getScene().getWindow();
    ExportExcel.exportToExcel(owner);

  }

  
  @FXML
  private void searchBtn(ActionEvent event){
    try{
      records.removeAll();
      records = searchDB(searchBar.getText());
      psqlTable.setItems(records);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @FXML
  private void reloadBtn(ActionEvent event){
    //Clear current view then load results
    records.removeAll();
    records = loadTable();
    psqlTable.setItems(records);
  }
}