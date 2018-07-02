package com.dayLeasing.service.dto;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * The Class CartDTO.
 *
 * @author Balaram
 */
public class CartDTO {

	/** The total. */
	private String total;

	/** The bookings. */
	private List<PropertyReservationDTO> bookings;

	/** The cart id. */
	private String cartId;

	/** The payment token. */
	private Map paymentToken;

	/** The paymentmailid. */
	private String paymentmailid;

	/** The payment status. */
	private String paymentStatus;

	/** The coupon code. */
	private String couponCode;

	/** The user name. */
	private String userName;

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * Gets the bookings.
	 *
	 * @return the bookings
	 */
	public List<PropertyReservationDTO> getBookings() {
		return bookings;
	}

	/**
	 * Sets the bookings.
	 *
	 * @param bookings
	 *            the new bookings
	 */
	public void setBookings(List<PropertyReservationDTO> bookings) {
		this.bookings = bookings;
	}

	/**
	 * Gets the cart id.
	 *
	 * @return the cart id
	 */
	public String getCartId() {
		return cartId;
	}

	/**
	 * Sets the cart id.
	 *
	 * @param cartId
	 *            the new cart id
	 */
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	/**
	 * Gets the payment token.
	 *
	 * @return the payment token
	 */
	public Map getPaymentToken() {
		return paymentToken;
	}

	/**
	 * Sets the payment token.
	 *
	 * @param paymentToken
	 *            the new payment token
	 */
	public void setPaymentToken(Map paymentToken) {
		this.paymentToken = paymentToken;
	}

	/**
	 * Gets the paymentmailid.
	 *
	 * @return the paymentmailid
	 */
	public String getPaymentmailid() {
		return paymentmailid;
	}

	/**
	 * Sets the paymentmailid.
	 *
	 * @param paymentmailid
	 *            the new paymentmailid
	 */
	public void setPaymentmailid(String paymentmailid) {
		this.paymentmailid = paymentmailid;
	}

	/**
	 * Gets the payment status.
	 *
	 * @return the payment status
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * Sets the payment status.
	 *
	 * @param paymentStatus
	 *            the new payment status
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * Gets the coupon code.
	 *
	 * @return the coupon code
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * Sets the coupon code.
	 *
	 * @param couponCode
	 *            the new coupon code
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
}
