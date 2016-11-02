package com.playground.data;

import java.io.Serializable;

public class OutMessage implements Serializable {

	private static final long serialVersionUID = 34609961167864695L;

	String nickname;
	
	String message;
	
	
	public OutMessage() {
		super();
	}

	public OutMessage(String nickname, String message) {
		super();
		this.nickname = nickname;
		this.message = message;
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
		return "OutMessage [nickname=" + nickname + ", message=" + message + "]";
	}
}
