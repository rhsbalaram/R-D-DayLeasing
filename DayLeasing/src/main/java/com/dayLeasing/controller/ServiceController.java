package com.dayLeasing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayLeasing.dao.model.Testtable;
import com.dayLeasing.service.DashboardService;
import com.dayLeasing.service.dto.DayLeasingUserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceController.
 *
 * @author Balaram
 */
@RestController
public class ServiceController {

  /** The service. */
  @Autowired
  DashboardService service;

  /**
   * List all users.
   *
   * @param modal
   *          the modal
   * @return the response entity
   */
  @RequestMapping(value = "/user/", method = RequestMethod.GET)
  public ResponseEntity<List<Testtable>> listAllUsers(ModelMap modal) {

    List<Testtable> name = service.getName(1);
    return new ResponseEntity<List<Testtable>>(name, HttpStatus.OK);
  }

  /**
   * Adds the user.
   *
   * @param dayLeasingUserDTO
   *          the day leasing user DTO
   * @return the string
   */
  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  public String addUser(@RequestBody DayLeasingUserDTO dayLeasingUserDTO) {

    return null;

  }

}
