package com.schoolAdmin.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.event.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.schoolAdmin.app.App;
import com.schoolAdmin.modals.AlertModule;
import com.schoolAdmin.modals.TableModel;
import com.schoolAdmin.database.Mysql;
import com.schoolAdmin.controllers.misc.SceneCtrl;
import com.schoolAdmin.controllers.admin.UpdateCtrl;

public class TableCtrl implements Initializable {
  Stage stage = new Stage();
  Mysql mysql = new Mysql();
  SceneCtrl scene_switcher = new SceneCtrl();
  UpdateCtrl update;
  Window owner = stage.getOwner();

  /*
  *Table View and Table Column 
  */
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

  /*
  *TextFields 
  */
  @FXML
  private TextField searchBar;

  @FXML
  private TextField side_id_entry;

  @FXML
  private TextField side_name_entry;

  @FXML
  private TextField side_detail_entry;

  @FXML
  private TextField side_units_used_entry;

  @FXML
  private TextField side_units_left_entry;

  @FXML
  private TextField side_restock_entry;

  
  /*
  *Buttons 
  */
  
  @FXML
  private Button subQuery;
  
  @FXML
  private Button discard_;

  @FXML
  private Button update_btn;

  /* 
  * Menu Items 
  */

  @FXML
  private MenuItem printQuery;

  @FXML
  private MenuItem refresh;

  @FXML
  private MenuItem exit;

  @FXML
  private MenuItem add_rec;
  
  @FXML
  private MenuItem delete_by_name;

  @FXML
  private MenuItem log_out;

  @FXML
  private MenuItem manual_;

  @FXML
  private MenuItem about_;

  @FXML
  private MenuItem switch_user_;

  @FXML
  private MenuItem update_row;

  @FXML
  private MenuItem update_option;

  @FXML
  private VBox sideBar_1;

  @FXML
  private VBox sideBar_2;

  TableModel table = null;
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
      table_click_Listener();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //Set Records
    psqlTable.setItems(records);
  }


  public static ObservableList<TableModel> loadTable() {

    ObservableList<TableModel> loadList = FXCollections.observableArrayList();

    try {
      Connection con = Mysql.connector();

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
        try (Connection conn = Mysql.connector();

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


  public void addScreen(){
    try {
      scene_switcher.add_scene();
      loadTable();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
   public String id_col; 
   
   public void deleteRow(){
    id_col= psqlTable.getSelectionModel().getSelectedItem().getIdCol();
    ObservableList<TableModel> selectedRow, allRows;
    allRows = psqlTable.getItems();
    selectedRow = psqlTable.getSelectionModel().getSelectedItems();

    String id_ = psqlTable.getSelectionModel().getSelectedItem().getIdCol();
    System.out.println(id_);
    mysql.delete_row_by_id(id_);
    selectedRow.forEach(allRows::remove); 
  }

  public void delete_by_name(){}

  //File actions
  @FXML
  private void add_screen(){
    addScreen();
  }
  
  @FXML
  
    //TODO Method 1
    // update.setField("150", "25", "3", "4", "5");

    //TODO Method 2
    // UpdateCtrl update = new UpdateCtrl("1", "2", "3", "4", "5");
  

  //TODO Method 3 click listener
  public void table_click_Listener(){
    psqlTable.getSelectionModel().selectedItemProperty().addListener((obs, old_selection, new_selection) -> { 
      side_id_entry.setText(new_selection.getIdCol());
      side_name_entry.setText(new_selection.getItem_name());
      side_detail_entry.setText(new_selection.getDesc());
      side_units_used_entry.setText(new_selection.getUnits_used());
      side_units_left_entry.setText(new_selection.getUnits_left()); 
    });
  }


  @FXML
  private void confirm_update(){
    try{
      mysql.updateValues(side_name_entry.getText(), side_detail_entry.getText(), side_units_used_entry.getText(), side_units_left_entry.getText(), "", side_id_entry.getText());
      AlertModule.showAlert(Alert.AlertType.CONFIRMATION, owner, "Action Completed", "Record updated successfully");
      reloadBtn();
    } catch(Exception e){
      AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Failed to complete action", "Failed to update record");
      e.getMessage();
      e.printStackTrace();
    }
  }

  @FXML
  private void discard_changes(){
        side_id_entry.clear();
        side_name_entry.clear();
        side_detail_entry.clear();
        side_units_used_entry.clear();
        side_units_left_entry.clear();

  }

  @FXML
  private void delete_by_id_btn(){
    deleteRow();
  }
  
  @FXML
  private void delete_by_name_btn(){
    scene_switcher.bulk_delete_scene();
  }

  @FXML
  private void searchBtn(ActionEvent event){

    Window owner = subQuery.getScene().getWindow();
    try{
      records.removeAll();
      records = searchDB(searchBar.getText(), owner);
      psqlTable.setItems(records);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  //TODO cannot make static reference to FXML as they would crash program
  // @FXML
  private void reloadBtn(){
    //Clear current view then load results
    records.removeAll();
    records = loadTable();
    psqlTable.setItems(records);
  }
  @FXML
  private void export_rec(ActionEvent event) throws IOException{
    Window owner = psqlTable.getScene().getWindow();
    ExportExcel.exportToExcel(owner);

  }
//TODO find out how to loop values and export current view
//  public void tableView(){
//    int table = psqlTable.getSelectionModel().getTableView().getItems().size();
//     System.out.println(table);
//   }

//   public String returnCol(){
//     return psqlTable.getSelectionModel().getSelectedItem().getIdCol();
//   }

//User actions
  @FXML
  private void manual_btn(){}

  @FXML
  private void about_btn(){
    scene_switcher.about_scene();
  }

  @FXML
  private void log_out_btn(){
    psqlTable.getScene().getWindow().hide();
    scene_switcher.login_scene();
  }

  @FXML
  private void switch_user(){
    psqlTable.getScene().getWindow().hide();
    scene_switcher.login_scene();
  }

  @FXML
  private void exitBtn(ActionEvent event){
   App.closeApp();
  }
}
