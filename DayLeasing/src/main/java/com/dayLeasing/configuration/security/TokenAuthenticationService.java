package com.dayLeasing.configuration.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.dayLeasing.dao.DayLeasingUserDao;
import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.service.DayLeasingUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;

// TODO: Auto-generated Javadoc

/**
 * The Class TokenAuthenticationService.
 *
 * @author Balaram
 */
@Component
public class TokenAuthenticationService {

  /** The Constant EXPIRATIONTIME. */
 public static final long EXPIRATIONTIME = 432000000; // 10 days

  /** The Constant SECRET. */
 public static final String SECRET = "difficult_12345";

  /** The Constant TOKEN_PREFIX. */
 public static final String TOKEN_PREFIX = "Bearer";

  /** The Constant HEADER_STRING. */
 public static final String HEADER_STRING = "Authorization";

  /** The day leasing user dao. */
  private static DayLeasingUserDao dayLeasingUserDao;

  /**
   * Sets the day leasing user dao.
   *
   * @param dayLeasingUserDao the new day leasing user dao
   */
  @Autowired
  public void setDayLeasingUserDao(DayLeasingUserDao dayLeasingUserDao) {
    TokenAuthenticationService.dayLeasingUserDao = dayLeasingUserDao;
  }

  /**
   * Adds the authentication.
   *
   * @param res
   *          the res
   * @param username
   *          the username
   * @param pwd
   *          the pwd
   * @param authorities
   *          the authorities
   */
  static void addAuthentication(HttpServletResponse res, String username, Object pwd,
      Collection<? extends GrantedAuthority> authorities) {
    String authority = "";
    for (GrantedAuthority auth : authorities) {
      authority = authority + "," + auth.toString();

    }
    authority = authority.replaceFirst(",", "");

    String JWT = Jwts.builder().setSubject(username + "authority" + authority)
        .setExpiration(new Date(System.currentTimeMillis() + (EXPIRATIONTIME*100)))
        .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    res.setContentType("application/json");
    try {
      DayleasingUsers user = dayLeasingUserDao.getUser(username);
      JSONObject jsonObject = new JSONObject();

      jsonObject.put(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
      jsonObject.put("userId", user.getUserGuid());
      res.getWriter().print(jsonObject);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Gets the authentication.
   *
   * @param request
   *          the request
   * @return the authentication
   */
 public static Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      try {
        // parse the token.
        String subject = Jwts.parser().setSigningKey(SECRET)
            .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
        String[] userAndAuthorities = subject.split("authority");
        String authorities = userAndAuthorities[1];
        String[] roles = authorities.split(",");

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (String role : roles) {
          setAuths.add(new SimpleGrantedAuthority(role));
        }

        List<GrantedAuthority> authRoles = new ArrayList<GrantedAuthority>(setAuths);

        return subject != null ? new UsernamePasswordAuthenticationToken(userAndAuthorities[0],
            null, authRoles) : null;
      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }
}