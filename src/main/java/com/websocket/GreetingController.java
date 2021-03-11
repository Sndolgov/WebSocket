package com.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.util.List;

@Controller
public class GreetingController
{
    private boolean add = true;

    @Autowired
    private List<String> sessions;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message, Principal user, @Header("simpSessionId") String sessionId) throws Exception {
        Thread.sleep(1000); // simulated delay
        if (add){
            sessions.add(sessionId);
        }
        add = !add;
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");

    }
}

//rvcqp4o2