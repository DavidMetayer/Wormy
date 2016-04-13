/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.Date;
// NOTE -- This is the correct ApplicationScoped for integration between WebSockets and JSF
import javax.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
public class Server {

    // LEN : I don't understand how this works.
    // -- This will automatically inject the correct Games object for the scope. You need to pick a scope for this class. It should be @ApplicationScoped. You may need to start using the other @XXXScoped classes (I'm importing the correct one here.)
    @Inject
    Games games;
    
    @OnMessage
    public void receiveMessage(String message, Session session) throws IOException {

        // Figure out which Game based on Session
        // LEN : I don't understand this. Do I use session to find the Game, the Worm, or both? Where do I put the sessions?
        // -- Both. The session (which you receive when this method is called) is unique for each Client-Server conversation (eg- Player1's web browser will have one Session, and Player2's web browser will have another Session.) This information comes to us automatically in this method because it is @OnMessage tagged and has a Session parameter in the method signature.
        // LEN : And how do I use sessions in the classes?
        // -- Sessions in the Classes are just there to be identifiers. To say that THIS SPECIFIC Player object is attached to a specific Session (which we use to send outgoing messages)
        // LEN : Do the Game classes for each Player need to be exactly the same?
        // -- Yes, they should both be accessing the same Game object so that when P1 makes a move, P2 sees the change
        // LEN : I need to be able to end the Game when one Worm dies, when a Player closes their session, or when they leave the game... but how do I do that from here?
        // -- You don't have to do it from here, exactly. On your Player object, you should have a Session. You can use that Session object to call getBasicRemote() and then sendText() and you can tell the player "Game Over" from anywhere in your code
        // LEN : I may also require a "ready" prompt that will start the Game when both Players are ready. How could I do this?
        // -- Same as above. Make a message (similar to the key presses) that says "I am ready!" and change a flag in the Game object. When the Game object has 2 ready players, it can send messages out to them on their Session objects. This can be done from the Games class or the Game class.
        Game game = games.findGameBySession(session);
        Worm currentWorm = game.findWormBySession(session);
        // LEN : Doing all the Game's processes here is the correct way to do it, right?
        // This class is mostly for receiving messages from a WebBrowser, a lot of your game logic will still be in the Game class (and the Worm class)
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
        // -- This should probably be done in the Game class. You can run a timed loop that runs every second. See https://docs.oracle.com/javaee/6/tutorial/doc/bnboy.html and look at Automatic Timers. You probably want something like @Schedule(second="*") tagged to a method in your Games class
        // LEN : Should I be creating all the processing methods for the Game in the Game class and then call them here?
        // -- Yes. This should just be a thin communication layer to tell Game "Hey this is the latest update I want done"
        } else if (opponent.getSegments().contains(currentWorm.getPosition() + currentWorm.getDirection())) {
            currentWorm.setStatus(false);
        }
        
        
        
        // Still don't understand how this will fit into the Javascript.
        // -- This sends back the JSON Object from the example (ie- a JSON object that includes at least two arrays that hold the positions of the two worms). You will need to implement the Game.toJSON method that builds this JSON Object. This JSON Object is used in JavaScript to update the whole board (with those three For loops in the proof-of-concept.)
        Basic basic = session.getBasicRemote().sendText(g.toJSON());
    }

}
