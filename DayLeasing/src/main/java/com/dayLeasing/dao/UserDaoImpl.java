package com.dayLeasing.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dayLeasing.dao.model.Users;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDaoImpl.
 *
 * @author Balaram
 */
@Repository
public class UserDaoImpl implements UserDao {

  /** The session factory. */
  @Autowired
  private SessionFactory sessionFactory;


  /* (non-Javadoc)
   * @see com.dayLeasing.dao.UserDao#findByUserName(java.lang.String)
   */
  /**
   * Find by user name.
   *
   * @param username the username
   * @return the user
   */
  @SuppressWarnings("unchecked")
  public Users findByUserName(String username) {

    List<Users> users = new ArrayList<Users>();

    Session openSession = sessionFactory.openSession();

    users = openSession.createQuery("from Users where username=?").setParameter(0, username).list();
    openSession.close();

    if (users.size() > 0) {
      return users.get(0);
    } else {
      return null;
    }

  }

}