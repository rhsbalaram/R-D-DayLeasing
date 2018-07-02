package com.dayLeasing.dao;

import com.dayLeasing.dao.model.Users;


// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 *
 * @author Balaram
 */
public interface UserDao {

	/**
	 * Find by user name.
	 *
	 * @param username the username
	 * @return the user
	 */
	Users findByUserName(String username);

}