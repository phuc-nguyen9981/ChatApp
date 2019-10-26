/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author CongNguyen
 */
public class FriendList {
    private int id;
    private int id_user1,id_user2; //user1 la user hien tai, user2 la ban
    private String name_user1, name_user2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user1() {
        return id_user1;
    }

    public void setId_user1(int id_user1) {
        this.id_user1 = id_user1;
    }

    public int getId_user2() {
        return id_user2;
    }

    public void setId_user2(int id_user2) {
        this.id_user2 = id_user2;
    }

    public String getName_user1() {
        return name_user1;
    }

    public void setName_user1(String name_user1) {
        this.name_user1 = name_user1;
    }

    public String getName_user2() {
        return name_user2;
    }

    public void setName_user2(String name_user2) {
        this.name_user2 = name_user2;
    }
    
    
}
