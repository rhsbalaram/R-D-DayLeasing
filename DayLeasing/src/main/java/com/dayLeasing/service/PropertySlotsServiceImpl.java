package com.dayLeasing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayLeasing.dao.PropertySlotsDao;
import com.dayLeasing.dao.PropertydetailsDao;
import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;
import com.dayLeasing.service.vto.PropertydetailsVTO;
import com.dayLeasing.service.vto.PropertyslotsVTO;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertySlotsServiceImpl.
 *
 * @author Balaram
 */
@Service
public class PropertySlotsServiceImpl implements PropertySlotsService {

	/** The property slots dao. */
	@Autowired
	PropertySlotsDao propertySlotsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertySlotsService#addSlot(com.dayLeasing.service
	 * .dto.PropertyslotsDTO, java.lang.String)
	 */
	@Override
	public PropertyslotsDTO addSlot(PropertyslotsDTO propertyslotsDTO,
			String areaID) {
		Propertyslots convertedToPropertyslots = PropertyslotsVTO
				.convertToPropertyslots(propertyslotsDTO, areaID);
		Propertyslots slot = propertySlotsDao
				.addSlots(convertedToPropertyslots);
		PropertyslotsDTO convertedToPropertyslotsDTO = PropertyslotsVTO
				.convertToPropertyslotsDTO(slot);
		return convertedToPropertyslotsDTO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertySlotsService#getSlots(java.lang.String)
	 */
	@Override
	public List<PropertyslotsDTO> getSlots(String areaID) {
		List<Propertyslots> slots = propertySlotsDao.getSlots(areaID);
		ArrayList<PropertyslotsDTO> slotsdto = new ArrayList<PropertyslotsDTO>();
		for (Propertyslots slot : slots) {
			PropertyslotsDTO convertedToPropertyslotsDTO = PropertyslotsVTO
					.convertToPropertyslotsDTO(slot);
			slotsdto.add(convertedToPropertyslotsDTO);
		}

		return slotsdto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertySlotsService#getSlot(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public PropertyslotsDTO getSlot(String areaID, String slotId) {
		Propertyslots propertyslots = propertySlotsDao.getSlot(areaID, slotId);
		PropertyslotsDTO convertedToPropertyslotsDTO = PropertyslotsVTO
				.convertToPropertyslotsDTO(propertyslots);

		return convertedToPropertyslotsDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertySlotsService#updateSlots(com.dayLeasing
	 * .service.dto.PropertyslotsDTO, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateSlots(PropertyslotsDTO propertyslotsDTO,
			String areaID, String slotId) {
		return propertySlotsDao.updateSlot(propertyslotsDTO, areaID, slotId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.PropertySlotsService#deleteSlots(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean deleteSlots(String areaID, String slotId) {
		return propertySlotsDao.deleteSlots(areaID, slotId);
	}

	@Override
	public int getReservationsCount(String slotId) {
		return propertySlotsDao.getReservationsCount(slotId);
	}

	@Override
	public List<PropertyslotsDTO> addMultipleSlots(
			List<PropertyslotsDTO> propertyslotsDTOS, String areaID) {
		List<Propertyslots> propertySlots = new ArrayList<Propertyslots>();
		for (PropertyslotsDTO dto : propertyslotsDTOS) {
			Propertyslots convertedToPropertyslots = PropertyslotsVTO
					.convertToPropertyslots(dto, areaID);
			propertySlots.add(convertedToPropertyslots);
		}
		propertyslotsDTOS.clear();
		List<Propertyslots> slots = propertySlotsDao
				.addMultipleSlots(propertySlots);
		for (Propertyslots slot : slots) {
			PropertyslotsDTO convertedToPropertyslotsDTO = PropertyslotsVTO
					.convertToPropertyslotsDTO(slot);
			propertyslotsDTOS.add(convertedToPropertyslotsDTO);
		}

		return propertyslotsDTOS;
	}

}
