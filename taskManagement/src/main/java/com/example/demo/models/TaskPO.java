package com.example.demo.models;

import java.util.List;

import com.example.demo.enums.TaskStatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
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
import jakarta.validation.constraints.NotNull;
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
	@Schema(description = "Unique identifier of the task", accessMode = Schema.AccessMode.READ_ONLY)
	private Integer id;
	
	@NotNull
	@Column(name = "title", nullable = false)
	@Schema(description = "Title of the task", requiredMode = Schema.RequiredMode.REQUIRED)
	private String title;
	
	@Column(name = "description", length = 1000)
	@Schema(description = "Description of the task", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String description;
	
	@NotNull
	@Column(name = "status", nullable = false)
	@Schema(description = "Status of the task. Available status are OPEN, IN PROGRESS and DONE", requiredMode = Schema.RequiredMode.REQUIRED)
	private TaskStatusEnum status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@JsonBackReference
	@Schema(description = "The project belonging to the task")
	private ProjectPO project;
	
	@ManyToMany
	@JoinTable(name ="Task_User", joinColumns= @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@Schema(description="List of users belonging to the task")
	private List<UserPO> users;
	
	
	

}
