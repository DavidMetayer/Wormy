/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;
import model.Worm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author c0641903
 */
public class Pellet {
    private boolean status;
    private int position;
    private String eatenBy;
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return status;
    }
    public void checkStatus(Worm redWorm, Worm blueWorm) {
        if (redWorm.getPosition() == position) {
            eatenBy = "redWorm";
            status = false;
        } else if (blueWorm.getPosition() == position) {
            eatenBy = "blueWorm";
            status = false;
        }
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(Worm worm1, Worm worm2) {
        Random randomPosition = new Random();
        do {
            position = randomPosition.nextInt(2500);
        } while (worm1.getCoordinates().contains(position) == false &&
                worm2.getCoordinates().contains(position) == false);
    }
    
    public void setEatenBy(String eatenBy) {
        this.eatenBy = eatenBy;
    }
    public String getEatenBy() {
        return eatenBy;
    }
}
