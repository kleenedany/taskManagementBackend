package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserPO;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Integer> {

}
