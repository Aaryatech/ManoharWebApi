package com.ats.manoharweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tn_config_related_product")
public class ConfigRelatedProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "related_product_id")
	private int relatedProductId;
	
	@Column(name = "product_id")
	private int productId;

	@Column(name = "configr_related_product_ids")
	private String configrRelatedProductIds;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "maker_user_id")
	private int makerUserId;
	
	@Column(name = "maker_datetime")
	private String makerDatetime;
	
	@Column(name = "updated_date_time")
	private String updatedDateTime	;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getRelatedProductId() {
		return relatedProductId;
	}

	public void setRelatedProductId(int relatedProductId) {
		this.relatedProductId = relatedProductId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getConfigrRelatedProductIds() {
		return configrRelatedProductIds;
	}

	public void setConfigrRelatedProductIds(String configrRelatedProductIds) {
		this.configrRelatedProductIds = configrRelatedProductIds;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getMakerDatetime() {
		return makerDatetime;
	}

	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}

	public String getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
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
		return "ConfigRelatedProduct [relatedProductId=" + relatedProductId + ", productId=" + productId
				+ ", configrRelatedProductIds=" + configrRelatedProductIds + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", updatedDateTime="
				+ updatedDateTime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2="
				+ exVar2 + "]";
	}
	
	
}
