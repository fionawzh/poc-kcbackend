package com.keatschinese.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.keatschinese.model.RoleModel;

public interface RoleRepository extends MongoRepository<RoleModel, String> {

	RoleModel findByName(String name);
	List<RoleModel> findByManageable(boolean manageable);
	
}
