package com.dayLeasing.service;

import java.util.Date;
import java.util.List;

import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.service.dto.CartDTO;
import com.dayLeasing.service.dto.PropertyReservationDTO;

// TODO: Auto-generated Javadoc

/**
 * The Interface PropertyReservationService.
 *
 * @author Balaram
 */
public interface PropertyReservationService {

	/**
	 * Update reservation.
	 *
	 * @param propertyreservationDTO
	 *            the propertyreservation DTO
	 * @param areaID
	 *            the area ID
	 * @param regisId
	 *            the regis id
	 * @return true, if successful
	 */
	boolean updateReservation(PropertyReservationDTO propertyreservationDTO,
			String areaID, String regisId);

	/*
	 * post userid get userid,reservationid,prodid put reservationid delete
	 * reservation id
	 */

	/**
	 * Adds the reservation.
	 *
	 * @param propertyReservationDTO
	 *            the property reservation DTO
	 * @param userId
	 *            the user id
	 * @return the property reservation DTO
	 */
	PropertyReservationDTO addReservation(
			PropertyReservationDTO propertyReservationDTO, String userId);

	/**
	 * Gets the reservation by user id.
	 *
	 * @param userId
	 *            the user id
	 * @return the reservation by user id
	 */
	List<PropertyReservationDTO> getReservationByUserId(String userId);

	/**
	 * Gets the reservations byproperty id.
	 *
	 * @param prodID
	 *            the prod ID
	 * @return the reservations byproperty id
	 */
	List<PropertyReservationDTO> getReservationsBypropertyId(String prodID);

	/**
	 * Gets the reservations by registration id.
	 *
	 * @param regisId
	 *            the regis id
	 * @return the reservations by registration id
	 */
	List<PropertyReservationDTO> getReservationsByRegistrationId(String regisId);

	/**
	 * Gets the reservations by registration id.
	 *
	 * @param regisId
	 *            the regis id
	 * @param userId
	 *            the user id
	 * @return the reservations by registration id
	 */
	PropertyReservationDTO getReservationsByRegistrationId(String regisId,
			String userId);

	/**
	 * Gets the reservations of landowner.
	 *
	 * @param userUuid
	 *            the user uuid
	 * @return the reservations of landowner
	 */
	public List<PropertyReservationDTO> getReservationsOfLandowner(
			String userUuid);

	/**
	 * Delete reservation.
	 *
	 * @param regisId
	 *            the regis id
	 * @return true, if successful
	 */
	public boolean deleteReservation(String regisId);

	/**
	 * Cart booking.
	 *
	 * @param cart
	 *            the cart
	 * @param cartId
	 *            the cart id
	 * @param hunterId
	 *            the hunter id
	 * @return the cart DTO
	 */
	public CartDTO cartBooking(CartDTO cart, String cartId, String hunterId);

	/**
	 * Cancel the booking.
	 *
	 * @param resId
	 *            the res id
	 * @return true, if successful
	 */
	public boolean cancelTheBooking(String resId);

	/**
	 * Gets the reservations by date.
	 *
	 * @param propertyId
	 *            the property id
	 * @param date
	 *            the date
	 * @return the reservations by date
	 */
	List<PropertyReservationDTO> getReservationsByDate(String propertyId,
			String date);

	/**
	 * Gets the coupon.
	 *
	 * @param hunterId
	 *            the hunter id
	 * @param coupon
	 *            the coupon
	 * @return the coupon
	 */
	public DayleasingCoupons getCoupon(String hunterId, String coupon);
}
