package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.UserType;

public interface UserTypeRepo extends JpaRepository<UserType, Integer> {

	List<UserType> findByDelStatusAndComapnyIdRequired(int del, int compId);
	
	UserType findByUserTypeIdAndComapnyIdRequired(int userTypeId, int compId);
	
	UserType findByUserTypeNameIgnoreCaseAndComapnyIdRequired(String userType, int compId);
	
	UserType findByUserTypeNameIgnoreCaseAndComapnyIdRequiredAndUserTypeIdNot(String userType, int compId, int userId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `mn_user_type` SET del_status=1 WHERE user_type_id=:userTypeId AND  comapny_id_required=:compId",nativeQuery=true)
	public int deleteUserTypeById(@Param("userTypeId") int userTypeId, @Param("compId") int compId);
	
}
