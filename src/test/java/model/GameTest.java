/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author c0640891
 */
public class GameTest {
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getHost method, of class Game.
     */
    @Test
    public void TestGetHostIsEmptyString(){
        Game game = new Game();
        String expResult = "";
        String result = game.getHost();
        assertEquals(expResult, result);
    }
    @Test
    public void TestGetOpponentIsEmptyString(){
        Game game = new Game();
        String expResult = "";
        String result = game.getOpponent();
        assertEquals(expResult, result);
    }
    @Test
    public void TestGetRedWormIsNull(){
        Game game = new Game();
        String expResult = null;
        Worm result = game.getRedWorm();
        assertEquals(expResult, result);

    }
    @Test
    public void TestGetBlueWormIsNull(){
        Game game = new Game();
        String expResult = null;
        Worm result = game.getBlueWorm();
        assertEquals(expResult, result);

    }
    @Test
    public void TestGetPelletOneIsNull(){
        Game game = new Game();
        String expResult = null;
        Pellet result = game.getPellet1();
        assertEquals(expResult, result);

    }
    @Test
    public void TestGetPelletTwoIsNull(){
        Game game = new Game();
        String expResult = null;
        Pellet result = game.getPellet2();
        assertEquals(expResult, result);

    }
    @Test
    public void TestGetPelletThreeIsNull(){
        Game game = new Game();
        String expResult = null;
        Pellet result = game.getPellet3();
        assertEquals(expResult, result);

    }
    
    @Test
    public void TestInputHost(){
        Worm blue = new Worm();
        Worm red = new Worm();
        Pellet one = new Pellet(1);
        Pellet two = new Pellet(2);
        Pellet three = new Pellet(3);
        Game game = new Game("Host", "Opponent", red, blue,one,two,three);
        String expResult = "Host";
        String result = game.getHost();
        assertEquals(expResult, result);
    }
    @Test
    public void TestInputOpponent(){
        Worm blue = new Worm();
        Worm red = new Worm();
        Pellet one = new Pellet(1);
        Pellet two = new Pellet(2);
        Pellet three = new Pellet(3);
        Game game = new Game("Host", "Opponent", red, blue,one,two,three);
        String expResult = "Opponent";
        String result = game.getOpponent();
        assertEquals(expResult, result);
    }
    @Test
    public void TestInputRedWorm(){
        Worm blue = new Worm();
        Worm red = new Worm();
        Pellet one = new Pellet(1);
        Pellet two = new Pellet(2);
        Pellet three = new Pellet(3);
        Game game = new Game("Host", "Opponent", red, blue,one,two,three);
        Worm expResult = red;
        Worm result = game.getRedWorm();
        assertEquals(expResult, result);
    }
    @Test
    public void TestInputBlueWorm(){
        Worm blue = new Worm();
        Worm red = new Worm();
        Pellet one = new Pellet(1);
        Pellet two = new Pellet(2);
        Pellet three = new Pellet(3);
        Game game = new Game("Host", "Opponent", red, blue,one,two,three);
        Worm expResult = blue;
        Worm result = game.getBlueWorm();
        assertEquals(expResult, result);
    }
    @Test
    public void TestInputPelletOne(){
        Worm blue = new Worm();
        Worm red = new Worm();
        Pellet one = new Pellet(1);
        Pellet two = new Pellet(2);
        Pellet three = new Pellet(3);
        Game game = new Game("Host", "Opponent", red, blue,one,two,three);
        Pellet expResult = one;
        Pellet result = game.getPellet1();
        assertEquals(expResult, result);
    }
    @Test
    public void TestInputPelletTwo(){
        Worm blue = new Worm();
        Worm red = new Worm();
        Pellet one = new Pellet(1);
        Pellet two = new Pellet(2);
        Pellet three = new Pellet(3);
        Game game = new Game("Host", "Opponent", red, blue,one,two,three);
        Pellet expResult = two;
        Pellet result = game.getPellet2();
        assertEquals(expResult, result);
    }
    @Test
    public void TestInputPelletThree(){
        Worm blue = new Worm();
        Worm red = new Worm();
        Pellet one = new Pellet(1);
        Pellet two = new Pellet(2);
        Pellet three = new Pellet(3);
        Game game = new Game("Host", "Opponent", red, blue,one,two,three);
        Pellet expResult = three;
        Pellet result = game.getPellet3();
        assertEquals(expResult, result);
    }
    
    
}
