package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.UserPO;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserPO> loadAllUsers() {
		return userRepository.findAll();
	}
	
	public UserPO updateUser(Integer id, UserPO newUser) {
		Optional<UserPO> user = userRepository.findById(id);
		user.get().setFirstName(newUser.getFirstName());
		user.get().setLastName(newUser.getLastName());
		user.get().setTasks(newUser.getTasks());
		return userRepository.save(user.get());
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
	
	public UserPO createUser(UserPO user) {
		return userRepository.save(user);
	}
	
	

}
