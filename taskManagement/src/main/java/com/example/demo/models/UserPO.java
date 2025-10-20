package com.example.demo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
	private Integer id;
	
	@Column(name= "firstname", nullable = false)
	private String firstName;
	
	@Column(name="lastname", nullable = false)
	private String lastName;
	
	@ManyToMany(mappedBy = "users")
	private List<TaskPO> tasks;

}
