package com.dayLeasing.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import scala.reflect.internal.annotations.compileTimeOnly;

import com.dayLeasing.dao.AreaDatesDao;
import com.dayLeasing.dao.PropertyReservationDao;
import com.dayLeasing.dao.model.AreaDates;
import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.dao.model.Propertyreservation;
import com.dayLeasing.service.dto.CartDTO;
import com.dayLeasing.service.dto.PropertyReservationDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;
import com.dayLeasing.service.util.DateUtils;
import com.dayLeasing.service.util.MailUtil;
import com.dayLeasing.service.util.SlotUnblocker;
import com.dayLeasing.service.vto.PropertyReservationVTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import com.stripe.net.RequestOptions;
import com.stripe.net.RequestOptions.RequestOptionsBuilder;

import freemarker.template.SimpleDate;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertyReservationServiceImpl.
 *
 * @author Balaram
 */
@Service
public class PropertyReservationServiceImpl implements
		PropertyReservationService {

	/** The property reservation dao. */
	@Autowired
	PropertyReservationDao propertyReservationDao;

	/** The slot unblocker. */
	@Autowired
	SlotUnblocker slotUnblocker;

	/** The area dates dao. */
	@Autowired
	AreaDatesDao areaDatesDao;

	@Autowired
	MailUtil mailUtil;

	@Autowired
	@Qualifier("payment")
	String paymentmailtext;
	
	SimpleDateFormat formatOfString=new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat formatToConvert=new SimpleDateFormat("MMM dd, YYYY");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#addReservation(com.
	 * dayLeasing.service.dto.PropertyReservationDTO, java.lang.String)
	 */
	@Override
	public PropertyReservationDTO addReservation(
			PropertyReservationDTO propertyReservationDTO, String userId) {
		Propertyreservation propertyReservation = PropertyReservationVTO
				.convertToPropertyReservation(propertyReservationDTO, userId);
		List<AreaDates> areaDateslist = areaDatesDao
				.getAreaDate(propertyReservationDTO.getPropertyAreaUuid());
		Date reservedDate = DateUtils
				.generateDateFromString(propertyReservationDTO
						.getReservationFrom());
		if (reservedDate == null) {
			return null;
		}
		boolean validreservation = false;
		for (AreaDates areadate : areaDateslist) {
			Date fromDate = areadate.getFromDate();
			Date toDate = areadate.getToDate();
			String cost = areadate.getCost();
			int parseInt = (int) (Float.parseFloat(cost) * 100);
			cost = String.valueOf(parseInt);
			int fromComparision = reservedDate.compareTo(fromDate);
			int toComparision = reservedDate.compareTo(toDate);
			if (fromComparision >= 0 && toComparision <= 0
					&& cost.equalsIgnoreCase(propertyReservationDTO.getPrice())) {
				validreservation = true;
				break;
			}

		}
		if (validreservation == false
				|| propertyReservationDao.getReservationsByDateforSlot(
						propertyReservationDTO.getPropertySlotsUuid(),
						reservedDate).size() > 0) {
			return null;
		}

		Propertyreservation prReservation = propertyReservationDao
				.addReservation(propertyReservation);
		PropertyReservationDTO propertyReservationDTO2 = PropertyReservationVTO
				.convertToPropertyReservationDTO(prReservation);
		slotUnblocker.unblockSlots(prReservation.getPropertyReservationUuid(),
				prReservation.getCartId());
		return propertyReservationDTO2;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#getReservationByUserId
	 * (java.lang.String)
	 */
	@Override
	public List<PropertyReservationDTO> getReservationByUserId(String userId) {
		List<Propertyreservation> propertyReservations = propertyReservationDao
				.getReservations(userId);
		List<PropertyReservationDTO> reservationDTOs = new ArrayList<>();
		for (Propertyreservation p : propertyReservations) {

			reservationDTOs.add(PropertyReservationVTO
					.convertToPropertyReservationDTO(p));
		}
		return reservationDTOs;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#cancelTheBooking(java
	 * .lang.String)
	 */
	public boolean cancelTheBooking(String resId) {
		return propertyReservationDao.cancelTheBooking(resId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#getReservationsBypropertyId
	 * (java.lang.String)
	 */
	@Override
	public List<PropertyReservationDTO> getReservationsBypropertyId(
			String propID) {
		List<Propertyreservation> propertyReservations = propertyReservationDao
				.getReservationsByPropertyId(propID);
		List<PropertyReservationDTO> reservationDTOs = new ArrayList<>();
		for (Propertyreservation p : propertyReservations) {

			reservationDTOs.add(PropertyReservationVTO
					.convertToPropertyReservationDTO(p));
		}
		return reservationDTOs;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#getReservationsOfLandowner
	 * (java.lang.String)
	 */
	public List<PropertyReservationDTO> getReservationsOfLandowner(
			String userUuid) {
		List<Propertyreservation> propertyReservations = propertyReservationDao
				.getReservationsOfLandowner(userUuid);
		List<PropertyReservationDTO> reservationDTOs = new ArrayList<>();
		for (Propertyreservation p : propertyReservations) {

			reservationDTOs.add(PropertyReservationVTO
					.convertToPropertyReservationDTO(p));
		}
		return reservationDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dayLeasing.service.PropertyReservationService#
	 * getReservationsByRegistrationId(java.lang.String)
	 */
	@Override
	public List<PropertyReservationDTO> getReservationsByRegistrationId(
			String regisId) {
		List<Propertyreservation> propertyReservations = propertyReservationDao
				.getReservationsByRegistrationId(regisId);
		List<PropertyReservationDTO> reservationDTOs = new ArrayList<>();
		for (Propertyreservation p : propertyReservations) {

			reservationDTOs.add(PropertyReservationVTO
					.convertToPropertyReservationDTO(p));
		}
		return reservationDTOs;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dayLeasing.service.PropertyReservationService#
	 * getReservationsByRegistrationId(java.lang.String, java.lang.String)
	 */
	@Override
	public PropertyReservationDTO getReservationsByRegistrationId(
			String regisId, String userId) {
		Propertyreservation propertyReservation = propertyReservationDao
				.getReservationsByRegistrationId_UserId(regisId, userId);
		PropertyReservationDTO reservationDTO = (PropertyReservationVTO
				.convertToPropertyReservationDTO(propertyReservation));

		return reservationDTO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#updateReservation(com
	 * .dayLeasing.service.dto.PropertyReservationDTO, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean updateReservation(
			PropertyReservationDTO propertyreservationDTO, String areaID,
			String regisId) {
		return propertyReservationDao.updateReservation(propertyreservationDTO,
				areaID, regisId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#deleteReservation(java
	 * .lang.String)
	 */
	@Override
	public boolean deleteReservation(String regisId) {
		// TODO Auto-generated method stub
		return propertyReservationDao.deleteReservationToUnblock(regisId, null,
				"Blocked");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#getReservationsByDate
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public List<PropertyReservationDTO> getReservationsByDate(
			String propertyId, String date) {
		List<Propertyreservation> propertyReservations = propertyReservationDao
				.getReservationsByDate(propertyId, date);
		List<PropertyReservationDTO> reservationDTOs = new ArrayList<>();
		for (Propertyreservation p : propertyReservations) {

			reservationDTOs.add(PropertyReservationVTO
					.convertToPropertyReservationDTO(p));
		}
		return reservationDTOs;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#cartBooking(com.dayLeasing
	 * .service.dto.CartDTO, java.lang.String, java.lang.String)
	 */
	@Override
	public CartDTO cartBooking(CartDTO cart, String cartId, String hunterId) {
		List<Propertyreservation> blockedReservation = propertyReservationDao
				.getBlockedReservation(cartId, hunterId);
		int amount = 0;
		Map paymentToken = cart.getPaymentToken();
		Map card = (Map) paymentToken.get("card");
		String mailidOfUser = (String) card.get("name");

		List<PropertyReservationDTO> bookings = cart.getBookings();
		String userId = null;
		for (Propertyreservation propertyreservation : blockedReservation) {
			userId = propertyreservation.getUserGuid();
			String status = "fail";
			String price = propertyreservation.getPrice();
			int slotprice = Integer.parseInt(price);
			amount = amount + slotprice;
			for (PropertyReservationDTO booking : bookings) {
				String propertyReservationUuid = booking
						.getPropertyReservationUuid();
				String reservationFrom = booking.getReservationFrom();
				String propertyReservationUuid2 = propertyreservation
						.getPropertyReservationUuid();
				Date reservationFrom2 = propertyreservation
						.getReservationFrom();
				String generateStringFromDate = DateUtils
						.generateStringFromDate(reservationFrom2);
				if (propertyReservationUuid
						.equalsIgnoreCase(propertyReservationUuid2)
						&& reservationFrom
								.equalsIgnoreCase(generateStringFromDate)) {
					status = "success";
					break;
				}

			}
			if (status.equalsIgnoreCase("fail")) {
				propertyReservationDao.deleteReservationToUnblock(null, cartId,
						"Pending");
				cart.setPaymentStatus("fail");
				return cart;
			}
			// do payment
			// if pass

		}
		String total = cart.getTotal();
		String couponCode = cart.getCouponCode();
		String userName = cart.getUserName();
		if (couponCode != null) {
			DayleasingCoupons coupon = propertyReservationDao.getCoupon(userId,
					couponCode);
			if (coupon != null) {
				int originalAmount=amount;
				String percentageDiscount = coupon.getPercentageDiscount();
				String amountDiscount = coupon.getAmountDiscount();
				String amountLimit = coupon.getAmountLimit();
				String minimumAmountCriteria = coupon
						.getMinimumAmountCriteria();
				int minimumAmountOfCriteria = 0;
				if (minimumAmountCriteria != null
						&& !minimumAmountCriteria.isEmpty()) {
					minimumAmountOfCriteria = Integer
							.parseInt(minimumAmountCriteria) * 100;
				}
				if (amount > minimumAmountOfCriteria) {
					if (percentageDiscount != null
							&& !percentageDiscount.isEmpty()
							&& !percentageDiscount.equalsIgnoreCase("null")) {
						float percentageDiscounted = Float
								.parseFloat(percentageDiscount);
						int discount = (int) (amount * (percentageDiscounted / 100));

						if (amountLimit != null && !amountLimit.isEmpty()
								&& !amountLimit.equalsIgnoreCase("null")) {
							int amountLimited = Integer.parseInt(amountLimit) * 100;
							if (discount > amountLimited) {
								discount = amountLimited;
							}
						}
						amount = amount - discount;
					} else if (amountDiscount != null
							&& !amountDiscount.isEmpty()
							&& !amountDiscount.equalsIgnoreCase("null")) {
						int amountDiscounted = Integer.parseInt(amountDiscount) * 100;
						amount = amount - amountDiscounted;
					}
				}
				//calculating price for each slot
				/*if(amount!=originalAmount){
					int discountAmount=originalAmount-amount;
					try{
						int variantInPrice=0;
					for (Propertyreservation reservationToUpdate : blockedReservation) {
						String price = reservationToUpdate.getPrice();
						int priceOfSlot = Integer.parseInt(price);
						int slotDiscount=(priceOfSlot*discountAmount)/originalAmount;
						variantInPrice=variantInPrice+slotDiscount;
						
						reservationToUpdate.setPrice(String.valueOf(priceOfSlot-slotDiscount));
					}
					variantInPrice=0+(discountAmount-variantInPrice);
					if(variantInPrice!=0){
						Propertyreservation propertyreservation = blockedReservation.get(0);
						int price = Integer.parseInt(propertyreservation.getPrice());
						propertyreservation.setPrice(String.valueOf(price-variantInPrice));
					}
					
					propertyReservationDao.updateSlotsPriceWithDiscount(blockedReservation);
					}catch(Exception e){
						propertyReservationDao.deleteReservationToUnblock(null, cartId,
								"Pending");
						cart.setPaymentStatus("fail");
						return cart;
					}
					
				}*/
			}
		}

		int cartAmount = Integer.parseInt(total);
		if (cartAmount == amount) {
			// do payment

			String tokenId = (String) cart.getPaymentToken().get("id");
			RequestOptions requestOptions = (new RequestOptionsBuilder())
					.setApiKey("sk_test_fY7mcp5UxG3zKh3whKTyapWA").build();
			/*
			 * create a Map object which is used for charging the customer and
			 * to provide refund to the customer. This object needs a source as
			 * a key and token from the front end as value(this field is
			 * mandatory). and it is passed as a parameter to create a charge
			 * object.
			 */
			Map<String, Object> chargeMap = new HashMap<String, Object>();
			Stripe.apiKey = "sk_test_fY7mcp5UxG3zKh3whKTyapWA";
			// System.out.println("hai "+request);
			chargeMap.put("amount", amount);
			chargeMap.put("currency", "usd");
			chargeMap.put("source", tokenId);

			// chargeMap.put("source",reque);
			// Map<String, Object> cardMap = new HashMap<String, Object>();
			// cardMap.put("number", "4242424242424242");
			// cardMap.put("exp_month", 12);
			// cardMap.put("exp_year", 2020);
			// chargeMap.put("card", cardMap);

			try {
				// charge object is created which takes a map object as a
				// parameter
				// charge object has a charge id which is used for both payment
				// and refund of the transaction.

				Charge charge = null;
				String status = null;
				String receiptEmail = null;
				String chargeId = null;
				if (amount == 0 && bookings.size() > 0) {
					status = "succeeded";
					receiptEmail = mailidOfUser;
				}
				else {
					charge = Charge.create(chargeMap, requestOptions);// store
					// charge
					// id
					// in
					// database
					// and
					// use
					// this
					// as
					// refund

					status = charge.getStatus();
					receiptEmail = charge.getSource().toJson();
					chargeId = charge.getId();
				}
				if (status.equalsIgnoreCase("succeeded")) {
					// System.out.println(charge);

					try {

						boolean confirmThePayment = propertyReservationDao
								.ConfirmThePayment(cartId, chargeId,
										receiptEmail, couponCode, userName,total);
						try {
							String replace = paymentmailtext.replace(
									"texttoreplacehere",
									String.valueOf(amount / 100));

							// //mailContent///
							List<PropertyReservationDTO> bookingsForhtml = cart
									.getBookings();
							String replace1 = "";
							for (PropertyReservationDTO propertyRes : bookingsForhtml) {
								replace1 = replace1
										+"<tr><td width='30%'  style='font-size: 18px; line-height: 24px; font-family: Arial, Helvetica, sans-serif; color: #7d7067; text-align: center' st-content='fulltext-content' align='center'>"+propertyRes.getPropertyName()+"</td>"
										+ "<td width='35%' style='font-size: 18px; line-height: 24px; font-family: Arial, Helvetica, sans-serif; color: #7d7067; text-align: center' st-content='fulltext-content' align='center'>"
										+ propertyRes.getSlotName()
										+ "</td><td width='35%' style='font-size: 18px; line-height: 24px; font-family: Arial, Helvetica, sans-serif; color: #7d7067; text-align: center' st-content='fulltext-content' align='center'>"
										+ formatToConvert.format(formatOfString.parse( propertyRes.getReservationFrom()))
										+ "</td></tr>";
							}
							String replace2 = replace.replace(
									"dynamictextreplacement", replace1);
							// ////////////////

							mailUtil.sendMail(mailidOfUser, "Payment Success!",
									replace2);
						} catch (Exception e) {
						}
						System.out.println(charge);
						cart.setPaymentStatus("success");
						return cart;
					} catch (Exception e) {
						Map<String, Object> refundParams = new HashMap<String, Object>();
						refundParams.put("charge", charge.getId());
						// refund object which uses the charge id from the
						// charge object created.
						Refund refund = Refund.create(refundParams);
						System.out.println(new Date()
								+ "-->Refunding the fail db transaction<--"
								+ refund);
						propertyReservationDao.deleteReservationToUnblock(null,
								cartId, "Pending");
						cart.setPaymentStatus("refund");
						return cart;
					}
				} else {
					propertyReservationDao.deleteReservationToUnblock(null,
							cartId, "Pending");
					cart.setPaymentStatus("fail");
					return cart;
				}

			} catch (StripeException e) {
				e.printStackTrace();
				propertyReservationDao.deleteReservationToUnblock(null, cartId,
						"Pending");
				cart.setPaymentStatus("fail");
				return cart;
			}

			// if pass update db
			// if db success send success
			// else refund it.

		} else {
			propertyReservationDao.deleteReservationToUnblock(null, cartId,
					"Pending");
			cart.setPaymentStatus("fail");
			return cart;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertyReservationService#getCoupon(java.lang
	 * .String, java.lang.String)
	 */
	public DayleasingCoupons getCoupon(String hunterId, String coupon) {
		return propertyReservationDao.getCoupon(hunterId, coupon);
	}
}
