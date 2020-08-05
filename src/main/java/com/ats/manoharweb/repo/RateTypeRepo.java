package com.ats.manoharweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.manoharweb.models.RateType;

public interface RateTypeRepo extends JpaRepository<RateType, Integer>{

	List<RateType> findByDelStatusAndCompanyIdOrderBySkuRateTypeIdDesc(int del, int compId);
}
