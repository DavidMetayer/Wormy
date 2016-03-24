/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0641903
 */
public class NewPlayerAdder {
    private NewPlayer newPlayer;
    
    
    public void createNewPlayer() {
        newPlayer = new NewPlayer();
    }
    
    public void addNewPlayer() {
        
        try (Connection conn = DatabaseUtils.connect()) {
            String hashedPassword = DatabaseUtils.hash(newPlayer.getPassword());
            String sql = "INSERT INTO players VALUES(?,?,0,0,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPlayer.getName());
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NewPlayerAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
