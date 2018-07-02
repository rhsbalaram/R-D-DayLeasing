package com.dayLeasing.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scala.sys.process.processInternal;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.util.DateUtils;
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
public class PropertydetailsDaoImpl extends AbstractDao implements
		PropertydetailsDao {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.dayLeasing.dao.PropertydetailsDao#addProperty(com.dayLeasing.dao.
	 * model.Propertydetails)
	 */
	@Override
	public Propertydetails addProperty(Propertydetails propertydetails) {

		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		openSession.saveOrUpdate(propertydetails);
		openSession.close();
		return propertydetails;
	}

	/*
	 * (non-Javadoc)
	 * 	 * @see
	 * com.dayLeasing.dao.PropertydetailsDao#getProperties(java.lang.String)
	 */
	@Override
	public List<Propertydetails> getProperties(String propertyUuid) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = null;
		if (propertyUuid.equalsIgnoreCase("ALL")) {
			createQuery = openSession
					.createQuery("Select property from Propertydetails as property order by property.propertyId asc");

		} else {
			createQuery = openSession
					.createQuery("Select property from Propertydetails as property where property.propertyUuid = :propertyUuid order by property.propertyId asc");
			createQuery.setParameter("propertyUuid", propertyUuid);
		}

		List<Propertydetails> list = (List<Propertydetails>) createQuery.list();
		openSession.close();
		return list;

	}

	@Override
	public List<Propertydetails> getPropertiesOfUser(String userId) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = null;

		createQuery = openSession
				.createQuery("Select property from Propertydetails as property where property.userGuid = :userId order by property.propertyId asc");
		createQuery.setParameter("userId", userId);

		List<Propertydetails> list = (List<Propertydetails>) createQuery.list();
		openSession.close();
		return list;

	}

	/*
	 * (non-Javadoc)
	 * @see com.dayLeasing.dao.PropertydetailsDao#getProperty(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Propertydetails getProperty(String userId, String propertyId) {
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = null;
		if (userId == null || userId.isEmpty()) {
			createQuery = openSession
					.createQuery("Select property from Propertydetails as property where property.propertyUuid = :propertyId order by property.propertyId asc");

			createQuery.setParameter("propertyId", propertyId);
		} else {
			createQuery = openSession
					.createQuery("Select property from Propertydetails as property where property.userGuid = :userId and property.propertyUuid = :propertyId order by property.propertyId asc");
			createQuery.setParameter("userId", userId);
			createQuery.setParameter("propertyId", propertyId);
		}
		List<Propertydetails> list = (List<Propertydetails>) createQuery.list();
		openSession.close();
		return list.get(0);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.dayLeasing.dao.PropertydetailsDao#updateProperty(com.dayLeasing.service
	 * .dto.PropertydetailsDTO, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateProperty(PropertydetailsDTO propertydetailsDTO,
			String userId, String propertyId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select property from Propertydetails as property where property.userGuid = :userId and property.propertyUuid = :propertyId");
		createQuery.setParameter("userId", userId);
		createQuery.setParameter("propertyId", propertyId);
		List<Propertydetails> list = (List<Propertydetails>) createQuery.list();

		Propertydetails propertydetails = list.get(0);
		Propertydetails convertedToPropertydetailsToUpdate = PropertydetailsVTO
				.convertToPropertydetailsToUpdate(propertydetailsDTO,
						propertydetails);
		openSession.update(convertedToPropertydetailsToUpdate);

		openSession.flush();
		openSession.close();
		return true;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.dayLeasing.dao.PropertydetailsDao#propertyDelete(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean propertyDelete(String userId, String propertyId) {
		
			// TODO Auto-generated method stub
		int reservationsCount = getReservationsCount(propertyId);
		if(reservationsCount<=0){
			SessionFactory sessionFactory = getSessionFactory();
			Session openSession = sessionFactory.openSession();
			Query deleteProperty = openSession
					.createQuery("delete from Propertydetails as property where property.userGuid = :userId and property.propertyUuid = :propertyId");
			deleteProperty.setParameter("userId", userId);
			deleteProperty.setParameter("propertyId", propertyId);
			int executeUpdate = deleteProperty.executeUpdate();
			if(executeUpdate>0){
				Query selectQueryForAreaUuid = openSession.createQuery("select area.propertyAreaUuid from Propertyarea as area where area.propertyUuid = :propertyId");
				selectQueryForAreaUuid.setParameter("propertyId", propertyId);
				List<String> listOfAreaUuid = selectQueryForAreaUuid.list();
				if(listOfAreaUuid.size()>0){
				Query createQuery = openSession
						.createQuery("delete  from Propertyarea as area where  area.propertyAreaUuid in (:listOfAreaUuid)");
				createQuery.setParameterList("listOfAreaUuid", listOfAreaUuid);
				
				createQuery.executeUpdate();
				Query createQuery1 = openSession
						.createQuery("delete from Propertyslots as slot where slot.propertyAreaUuid in (:listOfAreaUuid)");
				createQuery1.setParameterList("listOfAreaUuid", listOfAreaUuid);

				createQuery1.executeUpdate();
				Query createQuery2 = openSession
						.createQuery("delete from AreaDates as areaDate where areaDate.propertyAreaUuid in (:listOfAreaUuid)");

				createQuery2.setParameterList("listOfAreaUuid", listOfAreaUuid);
				createQuery2.executeUpdate();
			}
			}
			
			openSession.flush();
			openSession.close();
			return true;
		}else{
			return false;
		}

		
	}
	
	@Override
	public int getReservationsCount(String propertyId){
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query query = openSession.createQuery(
		        "select count(*) from Propertyreservation as res where res.propertyUuid =:propertyId and res.reservationFrom>=:currentdate and res.paymentStatus!='Cancelled'");
		query.setParameter("propertyId", propertyId);
		query.setParameter("currentdate", DateUtils.getMidNightTime());
		int count = (int) (long)query.uniqueResult();
		openSession.close();
		return count;
		
	}
}
