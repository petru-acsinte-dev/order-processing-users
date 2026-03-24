package com.orderprocessing.users.integration;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.jsonpath.JsonPath;
import com.orderprocessing.common.tests.AbstractIntegrationTestBase;
import com.orderprocessing.order_processing_users.OrderProcessingUsersApplication;
import com.orderprocessing.users.constants.Constants;
import com.orderprocessing.users.constants.UserRole;
import com.orderprocessing.users.constants.UserStatus;
import com.orderprocessing.users.dto.AddressDTO;
import com.orderprocessing.users.dto.CreateCustomerUserRequest;
import com.orderprocessing.users.dto.CustomerUserResponse;

@SpringBootTest(classes = OrderProcessingUsersApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(value={"test"})
public abstract class UsersIntegrationTestBase extends AbstractIntegrationTestBase {

	private String bearer;

	public String getBearer() {
		return bearer;
	}

	/**
	 * Helper method to create additional users for tests.
	 * @param username Unique username.
	 * @param email Unique email.
	 * @param password Password.
	 * @param addressLine Required address part.
	 * @return The {@link CustomerUserResponse} representing the new user.
	 * @throws Exception
	 */
	protected CustomerUserResponse createAndValidateUser(String username, String email, String password, String addressLine) throws Exception {
		final var createRequest = new CreateCustomerUserRequest();
		createRequest.setUsername(username);
		createRequest.setEmail(email);
		createRequest.setPassword(password);
		createRequest.setAddress(new AddressDTO(addressLine));

		// first user
		final MvcResult result = mockMvc.perform(post(Constants.USERS_PATH)
						.accept(MediaType.APPLICATION_JSON)
						.header(HttpHeaders.AUTHORIZATION, getBearer())
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding(StandardCharsets.UTF_8)
						.content(objectMapper.writeValueAsString(createRequest)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(JSON_PATH_USERNAME).value(username))
				.andExpect(jsonPath(JSON_PATH_EMAIL).value(email))
				.andExpect(jsonPath(JSON_PATH_ADDRESS_LINE1).value(addressLine))
				.andExpect(jsonPath(JSON_PATH_STATUS).value(UserStatus.ACTIVE))
				.andExpect(jsonPath(JSON_PATH_ROLE).value(UserRole.USER))
				.andExpect(jsonPath(JSON_PATH_EXTERNAL_ID).isNotEmpty())
				.andExpect(jsonPath(JSON_PATH_EXTERNAL_ID, matchesPattern(UUID_REGEX)))
				.andReturn();
		final String content = result.getResponse().getContentAsString();
		if (getLog().isDebugEnabled()) {
			getLog().debug(content);
		}
		return objectMapper.readValue(content, new TypeReference<CustomerUserResponse>() {});
	}

	/**
	 * Performs login for the specified credentials and retrieves a JWT token.
	 * @param username Name of existing user.
	 * @param password Password of existing user.
	 * @throws Exception
	 */
	protected void loginAs(String username, String password) throws Exception {
		final MvcResult result = mockMvc.perform(post(Constants.LOGIN_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				// {"username":"x","password":"y"} payload
				.content(String.format("{\"username\":\"%s\",\"password\":\"%s\"}",  //$NON-NLS-1$
						username, password)))
			.andExpect(status().isOk())
			.andReturn();
		final String strContent = result.getResponse().getContentAsString();
		final String token = JsonPath.read(strContent, "$.token"); //$NON-NLS-1$
		bearer = Constants.BEARER + token;
	}
}
