package com.playground.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import com.google.gson.Gson;
import com.playground.data.InMessage;
import com.playground.data.repo.MessageRepository;

public class RedisMessageListener implements MessageListener {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageRepository messageRepo;
	
	private Gson gson = new Gson();

	@Override
	public void onMessage(Message message, byte[] pattern) {
		logger.info("Received by RedisMessageListener " + message.toString() );
	
		// a little trick... because outmessage is subset of inmessage... 
		// do NOT do this for production and mantainable code !!!
		InMessage inMessage = gson.fromJson(message.toString(), InMessage.class);
		
		// write it to postgresql
		messageRepo.save(inMessage);
	}

}
