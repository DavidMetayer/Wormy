/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

enum PlayerStatus {IDLE, READY, PLAYING}
/**
 *
 * @author c0641903
 */
public class ActivePlayer {
    private Player player;
    private PlayerStatus playerStatus;
    private boolean loginStatus;
    
    public ActivePlayer() {
        player = null;
        playerStatus = null;
        loginStatus = false;
    }
    public ActivePlayer(Player player, PlayerStatus playerStatus, boolean loginStatus) {
        this.player = player;
        this.playerStatus = playerStatus;
        this.loginStatus = loginStatus;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }
    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }
    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
    
}
