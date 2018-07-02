package com.dayLeasing.service.vto;

import java.util.HashSet;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.dto.DayLeasingUserDTO;

// TODO: Auto-generated Javadoc

/**
 * The Class UsersVTO.
 *
 * @author Balaram
 */
public class UsersVTO {

  /**
   * Convert to users.
   *
   * @param dayLeasingUserDTO
   *          the day leasing user DTO
   * @return the users
   */
  public static Users convertToUsers(DayLeasingUserDTO dayLeasingUserDTO) {
    Users users = new Users();
    users.setPassword(dayLeasingUserDTO.getPassword());
    users.setUsername(dayLeasingUserDTO.getEmailId());

    HashSet<UserRoles> hashSet = new HashSet<UserRoles>();

    users.setUserRoleses(hashSet);
    return users;
  }

  /**
   * Conver to user roles.
   *
   * @param dayLeasingUserDTO
   *          the day leasing user DTO
   * @return the user roles
   */
  public static UserRoles converToUserRoles(DayLeasingUserDTO dayLeasingUserDTO) {
    UserRoles userRoles = new UserRoles();
    userRoles.setRole("HUNTER");
    userRoles.setUsername(dayLeasingUserDTO.getEmailId());
    return userRoles;
  }

}
