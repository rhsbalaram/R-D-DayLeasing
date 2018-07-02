package com.dayLeasing.service;

import java.util.List;

import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.service.dto.CouponCodeDTO;

public interface CouponCodeService {
	public CouponCodeDTO addCoupon(CouponCodeDTO coupon);
	 public List<CouponCodeDTO> getCoupons();
	 public boolean updateSlot(CouponCodeDTO coupon);
	 public boolean deleteCoupon( int sno);

}
