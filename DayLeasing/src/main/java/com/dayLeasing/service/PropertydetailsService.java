package com.dayLeasing.service;

import java.util.List;

import com.dayLeasing.service.dto.PropertydetailsDTO;
// TODO: Auto-generated Javadoc

/**
 * The Interface PropertydetailsService.
 *
 * @author Balaram
 */
public interface PropertydetailsService {

  /**
   * Adds the property.
   *
   * @param propertydetailsDTO the propertydetails DTO
   * @param userId the user id
   * @return true, if successful
   */
  public PropertydetailsDTO addProperty(PropertydetailsDTO propertydetailsDTO, String userId);

  /**
   * Gets the properties.
   *
   * @param userId the user id
   * @return the properties
   */
  public List<PropertydetailsDTO> getProperties(String userId);
  public List<PropertydetailsDTO> getPropertiesOfUser(String userId);
  /**
   * Gets the property.
   *
   * @param userId the user id
   * @param propertyId the property id
   * @return the property
   */
  public PropertydetailsDTO getProperty(String userId, String propertyId);

  /**
   * Update user.
   *
   * @param propertydetailsDTO the propertydetails DTO
   * @param userId the user id
   * @param propertyId the property id
   * @return true, if successful
   */
  public boolean updateProperty(PropertydetailsDTO propertydetailsDTO, String userId, String propertyId);

  /**
   * Delete property.
   *
   * @param userId the user id
   * @param propertyId the property id
   * @return true, if successful
   */
  public boolean deleteProperty(String userId, String propertyId);
  public int getReservationsCount(String propertyId);
}
