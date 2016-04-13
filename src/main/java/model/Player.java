/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.websocket.Session;

/**
 *
 * @author c0641903
 */

public class Player {
    
    // Attributes
    private String name;
    private String hashedPassword;
    private int wins;
    private int losses;
    private double winLossRatio;
    private Session session;
    
    // Constructors
    public Player() {
        name = "";
        hashedPassword = "";
        wins = 0;
        losses = 0;
        winLossRatio = 0;
    }
    public Player(String name, String hashedPassword, int wins, int losses, double winLossRatio) {
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.wins = wins;
        this.losses = losses;
        this.winLossRatio = winLossRatio;
    }

    // Name Getter/Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Hashed Password Getter/Setter
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    
    // Wins Getter/Setter
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }

    // Losses Getter/Setter
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }

    // Win/Loss Ratio Getter/Setter
    public double getWinLossRatio() {
        return winLossRatio;
    }
    public void setWinLossRatio(double winLossRatio) {
        this.winLossRatio = winLossRatio;
    }
    
    // Misc. Methods
    public void changeWins() {
        wins += 1;
    }
    public void changeLosses() {
        losses += 1;
    }
    public void changeWinLossRatio() {
        winLossRatio = wins / losses;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
    
}
