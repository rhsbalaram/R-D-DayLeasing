package com.dayLeasing.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractDao.
 *
 * @author Balaram
 */
public abstract class AbstractDao {

  /** The session factory. */
  public SessionFactory sessionFactory;

  /**
   * Gets the session.
   *
   * @return the session
   */
  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  /**
   * Gets the session factory.
   *
   * @return the sessionFactory
   */
  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  /**
   * Sets the session factory.
   *
   * @param sessionFactory
   *          the sessionFactory to set
   */
  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

}
