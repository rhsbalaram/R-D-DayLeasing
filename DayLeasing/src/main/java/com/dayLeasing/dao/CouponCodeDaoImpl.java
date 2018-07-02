package com.dayLeasing.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dayLeasing.dao.model.DayleasingCoupons;
import com.dayLeasing.dao.model.Propertyslots;
import com.dayLeasing.service.dto.PropertyslotsDTO;
import com.dayLeasing.service.vto.PropertyslotsVTO;

@Transactional
@Repository
public class CouponCodeDaoImpl extends AbstractDao implements CouponCodeDao {
	
	@Override
	  public DayleasingCoupons addCoupon(DayleasingCoupons coupon) {

	    SessionFactory sessionFactory = getSessionFactory();
	    Session openSession = sessionFactory.openSession();
	    openSession.save(coupon);
	    openSession.close();
	    return coupon;
	  }
	
	 @Override
	  public List<DayleasingCoupons> getCoupons() {
	    SessionFactory sessionFactory = getSessionFactory();
	    Session openSession = sessionFactory.openSession();
	    Query createQuery = openSession
	        .createQuery("Select coupon from DayleasingCoupons as coupon  order by coupon.sno asc");
	    

	    List<DayleasingCoupons> list = (List<DayleasingCoupons>) createQuery.list();
	    openSession.close();
	    return list;

	  }
	 
	 @Override
	  public boolean updateSlot(DayleasingCoupons coupon) {
	    // TODO Auto-generated method stub
		 
	    SessionFactory sessionFactory = getSessionFactory();
	    Session openSession = sessionFactory.openSession();
	   
	  
	    openSession.update(coupon);

	    openSession.flush();
	    openSession.close();
	    return true;
		  

	  }
	 
	 @Override
	  public boolean deleteCoupon( int sno) {
		  
	    SessionFactory sessionFactory = getSessionFactory();
	    Session openSession = sessionFactory.openSession();
	    Query createQuery = openSession
	        .createQuery("delete from DayleasingCoupons as coupon where coupon.sno = :sno ");
	    createQuery.setParameter("sno", sno);
	    
	    createQuery.executeUpdate();

	    openSession.flush();
	    openSession.close();
	    return true;
		  }
	

}
