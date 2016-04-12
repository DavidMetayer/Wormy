/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author c0640891
 */
public class ActivePlayerTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    public ActivePlayerTest(){
    
    }
    
    @Before
    public void setup(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void teardown(){
        System.setOut(null);
        System.setErr(null);
        System.setIn(null);
    
    }
    
    @Test
    public void NoActivePlayerPlayerIsNull(){
        ActivePlayer Player = new ActivePlayer();
        Player result = Player.getPlayer();
        String expResult = null;
        assertEquals(expResult, result);
    }
    
    @Test
    public void NoActivePlayerNameIsNull(){
        ActivePlayer Player = new ActivePlayer();
        String result = Player.getName();
        String expResult = "";
        assertEquals(expResult, result);
    }
    @Test
    public void NoActivePlayerPasswordIsNull(){
        ActivePlayer Player = new ActivePlayer();
        String result = Player.getPassword();
        String expResult = "";
        assertEquals(expResult, result);
    }
    @Test
    public void NoActivePlayerStatusIsNull(){
        ActivePlayer Player = new ActivePlayer();
        Boolean result = Player.getStatus();
        Boolean expResult = false;
        assertEquals(expResult, result);
    }
     @Test
    public void ActivePlayerPlayerObjectIsOkay(){
        Player instance = new Player();
        ActivePlayer Player = new ActivePlayer(instance,"Bob","password",true);
        Player result = Player.getPlayer();
        Player expResult = instance;
        assertEquals(expResult, result);
    }
    @Test
    public void ActivePlayerNameIsBob(){
        ActivePlayer Player = new ActivePlayer(null,"Bob","password",true);
        String result = Player.getName();
        String expResult = "Bob";
        assertEquals(expResult, result);
    }
     @Test
    public void ActivePlayerPasswordIsPassword(){
        ActivePlayer Player = new ActivePlayer(null,"Bob","Password",true);
        String result = Player.getPassword();
        String expResult = "Password";
        assertEquals(expResult, result);
    }
     @Test
    public void ActivePlayerStatusIsTrue(){
        ActivePlayer Player = new ActivePlayer(null,"Bob","password",true);
        Boolean result = Player.getStatus();
        Boolean expResult = true;
        assertEquals(expResult, result);
    }
    @Test
    public void ActivePlayerDoLoginFail(){
        ActivePlayer Player = new ActivePlayer();
        String result = Player.login();
        String expResult = "login";
        assertEquals(expResult, result);
    }
}
