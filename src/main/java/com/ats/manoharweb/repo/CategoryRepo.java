package com.ats.manoharweb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.Category;
import java.lang.String;
import java.util.List;

import javax.transaction.Transactional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	@Query(value="SELECT COUNT(cat_name) FROM `m_category` WHERE company_id=:compId AND cat_name LIKE %:ch%", nativeQuery=true)
	public int getMaxCatCodeCount(@Param("ch") char ch, @Param("compId") int compId);
	
	Category findByCatNameIgnoreCaseAndCompanyIdAndDelStatus(String catName, int compId, int del);
	
	Category findByCatNameIgnoreCaseAndCompanyIdAndDelStatusAndCatIdNot(String catName, int compId, int del, int catId);
	
	List<Category> findByDelStatusAndCompanyIdOrderByCatIdDesc(int del, int compId);
	
	Category findByCatId(int catId);

	@Transactional
	@Modifying
	@Query(value="UPDATE `m_category` SET del_status=1 WHERE cat_id=:catId",nativeQuery=true)
	public int deleteByCatId(@Param("catId") int catId);
}
