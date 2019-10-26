package database;



import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CongNguyen
 */
public class UserDAO {
    public static ArrayList getAllUser(){
        ArrayList<User> list_user = new ArrayList<>();
        
        Connection conn = DBConnection.getDBConnection();
        String sql = "select * from user_infor";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                User _u = new User();
                _u.setName(rs.getString("name"));
                _u.setUsername(rs.getString("username"));
                _u.setPassword(rs.getString("password"));
                _u.setEmail(rs.getString("email"));
                _u.setGender(rs.getString("gender"));
                list_user.add(_u);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list_user;
    }
    public static void RegisterAccount(User _u ){
        Connection conn = DBConnection.getDBConnection();
        String sql ="insert into user_infor(name, username, password, email, gender)"
        + " values (?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, _u.getName());
            ps.setString(2, _u.getUsername());
            ps.setString(3, _u.getPassword());
            ps.setString(4, _u.getEmail());
            ps.setString(5, _u.getGender()); // quen ko de gender nvarchar :(
            ps.executeUpdate();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static boolean checkUsername(String _username){
        String sql = "select username from user_infor where username='" + _username +"'";
        Connection conn = DBConnection.getDBConnection();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true; 
    }
}
