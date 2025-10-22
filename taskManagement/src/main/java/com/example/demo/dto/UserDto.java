package com.example.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private List<TaskDto> tasks;

}
