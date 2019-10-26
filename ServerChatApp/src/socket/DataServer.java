/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import database.UserDAO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import model.User;

/**
 *
 * @author png99
 */
public class DataServer extends Thread{
    private ServerSocket serverSocket; // server socket
    private Connection conn; // connect to database
    private TextArea taDisplay; // display screen
    private Map<String, Socket> onlineUser;
    
    public DataServer(Connection conn, TextArea ta) {
        this.conn = conn;
        this.taDisplay = ta;
        onlineUser = new HashMap<>();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(8000);
            while(true) {
                Socket client = serverSocket.accept();
                
                Thread handle = new Thread(new HandleClient(client));
                handle.start();
            }   
        
        } catch (IOException ex) {
            Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class HandleClient implements Runnable{

        private Socket socketClient;
        private DataInputStream fromClient = null;
        private DataOutputStream toClient = null;
        public HandleClient(Socket client) {
            this.socketClient = client;
        }
                       
        @Override
        public void run() {
            while(true){
                int end = 0;
                try {
                    fromClient = new DataInputStream(socketClient.getInputStream());
                    toClient = new DataOutputStream(socketClient.getOutputStream());
                    String method = fromClient.readUTF();  
                    System.out.println(method);
                    switch (method) {
                        case "login":
                            handleLogin();
                            break;
                        case "register":
                            handleRegister();
                            break;
                        case "logout":
                            handleLogOut();
                            end = 1;
                            break;
                        case "get-friends-list":
                            handleGetFriendsList();
                            break;
                        case "send-message":
                            handleSendMessage();
                            break;
                        default:
                            break;

                    }

                } catch (IOException ex) {
                    Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(end==1)  break;
            }
        }
        
        public void handleLogin() throws IOException{
            //0 là đăng nhập thành công
            //1 là sai tên đăng nhập hoặc mật khẩu
            //2 là tài khoản đã được sử dụng
            String username = fromClient.readUTF();
            String pass = fromClient.readUTF();
            if(!(onlineUser.get(username) == null) ){
                toClient.writeInt(2);
                return;
            }
            int check = UserDAO.checkUserLogin(username, pass, conn);
            if(check == 0){
                onlineUser.put(username, this.socketClient);
            }
            toClient.writeInt(check);
            toClient.flush();
            taDisplay.appendText("Check login from " + socketClient.getLocalAddress() 
                    + " for user " + username + " status: " + check + "\n");
            taDisplay.appendText("Number of online user: " + onlineUser.size() + "\n");
        }
        public void handleRegister() throws IOException, ClassNotFoundException {
            String userName = fromClient.readUTF();
            String password = fromClient.readUTF();
            String fullName = fromClient.readUTF();
            String email = fromClient.readUTF();
            String gender = fromClient.readUTF();
            int isValid = UserDAO.checkUsername(userName, conn);
            //0 tức là tạo tài khoản thành công            
            //1 có nghĩa là username đã được sử dụng
            //2 có nghĩa là email đã được sử dụng.
            //Các số khác có nghĩa là đã xảy ra lỗi 
            if(isValid == 1){
                toClient.writeInt(1);
                return;
            }
            isValid = UserDAO.checkEmail(email, conn);
            if(isValid == 1){
                toClient.writeInt(2);
                return;
            }
            User user = new User(fullName, email, userName, password, gender);
            isValid = UserDAO.addUser(user, conn);
            toClient.writeInt(isValid);
            taDisplay.appendText("Add user from " + socketClient.getLocalAddress() 
                    + "with username " + userName + "\n");
        }
        
        public void handleLogOut(){
            System.out.println("handle log out");
            try {
                String uname = fromClient.readUTF();

                onlineUser.remove(uname);
                taDisplay.appendText("User " + uname + " logged out.\n");
                taDisplay.appendText("Number of online user: " + onlineUser.size()+ "\n");
            } catch (IOException ex) {
                Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void handleGetFriendsList(){
            try {
                String username = fromClient.readUTF();
                ArrayList<String> friendList = UserDAO.getFriendList(username, conn);
                System.out.println(friendList.size());
                toClient.writeInt(friendList.size());
                for(int i=0; i<friendList.size(); i++){
                    toClient.writeUTF(friendList.get(i));
                }
            } catch (IOException ex) {
                Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        public void handleSendMessage(){
            try {
                String sender = fromClient.readUTF();
                String rcver = fromClient.readUTF();
                String message = fromClient.readUTF();
                Socket rcvSocket = onlineUser.get(rcver);
                DataOutputStream out = new DataOutputStream(rcvSocket.getOutputStream());
                System.out.println(rcvSocket.getPort());
                out.writeUTF("get-message");
                out.writeUTF(sender);
                out.writeUTF(message);
                
            } catch (IOException ex) {
                Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void close() throws IOException{
        if(serverSocket != null && !serverSocket.isClosed()){
            serverSocket.close();
        }
    }
}

