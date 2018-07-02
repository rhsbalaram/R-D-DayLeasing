package com.dayLeasing.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dayLeasing.configuration.security.TokenAuthenticationService;
import com.dayLeasing.dao.DayLeasingUserDao;
import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.dao.model.UserRoles;
import com.dayLeasing.dao.model.Users;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.util.MailUtil;
import com.dayLeasing.service.vto.DayLeasingUserVTO;
import com.dayLeasing.service.vto.UsersVTO;

// TODO: Auto-generated Javadoc
/**
 * The Class DayLeasingUserServiceImpl.
 *
 * @author Balaram
 */
@Service
public class DayLeasingUserServiceImpl implements DayLeasingUserService {

	/** The day leasing user dao. */
	@Autowired
	DayLeasingUserDao dayLeasingUserDao;

	/** The mail util. */
	@Autowired
	MailUtil mailUtil;
	
	@Autowired
	@Qualifier("verifymail")
	String verifymail;
	
	@Autowired
	@Qualifier("passwordchange")
	String passwordchange;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.DayLeasingUserService#userRegisteration(com.dayLeasing
	 * .service.dto.DayLeasingUserDTO)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean userRegisteration(DayLeasingUserDTO dayLeasingUserDTO,HttpServletRequest req) {
		// TODO Auto-generated method stub
		if (dayLeasingUserDao.checkExistingUser(dayLeasingUserDTO.getEmailId())) {
			//mailUtil.sendMail("balaramrhs.friend@gmail.com", "text", "texting");
			return false;
		}

		DayleasingUsers dayleasingUsers = DayLeasingUserVTO
				.convertToDayleasingUsers(dayLeasingUserDTO);
		Users users = UsersVTO.convertToUsers(dayLeasingUserDTO);
		UserRoles userRoles = UsersVTO.converToUserRoles(dayLeasingUserDTO);
		boolean success = dayLeasingUserDao.userRegisteration(dayleasingUsers,
				users, userRoles);
		String createtokenForMail = createtokenForMail(dayLeasingUserDTO.getEmailId());
		String servername = req.getServerName();
		/////////////////////////Mail Content///////////
		
		////////////////////////////
		
		
		String verificationMailBody="https://www.dayleasing.com/#/loginVerification/"+URLEncoder.encode(createtokenForMail);
		String replace = verifymail.replace("texttoreplacehere", verificationMailBody);
	
		mailUtil.sendMail(dayLeasingUserDTO.getEmailId(), "Dayleasing Email Verification",replace );

		return success;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.DayLeasingUserService#getDayLeasingUserDTO(java
	 * .lang.String)
	 */
	@Override
	public DayLeasingUserDTO getDayLeasingUserDTO(String userId) {
		DayleasingUsers dayLeasingUser = dayLeasingUserDao
				.getDayLeasingUser(userId);
		DayLeasingUserDTO dayLeasingUserDTO = DayLeasingUserVTO
				.convertToDayLeasingUserDTO(dayLeasingUser);
		return dayLeasingUserDTO;

	}
	
	@Override
	 public List<DayleasingUsers> getAllHunters(){
		  List<DayleasingUsers> allHunters = dayLeasingUserDao.getAllHunters();
		  return allHunters;
	 }
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.DayLeasingUserService#updateUser(com.dayLeasing
	 * .service.dto.DayLeasingUserDTO, java.lang.String)
	 */
	@Override
	public boolean updateUser(DayLeasingUserDTO dayLeasingUserDTO, String userId) {
		return dayLeasingUserDao.userUpdate(dayLeasingUserDTO, userId);
	}
	
	@Override
	 public boolean userPasswordUpdate( String userId,String password){
		 return dayLeasingUserDao.userPasswordUpdate(userId, password);
	 }
	
	@Override
	public boolean userEnable( String userId){
		return dayLeasingUserDao.userEnable(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayLeasing.service.DayLeasingUserService#deleteUser(com.dayLeasing
	 * .service.dto.DayLeasingUserDTO, java.lang.String)
	 */
	@Override
	public boolean deleteUser(DayLeasingUserDTO dayLeasingUserDTO, String userId) {
		return dayLeasingUserDao.userDelete(dayLeasingUserDTO, userId);
	}
	
	@Override
	public boolean sendVerification(String email,HttpServletRequest req) {
		
		if (dayLeasingUserDao.checkExistingUser(email)) {
			//mailUtil.sendMail("balaramrhs.friend@gmail.com", "text", "texting");
			String createtokenForMail = createtokenForMail(email);
			String servername = req.getServerName();
			
			
			String verificationMailBody="https://www.dayleasing.com/#/changePassword/"+URLEncoder.encode(createtokenForMail);
			String replace = passwordchange.replace("texttoreplacehere", verificationMailBody);
			mailUtil.sendMail(email, "Dayleasing Email Verification",replace );

			return true;
		}
		else return false;
		
	}

	
	public String createtokenForMail(String email){
		
		    	String JWT = Jwts.builder().setSubject(email + "authority" + "HUNTER")
		    	        .setExpiration(new Date(System.currentTimeMillis() + TokenAuthenticationService.EXPIRATIONTIME))
		    	        .signWith(SignatureAlgorithm.HS512, TokenAuthenticationService.SECRET).compact();
		    	return JWT;
		    
	}

	@Override
	public String getUserGuId(String email) {
		DayleasingUsers dayLeasingUser = dayLeasingUserDao
				.getUser(email);
		return dayLeasingUser.getUserGuid();
	}

	// mailVerfication Code -> success enable user -> redirect to login

	// forgetPassword -> sendmail->enable user->redirect to forget password with
	// authentication token.

}
