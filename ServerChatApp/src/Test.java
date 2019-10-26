
import database.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import model.User;
import database.DatabaseConnection;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author png99
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        ArrayList<User> list = UserDAO.findUser("1234", conn);
        for(User i: list){
            System.out.println(i);
        }
    }
}
