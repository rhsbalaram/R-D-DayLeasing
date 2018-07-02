package com.dayLeasing.service.vto;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.Propertyarea;
import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.dto.PropertyareaDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;
import com.dayLeasing.service.util.GenerateUUID;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertyareaVTO.
 *
 * @author Balaram
 */
public class PropertyareaVTO {

  /**
   * Convert to property area.
   *
   * @param propertyAreaDTO
   *          the property area DTO
   * @param Id
   *          the id
   * @return the propertyarea
   */
  public static Propertyarea convertToPropertyArea(PropertyareaDTO propertyAreaDTO, String Id) {
    Propertyarea propertyareas = new Propertyarea();
    propertyareas.setPropertyAreaUuid(GenerateUUID.generateUUID());
    // propertyareas.setPropertyAreaId(propertyAreaDTO.getPropertyAreaId());
    propertyareas.setPropertyAreaName(propertyAreaDTO.getPropertyAreaName());
    propertyareas.setPropertyHiddenAreaName(propertyAreaDTO.getPropertyHiddenAreaName());
    propertyareas.setPropertyAreaNote(propertyAreaDTO.getPropertyAreaNote());
    propertyareas.setPropertyUuid(Id);
    propertyareas.setPropertyPrice(propertyAreaDTO.getPropertyPrice());
    propertyareas.setPropertySize(propertyAreaDTO.getPropertySize());
    propertyareas.setPropertySlots(propertyAreaDTO.getPropertySlots());
    propertyareas.setMaxHunter(propertyAreaDTO.getMaxHunter());

    return propertyareas;
  }

  /**
   * Convert to property areas DTO.
   *
   * @param propertyArea
   *          the property area
   * @return the propertyarea DTO
   */
  public static PropertyareaDTO convertToPropertyAreasDTO(Propertyarea propertyArea) {
    PropertyareaDTO propertyareasDTO = new PropertyareaDTO();
    propertyareasDTO.setPropertyAreaId(propertyArea.getPropertyAreaId());
    propertyareasDTO.setPropertyAreaName(propertyArea.getPropertyAreaName());
    propertyareasDTO.setPropertyHiddenAreaName(propertyArea.getPropertyHiddenAreaName());
    propertyareasDTO.setPropertyAreaNote(propertyArea.getPropertyAreaNote());
    propertyareasDTO.setPropertyAreaUuid(propertyArea.getPropertyAreaUuid());
    propertyareasDTO.setPropertyPrice(propertyArea.getPropertyPrice());
    propertyareasDTO.setPropertySize(propertyArea.getPropertySize());
    propertyareasDTO.setPropertySlots(propertyArea.getPropertySlots());
    propertyareasDTO.setMaxHunter(propertyArea.getMaxHunter());

    return propertyareasDTO;
  }

  /**
   * Convert to propertyarea to update.
   *
   * @param propertyareaDTO
   *          the propertyarea DTO
   * @param propertyarea
   *          the propertyarea
   * @return the propertyarea
   */
  public static Propertyarea convertToPropertyareaToUpdate(PropertyareaDTO propertyareaDTO,
      Propertyarea propertyarea) {

    // propertyslots.setIsActive(PropertyslotsDTO.getIsActive());
    // propertyslots.setPropertyAreaUuid(PropertyslotsDTO.getPropertyAreaUuid());
    // propertyslots.setAgree(PropertyslotsDTO.set);
    // propertyslots.setPropertySlotsId(PropertyslotsDTO.getPropertySlotsId());
    // propertyslots.setPropertySlotsUuid(PropertyslotsDTO.getPropertySlotsUuid());

    // propertyslots.setPropertyUuid((PropertyslotsDTO.getPropertyUuid()));
    // Instant instant = propertyslots.getDob().toInstant();
    propertyarea.setIsActive(propertyareaDTO.getIsActive());
    propertyarea.setPropertyAreaName(propertyareaDTO.getPropertyAreaName());
    propertyarea.setPropertyHiddenAreaName(propertyareaDTO.getPropertyHiddenAreaName());

    propertyarea.setPropertyAreaNote(propertyareaDTO.getPropertyAreaNote());
    propertyarea.setPropertyPrice(propertyareaDTO.getPropertyPrice());
    propertyarea.setPropertySize(propertyareaDTO.getPropertySize());
    propertyarea.setPropertySlots(propertyareaDTO.getPropertySlots());
    propertyarea.setMaxHunter(propertyareaDTO.getMaxHunter());

    return propertyarea;
  }

}
