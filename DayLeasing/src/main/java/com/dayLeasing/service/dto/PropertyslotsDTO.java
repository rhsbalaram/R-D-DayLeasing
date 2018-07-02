package com.dayLeasing.service.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertyslotsDTO.
 *
 * @author Balaram
 */
public class PropertyslotsDTO implements Serializable {

  /** The property slots id. */
  private Integer propertySlotsId;

  /** The property slots uuid. */
  private String propertySlotsUuid;

  /** The property uuid. */
  private String propertyUuid;

  /** The property area uuid. */
  private String propertyAreaUuid;

  /** The slot id. */
  private Integer slotId;

  /** The slot name. */
  private String slotName;

  /** The slot from. */
  private Date slotFrom;

  /** The slot to. */
  private Date slotTo;

  /** The is active. */
  private Boolean isActive;
  private String outLine;
  private String slotSize;
	private String slotPrice;
	private String maxHunter;

  /**
   * Gets the property slots id.
   *
   * @return the property slots id
   */
  public Integer getPropertySlotsId() {
    return propertySlotsId;
  }

  /**
   * Sets the property slots id.
   *
   * @param propertySlotsId
   *          the new property slots id
   */
  public void setPropertySlotsId(Integer propertySlotsId) {
    this.propertySlotsId = propertySlotsId;
  }

  /**
   * Gets the property slots uuid.
   *
   * @return the property slots uuid
   */
  public String getPropertySlotsUuid() {
    return propertySlotsUuid;
  }

  /**
   * Sets the property slots uuid.
   *
   * @param propertySlotsUuid
   *          the new property slots uuid
   */
  public void setPropertySlotsUuid(String propertySlotsUuid) {
    this.propertySlotsUuid = propertySlotsUuid;
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
   * Gets the slot id.
   *
   * @return the slot id
   */
  public Integer getSlotId() {
    return slotId;
  }

  /**
   * Sets the slot id.
   *
   * @param slotId
   *          the new slot id
   */
  public void setSlotId(Integer slotId) {
    this.slotId = slotId;
  }

  /**
   * Gets the slot name.
   *
   * @return the slot name
   */
  public String getSlotName() {
    return slotName;
  }

  /**
   * Sets the slot name.
   *
   * @param slotName
   *          the new slot name
   */
  public void setSlotName(String slotName) {
    this.slotName = slotName;
  }

  /**
   * Gets the slot from.
   *
   * @return the slot from
   */
  public Date getSlotFrom() {
    return slotFrom;
  }

  /**
   * Sets the slot from.
   *
   * @param slotFrom
   *          the new slot from
   */
  public void setSlotFrom(Date slotFrom) {
    this.slotFrom = slotFrom;
  }

  /**
   * Gets the slot to.
   *
   * @return the slot to
   */
  public Date getSlotTo() {
    return slotTo;
  }

  /**
   * Sets the slot to.
   *
   * @param slotTo
   *          the new slot to
   */
  public void setSlotTo(Date slotTo) {
    this.slotTo = slotTo;
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

public String getSlotSize() {
	return slotSize;
}

public void setSlotSize(String slotSize) {
	this.slotSize = slotSize;
}

public String getSlotPrice() {
	return slotPrice;
}

public void setSlotPrice(String slotPrice) {
	this.slotPrice = slotPrice;
}

public String getMaxHunter() {
	return maxHunter;
}

public void setMaxHunter(String maxHunter) {
	this.maxHunter = maxHunter;
}

public String getOutLine() {
	return outLine;
}

public void setOutLine(String outLine) {
	this.outLine = outLine;
}

}
