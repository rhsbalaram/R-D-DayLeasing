package com.dayLeasing.service;

import java.util.List;

import com.dayLeasing.dao.model.AreaDates;
import com.dayLeasing.service.dto.AreaDatesDTO;

// TODO: Auto-generated Javadoc

/**
 * The Interface AreaDatesService.
 *
 * @author Balaram
 */
public interface AreaDatesService {

	/**
	 * Adds the area date.
	 *
	 * @param areaDatesDto
	 *            the area dates dto
	 * @return the string
	 */
	public String addAreaDate(AreaDatesDTO areaDatesDto);

	/**
	 * Gets the area date.
	 *
	 * @param propertyAreaUuid
	 *            the property area uuid
	 * @return the area date
	 */
	public List<AreaDatesDTO> getAreaDate(String propertyAreaUuid);

	/**
	 * Update area date.
	 *
	 * @param areaDatesDto
	 *            the area dates dto
	 * @return true, if successful
	 */
	public boolean updateAreaDate(AreaDatesDTO areaDatesDto);

	/**
	 * Area date delete.
	 *
	 * @param propertyAreaUuid
	 *            the property area uuid
	 * @return true, if successful
	 */
	public boolean AreaDateDelete(String propertyAreaUuid);

}
