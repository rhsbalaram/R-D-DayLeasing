package com.dayLeasing.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.Propertyarea;
import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.dto.PropertyareaDTO;
import com.dayLeasing.service.util.DateUtils;
import com.dayLeasing.service.vto.PropertyareaVTO;
import com.dayLeasing.service.vto.PropertyslotsVTO;

// TODO: Auto-generated Javadoc

/**
 * The Class PropertyareaDaoImpl.
 *
 * @author Balaram
 */
@Repository
public class PropertyareaDaoImpl extends AbstractDao implements PropertyareaDao {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.dayLeasing.dao.PropertyareaDao#addPropertyArea(com.dayLeasing.dao
	 * .model.Propertyarea)
	 */
	@Override
	public String addPropertyArea(Propertyarea propertyarea) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		openSession.save(propertyarea);
		openSession.close();
		return propertyarea.getPropertyAreaUuid();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.dayLeasing.dao.PropertyareaDao#getPropertyAreas(java.lang.String)
	 */
	@Override
	public List<Propertyarea> getPropertyAreas(String propertyId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("select area from Propertyarea as area where  area.propertyUuid=:userId order by area.propertyAreaId asc");
		createQuery.setParameter("userId", propertyId);
		// createQuery.setParameter("PropertyAreaUUID", propertyId);
		List<Propertyarea> list = (List<Propertyarea>) createQuery.list();

		openSession.close();
		return list;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.dayLeasing.dao.PropertyareaDao#updateProperty(com.dayLeasing.service
	 * .dto.PropertyareaDTO, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateProperty(PropertyareaDTO propertyareaDTO,
			String userId, String propertyId) {
		// TODO Auto-generated method stub
		
		/*int reservationsCount = getReservationsCount(propertyId);
		if(reservationsCount<=0)
		{*/
			SessionFactory sessionFactory = getSessionFactory();
			Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select area from Propertyarea as area where area.propertyUuid = :userId and area.propertyAreaUuid = :propertyId");
		createQuery.setParameter("userId", userId);
		createQuery.setParameter("propertyId", propertyId);
		@SuppressWarnings("unchecked")
		List<Propertyarea> list = createQuery.list();

		if (list != null && list.size() > 0) {
			Propertyarea propertyarea = list.get(0);
			Propertyarea convertedToPropertyareaToUpdate = PropertyareaVTO
					.convertToPropertyareaToUpdate(propertyareaDTO,
							propertyarea);
			openSession.update(convertedToPropertyareaToUpdate);
			openSession.flush();
			openSession.close();
			return true;
		} else {
			openSession.close();
			return false;
		}
		/*}else{
			
			return false;
		}*/

	}

	/*
	 * (non-Javadoc)
	 * @see com.dayLeasing.dao.PropertyareaDao#propertyDelete(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean AreaDelete(String propertyId, String areaId) {
		// TODO Auto-generated method stub

		
		int reservationsCount = getReservationsCount(areaId);
		if(reservationsCount<=0)
		{
			SessionFactory sessionFactory = getSessionFactory();
			Session openSession = sessionFactory.openSession();	
		Query createQuery = openSession
				.createQuery("delete  from Propertyarea as area where area.propertyUuid = :propertyId and area.propertyAreaUuid = :areaId");
		createQuery.setParameter("propertyId", propertyId);
		createQuery.setParameter("areaId", areaId);
		createQuery.executeUpdate();
		Query createQuery1 = openSession
				.createQuery("delete from Propertyslots as slot where slot.propertyAreaUuid = :areaID");
		createQuery1.setParameter("areaID", areaId);

		createQuery1.executeUpdate();
		Query createQuery2 = openSession
				.createQuery("delete from AreaDates as areaDate where areaDate.propertyAreaUuid = :propertyAreaUuid");

		createQuery2.setParameter("propertyAreaUuid", areaId);
		createQuery2.executeUpdate();
		openSession.flush();
		openSession.close();
		return true;
		}else{
			
			return false;
		}

	}
	
	@Override
	public int getReservationsCount(String areaId){
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query query = openSession.createQuery(
		        "select count(*) from Propertyreservation as res where res.propertyAreaUuid =:areaId and res.reservationFrom>=:currentdate and res.paymentStatus!='Cancelled'");
		query.setParameter("areaId", areaId);
		query.setParameter("currentdate", DateUtils.getMidNightTime());
		int count = (int) (long)query.uniqueResult();
		openSession.close();
		return count;
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.dayLeasing.dao.PropertyareaDao#getPropertyArea(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Propertyarea getPropertyArea(String propertyAreaUuid) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query createQuery = openSession
				.createQuery("Select area from Propertyarea as area where  area.propertyAreaUuid= :propertyAreaUuid order by area.propertyAreaId asc");

		createQuery.setParameter("propertyAreaUuid", propertyAreaUuid);

		List<Propertyarea> list = (List<Propertyarea>) createQuery.list();
		openSession.close();
		if (list != null && list.size() > 0)
			return list.get(0);
		else {
			return null;
		}

	}

}
