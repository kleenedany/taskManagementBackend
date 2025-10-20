package com.example.demo.dto;

import java.util.List;

import com.example.demo.enums.TaskStatus;
import com.example.demo.models.ProjectPO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
	
	private Integer id;
	private String title;
	private String description;
	private TaskStatus status;
	private ProjectPO project;
	private List<UserDTO> users;

	

}
