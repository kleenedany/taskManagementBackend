package com.example.demo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PROJECT")
public class ProjectPO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the project", accessMode = Schema.AccessMode.READ_ONLY) 
	private Integer id;
	
	@NotNull
	@Column(name="title", nullable = false)
	@Schema(description = "Title of the project", requiredMode = Schema.RequiredMode.REQUIRED)
	private String title;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference
	@Schema(description = "List of tasks belonging to the project")
	private List<TaskPO> tasks;
}
