package com.orderprocessing.users.exceptions;

import org.springframework.http.HttpStatus;

import com.orderprocessing.common.exceptions.ApiErrors;
import com.orderprocessing.common.exceptions.ApiException;

public class UserServiceException extends ApiException {

	private static final long serialVersionUID = 1L;

	public UserServiceException(Exception cause) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, ApiErrors.USER_SERVICE_ERROR, MessageKeys.USER_SERVICE_ERROR, cause.getMessage());
	}

}
