package com.ats.manoharweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "cat_id")
	private int catId;	
	
	@Column(name = "category_code")
	private String categoryCode;
	
	@Column(name = "cat_name")
	private String catName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "item_image")
	private String itemImage;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "seq_no")
	private int seqNo;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}


	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", categoryCode=" + categoryCode + ", catName=" + catName + ", description="
				+ description + ", itemImage=" + itemImage + ", delStatus=" + delStatus + ", seqNo=" + seqNo
				+ ", companyId=" + companyId + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + "]";
	}

	
}
