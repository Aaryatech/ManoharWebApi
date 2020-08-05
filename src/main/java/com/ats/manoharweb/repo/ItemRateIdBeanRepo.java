package com.ats.manoharweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.ItemRateIdBean;

public interface ItemRateIdBeanRepo extends JpaRepository<ItemRateIdBean, Integer> {

	@Query(value="SELECT\n" + 
			"    type.sku_rate_type_id AS rate_id,\n" + 
			"    type.rate_type_name AS rate_name,\n" + 
			"    sku.sku_rate AS rate,\n" + 
			"    sku.item_id AS item_id\n" + 
			"FROM\n" + 
			"    rate_type type,\n" + 
			"    sku_configuration sku\n" + 
			"WHERE\n" + 
			"    type.del_status=0 AND\n" + 
			"    sku.del_status=0 AND\n" + 
			"    sku.company_id=:compId AND\n" + 
			"    type.company_id=:compId AND\n" + 
			"    sku.item_id=:itemId AND\n" + 
			"    sku.sku_rate_type_id=type.sku_rate_type_id", nativeQuery=true)
	public List<ItemRateIdBean> getAllRateTypeItemConfig(@Param("compId") int compId, @Param("itemId") int itemId);
}
