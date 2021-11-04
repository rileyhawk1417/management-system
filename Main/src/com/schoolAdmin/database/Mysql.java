package com.schoolAdmin.database;

import java.sql.*;

public class Mysql {
    private final static String db = "jdbc:mysql://localhost:3306/java_data";
    private final static String user = "guest";
    private final static String password = "1234567890";

    public static Connection connector() throws SQLException {
        return DriverManager.getConnection(db, user, password);
    }

    public boolean check(String search) throws SQLException {
        String QUERY = "SELECT * FROM user_login WHERE name LIKE ?";

        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(QUERY);) {

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
        return false;
    }

    public boolean validate(String user, String pass) throws SQLException {

        String QUERY = "SELECT * FROM user_login WHERE name = ? AND password = ? ";

        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(QUERY);) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
        return false;
    }

    public void searchDB(String query) {
        String search = "select * from ace_hardware WHERE name LIKE '" + query + "%'";
        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(search);) {
            ResultSet res = pstmt.executeQuery();
            try {
                while (res.next()) {
                    System.out.println(res.getString("id") + res.getString("name") + res.getString("detail"));
                }
                if (!res.next()) {
                    System.out.println("Search failed");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Nothing found");
            }
        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
    }

    public void insertValues(String name, String detail, String units_used, String units_left, String restock) {
        String values = "INSERT INTO ace_hardware (name, detail, units_used, units_left, restock) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(values);
            pstmt.setString(1, name);
            pstmt.setString(2, detail);
            pstmt.setString(3, units_used);
            pstmt.setString(4, units_left);
            pstmt.setString(5, restock);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void updateValues( String name, String detail, String units_used, String units_left, String restock, String id){
        String update = "UPDATE ace_hardware SET name= ?, detail = ?, units_used = ?, units_left = ?, restock = ? WHERE id=?";
        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, name);
            pstmt.setString(2, detail);
            pstmt.setString(3, units_used);
            pstmt.setString(4, units_left);
            pstmt.setString(5, restock);
            pstmt.setString(6, id);

            pstmt.execute();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete_row_by_id( String id_){
        String update = "DELETE FROM ace_hardware WHERE id = ? ";
        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, id_);
            
            pstmt.execute();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete_row_by_name( String name_){
        String update = "DELETE FROM ace_hardware WHERE name LIKE '" +name_ + "%'";
       
        try(
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(update);){
            // pstmt.setString(1, name_);
            
            pstmt.executeQuery();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }

            }
        }
    }

    public Connection checkConnection() {
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(db, user, password);

            if (conn != null) {
                System.out.println("DB Connection ok!");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Mysql mysql = new Mysql();
        mysql.checkConnection(); 
    }

}
