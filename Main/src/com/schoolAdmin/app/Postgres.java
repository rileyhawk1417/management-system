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
    String res = "";

    try(Connection conn = connector();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY) 
    ) {
       rs.next();
        System.out.println(rs.getString("user_name") + "\t" + rs.getString("password"));
    } catch (Exception e) {
        //TODO: handle exception
        System.out.println(e);
    } 
}

public static String authenticate(String a, String b){

    String QUERY = "SELECT user_name FROM users WHERE user_name = ? ";

    try(Connection conn = connector();
        //Statement stmt = conn.createStatement();
        
        ) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY);
            pstmt.setString(1, a);
            //rs.next();
            ResultSet rs = pstmt.executeQuery();
            //rs.next();
            /*
            *TODO: Fix values being passed into authenticator(change variable name)
            *TODO: Clear the clunky code. 
            */
        if(rs.next()){
            boolean registered=false;
            registered=a.equals(rs.getString(1));
            if (registered){
            System.out.println("User is registered");
            }
        } else {
            System.out.println("User Not registered");
        }
       
        System.out.println(rs);
        
    } catch (Exception e) {
      System.out.println(e);
        //TODO: handle exception
    }
    return "Connected";
}

// public String loadTable(){
    //     String QUERY = "SELECT * FROM users";
    
    //     try(Connection conn=connect())
    // }
    public static void main(String[] args){
        String a=""; 
        String b="";
        
        Postgres sql=new Postgres();
        sql.getUsers();
        //sql.authenticate(a, b);
    }  
}

/*
    public static Connection connector() throws SQLException{
    Connection conn=null;
    
    try {
        //Class.forName("org.postgresql.Driver");
        conn=DriverManager.getConnection(db, user, password);
        
        if(conn !=null){
            System.out.println("DB Connection ok!");
        } else{
            System.out.println("Connection Failed");
        }
        
    } catch (SQLException e) {
        //TODO: handle exception
        System.out.println(e.getMessage());
    }
   return conn; 
}

*/


// class loadTable()