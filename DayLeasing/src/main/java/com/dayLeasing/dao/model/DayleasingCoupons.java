package com.dayLeasing.dao.model;

// default package
// Generated Jun 8, 2017 2:53:48 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DayleasingCoupons generated by hbm2java
 */
@Entity
@Table(name = "dayleasing_coupons", catalog = "dayleasing")
public class DayleasingCoupons implements java.io.Serializable {

	private Integer sno;
	private String couponCode;
	private Date fromDate;
	private Date toDate;
	private String percentageDiscount;
	private String amountDiscount;
	private String amountLimit;
	private String minimumAmountCriteria;
	private Integer usageCount;
	private String memberType;
	private Date created;
	private Date lastModified;

	public DayleasingCoupons() {
	}

	public DayleasingCoupons(String couponCode, Date fromDate, Date toDate,
			Date created, Date lastModified) {
		this.couponCode = couponCode;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.created = created;
		this.lastModified = lastModified;
	}

	public DayleasingCoupons(String couponCode, Date fromDate, Date toDate,
			String percentageDiscount, String amountDiscount,
			String amountLimit, Integer usageCount, String memberType,
			Date created, Date lastModified) {
		this.couponCode = couponCode;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.percentageDiscount = percentageDiscount;
		this.amountDiscount = amountDiscount;
		this.amountLimit = amountLimit;
		this.usageCount = usageCount;
		this.memberType = memberType;
		this.created = created;
		this.lastModified = lastModified;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sno", unique = true, nullable = false)
	public Integer getSno() {
		return this.sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	@Column(name = "coupon_code", nullable = false, length = 20)
	public String getCouponCode() {
		return this.couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "from_date", nullable = false, length = 10)
	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "to_date", nullable = false, length = 10)
	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name = "percentage_discount", length = 20)
	public String getPercentageDiscount() {
		return this.percentageDiscount;
	}

	public void setPercentageDiscount(String percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}

	@Column(name = "amount_discount", length = 20)
	public String getAmountDiscount() {
		return this.amountDiscount;
	}

	public void setAmountDiscount(String amountDiscount) {
		this.amountDiscount = amountDiscount;
	}

	@Column(name = "minimum_amount_criteria", length = 20)
	public String getMinimumAmountCriteria() {
		return minimumAmountCriteria;
	}

	public void setMinimumAmountCriteria(String minimumAmountCriteria) {
		this.minimumAmountCriteria = minimumAmountCriteria;
	}

	@Column(name = "discount_amount_limit", length = 20)
	public String getAmountLimit() {
		return this.amountLimit;
	}

	public void setAmountLimit(String amountLimit) {
		this.amountLimit = amountLimit;
	}

	@Column(name = "usage_count")
	public Integer getUsageCount() {
		return this.usageCount;
	}

	public void setUsageCount(Integer usageCount) {
		this.usageCount = usageCount;
	}

	@Column(name = "member_type", length = 20)
	public String getMemberType() {
		return this.memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", nullable = false, length = 19)
	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}