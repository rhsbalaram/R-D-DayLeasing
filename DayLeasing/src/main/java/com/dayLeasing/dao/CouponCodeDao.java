package com.dayLeasing.dao;

import java.util.List;

import com.dayLeasing.dao.model.DayleasingCoupons;

public interface CouponCodeDao {
	public DayleasingCoupons addCoupon(DayleasingCoupons coupon);
	 public List<DayleasingCoupons> getCoupons();
	 public boolean updateSlot(DayleasingCoupons coupon);
	 public boolean deleteCoupon( int sno);

}
