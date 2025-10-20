package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.ProjectPO;
import com.example.demo.repository.ProjectRepository;

@Service
public class ProjectService {
	
	private final ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public List<ProjectPO> loadAllProjects() {
		return projectRepository.findAll();
	}
	
	public ProjectPO createProject(ProjectPO project) {
		return projectRepository.save(project);
	}
	
	public void deleteProject(Integer id) {
		projectRepository.deleteById(id);
	}
	
	public ProjectPO updateProject(Integer id, ProjectPO newProject) {
		Optional<ProjectPO> project = projectRepository.findById(id);
		project.get().setName(newProject.getName());
		project.get().setTasks(newProject.getTasks());
		return projectRepository.save(project.get());
	}
	
	

}
