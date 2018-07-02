package com.dayLeasing.service.dto;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertyareaDTO.
 *
 * @author Balaram
 */
public class PropertyareaDTO {

  /** The property area id. */
  private Integer propertyAreaId;

  /** The property area uuid. */
  private String propertyAreaUuid;

  /** The property uuid. */
  private String propertyUuid;

  /** The property area name. */
  private String propertyAreaName;
  private String propertyHiddenAreaName;

  /** The property area note. */
  private String propertyAreaNote;

  /** The property slots. */
  private String propertySlots;

  /** The property size. */
  private String propertySize;

  /** The property price. */
  private String propertyPrice;

  /** The is active. */
  private Boolean isActive;
  
  private String maxHunter;
  
  private List<PropertyslotsDTO> slots;
  
  private List<AreaDatesDTO> dates;

  /**
   * Gets the property area id.
   *
   * @return the property area id
   */
  public Integer getPropertyAreaId() {
    return propertyAreaId;
  }

  /**
   * Sets the property area id.
   *
   * @param propertyAreaId
   *          the new property area id
   */
  public void setPropertyAreaId(Integer propertyAreaId) {
    this.propertyAreaId = propertyAreaId;
  }

  /**
   * Gets the property area uuid.
   *
   * @return the property area uuid
   */
  public String getPropertyAreaUuid() {
    return propertyAreaUuid;
  }

  /**
   * Sets the property area uuid.
   *
   * @param propertyAreaUuid
   *          the new property area uuid
   */
  public void setPropertyAreaUuid(String propertyAreaUuid) {
    this.propertyAreaUuid = propertyAreaUuid;
  }

  /**
   * Gets the property uuid.
   *
   * @return the property uuid
   */
  public String getPropertyUuid() {
    return propertyUuid;
  }

  /**
   * Sets the property uuid.
   *
   * @param propertyUuid
   *          the new property uuid
   */
  public void setPropertyUuid(String propertyUuid) {
    this.propertyUuid = propertyUuid;
  }

  /**
   * Gets the property area name.
   *
   * @return the property area name
   */
  public String getPropertyAreaName() {
    return propertyAreaName;
  }

  /**
   * Sets the property area name.
   *
   * @param propertyAreaName
   *          the new property area name
   */
  public void setPropertyAreaName(String propertyAreaName) {
    this.propertyAreaName = propertyAreaName;
  }

  public String getPropertyHiddenAreaName() {
	return propertyHiddenAreaName;
}

public void setPropertyHiddenAreaName(String propertyHiddenAreaName) {
	this.propertyHiddenAreaName = propertyHiddenAreaName;
}

/**
   * Gets the property area note.
   *
   * @return the property area note
   */
  public String getPropertyAreaNote() {
    return propertyAreaNote;
  }

  /**
   * Sets the property area note.
   *
   * @param propertyAreaNote
   *          the new property area note
   */
  public void setPropertyAreaNote(String propertyAreaNote) {
    this.propertyAreaNote = propertyAreaNote;
  }

  /**
   * Gets the property slots.
   *
   * @return the property slots
   */
  public String getPropertySlots() {
    return propertySlots;
  }

  /**
   * Sets the property slots.
   *
   * @param propertySlots
   *          the new property slots
   */
  public void setPropertySlots(String propertySlots) {
    this.propertySlots = propertySlots;
  }

  /**
   * Gets the property size.
   *
   * @return the property size
   */
  public String getPropertySize() {
    return propertySize;
  }

  /**
   * Sets the property size.
   *
   * @param propertySize
   *          the new property size
   */
  public void setPropertySize(String propertySize) {
    this.propertySize = propertySize;
  }

  /**
   * Gets the property price.
   *
   * @return the property price
   */
  public String getPropertyPrice() {
    return propertyPrice;
  }

  /**
   * Sets the property price.
   *
   * @param propertyPrice
   *          the new property price
   */
  public void setPropertyPrice(String propertyPrice) {
    this.propertyPrice = propertyPrice;
  }

  /**
   * Gets the checks if is active.
   *
   * @return the checks if is active
   */
  public Boolean getIsActive() {
    return isActive;
  }

  /**
   * Sets the checks if is active.
   *
   * @param isActive
   *          the new checks if is active
   */
  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

public String getMaxHunter() {
	return maxHunter;
}

public void setMaxHunter(String maxHunter) {
	this.maxHunter = maxHunter;
}

public List<PropertyslotsDTO> getSlots() {
	return slots;
}

public void setSlots(List<PropertyslotsDTO> slots) {
	this.slots = slots;
}

public List<AreaDatesDTO> getDates() {
	return dates;
}

public void setDates(List<AreaDatesDTO> dates) {
	this.dates = dates;
}

}
