package com.websocket;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestMessaging
{
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private List<String> sessions;

    @SneakyThrows
    public void sendMessage()
    {
        while (true)
        {
            Thread.sleep(2000);
            for (String session : sessions)
            {
                SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
                headerAccessor.setSessionId(session);
                headerAccessor.setLeaveMutable(true);
                template.convertAndSendToUser(session,"/queue/changes", new Greeting("Hello world"), headerAccessor.getMessageHeaders());

//                template.convertAndSendToUser(session, "/topic/greetings", new Greeting("Hello world!"));
            }
        }
    }
}
