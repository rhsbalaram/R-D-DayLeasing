package com.dayLeasing.service.vto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.util.GenerateUUID;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertydetailsVTO.
 *
 * @author Balaram
 */
public class PropertydetailsVTO {

  /**
   * Convert to propertydetails.
   *
   * @param propertydetailsDTO
   *          the propertydetails DTO
   * @param userId
   *          the user id
   * @return the propertydetails
   */
  public static Propertydetails convertToPropertydetails(PropertydetailsDTO propertydetailsDTO,
      String userId) {
    Propertydetails propertydetails = new Propertydetails();
    propertydetails.setUserGuid(userId);
    propertydetails.setPropertyBorder(propertydetailsDTO.getPropertyBorder());
    propertydetails.setPropertyDescription(propertydetailsDTO.getPropertyDescription());
    List<Map> propertyFeatures = propertydetailsDTO.getPropertyFeatures();
    String featuredString="";
    for(Map feature : propertyFeatures){
    	featuredString=featuredString+feature.get("data")+"~";
    }
    propertydetails.setPropertyFeatures(featuredString);
    propertydetails.setPropertyName(propertydetailsDTO.getPropertyName());
    propertydetails.setPropertyType(propertydetailsDTO.getPropertyType());
    propertydetails.setPropertyUuid(GenerateUUID.generateUUID());
    // propertydetails.setPropertyId(propertydetailsDTO.getPropertyId());
    propertydetails.setPropertyMaxHunter(propertydetailsDTO.getPropertyMaxHunter());
    propertydetails.setOutLine(propertydetailsDTO.getOutLine());
    propertydetails.setPropertySlots(propertydetailsDTO.getPropertySlots());
    propertydetails.setAddress(propertydetailsDTO.getAddress());
    propertydetails.setCountry(propertydetailsDTO.getCountry());
    propertydetails.setRegion(propertydetailsDTO.getRegion());
    propertydetails.setSubRegion(propertydetailsDTO.getSubRegion());
    return propertydetails;

  }

  /**
   * Convert to propertydetails DTO.
   *
   * @param propertydetails
   *          the propertydetails
   * @return the propertydetails DTO
   */
  public static PropertydetailsDTO convertToPropertydetailsDTO(Propertydetails propertydetails) {
    PropertydetailsDTO propertydetailsDTO = new PropertydetailsDTO();
    propertydetailsDTO.setPropertyBorder(propertydetails.getPropertyBorder());
    propertydetailsDTO.setPropertyDescription(propertydetails.getPropertyDescription());
    String propertyFeatures = propertydetails.getPropertyFeatures();
    String[] split = propertyFeatures.split("~");
    List<Map> list = new ArrayList<Map>();
    for(String str: split)
    {
    	if(str!=null&&str!=""){
    		Map map = new HashMap();
    		map.put("data", str);
    		list.add(map);
    	}
    }
    propertydetailsDTO.setPropertyFeatures(list);
    propertydetailsDTO.setPropertyName(propertydetails.getPropertyName());
    propertydetailsDTO.setPropertyType(propertydetails.getPropertyType());
    propertydetailsDTO.setPropertyUuid(propertydetails.getPropertyUuid());
    // propertydetailsDTO.setPropertyId(propertydetails.getPropertyId());
    propertydetailsDTO.setPropertyMaxHunter(propertydetails.getPropertyMaxHunter());
    propertydetailsDTO.setOutLine(propertydetails.getOutLine());
    propertydetailsDTO.setPropertySlots(propertydetails.getPropertySlots());
    propertydetailsDTO.setAddress(propertydetails.getAddress());
    propertydetailsDTO.setCountry(propertydetails.getCountry());
    propertydetailsDTO.setRegion(propertydetails.getRegion());
    propertydetailsDTO.setSubRegion(propertydetails.getSubRegion());
    return propertydetailsDTO;

  }

  /**
   * Convert to propertydetails to update.
   *
   * @param propertydetailsDTO
   *          the propertydetails DTO
   * @param propertydetails
   *          the propertydetails
   * @return the propertydetails
   */
  public static Propertydetails convertToPropertydetailsToUpdate(
      PropertydetailsDTO propertydetailsDTO, Propertydetails propertydetails) {

    propertydetails.setPropertyBorder(propertydetailsDTO.getPropertyBorder());
    propertydetails.setPropertyDescription(propertydetailsDTO.getPropertyDescription());
    List<Map> propertyFeatures = propertydetailsDTO.getPropertyFeatures();
    String featuredString="";
    for(Map feature : propertyFeatures){
    	featuredString=featuredString+feature.get("data")+"~";
    }
    propertydetails.setPropertyFeatures(featuredString);
    propertydetails.setPropertyName(propertydetailsDTO.getPropertyName());
    propertydetails.setPropertyType(propertydetailsDTO.getPropertyType());
    // propertydetails.setPropertyUuid(propertydetailsDTO.getPropertyUuid());
    // propertydetails.setPropertyId(propertydetailsDTO.getPropertyId());
    propertydetails.setPropertyMaxHunter(propertydetailsDTO.getPropertyMaxHunter());
    propertydetails.setOutLine(propertydetailsDTO.getOutLine());
    propertydetails.setPropertySlots(propertydetailsDTO.getPropertySlots());
    propertydetails.setAddress(propertydetailsDTO.getAddress());
    propertydetails.setCountry(propertydetailsDTO.getCountry());
    propertydetails.setRegion(propertydetailsDTO.getRegion());
    propertydetails.setSubRegion(propertydetailsDTO.getSubRegion());
    return propertydetails;

  }

}
