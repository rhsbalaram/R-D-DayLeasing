package com.dayLeasing.service.vto;

import com.dayLeasing.dao.model.Propertyreservation;
import com.dayLeasing.service.dto.PropertyReservationDTO;
import com.dayLeasing.service.util.DateUtils;
import com.dayLeasing.service.util.GenerateUUID;

public class PropertyReservationVTO {
	//convert to property reservation
	
	public static Propertyreservation convertToPropertyReservation(PropertyReservationDTO propertyReservationDTO,String userid){
		Propertyreservation propertyreservation=new Propertyreservation();
		propertyreservation.setPropertyReservationUuid(GenerateUUID.generateUUID());
		propertyreservation.setUserGuid(userid);
		propertyreservation.setPropertyAreaUuid(propertyReservationDTO.getPropertyAreaUuid());
		propertyreservation.setPropertySlotsUuid(propertyReservationDTO.getPropertySlotsUuid());
		propertyreservation.setPropertyUuid(propertyReservationDTO.getPropertyUuid());
		//propertyreservation.setPropertyReservationUuid(propertyReservationDTO.getPropertyReservationUuid());
		propertyreservation.setPropertyReservationId(propertyReservationDTO.getPropertyReservationId());
		propertyreservation.setPaymentSuccessId(propertyReservationDTO.getPaymentSuccessId());
		propertyreservation.setPaymentFailureId(propertyReservationDTO.getPaymentFailureId());
		propertyreservation.setPaymentStatus("Blocked");
		propertyreservation.setPaymentCancelId(propertyReservationDTO.getPaymentCancelId());
		propertyreservation.setPrice(propertyReservationDTO.getPrice());
		propertyreservation.setRefundId(propertyReservationDTO.getRefundId());
		propertyreservation.setReservationFrom(DateUtils.generateDateFromString(propertyReservationDTO.getReservationFrom()));
		propertyreservation.setReservationTo(DateUtils.generateDateFromString(propertyReservationDTO.getReservationTo()));
		String generateUUID = GenerateUUID.generateUUID();
		String cartId = propertyReservationDTO.getCartId();
		if(cartId==null){
			cartId=generateUUID;
		}
		propertyreservation.setCartId(cartId);
		propertyreservation.setPropertyName(propertyReservationDTO.getPropertyName());
		propertyreservation.setAreaName(propertyReservationDTO.getAreaName());
		propertyreservation.setSlotName(propertyReservationDTO.getSlotName());
		propertyreservation.setCouponCode(propertyReservationDTO.getCouponCode());
		propertyreservation.setCartPrice(propertyReservationDTO.getCartPrice());
		propertyreservation.setUserName(propertyreservation.getUserName());
		propertyreservation.setPaymentMailId(propertyReservationDTO.getPaymentMailId());

//		propertyreservation.set

		return propertyreservation;
	}
	
	public static PropertyReservationDTO convertToPropertyReservationDTO(Propertyreservation propertyreservation){
		PropertyReservationDTO propertyReservationDTO=new PropertyReservationDTO();
		
		propertyReservationDTO.setPaymentCancelId(propertyreservation.getPaymentCancelId());
		propertyReservationDTO.setPaymentFailureId(propertyreservation.getPaymentFailureId());
		propertyReservationDTO.setPaymentStatus(propertyreservation.getPaymentStatus());
		propertyReservationDTO.setPaymentSuccessId(propertyreservation.getPaymentSuccessId());
		propertyReservationDTO.setPrice(propertyreservation.getPrice());
		propertyReservationDTO.setPropertyAreaUuid(propertyreservation.getPropertyAreaUuid());
		propertyReservationDTO.setPropertyReservationId(propertyreservation.getPropertyReservationId());
		propertyReservationDTO.setPropertyReservationUuid(propertyreservation.getPropertyReservationUuid());
		propertyReservationDTO.setPropertyUuid(propertyreservation.getPropertyUuid());
		propertyReservationDTO.setPropertySlotsUuid(propertyreservation.getPropertySlotsUuid());
		propertyReservationDTO.setPropertyUuid(propertyreservation.getPropertyUuid());
		propertyReservationDTO.setRefundId(propertyreservation.getRefundId());
		propertyReservationDTO.setReservationFrom(DateUtils.generateStringFromDate(propertyreservation.getReservationFrom()));
		propertyReservationDTO.setReservationTo(DateUtils.generateStringFromDate(propertyreservation.getReservationTo()));
		propertyReservationDTO.setUserGuid(propertyreservation.getUserGuid());
		propertyReservationDTO.setCartId(propertyreservation.getCartId());
		propertyReservationDTO.setPropertyName(propertyreservation.getPropertyName());
		propertyReservationDTO.setAreaName(propertyreservation.getAreaName());
		propertyReservationDTO.setSlotName(propertyreservation.getSlotName());
		propertyReservationDTO.setCouponCode(propertyreservation.getCouponCode());
		propertyReservationDTO.setCartPrice(propertyreservation.getCartPrice());
		propertyReservationDTO.setUserName(propertyreservation.getUserName());
		propertyReservationDTO.setPaymentMailId(propertyreservation.getPaymentMailId());
		return propertyReservationDTO;
		
		
		
		
	}

	public static Propertyreservation convertToPropertyReservationToUpdate(
			PropertyReservationDTO propertyReservationDTO, Propertyreservation propertyreservation) {
		propertyreservation.setPropertyReservationUuid(GenerateUUID.generateUUID());
		propertyreservation.setUserGuid(propertyReservationDTO.getUserGuid());
		propertyreservation.setPropertyAreaUuid(propertyReservationDTO.getPropertyAreaUuid());
		propertyreservation.setPropertySlotsUuid(propertyReservationDTO.getPropertySlotsUuid());
		propertyreservation.setPropertyUuid(propertyReservationDTO.getPropertyUuid());
		propertyreservation.setPropertyReservationUuid(propertyReservationDTO.getPropertyReservationUuid());
		propertyreservation.setPropertyReservationId(propertyReservationDTO.getPropertyReservationId());
		propertyreservation.setPaymentSuccessId(propertyReservationDTO.getPaymentSuccessId());
		propertyreservation.setPaymentFailureId(propertyReservationDTO.getPaymentFailureId());
		propertyreservation.setPaymentStatus(propertyReservationDTO.getPaymentStatus());
		propertyreservation.setPaymentCancelId(propertyReservationDTO.getPaymentCancelId());
		propertyreservation.setPrice(propertyReservationDTO.getPrice());
		propertyreservation.setRefundId(propertyReservationDTO.getRefundId());
		propertyreservation.setReservationFrom(DateUtils.generateDateFromString(propertyReservationDTO.getReservationFrom()));
		propertyreservation.setReservationTo(DateUtils.generateDateFromString(propertyReservationDTO.getReservationTo()));
		propertyreservation.setCartId(propertyReservationDTO.getCartId());
		propertyreservation.setPropertyName(propertyReservationDTO.getPropertyName());
		propertyreservation.setAreaName(propertyReservationDTO.getAreaName());
		propertyreservation.setSlotName(propertyReservationDTO.getSlotName());
		propertyreservation.setCouponCode(propertyReservationDTO.getCouponCode());
		propertyreservation.setCartPrice(propertyReservationDTO.getCartPrice());
		propertyreservation.setUserName(propertyreservation.getUserName());
		propertyreservation.setPaymentMailId(propertyReservationDTO.getPaymentMailId());
		// TODO Auto-generated method stub
		return propertyreservation;
		
	}

}
