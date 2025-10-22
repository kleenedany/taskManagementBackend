package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.TaskDto;
import com.example.demo.dto.UserDto;
import com.example.demo.models.ProjectPO;
import com.example.demo.models.TaskPO;
import com.example.demo.models.UserPO;
import com.example.demo.repository.ProjectRepository;

@Service
public class ProjectService {
	
	private final ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	/**
	 * Get all projects
	 * @return all found projects
	 */
	public List<ProjectDto> loadAllProjects() {
		List<ProjectPO> projectListPO = this.projectRepository.findAll();
		List<ProjectDto> projectListDto = new ArrayList<>();
		
		projectListPO.stream().forEach(projectPO -> {
			projectListDto.add(this.convertProjectPOToProjectDto(projectPO));
		});
		
		return projectListDto;
	}
	
	/**
	 * Get a specific project
	 * @param id The id of the project
	 * @return The found project
	 */
	public ProjectDto loadProject(Integer id) {
		Optional<ProjectPO> project = this.projectRepository.findById(id);
		return this.convertProjectPOToProjectDto(project.get());
	}
	
	/** 
	 * Create a new project
	 * @param project The details of the project
	 */
	public void createProject(ProjectDto project) {
		 this.projectRepository.save(this.convertProjectDtoToProjectPO(project));
	}
	
	/**
	 * Delete a specific project
	 * @param id The id of the project
	 */
	public void deleteProject(Integer id) {
		this.projectRepository.deleteById(id);
	}
	
	/**
	 * Update a specific project
	 * @param id The id of the project
	 * @param projectDto The updated project
	 */
	public void updateProject(Integer id, ProjectDto projectDto) {
		Optional<ProjectPO> project = this.projectRepository.findById(id);
		project.get().setTitle(projectDto.getTitle());
		this.projectRepository.save(project.get());
	}
	
	/**
	 * Convert a ProjectDto object into a ProjectPO object
	 * @param projectdto The ProjectDto object
	 * @return The ProjectPO object
	 */
	private ProjectPO convertProjectDtoToProjectPO(ProjectDto projectdto) {
		ProjectPO projectPO = new ProjectPO();
		projectPO.setId(projectdto.getId());
		projectPO.setTitle(projectdto.getTitle());
		projectPO.setTasks(this.convertTaskDtoToTaskPO(projectdto.getTasks(), projectdto.getId()));
		return projectPO;
	}
	
	/**
	 * Convert a TaskDto object into a TaskPO object
	 * @param taskListDto The TaskDto objects
	 * @param projectId The id of the project
	 * @return The TaskPO objects
	 */
	private List<TaskPO> convertTaskDtoToTaskPO(List<TaskDto> taskListDto, Integer projectId) {
		List<TaskPO> taskLisPO = new ArrayList<>();
		taskListDto.stream().forEach(taskDto -> {
			Optional<ProjectPO> projectPO = this.projectRepository.findById(projectId);
			TaskPO taskPO = new TaskPO();
			taskPO.setDescription(taskDto.getDescription());
			taskPO.setId(taskDto.getId());
			taskPO.setProject(projectPO.get());
			taskPO.setStatus(taskDto.getStatus());
			taskPO.setTitle(taskDto.getTitle());
			taskPO.setUsers(this.convertUserDtoTOUserPO(taskDto.getUsers()));
			
			taskLisPO.add(taskPO);
		});
		
		return taskLisPO;
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
	 * Convert a ProjectPO object into a ProjectDto object
	 * @param projectPO The ProjectPO object
	 * @return The ProjectDto object
	 */
	private ProjectDto convertProjectPOToProjectDto(ProjectPO projectPO) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(projectPO.getId());
		projectDto.setTitle(projectPO.getTitle());
		projectDto.setTasks(this.convertTaskPOToTaskDto(projectPO.getTasks(), projectPO.getId()));
		return projectDto;
	}
	
	/**
	 * Convert a TaskPO object into a TaskDto object
	 * @param taskListPO The TaskPO objects
	 * @param projectId The id of the project
	 * @return The TaskDto objects
	 */
	private List<TaskDto> convertTaskPOToTaskDto(List<TaskPO> taskListPO, Integer projectId) {
		List<TaskDto> taskListDto = new ArrayList<>();
		taskListPO.stream().forEach(taskPO -> {
			TaskDto taskDto = new TaskDto();
			taskDto.setDescription(taskPO.getDescription());
			taskDto.setId(taskPO.getId());
			taskDto.setProjectId(projectId);
			taskDto.setStatus(taskPO.getStatus());
			taskDto.setTitle(taskPO.getTitle());
			taskDto.setUsers(this.convertUserPOToUserDto(taskPO.getUsers()));
			
			taskListDto.add(taskDto);
		});
		
		return taskListDto;
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
