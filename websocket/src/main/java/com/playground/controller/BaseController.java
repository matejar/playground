package com.playground.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.playground.logic.MessageLogic;
import com.playground.model.InMessage;
import com.playground.model.OutMessage;

@Controller
public class BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageLogic msgLogic;
	
	@RequestMapping(path = "/message", method = RequestMethod.POST)
	public void sendMessage(
			@RequestParam(value="jsonMessage") OutMessage outMessage,
			Model model) {
		
		logger.info("Message received: " + outMessage.toString());

		InMessage inMessage = convertFromOutMessage(outMessage);
		msgLogic.saveMessage(inMessage);
		
		// return via WebSocket		
	}	
	
	private InMessage convertFromOutMessage(OutMessage outMessage) {
		InMessage message = new InMessage();
		message.setId(outMessage.getId());
		message.setMessage(outMessage.getMessage());
		return message;
	}

	@RequestMapping(path = "/message", method = RequestMethod.GET)
	public String getMessages() {
		
		return "messages";
	}
	
}
