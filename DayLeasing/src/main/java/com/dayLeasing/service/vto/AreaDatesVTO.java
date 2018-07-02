package com.dayLeasing.service.vto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.dayLeasing.dao.model.AreaDates;
import com.dayLeasing.service.dto.AreaDatesDTO;
import com.dayLeasing.service.util.DateUtils;
import com.dayLeasing.service.util.GenerateUUID;

public class AreaDatesVTO {
	public static AreaDates convertToDates(AreaDatesDTO areaDatesDto){
		AreaDates areaDates= new AreaDates();
		
		areaDates.setId(areaDatesDto.getId());
		areaDates.setFromDate(DateUtils.generateDateFromString(areaDatesDto.getFromDate()));
		areaDates.setToDate(DateUtils.generateDateFromString(areaDatesDto.getToDate()));
		
		areaDates.setCost(areaDatesDto.getCost());
		areaDates.setPropertyAreaUuid(areaDatesDto.getPropertyAreaUuid());
		String uuid = areaDatesDto.getUuid();
		if(uuid==null)
		{
			areaDates.setUuid(GenerateUUID.generateUUID());	
		}
		else{
			areaDates.setUuid(areaDatesDto.getUuid());
		}
		
		areaDates.setId(areaDatesDto.getId());
		return areaDates;
	}
	public static AreaDatesDTO convertToDatesDTO(AreaDates areaDates){
		AreaDatesDTO areaDatesDTO= new AreaDatesDTO();
//		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		areaDatesDTO.setId(areaDates.getId());
		areaDatesDTO.setFromDate(DateUtils.generateStringFromDate(areaDates.getFromDate()));
		areaDatesDTO.setToDate(DateUtils.generateStringFromDate(areaDates.getToDate()));
		
		areaDatesDTO.setCost(areaDates.getCost());
		areaDatesDTO.setPropertyAreaUuid(areaDates.getPropertyAreaUuid());
		areaDatesDTO.setUuid(areaDates.getUuid());
		return areaDatesDTO;
	}

}
