package com.dayLeasing.service;

import java.util.List;

import com.dayLeasing.service.dto.PropertyareaDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;
// TODO: Auto-generated Javadoc

/**
 * The Interface PropertySlotsService.
 *
 * @author Balaram
 */
public interface PropertySlotsService {

  /**
   * Adds the slot.
   *
   * @param propertyslotsDTO the propertyslots DTO
   * @param areaID the area ID
   * @return property slot, if successful
   */
  public PropertyslotsDTO addSlot(PropertyslotsDTO propertyslotsDTO, String areaID);
  
  /**
   * Adds the slot.
   *
   * @param propertyAreaDTO the property area DTO
   * @param areaID the area ID
   * @return List of property slots, if successful
   */
  public List<PropertyslotsDTO> addMultipleSlots(List<PropertyslotsDTO> propertyslotsDTOS, String areaID);
    

  /**
   * Gets the slots.
   *
   * @param areaID the area ID
   * @return the slots
   */
  public List<PropertyslotsDTO> getSlots(String areaID);

  /**
   * Gets the slot.
   *
   * @param areaID the area ID
   * @param slotId the slot id
   * @return the slot
   */
  public PropertyslotsDTO getSlot(String areaID, String slotId);

  /**
   * Update slots.
   *
   * @param propertyslotsDTO the propertyslots DTO
   * @param areaID the area ID
   * @param slotId the slot id
   * @return true, if successful
   */
  public boolean updateSlots(PropertyslotsDTO propertyslotsDTO, String areaID, String slotId);

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
