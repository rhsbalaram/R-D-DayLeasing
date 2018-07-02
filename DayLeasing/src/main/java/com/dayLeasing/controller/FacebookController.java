package com.dayLeasing.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dayLeasing.configuration.security.TokenAuthenticationService;
import com.dayLeasing.dao.DayLeasingUserDao;
import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.util.GenerateUUID;

@Controller
public class FacebookController {
	 private Facebook facebook;
		
	  private FacebookConnectionFactory facebookConnectionFactory;
	  

		/** The day leasing user dao. */
		@Autowired
		DayLeasingUserDao dayLeasingUserDao;
	 
	  
	  @Autowired
	  ConnectionFactoryLocator connectionFactoryLocator;
	 

	    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
	        this.facebook = facebook;
	    }
	
	 @RequestMapping(value={"/connect/facebook"},method=RequestMethod.GET,params="code")
	  public String helloFacebook(HttpServletRequest req,@RequestParam String code) {
		  Map<String, String[]> parameterMap = req.getParameterMap();
		  AccessGrant accessGrant = getAccessGrant(code, req.getRequestURL().toString());
		  Connection<Facebook> connection = 
			      facebookConnectionFactory.createConnection(accessGrant);
		Facebook api = connection.getApi();
		String[] fields = { "id", "email", "first_name", "last_name" };
		User userProfile = api.fetchObject("me", User.class, fields);
		String email = userProfile.getEmail();
		if (email == null || (email != null && !email.isEmpty())) {
			int mid = userProfile.getId().length() / 2;
			String halfIdString = userProfile.getId().substring(0, mid);
			email = userProfile.getFirstName() + userProfile.getLastName()
					+ "_" + halfIdString + "@facebook.com";
		}
		String redirectUrl = "redirect:http://localhost:9000/#/facebookAuth/";
		
		DayleasingUsers user = dayLeasingUserDao.getUser(email);
		if (user != null) {
			redirectUrl = redirectUrl
					+ URLEncoder.encode(createtokenForMail(email));
		} else {
			redirectUrl += insertDataAndAuthenticate(userProfile, email);
		}

		return redirectUrl;
	}

	 private AccessGrant getAccessGrant(String authorizationCode,String applicationHost) {
		 
		 facebookConnectionFactory = (FacebookConnectionFactory)connectionFactoryLocator.getConnectionFactory("facebook");
		 
		  OAuth2Operations oauthOperations = 
				  facebookConnectionFactory.getOAuthOperations();
		  return oauthOperations.exchangeForAccess(authorizationCode,
		      applicationHost , null);
		}

	 public String createtokenForMail(String email){
			
	    	String JWT = Jwts.builder().setSubject(email + "authority" + "HUNTER")
	    	        .setExpiration(new Date(System.currentTimeMillis() + TokenAuthenticationService.EXPIRATIONTIME))
	    	        .signWith(SignatureAlgorithm.HS512, TokenAuthenticationService.SECRET).compact();
	    	return JWT;
	    
}
	 public String insertDataAndAuthenticate(User userProfile, String email){
		 DayleasingUsers dayleasingUsers = new DayleasingUsers();
		    dayleasingUsers.setCreatedDate(new Date());
		    dayleasingUsers.setMemberType("Hunter");
		    dayleasingUsers.setEmailId(email);
		    dayleasingUsers.setFname(userProfile.getFirstName());
		    dayleasingUsers.setLname(userProfile.getLastName());
		    dayleasingUsers.setUserGuid(GenerateUUID.generateUUID());
		    dayleasingUsers.setUsername(email);
		    
		    Users users = new Users();
		    users.setPassword(userProfile.getId());
		    users.setUsername(email);
		    users.setEnabled(true);
		    
		    UserRoles userRoles = new UserRoles();
		    userRoles.setRole("HUNTER");
		    userRoles.setUsername(email);
		    
		    dayLeasingUserDao.userRegisteration(dayleasingUsers,
					users, userRoles);
		    
		 return URLEncoder.encode(createtokenForMail(email));
	 }

}
