package com.ats.manoharweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_flavour")
public class Flavour {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "flavour_id")
	private int flavourId;
	
	@Column(name = "flavour_code")
	private String flavourCode;
	
	@Column(name = "flavour_name")
	private String flavourName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "rate")
	private float rate;
	
	@Column(name = "seq_no")
	private int seqNo;
	
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
	
	@Column(name = "company_id")
	private int companyId;

	public int getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(int flavourId) {
		this.flavourId = flavourId;
	}

	public String getFlavourCode() {
		return flavourCode;
	}

	public void setFlavourCode(String flavourCode) {
		this.flavourCode = flavourCode;
	}

	public String getFlavourName() {
		return flavourName;
	}

	public void setFlavourName(String flavourName) {
		this.flavourName = flavourName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "Flavour [flavourId=" + flavourId + ", flavourCode=" + flavourCode + ", flavourName=" + flavourName
				+ ", description=" + description + ", rate=" + rate + ", seqNo=" + seqNo + ", delStatus=" + delStatus
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", companyId=" + companyId + "]";
	}
	
	
}
