/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author png99
 */
public class GroupChatSocket extends MulticastSocket{

    private int port;
    private InetAddress address;
    
    public GroupChatSocket() throws IOException {
    }

    public GroupChatSocket(int port, String address) throws IOException {
        super(port);
        this.port = port;
        this.address = InetAddress.getByName(address);
        this.joinGroup(this.address);
    }
    
    public void sendMessage(String message){
        try {
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, this.address, this.port);
            this.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(GroupChatSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMessage(){
        String message = "";
        try {            
            byte[] data = new byte[1024];
            DatagramPacket rcvPacket = new DatagramPacket(data, data.length);
            this.receive(rcvPacket);  
            message = new String(data).trim();
        } catch (IOException ex) {
            Logger.getLogger(GroupChatSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
    
    public void exitGroup(){
        try {
            this.leaveGroup(address);
        } catch (IOException ex) {
            Logger.getLogger(GroupChatSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
