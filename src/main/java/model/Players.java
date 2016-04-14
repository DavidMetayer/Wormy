/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author c0641903
 */

@ManagedBean
@ApplicationScoped
public class Players {
    
    // Attributes
    private List<Player> players;
    private static Players instance;

    // Constructors
    public Players() {
        retrievePlayers();
        instance = this;
    }
    public Players(List<Player> players, Players instance) {
        this.players = players;
        this.instance = instance;
    }
    public Players(int a){
        players = new ArrayList<>();
        instance = null;
    }

    // Players Getter/Setter
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    // Instance Getter/Setter
    public static Players getInstance() {
        return instance;
    }
    public static void setInstance(Players instance) {
        Players.instance = instance;
    }
    
    // Misc. Methods.
    private void retrievePlayers() {
        try (Connection connection = DatabaseUtils.connect()) {
            players = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM players");
            while (resultSet.next()) {
                Player selectedPlayer = new Player(
                        resultSet.getString("name"),
                        resultSet.getString("hashedPassword"),
                        resultSet.getInt("wins"),
                        resultSet.getInt("losses"),
                        resultSet.getDouble("winLossRatio")
                );
                players.add(selectedPlayer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
            players = new ArrayList<>();
        }
    }
    public String register(String name, String password) {
        try (Connection connection = DatabaseUtils.connect()) {
            String hashedPassword = DatabaseUtils.hash(password);
            String sql = "INSERT INTO players VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, hashedPassword);
            statement.setInt(3, 0);
            statement.setInt(4, 0);
            statement.setDouble(5, 0);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
        }
        retrievePlayers();
        return "login";
    }

}
