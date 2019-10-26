/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author png99
 */
public class UserDAO {
    
    public UserDAO() {
    }
    
    public static ArrayList<User> getAllUser(Connection conn){
        ArrayList<User> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("SELECT * FROM UserTbl;");
            while(resultSet.next()){
                String fullName = resultSet.getString(1);
                String email = resultSet.getString(2);
                String userName = resultSet.getString(3);
                String password = resultSet.getString(4);
                String gender = resultSet.getString(5);
                
                list.add(new User(userName, password, userName, email, gender));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public static int checkUserLogin(String username, String password, Connection conn){
        String sql = "SELECT * FROM UserTbl WHERE username = ? AND password = ?;";
        int isValid = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet res = statement.executeQuery();
            isValid = res.next()? 0: 1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isValid;
    }
    
    public static int addUser(User _u, Connection conn){
        String sql ="INSERT INTO UserTbl (fullname, email, username, password, gender) VALUES (?, ?, ?, ?, ?);";
        int res = 3;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, _u.getName());
            ps.setString(2, _u.getEmail());
            ps.setString(3, _u.getUsername());
            ps.setString(4, _u.getPassword());
            ps.setString(5, _u.getGender()); 
            ps.executeUpdate();
            res = 0;
        }catch(Exception e){
            e.printStackTrace();
        }    
        return res;
    }
    public static int checkUsername(String uname, Connection conn){
        String sql = "SELECT * FROM UserTbl WHERE username = ?";
        int res = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, uname);
            
            ResultSet result = statement.executeQuery();
            res = result.next()? 1: 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static int checkEmail(String email, Connection conn){
        String sql = "SELECT * FROM UserTbl WHERE email = ?";
        int res = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            
            ResultSet result = statement.executeQuery();
            res = result.next()? 1: 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static ArrayList<String> getFriendList(String username, Connection conn) {
        String sql = "SELECT USERNAME_2 FROM FriendListTbl where USERNAME_1 = ?";
        ArrayList<String> res = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareCall(sql);
            statement.setString(1, username);
            ResultSet set =statement.executeQuery();
            while(set.next()){
                String tmp = set.getString(1);
                
                res.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static ArrayList<User> findUser(String key, Connection conn){
        String sql = "SELECT * FROM UserTbl WHERE USERNAME LIKE ?";
        ArrayList<User> res = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareCall(sql);
            statement.setString(1, "%"+key+"%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String fullName = resultSet.getString(1);
                String email = resultSet.getString(2);
                String userName = resultSet.getString(3);
                String password = resultSet.getString(4);
                String gender = resultSet.getString(5);
                
                res.add(new User(userName, password, userName, email, gender));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
