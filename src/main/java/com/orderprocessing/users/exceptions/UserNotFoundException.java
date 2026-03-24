package com.orderprocessing.users.exceptions;

import com.orderprocessing.common.exceptions.ApiErrors;
import com.orderprocessing.common.exceptions.NotFoundApiException;

public class UserNotFoundException extends NotFoundApiException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Object identifier) {
		super(ApiErrors.USER_NOT_FOUND, MessageKeys.USER_NOT_FOUND, identifier);
	}

}
