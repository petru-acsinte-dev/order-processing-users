package com.orderprocessing.users.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(@JsonProperty("token") String token) {}
