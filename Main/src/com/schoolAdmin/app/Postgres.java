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
            Statement stmt = conn.createStatement();
            pstmt.setString(1, user);
           // pstmt.setString(2, user);
            pstmt.setString(2, pass);
            System.out.println(pstmt);
            //rs.next();
            ResultSet res = pstmt.executeQuery();
            System.out.println(res.next());
            //rs.next();
            /*
            *TODO: Fix values being passed into authenticator(change variable name)
            *TODO: Clear the clunky code. 
            */
        //         res.next();

        if(res.next()){
            return true;
        }
        //         boolean user=true;
        //         boolean pass=false;
        //         user=user.equals(res.getString("user_name"));
        //         pass=pass.equals(res.getString("password"));
        //     if (user && pass){
        //     System.out.println("User is registered" + user);

        // } else {
        //     System.out.println("User Not registered");
        // }
       
        // System.out.println(res.next() + user);
        
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

// public String loadTable(){
    //     String QUERY = "SELECT * FROM users";
    
    //     try(Connection conn=connect())
    // }

    public static Connection checkConnection(){
    Connection conn=null;
    
    try {
        //Class.forName("org.postgresql.Driver");
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
        String a=""; 
        String b="";
        
        Postgres sql=new Postgres();
        sql.checkConnection();
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