package com.dayLeasing.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scala.sys.process.processInternal;

import com.dayLeasing.dao.model.AreaDates;
import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.vto.DayLeasingUserVTO;
import com.dayLeasing.service.vto.PropertydetailsVTO;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertydetailsDaoImpl.
 *
 * @author Balaram
 */

@Transactional
@Repository
public class AreaDatesDaoImpl extends AbstractDao implements AreaDatesDao {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.dayLeasing.dao.PropertydetailsDao#addProperty(com.dayLeasing.dao.
	 * model.Propertydetails)
	 */
	@Override
	public String addAreaDate(AreaDates areadates) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		openSession.save(areadates);
		openSession.close();
		return areadates.getPropertyAreaUuid();

	}

	@Override
	public List<AreaDates> getAreaDate(String propertyAreaUuid) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select areaDate from AreaDates as areaDate where areaDate.propertyAreaUuid = :propertyAreaUuid");
		createQuery.setParameter("propertyAreaUuid", propertyAreaUuid);

		List<AreaDates> list = (List<AreaDates>) createQuery.list();
		openSession.close();
		return list;
	}

	@Override
	public boolean updateAreaDate(AreaDates areadates) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		openSession.update(areadates);
		openSession.flush();

		openSession.close();
		return true;
	}

	@Override
	public boolean AreaDateDelete(String propertyAreaUuid) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("delete from AreaDates as areaDate where areaDate.propertyAreaUuid = :propertyAreaUuid");

		createQuery.setParameter("propertyAreaUuid", propertyAreaUuid);
		createQuery.executeUpdate();

		openSession.flush();
		openSession.close();
		return true;

	}

}
