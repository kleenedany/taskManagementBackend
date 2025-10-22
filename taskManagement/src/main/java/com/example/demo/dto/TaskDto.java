package com.example.demo.dto;

import java.util.List;

import com.example.demo.enums.TaskStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
	
	private Integer id;
	private String title;
	private String description;
	private TaskStatusEnum status;
	private Integer projectId;
	private List<UserDto> users;
}
