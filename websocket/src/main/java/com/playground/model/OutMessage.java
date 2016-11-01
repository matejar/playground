package com.playground.model;

import java.io.Serializable;

public class OutMessage implements Serializable {

	private static final long serialVersionUID = 34609961167864695L;

	String id;
	
	String nickname;
	
	String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "OutMessage [id=" + id + ", nickname=" + nickname + ", message=" + message + "]";
	}
}
