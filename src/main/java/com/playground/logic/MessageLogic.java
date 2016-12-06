package com.playground.logic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.playground.data.InMessage;
import com.playground.data.OutMessage;
import com.playground.data.repo.MessageRepository;

@Component
public class MessageLogic {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	MessageRepository messageRepo;
	
	private Gson gson = new Gson();
	
	/**
	 * Method saves message to Redis... If message already exists, message will
	 * be overwritten.
	 */
	public void saveMessage(OutMessage message) {
		logger.info("Saving message: " + message);
		
		// push message to Redis queue
		publish(gson.toJson(message));
	}

	// put message to the Redis queue
	public void publish(final String message) {
		redisTemplate.execute(new RedisCallback<Long>() {
			@SuppressWarnings("unchecked")
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.publish(
						((RedisSerializer<String>) redisTemplate.getKeySerializer()).serialize("queue"),
						((RedisSerializer<Object>) redisTemplate.getValueSerializer()).serialize(message));
			}
		});
	}
	
	public List<InMessage> getMessages() {
		return messageRepo.findAll();
	}
}
