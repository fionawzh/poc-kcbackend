package com.keatschinese.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.keatschinese.exception.ClientOrientedException;
import com.keatschinese.model.RoleModel;
import com.keatschinese.model.UserModel;
import com.keatschinese.repository.PermissionRepository;
import com.keatschinese.repository.RoleRepository;
import com.keatschinese.repository.UserRepository;

@Service("ApiService")
public class ApiServiceImpl implements ApiService {
	
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void regist(UserModel user) throws ClientOrientedException {
		if (user == null || user.getName() == null || user.getName().trim().length() == 0) {
			throw new ClientOrientedException("user name cannot be empty!");
		} 
		UserModel model = null;
		if ((model = userRepository.findByName(user.getName())) != null) {
			model.setLastUpdatedTime(new Date());
		} else {
			RoleModel roleModel = roleRepository.findByName("api-user");
			if (roleModel == null) {
				throw new ClientOrientedException("server side exception!");
			}
			model = new UserModel();
			model.setName(user.getName());
			model.setPassword(user.getName());
			model.setCreatedTime(new Date());
			model.setLastUpdatedTime(new Date());
			model.setManageable(false);
			model.setRoles(Arrays.asList(roleModel));
			model = userRepository.save(model);
		}
	}

	@PreAuthorize("hasAuthority('fake-api-user')")
	@Override
	public List<UserModel> findAllUsers() throws ClientOrientedException {
		return userRepository.findAll();
	}

}
