package com.keatschinese.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.keatschinese.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {

	UserModel findByName(String name);
	List<UserModel> findByManageable(boolean manageable);
}
