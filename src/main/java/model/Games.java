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

/**
 *
 * @author c0641903
 */

public class Games {
    
    // Attributes
    private List<Game> games;
    private Game game;

    // Constructors
    public Games() {
        game = new Game("", "", null, null, null, null, null);
        retrieveGames();
    }
    public Games(List<Game> games, Game game) {
        this.games = games;
        this.game = game;
    }
    
    // Games Getter/Setter
    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }

    // Game Getter/Setter
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    
    // Misc. Methods
    private void retrieveGames() {
        try (Connection connection = DatabaseUtils.connect()) {
            games = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM games WHERE opponent = NULL");
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
    private String createGame(ActivePlayer activePlayer) {
        try (Connection connection = DatabaseUtils.connect()) {
            String sql = "INSERT INTO games(host) VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, activePlayer.getName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
            return "index";
        }
        retrieveGames();
        return "game";
    }
    public String joinGame(ActivePlayer activePlayer, Game game) {
        retrieveGames();
        for (Game selectedGame : games) {
            if (game.getHost().equals(selectedGame.getHost())) {
                try (Connection connection = DatabaseUtils.connect()) {
                    String sql = "UPDATE games SET opponent = ? WHERE host = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, activePlayer.getName());
                    statement.setString(2, game.getHost());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
                    return "index";
                }
                return "game";
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
    
}
