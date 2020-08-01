package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.manoharweb.models.TaxInfo;

public interface TaxInfoRepo extends JpaRepository<TaxInfo, Integer> {

	List<TaxInfo> findByCompanyIdAndDelStatusOrderByTaxInfoIdDesc(int compId, int del);
	
	TaxInfo findByCompanyIdAndTaxInfoId(int compId, int taxId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_tax_info SET del_status=1 WHERE tax_info_id=:taxId", nativeQuery=true)
	public int deleteTaxById(@Param("taxId") int taxId);
	
	TaxInfo findByCompanyIdAndDelStatusAndTaxTitleIgnoreCase(int compId, int del, String taxTitle);
	
	TaxInfo findByCompanyIdAndDelStatusAndTaxTitleIgnoreCaseAndTaxInfoIdNot(int compId, int del, String taxTitle, int taxId);
}
