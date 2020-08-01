package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.ProductStatus;

public interface ProductStatusRepo extends JpaRepository<ProductStatus, Integer>{

	List<ProductStatus> findByDelStatusAndCompanyIdOrderByProductStatusIdDesc(int del, int compId);
	
	ProductStatus findByCompanyIdAndProductStatusId(int compId, int productId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_product_status` SET del_status=1 WHERE product_status_id=:productId",nativeQuery=true)
	public int deleteProductStatusById(@Param("productId") int productId);
	
	ProductStatus findByDelStatusAndCompanyIdAndProductStatus(int del, int compId, String product);
	
	ProductStatus findByDelStatusAndCompanyIdAndProductStatusAndProductStatusIdNot(int del, int compId, String product, int productId);
}
