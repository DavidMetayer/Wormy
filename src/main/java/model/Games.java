/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
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

    @Schedule(second = "*/5")
    public void updateAllGames() throws IOException {
        for (Game game : games) {
            Worm worm = game.getBlueWorm();
            game.updateWormPosition(worm);
            game.updateWormSize(worm);
            game.updateWormLTP(worm);
            game.updateWormSegments(worm);
            worm = game.getRedWorm();
            game.updateWormPosition(worm);
            game.updateWormSize(worm);
            game.updateWormLTP(worm);
            game.updateWormSegments(worm);
            game.getBlueWorm().getPlayer().getSession().getBasicRemote().sendText(game.toJson());
            game.getRedWorm().getPlayer().getSession().getBasicRemote().sendText(game.toJson());
        }
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
                if (selectedGame.getOpponent().equals("")) {
                    games.add(selectedGame);
                }
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

            for (Game selectedGame : games) {
                if (selectedGame.getHost().equals(player.getName())) {
                    List<Integer> redSegments = new ArrayList<>();
                    redSegments.add(2);
                    redSegments.add(1);
                    redSegments.add(0);
                    List<Integer> blueSegments = new ArrayList<>();
                    blueSegments.add(2497);
                    blueSegments.add(2498);
                    blueSegments.add(2499);
                    Worm rw = new Worm(player, true, "red", 1, 2, 0, redSegments);
                    Worm bw = new Worm(null, true, "cyan", -1, 2497, 2499, blueSegments);
                    selectedGame.setRedWorm(rw);
                    selectedGame.setBlueWorm(bw);
                    Pellet p1 = new Pellet(824);
                    Pellet p2 = new Pellet(1224);
                    Pellet p3 = new Pellet(1624);
                    selectedGame.setPellet1(p1);
                    selectedGame.setPellet2(p2);
                    selectedGame.setPellet3(p3);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
            return "index";
        }
        retrieveGames();
        return "game";
    }

    public String joinGame(Player player, Game game) throws IOException {
        retrieveGames();
        for (Game selectedGame : games) {
            if (game.getHost().equals(selectedGame.getHost())) {
                try (Connection connection = DatabaseUtils.connect()) {
                    String sql = "UPDATE games SET opponent = ? WHERE host = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, player.getName());
                    statement.setString(2, game.getHost());
                    statement.executeUpdate();

                    selectedGame.setOpponent(player.getName());
                    List<Integer> redSegments = new ArrayList<>();
                    redSegments.add(2);
                    redSegments.add(1);
                    redSegments.add(0);
                    List<Integer> blueSegments = new ArrayList<>();
                    blueSegments.add(2497);
                    blueSegments.add(2498);
                    blueSegments.add(2499);
                    Players players = new Players();
                    Worm rw = new Worm(players.getPlayer(selectedGame.getHost()), true, "red", 1, 2, 0, redSegments);
                    Worm bw = new Worm(player, true, "cyan", -1, 2497, 2499, blueSegments);
                    selectedGame.setRedWorm(rw);
                    selectedGame.setBlueWorm(bw);
                    Pellet p1 = new Pellet(824);
                    Pellet p2 = new Pellet(1224);
                    Pellet p3 = new Pellet(1624);
                    selectedGame.setPellet1(p1);
                    selectedGame.setPellet2(p2);
                    selectedGame.setPellet3(p3);
                    // selectedGame.getRedWorm().getPlayer().getSession().getBasicRemote().sendText(selectedGame.toJson());

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
            String sql = "DELETE FROM games WHERE host = ?";
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

    public Game getGame(Session session) {
        // -- See below. I also implemented getSession on Player
        for (Game selectedGame : games) {
            if (selectedGame.getBlueWorm().getPlayer().getSession().equals(session)
                    || selectedGame.getRedWorm().getPlayer().getSession().equals(session)) {
                return selectedGame;
            }
        }
        return null;
    }

}
