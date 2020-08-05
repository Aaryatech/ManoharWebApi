package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

	Item findByItemIdAndCompanyId(int itemId, int compId);

	List<Item> findByCompanyIdAndDelStatusOrderByItemIdDesc(int compId, int del);

	@Transactional
	@Modifying
	@Query(value="UPDATE `m_item` SET del_status=1 WHERE item_id=:itemId", nativeQuery=true)
	int deleteItem(@Param("itemId") int itemId);
}
