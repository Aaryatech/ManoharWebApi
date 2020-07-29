package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.Designation;


public interface DesignationRepo extends JpaRepository<Designation, Integer> {
	
  List<Designation> findByDelStatusAndExInt1AndIsActiveOrderByDesignationIdDesc(int del, int compId, int status);
  
  List<Designation> findByDelStatusAndExInt1OrderByDesignationIdDesc(int del, int compId);
  
  Designation findByDesignationIdAndExInt1(int desigId, int compId);
  
  @Transactional
  @Modifying
  @Query(value="UPDATE `mn_designation` SET del_status=1 WHERE designation_id=:desigId AND ex_int1=:compId",nativeQuery=true)
  public int deleteDesgnation(@Param("desigId") int desigId, @Param("compId") int compId);
  
  
  Designation findByDesignationIgnoreCaseAndExInt1AndDelStatus(String designation, int compId, int del);
  
  Designation findByDesignationIgnoreCaseAndExInt1AndDelStatusAndDesignationIdNot(String designation, int compId, int del, int desigId);
}
