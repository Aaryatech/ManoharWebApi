package com.ats.manoharweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "item_id")
	private int itemId;
	
	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "short_name")
	private String shortName;
	
	@Column(name = "cat_id")
	private int catId;
	
	@Column(name = "sub_cat_id")
	private int subCatId;
	
	@Column(name = "tag_ids")
	private String tagIds;
	
	@Column(name = "flavour_id")
	private int flavourId;
	
	@Column(name = "brand_id")
	private int brandId;
	
	@Column(name = "tax_id")
	private int taxId;
	/****************************/
	@Column(name = "purchase_uom")
	private int purchaseUom;
	
	@Column(name = "stock_uom")
	private int stockUom;
	
	@Column(name = "stock_to_purchase_uom")
	private String stockToPurchaseUom;
	
	@Column(name = "min_qty")
	private int minQty;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "test_type_ids")
	private int testTypeIds;
	
	@Column(name = "is_billable")
	private int isBillable;
	
	@Column(name = "is_salebale")
	private int isSalebale;
	
	@Column(name = "is_decimal")
	private int isDecimal;
	
	@Column(name = "product_show_in")
	private int productShowIn;
	
	/**********************************/
	
	@Column(name = "product_status_id")
	private int productStatusId;
	
	@Column(name = "is_tag_applicable")
	private int isTagApplicable;
	
	@Column(name = "show_in_order_frquntly")
	private int showInOrderFrquntly;
	
	@Column(name = "item_is_used")
	private int itemIsUsed;	
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "sort_no")
	private int sortNo;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;	
	
	@Column(name = "ex_int3")
	private int exInt3;
	
	@Column(name = "ex_int4")
	private int exInt4;
	
	@Column(name = "ex_float1")
	private float exFloat1;
	
	@Column(name = "ex_float2")
	private float exFloat2;
	
	@Column(name = "ex_float3")
	private float exFloat3;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;
	
	@Column(name = "ex_var3")
	private String exVar3;
	
	@Column(name = "ex_var4")
	private String exVar4;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public int getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(int flavourId) {
		this.flavourId = flavourId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public int getStockUom() {
		return stockUom;
	}

	public void setStockUom(int stockUom) {
		this.stockUom = stockUom;
	}

	public String getStockToPurchaseUom() {
		return stockToPurchaseUom;
	}

	public void setStockToPurchaseUom(String stockToPurchaseUom) {
		this.stockToPurchaseUom = stockToPurchaseUom;
	}

	public int getMinQty() {
		return minQty;
	}

	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getTestTypeIds() {
		return testTypeIds;
	}

	public void setTestTypeIds(int testTypeIds) {
		this.testTypeIds = testTypeIds;
	}

	public int getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(int isBillable) {
		this.isBillable = isBillable;
	}

	public int getIsSalebale() {
		return isSalebale;
	}

	public void setIsSalebale(int isSalebale) {
		this.isSalebale = isSalebale;
	}

	public int getIsDecimal() {
		return isDecimal;
	}

	public void setIsDecimal(int isDecimal) {
		this.isDecimal = isDecimal;
	}

	public int getProductShowIn() {
		return productShowIn;
	}

	public void setProductShowIn(int productShowIn) {
		this.productShowIn = productShowIn;
	}

	public int getProductStatusId() {
		return productStatusId;
	}

	public void setProductStatusId(int productStatusId) {
		this.productStatusId = productStatusId;
	}

	public int getIsTagApplicable() {
		return isTagApplicable;
	}

	public void setIsTagApplicable(int isTagApplicable) {
		this.isTagApplicable = isTagApplicable;
	}

	public int getShowInOrderFrquntly() {
		return showInOrderFrquntly;
	}

	public void setShowInOrderFrquntly(int showInOrderFrquntly) {
		this.showInOrderFrquntly = showInOrderFrquntly;
	}

	public int getItemIsUsed() {
		return itemIsUsed;
	}

	public void setItemIsUsed(int itemIsUsed) {
		this.itemIsUsed = itemIsUsed;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
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

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}

	public int getExInt4() {
		return exInt4;
	}

	public void setExInt4(int exInt4) {
		this.exInt4 = exInt4;
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

	public float getExFloat3() {
		return exFloat3;
	}

	public void setExFloat3(float exFloat3) {
		this.exFloat3 = exFloat3;
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

	public String getExVar4() {
		return exVar4;
	}

	public void setExVar4(String exVar4) {
		this.exVar4 = exVar4;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public int getPurchaseUom() {
		return purchaseUom;
	}

	public void setPurchaseUom(int purchaseUom) {
		this.purchaseUom = purchaseUom;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemCode=" + itemCode + ", itemName=" + itemName + ", shortName="
				+ shortName + ", catId=" + catId + ", subCatId=" + subCatId + ", tagIds=" + tagIds + ", flavourId="
				+ flavourId + ", brandId=" + brandId + ", taxId=" + taxId + ", purchaseUom=" + purchaseUom
				+ ", stockUom=" + stockUom + ", stockToPurchaseUom=" + stockToPurchaseUom + ", minQty=" + minQty
				+ ", companyId=" + companyId + ", testTypeIds=" + testTypeIds + ", isBillable=" + isBillable
				+ ", isSalebale=" + isSalebale + ", isDecimal=" + isDecimal + ", productShowIn=" + productShowIn
				+ ", productStatusId=" + productStatusId + ", isTagApplicable=" + isTagApplicable
				+ ", showInOrderFrquntly=" + showInOrderFrquntly + ", itemIsUsed=" + itemIsUsed + ", delStatus="
				+ delStatus + ", sortNo=" + sortNo + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3
				+ ", exInt4=" + exInt4 + ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2 + ", exFloat3=" + exFloat3
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", exVar4=" + exVar4 + "]";
	}

	
}
