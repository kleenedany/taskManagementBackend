package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskDto;
import com.example.demo.dto.UserDto;
import com.example.demo.models.ProjectPO;
import com.example.demo.models.TaskPO;
import com.example.demo.models.UserPO;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;
	
	public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
		this.taskRepository = taskRepository;
		this.projectRepository = projectRepository;
	}
	
	/**
	 * Update a specific task
	 * @param id The id of the task
	 * @param taskDto The updated task
	 * @return
	 */
	public void updateTask(Integer id, TaskDto taskDto) {
		Optional<TaskPO> taskPO = this.taskRepository.findById(id);
		taskPO.get().setDescription(taskDto.getDescription());
		taskPO.get().setStatus(taskDto.getStatus());
		taskPO.get().setTitle(taskDto.getTitle());
		taskPO.get().setUsers(this.convertUserDtoTOUserPO(taskDto.getUsers()));
		this.taskRepository.save(taskPO.get());
	}
	
	/**
	 * Delete a specific task
	 * @param id The id of the task
	 */
	public void deleteTask(Integer id) {
		this.taskRepository.deleteById(id);
	}
	
	/**
	 * Create a new task
	 * @param taskDto The details of the task
	 * @param projectId the id of the project the tasks belongs to
	 */
	public void createTask(TaskDto taskDto, Integer projectId) {
		this.taskRepository.save(this.convertTaskDtoToTaskPO(taskDto, projectId));
	}
	
	/**
	 * Get a specific task
	 * @param id The id of the task
	 * @return The found task
	 */
	public TaskDto loadTask(Integer id) {
		Optional<TaskPO> taskPO = this.taskRepository.findById(id);
		return this.convertTaskPOToTaskDto(taskPO.get(), taskPO.get().getProject().getId());
	}
	
	/**
	 * Convert a UserDto object into a UserPO object
	 * @param userListDto The UserDto objects
	 * @return The UserPO objects
	 */
	private List<UserPO> convertUserDtoTOUserPO(List<UserDto> userListDto) {
		List<UserPO> userListPO = new ArrayList<>();
		
		userListDto.stream().forEach(userDto -> {
			UserPO userPO = new UserPO();
			userPO.setFirstName(userDto.getFirstName());
			userPO.setId(userDto.getId());
			userPO.setLastName(userDto.getLastName());
			
			userListPO.add(userPO);
		});
		
		return userListPO;
	}
	
	/**
	 * Convert a TaskDto object into a TaskPO object
	 * @param taskDto The TaskDto object
	 * @param projectId The id of the project
	 * @return The TaskPO object
	 */
	private TaskPO convertTaskDtoToTaskPO(TaskDto taskDto, Integer projectId) {
			Optional<ProjectPO> projectPO = this.projectRepository.findById(projectId);
			TaskPO taskPO = new TaskPO();
			taskPO.setDescription(taskDto.getDescription());
			taskPO.setId(taskDto.getId());
			taskPO.setProject(projectPO.get());
			taskPO.setStatus(taskDto.getStatus());
			taskPO.setTitle(taskDto.getTitle());
			taskPO.setUsers(this.convertUserDtoTOUserPO(taskDto.getUsers()));
		return taskPO;
	}
	
	/**
	 * Convert a TaskPO object into a TaskDto object
	 * @param taskPO The TaskPO object
	 * @param projectId The id of the project
	 * @return The TaskDto object
	 */
	private TaskDto convertTaskPOToTaskDto(TaskPO taskPO, Integer projectId) {
			TaskDto taskDto = new TaskDto();
			taskDto.setDescription(taskPO.getDescription());
			taskDto.setId(taskPO.getId());
			taskDto.setProjectId(projectId);
			taskDto.setStatus(taskPO.getStatus());
			taskDto.setTitle(taskPO.getTitle());
			taskDto.setUsers(this.convertUserPOToUserDto(taskPO.getUsers()));
		
		return taskDto;
	}
	
	/**
	 * Convert a UserPO object into a UserDto object
	 * @param userListPO The UserPO objects
	 * @return The UserDto objects
	 */
	private List<UserDto> convertUserPOToUserDto(List<UserPO> userListPO) {
		List<UserDto> userListDto = new ArrayList<>();
		
		userListPO.stream().forEach(userPO -> {
			UserDto userDto = new UserDto();
			userDto.setFirstName(userPO.getFirstName());
			userDto.setId(userPO.getId());
			userDto.setLastName(userPO.getLastName());
			
			userListDto.add(userDto);
		});
		
		return userListDto;
	}

}
