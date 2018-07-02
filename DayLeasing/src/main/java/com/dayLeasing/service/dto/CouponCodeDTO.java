package com.dayLeasing.service.dto;

import java.util.Date;

public class CouponCodeDTO {
	
	
	private Integer sno;
	private String couponCode;
	private String fromDate;
	private String toDate;
	private String percentageDiscount;
	private String amountDiscount;
	private String amountLimit;
	private String minimumAmountCriteria;
	private Integer usageCount;
	private String memberType;
	private Date created;
	private Date lastModified;

	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPercentageDiscount() {
		return percentageDiscount;
	}
	public void setPercentageDiscount(String percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}
	public String getAmountDiscount() {
		return amountDiscount;
	}
	public void setAmountDiscount(String amountDiscount) {
		this.amountDiscount = amountDiscount;
	}
	public String getAmountLimit() {
		return amountLimit;
	}
	public void setAmountLimit(String amountLimit) {
		this.amountLimit = amountLimit;
	}
	public String getMinimumAmountCriteria() {
		return minimumAmountCriteria;
	}
	public void setMinimumAmountCriteria(String minimumAmountCriteria) {
		this.minimumAmountCriteria = minimumAmountCriteria;
	}
	public Integer getUsageCount() {
		return usageCount;
	}
	public void setUsageCount(Integer usageCount) {
		this.usageCount = usageCount;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
}
