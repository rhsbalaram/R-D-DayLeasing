package com.dayLeasing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayLeasing.dao.PropertyareaDao;
import com.dayLeasing.dao.model.Propertyarea;
import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.service.dto.AreaDatesDTO;
import com.dayLeasing.service.dto.PropertyareaDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;
import com.dayLeasing.service.vto.PropertyareaVTO;
import com.dayLeasing.service.vto.PropertydetailsVTO;
import com.dayLeasing.service.vto.PropertyslotsVTO;
// TODO: Auto-generated Javadoc

/**
 * The Class PropertyareaServiceImpl.
 *
 * @author Balaram
 */
@Service
public class PropertyareaServiceImpl implements PropertyareaService {

  /** The propertyarea dao. */
  @Autowired
  PropertyareaDao propertyareaDao;
  
  @Autowired
  AreaDatesService areaDatesService;

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertyareaService#addPropertyArea(com.dayLeasing.service.dto.PropertyareaDTO, java.lang.String)
   */
  @Override
  public String addPropertyArea(PropertyareaDTO propertyareaDTO, String areaID) {
    // TODO Auto-generated method stub
    Propertyarea convertedToPropertyarea = PropertyareaVTO.convertToPropertyArea(propertyareaDTO,
        areaID);
    return propertyareaDao.addPropertyArea(convertedToPropertyarea);

  }

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertyareaService#getPropertyAreas(java.lang.String)
   */
  @Override
  public List<PropertyareaDTO> getPropertyAreas(String userId) {
    // TODO Auto-generated method stub

    List<Propertyarea> areas = propertyareaDao.getPropertyAreas(userId);
    List<PropertyareaDTO> areasdto = new ArrayList<PropertyareaDTO>();
    for (Propertyarea area : areas) {
      PropertyareaDTO convertedToPropertyareasDTO = PropertyareaVTO.convertToPropertyAreasDTO(area);
      areasdto.add(convertedToPropertyareasDTO);
    }

    return areasdto;

  }

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertyareaService#updateProperty(com.dayLeasing.service.dto.PropertyareaDTO, java.lang.String, java.lang.String)
   */
  @Override
  public boolean updateProperty(PropertyareaDTO propertyareaDTO, String userId, String propertyId) {
    // TODO Auto-generated method stub
    return propertyareaDao.updateProperty(propertyareaDTO, userId, propertyId);

  }

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertyareaService#propertyDelete(java.lang.String, java.lang.String)
   */
  @Override
  public boolean AreaDelete(String propertyId, String areaId) {
    // TODO Auto-generated method stub
    return propertyareaDao.AreaDelete(propertyId, areaId);
  }
  
  @Override
  public int getReservationsCount(String areaId){
	  return propertyareaDao.getReservationsCount(areaId);
  }

  /* (non-Javadoc)
   * @see com.dayLeasing.service.PropertyareaService#getPropertyArea(java.lang.String, java.lang.String)
   */
  @Override
  public PropertyareaDTO getPropertyArea(String areauuid) {
    // TODO Auto-generated method stub
    Propertyarea area = propertyareaDao.getPropertyArea(areauuid);
    PropertyareaDTO areadto = PropertyareaVTO.convertToPropertyAreasDTO(area);
    List<AreaDatesDTO> areaDate = areaDatesService.getAreaDate(areauuid);
    areadto.setDates(areaDate);

    return areadto;

  }

}
