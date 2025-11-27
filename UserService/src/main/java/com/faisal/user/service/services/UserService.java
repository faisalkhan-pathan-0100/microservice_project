package com.faisal.user.service.services;

import java.util.List;
import java.util.Map;

import com.faisal.user.service.entities.User;

public interface UserService {

	
	//create user
	User saveUser(User user);
	
	//get all users
	List<User> getAllUser();
	
	//get single user
	User getSingleUser(String userId);
	
	//delete user
	String deleteUser(String userid);
	
	//update user
	User updateUser(String userId, User user);

    //partial update
    User partialUpdateUser(String userId, Map<String,Object> field);
	
}
