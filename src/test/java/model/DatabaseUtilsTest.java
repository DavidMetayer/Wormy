/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static java.lang.System.err;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import static model.DatabaseUtils.salt;
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
public class DatabaseUtilsTest {
    
    public DatabaseUtilsTest() {
    }
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
    /**
     * Test of hash method, of class DatabaseUtils.
     */
    @Test
    public void testHash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String password = "hihi";
        String salt = "THISISArandomSTRINGofCHARACTERSusedTOsaltTHEpasswords";
        String saltedPassword = password + salt;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            byte[] hash = messageDigest.digest(saltedPassword.getBytes("UTF-8"));
            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(b & 0xff).toUpperCase();
                if (hex.length() == 1) {
                    hashedPassword.append("0");
                }
                hashedPassword.append(hex);
            }
            String expResult = hashedPassword.toString();
            String result = DatabaseUtils.hash(password);
            assertEquals(expResult, result);
    }

    /**
     * Test of connect method, of class DatabaseUtils.
     */
    @Test
    public void testConnect() throws Exception {
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        String database = "wormyvs";
        String jdbc = "jdbc:mysql://" + host + ":" + port + "/" + database;
        String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        Connection expResult = DriverManager.getConnection(jdbc, username, password);
        Connection result = DatabaseUtils.connect();
        assertEquals(expResult, result);
        System.out.println("Something went wrong, maybe this ->");
        
        
    }
    
}
