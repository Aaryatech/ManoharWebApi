package com.ats.manoharweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.manoharweb.models.UserType;


public interface UserTypeRepo extends JpaRepository<UserType, Integer> {

	List<UserType> findByDelStatus(int del);
}
