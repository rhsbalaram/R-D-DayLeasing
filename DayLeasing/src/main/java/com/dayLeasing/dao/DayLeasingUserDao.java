package com.dayLeasing.dao;

import java.util.List;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
// TODO: Auto-generated Javadoc

/**
 * The Interface DayLeasingUserDao.
 *
 * @author Balaram
 */
public interface DayLeasingUserDao {

  /**
   * User registeration.
   *
   * @param dayleasingUsers the dayleasing users
   * @param users the users
   * @param userRoles the user roles
   * @return true, if successful
   */
  public boolean userRegisteration(DayleasingUsers dayleasingUsers, Users users, UserRoles userRoles);

  /**
   * Check existing user.
   *
   * @param email the email
   * @return true, if successful
   */
  public boolean checkExistingUser(String email);

  /**
   * Gets the user.
   *
   * @param email the email
   * @return the user
   */
  public DayleasingUsers getUser(String email);

  /**
   * Gets the day leasing user.
   *
   * @param userId the user id
   * @return the day leasing user
   */
  public DayleasingUsers getDayLeasingUser(String userId);
  public List<DayleasingUsers> getAllHunters();
  /**
   * User update.
   *
   * @param dayLeasingUserDTO the day leasing user DTO
   * @param userId the user id
   * @return true, if successful
   */
  public boolean userUpdate(DayLeasingUserDTO dayLeasingUserDTO, String userId);
  public boolean userEnable( String userId);
  public boolean userPasswordUpdate( String userId,String password);
  /**
   * User delete.
   *
   * @param dayLeasingUserDTO the day leasing user DTO
   * @param userId the user id
   * @return true, if successful
   */
  public boolean userDelete(DayLeasingUserDTO dayLeasingUserDTO, String userId);
}
