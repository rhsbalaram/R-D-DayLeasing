package com.dayLeasing.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dayLeasing.dao.model.Testtable;

// TODO: Auto-generated Javadoc
/**
 * The Class DashboardDaoImpl.
 *
 * @author Balaram
 */
@Repository("DashboardDao")
public class DashboardDaoImpl extends AbstractDao implements DashboardDao {

  /*
   * (non-Javadoc)
   * @see com.dayLeasing.dao.DashboardDao#getName(int)
   */
  @Override
  public List<Testtable> getName(int index) {
    // TODO Auto-generated method stub
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession.createQuery(" from Testtable");

    List<Testtable> list = createQuery.list();

    openSession.close();
    return list;
  }

}
