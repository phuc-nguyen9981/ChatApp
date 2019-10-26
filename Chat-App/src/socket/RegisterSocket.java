/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author png99
 */
public class RegisterSocket extends Socket{
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    public RegisterSocket(String host, int port) throws UnknownHostException, IOException {
        super(host, port);
        this.fromServer = new DataInputStream(this.getInputStream());
        this.toServer = new DataOutputStream(this.getOutputStream());
    }
    
    public int handleRegister(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String fullName = user.getName();
        String email = user.getEmail();
        String gender = user.getGender();
        int res = 0;
        try {
            toServer.writeUTF("register");
            toServer.writeUTF(username);
            toServer.writeUTF(password);
            toServer.writeUTF(fullName);
            toServer.writeUTF(email);
            toServer.writeUTF(gender);
            
            res = fromServer.readInt();
        } catch (IOException ex) {
            Logger.getLogger(RegisterSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
}
