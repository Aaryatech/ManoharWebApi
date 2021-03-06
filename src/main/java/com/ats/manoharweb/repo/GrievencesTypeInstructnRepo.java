package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.GrievencesTypeInstructn;


public interface GrievencesTypeInstructnRepo extends JpaRepository<GrievencesTypeInstructn, Integer> {

	List<GrievencesTypeInstructn> findByDelStatusAndCompanyIdOrderByGrevTypeIdDesc(int del, int compId);
	
	GrievencesTypeInstructn findByDelStatusAndGrevTypeIdAndCompanyId(int del, int grievTypeId,int compId);
	
	GrievencesTypeInstructn findByCaptionIgnoreCaseAndCompanyId(String caption, int compId);
	
	GrievencesTypeInstructn findByCaptionIgnoreCaseAndCompanyIdAndGrevTypeIdNot(String caption, int compId, int grievTypeId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `mn_grievences_type_instructn` SET del_status=1 WHERE grev_type_id=:grievTypeId",nativeQuery=true)
	public int deleteGrievancTypeInst(@Param("grievTypeId") int grievTypeId);
}
