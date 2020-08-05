package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.SkuConfiguration;

public interface SkuConfigurationRepo extends JpaRepository<SkuConfiguration, Integer> {

	List<SkuConfiguration> findByCompanyIdAndDelStatusOrderBySkuIdDesc(int compId, int del);

	@Query(value="SELECT * FROM `sku_configuration` WHERE company_id=:compId AND item_id=:itemId AND del_status=0", nativeQuery=true)
	List<SkuConfiguration> findByDelStatusAndCompanyIdAndItemId(int compId, int itemId);
	

	@Transactional
	@Modifying
	@Query(value="UPDATE `sku_configuration` SET sku_name=:skuName, sku_stock_qty=:skuStockQty, stock_to_sale_uom=:skuSellStock, sale_uom=:skuUom  WHERE sku_id=:skuId",nativeQuery=true)
	int updateSku(@Param("skuId") int skuId, @Param("skuName") String skuName, @Param("skuUom") int skuUom, @Param("skuStockQty") float skuStockQty,
			@Param("skuSellStock") float skuSellStock);

	@Transactional
	@Modifying
	@Query(value="UPDATE `sku_configuration` SET del_status=1 WHERE sku_id=:skuDtlId",nativeQuery=true)
	int deleteSkuConfiguration(@Param("skuDtlId")int skuDtlId);
	
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `sku_configuration` SET del_status=1 WHERE item_id=:itemId",nativeQuery=true)
	int deleteSkuConfigurationByItemId(@Param("itemId") int itemId);
}
