package com.dayLeasing.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dayLeasing.configuration.InValidRequestException;
import com.dayLeasing.configuration.security.TokenAuthenticationService;
import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.service.DayLeasingUserService;
import com.dayLeasing.service.dto.DayLeasingUserDTO;

// TODO: Auto-generated Javadoc

/**
 * The Class DayLeasingUserController.
 *
 * @author Balaram
 */
@Api(value = "USER")
@RestController
public class DayLeasingUserController {

	/** The day leasing user service. */
	@Autowired
	DayLeasingUserService dayLeasingUserService;

	/**
	 * User registeration Post call. to get user register with out auth.
	 *
	 * @param dayLeasingUserDTO
	 *            the day leasing user DTO
	 * @param binding
	 *            the binding
	 * @param map
	 *            the map
	 * @return the response entity
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Boolean>> userRegisteration(
			@Valid @RequestBody DayLeasingUserDTO dayLeasingUserDTO,
			BindingResult binding, ModelMap map, HttpServletRequest req) {

		if (binding.hasErrors()) {
			throw new InValidRequestException(binding.getFieldErrors().get(0)
					.getDefaultMessage());
		}

		boolean success = dayLeasingUserService.userRegisteration(
				dayLeasingUserDTO, req);

		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("registration", success);

		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);

	}

	@RequestMapping(value = "/emailVerification", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Boolean>> getRegisteredUser(
			HttpServletRequest request) {

		Authentication authentication = TokenAuthenticationService
				.getAuthentication(request);
		String userId = authentication.getName();
		boolean status = dayLeasingUserService.userEnable(userId);
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("status", status);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Boolean>> changePassword(
			HttpServletRequest request, @RequestBody String password) {

		Authentication authentication = TokenAuthenticationService
				.getAuthentication(request);
		String userId = authentication.getName();
		boolean status = dayLeasingUserService.userPasswordUpdate(userId,
				password);
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("status", status);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/changePasswordVerification", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Boolean>> changePasswordVerification(
			HttpServletRequest request) {

		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("status", true);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/sendVerification", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Boolean>> sendVerification(
			@RequestParam String email, HttpServletRequest req) {

		boolean status = dayLeasingUserService.sendVerification(email, req);
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("status", status);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

	/**
	 * Gets the registered user.
	 *
	 * @param userId
	 *            the user id
	 * @return the registered user
	 */
	@RequestMapping(value = "/person/{userId}", method = RequestMethod.GET)
	public ResponseEntity<DayLeasingUserDTO> getRegisteredUser(
			@PathVariable String userId) {
		DayLeasingUserDTO dayLeasingUserDTO = dayLeasingUserService
				.getDayLeasingUserDTO(userId);
		return new ResponseEntity<DayLeasingUserDTO>(dayLeasingUserDTO,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/user/hunterdetails/All", method = RequestMethod.GET)
	public ResponseEntity<List<DayleasingUsers>> getAllhunterdetails() {
		List<DayleasingUsers> allHunters = dayLeasingUserService
				.getAllHunters();
		return new ResponseEntity<List<DayleasingUsers>>(allHunters,
				HttpStatus.OK);
	}

	/**
	 * Update user.
	 *
	 * @param userId
	 *            the user id
	 * @param dayLeasingUserDTO
	 *            the day leasing user DTO
	 * @param binding
	 *            the binding
	 * @param map
	 *            the map
	 * @return the response entity
	 */
	@RequestMapping(value = "/person/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<HashMap<String, Boolean>> updateUser(
			@PathVariable String userId,
			@Valid @RequestBody DayLeasingUserDTO dayLeasingUserDTO,
			BindingResult binding, ModelMap map) {
		boolean success = dayLeasingUserService.updateUser(dayLeasingUserDTO,
				userId);
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("update", success);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

	/**
	 * Delete user.
	 *
	 * @param userId
	 *            the user id
	 * @param dayLeasingUserDTO
	 *            the day leasing user DTO
	 * @param binding
	 *            the binding
	 * @param map
	 *            the map
	 * @return the response entity
	 */
	@RequestMapping(value = "/person/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<HashMap<String, Boolean>> deleteUser(
			@PathVariable String userId,
			@RequestBody DayLeasingUserDTO dayLeasingUserDTO,
			BindingResult binding, ModelMap map) {
		boolean success = dayLeasingUserService.deleteUser(dayLeasingUserDTO,
				userId);
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("update", success);
		return new ResponseEntity<HashMap<String, Boolean>>(hashMap,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/fbAuthVerification", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> validateFBAuth(HttpServletRequest request) {

		Authentication authentication = TokenAuthenticationService
				.getAuthentication(request);
		String userId = authentication.getName();
		String token = request
				.getHeader(TokenAuthenticationService.HEADER_STRING);
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("userId", dayLeasingUserService.getUserGuId(userId));
		hashMap.put(TokenAuthenticationService.HEADER_STRING, token);

		return new ResponseEntity<HashMap<String, String>>(hashMap, HttpStatus.OK);
	}

}
