package com.dayLeasing.dao;

import java.util.List;

import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.service.dto.PropertydetailsDTO;

// TODO: Auto-generated Javadoc

/**
 * The Interface PropertydetailsDao.
 *
 * @author Balaram
 */
public interface PropertydetailsDao {

	/**
	 * Adds the property.
	 *
	 * @param propertydetails
	 *            the propertydetails
	 * @return true, if successful
	 */
	public Propertydetails addProperty(Propertydetails propertydetails);

	/**
	 * Gets the properties.
	 *
	 * @param userId
	 *            the user id
	 * @return the properties
	 */
	public List<Propertydetails> getProperties(String userId);

	/**
	 * Gets the property.
	 *
	 * @param userId
	 *            the user id
	 * @param propertyId
	 *            the property id
	 * @return the property
	 */
	public Propertydetails getProperty(String userId, String propertyId);

	/**
	 * Gets the properties of user.
	 *
	 * @param userId
	 *            the user id
	 * @return the properties of user
	 */
	public List<Propertydetails> getPropertiesOfUser(String userId);

	/**
	 * Update property.
	 *
	 * @param propertydetailsDTO
	 *            the propertydetails DTO
	 * @param userId
	 *            the user id
	 * @param propertyId
	 *            the property id
	 * @return true, if successful
	 */
	public boolean updateProperty(PropertydetailsDTO propertydetailsDTO,
			String userId, String propertyId);

	/**
	 * Property delete.
	 *
	 * @param userId
	 *            the user id
	 * @param propertyId
	 *            the property id
	 * @return true, if successful
	 */
	public boolean propertyDelete(String userId, String propertyId);
	public int getReservationsCount(String propertyId);
}
