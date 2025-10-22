package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProjectPO;

/** Repository for PROJECT table database access */
@Repository
public interface ProjectRepository extends JpaRepository<ProjectPO, Integer> {

}
