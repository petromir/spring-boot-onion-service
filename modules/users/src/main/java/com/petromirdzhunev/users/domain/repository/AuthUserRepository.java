package com.petromirdzhunev.users.domain.repository;

import com.petromirdzhunev.users.domain.AuthUser;

public interface AuthUserRepository {
	AuthUser insertAuthUser(AuthUser authUser);

	AuthUser authUser(String email);

	void deleteAuthUser(Long authUserId);

	void assertAuthUserNotExists(String name);

	AuthUser authUser(Long userId);
}
