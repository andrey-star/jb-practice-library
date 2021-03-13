package com.andreystar.library.service;

import com.andreystar.library.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	List<User> findAll();
	
	Optional<User> findById(int id);
	
	void save(User user);
	
	void deleteById(int id);

}
