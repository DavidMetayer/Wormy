/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.Date;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author c0641903
 */
@ServerEndpoint("/play")
public class Server {

    // LEN : I don't understand how this works.
    @Inject
    Games games;
    
    @OnMessage
    public void receiveMessage(String message, Session session) throws IOException {

        // Figure out which Game based on Session
        // LEN : I don't understand this. Do I use session to find the Game, the Worm, or both? Where do I put the sessions?
        // LEN : And how do I use sessions in the classes?
        // LEN : Do the Game classes for each Player need to be exactly the same?
        // LEN : I need to be able to end the Game when one Worm dies, when a Player closes their session, or when they leave the game... but how do I do that from here?
        // LEN : I may also require a "ready" prompt that will start the Game when both Players are ready. How could I do this?
        Game game = games.findGameBySession(session);
        Worm currentWorm = game.findWormBySession(session);
        // LEN : Doing all the Game's processes here is the correct way to do it, right?
        switch (message) {
            case "L":
                currentWorm.setDirection(-1);
                break;
            case "U":
                currentWorm.setDirection(-50);
                break;
            case "R":
                currentWorm.setDirection(1);
                break;
            case "D":
                currentWorm.setDirection(50);
                break;
            default:
                break;
        }
        String positionString = Integer.toString(currentWorm.getPosition());
        if (currentWorm.getPosition() < 50 && currentWorm.getDirection() == -50) {
            currentWorm.setStatus(false);
        } else if ((positionString.endsWith("49") || positionString.endsWith("99")) && currentWorm.getDirection() == 1) {
            currentWorm.setStatus(false);
        } else if (currentWorm.getPosition() > 2449 && currentWorm.getDirection() == 50) {
            currentWorm.setStatus(false);
        } else if (currentWorm.getPosition() % 50 == 0 && currentWorm.getDirection() == -1) {
            currentWorm.setStatus(false);
        // LEN : I need to also keep track of the other Worm for this. How?
        // LEN : Should I be creating all the processing methods for the Game in the Game class and then call them here?
        } else if (opponent.getSegments().contains(currentWorm.getPosition() + currentWorm.getDirection())) {
            currentWorm.setStatus(false);
        }
        
        
        
        // Still don't understand how this will fit into the Javascript.
        Basic basic = session.getBasicRemote().sendText(g.toJSON());
    }

}
