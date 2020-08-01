package com.ats.manoharweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.manoharweb.models.RawMaterialUom;

@Repository
public interface RawMaterialUomRepository extends JpaRepository<RawMaterialUom, Integer>{
	
	List<RawMaterialUom> findAllByDelStatus(int delStatus);

	RawMaterialUom save(RawMaterialUom rawMaterialUom);

	RawMaterialUom findByUomId(int uomId);
	 
	@Transactional
	 @Modifying
	 @Query("UPDATE RawMaterialUom SET del_status=1 WHERE uom_id=:uomId")
	 int deleteRmUom(@Param("uomId")int uomId);
}
