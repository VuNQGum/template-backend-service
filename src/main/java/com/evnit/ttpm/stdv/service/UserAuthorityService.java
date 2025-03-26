package com.evnit.ttpm.stdv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evnit.ttpm.stdv.model.CustomUserDetails;
import com.evnit.ttpm.stdv.model.UserAuthority;
import com.evnit.ttpm.stdv.repository.UserAuthorityReopsitory;

@Service
public class UserAuthorityService {

	private final UserAuthorityReopsitory userAuthorityReopsitory;

	@Autowired
	public UserAuthorityService(UserAuthorityReopsitory userAuthorityReopsitory) {
		this.userAuthorityReopsitory = userAuthorityReopsitory;
	}

	public void insertUserAuth2(UserAuthority userAuthority, CustomUserDetails currentUser) {
		userAuthorityReopsitory.save(userAuthority);
	}

	public void deleteUserAuth(UserAuthority userAuthority, CustomUserDetails currentUser) {
		userAuthorityReopsitory.delete(userAuthority);
	}

}
