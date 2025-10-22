package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.models.UserPO;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * Get all user
	 * @return return all found user
	 */
	public List<UserDto> loadAllUsers() {
		List<UserPO> userListPO = this.userRepository.findAll();
		List<UserDto> userListDto = new ArrayList<>();
		
		userListPO.stream().forEach(userPO -> {
			userListDto.add(this.convertUserPOToUserDto(userPO));
		});
		
		return userListDto;
	}
	
	/**
	 * Get a specific user
	 * @param id The id of the project
	 * @return The found project
	 */
	public UserDto loadUser(Integer id) {
		Optional<UserPO> userPO = this.userRepository.findById(id);
		return this.convertUserPOToUserDto(userPO.get());
	}
	
	/**
	 * Update a specific user
	 * @param id The id of the user
	 * @param userPO The updated user
	 */
	public void updateUser(Integer id, UserDto userDto) {
		Optional<UserPO> userPO = userRepository.findById(id);
		userPO.get().setFirstName(userDto.getFirstName());
		userPO.get().setLastName(userDto.getLastName());
		 this.userRepository.save(userPO.get());
	}
	
	/**
	 * Delete a specific user
	 * @param id The id of the user
	 */
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
	
	/**
	 * Create a new user
	 * @param user The details of the user
	 */
	public void createUser(UserDto user) {
		 this.userRepository.save(this.convertUserDtoTOUserPO(user));
	}
	
	/**
	 * Convert a UserPO object into a UserDto object
	 * @param userPO The UserPO object
	 * @return The UserDto object
	 */
	private UserDto convertUserPOToUserDto(UserPO userPO) {
			UserDto userDto = new UserDto();
			userDto.setFirstName(userPO.getFirstName());
			userDto.setId(userPO.getId());
			userDto.setLastName(userPO.getLastName());
			
		return userDto;
	}
	
	/**
	 * Convert a UserDto object into a UserPO object
	 * @param userDto The UserDto object
	 * @return The UserPO object
	 */
	private UserPO convertUserDtoTOUserPO(UserDto userDto) {
			UserPO userPO = new UserPO();
			userPO.setFirstName(userDto.getFirstName());
			userPO.setId(userDto.getId());
			userPO.setLastName(userDto.getLastName());
		
		return userPO;
	}
}
