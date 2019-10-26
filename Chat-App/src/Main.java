
import User.LoginView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.UserDAO;
import java.util.Scanner;
import javax.swing.JFrame;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author png99
 */
public class Main {

    public static void main(String[] args) {
        JFrame login = new LoginView();
        login.setVisible(true);
//        Scanner sc = new Scanner(System.in);
//        String s1 = sc.nextLine();
//        String s2 = sc.nextLine();
//        
//        try {
//            Socket socket = new Socket("localhost", 8000);
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//        
//            dos.writeUTF(s1);
//            dos.writeUTF(s2);
//            dos.flush();
//            
//            boolean res = dis.readBoolean();
//            socket.close();
//            System.out.println(res);
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
