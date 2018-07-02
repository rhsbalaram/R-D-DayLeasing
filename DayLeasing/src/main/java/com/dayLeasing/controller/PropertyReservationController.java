package com.dayLeasing.controller;

import com.dayLeasing.configuration.InValidRequestException;
import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.service.dto.CartDTO;
import com.dayLeasing.service.dto.PropertyReservationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.dayLeasing.service.PropertyReservationService;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyReservationController.
 *
 * @author Balaram
 */
@RestController
public class PropertyReservationController {

	/** The property reservation service. */
	@Autowired
	public PropertyReservationService propertyReservationService;

	/**
	 * Adds the reservation.
	 *
	 * @param propertyReservationDTO
	 *            the property reservation DTO
	 * @param binding
	 *            the binding
	 * @param map
	 *            the map
	 * @return the response entity
	 */
	@RequestMapping(value = "hunter/reservation", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, PropertyReservationDTO>> addReservation(
			@Valid @RequestBody PropertyReservationDTO propertyReservationDTO,
			BindingResult binding, ModelMap map) {
		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}
		PropertyReservationDTO reservationDTO = propertyReservationService
				.addReservation(propertyReservationDTO, null);
		HashMap<String, PropertyReservationDTO> reservationDTOHashMap = new HashMap<>();
		reservationDTOHashMap.put("reservation", reservationDTO);
		return new ResponseEntity<HashMap<String, PropertyReservationDTO>>(
				reservationDTOHashMap, HttpStatus.OK);
	}

	/**
	 * Confirm reservation.
	 *
	 * @param cartDTO
	 *            the cart DTO
	 * @param cartId
	 *            the cart id
	 * @param hunterId
	 *            the hunter id
	 * @param binding
	 *            the binding
	 * @param map
	 *            the map
	 * @return the response entity
	 */
	@RequestMapping(value = "hunter/{hunterId}/reservation/cart/{cartId}", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, CartDTO>> confirmReservation(
			@Valid @RequestBody CartDTO cartDTO, @PathVariable String cartId,
			@PathVariable String hunterId, BindingResult binding, ModelMap map) {
		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}
		CartDTO cartBooking = propertyReservationService.cartBooking(cartDTO,
				cartId, hunterId);
		HashMap<String, CartDTO> reservationDTOHashMap = new HashMap<>();
		reservationDTOHashMap.put("cart", cartBooking);
		return new ResponseEntity<HashMap<String, CartDTO>>(
				reservationDTOHashMap, HttpStatus.OK);
	}

	/**
	 * Gets the reservation by user id.
	 *
	 * @param hunterid
	 *            the hunterid
	 * @return the reservation by user id
	 */
	@RequestMapping(value = "hunter/{hunterid}/reservation", method = RequestMethod.POST)
	public ResponseEntity<List<PropertyReservationDTO>> getReservationByUserId(
			@PathVariable String hunterid) {
		List<PropertyReservationDTO> propertyReservationDTOS = propertyReservationService
				.getReservationByUserId(hunterid);
		return new ResponseEntity<List<PropertyReservationDTO>>(
				propertyReservationDTOS, HttpStatus.OK);
	}

	/**
	 * Gets the reservations by property id.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the reservations by property id
	 */
	@RequestMapping(value = "hunter/reservation/property/{propertyId}", method = RequestMethod.GET)
	public ResponseEntity<List<PropertyReservationDTO>> getReservationsByPropertyId(
			@PathVariable String propertyId) {
		List<PropertyReservationDTO> propertyReservationDTOS = propertyReservationService
				.getReservationsBypropertyId(propertyId);
		return new ResponseEntity<List<PropertyReservationDTO>>(
				propertyReservationDTOS, HttpStatus.OK);
	}

	/**
	 * Gets the reservations for landowner.
	 *
	 * @param userId
	 *            the user id
	 * @return the reservations for landowner
	 */
	// landowner
	@RequestMapping(value = "user/{userId}/reservation", method = RequestMethod.GET)
	public ResponseEntity<List<PropertyReservationDTO>> getReservationsForLandowner(
			@PathVariable String userId) {
		List<PropertyReservationDTO> propertyReservationDTOS = propertyReservationService
				.getReservationsOfLandowner(userId);
		return new ResponseEntity<List<PropertyReservationDTO>>(
				propertyReservationDTOS, HttpStatus.OK);
	}

	/**
	 * Gets the reservations by registration id.
	 *
	 * @param reservationId
	 *            the reservation id
	 * @return the reservations by registration id
	 */
	@RequestMapping(value = "hunter/reservation/{reservationId}", method = RequestMethod.GET)
	public ResponseEntity<List<PropertyReservationDTO>> getReservationsByRegistrationId(
			@PathVariable String reservationId) {
		List<PropertyReservationDTO> propertyReservationDTOS = propertyReservationService
				.getReservationsByRegistrationId(reservationId);
		return new ResponseEntity<List<PropertyReservationDTO>>(
				propertyReservationDTOS, HttpStatus.OK);
	}

	/**
	 * Gets the reservations by registration id.
	 *
	 * @param reservationId
	 *            the reservation id
	 * @param hunterid
	 *            the hunterid
	 * @return the reservations by registration id
	 */
	@RequestMapping(value = "hunter/{hunterid}/reservation/{reservationId}", method = RequestMethod.GET)
	public ResponseEntity<PropertyReservationDTO> getReservationsByRegistrationId(
			@PathVariable String reservationId, @PathVariable String hunterid) {
		PropertyReservationDTO reservationDTO = propertyReservationService
				.getReservationsByRegistrationId(reservationId, hunterid);
		return new ResponseEntity<PropertyReservationDTO>(reservationDTO,
				HttpStatus.OK);

	}

	/**
	 * Delete reservation.
	 *
	 * @param reservationId
	 *            the reservation id
	 * @return the response entity
	 */
	@RequestMapping(value = "hunter/reservation/delete/{reservationId}", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Boolean>> deleteReservation(
			@PathVariable String reservationId) {
		boolean success = propertyReservationService
				.deleteReservation(reservationId);
		HashMap<String, Boolean> map = new HashMap<>();
		map.put("update", success);

		return new ResponseEntity<HashMap<String, Boolean>>(map, HttpStatus.OK);
	}

	/**
	 * Delete landowner reservation.
	 *
	 * @param reservationId
	 *            the reservation id
	 * @return the response entity
	 */
	@RequestMapping(value = "user/reservation/delete/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Boolean>> deleteLandownerReservation(
			@PathVariable String reservationId) {
		boolean success = propertyReservationService
				.cancelTheBooking(reservationId);
		HashMap<String, Boolean> map = new HashMap<>();
		map.put("status", success);

		return new ResponseEntity<HashMap<String, Boolean>>(map, HttpStatus.OK);
	}

	/**
	 * Update reservation.
	 *
	 * @param propertyReservationDTO
	 *            the property reservation DTO
	 * @param hunterid
	 *            the hunterid
	 * @param reservationId
	 *            the reservation id
	 * @return the response entity
	 */
	@RequestMapping(value = "hunter/{hunterid}/reservation/{reservationId}", method = RequestMethod.PUT)
	public ResponseEntity<HashMap<String, PropertyReservationDTO>> updateReservation(
			PropertyReservationDTO propertyReservationDTO,
			@PathVariable String hunterid, @PathVariable String reservationId) {
		boolean success = propertyReservationService.updateReservation(
				propertyReservationDTO, hunterid, reservationId);
		HashMap<String, PropertyReservationDTO> map = new HashMap<>();
		map.put("reservation update", propertyReservationDTO);
		return new ResponseEntity<HashMap<String, PropertyReservationDTO>>(map,
				HttpStatus.OK);
	}

	/**
	 * Gets the reservations by date.
	 *
	 * @param propertyId
	 *            the property id
	 * @param date
	 *            the date
	 * @return the reservations by date
	 */
	@RequestMapping(value = "hunter/reservation/date/{propertyId}", method = RequestMethod.GET)
	public ResponseEntity<List<PropertyReservationDTO>> getReservationsByDate(
			@PathVariable String propertyId, @RequestParam String date) {
		//
		List<PropertyReservationDTO> propertyReservationDTOS = propertyReservationService
				.getReservationsByDate(propertyId, date);
		return new ResponseEntity<List<PropertyReservationDTO>>(
				propertyReservationDTOS, HttpStatus.OK);
	}

	/**
	 * Gets the coupons by date.
	 *
	 * @param hunterId
	 *            the hunter id
	 * @param couponId
	 *            the coupon id
	 * @return the coupons by date
	 */
	@RequestMapping(value = "hunter/{hunterId}/coupon/{couponId}", method = RequestMethod.GET)
	public ResponseEntity<DayleasingCoupons> getCouponsByDate(
			@PathVariable String hunterId, @PathVariable String couponId) {
		//
		DayleasingCoupons coupon = propertyReservationService.getCoupon(
				hunterId, couponId);
		return new ResponseEntity<DayleasingCoupons>(coupon, HttpStatus.OK);
	}

}
