package com.dayLeasing.service;

import java.util.List;

import com.dayLeasing.dao.model.Propertyarea;
import com.dayLeasing.service.dto.PropertyareaDTO;
// TODO: Auto-generated Javadoc

/**
 * The Interface PropertyareaService.
 *
 * @author Balaram
 */
public interface PropertyareaService {

  /**
   * Adds the property area.
   *
   * @param propertyarea the propertyarea
   * @param areaID the area ID
   * @return true, if successful
   */
  public String addPropertyArea(PropertyareaDTO propertyarea, String areaID);

  /**
   * Gets the property areas.
   *
   * @param userId the user id
   * @return the property areas
   */
  public List<PropertyareaDTO> getPropertyAreas(String userId);

  /**
   * Update property.
   *
   * @param PropertyareaDTO the propertyarea DTO
   * @param userId the user id
   * @param propertyId the property id
   * @return true, if successful
   */
  public boolean updateProperty(PropertyareaDTO PropertyareaDTO, String userId, String propertyId);

  /**
   * Property delete.
   *
   * @param userId the user id
   * @param propertyId the property id
   * @return true, if successful
   */
  public boolean AreaDelete(String userId, String propertyId);
  public int getReservationsCount(String areaId);
  /**
   * Gets the property area.
   *
   * @param userId the user id
   * @param propertyId the property id
   * @return the property area
   */
  public PropertyareaDTO getPropertyArea( String areauuid);

}
