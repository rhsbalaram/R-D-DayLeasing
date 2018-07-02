package com.dayLeasing.service.util;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayLeasing.dao.PropertyReservationDao;

// TODO: Auto-generated Javadoc

/**
 * The Class SlotUnblocker. to make blocked slots to free
 *
 * @author Balaram
 */
@Service
public class SlotUnblocker {

	/** The property reservation dao. */
	@Autowired
	PropertyReservationDao propertyReservationDao;

	/**
	 * Unblock slots.
	 *
	 * @param reservationId
	 *            the reservation id
	 * @param cartId
	 *            the cart id
	 */
	public void unblockSlots(String reservationId, String cartId) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				propertyReservationDao.deleteReservationToUnblock(
						reservationId, cartId, "Blocked");
				timer.cancel();

			}
		}, 5 * 60 * 1000);
	}

}
