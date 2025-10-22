package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
		public ResponseEntity<List<UserDto>> loadAllUsers() {
			return ResponseEntity.ok(this.userService.loadAllUsers());
		}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody UserDto user) {
		this.userService.updateUser(id, user);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
		this.userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody UserDto user) {
		this.userService.createUser(user);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> loadUser(@PathVariable Integer id) {
		UserDto userDto = this.userService.loadUser(id);
		return ResponseEntity.ok(userDto);
	}
}
