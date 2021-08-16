package com.schoolAdmin.app;

import java.sql.*;
public class Postgres {
    private final static String db = "jdbc:postgresql://localhost:5432/java_admin";
    private final static String user = "riley";
    private final static String password = "123456789";
    
    public static Connection connector() throws SQLException{
    return DriverManager.getConnection(db, user, password); 
}

public static void getUsers() {
    String QUERY = "SELECT * FROM users";


    try(Connection conn = connector();
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(QUERY) 
    ) {
       res.next();
        System.out.println(res.getString("user_name") + "\t" + res.getString("password"));
    } catch (Exception e) {
        //TODO: handle exception
        System.out.println(e);
    } 
}

public boolean validate(String user, String pass) throws SQLException{

    String QUERY = "SELECT * FROM users WHERE user_name = ? AND password = ? ";

    try(Connection conn = connector();
    
    PreparedStatement pstmt = conn.prepareStatement(QUERY);
    ) {

            pstmt.setString(1, user);

            pstmt.setString(2, pass);
            System.out.println(pstmt);

            ResultSet res = pstmt.executeQuery();

            System.out.println(user + pass);

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

        Postgres sql=new Postgres();
        sql.checkConnection();

    }  
}
