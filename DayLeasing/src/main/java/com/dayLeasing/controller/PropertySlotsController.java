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
import com.dayLeasing.service.PropertySlotsService;
import com.dayLeasing.service.PropertydetailsService;
import com.dayLeasing.service.dto.PropertyareaDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertySlotsController.
 *
 * @author Balaram
 */

@RestController
public class PropertySlotsController {

	/** The property slots service. */
	@Autowired
	PropertySlotsService propertySlotsService;

	/**
	 * Adds the slot.
	 *
	 * @param propertyslotsDTO
	 *            the propertyslots DTO
	 * @param areaID
	 *            the area ID
	 * @param binding
	 *            the binding
	 * @param map
	 *            the map
	 * @return the response entity
	 */
	@RequestMapping(value = "user/area/{areaID}/slot", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, PropertyslotsDTO>> addSlot(
			@Valid @RequestBody PropertyslotsDTO propertyslotsDTO,
			@PathVariable String areaID, BindingResult binding, ModelMap map) {

		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}

		PropertyslotsDTO slot = propertySlotsService.addSlot(propertyslotsDTO,
				areaID);

		HashMap<String, PropertyslotsDTO> hashMap = new HashMap<String, PropertyslotsDTO>();
		hashMap.put("slot", slot);
		return new ResponseEntity<HashMap<String, PropertyslotsDTO>>(hashMap,
				HttpStatus.OK);

	}

	/***
	 * 
	 * @return
	 */
	@RequestMapping(value = "user/area/{areaID}/slots", method = RequestMethod.POST)
	public ResponseEntity<List<PropertyslotsDTO>> addMultipleSlots(
			@Valid @RequestBody List<PropertyslotsDTO> propertyslotDTOS,
			@PathVariable String areaID, BindingResult binding, ModelMap map) {
		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}
		List<PropertyslotsDTO> slots = propertySlotsService.addMultipleSlots(propertyslotDTOS, areaID);
		
		return new ResponseEntity<List<PropertyslotsDTO>>(slots, HttpStatus.OK);

	}

	/**
	 * Gets the slots.
	 *
	 * @param areaID
	 *            the area ID
	 * @return the slots
	 */
	@RequestMapping(value = "user/area/{areaID}/slot", method = RequestMethod.GET)
	public ResponseEntity<List<PropertyslotsDTO>> getSlots(
			@PathVariable String areaID) {
		List<PropertyslotsDTO> slots = propertySlotsService.getSlots(areaID);
		return new ResponseEntity<List<PropertyslotsDTO>>(slots, HttpStatus.OK);
	}

	/**
	 * Gets the slot.
	 *
	 * @param areaID
	 *            the area ID
	 * @param slotId
	 *            the slot id
	 * @return the slot
	 */
	@RequestMapping(value = "user/area/{areaID}/slot/{slotId}", method = RequestMethod.GET)
	public ResponseEntity<PropertyslotsDTO> getSlot(
			@PathVariable String areaID, @PathVariable String slotId) {
		PropertyslotsDTO slot = propertySlotsService.getSlot(areaID, slotId);
		return new ResponseEntity<PropertyslotsDTO>(slot, HttpStatus.OK);
	}

	/**
	 * Gets the slot for hunter.
	 *
	 * @param areaID
	 *            the area ID
	 * @param slotId
	 *            the slot id
	 * @return the slot for hunter
	 */
	@RequestMapping(value = "hunter/area/{areaID}/slot/{slotId}", method = RequestMethod.GET)
	public ResponseEntity<PropertyslotsDTO> getSlotForHunter(
			@PathVariable String areaID, @PathVariable String slotId) {
		PropertyslotsDTO slot = propertySlotsService.getSlot(areaID, slotId);
		return new ResponseEntity<PropertyslotsDTO>(slot, HttpStatus.OK);
	}

	/**
	 * Update slot.
	 *
	 * @param propertyslotsDTO
	 *            the propertyslots DTO
	 * @param areaID
	 *            the area ID
	 * @param slotId
	 *            the slot id
	 * @return the response entity
	 */
	@RequestMapping(value = "user/area/{areaID}/slot/{slotId}", method = RequestMethod.PUT)
	public ResponseEntity<HashMap<String, PropertyslotsDTO>> updateSlot(
			@Valid @RequestBody PropertyslotsDTO propertyslotsDTO,
			@PathVariable String areaID, @PathVariable String slotId) {
		boolean success = propertySlotsService.updateSlots(propertyslotsDTO,
				areaID, slotId);
		HttpStatus status = HttpStatus.OK;
		if (success == false) {
			status = HttpStatus.BAD_REQUEST;
		}

		HashMap<String, PropertyslotsDTO> hashMap = new HashMap<String, PropertyslotsDTO>();
		hashMap.put("slot", propertyslotsDTO);

		return new ResponseEntity<HashMap<String, PropertyslotsDTO>>(hashMap,
				status);
	}

	/**
	 * Delete slot.
	 *
	 * @param areaID
	 *            the area ID
	 * @param slotId
	 *            the slot id
	 * @return the response entity
	 */
	@RequestMapping(value = "user/{areaID}/slot/{slotId}", method = RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Boolean>> deleteSlot(
			@PathVariable String areaID, @PathVariable String slotId) {
		boolean success = propertySlotsService.deleteSlots(areaID, slotId);
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("update", success);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

	@RequestMapping(value = "user/slot/reservation/{slotId}", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Boolean>> getReservationStatusOfSlot(
			@PathVariable String slotId) {
		boolean success = true;
		int reservationsCount = propertySlotsService
				.getReservationsCount(slotId);
		if (reservationsCount <= 0) {
			success = false;
		}
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("status", success);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

}
