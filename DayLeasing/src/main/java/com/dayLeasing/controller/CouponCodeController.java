package com.dayLeasing.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayLeasing.configuration.InValidRequestException;

import com.dayLeasing.service.CouponCodeService;
import com.dayLeasing.service.dto.CouponCodeDTO;


@RestController
public class CouponCodeController {
	
	@Autowired
	CouponCodeService couponCodeService;
	
	@RequestMapping(value = "user/coupon", method = RequestMethod.POST)
	public ResponseEntity<CouponCodeDTO> addCoupon(
			@Valid @RequestBody CouponCodeDTO couponCodeDTO,
			 BindingResult binding, ModelMap map) {
		
		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}
		
		 CouponCodeDTO addCoupon = couponCodeService.addCoupon(couponCodeDTO);
		
		return new ResponseEntity<CouponCodeDTO>(addCoupon,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "user/coupon", method = RequestMethod.GET)
	public ResponseEntity<List<CouponCodeDTO>> getCoupons() {
		 List<CouponCodeDTO> coupons = couponCodeService.getCoupons();
		return new ResponseEntity<List<CouponCodeDTO>>(coupons, HttpStatus.OK);
	}
	
	@RequestMapping(value = "user/coupon", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> editCoupon(
			@Valid @RequestBody CouponCodeDTO couponCodeDTO,
			 BindingResult binding, ModelMap map) {
		
		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}
		
		  boolean updateCoupon = couponCodeService.updateSlot(couponCodeDTO);
		
		return new ResponseEntity<Boolean>(updateCoupon,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "user/coupon/{couponId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteCoupon(
			@PathVariable int couponId){
		
		boolean deleteCoupon = couponCodeService.deleteCoupon(couponId);
		return new ResponseEntity<Boolean>(deleteCoupon,
				HttpStatus.OK);
	}
	
}
