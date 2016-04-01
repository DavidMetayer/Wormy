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

public class Game {
    
    // Attributes
    private String host;
    private String opponent;
    private Worm redWorm;
    private Worm blueWorm;
    private Pellet pellet1;
    private Pellet pellet2;
    private Pellet pellet3;

    // Constructors
    public Game() {
        host = "";
        opponent = "";
        redWorm = null;
        blueWorm = null;
        pellet1 = null;
        pellet2 = null;
        pellet3 = null;
    }
    public Game(String host, String opponent, Worm redWorm, Worm blueWorm, Pellet pellet1, Pellet pellet2, Pellet pellet3) {
        this.host = host;
        this.opponent = opponent;
        this.redWorm = redWorm;
        this.blueWorm = blueWorm;
        this.pellet1 = pellet1;
        this.pellet2 = pellet2;
        this.pellet3 = pellet3;
    }

    // Host Getter/Setter
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    // Opponent Getter/Setter
    public String getOpponent() {
        return opponent;
    }
    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }
    
    // Red Worm Getter/Setter
    public Worm getRedWorm() {
        return redWorm;
    }
    public void setRedWorm(Worm redWorm) {
        this.redWorm = redWorm;
    }

    // Blue Worm Getter/Setter
    public Worm getBlueWorm() {
        return blueWorm;
    }
    public void setBlueWorm(Worm blueWorm) {
        this.blueWorm = blueWorm;
    }
    
    // Pellet 1 Getter/Setter
    public Pellet getPellet1() {
        return pellet1;
    }
    public void setPellet1(Pellet pellet1) {
        this.pellet1 = pellet1;
    }
    
    // Pellet 2 Getter/Setter
    public Pellet getPellet2() {
        return pellet2;
    }
    public void setPellet2(Pellet pellet2) {
        this.pellet2 = pellet2;
    }
    
    // Pellet 3 Getter/Setter
    public Pellet getPellet3() {
        return pellet3;
    }
    public void setPellet3(Pellet pellet3) {
        this.pellet3 = pellet3;
    }
    
}
