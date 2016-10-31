package com.playground.model;

import java.io.Serializable;
import java.time.LocalDate;

public class InMessage implements Serializable {

	private static final long serialVersionUID = -6096385774770341661L;
	
	private String id;
	
	private String message;
	
	private LocalDate dateCreated = LocalDate.now();

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "InMessage [id=" + id + ", message=" + message + ", dateCreated=" + dateCreated + "]";
	}	
}
