/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.Session;

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
    public void updateWormDirection(Worm worm, int direction) {
        switch (worm.getColour()) {
            case "red":
                redWorm.setDirection(direction);
                break;
            case "cyan":
                blueWorm.setDirection(direction);
                break;
        }
    }
    public void updateWormStatus(Worm worm) {
        switch (worm.getColour()) {
            case "red":
                redWorm.changeStatus(blueWorm);
                break;
            case "cyan":
                blueWorm.changeStatus(redWorm);
                break;
        }
    }
    public boolean getWormStatus(Worm worm) {
        switch (worm.getColour()) {
            case "red":
                return redWorm.getStatus();
            case "cyan":
                return blueWorm.getStatus();
        }
        return false;
    }
    public void updateWormPosition(Worm worm) {
        switch (worm.getColour()) {
            case "red":
                redWorm.changePosition();
                break;
            case "cyan":
                blueWorm.changePosition();
                break;
        }
    }
    public void updateWormSize(Worm worm) {
        switch (worm.getColour()) {
            case "red":
                if (redWorm.changeSize(pellet1, pellet2, pellet3).equals(pellet1)) {
                    pellet1.changePosition(redWorm, blueWorm);
                } else if (redWorm.changeSize(pellet1, pellet2, pellet3).equals(pellet2)) {
                    pellet2.changePosition(redWorm, blueWorm);
                } else if (redWorm.changeSize(pellet1, pellet2, pellet3).equals(pellet3)) {
                    pellet3.changePosition(redWorm, blueWorm);
                }
                break;
            case "cyan":
                if (blueWorm.changeSize(pellet1, pellet2, pellet3).equals(pellet1)) {
                    pellet1.changePosition(redWorm, blueWorm);
                } else if (blueWorm.changeSize(pellet1, pellet2, pellet3).equals(pellet2)) {
                    pellet2.changePosition(redWorm, blueWorm);
                } else if (blueWorm.changeSize(pellet1, pellet2, pellet3).equals(pellet3)) {
                    pellet3.changePosition(redWorm, blueWorm);
                }
                break;
        }
    }
    public void updateWormLTP(Worm worm) {
        switch (worm.getColour()) {
            case "red":
                redWorm.changeLastTailPosition();
                break;
            case "cyan":
                blueWorm.changeLastTailPosition();
                break;
        }
    }
    public void updateWormSegments(Worm worm) {
        switch (worm.getColour()) {
            case "red":
                redWorm.changeSegments();
                break;
            case "cyan":
                blueWorm.changeSegments();
                break;
        }
    }
    public Worm getWorm(Session session) {
        if (redWorm.getPlayer().getSession().equals(session)) {
            return redWorm;
        }
        if (blueWorm.getPlayer().getSession().equals(session)) {
            return blueWorm;
        }
        return null;
    }
    
    public String toJson() {
        JsonArrayBuilder redSegments = Json.createArrayBuilder();
        JsonArrayBuilder blueSegments = Json.createArrayBuilder();
        for (Integer selectedSegment : redWorm.getSegments()) {
            redSegments.add(selectedSegment);
        }
        for (Integer selectedSegment : blueWorm.getSegments()) {
            blueSegments.add(selectedSegment);
        }
        JsonObject json = Json.createObjectBuilder()
                .add("host", host)
                .add("opponent", opponent)
                .add("redWorm", redSegments.build())
                .add("redWormStatus", redWorm.getStatus())
                .add("redWormDirection", redWorm.getDirection())
                .add("blueWorm", blueSegments.build())
                .add("blueWormStatus", blueWorm.getStatus())
                .add("blueWormDirection", blueWorm.getDirection())
                .add("pellet1", pellet1.getPosition())
                .add("pellet2", pellet2.getPosition())
                .add("pellet3", pellet3.getPosition())
                .build();
        return json.toString();
    }
    
}
