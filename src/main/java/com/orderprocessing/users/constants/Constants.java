package com.orderprocessing.users.constants;

import java.util.UUID;

public class Constants {

	private Constants() {}

	public static final UUID ADMIN_UUID0 = UUID.fromString("00000000-0000-0000-0000-000000000000"); //$NON-NLS-1$

	/**
	 * Users main endpoint
	 */
	public static final String USERS_PATH = "/users"; //$NON-NLS-1$

	/**
	 * Login endpoint
	 */
	public static final String LOGIN_PATH = "/login/auth"; //$NON-NLS-1$

	/**
	 * Authentication bearer
	 */
	public static final String BEARER = "Bearer "; //$NON-NLS-1$
}
