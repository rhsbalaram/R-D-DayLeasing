package com.dayLeasing.dao;

import java.util.Date;
import java.util.List;

import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.dao.model.Propertyreservation;
//import com.dayLeasing.service.PropertyReservation;
import com.dayLeasing.service.dto.PropertyReservationDTO;

// TODO: Auto-generated Javadoc

/**
 * The Interface PropertyReservationDao.
 *
 * @author Balaram
 */
public interface PropertyReservationDao {

	/**
	 * Adds the reservation.
	 *
	 * @param propertyReservation
	 *            the property reservation
	 * @return the propertyreservation
	 */
	public Propertyreservation addReservation(
			Propertyreservation propertyReservation);

	/**
	 * Gets the reservations.
	 *
	 * @param userId
	 *            the user id
	 * @return the reservations
	 */
	public List<Propertyreservation> getReservations(String userId);

	/**
	 * Gets the reservations by property id.
	 *
	 * @param prodID
	 *            the prod ID
	 * @return the reservations by property id
	 */
	public List<Propertyreservation> getReservationsByPropertyId(String prodID);

	/**
	 * Gets the reservations by registration id.
	 *
	 * @param regisId
	 *            the regis id
	 * @return the reservations by registration id
	 */
	public List<Propertyreservation> getReservationsByRegistrationId(
			String regisId);

	/**
	 * Gets the reservations by registration id user id.
	 *
	 * @param regisId
	 *            the regis id
	 * @param userId
	 *            the user id
	 * @return the reservations by registration id user id
	 */
	public Propertyreservation getReservationsByRegistrationId_UserId(
			String regisId, String userId);

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
	public boolean updateReservation(
			PropertyReservationDTO propertyreservationDTO, String areaID,
			String regisId);

	/**
	 * Cancel the booking.
	 *
	 * @param resId
	 *            the res id
	 * @return true, if successful
	 */
	public boolean cancelTheBooking(String resId);

	/**
	 * Delete reservation.
	 *
	 * @param regisId
	 *            the regis id
	 * @param userId
	 *            the user id
	 * @return true, if successful
	 */
	boolean deleteReservation(String regisId, String userId);

	/**
	 * Delete reservation to unblock.
	 *
	 * @param reservationId
	 *            the reservation id
	 * @param cartId
	 *            the cart id
	 * @param paymentStatus
	 *            the payment status
	 * @return true, if successful
	 */
	public boolean deleteReservationToUnblock(String reservationId,
			String cartId, String paymentStatus);

	/**
	 * Gets the blocked reservation.
	 *
	 * @param cartId
	 *            the cart id
	 * @param hunterId
	 *            the hunter id
	 * @return the blocked reservation
	 */
	public List<Propertyreservation> getBlockedReservation(String cartId,
			String hunterId);
	
	public void updateSlotsPriceWithDiscount(List<Propertyreservation> reservationList);

	/**
	 * Confirm the payment.
	 *
	 * @param cartId
	 *            the cart id
	 * @param refundId
	 *            the refund id
	 * @param mailId
	 *            the mail id
	 * @param couponCode
	 *            the coupon code
	 * @param userNames
	 *            the user names
	 * @return true, if successful
	 */
	public boolean ConfirmThePayment(String cartId, String refundId,
			String mailId, String couponCode, String userNames,String cartAmount);

	/**
	 * Gets the reservations of landowner.
	 *
	 * @param userUuid
	 *            the user uuid
	 * @return the reservations of landowner
	 */
	public List<Propertyreservation> getReservationsOfLandowner(String userUuid);

	/**
	 * Gets the reservations by date.
	 *
	 * @param propertyId
	 *            the property id
	 * @param date
	 *            the date
	 * @return the reservations by date
	 */
	List<Propertyreservation> getReservationsByDate(String propertyId,
			String date);

	/**
	 * Gets the reservations by datefor slot.
	 *
	 * @param slotId
	 *            the slot id
	 * @param date
	 *            the date
	 * @return the reservations by datefor slot
	 */
	public List<Propertyreservation> getReservationsByDateforSlot(
			String slotId, Date date);

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
