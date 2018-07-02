package com.dayLeasing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayLeasing.dao.AreaDatesDao;
import com.dayLeasing.dao.model.AreaDates;
import com.dayLeasing.service.dto.AreaDatesDTO;
import com.dayLeasing.service.vto.AreaDatesVTO;

// TODO: Auto-generated Javadoc

/**
 * The Class AreaDatesServiceImpl.
 *
 * @author Balaram
 */
@Service
public class AreaDatesServiceImpl implements AreaDatesService {

	/** The area dates dao. */
	@Autowired
	AreaDatesDao areaDatesDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.dayLeasing.service.AreaDatesService#addAreaDate(com.dayLeasing.service
	 * .dto.AreaDatesDTO)
	 */
	@Override
	public String addAreaDate(AreaDatesDTO areaDatesDto) {
		// TODO Auto-generated method stub
		AreaDates areadates = AreaDatesVTO.convertToDates(areaDatesDto);
		return areaDatesDao.addAreaDate(areadates);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.AreaDatesService#getAreaDate(java.lang.String)
	 */
	@Override
	public List<AreaDatesDTO> getAreaDate(String propertyAreaUuid) {
		// TODO Auto-generated method stub
		List<AreaDates> areaDateslist = areaDatesDao
				.getAreaDate(propertyAreaUuid);
		List<AreaDatesDTO> areaDatesDtoList = new ArrayList<>();
		for (AreaDates areaDate : areaDateslist) {
			AreaDatesDTO d = AreaDatesVTO.convertToDatesDTO(areaDate);
			areaDatesDtoList.add(d);
		}

		return areaDatesDtoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.AreaDatesService#updateAreaDate(com.dayLeasing
	 * .service.dto.AreaDatesDTO)
	 */
	public boolean updateAreaDate(AreaDatesDTO areaDatesDto) {
		AreaDates areadates = AreaDatesVTO.convertToDates(areaDatesDto);
		return areaDatesDao.updateAreaDate(areadates);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.AreaDatesService#AreaDateDelete(java.lang.String)
	 */
	@Override
	public boolean AreaDateDelete(String propertyAreaUuid) {
		// TODO Auto-generated method stub
		return areaDatesDao.AreaDateDelete(propertyAreaUuid);

	}

}
