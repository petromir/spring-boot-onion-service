package com.petromirdzhunev.users.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.jwt")
public record JwtProperties(String secret, Long tokenValidity) { }
