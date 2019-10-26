/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author png99
 */
public class DatabaseConnection {
    public static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=ChatAppDB";
    public static final String DB_USERNAME = "sald";
    public static final String DB_PASSWORD = "admiral";
    static Connection conn = null;
    
    
    public DatabaseConnection() {
    }
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        
        try{
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=ChatAppDB";
            String user="sa";
            String password = "1234";
                try{
                    conn = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
                }catch(Exception e){
                    e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
