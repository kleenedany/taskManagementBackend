package com.example.demo.models;

import java.util.List;

import com.example.demo.enums.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TASK")
public class TaskPO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "status", nullable = false)
	private TaskStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private ProjectPO project;
	
	@ManyToMany
	@JoinTable(name ="Task_User", joinColumns= @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserPO> users;
	
	
	

}
