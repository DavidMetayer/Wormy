/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import java.util.Date;
import javax.websocket.OnMessage;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author c0641903
 */

@ServerEndpoint("/whatGoesHere?")
public class Server {
    
    @OnMessage
    public void receiveMessage(String message, Session session) throws IOException {
        
        switch (message) {
            case "L":
                
                break;
            case "U":
                
                break;
            case "R":
                
                break;
            case "D":
                
                break;
            default:
                
                break;
        }
    }
    
}
