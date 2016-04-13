/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author c0641903
 */

public class Worm {
    
    // Attributes
    private Player player;
    private String colour;
    private int direction;
    private boolean status;
    private int position;
    private int lastTailPosition;
    private List<Integer> segments;
    
    // Constructors
    public Worm() {
        player = new Player();
        status = false;
        colour = "";
        direction = 0;
        position = 0;
        lastTailPosition = 0;
        segments = new ArrayList<>();
    }
    public Worm(Player player, boolean status, String colour, int direction, int position, int lastTailPosition, List<Integer> segments) {
        this.player = player;
        this.status = status;
        this.colour = colour;
        this.direction = direction;
        this.position = position;
        this.lastTailPosition = lastTailPosition;
        this.segments = segments;
    }

    // Player Getter/Setter
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    // Colour Getter/Setter
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Direction Getter/Setter
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    // Status Getter/Setter
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    // Position Getter/Setter
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    // Last Tail Position Getter/Setter
    public int getLastTailPosition() {
        return lastTailPosition;
    }
    public void setLastTailPosition(int lastTailPosition) {
        this.lastTailPosition = lastTailPosition;
    }

    // Segments Getter/Setter
    public List<Integer> getSegments() {
        return segments;
    }
    public void setSegments(List<Integer> segments) {
        this.segments = segments;
    }
    
    // Misc. Methods
    public void changePosition() {
        position += direction;
    }
    public void changeSize(Pellet p1, Pellet p2, Pellet p3) {
        if (position == p1.getPosition() || position == p2.getPosition() || position == p3.getPosition()) {
            segments.add(lastTailPosition);
        }
    }
    public void changeLastTailPosition() {
        lastTailPosition = segments.get(segments.size() - 1);
    }
    public void changeSegments() {
        int newSegment;
        int currentSegment;
        newSegment = position;
        for (int segment : segments) {
            currentSegment = segments.get(segment);
            segments.set(segment, newSegment);
            newSegment = currentSegment;
        }
    }
    
}
