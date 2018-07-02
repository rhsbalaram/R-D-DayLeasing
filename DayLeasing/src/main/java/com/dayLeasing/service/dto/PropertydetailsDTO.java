package com.dayLeasing.service.dto;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertydetailsDTO.
 *
 * @author Balaram
 */
public class PropertydetailsDTO {

  /** The property id. */
  private Integer propertyId;

  /** The property uuid. */
  private String propertyUuid;

  /** The property name. */
  private String propertyName;

  /** The property description. */
  private String propertyDescription;

  /** The property type. */
  private String propertyType;

  /** The property features. */
  private List<Map> propertyFeatures;

  /** The property border. */
  private String propertyBorder;

  /** The property max hunter. */
  private Integer propertyMaxHunter;

  /** The is active. */
  private Boolean isActive;

  /** The user guid. */
  private String userGuid;
  
  private String outLine;
  
  private String propertySlots;
  
  private List<PropertyareaDTO> areas;
  
  private String region;
	private String subRegion;
	private String country;
	private String address;

  /**
   * Gets the property id.
   *
   * @return the property id
   */
  public Integer getPropertyId() {
    return propertyId;
  }

  /**
   * Sets the property id.
   *
   * @param propertyId
   *          the new property id
   */
  public void setPropertyId(Integer propertyId) {
    this.propertyId = propertyId;
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
   * Gets the property name.
   *
   * @return the property name
   */
  public String getPropertyName() {
    return propertyName;
  }

  /**
   * Sets the property name.
   *
   * @param propertyName
   *          the new property name
   */
  public void setPropertyName(String propertyName) {
    this.propertyName = propertyName;
  }

  /**
   * Gets the property description.
   *
   * @return the property description
   */
  public String getPropertyDescription() {
    return propertyDescription;
  }

  /**
   * Sets the property description.
   *
   * @param propertyDescription
   *          the new property description
   */
  public void setPropertyDescription(String propertyDescription) {
    this.propertyDescription = propertyDescription;
  }

  /**
   * Gets the property type.
   *
   * @return the property type
   */
  public String getPropertyType() {
    return propertyType;
  }

  /**
   * Sets the property type.
   *
   * @param propertyType
   *          the new property type
   */
  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }

  /**
   * Gets the property features.
   *
   * @return the property features
   */
  public List<Map> getPropertyFeatures() {
    return propertyFeatures;
  }

  /**
   * Sets the property features.
   *
   * @param propertyFeatures
   *          the new property features
   */
  public void setPropertyFeatures(List<Map> propertyFeatures) {
    this.propertyFeatures = propertyFeatures;
  }

  /**
   * Gets the property border.
   *
   * @return the property border
   */
  public String getPropertyBorder() {
    return propertyBorder;
  }

  /**
   * Sets the property border.
   *
   * @param propertyBorder
   *          the new property border
   */
  public void setPropertyBorder(String propertyBorder) {
    this.propertyBorder = propertyBorder;
  }

  /**
   * Gets the property max hunter.
   *
   * @return the property max hunter
   */
  public Integer getPropertyMaxHunter() {
    return propertyMaxHunter;
  }

  /**
   * Sets the property max hunter.
   *
   * @param propertyMaxHunter
   *          the new property max hunter
   */
  public void setPropertyMaxHunter(Integer propertyMaxHunter) {
    this.propertyMaxHunter = propertyMaxHunter;
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

  /**
   * Gets the user guid.
   *
   * @return the user guid
   */
  public String getUserGuid() {
    return userGuid;
  }

  /**
   * Sets the user guid.
   *
   * @param userGuid
   *          the new user guid
   */
  public void setUserGuid(String userGuid) {
    this.userGuid = userGuid;
  }

public String getOutLine() {
	return outLine;
}

public void setOutLine(String outLine) {
	this.outLine = outLine;
}

public String getPropertySlots() {
	return propertySlots;
}

public void setPropertySlots(String propertySlots) {
	this.propertySlots = propertySlots;
}

public List<PropertyareaDTO> getAreas() {
	return areas;
}

public void setAreas(List<PropertyareaDTO> areas) {
	this.areas = areas;
}

public String getRegion() {
	return region;
}

public void setRegion(String region) {
	this.region = region;
}

public String getSubRegion() {
	return subRegion;
}

public void setSubRegion(String subRegion) {
	this.subRegion = subRegion;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

}
