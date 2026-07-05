package com.petromirdzhunev.user.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.jwt")
public record JwtProperties(String secret, Long tokenValidity) { }
