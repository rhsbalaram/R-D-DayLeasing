package com.dayLeasing.service.vto;

import java.util.Date;

import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.service.dto.PropertyslotsDTO;
import com.dayLeasing.service.util.GenerateUUID;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyslotsVTO.
 *
 * @author Balaram
 */
public class PropertyslotsVTO {

  /**
   * Convert to propertyslots.
   *
   * @param PropertyslotsDTO
   *          the propertyslots DTO
   * @param areaID
   *          the area ID
   * @return the propertyslots
   */
  public static Propertyslots convertToPropertyslots(PropertyslotsDTO PropertyslotsDTO,
      String areaID) {
    Propertyslots propertyslots = new Propertyslots();
    // propertyslots.setIsActive(PropertyslotsDTO.getIsActive());
    propertyslots.setPropertyAreaUuid(areaID);
    // propertyslots.setAgree(PropertyslotsDTO.set);
    // propertyslots.setPropertySlotsId(PropertyslotsDTO.getPropertySlotsId());
    propertyslots.setPropertySlotsUuid(GenerateUUID.generateUUID());

    // propertyslots.setPropertyUuid((PropertyslotsDTO.getPropertyUuid()));
    // Instant instant = propertyslots.getDob().toInstant();
    propertyslots.setSlotFrom(PropertyslotsDTO.getSlotFrom());
    propertyslots.setSlotId(PropertyslotsDTO.getSlotId());
    propertyslots.setSlotName(PropertyslotsDTO.getSlotName());
    propertyslots.setSlotTo(PropertyslotsDTO.getSlotTo());
    propertyslots.setSlotPrice(PropertyslotsDTO.getSlotPrice());
    propertyslots.setSlotSize(PropertyslotsDTO.getSlotSize());
    propertyslots.setMaxHunter(PropertyslotsDTO.getMaxHunter());
    propertyslots.setOutLine(PropertyslotsDTO.getOutLine());

    return propertyslots;
  }

  /**
   * Convert to propertyslots DTO.
   *
   * @param propertyslots
   *          the propertyslots
   * @return the propertyslots DTO
   */
  public static PropertyslotsDTO convertToPropertyslotsDTO(Propertyslots propertyslots) {
    PropertyslotsDTO propertyslotsDTO = new PropertyslotsDTO();
    // propertyslotsDTO.setIsActive(propertyslots.getIsActive());
    propertyslotsDTO.setPropertyAreaUuid(propertyslots.getPropertyAreaUuid());
    // propertyslots.setAgree(PropertyslotsDTO.set);
    
    propertyslotsDTO.setPropertySlotsId(propertyslots.getPropertySlotsId());
    propertyslotsDTO.setPropertySlotsUuid(propertyslots.getPropertySlotsUuid());

    // propertyslotsDTO.setPropertyUuid((propertyslots.getPropertyUuid()));
    // Instant instant = propertyslots.getDob().toInstant();
    propertyslotsDTO.setSlotFrom(propertyslots.getSlotFrom());
    propertyslotsDTO.setSlotId(propertyslots.getSlotId());
    propertyslotsDTO.setSlotName(propertyslots.getSlotName());
    propertyslotsDTO.setSlotTo(propertyslots.getSlotTo());
    propertyslotsDTO.setSlotPrice(propertyslots.getSlotPrice());
    propertyslotsDTO.setSlotSize(propertyslots.getSlotSize());
    propertyslotsDTO.setMaxHunter(propertyslots.getMaxHunter());
    propertyslotsDTO.setOutLine(propertyslots.getOutLine());

    return propertyslotsDTO;
  }

  /**
   * Convert to propertyslots to update.
   *
   * @param PropertyslotsDTO
   *          the propertyslots DTO
   * @param propertyslots
   *          the propertyslots
   * @return the propertyslots
   */
  public static Propertyslots convertToPropertyslotsToUpdate(PropertyslotsDTO PropertyslotsDTO,
      Propertyslots propertyslots) {

    // propertyslots.setIsActive(PropertyslotsDTO.getIsActive());
    // propertyslots.setPropertyAreaUuid(PropertyslotsDTO.getPropertyAreaUuid());
    // propertyslots.setAgree(PropertyslotsDTO.set);
    // propertyslots.setPropertySlotsId(PropertyslotsDTO.getPropertySlotsId());
    // propertyslots.setPropertySlotsUuid(PropertyslotsDTO.getPropertySlotsUuid());

    // propertyslots.setPropertyUuid((PropertyslotsDTO.getPropertyUuid()));
    // Instant instant = propertyslots.getDob().toInstant();
    propertyslots.setSlotFrom(PropertyslotsDTO.getSlotFrom());
    // propertyslots.setSlotId(PropertyslotsDTO.getSlotId());
    propertyslots.setSlotName(PropertyslotsDTO.getSlotName());
    propertyslots.setSlotTo(PropertyslotsDTO.getSlotTo());
    propertyslots.setSlotPrice(PropertyslotsDTO.getSlotPrice());
    propertyslots.setSlotSize(PropertyslotsDTO.getSlotSize());
    propertyslots.setMaxHunter(PropertyslotsDTO.getMaxHunter());
    propertyslots.setOutLine(PropertyslotsDTO.getOutLine());

    return propertyslots;
  }
}
