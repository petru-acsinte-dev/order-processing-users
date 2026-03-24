package com.orderprocessing.users.exceptions;

import org.springframework.http.HttpStatus;

import com.orderprocessing.common.exceptions.ApiErrors;
import com.orderprocessing.common.exceptions.ApiException;

public class DuplicateUserException extends ApiException {

	private static final long serialVersionUID = 1L;

	public DuplicateUserException(String username, String email) {
		super(HttpStatus.CONFLICT, ApiErrors.DUPLICATE_USER, MessageKeys.USER_ALREADY_EXISTS,
				username, email);
	}

}
