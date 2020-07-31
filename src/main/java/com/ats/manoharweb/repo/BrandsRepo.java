package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.Brands;

public interface BrandsRepo extends JpaRepository<Brands, Integer> {
	
	List<Brands> findByCompanyIdAndDelStatusOrderByBrandIdDesc(int compId, int del);
	
	Brands findByCompanyIdAndBrandId(int compId, int brandId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_brand SET del_status=1 WHERE brand_id=:brandId", nativeQuery=true)
	int deleteBrandById(@Param("brandId") int brandId);
	
	@Query(value="SELECT COUNT(brand_name) FROM `m_brand` WHERE company_id=:compId AND brand_name LIKE %:ch%", nativeQuery=true)
	public int getMaxBrandCodeCount(@Param("ch") char ch, @Param("compId") int compId);
	
	Brands findByCompanyIdAndDelStatusAndBrandNameIgnoreCase(int compId, int del, String brandName);
	
	Brands findByCompanyIdAndDelStatusAndBrandNameIgnoreCaseAndBrandIdNot(int compId, int del, String brandName, int brandId);
	
}
