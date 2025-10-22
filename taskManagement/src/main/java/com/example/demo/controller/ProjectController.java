package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProjectDto;
import com.example.demo.service.ProjectService;

@RestController
public class ProjectController {
	
	private final ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/projects")
	public ResponseEntity<List<ProjectDto>> loadAllProjects() {
		List<ProjectDto> projectListDto = this.projectService.loadAllProjects();
		return ResponseEntity.ok(projectListDto);
	}
	
	@PostMapping(value = "/projects")
	public ResponseEntity<Void> createProject(@RequestBody ProjectDto project) {
		if(project.getTasks() == null) {
			project.setTasks(new ArrayList<>());
		}
		this.projectService.createProject(project);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
		this.projectService.deleteProject(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/projects/{id}")
	public ResponseEntity<Void> updateProject(@PathVariable Integer id, @RequestBody ProjectDto project){
		this.projectService.updateProject(id, project);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/projects/{id}")
	public ResponseEntity<ProjectDto> loadProject(@PathVariable Integer id) {
		ProjectDto projectDto = this.projectService.loadProject(id);
		return ResponseEntity.ok(projectDto);
	}

}
