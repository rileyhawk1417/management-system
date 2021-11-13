package com.schoolAdmin.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
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
import com.schoolAdmin.database.Sqlite;
import com.schoolAdmin.controllers.misc.SceneCtrl;
// import com.schoolAdmin.controllers.admin.UpdateCtrl;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import java.nio.file.*;

/*
* This file handles most of the table operations
* From presenting the data to manipulation
*/

public class TableCtrl implements Initializable {
  Stage stage = new Stage();
  Sqlite sqlite = new Sqlite();
  SceneCtrl scene_switcher = new SceneCtrl();
  // UpdateCtrl update;
  Window owner = stage.getOwner();
  App app = new App();

  /*
  *Table View and Table Column 
  */
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
  private TextField side_unit_price_entry;


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
  private MenuItem export_current_view;
  
  @FXML
  private MenuItem delete_by_id_context;

  @FXML
  private MenuItem export_view;

  @FXML
  private MenuItem close;

  @FXML
  private MenuItem export_btn;
  
  @FXML
  private MenuItem export_view_btn;

  @FXML
  private MenuItem reload_;

  @FXML
  private VBox sideBar_1;

  @FXML
  private VBox sideBar_2;

  TableModel table = null;
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
      table_click_Listener();
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

   
   public void deleteRow(){
    ObservableList<TableModel> selectedRow, allRows;
    allRows = mysqlTable.getItems();
    selectedRow = mysqlTable.getSelectionModel().getSelectedItems();

    String id_ = mysqlTable.getSelectionModel().getSelectedItem().getIdCol();
    System.out.println(id_);
    sqlite.delete_row_by_id(id_);
    selectedRow.forEach(allRows::remove); 
  }

  //File actions
  @FXML
  private void add_screen(){
    addScreen();
  }
  
  /*
  *Reference methods or reminders
  * to show that when dealing with private values
  * they cant be passed to another class
  //TODO Method 1
  // update.setField("150", "25", "3", "4", "5");
  
  //TODO Method 2
  // UpdateCtrl update = new UpdateCtrl("1", "2", "3", "4", "5");
  */
  
  /*
  * Method 3 or function table_click_listener
  * This method listens for a selected record then
  * displays it on the side panel for update.
  */

  public void table_click_Listener(){
    mysqlTable.getSelectionModel().selectedItemProperty().addListener((obs, old_selection, new_selection) -> { 
      side_id_entry.setText(new_selection.getIdCol());
      side_name_entry.setText(new_selection.getItem_name());
      side_detail_entry.setText(new_selection.getDesc());
      side_units_used_entry.setText(new_selection.getUnits_used());
      side_units_left_entry.setText(new_selection.getUnits_left());
      side_unit_price_entry.setText(new_selection.getUnit_price()); 
    });
  }


  @FXML
  private void confirm_update(){
    try{
      sqlite.updateValues(side_name_entry.getText(), side_detail_entry.getText(), side_units_used_entry.getText(), side_units_left_entry.getText(), side_unit_price_entry.getText(), side_restock_entry.getText(), side_id_entry.getText());
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
        side_unit_price_entry.clear();

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
      mysqlTable.setItems(records);
    } catch (Exception e){
      e.printStackTrace();
      AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Action Error", "Unable to complete search");
    }
  }

  //TODO cannot make static reference to FXML as they would crash program
  // @FXML
  private void reloadBtn(){
    //Clear current view then load results
    records.removeAll();
    records = loadTable();
    mysqlTable.setItems(records);
  }

  @FXML
  private void reload(){
    reloadBtn();
  }

  /**
   * 
   * *The methods to export records to excel
   * *ExportExcel.exportToExcel() exports all records in database.
   * *export_tableView() exports the current table view of the records.
   */
  @FXML
  private void export_rec(ActionEvent event) throws IOException{
    Window owner = mysqlTable.getScene().getWindow();
    ExportExcel.exportToExcel(owner);

  }

  @FXML
  private void export_tableView(){
    Workbook wb = new HSSFWorkbook();
    Sheet spreadsheet = wb.createSheet("Current TableView");

    Row row = spreadsheet.createRow(0);

    for(int i=0; i<mysqlTable.getColumns().size(); i++ ) {
      row.createCell(i).setCellValue(mysqlTable.getColumns().get(i).getText());
    }

    for(int x=0; x<mysqlTable.getItems().size(); x++){
      row = spreadsheet.createRow(x+1);
      for(int y=0; y<mysqlTable.getColumns().size(); y++){
        if(mysqlTable.getColumns().get(y).getCellData(x) !=null){
          row.createCell(y).setCellValue(mysqlTable.getColumns().get(y).getCellData(x).toString());
        } else{
          row.createCell(y).setCellValue("");
        }
      }
    }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export Excel Document");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
                );
            try{
                File saveFile = fileChooser.showSaveDialog(owner);
                String savePath = "Exported TableView.xlsx";
                FileOutputStream save_file = new FileOutputStream(savePath);
                fileChooser.setInitialFileName("Exported Table");
                //TODO Set initial filename not working when saving? 
               
                Path src = Paths.get(savePath); 
                Path dest = Paths.get(saveFile.getAbsolutePath());
               
                StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
                StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
                LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

                if(saveFile !=null){
                  try{      
                    //try to save workbook
                    wb.write(save_file);
            
                        //try to copy and delete file
                        Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        Files.delete(src);
                        wb.close();
                      }
                        catch(IOException e){
                          e.getMessage();
                          e.printStackTrace();
                        }
                }
              } catch(FileNotFoundException e){
                  e.getMessage();
                  e.printStackTrace();
                }
    
  }
  
/*
*User actions
*/
@FXML
  private void manual_btn(){
    try {
      app.manual_pdf();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @FXML
  private void about_btn(){
    scene_switcher.about_scene();
  }

  @FXML
  private void switch_user(){
    mysqlTable.getScene().getWindow().hide();
    scene_switcher.decision_scene();
  }

  @FXML
  private void exitBtn(ActionEvent event){
   App.closeApp();
  }
}
