package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.SubCat;

public interface SubCatRepo extends JpaRepository<SubCat, Integer> {

	@Query(value="SELECT\n" + 
			"    sub.sub_cat_id,\n" + 
			"    sub.sub_cat_name,\n" + 
			"    sub.cat_id,\n" + 
			"    sub.del_status,\n" + 
			"    sub.prefix,\n" + 
			"    sub.seq_no,\n" + 
			"    sub.company_id,\n" + 
			"    sub.ex_int1,\n" + 
			"    sub.ex_int2,\n" + 
			"    sub.ex_var1,\n" + 
			"    cat.cat_name as ex_var2\n" + 
			"FROM\n" + 
			"    m_cat_sub sub,\n" + 
			"    m_category cat\n" + 
			"WHERE\n" + 
			"    sub.del_status=0 AND\n" + 
			"    sub.company_id=:compId AND\n" + 
			"    cat.del_status=0 AND\n" + 
			"    cat.cat_id=sub.cat_id ORDER BY sub_cat_id DESC",nativeQuery=true)
	List<SubCat> getAllSubCategory(@Param("compId") int compId);
	
	SubCat findBySubCatIdAndDelStatus(int subCatId, int del);

	@Transactional
	@Modifying
	@Query(value="UPDATE `m_cat_sub` SET del_status=1 AND sub_cat_id=:subCatId", nativeQuery=true)
	int deleteBySubCatId(@Param("subCatId") int subCatId);
	
	SubCat findBySubCatNameIgnoreCaseAndCompanyIdAndDelStatus(String subCatName, int compId, int del);
	
	SubCat findBySubCatNameIgnoreCaseAndCompanyIdAndDelStatusAndSubCatIdNot(String subCatName, int compId, int del, int subCatId);

	SubCat findByPrefixIgnoreCaseAndCompanyIdAndDelStatusAndSubCatIdNot(String prefix, int compId, int i, int subCatId);

	SubCat findByPrefixIgnoreCaseAndCompanyIdAndDelStatus(String prefix, int compId, int i);
	
	List<SubCat> findByCatIdAndCompanyIdAndDelStatus(int catId, int compId, int del);
}
