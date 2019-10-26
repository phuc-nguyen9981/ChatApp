package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.SQLException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CongNguyen 
 */
public class DBConnection {
    static Connection conn = null;
    public static Connection getDBConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=User";
            String user="sa";
            String password = "1234";
            try{
                conn=DriverManager.getConnection(url, user, password);
            }catch(Exception e){
                e.printStackTrace();
        }
    }catch(Exception e){
        e.printStackTrace();
    }
        return conn;
    }
}
