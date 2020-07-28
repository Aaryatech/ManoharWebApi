package com.ats.manoharweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.manoharweb.models.Designation;


public interface DesignationRepo extends JpaRepository<Designation, Integer> {
	
  List<Designation> findByDelStatusOrderByDesignationIdDesc(int del);
  
  
}
