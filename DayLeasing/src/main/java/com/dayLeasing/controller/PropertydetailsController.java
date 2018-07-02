package com.dayLeasing.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.hadoop.mapred.gethistory_jsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayLeasing.configuration.InValidRequestException;
import com.dayLeasing.service.AreaDatesService;
import com.dayLeasing.service.PropertySlotsService;
import com.dayLeasing.service.PropertyareaService;
import com.dayLeasing.service.PropertydetailsService;
import com.dayLeasing.service.dto.AreaDatesDTO;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.dto.PropertyareaDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertydetailsController.
 *
 * @author Balaram
 */
@RestController
public class PropertydetailsController {

	/** The Propertydetails service. */
	@Autowired
	PropertydetailsService PropertydetailsService;

	@Autowired
	PropertyareaService propertyareaService;

	@Autowired
	PropertySlotsService propertySlotsService;

	@Autowired
	AreaDatesService areaDatesService;

	/**
	 * Adds the property.
	 *
	 * @param propertydetailsDTO
	 *            the propertydetails DTO
	 * @param userId
	 *            the user id
	 * @param binding
	 *            the binding
	 * @param map
	 *            the map
	 * @return the response entity
	 */
	@RequestMapping(value = "user/{userId}/property", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, PropertydetailsDTO>> addProperty(
			@Valid @RequestBody PropertydetailsDTO propertydetailsDTO,
			@PathVariable String userId, BindingResult binding, ModelMap map) {

		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}

		PropertydetailsDTO property = PropertydetailsService.addProperty(
				propertydetailsDTO, userId);

		HashMap<String, PropertydetailsDTO> hashMap = new HashMap<String, PropertydetailsDTO>();
		hashMap.put("property", property);
		return new ResponseEntity<HashMap<String, PropertydetailsDTO>>(hashMap,
				HttpStatus.OK);

	}

	/**
	 * Gets the properties.
	 *
	 * @param userId
	 *            the user id
	 * @return the properties
	 */
	@RequestMapping(value = "user/{userId}/property", method = RequestMethod.GET)
	public ResponseEntity<List<PropertydetailsDTO>> getProperties(
			@PathVariable String userId) {
		List<PropertydetailsDTO> properties = PropertydetailsService
				.getPropertiesOfUser(userId);
		return new ResponseEntity<List<PropertydetailsDTO>>(properties,
				HttpStatus.OK);
	}

	/**
	 * Gets the properties.
	 *
	 * @param filter
	 *            the filter
	 * @return the properties
	 */
	@RequestMapping(value = "hunter/{filter}/property", method = RequestMethod.GET)
	public ResponseEntity<List<PropertydetailsDTO>> getPropertiesForHunter(
			@PathVariable String filter) {
		List<PropertydetailsDTO> properties = PropertydetailsService
				.getProperties(filter);
		return new ResponseEntity<List<PropertydetailsDTO>>(properties,
				HttpStatus.OK);
	}

	/**
	 * Gets the property. along with areas, slots and dates
	 *
	 * @param userId
	 *            the user id
	 * @param propertyId
	 *            the property id
	 * @return the property
	 */
	@RequestMapping(value = "user/{userId}/property/{propertyId}", method = RequestMethod.GET)
	public ResponseEntity<PropertydetailsDTO> getProperty(
			@PathVariable String userId, @PathVariable String propertyId) {
		PropertydetailsDTO properties = PropertydetailsService.getProperty(
				userId, propertyId);
		List<PropertyareaDTO> propertyAreas = propertyareaService
				.getPropertyAreas(propertyId);
		for (PropertyareaDTO area : propertyAreas) {
			List<PropertyslotsDTO> slots = propertySlotsService.getSlots(area
					.getPropertyAreaUuid());
			area.setSlots(slots);
			List<AreaDatesDTO> areaDate = areaDatesService.getAreaDate(area
					.getPropertyAreaUuid());
			area.setDates(areaDate);
		}
		properties.setAreas(propertyAreas);
		return new ResponseEntity<PropertydetailsDTO>(properties, HttpStatus.OK);
	}

	/**
	 * Gets the property.
	 *
	 * @param propertyId
	 *            the propertyId
		 * @return the property
	 */
	@RequestMapping(value = "hunter/property/{propertyId}", method = RequestMethod.GET)
	public ResponseEntity<PropertydetailsDTO> getPropertyForHunter(
			@PathVariable String propertyId) {
		PropertydetailsDTO properties = PropertydetailsService.getProperty(
				null, propertyId);
		List<PropertyareaDTO> propertyAreas = propertyareaService
				.getPropertyAreas(propertyId);
		for (PropertyareaDTO area : propertyAreas) {
			List<PropertyslotsDTO> slots = propertySlotsService.getSlots(area
					.getPropertyAreaUuid());
			area.setSlots(slots);
			List<AreaDatesDTO> areaDate = areaDatesService.getAreaDate(area
					.getPropertyAreaUuid());
			area.setDates(areaDate);
		}
		properties.setAreas(propertyAreas);
		return new ResponseEntity<PropertydetailsDTO>(properties, HttpStatus.OK);
	}

	/**
	 * update Property.
	 *
	 * @param propertydetailsDTO
	 *            the propertydetails DTO
	 * @param userId
	 *            the user id
	 * @param propertyId
	 *            the property id
	 * @return the response entity
	 */
	@RequestMapping(value = "user/{userId}/property/{propertyId}", method = RequestMethod.PUT)
	public ResponseEntity<HashMap<String, PropertydetailsDTO>> updateProperty(
			@Valid @RequestBody PropertydetailsDTO propertydetailsDTO,
			@PathVariable String userId, @PathVariable String propertyId) {
		boolean success = PropertydetailsService.updateProperty(
				propertydetailsDTO, userId, propertyId);
		HashMap<String, PropertydetailsDTO> hashMap = new HashMap<String, PropertydetailsDTO>();
		hashMap.put("property", propertydetailsDTO);
		return new ResponseEntity<HashMap<String, PropertydetailsDTO>>(hashMap,
				HttpStatus.OK);
	}

	// /////////
	/**
	 * Update propertyBoundaries.
	 *
	 * @param propertydetailsDTO
	 *            the propertydetails DTO
	 * @param userId
	 *            the user id
	 * @param propertyId
	 *            the property id
	 * @return the response entity
	 */
	@RequestMapping(value = "user/{userId}/propertyBoundaries/{propertyId}", method = RequestMethod.PUT)
	public ResponseEntity<HashMap<String, String>> updatePropertyBoundaries(
			@Valid @RequestBody PropertydetailsDTO propertydetailsDTO,
			@PathVariable String userId, @PathVariable String propertyId) {
		boolean success = PropertydetailsService.updateProperty(
				propertydetailsDTO, userId, propertyId);

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("property", propertyId);
		return new ResponseEntity<HashMap<String, String>>(hashMap,
				HttpStatus.OK);
	}

	/**
	 * Delete user.
	 *
	 * @param userId
	 *            the user id
	 * @param propertyId
	 *            the property id
	 * @return the response entity
	 */
	@RequestMapping(value = "user/{userId}/property/{propertyId}", method = RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Boolean>> deleteUser(
			@PathVariable String userId, @PathVariable String propertyId) {
		boolean success = PropertydetailsService.deleteProperty(userId,
				propertyId);
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("update", success);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "user/property/reservation/{propertyId}", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Boolean>> getReservationStatusOfProperty(
			 @PathVariable String propertyId) {
		boolean success =true;
				int reservationsCount = PropertydetailsService.getReservationsCount(propertyId);
				if(reservationsCount<=0){
					success=false;
				}
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("status", success);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

}
