package com.dayLeasing.dao;

import java.util.List;

import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.service.dto.PropertyslotsDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PropertySlotsDao.
 *
 * @author Balaram
 */
public interface PropertySlotsDao {

  /**
   * Adds the slots.
   *
   * @param propertyslots the propertyslots
   * @return true, if successful
   */
  public Propertyslots addSlots(Propertyslots propertyslots);
  
  /**
   * Adds the slots.
   *
   * @param propertyslots the propertyslots
   * @return true, if successful
   */
  public List<Propertyslots> addMultipleSlots(List<Propertyslots> propertyslots);

  /**
   * Gets the slots.
   *
   * @param areaID the area ID
   * @return the slots
   */
  public List<Propertyslots> getSlots(String areaID);

  /**
   * Gets the slot.
   *
   * @param areaID the area ID
   * @param slotId the slot id
   * @return the slot
   */
  public Propertyslots getSlot(String areaID, String slotId);

  /**
   * Update slot.
   *
   * @param propertyslotsDTO the propertyslots DTO
   * @param areaID the area ID
   * @param slotId the slot id
   * @return true, if successful
   */
  public boolean updateSlot(PropertyslotsDTO propertyslotsDTO, String areaID, String slotId);

  /**
   * Delete slots.
   *
   * @param areaID the area ID
   * @param slotId the slot id
   * @return true, if successful
   */
  public boolean deleteSlots(String areaID, String slotId);
  public int getReservationsCount(String slotId);
}
