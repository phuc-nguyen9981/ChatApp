/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchatapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import socket.DataServer;
import database.DatabaseConnection;

/**
 *
 * @author png99
 */
public class ServerChatApp extends Application {
    
    private Connection conn;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ServerChatApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerChatApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StackPane root = new StackPane();
        TextArea taDisplay = new TextArea(); 
        DataServer server = new DataServer(conn, taDisplay);
        root.getChildren().add(taDisplay);
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        server.start();
        taDisplay.appendText("Server start at " + new Date() + "\n");
        primaryStage.setOnCloseRequest((e) -> {
            try {
                server.close();
                server.stop();
            } catch (IOException ex) {
                Logger.getLogger(ServerChatApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
