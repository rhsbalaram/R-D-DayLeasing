package com.dayLeasing.dao;

import java.util.List;

import com.dayLeasing.dao.model.AreaDates;

// TODO: Auto-generated Javadoc

/**
 * The Interface AreaDatesDao.
 *
 * @author Balaram
 */
public interface AreaDatesDao {

	/**
	 * Adds the area date.
	 *
	 * @param areadates
	 *            the areadates
	 * @return the string
	 */
	String addAreaDate(AreaDates areadates);

	/**
	 * Update area date.
	 *
	 * @param areadates
	 *            the areadates
	 * @return true, if successful
	 */
	public boolean updateAreaDate(AreaDates areadates);

	/**
	 * Gets the area date.
	 *
	 * @param propertyAreaUuid
	 *            the property area uuid
	 * @return the area date
	 */
	List<AreaDates> getAreaDate(String propertyAreaUuid);

	/**
	 * Area date delete.
	 *
	 * @param propertyAreaUuid
	 *            the property area uuid
	 * @return true, if successful
	 */
	boolean AreaDateDelete(String propertyAreaUuid);

}
