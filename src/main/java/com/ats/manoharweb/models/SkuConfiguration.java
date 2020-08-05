package com.ats.manoharweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sku_configuration")
public class SkuConfiguration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "sku_id")
	private int skuId;
	
	@Column(name = "item_id")
	private int itemId;
	
	@Column(name = "sku_name")
	private String skuName;
	
	@Column(name = "sku_rate_type_id")
	private int skuRateTypeId;
	
	@Column(name = "sku_rate")
	private float skuRate;
	
	@Column(name = "sku_stock_qty")
	private int skuStockQty;
	
	@Column(name = "sale_uom")
	private int saleUom;
	
	@Column(name = "stock_to_sale_uom")
	private int stockToSaleUom;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;
	
	@Column(name = "ex_var3")
	private String exVar3;
	
	@Column(name = "ex_float1")
	private float exFloat1;
	
	@Column(name = "ex_float2")
	private float exFloat2;
	
	@Column(name = "company_id")
	private int companyId;

	public int getSkuId() {
		return skuId;
	}

	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public int getSkuRateTypeId() {
		return skuRateTypeId;
	}

	public void setSkuRateTypeId(int skuRateTypeId) {
		this.skuRateTypeId = skuRateTypeId;
	}

	public float getSkuRate() {
		return skuRate;
	}

	public void setSkuRate(float skuRate) {
		this.skuRate = skuRate;
	}

	public int getSkuStockQty() {
		return skuStockQty;
	}

	public void setSkuStockQty(int skuStockQty) {
		this.skuStockQty = skuStockQty;
	}

	public int getSaleUom() {
		return saleUom;
	}

	public void setSaleUom(int saleUom) {
		this.saleUom = saleUom;
	}

	public int getStockToSaleUom() {
		return stockToSaleUom;
	}

	public void setStockToSaleUom(int stockToSaleUom) {
		this.stockToSaleUom = stockToSaleUom;
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

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	public float getExFloat1() {
		return exFloat1;
	}

	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}

	public float getExFloat2() {
		return exFloat2;
	}

	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "SkuConfiguration [skuId=" + skuId + ", itemId=" + itemId + ", skuName=" + skuName + ", skuRateTypeId="
				+ skuRateTypeId + ", skuRate=" + skuRate + ", skuStockQty=" + skuStockQty + ", saleUom=" + saleUom
				+ ", stockToSaleUom=" + stockToSaleUom + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2 + ", companyId="
				+ companyId + "]";
	}
	
	
}
