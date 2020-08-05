package com.ats.manoharweb.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.SkuConfigDetail;

public interface SkuConfigDetailRepo extends JpaRepository<SkuConfigDetail, Integer> {

	@Transactional
	@Modifying
	@Query(value="UPDATE sku_config_detail SET del_status=1 WHERE sku_id=:skuDtlId",nativeQuery=true)
	int deleteSkuDtlById(@Param("skuDtlId") int skuDtlId);

}
