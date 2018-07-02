package com.dayLeasing.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dayLeasing.dao.model.Propertydetails;
import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.service.dto.PropertydetailsDTO;
import com.dayLeasing.service.dto.PropertyslotsDTO;
import com.dayLeasing.service.util.DateUtils;
import com.dayLeasing.service.vto.PropertydetailsVTO;
import com.dayLeasing.service.vto.PropertyslotsVTO;

/**
 * @author Balaram
 *
 */
@Transactional
@Repository
public class PropertySlotsDaoImpl extends AbstractDao implements PropertySlotsDao {

  /* (non-Javadoc)
   * @see com.dayLeasing.dao.PropertySlotsDao#addSlots(com.dayLeasing.dao.model.Propertyslots)
   */
  @Override
  public Propertyslots addSlots(Propertyslots propertyslots) {

    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    openSession.save(propertyslots);
    openSession.close();
    return propertyslots;
  }
  @Override
  public List<Propertyslots> getSlots(String areaID) {
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("Select slot from Propertyslots as slot where slot.propertyAreaUuid = :areaID order by slot.propertySlotsId asc");
    createQuery.setParameter("areaID", areaID);

    List<Propertyslots> list = (List<Propertyslots>) createQuery.list();
    openSession.close();
    return list;

  }
  @Override
  public Propertyslots getSlot(String areaID, String slotId) {
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("Select slot from Propertyslots as slot where slot.propertyAreaUuid = :areaID and slot.propertySlotsUuid = :slotId order by slot.propertySlotsId asc");
    createQuery.setParameter("areaID", areaID);
    createQuery.setParameter("slotId", slotId);
    List<Propertyslots> list = (List<Propertyslots>) createQuery.list();
    openSession.close();
    return list.get(0);

  }
  @Override
  public boolean updateSlot(PropertyslotsDTO propertyslotsDTO, String areaID, String slotId) {
    // TODO Auto-generated method stub
	  int reservationsCount = getReservationsCount(slotId);
	  //if(reservationsCount<=0){
	  if(true){
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("Select slot from Propertyslots as slot where slot.propertyAreaUuid = :areaID and slot.propertySlotsUuid = :slotId");
    createQuery.setParameter("areaID", areaID);
    createQuery.setParameter("slotId", slotId);
    List<Propertyslots> list = (List<Propertyslots>) createQuery.list();

    Propertyslots propertyslots = list.get(0);
    Propertyslots convertedToPropertyslotsToUpdate = PropertyslotsVTO
        .convertToPropertyslotsToUpdate(propertyslotsDTO, propertyslots);
    openSession.update(convertedToPropertyslotsToUpdate);

    openSession.flush();
    openSession.close();
    return true;
	  }else{
		  return false;
	  }

  }

  /* (non-Javadoc)
   * @see com.dayLeasing.dao.PropertySlotsDao#deleteSlots(java.lang.String, java.lang.String)
   */
  @Override
  public boolean deleteSlots(String areaID, String slotId) {
	  int reservationsCount = getReservationsCount(slotId);
	  if(reservationsCount<=0){
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("delete from Propertyslots as slot where slot.propertyAreaUuid = :areaID and slot.propertySlotsUuid = :slotId");
    createQuery.setParameter("areaID", areaID);
    createQuery.setParameter("slotId", slotId);
    createQuery.executeUpdate();

    openSession.flush();
    openSession.close();
    return true;
	  }else{
		  return false;
	  }
  }
  
  @Override
	public int getReservationsCount(String slotId){
		SessionFactory sessionFactory = getSessionFactory();
		Session openSession = sessionFactory.openSession();
		Query query = openSession.createQuery(
		        "select count(*) from Propertyreservation as res where res.propertySlotsUuid =:slotId and res.reservationFrom>=:currentdate and res.paymentStatus!='Cancelled'");
		query.setParameter("slotId", slotId);
		query.setParameter("currentdate", DateUtils.getMidNightTime());
		int count = (int) (long)query.uniqueResult();
		openSession.close();
		return count;
		
	}
@Override
public List<Propertyslots> addMultipleSlots(List<Propertyslots> propertyslots) {
	 SessionFactory sessionFactory = getSessionFactory();
	    Session openSession = sessionFactory.openSession();
	    for(Propertyslots propertyslot : propertyslots){
	    	openSession.save(propertyslot);
	    }
	    openSession.close();
	return propertyslots;
}
}
