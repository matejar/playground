package com.playground.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageListener implements MessageListener {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onMessage(Message message, byte[] pattern) {
		logger.info("Received by RedisMessageListener " + message.toString() );
	
		// write it to posgresql
	}

}
