package com.example.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO {
	
	private Integer id;
	private String name;
	private List<TaskDTO> tasks;
}
