package com.dayLeasing.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
// TODO: Auto-generated Javadoc

/**
 * The Interface DayLeasingUserService.
 *
 * @author Balaram
 */
public interface DayLeasingUserService {

  /**
   * User registeration.
   *
   * @param dayLeasingUserDTO the day leasing user DTO
   * @return true, if successful
   */
  public boolean userRegisteration(DayLeasingUserDTO dayLeasingUserDTO,HttpServletRequest req);

  /**
   * Gets the day leasing user DTO.
   *
   * @param userId the user id
   * @return the day leasing user DTO
   */
  public DayLeasingUserDTO getDayLeasingUserDTO(String userId);
  public List<DayleasingUsers> getAllHunters();
  /**
   * Update user.
   *
   * @param dayLeasingUserDTO the day leasing user DTO
   * @param userId the user id
   * @return true, if successful
   */
  public boolean updateUser(DayLeasingUserDTO dayLeasingUserDTO, String userId);

  public boolean userEnable( String userId);
  public boolean userPasswordUpdate( String userId,String password);
  public boolean sendVerification(String email,HttpServletRequest req);
  public String getUserGuId(String email);
  /**
   * Delete user.
   *
   * @param dayLeasingUserDTO the day leasing user DTO
   * @param userId the user id
   * @return true, if successful
   */
  public boolean deleteUser(DayLeasingUserDTO dayLeasingUserDTO, String userId);

}
