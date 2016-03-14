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
    private int position;
    private List<Integer> coords = new ArrayList<>();
    
    public int moveLeft(){
        if (coords.get(0) % 50 != 0){
            position = coords.get(0) - 1;
        } else {
            position = coords.get(0);
        }
      //  return getPosition(); // Don't worry about this right now. Just planning the logic.
        return 0;
    }
    
    public int moveRight(){
        String positionString = Integer.toString(coords.get(0));
        if(positionString.endsWith("49") != true || positionString.endsWith("99") != true){
            position = coords.get(0) + 1;
        }
        else{
            position = coords.get(0);
        }
        
        //return getPosition(); // Don't worry about this right now. Just planning the logic.
        
        return 0;
    }
    
}
