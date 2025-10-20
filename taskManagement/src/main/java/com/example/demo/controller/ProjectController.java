package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ProjectPO;
import com.example.demo.service.ProjectService;

@RestController
public class ProjectController {
	
	private final ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/projects")
	public List<ProjectPO> loadAllProjects() {
		return projectService.loadAllProjects();
	}
	
	@PostMapping("/projects")
	public ProjectPO createProject(@RequestBody ProjectPO project) {
		return projectService.createProject(project);
	}
	
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
		projectService.deleteProject(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/projects/{id}")
	public ResponseEntity<ProjectPO> updateProject(@PathVariable Integer id, @RequestBody ProjectPO project){
		ProjectPO updatedTask = projectService.updateProject(id, project);
		return ResponseEntity.ok(updatedTask);
	}

}
