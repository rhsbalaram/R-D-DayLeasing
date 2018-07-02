package com.dayLeasing.service.vto;

import java.time.Instant;
import java.util.Date;

import com.dayLeasing.dao.model.DayleasingUsers;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import com.dayLeasing.service.util.DateUtils;
import com.dayLeasing.service.util.GenerateUUID;

// TODO: Auto-generated Javadoc

/**
 * The Class DayLeasingUserVTO.
 *
 * @author Balaram
 */
public class DayLeasingUserVTO {

  /**
   * Convert to dayleasing users.
   *
   * @param dayLeasingUserDTO
   *          the day leasing user DTO
   * @return the dayleasing users
   */
  public static DayleasingUsers convertToDayleasingUsers(DayLeasingUserDTO dayLeasingUserDTO) {
    DayleasingUsers dayleasingUsers = new DayleasingUsers();
    dayleasingUsers.setAddress1(dayLeasingUserDTO.getAddress1());
    dayleasingUsers.setAddress2(dayLeasingUserDTO.getAddress2());
    // dayleasingUsers.setAgree(dayLeasingUserDTO.set);
    dayleasingUsers.setCity(dayLeasingUserDTO.getCity());
    dayleasingUsers.setCountry(dayLeasingUserDTO.getCountry());
    dayleasingUsers.setCreatedDate(new Date());
    dayleasingUsers.setMemberType("Hunter");
   // dayleasingUsers.setDob(Date.from(dayLeasingUserDTO.getDob()));
    // Instant instant = dayleasingUsers.getDob().toInstant();
    dayleasingUsers.setEmailId(dayLeasingUserDTO.getEmailId());
    dayleasingUsers.setFname(dayLeasingUserDTO.getFname());
    dayleasingUsers.setGender(dayLeasingUserDTO.getGender());
    dayleasingUsers.setLname(dayLeasingUserDTO.getLname());
    dayleasingUsers.setUserGuid(GenerateUUID.generateUUID());
    dayleasingUsers.setUsername(dayLeasingUserDTO.getEmailId());
    dayleasingUsers.setZipCode(dayLeasingUserDTO.getZipCode());
    dayleasingUsers.setPhone(dayLeasingUserDTO.getPhone());
    return dayleasingUsers;
  }

  /**
   * Convert to day leasing user DTO.
   *
   * @param dayleasingUsers
   *          the dayleasing users
   * @return the day leasing user DTO
   */
  public static DayLeasingUserDTO convertToDayLeasingUserDTO(DayleasingUsers dayleasingUsers) {
    DayLeasingUserDTO dayLeasingUserDTO = new DayLeasingUserDTO();
    dayLeasingUserDTO.setAddress1(dayleasingUsers.getAddress1());
    dayLeasingUserDTO.setAddress2(dayleasingUsers.getAddress2());
    dayLeasingUserDTO.setCity(dayleasingUsers.getCity());
    dayLeasingUserDTO.setCountry(dayleasingUsers.getCountry());
   // dayLeasingUserDTO.setCreatedDate(DateUtils.returnInstantIfNotnull(dayleasingUsers.getCreatedDate()));
    dayLeasingUserDTO.setCurrentField(dayleasingUsers.getCurrentField());
   // dayLeasingUserDTO.setDob(DateUtils.returnInstantIfNotnull(dayleasingUsers.getDob()));
    dayLeasingUserDTO.setEmailId(dayleasingUsers.getEmailId());
    dayLeasingUserDTO.setFname(dayleasingUsers.getFname());
    dayLeasingUserDTO.setGender(dayleasingUsers.getGender());
    dayLeasingUserDTO.setGuestOf(dayleasingUsers.getGuestOf());
   // dayLeasingUserDTO.setHuntDate(DateUtils.returnInstantIfNotnull(dayleasingUsers.getHuntDate()));
    dayLeasingUserDTO.setHuntingLicense(dayleasingUsers.getHuntingLicense());
    dayLeasingUserDTO.setLname(dayleasingUsers.getLname());
    dayLeasingUserDTO.setMemberType(dayleasingUsers.getMemberType());
    dayLeasingUserDTO.setMname(dayleasingUsers.getMname());
    dayLeasingUserDTO.setPhone(dayleasingUsers.getPhone());
    dayLeasingUserDTO.setPhoto(dayleasingUsers.getPhoto());
    dayLeasingUserDTO.setSafetyCourse(dayleasingUsers.getSafetyCourse());
    dayLeasingUserDTO.setSource(dayleasingUsers.getSource());
    dayLeasingUserDTO.setStartingField(dayleasingUsers.getStartingField());
    dayLeasingUserDTO.setState(dayleasingUsers.getState());
    dayLeasingUserDTO.setUserClass(dayleasingUsers.getUserClass());
    dayLeasingUserDTO.setVehicle(dayleasingUsers.getVehicle());
    dayLeasingUserDTO.setVehicleColor(dayleasingUsers.getVehicleColor());
    dayLeasingUserDTO.setVehicleLicense(dayleasingUsers.getVehicleLicense());
    dayLeasingUserDTO.setZipCode(dayleasingUsers.getZipCode());
    return dayLeasingUserDTO;
  }

  /**
   * Convert to dayleasing users to update.
   *
   * @param dayLeasingUserDTO
   *          the day leasing user DTO
   * @param dayleasingUsers
   *          the dayleasing users
   * @return the dayleasing users
   */
  public static DayleasingUsers convertToDayleasingUsersToUpdate(
      DayLeasingUserDTO dayLeasingUserDTO, DayleasingUsers dayleasingUsers) {

    dayleasingUsers.setAddress1(dayLeasingUserDTO.getAddress1());
    dayleasingUsers.setAddress2(dayLeasingUserDTO.getAddress2());
    // dayleasingUsers.setAgree(dayLeasingUserDTO.set);
    dayleasingUsers.setCity(dayLeasingUserDTO.getCity());
    dayleasingUsers.setCountry(dayLeasingUserDTO.getCountry());
    // dayleasingUsers.setCreatedDate(new Date());
   // dayleasingUsers.setDob(Date.from(dayLeasingUserDTO.getDob()));
    // Instant instant = dayleasingUsers.getDob().toInstant();
    // dayleasingUsers.setEmailId(dayLeasingUserDTO.getEmailId());
    dayleasingUsers.setFname(dayLeasingUserDTO.getFname());
    dayleasingUsers.setGender(dayLeasingUserDTO.getGender());
    dayleasingUsers.setLname(dayLeasingUserDTO.getLname());
    // dayleasingUsers.setUserGuid(GenerateUUID.generateUUID());
    // dayleasingUsers.setUsername(dayLeasingUserDTO.getEmailId());
    dayleasingUsers.setZipCode(dayLeasingUserDTO.getZipCode());
    return dayleasingUsers;
  }

}
