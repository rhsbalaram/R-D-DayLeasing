package com.dayLeasing.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.dayLeasing.dao.PropertydetailsDao;
import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.vto.PropertydetailsVTO;
// TODO: Auto-generated Javadoc

/**
 * The Class PropertydetailsServiceImpl.
 *
 * @author Balaram
 */
@Service
public class PropertydetailsServiceImpl implements PropertydetailsService {

  /** The Propertydetails dao. */
  @Autowired
  PropertydetailsDao PropertydetailsDao;

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertydetailsService#addProperty(com.dayLeasing.service.dto.PropertydetailsDTO, java.lang.String)
   */
  @Override
  public PropertydetailsDTO addProperty(PropertydetailsDTO propertydetailsDTO, String userId) {
    Propertydetails propertydetails = PropertydetailsVTO.convertToPropertydetails(
        propertydetailsDTO, userId);
     Propertydetails property = PropertydetailsDao.addProperty(propertydetails);
     PropertydetailsDTO convertedToPropertydetailsDTO = PropertydetailsVTO
             .convertToPropertydetailsDTO(property);
     return convertedToPropertydetailsDTO;

  }

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertydetailsService#getProperties(java.lang.String)
   */
  @Override
  public List<PropertydetailsDTO> getProperties(String propertyUuid) {
    List<Propertydetails> properties = PropertydetailsDao.getProperties(propertyUuid);
    ArrayList<PropertydetailsDTO> propertiesdto = new ArrayList<PropertydetailsDTO>();
    for (Propertydetails property : properties) {
      PropertydetailsDTO convertedToPropertydetailsDTO = PropertydetailsVTO
          .convertToPropertydetailsDTO(property);
      propertiesdto.add(convertedToPropertydetailsDTO);
    }

    return propertiesdto;
  }
  
  @Override
  public List<PropertydetailsDTO> getPropertiesOfUser(String userId) {
    List<Propertydetails> properties = PropertydetailsDao.getPropertiesOfUser(userId);
    ArrayList<PropertydetailsDTO> propertiesdto = new ArrayList<PropertydetailsDTO>();
    for (Propertydetails property : properties) {
      PropertydetailsDTO convertedToPropertydetailsDTO = PropertydetailsVTO
          .convertToPropertydetailsDTO(property);
      propertiesdto.add(convertedToPropertydetailsDTO);
    }

    return propertiesdto;
  }

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertydetailsService#getProperty(java.lang.String, java.lang.String)
   */
  @Override
  public PropertydetailsDTO getProperty(String userId, String propertyId) {
    Propertydetails property = PropertydetailsDao.getProperty(userId, propertyId);

    PropertydetailsDTO convertedToPropertydetailsDTO = PropertydetailsVTO
        .convertToPropertydetailsDTO(property);

    return convertedToPropertydetailsDTO;
  }

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertydetailsService#updateUser(com.dayLeasing.service.dto.PropertydetailsDTO, java.lang.String, java.lang.String)
   */
  @Override
  public boolean updateProperty(PropertydetailsDTO propertydetailsDTO, String userId, String propertyId) {
    return PropertydetailsDao.updateProperty(propertydetailsDTO, userId, propertyId);
  }

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertydetailsService#deleteProperty(java.lang.String, java.lang.String)
   */
  @Override
  public boolean deleteProperty(String userId, String propertyId) {
    return PropertydetailsDao.propertyDelete(userId, propertyId);
  }
  
  @Override
  public int getReservationsCount(String propertyId){
	  return PropertydetailsDao.getReservationsCount(propertyId);
  }

}
