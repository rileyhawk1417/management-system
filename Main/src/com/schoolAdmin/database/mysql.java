package com.schoolAdmin.psql;

import java.sql.*;
import com.schoolAdmin.controllers.TableCtrl;
import com.schoolAdmin.modals.TableModel;
public class mysql {
    private final static String db = "jdbc:mysql://localhost:3306/java_data";
    private final static String user = "guest";
    private final static String password = "1234567890";
    
    public static Connection connector() throws SQLException{
    return DriverManager.getConnection(db, user, password); 
}

public static void getUsers() {
    String QUERY = "SELECT * FROM user_login";


    try(Connection conn = connector();
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(QUERY) 
    ) {
       res.next();
        System.out.println(res.getString("username") + "\t" + res.getString("password"));
    } catch (Exception e) {
        //TODO: handle exception
        System.out.println(e);
    } 
}

public boolean validate(String user, String pass) throws SQLException{

    String QUERY = "SELECT * FROM user_login WHERE name = ? AND password = ? ";

    try(Connection conn = connector();
    
    PreparedStatement pstmt = conn.prepareStatement(QUERY);
    ) {

            pstmt.setString(1, user);

            pstmt.setString(2, pass);
           

            ResultSet res = pstmt.executeQuery();
            /*
            *TODO: Fix values being passed into authenticator(change variable name)
            *TODO: Clear the clunky code. 
            */
        if(res.next()){
            return true;
        }
    
    } catch (SQLException e) {
      printSQLException(e);
        //TODO: handle exception
    }
    return false;
}

// public static void getTable() {
//     String QUERY = "SELECT * FROM stockControl";


//     try(Connection conn = connector();
//         Statement stmt = conn.createStatement();
//         ResultSet res = stmt.executeQuery(QUERY) 
//     ) {
     
//      if(res.next()){
//         while(res.next()){
//    //TODO: Print to Table Crl
//     TableCtrl.bringRes(
//         res.getString("id"), 
//         res.getString("name"), 
//         res.getString("detail"), 
//         res.getString("units_consumed"), 
//         res.getString("units_remaining"), 
//         res.getString("restock"), 
//         res.getString("gender"));
//         } 
//      } else{

//          System.out.println(res.next());
//      }
        
//     } catch (Exception e) {
//         //TODO: handle exception
//         System.out.println(e);
//     } 
// }



public static void printSQLException(SQLException ex){
    for (Throwable e: ex){
        if(e instanceof SQLException){
            e.printStackTrace(System.err);
            System.err.println("SQLState: " + ((SQLException) e).getSQLState());
            System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
            System.err.println("Message: " + e.getMessage());
            Throwable t = ex.getCause();
            while(t != null){
                System.out.println("Cause: " + t);
                t = t.getCause();
            }

        }
    }
}

public static Connection checkConnection(){
    Connection conn=null;
    
    try {

        conn=DriverManager.getConnection(db, user, password);
        
        if(conn !=null){
            System.out.println("DB Connection ok!");
        } else{
            System.out.println("Connection Failed");
        }
        
    } catch (Exception e) {
        //TODO: handle exception
        System.out.println(e.getMessage());
    }
   return conn; 
}


    public static void main(String[] args){

        mysql sql=new mysql();
        sql.checkConnection();
        // sql.getTable();

    }  
}
