package com.andreystar.library.service;

import com.andreystar.library.dao.UserRepository;
import com.andreystar.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	@Override
	public void save(User user) {
		userRepository.save(user);
	}
	
	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}
}
