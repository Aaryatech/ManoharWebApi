package com.ats.manoharweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.manoharweb.models.GetItemDetails;
@Repository
public interface GetItemDetailsRepo extends JpaRepository<GetItemDetails, Integer> {

	@Query(value="SELECT\n" + 
			"   t1.item_id,\n" + 
			"   t1.item_code,\n" + 
			"   t1.item_name,\n" + 
			"   COALESCE(t2.cnt_sku_id,0) AS cnt_sku_id\n" + 
			"FROM\n" + 
			"(SELECT\n" + 
			"   itm.item_id,\n" + 
			"   itm.item_code,\n" + 
			"   itm.item_name\n" + 
			"FROM\n" + 
			"    m_item itm\n" + 
			"WHERE\n" + 
			"    \n" + 
			"    itm.del_status=0 AND   \n" + 
			"    itm.company_id=:compId\n" + 
			"    \n" + 
			") t1\n" + 
			"    LEFT JOIN (SELECT item_id, COUNT(sku_id) AS cnt_sku_id FROM `sku_configuration` WHERE del_status=0 AND company_id=:compId GROUP BY item_id) t2\n" + 
			"    \n" + 
			"    ON t1.item_id=t2.item_id\n" + 
			"    ORDER BY t1.item_id DESC",nativeQuery=true)
	List<GetItemDetails> getItemDetails(@Param("compId") int compId);
}
