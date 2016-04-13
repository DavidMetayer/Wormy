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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author c0641903
 */

@ManagedBean
@SessionScoped
public class ActivePlayer {
    
    // Attributes
    private Player player;
    private String name;
    private String password;
    private boolean status;
    
    // Constructors
    public ActivePlayer() {
        player = null;
        name = "";
        password = "";
        status = false;
    }
    public ActivePlayer(Player player, String name, String password, boolean status) {
        this.player = player;
        this.name = name;
        this.password = password;
        this.status = status;
    }

    // Player Getter/Setter
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    // Name Getter/Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Password Getter/Setter
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Status Getter/Setter
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    // Misc. Methods
    public String login() {
        String hashedPassword = DatabaseUtils.hash(password);
        Players players = new Players();
        for (Player selectedPlayer : players.getInstance().getPlayers()) {
            if (name.equals(selectedPlayer.getName())
                    && hashedPassword.equals(selectedPlayer.getHashedPassword())) {
                status = true;
                player = selectedPlayer;
                return "index";
            }
        }
        player = null;
        status = false;
        return "login";
    }
    public String update(String name, String password, Player player) {
        try (Connection connection = DatabaseUtils.connect()) {
            String hashedPassword = DatabaseUtils.hash(password);
            String sql = "UPDATE players SET name = ?, hashedPassword = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, hashedPassword);
            statement.setString(3, player.getName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
        }
        Players players = new Players();
        for (Player selectedPlayer : players.getInstance().getPlayers()) {
            if (name.equals(selectedPlayer.getName())) {
                this.player = selectedPlayer;
                return "index";
            }
        }
        return "index";
    }
    public String delete(Player player) {
        try (Connection connection = DatabaseUtils.connect()) {
            String sql = "DELETE FROM players WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, player.getName());
            statement.executeUpdate();
            this.player = null;
            status = false;
            return "login";
        } catch (SQLException ex) {
            Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
            return "manage";
        }
    }
    
}
