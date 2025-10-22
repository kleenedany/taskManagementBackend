package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskDto;
import com.example.demo.service.TaskService;

@RestController
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Void> updateTask(@PathVariable Integer id, @RequestBody TaskDto task) {
		this.taskService.updateTask(id, task);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/projects/{projectId}/tasks") 
	public ResponseEntity<Void> createTask(@PathVariable Integer projectId, @RequestBody TaskDto task) {
		this.taskService.createTask(task, projectId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<TaskDto> loadTask(@PathVariable Integer id) {
		TaskDto taskDto = this.taskService.loadTask(id);
		return ResponseEntity.ok(taskDto);
	}
}
