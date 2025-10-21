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
import com.example.demo.models.TaskPO;
import com.example.demo.service.TaskService;

@RestController
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping("/task/{id}")
	public ResponseEntity<TaskPO> updateTask(@PathVariable Integer id, @RequestBody TaskPO task) {
		TaskPO updatedTask = taskService.updateTask(id, task);
		return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/task")
	public List<TaskPO> loadProjectTasks(@RequestBody ProjectPO project) {
		return taskService.loadProjectTasks(project);
	}
	
	@PostMapping("/task") 
	public TaskPO createTask(@RequestBody TaskPO task) {
		return taskService.createTask(task);
	}
}
