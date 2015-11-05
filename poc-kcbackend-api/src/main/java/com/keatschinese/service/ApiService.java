package com.keatschinese.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.keatschinese.exception.ClientOrientedException;
import com.keatschinese.model.UserModel;

public interface ApiService {

	void regist(UserModel user) throws ClientOrientedException;
	/*
	 * just a test
	 */
	@PreAuthorize("hasAuthority('fake-api-user')")
	List<UserModel> findAllUsers() throws ClientOrientedException;
}
