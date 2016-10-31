package com.playground.logic.repo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.playground.model.InMessage;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

	private static final String KEY = "Message";

	private RedisTemplate<String, InMessage> redisTemplate;
	private HashOperations hashOps;

	@Autowired
	public MessageRepositoryImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOps = redisTemplate.opsForHash();
	}

	@Override
	public void saveMessage(InMessage message) {
		hashOps.put(KEY, message.getId(), message);
	}
	
	@Override
	public InMessage getMessage(String id) {
        return (InMessage) hashOps.get(KEY, id);
    }
	
	@Override
	public void deleteMessage(String id) {
        hashOps.delete(KEY, id);
    }
}
