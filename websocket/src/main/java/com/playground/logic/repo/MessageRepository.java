package com.playground.logic.repo;

import com.playground.model.InMessage;

public interface MessageRepository {

	void saveMessage(InMessage message);

	InMessage getMessage(String id);

	void deleteMessage(String id);	
}
