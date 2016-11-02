package com.playground.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.playground.data.InMessage;
import com.playground.data.OutMessage;
import com.playground.logic.MessageLogic;

@Controller
public class MessageController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageLogic msgLogic;
	
	@Autowired
    private SimpMessagingTemplate template;
	
	@RequestMapping(path="/")
	public String home(Model model) {		
		return "index";
	}	
	
	@RequestMapping(path="/message", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody void sendMessage(
			@RequestBody OutMessage outMessage,
			Model model) {
		
		logger.info("Message received: " + outMessage.toString());
		
		// save in Redis
		msgLogic.saveMessage(outMessage);
		
		// send message to websocket
		this.template.convertAndSend("/topic/messages", outMessage);
	}

	@RequestMapping(path="/message", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<OutMessage> getMessages(Model model) {
		List<InMessage> inMessages = msgLogic.getMessages();
		logger.info("Found " + inMessages.size() + " messages." );
		return inMessages.stream()
				.map(p -> new OutMessage(p.getNickname(), p.getMessage()))
				.collect(Collectors.toList());
	}
}
