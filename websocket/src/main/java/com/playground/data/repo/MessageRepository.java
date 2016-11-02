package com.playground.data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.playground.data.InMessage;

public interface MessageRepository extends MongoRepository<InMessage, String> {

}
