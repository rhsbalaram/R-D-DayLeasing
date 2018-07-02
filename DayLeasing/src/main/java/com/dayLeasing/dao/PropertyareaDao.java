package com.dayLeasing.dao;

import java.util.List;

import org.hibernate.Session;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.Propertyarea;
import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.dto.PropertyareaDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
// TODO: Auto-generated Javadoc

/**
 * The Interface PropertyareaDao.
 *
 * @author Balaram
 */
public interface PropertyareaDao {

  /**
   * Adds the property area.
   *
   * @param propertyarea the propertyarea
   * @return true, if successful
   */
  public String addPropertyArea(Propertyarea propertyarea);

  /**
   * Gets the property area.
   *
   * @param propertyAreaUuid the propertyAreaUuid
   * @return the property area
   */
  public Propertyarea getPropertyArea( String propertyAreaUuid);

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
   * Gets the property areas.
   *
   * @param propertyId the propertyId
   * @return the property areas
   */
  List<Propertyarea> getPropertyAreas(String propertyId);

}
