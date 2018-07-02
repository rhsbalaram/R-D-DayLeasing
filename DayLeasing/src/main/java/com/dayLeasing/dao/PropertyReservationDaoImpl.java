package com.dayLeasing.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dayLeasing.service.util.DateUtils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.dao.model.Propertyreservation;
import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.service.dto.PropertyReservationDTO;
import com.dayLeasing.service.vto.PropertyReservationVTO;
import com.dayLeasing.service.vto.PropertyslotsVTO;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertyReservationDaoImpl.
 *
 * @author Balaram
 */
@Transactional
@Repository
public class PropertyReservationDaoImpl extends AbstractDao implements
		PropertyReservationDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#addReservation(com.dayLeasing
	 * .dao.model.Propertyreservation)
	 */
	@Override
	public Propertyreservation addReservation(
			Propertyreservation propertyReservation) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		openSession.save(propertyReservation);
		openSession.close();
		return propertyReservation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#getReservations(java.lang.String
	 * )
	 */
	@Override
	public List<Propertyreservation> getReservations(String userId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select reservation from Propertyreservation as reservation where reservation.userGuid = :userId and reservation.paymentStatus = 'Booked'");
		createQuery.setParameter("userId", userId);

		List<Propertyreservation> list = (List<Propertyreservation>) createQuery
				.list();
		openSession.close();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#getReservationsByPropertyId
	 * (java.lang.String)
	 */
	@Override
	public List<Propertyreservation> getReservationsByPropertyId(String propID) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select reservation from Propertyreservation as reservation where reservation.propertyUuid = :propID");
		createQuery.setParameter("propID", propID);

		List<Propertyreservation> list = (List<Propertyreservation>) createQuery
				.list();
		openSession.close();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#getReservationsOfLandowner(
	 * java.lang.String)
	 */
	@Override
	public List<Propertyreservation> getReservationsOfLandowner(String userUuid) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select reservation from Propertyreservation as reservation where reservation.propertyUuid in ( Select property.propertyUuid from Propertydetails as property where property.userGuid = :userId ) and (reservation.paymentStatus = 'Booked' or reservation.paymentStatus = 'Cancelled')");
		createQuery.setParameter("userId", userUuid);

		List<Propertyreservation> list = (List<Propertyreservation>) createQuery
				.list();
		openSession.close();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#getReservationsByRegistrationId
	 * (java.lang.String)
	 */
	@Override
	public List<Propertyreservation> getReservationsByRegistrationId(
			String regisId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select reservation from Propertyreservation as reservation where reservation.propertyReservationId = :regisId");
		createQuery.setParameter("regisId", Integer.parseInt(regisId));

		List<Propertyreservation> list = (List<Propertyreservation>) createQuery
				.list();
		openSession.close();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dayLeasing.dao.PropertyReservationDao#
	 * getReservationsByRegistrationId_UserId(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Propertyreservation getReservationsByRegistrationId_UserId(
			String regisId, String userId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select reservation from Propertyreservation as reservation where reservation.propertyReservationId = :regisId and reservation.userGuid = :userId");
		createQuery.setParameter("regisId", Integer.parseInt(regisId));
		createQuery.setParameter("userId", userId);
		List<Propertyreservation> list = (List<Propertyreservation>) createQuery
				.list();
		openSession.close();
		// if(list.size()>0)
		return list.get(0);
		// return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#updateReservation(com.dayLeasing
	 * .service.dto.PropertyReservationDTO, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateReservation(
			PropertyReservationDTO propertyreservationDTO, String userId,
			String regisId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();

		Query createQuery = openSession
				.createQuery("Select reservation from Propertyreservation as reservation where reservation.propertyReservationId = :regisId and reservation.userGuid = :userId");
		createQuery.setParameter("regisId", Integer.parseInt(regisId));
		createQuery.setParameter("userId", userId);
		List<Propertyreservation> list = (List<Propertyreservation>) createQuery
				.list();
		if (list.size() > 0) {
			Propertyreservation propertyreservation = list.get(0);
			Propertyreservation propertyreservation2 = PropertyReservationVTO
					.convertToPropertyReservationToUpdate(
							propertyreservationDTO, propertyreservation);
			openSession.update(propertyreservation2);
		}
		openSession.flush();
		openSession.close();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#deleteReservation(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public boolean deleteReservation(String regisId, String userId) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("delete from Propertyreservation as reservation where reservation.propertyReservationId = :regisId and reservation.userGuid = :userId");
		createQuery.setParameter("regisId", regisId);
		createQuery.setParameter("userId", userId);
		createQuery.executeUpdate();

		openSession.flush();
		openSession.close();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#deleteReservationToUnblock(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteReservationToUnblock(String reservationId,
			String cartId, String paymentStatus) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("delete from Propertyreservation as reservation where reservation.paymentStatus = :paymentStatus and (reservation.propertyReservationUuid = :propertyReservationUuid or reservation.cartId = :cartId)");
		createQuery.setParameter("paymentStatus", paymentStatus);
		createQuery.setParameter("propertyReservationUuid", reservationId);
		createQuery.setParameter("cartId", cartId);
		createQuery.executeUpdate();

		openSession.flush();
		openSession.close();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#getBlockedReservation(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public List<Propertyreservation> getBlockedReservation(String cartId,
			String hunterId) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createupdateQuery = openSession
				.createQuery("Update Propertyreservation as reservation set reservation.paymentStatus = 'Pending',reservation.userGuid=:hunterId  where reservation.paymentStatus = 'Blocked' and  reservation.cartId = :cartId");
		createupdateQuery.setParameter("cartId", cartId);
		createupdateQuery.setParameter("hunterId", hunterId);
		createupdateQuery.executeUpdate();
		openSession.flush();
		Query createQuery = openSession
				.createQuery("select reservation from Propertyreservation as reservation where reservation.paymentStatus = 'Pending' and  reservation.cartId = :cartId");

		createQuery.setParameter("cartId", cartId);
		List<Propertyreservation> list = (List<Propertyreservation>) createQuery
				.list();
		openSession.close();
		return list;
	}
	
	
	public void updateSlotsPriceWithDiscount(List<Propertyreservation> reservationList){
		SessionFactory sessionFactory= getSessionFactory();
		Session openSession = sessionFactory.openSession();
		for(Propertyreservation propertyreservation : reservationList){
			openSession.update(propertyreservation);
			openSession.flush();
		}
		openSession.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#ConfirmThePayment(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean ConfirmThePayment(String cartId, String refundId,
			String mailId, String couponCode, String userName,String cartAmount) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createupdateQuery = openSession
				.createQuery("Update Propertyreservation as reservation set reservation.paymentStatus = 'Booked' , reservation.paymentSuccessId = :refundId, reservation.userName = :userName , reservation.paymentMailId=:mailId ,reservation.couponCode =:couponCode, reservation.cartPrice =:cartPrice  where reservation.paymentStatus = 'Pending' and  reservation.cartId = :cartId");
		createupdateQuery.setParameter("cartId", cartId);
		createupdateQuery.setParameter("refundId", refundId);
		createupdateQuery.setParameter("mailId", mailId);
		createupdateQuery.setParameter("couponCode", couponCode);
		createupdateQuery.setParameter("userName", userName);
		createupdateQuery.setParameter("cartPrice", cartAmount);
		createupdateQuery.executeUpdate();
		openSession.flush();
		openSession.close();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#cancelTheBooking(java.lang.
	 * String)
	 */
	@Override
	public boolean cancelTheBooking(String resId) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createupdateQuery = openSession
				.createQuery("Update Propertyreservation as reservation set reservation.paymentStatus = 'Cancelled'  where reservation.propertyReservationUuid  = :resId");
		createupdateQuery.setParameter("resId", resId);

		createupdateQuery.executeUpdate();
		openSession.flush();
		openSession.close();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#getReservationsByDate(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public List<Propertyreservation> getReservationsByDate(String propertyId,
			String date) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();

		Date selectedDate = DateUtils.generateDateFromString(date);
		Query createQuery = openSession
				.createQuery("Select reservation from Propertyreservation as reservation where reservation.propertyUuid =:propertyId and reservation.paymentStatus != 'Cancelled' and reservation.reservationFrom <= :selectedDate and reservation.reservationTo >= :selectedDate ");
		createQuery.setParameter("propertyId", propertyId);

		createQuery.setParameter("selectedDate", selectedDate);

		List<Propertyreservation> list = (List<Propertyreservation>) createQuery
				.list();
		openSession.close();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#getReservationsByDateforSlot
	 * (java.lang.String, java.util.Date)
	 */
	@Override
	public List<Propertyreservation> getReservationsByDateforSlot(
			String slotId, Date selectedDate) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();

		Query createQuery = openSession
				.createQuery("Select reservation from Propertyreservation as reservation where reservation.propertySlotsUuid =:slotId and reservation.paymentStatus != 'Cancelled' and reservation.reservationFrom <= :selectedDate and reservation.reservationTo >= :selectedDate ");
		createQuery.setParameter("slotId", slotId);

		createQuery.setParameter("selectedDate", selectedDate);

		List<Propertyreservation> list = (List<Propertyreservation>) createQuery.list();
		openSession.close();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.dao.PropertyReservationDao#getCoupon(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public DayleasingCoupons getCoupon(String hunterId, String coupon) {

		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("select count(*) from Propertyreservation as res where res.userGuid=:hunterId and res.couponCode=:coupon");
		createQuery.setParameter("hunterId", hunterId);
		createQuery.setParameter("coupon", coupon);
		Long count = (Long) createQuery.uniqueResult();
		Query createQuery2 = openSession
				.createQuery("select coupon from DayleasingCoupons as coupon where coupon.couponCode=:coupon and coupon.fromDate<=:sqlDate and coupon.toDate>=:sqlDate");

		createQuery2.setParameter("coupon", coupon);
		createQuery2.setParameter("sqlDate",
				new java.sql.Date(new Date().getTime()));
		List<DayleasingCoupons> list = createQuery2.list();
		openSession.close();
		if (list.size() > 0) {
			DayleasingCoupons dayleasingCoupons = list.get(0);
			Integer usageCount = dayleasingCoupons.getUsageCount();
			if (count != null) {
				int countvalue = count.intValue();
				if (countvalue < usageCount) {
					return dayleasingCoupons;
				} else {
					dayleasingCoupons.setUsageCount(-1);
					return dayleasingCoupons;
				}
			} else {
				return dayleasingCoupons;
			}
		} else {
			return null;
		}

	}

}
