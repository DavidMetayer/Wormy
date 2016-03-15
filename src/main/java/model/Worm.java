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
    private boolean status;
    private String colour;
    private int direction;
    private int position;
    private int previousEndPosition;
    private List<Integer> coordinates;
    
    // Constructors
    public Worm() {
        status = true;
        colour = "";
        direction = 0;
        position = 0;
        previousEndPosition = 0;
        coordinates = new ArrayList<>();
    }
    public Worm(boolean status, String colour, int direction, int position, int previousEndPosition, int coord1, int coord2, int coord3) {
        this.status = status;
        this.colour = colour;
        this.direction = direction;
        this.position = position;
        this.previousEndPosition = previousEndPosition;
        coordinates = new ArrayList<>();
        coordinates.add(coord1);
        coordinates.add(coord2);
        coordinates.add(coord3);
    }
    
    // Status Methods
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return status;
    }
    public void checkStatus(Worm otherWorm) {
        String positionString = Integer.toString(position);
        if (position < 50 && direction == -50) {
            status = false;
        } else if ((positionString.endsWith("49") || positionString.endsWith("99")) && direction == 1) {
            status = false;
        } else if (position > 2449 && direction == 50) {
            status = false;
        } else if (position % 50 == 0 && direction == -1) {
            status = false;
        } else if (otherWorm.getCoordinates().contains(position)) {
            status = false;
        } else {
            status = true;
        }
    }
    
    // Colour Methods
    public void setColour(String colour) {
        this.colour = colour;
    }
    public String getColour() {
        return colour;
    }
    
    // Direction Methods
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getDirection(){
        return direction;
    }
    // Either this...
    public void changeDirection(int direction) {
        switch (direction) {
            case 37:
                direction = -1;
                break;
            case 38:
                direction = -50;
                break;
            case 39:
                direction = 1;
                break;
            case 40:
                direction = 50;
                break;
            default:
                break;
        }
    }
    // Or these...
    public void moveUp() {
        direction = -50;
    }
    public void moveRight() {
        direction = 1;
    }
    public void moveDown() {
        direction = 50;
    }
    public void moveLeft() {
        direction = -1;
    }
    
    // Position Methods
    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }
    public void changePosition() {
        int coordA = coordinates.get(0);
        int coordB;
        position += direction;
        coordinates.set(0, position);
        // May cause an error.
        for (int a = 1; a < coordinates.size()-1; a++) {
            coordB = coordinates.get(a);
            coordinates.set(a, coordA);
            coordA = coordB;
        }
        previousEndPosition = coordA;
    }
    
    // PreviousEndPosition Methods
    public void setPreviousEndPosition(int previousEndPosition) {
        this.previousEndPosition = previousEndPosition;
    }
    public int getPreviousEndPosition() {
        return previousEndPosition;
    }
    
    // Coordinates Methods
    public void setCoordinate(int index, int coord) {
        coordinates.set(index, coord);
    }
    public int getCoordinate(int index) {
        return coordinates.get(index);
    }
    public List getCoordinates() {
        return coordinates;
    }
    public void addCoordinate() {
        coordinates.add(previousEndPosition);
    }
}
