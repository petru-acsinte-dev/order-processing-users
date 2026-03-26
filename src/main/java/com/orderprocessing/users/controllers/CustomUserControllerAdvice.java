package com.orderprocessing.users.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orderprocessing.common.exceptions.ApiError;
import com.orderprocessing.common.exceptions.ApiException;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden // breaks swagger UI
@RestControllerAdvice
public class CustomUserControllerAdvice {

	private static Logger log = LoggerFactory.getLogger(CustomUserControllerAdvice.class);

	private final MessageSource messageSource;

	public CustomUserControllerAdvice(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiError> handleApiException(ApiException ex, Locale locale) {
		log.error("Exception caught: {}", ex.getMessage(), ex); //$NON-NLS-1$
		final String message = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                locale
        );

		final ApiError error = new ApiError(ex.getErrorCode(), message);

		return ResponseEntity
				.status(ex.getStatus())
				.body(error);
	}

}
