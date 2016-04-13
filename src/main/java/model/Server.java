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

    @Inject
    Games games;
    
    @OnMessage
    public void receiveMessage(String message, Session session) throws IOException {

        // Figure out which Game based on Session
        Game game = games.findGameBySession(session);
        Worm currentWorm = game.findWormBySession(session);
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
        } else if (opponent.getSegments().contains(currentWorm.getPosition() + currentWorm.getDirection())) {
            currentWorm.setStatus(false);
        }
        
        
        

        Basic basic = session.getBasicRemote().sendText(g.toJSON());
    }

}
