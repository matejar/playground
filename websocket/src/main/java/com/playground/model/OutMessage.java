package com.playground.model;

import java.io.Serializable;

public class OutMessage implements Serializable {

	private static final long serialVersionUID = 34609961167864695L;

	String id;
	
	String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "OutMessage [id=" + id + ", message=" + message + "]";
	}
}
