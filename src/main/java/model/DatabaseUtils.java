/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0641903
 */

public class DatabaseUtils {
    
    // Salt Variable
    public final static String salt = "THISISArandomSTRINGofCHARACTERSusedTOsaltTHEpasswords";
    
    // Password Hashing Method
    public static String hash(String password) {
        try {
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
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    // Database Connection Method
    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        String database = "wormyvs";
        String jdbc = "jdbc:mysql://" + host + ":" + port + "/" + database;
        String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        return DriverManager.getConnection(jdbc, username, password);
    }
    
}
