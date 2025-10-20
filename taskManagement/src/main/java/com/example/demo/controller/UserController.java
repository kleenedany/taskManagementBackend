package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.UserPO;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/user")
		public List<UserPO> loadAllUser() {
			return userService.loadAllUsers();
		}
	
	@PostMapping("user/{id}")
	public ResponseEntity<UserPO> updateUser(@PathVariable Integer id, @RequestBody UserPO user) {
		UserPO updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/user")
	public UserPO createUser(@RequestBody UserPO user) {
		return userService.createUser(user);
	}
	

}
