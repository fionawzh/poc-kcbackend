package com.keatschinese.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keatschinese.http.CommonResponse;
import com.keatschinese.exception.ClientOrientedException;
import com.keatschinese.model.UserModel;
import com.keatschinese.service.ApiService;

@RestController
@RequestMapping("/v1/api")
public class ApiController {

	private Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	private ApiService apiService;
	
	@RequestMapping(method = RequestMethod.POST, value={"/apiregist"})
	public CommonResponse apiregist(@RequestBody UserModel user) {
		logger.debug("enter regist method!");
		try {
			apiService.regist(user);
			return new CommonResponse(CommonResponse.ResponseStatus.SUCCESS);
		} catch (ClientOrientedException e) {
			return new CommonResponse(CommonResponse.ResponseStatus.FAILED, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAuthority('fake-api-user')")
	@RequestMapping(method = RequestMethod.GET, value={"/users"})
	public CommonResponse<List<UserModel>> list() {
		logger.debug("enter list method!");
		try {
			return new CommonResponse(CommonResponse.ResponseStatus.SUCCESS, apiService.findAllUsers());
		} catch (ClientOrientedException e) {
			return new CommonResponse(CommonResponse.ResponseStatus.FAILED, e.getMessage());
		}
	}
	
}
