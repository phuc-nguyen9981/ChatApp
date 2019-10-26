
import database.UserDAO;
import models.User;
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
public class testconnect {
    public static void main(String[] args) {
        ArrayList<User> l = UserDAO.getAllUser();
        for(int i = 0;i<l.size();i++){
            System.out.println(l.get(i).getEmail());
            System.out.println(l.get(i).getName());
        }
    }
}
