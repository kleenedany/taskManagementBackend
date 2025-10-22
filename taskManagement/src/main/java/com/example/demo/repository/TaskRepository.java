package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProjectPO;
import com.example.demo.models.TaskPO;
import java.util.List;


/** Repository for TASK table database access */
@Repository
public interface TaskRepository extends JpaRepository<TaskPO, Integer> {
	List<TaskPO> findByProject(ProjectPO project);

}
