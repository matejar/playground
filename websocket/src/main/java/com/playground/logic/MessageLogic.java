package com.playground.logic;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.playground.model.InMessage;

@Component
public class MessageLogic {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * Method saves message to Redis... If message already exists, message will
	 * be overwritten.
	 */
	public void saveMessage(InMessage message) {
		logger.info("Saving message: " + message);

		if (StringUtils.isEmpty(message.getId())) {
			message.setId(UUID.randomUUID().toString());
			logger.debug("No id in provided message. Setting id: " + message);
		}

		// push message to Redis queue
		publish(message.toString());
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
}
