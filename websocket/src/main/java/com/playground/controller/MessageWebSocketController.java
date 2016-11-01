package com.playground.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.playground.model.OutMessage;

@Controller
public class MessageWebSocketController {

	@MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutMessage send(OutMessage message) throws Exception {
        return message;
    }
}
