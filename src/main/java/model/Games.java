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
import javax.websocket.Session;

/**
 *
 * @author c0641903
 */

@ManagedBean
@ApplicationScoped
public class Games {
    
    // Attributes
    private List<Game> games;

    // Constructors
    public Games() {
        retrieveGames();
    }
    public Games(List<Game> games) {
        this.games = games;
    }
    public Games(int a) {
        games = new ArrayList<>();
    }
    
    // Games Getter/Setter
    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }
    
    // Misc. Methods
    private void retrieveGames() {
        try (Connection connection = DatabaseUtils.connect()) {
            games = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM games");
            while (resultSet.next()) {
                Game selectedGame = new Game(
                        resultSet.getString("host"),
                        resultSet.getString("opponent"),
                        null,
                        null,
                        null,
                        null,
                        null
                );
                games.add(selectedGame);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
            games = new ArrayList<>();
        }
    }
    // LEN : Where should I instantiate the Game data (i.e. Worms, Pellets, etc.)?
    // It can be done here, when you add to the DB, you also do a new Game(name, ..., ...)
    public String createGame(Player player) {
        try (Connection connection = DatabaseUtils.connect()) {
            String sql = "INSERT INTO games VALUES(?, '')";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, player.getName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
            return "index";
        }
        retrieveGames();
        
        return "game";
    }
    public String joinGame(Player player, Game game) {
        retrieveGames();
        for (Game selectedGame : games) {
            if (game.getHost().equals(selectedGame.getHost()) && selectedGame.getOpponent().equals("")) {
                try (Connection connection = DatabaseUtils.connect()) {
                    String sql = "UPDATE games SET opponent = ? WHERE host = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, player.getName());
                    statement.setString(2, game.getHost());
                    statement.executeUpdate();
                    retrieveGames();
                    return "game";
                } catch (SQLException ex) {
                    Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
                    return "index";
                }
            }
        }
        return "index";
    }
    public String endGame(Game game) {
        try (Connection connection = DatabaseUtils.connect()) {
                String sql = "DELETE FROM GAMES WHERE host = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, game.getHost());
                statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
        }
        retrieveGames();
        return "index";
    }
    public boolean checkGame(Game game) {
        if (game.getOpponent().equals("")) {
            return true;
        }
        return false;
    }
    // LEN : Absolutely confused here.
    public Game findGameBySession(Session s) {
        // -- See below. I also implemented getSession on Player
        for (Game g : games) {
            if (g.getBlueWorm().getPlayer().getSession().equals(s) || 
                    g.getRedWorm().getPlayer().getSession().equals(s))
                return g;
        }
        return null;
    }
}
