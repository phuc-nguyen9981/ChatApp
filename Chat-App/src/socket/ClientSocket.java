/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import User.PersonalChatView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import models.User;

/**
 *
 * @author png99
 */
public class ClientSocket extends Socket{
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private String username;
    private Thread listeningThread;
    public ClientSocket(String host, int port, String username) throws UnknownHostException, IOException {
        super(host, port);
        this.fromServer = new DataInputStream(this.getInputStream());
        this.toServer = new DataOutputStream(this.getOutputStream());
        this.username = username;
    }
    
    public void listening(HashMap<String, PersonalChatView> onlineUser){
        listeningThread = new Thread(){
            @Override            
            public void run() {
                while(true){
                    try {
                        String method = fromServer.readUTF();
                        switch(method){
                            case "get-message":
                                handleGetMessage(onlineUser);
                                break;                        
                            default:
                                break;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }                                   
        };
        this.listeningThread.start();
    }
        
    public int handleLogin(String username, String password){
        int isSuccess = 0;
        try {
            toServer.writeUTF("login");
            toServer.writeUTF(username);
            toServer.writeUTF(password);
            isSuccess = fromServer.readInt();
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }  
   
    public Socket getSocketToServer(String localhost, int port){
        try {
            Socket clientSocket = new Socket(localhost, port);
            System.out.println("get socket success: "+localhost+":"+port);
            return clientSocket;
        } catch (Exception e) {
        }
        return null;
    }
    public void handleLogOut(String username){               
        try {
            System.out.println(this.getLocalPort());
            System.out.println(this.getPort());
            toServer.writeUTF("logout");
            toServer.writeUTF(username);
            System.out.println("handle logout");
            this.listeningThread.stop();
            this.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getListFriend(String username){
        ArrayList<String> listFriend = new ArrayList<>();
        try {
            toServer.writeUTF("get-friends-list");
            toServer.writeUTF(username);
            int n = fromServer.readInt();
            for(int i=0; i<n; i++){
                String tmp = fromServer.readUTF();
                listFriend.add(tmp);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFriend;
    }
    
    public void sendMessage(String sender, String rcver, String message){
        try {
            toServer.writeUTF("send-message");
            toServer.writeUTF(sender);
            toServer.writeUTF(rcver);
            toServer.writeUTF(message);        
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleGetMessage(HashMap<String, PersonalChatView> onlineUser){
        try {
            String sender = fromServer.readUTF();
            String message = fromServer.readUTF();
            PersonalChatView chatView = onlineUser.get(sender);
            System.out.println(onlineUser.size());
            if(chatView == null){                
                chatView = new PersonalChatView(username, sender, this, onlineUser);
                chatView.addChattingUser(sender, chatView);
                chatView.setVisible(true);
            }
            Date time = new Date();
            JTextArea ta = chatView.getTextArea();
            ta.append(sender + "(" + time +")"+ " : " + message + "\n");
            
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
