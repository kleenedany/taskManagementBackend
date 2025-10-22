package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.models.UserPO;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
		public ResponseEntity<List<UserDto>> loadAllUser() {
			return ResponseEntity.ok(this.userService.loadAllUsers());
		}
	
	@PostMapping("user/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody UserDto user) {
		userService.updateUser(id, user);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/user")
	public ResponseEntity<Void> createUser(@RequestBody UserPO user) {
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/user")
	public ResponseEntity<UserDto> loadUser(@PathVariable Integer id) {
		UserDto userDto = this.userService.loadUser(id);
		return ResponseEntity.ok(userDto);
	}
	
	

}
