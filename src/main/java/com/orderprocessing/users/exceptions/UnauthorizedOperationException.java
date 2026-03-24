package com.orderprocessing.users.exceptions;

import com.orderprocessing.common.exceptions.ApiErrors;
import com.orderprocessing.common.exceptions.ForbiddenApiException;

public class UnauthorizedOperationException extends ForbiddenApiException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedOperationException() {
		super(ApiErrors.UNAUTHORIZED_OPERATION, MessageKeys.UNAUTHORIZED_OPERATION);
	}

}
