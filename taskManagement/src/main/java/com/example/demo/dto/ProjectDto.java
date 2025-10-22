package com.example.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
	
	private Integer id;
	private String title;
	private List<TaskDto> tasks;
}
