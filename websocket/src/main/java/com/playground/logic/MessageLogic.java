package com.playground.logic;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.playground.logic.repo.MessageRepository;
import com.playground.model.InMessage;

@Component
public class MessageLogic {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageRepository messageRepo;
	
	/**
	 *  Method saves message to Redis... If message already exists, message will be overwritten.
	 */
	public void saveMessage(InMessage message) {
		logger.info("Saving message: " + message);
		
		if(StringUtils.isEmpty(message.getId())) {
			message.setId(UUID.randomUUID().toString());			
			logger.debug("No id in provided message. Setting id: " + message);			
		}		
		
		// save message in redis
		messageRepo.saveMessage(message);
		
		// send message via websocket
	}
}
