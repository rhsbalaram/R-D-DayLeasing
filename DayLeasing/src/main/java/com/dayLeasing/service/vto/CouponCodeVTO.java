package com.dayLeasing.service.vto;

import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.service.dto.CouponCodeDTO;
import com.dayLeasing.service.util.DateUtils;

public class CouponCodeVTO {
	
	public static DayleasingCoupons convertTOCoponCode(CouponCodeDTO coupon){
		DayleasingCoupons dayleasingCoupons = new DayleasingCoupons();
		dayleasingCoupons.setAmountDiscount(coupon.getAmountDiscount());
		dayleasingCoupons.setAmountLimit(coupon.getAmountLimit());
		dayleasingCoupons.setCouponCode(coupon.getCouponCode());
		dayleasingCoupons.setFromDate(DateUtils.generateDateFromString(coupon.getFromDate()));
		dayleasingCoupons.setMinimumAmountCriteria(coupon.getMinimumAmountCriteria());
		dayleasingCoupons.setPercentageDiscount(coupon.getPercentageDiscount());
		dayleasingCoupons.setSno(coupon.getSno());
		dayleasingCoupons.setToDate(DateUtils.generateDateFromString(coupon.getToDate()));
		dayleasingCoupons.setUsageCount(coupon.getUsageCount());
		return dayleasingCoupons;
	}
	
	public static CouponCodeDTO convertToCouponCodeDTO(DayleasingCoupons coupon)
	{
		CouponCodeDTO dayleasingCoupons = new CouponCodeDTO();
		dayleasingCoupons.setAmountDiscount(coupon.getAmountDiscount());
		dayleasingCoupons.setAmountLimit(coupon.getAmountLimit());
		dayleasingCoupons.setCouponCode(coupon.getCouponCode());
		dayleasingCoupons.setFromDate(DateUtils.generateStringFromDate(coupon.getFromDate()));
		dayleasingCoupons.setMinimumAmountCriteria(coupon.getMinimumAmountCriteria());
		dayleasingCoupons.setPercentageDiscount(coupon.getPercentageDiscount());
		dayleasingCoupons.setSno(coupon.getSno());
		dayleasingCoupons.setToDate(DateUtils.generateStringFromDate(coupon.getToDate()));
		dayleasingCoupons.setUsageCount(coupon.getUsageCount());
		return dayleasingCoupons;
	}

}
