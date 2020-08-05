package com.ats.manoharweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.SkuItemDtl;

public interface SkuItemDtlRepo extends JpaRepository<SkuItemDtl, Integer> {

	@Query(value="SELECT\n" + 
			"    dtl.sku_detail_id,\n" + 
			"    dtl.rate_type_mrp,\n" + 
			"    dtl.rate_type_id,\n" + 
			"    dtl.sku_id,\n" + 
			"    type.sku_rate_type_id,\n" + 
			"    type.rate_type_name\n" + 
			"FROM\n" + 
			"    sku_configuration sku,\n" + 
			"    sku_config_detail dtl,\n" + 
			"    m_item itm,\n" + 
			"    rate_type type\n" + 
			"WHERE\n" + 
			"    sku.del_status=0 AND\n" + 
			"    dtl.del_status=0 AND\n" + 
			"    itm.del_status=0 AND\n" + 
			"    itm.item_id=sku.item_id AND\n" + 
			"    sku.sku_id=dtl.sku_id AND\n" + 
			"    itm.company_id=:compId AND\n" + 
			"    sku.company_id=:compId AND\n" + 
			"    dtl.company_id=:compId AND\n" + 
			"    type.company_id=:compId AND\n" + 
			"    type.sku_rate_type_id=dtl.rate_type_id",nativeQuery=true)
	public List<SkuItemDtl> getAllSkuDtls(@Param("compId") int compId);
}
