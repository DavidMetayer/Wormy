/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Random;

/**
 *
 * @author c0641903
 */

public class Pellet {
    
    // Attributes
    private int position;
    
    // Constructors
    public Pellet() {
        position = 0;
    }
    public Pellet(int position) {
        this.position = position;
    }

    // Position Getter/Setter
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    
    // Misc. Methods
    public void changePosition(Worm w1, Worm w2) {
        Random randomPosition = new Random();
        int newPosition;
        do {
            newPosition = randomPosition.nextInt(2500);
        } while (w1.getSegments().contains(newPosition) == false && w2.getSegments().contains(newPosition) == false);
        position = newPosition;
    }
    
}
