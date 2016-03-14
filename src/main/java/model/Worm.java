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
    private boolean alive;
    private int movement;
    private int position;
    private int prevEndPos;
    private List<Integer> coords = new ArrayList<>();
    
    public void setInitialCoords(int c1, int c2, int c3){
        coords.add(c1);
        coords.add(c2);
        coords.add(c3);
    }
    public String getPosition(){
        return "g" + position;
    }
    public void moveUp(){
        movement = -50;
    }
    public void moveRight(){
        movement = 1;
    }
    public void moveDown(){
        movement = 50;
    }
    public void moveLeft(){
        movement = -1;
    }
    public void move(){
        String positionString = Integer.toString(position);
        // May cause an error.
        if (position < 50 && movement == -50){
            alive = false;
        } else if ((positionString.endsWith("49") || positionString.endsWith("99")) && movement == 1){
            alive = false;
        } else if (position > 2449 && movement == 50){
            alive = false;
        } else if (position % 50 == 0 && movement == -1){
            alive = false;
        } else {
            int coordA = coords.get(0);
            int coordB;
            position += movement;
            coords.set(0, position);
            // May cause an error.
            for (int a = 1; a < coords.size()-1; a++){
                coordB = coords.get(a);
                coords.set(a, coordA);
                coordA = coordB;
            }
            prevEndPos = coordA;
        }
    }
    public String getCoord(int index){
        return "g" + coords.get(index);
    }
    public void grow(){
        coords.add(prevEndPos);
    }
    public boolean getStatus(){
        return alive;
    }
}
