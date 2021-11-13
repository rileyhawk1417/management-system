package com.schoolAdmin.controllers.cashier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.*;
import javafx.event.*;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

import com.schoolAdmin.app.App;
import com.schoolAdmin.modals.AlertModule;
import com.schoolAdmin.modals.TableModel;
import com.schoolAdmin.database.Sqlite;
import com.schoolAdmin.controllers.misc.SceneCtrl;

public class TableCtrl implements Initializable {
  Stage stage = new Stage();
  Sqlite sqlite = new Sqlite();
  SceneCtrl scene_switcher = new SceneCtrl();
  App app = new App();

  @FXML
  private AnchorPane parent_;

  @FXML
  TableView<TableModel> mysqlTable;

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
  TableColumn<TableModel, String> unitPrice;

  @FXML
  TableColumn<TableModel, String> restock;

  @FXML
  private TextField searchBar;
  
  @FXML
  private Button subQuery;

  @FXML
  private MenuItem refresh;

  @FXML
  private MenuItem exit;

  // @FXML
  // private MenuItem add_rec;
  
  @FXML
  private MenuItem delete_by_id;

  @FXML
  private MenuItem log_out;

  @FXML
  private MenuItem close;

  @FXML
  private MenuItem manual_;

  @FXML
  private MenuItem about_;

  @FXML
  private MenuItem switch_user_;

  TableModel content;
  ObservableList<TableModel> records;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

    assert mysqlTable != null : "Failed to load databaseTable";

    idColmn.setCellValueFactory(new PropertyValueFactory<TableModel, String>("idCol"));
    itemName.setCellValueFactory(new PropertyValueFactory<TableModel, String>("item_name"));
    description.setCellValueFactory(new PropertyValueFactory<TableModel, String>("desc"));
    unitsConsumed.setCellValueFactory(new PropertyValueFactory<TableModel, String>("units_used"));
    unitsLeft.setCellValueFactory(new PropertyValueFactory<TableModel, String>("units_left"));
    unitPrice.setCellValueFactory(new PropertyValueFactory<TableModel, String>("unit_price"));
    restock.setCellValueFactory(new PropertyValueFactory<TableModel, String>("restock"));

    try {
      records = loadTable();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //Set Records
    mysqlTable.setItems(records);
  }


  public static ObservableList<TableModel> loadTable() {

    ObservableList<TableModel> loadList = FXCollections.observableArrayList();

    try {
      Connection con = Sqlite.connector();

      ResultSet res = con.createStatement().executeQuery("SELECT * FROM ace_hardware");

      while (res.next()) {

        loadList.add(new TableModel(
          res.getString("id"),
          res.getString("name"),
          res.getString("detail"),
          res.getString("units_used"),
          res.getString("units_left"),
          res.getString("unit_price"),
          res.getString("restock")));
        }
        System.out.println(loadList.size());

      } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      System.out.println(e.getMessage());
      System.out.println("Error loading Table");

    }
    return loadList;
  }

  public static ObservableList<TableModel> searchDB(String query, Window owner) {
    ObservableList<TableModel> queryList = FXCollections.observableArrayList();
        String search = "select * from ace_hardware WHERE name LIKE '"+query+"%'";
        try (Connection conn = Sqlite.connector();

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
                                res.getString("unit_price"),
                                res.getString("restock")));
                }
               // Error still triggered even if results are found
                // if(!res.next()){
                // AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Failed to load results from DB");

                //     System.out.println("Search failed");
                // }
            } catch (Exception e) {
                AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Nothing matches your search");
                System.out.println(e);
                System.out.println("Nothing found");
            }
        } catch (SQLException e) {
          e.printStackTrace();
            // TODO: handle exception
        }
        return queryList;
    }


  public void deleteRow(){
    ObservableList<TableModel> selectedRow, allRows;
    allRows = mysqlTable.getItems();
    selectedRow = mysqlTable.getSelectionModel().getSelectedItems();

    // String id_ = psqlTable.getSelectionModel().getSelectedItem().getIdCol();
    // System.out.println(id_);
    // mysql.delete_row_by_id(id_);
    selectedRow.forEach(allRows::remove);
  }

  //File actions
  @FXML
  private void delete_by_id_btn(){
    deleteRow();
  }

  @FXML
  private void searchBtn(ActionEvent event){

    Window owner = subQuery.getScene().getWindow();
    try{
      records.removeAll();
      records = searchDB(searchBar.getText(), owner);
      mysqlTable.setItems(records);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  //TODO cannot make static reference to FXML as they would crash program
  @FXML
  private void reloadBtn(ActionEvent event){
    //Clear current view then load results
    records.removeAll();
    records = loadTable();
    mysqlTable.setItems(records);
  }
//User actions
  @FXML
  private void manual_btn(){
    try{
      app.manual_pdf();
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  @FXML
  private void about_btn(){
    parent_.getScene().getWindow().hide();
   scene_switcher.about_scene(); 
  }

  @FXML
  private void switch_user(){
    parent_.getScene().getWindow().hide();
    scene_switcher.decision_scene();
  }

  @FXML
  private void exitBtn(ActionEvent event){
   App.closeApp();
  }
}
