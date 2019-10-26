/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author png99
 */
public class User {
    private String _username,_password,_name, _email,_gender;
    public User(){
    }

    public User(String _name, String _email, String _username, String _password, String _gender) {
        this._username = _username;
        this._password = _password;
        this._name = _name;
        this._email = _email;
        this._gender = _gender;
    }
    
    
    
    public String getUsername() {
        return _username;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String _gender) {
        this._gender = _gender;
    }

    @Override
    public String toString() {
        return "User{" + "_username=" + _username + ", _password=" + _password + ", _name=" + _name + ", _email=" + _email + ", _gender=" + _gender + '}';
    }
    
}
