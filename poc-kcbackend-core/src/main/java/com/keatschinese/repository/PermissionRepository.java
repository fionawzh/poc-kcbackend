package com.keatschinese.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.keatschinese.model.PermissionModel;

public interface PermissionRepository extends
		MongoRepository<PermissionModel, String> {

	PermissionModel findByName(String name);
	List<PermissionModel> findByManageable(boolean manageable);
}
