package com.petromirdzhunev.user.domain.repository;

import com.petromirdzhunev.user.domain.AuthUser;

public interface AuthUserRepository {
	AuthUser insertAuthUser(AuthUser authUser);

	AuthUser authUser(String email);

	void deleteAuthUser(Long authUserId);

	void assertAuthUserNotExists(String name);

	AuthUser authUser(Long userId);
}
