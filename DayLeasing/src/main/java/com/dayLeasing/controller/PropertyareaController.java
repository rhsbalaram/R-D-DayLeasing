package com.dayLeasing.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

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
import com.dayLeasing.service.dto.AreaDatesDTO;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.dto.PropertyareaDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;
import com.dayLeasing.service.*;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertyareaController.
 *
 * @author Balaram
 */
@RestController
@RequestMapping("/user")
public class PropertyareaController {

	/** The propertyarea service. */
	@Autowired
	PropertyareaService propertyareaService;

	@Autowired
	AreaDatesService areaDatesService;

	/**
	 * Adds the property area.
	 *
	 * @param propertyareaDTO
	 *            the propertyarea DTO
	 * @param propertyId
	 *            the property id
	 * @param binding
	 *            the binding
	 * @param map
	 *            the map
	 * @return the response entity
	 */
	@RequestMapping(value = "property/{propertyId}/area", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, PropertyareaDTO>> addPropertyArea(
			@Valid @RequestBody PropertyareaDTO propertyareaDTO,
			@PathVariable String propertyId, BindingResult binding, ModelMap map) {

		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}

		String areaid = propertyareaService.addPropertyArea(propertyareaDTO,
				propertyId);

		List<AreaDatesDTO> dates = propertyareaDTO.getDates();

		for (AreaDatesDTO date : dates) {
			date.setPropertyAreaUuid(areaid);
			areaDatesService.addAreaDate(date);
		}
		PropertyareaDTO propertyArea = propertyareaService
				.getPropertyArea(areaid);
		HashMap<String, PropertyareaDTO> hashMap = new HashMap<String, PropertyareaDTO>();
		hashMap.put("area", propertyArea);
		return new ResponseEntity<HashMap<String, PropertyareaDTO>>(hashMap,
				HttpStatus.OK);

	}

	/**
	 * Gets the property areas.
	 *
	 * @param propertyId
	 *            the propertyId
	 * @return the property areas
	 */
	@RequestMapping(value = "property/{propertyId}/area", method = RequestMethod.GET)
	// >
	public ResponseEntity<List<PropertyareaDTO>> getPropertyAreas(
			@PathVariable String propertyId) {
		List<PropertyareaDTO> property = propertyareaService
				.getPropertyAreas(propertyId);
		// return "hai";
		return new ResponseEntity<List<PropertyareaDTO>>(property,
				HttpStatus.OK);
	}

	/**
	 * Gets the property area.
	 *
	 * @param areaId
	 *            the areaId
	 * @return the property area
	 */
	@RequestMapping(value = "property/{propertyId}/area/{areaId}", method = RequestMethod.GET)
	public ResponseEntity<PropertyareaDTO> getPropertyArea(
			@PathVariable String areaId) {
		PropertyareaDTO area = propertyareaService.getPropertyArea(areaId);

		return new ResponseEntity<PropertyareaDTO>(area, HttpStatus.OK);

	}

	/**
	 * Update area.
	 *
	 * @param propertyAreaDTO
	 *            the propertyAreaDTO
	 * @param areaId
	 *            the areaId
	 * @param propertyId
	 *            the property id
	 * @return the response entity
	 */
	@RequestMapping(value = "property/{propertyId}/area/{areaId}", method = RequestMethod.PUT)
	public ResponseEntity<HashMap<String, PropertyareaDTO>> updateArea(
			@Valid @RequestBody PropertyareaDTO propertyAreaDTO,
			@PathVariable String propertyId, @PathVariable String areaId) {
		boolean success = propertyareaService.updateProperty(propertyAreaDTO,
				propertyId, areaId);
		HttpStatus status=HttpStatus.OK;
		if(success==false){
			status=HttpStatus.BAD_REQUEST;
		}
		if(success==true){
			
		

		List<AreaDatesDTO> dates = propertyAreaDTO.getDates();
		areaDatesService.AreaDateDelete(areaId);
		for (AreaDatesDTO date : dates) {
			date.setPropertyAreaUuid(areaId);
			areaDatesService.addAreaDate(date);
		}
		}
		PropertyareaDTO propertyArea = propertyareaService
				.getPropertyArea(areaId);
		HashMap<String, PropertyareaDTO> hashMap = new HashMap<String, PropertyareaDTO>();
		hashMap.put("area", propertyArea);
		return new ResponseEntity<HashMap<String, PropertyareaDTO>>(hashMap,
				status);
	}

	/**
	 * Property delete.
	 *
	 * @param areaId
	 *            the areaId
	 * @param propertyId
	 *            the property id
	 * @return the response entity
	 */
	@RequestMapping(value = "property/{propertyId}/area/{areaId}", method = RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Boolean>> AreaDelete(
			@PathVariable String propertyId, @PathVariable String areaId) {
		boolean success = propertyareaService.AreaDelete(propertyId, areaId);
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("update", success);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "area/reservation/{areaId}", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Boolean>> getReservationStatusOfArea( @PathVariable String areaId){
		int reservationsCount = propertyareaService.getReservationsCount(areaId);
		boolean reservationStatus=true;
		if(reservationsCount<=0){
			reservationStatus=false;
		}
		
				
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("status", reservationStatus);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

}
