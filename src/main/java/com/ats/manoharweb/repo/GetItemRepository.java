package com.ats.manoharweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.manoharweb.models.GetItem;


@Repository
public interface GetItemRepository extends JpaRepository<GetItem, Integer> {
	
	@Query(value="SELECT\n" + 
			"    t1.*,\n" + 
			"    COALESCE(t2.checked, 0) AS checked\n" + 
			"FROM\n" + 
			"    (\n" + 
			"   SELECT\n" + 
			"    i.item_id,\n" + 
			"    i.item_name,\n" + 
			"    i.cat_id\n" + 
			"FROM\n" + 
			"    m_item i\n" + 
			"WHERE\n" + 
			"    i.del_status = 0 AND \n" + 
			"    i.item_is_used=0 And\n" + 
			"    i.company_id=:compId AND\n" + 
			"    i.item_id != 0\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT dtl.item_id,\n" + 
			"        1 AS checked\n" + 
			"    FROM\n" + 
			"        m_item dtl\n" + 
			"    INNER JOIN tn_config_related_product itm ON\n" + 
			"        FIND_IN_SET(\n" + 
			"            dtl.item_id,\n" + 
			"            itm.configr_related_product_ids\n" + 
			"        ) > 0 AND dtl.del_status = 0 AND dtl.item_is_used=0 AND itm.del_status = 0 AND itm.ex_int1=:compId AND itm.ex_int1=:compId\n" + 
			"    GROUP BY\n" + 
			"        dtl.item_id\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.item_id = t2.item_id",nativeQuery=true)
		public List<GetItem> getAllRelatedItems(@Param("compId") int compId);
	
		@Query(value="SELECT\n" + 
				"        t1.*,\n" + 
				"        COALESCE(t2.checked,\n" + 
				"        0) AS checked \n" + 
				"    FROM\n" + 
				"        (     SELECT\n" + 
				"    i.item_id,\n" + 
				"    i.item_name,\n" + 
				"    i.cat_id\n" + 
				"FROM\n" + 
				"    m_item i\n" + 
				"WHERE\n" + 
				"    i.del_status = 0 AND \n" + 
				"    i.item_is_used=0 AND\n" + 
				"    i.company_id=:compId AND\n" + 
				"    i.item_id !=:itemId ) t1 \n" + 
				"    LEFT JOIN\n" + 
				"        (\n" + 
				"            SELECT\n" + 
				"                dtl.item_id,\n" + 
				"                1 AS checked     \n" + 
				"            FROM\n" + 
				"                m_item dtl     \n" + 
				"            INNER JOIN\n" + 
				"                tn_config_related_product itm \n" + 
				"                    ON         FIND_IN_SET(             dtl.item_id,\n" + 
				"                itm.configr_related_product_ids         ) > 0 \n" + 
				"                AND dtl.del_status = 0 \n" + 
				"                AND dtl.item_is_used = 0 \n" + 
				"                AND itm.del_status = 0  \n" + 
				"                AND itm.product_id=:itemId\n" + 
				"           	 AND itm.ex_int1=:compId \n" + 
				"            	 AND dtl.company_id=:compId\n" + 
				"            GROUP BY\n" + 
				"                dtl.item_id \n" + 
				"        ) t2 \n" + 
				"            ON     t1.item_id = t2.item_id",nativeQuery=true)
		public List<GetItem> getAllRelatedItemsById(@Param("itemId") int itemId, @Param("compId") int compId);
}

