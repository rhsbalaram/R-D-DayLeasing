package com.dayLeasing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayLeasing.dao.CouponCodeDao;
import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.service.dto.CouponCodeDTO;
import com.dayLeasing.service.vto.CouponCodeVTO;

@Service
public class CouponCodeServiceImpl implements CouponCodeService {

	@Autowired
	CouponCodeDao couponCodeDao;
	
	@Override
	public CouponCodeDTO addCoupon(CouponCodeDTO coupon) {
		// TODO Auto-generated method stub
		DayleasingCoupons convertTOCoponCode = CouponCodeVTO.convertTOCoponCode(coupon);
		DayleasingCoupons addCoupon = couponCodeDao.addCoupon(convertTOCoponCode);
		CouponCodeDTO convertToCouponCodeDTO = CouponCodeVTO.convertToCouponCodeDTO(addCoupon);
		return convertToCouponCodeDTO;
	}

	@Override
	public List<CouponCodeDTO> getCoupons() {
		// TODO Auto-generated method stub
		List<DayleasingCoupons> coupons = couponCodeDao.getCoupons();
		ArrayList<CouponCodeDTO> couponArrayList = new ArrayList<CouponCodeDTO>();
		for(DayleasingCoupons coupon : coupons ){
			CouponCodeDTO convertToCouponCodeDTO = CouponCodeVTO.convertToCouponCodeDTO(coupon);
			couponArrayList.add(convertToCouponCodeDTO);
		}
		return couponArrayList;
	}

	@Override
	public boolean updateSlot(CouponCodeDTO coupon) {
		// TODO Auto-generated method stub
		DayleasingCoupons convertTOCoponCode = CouponCodeVTO.convertTOCoponCode(coupon);
		boolean updateSlot = couponCodeDao.updateSlot(convertTOCoponCode);
		
		return updateSlot;
	}

	@Override
	public boolean deleteCoupon(int sno) {
		// TODO Auto-generated method stub
		boolean deleteCoupon = couponCodeDao.deleteCoupon(sno);
		return deleteCoupon;
	}

}
