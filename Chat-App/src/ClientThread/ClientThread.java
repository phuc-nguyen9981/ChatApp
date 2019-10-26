/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientThread;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author CongNguyen
 */
public class ClientThread {
    public final static String Server_address = "localhost";
    public final static int port_number = 9000;
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket(Server_address, port_number);
            System.out.println("Connected:" + socket );
           /* InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();*/
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);
            while(true){
                dos.writeUTF(input.nextLine());
                dos.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
