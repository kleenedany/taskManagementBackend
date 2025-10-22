package com.example.demo.models;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="APP_USER")
public class UserPO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the user", accessMode = Schema.AccessMode.READ_ONLY)
	private Integer id;
	
	@NotNull
	@Column(name= "firstname", nullable = false)
	@Schema(description = "Firstname of the user", requiredMode = Schema.RequiredMode.REQUIRED )
	private String firstName;
	
	@NotNull
	@Column(name="lastname", nullable = false)
	@Schema(description = "Lastname of the user", requiredMode = Schema.RequiredMode.REQUIRED)
	private String lastName;
	
	@ManyToMany(mappedBy = "users")
	private List<TaskPO> tasks;

}
