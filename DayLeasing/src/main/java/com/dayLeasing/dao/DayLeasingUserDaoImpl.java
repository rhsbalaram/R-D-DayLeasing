package com.dayLeasing.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.vto.DayLeasingUserVTO;

// TODO: Auto-generated Javadoc
/**
 * The Class DayLeasingUserDaoImpl.
 *
 * @author Balaram
 */
@Transactional
@Repository
public class DayLeasingUserDaoImpl extends AbstractDao implements DayLeasingUserDao {

  /* (non-Javadoc)
   * @see com.dayLeasing.dao.DayLeasingUserDao#userRegisteration(com.dayLeasing.dao.model.DayleasingUsers, com.dayLeasing.dao.model.Users, com.dayLeasing.dao.model.UserRoles)
   */
  @Override
  public boolean userRegisteration(DayleasingUsers dayleasingUsers, Users users, UserRoles userRoles) {
    // TODO Auto-generated method stub
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    openSession.save(dayleasingUsers);
    openSession.save(users);
    userRoles.setUsers(users);
    users.getUserRoleses().add(userRoles);
    openSession.save(userRoles);

    openSession.close();
    return true;

  }

  /* (non-Javadoc)
   * @see com.dayLeasing.dao.DayLeasingUserDao#checkExistingUser(java.lang.String)
   */
  @Override
  public boolean checkExistingUser(String email) {
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("Select user from Users as user where user.username = :email");
    createQuery.setParameter("email", email);

    List list = createQuery.list();
    openSession.close();
    if (list.size() > 0) {
      return true;
    } else
      return false;
  }

  /* (non-Javadoc)
   * @see com.dayLeasing.dao.DayLeasingUserDao#getUser(java.lang.String)
   */
  @Override
  public DayleasingUsers getUser(String email) {
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("Select user from DayleasingUsers as user where user.emailId = :email");
    createQuery.setParameter("email", email);

    List<DayleasingUsers> list = (List<DayleasingUsers>) createQuery.list();
    openSession.close();
    return list.isEmpty() ? null :list.get(0);

  }
  
  @Override
  public List<DayleasingUsers> getAllHunters() {
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("Select user from DayleasingUsers as user where user.memberType ='Hunter'");
    //createQuery.setParameter("email", email);

    List<DayleasingUsers> list = (List<DayleasingUsers>) createQuery.list();
    openSession.close();
    return list;

  }

  /* (non-Javadoc)
   * @see com.dayLeasing.dao.DayLeasingUserDao#getDayLeasingUser(java.lang.String)
   */
  @Override
  public DayleasingUsers getDayLeasingUser(String userId) {
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("Select user from DayleasingUsers as user where user.userGuid = :userId");
    createQuery.setParameter("userId", userId);

    List<DayleasingUsers> list = (List<DayleasingUsers>) createQuery.list();
    openSession.close();
    return list.get(0);

  }

  /* (non-Javadoc)
   * @see com.dayLeasing.dao.DayLeasingUserDao#userUpdate(com.dayLeasing.service.dto.DayLeasingUserDTO, java.lang.String)
   */
  @Override
  public boolean userUpdate(DayLeasingUserDTO dayLeasingUserDTO, String userId) {
    // TODO Auto-generated method stub
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("Select user from DayleasingUsers as user where user.userGuid = :userId");
    createQuery.setParameter("userId", userId);
    List<DayleasingUsers> list = (List<DayleasingUsers>) createQuery.list();
    DayleasingUsers dayleasingUsers = list.get(0);
    DayleasingUsers convertedToDayleasingUsersToUpdate = DayLeasingUserVTO
        .convertToDayleasingUsersToUpdate(dayLeasingUserDTO, dayleasingUsers);
    openSession.update(convertedToDayleasingUsersToUpdate);
    Query createQueryforusers = openSession
        .createQuery("Select user from Users as user where user.username = :email");
    createQueryforusers.setParameter("email", dayLeasingUserDTO.getEmailId());

    List<Users> userlist = (List<Users>) createQueryforusers.list();
    Users users = userlist.get(0);
    String password = dayLeasingUserDTO.getPassword();
    if (password != null) {
      users.setPassword(password);
      openSession.update(users);

    }
    openSession.flush();
    openSession.close();
    return true;

  }
  
  @Override
  public boolean userEnable( String userId) {
	  SessionFactory sessionFactory = getSessionFactory();
	    Session openSession = sessionFactory.openSession();
	    Query createQueryforusers = openSession
	            .createQuery("Update  Users as user set user.enabled =:enabled where user.username = :email");
	    createQueryforusers.setParameter("enabled", true);
	    createQueryforusers.setParameter("email", userId);
	    int executeUpdate = createQueryforusers.executeUpdate();
		openSession.flush();
		openSession.close();
		if(executeUpdate==1)
		{return true;}
		return false;
	       
	    
	  
  }
  
  @Override
  public boolean userPasswordUpdate( String userId,String password) {
	  SessionFactory sessionFactory = getSessionFactory();
	    Session openSession = sessionFactory.openSession();
	    Query createQueryforusers = openSession
	            .createQuery("Update  Users as user set user.password =:password where user.username = :email");
	    createQueryforusers.setParameter("password", password);
	    createQueryforusers.setParameter("email", userId);
	    createQueryforusers.executeUpdate();
		openSession.flush();
		openSession.close();
		return true;
	       
	    
	  
  }
  

  /* (non-Javadoc)
   * @see com.dayLeasing.dao.DayLeasingUserDao#userDelete(com.dayLeasing.service.dto.DayLeasingUserDTO, java.lang.String)
   */
  @Override
  public boolean userDelete(DayLeasingUserDTO dayLeasingUserDTO, String userId) {
    SessionFactory sessionFactory = getSessionFactory();
    Session openSession = sessionFactory.openSession();
    Query createQuery = openSession
        .createQuery("delete from DayleasingUsers as user  where user.userGuid = :userId");
    createQuery.setParameter("userId", userId);
    createQuery.executeUpdate();
    Query createQueryforusers = openSession
        .createQuery("Select user from Users as user where user.username = :email");
    createQueryforusers.setParameter("email", dayLeasingUserDTO.getEmailId());

    List<Users> userlist = (List<Users>) createQueryforusers.list();
    Users users = userlist.get(0);
    Set<UserRoles> userRoleses = users.getUserRoleses();
    for (UserRoles userRoles : userRoleses) {
      openSession.delete(userRoles);
    }
    openSession.delete(users);
    openSession.flush();
    openSession.close();
    return true;
  }

}
