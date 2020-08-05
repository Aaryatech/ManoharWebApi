package com.ats.manoharweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sku_config_detail")
public class SkuConfigDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "sku_detail_id")
	private int skuDetailId;	
	
	@Column(name = "rate_type_id")
	private int rateTypeId;

	@Column(name = "rate_type_mrp")
	private float rateTypeMrp;	
	
	@Column(name = "sku_id")
	private int skuId;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getSkuDetailId() {
		return skuDetailId;
	}

	public void setSkuDetailId(int skuDetailId) {
		this.skuDetailId = skuDetailId;
	}

	public int getRateTypeId() {
		return rateTypeId;
	}

	public void setRateTypeId(int rateTypeId) {
		this.rateTypeId = rateTypeId;
	}

	public float getRateTypeMrp() {
		return rateTypeMrp;
	}

	public void setRateTypeMrp(float rateTypeMrp) {
		this.rateTypeMrp = rateTypeMrp;
	}

	public int getSkuId() {
		return skuId;
	}

	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	@Override
	public String toString() {
		return "SkuConfigDetail [skuDetailId=" + skuDetailId + ", rateTypeId=" + rateTypeId + ", rateTypeMrp="
				+ rateTypeMrp + ", skuId=" + skuId + ", companyId=" + companyId + ", delStatus=" + delStatus
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
}
