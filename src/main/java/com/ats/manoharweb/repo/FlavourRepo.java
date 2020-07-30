package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.Flavour;

public interface FlavourRepo extends JpaRepository<Flavour, Integer> {

		List<Flavour> findByCompanyIdAndDelStatusOrderByFlavourIdDesc(int compId, int del);
		
		Flavour findByFlavourId(int flavourId);
		
		@Transactional
		@Modifying
		@Query(value="UPDATE `m_flavour` SET del_status=1 WHERE flavour_id=:flavourId",nativeQuery=true)
		public int deleteFlavourById(@Param("flavourId") int flavourId);
		
		@Query(value="SELECT COUNT(flavour_name) FROM `m_flavour` WHERE company_id=:compId AND flavour_name LIKE %:ch%", nativeQuery=true)
		public int getMaxFlvrCodeCount(@Param("ch") char ch, @Param("compId") int compId);
		
		Flavour findByFlavourNameIgnoreCaseAndCompanyIdAndDelStatus(String flavourName, int compId, int del);
		
		Flavour findByFlavourNameIgnoreCaseAndCompanyIdAndDelStatusAndFlavourIdNot(String flavourName, int compId, int del, int flavourId);
}
