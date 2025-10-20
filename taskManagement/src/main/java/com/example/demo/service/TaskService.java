package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.ProjectPO;
import com.example.demo.models.TaskPO;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public TaskPO updateTask(Integer id, TaskPO newTask) {
		Optional<TaskPO> task = taskRepository.findById(id);
		task.get().setDescription(newTask.getDescription());
		task.get().setProject(newTask.getProject());
		task.get().setStatus(newTask.getStatus());
		task.get().setTitle(newTask.getTitle());
		task.get().setUsers(newTask.getUsers());
		return taskRepository.save(task.get());
	}
	
	public void deleteTask(Integer id) {
		taskRepository.deleteById(id);
	}
	
	public TaskPO createTask(TaskPO task) {
		return taskRepository.save(task);
	}
	
	public List<TaskPO> loadProjectTasks(ProjectPO projectPO) {
		return taskRepository.findByProject(projectPO);
	}

}
