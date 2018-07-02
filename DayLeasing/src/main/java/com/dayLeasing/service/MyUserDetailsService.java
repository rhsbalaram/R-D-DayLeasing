package com.dayLeasing.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayLeasing.dao.UserDao;
import com.dayLeasing.dao.model.UserRoles;

// TODO: Auto-generated Javadoc
/**
 * The Class MyUserDetailsService.
 *
 * @author Balaram
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

  /** The user dao. */
  @Autowired
  private UserDao userDao;

  /*
   * (non-Javadoc)
   * @see
   * org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang
   * .String)
   */
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

    com.dayLeasing.dao.model.Users user = userDao.findByUserName(username);
    List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoleses());

    return buildUserForAuthentication(user, authorities);

  }


  /**
   * Builds the user for authentication.
   *
   * @param user
   *          the user
   * @param authorities
   *          the authorities
   * @return the user
   */
  // org.springframework.security.core.userdetails.User
  private User buildUserForAuthentication(com.dayLeasing.dao.model.Users user,
      List<GrantedAuthority> authorities) {
    return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true,
        authorities);
  }

  /**
   * Builds the user authority.
   *
   * @param userRoles
   *          the user roles
   * @return the list
   */
  private List<GrantedAuthority> buildUserAuthority(Set<UserRoles> userRoles) {

    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

    // Build user's authorities
    for (UserRoles userRole : userRoles) {
      setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
    }

    List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

    return Result;
  }

}