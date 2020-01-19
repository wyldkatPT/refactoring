package com.celfocus.training.exception;

public class UserNotFoundException extends ApplicationException {

	public UserNotFoundException() {
		super(ErrorMessage.USER_NOT_FOUND);
	}
}
