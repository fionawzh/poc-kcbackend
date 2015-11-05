package com.keatschinese;

import java.util.Arrays;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.keatschinese.model.PermissionModel;
import com.keatschinese.model.RoleModel;
import com.keatschinese.model.UserModel;
import com.keatschinese.repository.PermissionRepository;
import com.keatschinese.repository.RoleRepository;
import com.keatschinese.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(Application.class);
	
	/*
	 * for test only, later move to .sql
	 */
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) throws Exception {
		new SpringApplication(new Object[]{Application.class}).run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("start of initializing permission, role and user for api user");
		PermissionModel permission = null;
		if ((permission = permissionRepository.findByName("read")) == null) {
			permission = new PermissionModel();
			permission.setName("read");
			permission.setManageable(false);
			permission = permissionRepository.save(permission);
		}
		RoleModel role = null;
		if ((role = roleRepository.findByName("api-user")) == null) {
			role = new RoleModel();
			role.setName("api-user");
			role.setManageable(false);
			role.setPermissions(Arrays.asList(permission));
			role = roleRepository.save(role);
		}
		if (userRepository.findByName("test") == null) {
			UserModel user = new UserModel();
			user.setCreatedTime(new Date());
			user.setLastUpdatedTime(new Date());
			user.setName("test");
			user.setPassword("test");
			user.setManageable(false);
			user.setRoles(Arrays.asList(role));
			user = userRepository.save(user);
		}
		logger.info("end of initializing permission, role and user for api user");
	}
	
}
