package com.petromirdzhunev.users.domain.exception;

public class EntityAlreadyExistsException extends RuntimeException {

	public EntityAlreadyExistsException(final String message) {
		super(message);
	}
}
