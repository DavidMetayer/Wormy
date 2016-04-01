/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author c0641903
 */

public class NewPlayer {
    
    // Attributes
    private String name;
    private String password;
    private String confirmedPassword;

    // Constructors
    public NewPlayer() {
        name = "";
        password = "";
        confirmedPassword = "";
    }
    public NewPlayer(String name, String password, String confirmedPassword, Player player) {
        this.name = name;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    // Name Getter/Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Password Getter/Setter
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Confirmed Password Getter/Setter
    public String getConfirmedPassword() {
        return confirmedPassword;
    }
    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
    
}
