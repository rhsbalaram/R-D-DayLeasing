package com.dayLeasing.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

// TODO: Auto-generated Javadoc

/**
 * The Class DayLeasingUserDTO.
 *
 * @author Balaram
 */
public class DayLeasingUserDTO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The fname. */
  @NotNull
  private String fname;

  /** The mname. */
  private String mname;

  /** The lname. */
  @NotNull
  private String lname;

  /** The password. */
  @NotNull
  private String password;

  /** The dob. */
 
  private Instant dob;

  /** The gender. */
  private Boolean gender;

  /** The address 1. */
 
  private String address1;

  /** The address 2. */
  private String address2;

  /** The state. */
  
  private String state;

  /** The city. */
  
  private String city;

  /** The zip code. */
  
  private String zipCode;

  /** The country. */
  
  private String country;

  /** The member type. */
  private String memberType;

  /** The email id. */
  @NotNull
  private String emailId;

  /** The user class. */
  private String userClass;

  /** The phone. */
  private String phone;

  /** The guest of. */
  private String guestOf;

  /** The hunt date. */
  
  private Instant huntDate;

  /** The vehicle. */
  private String vehicle;

  /** The vehicle color. */
  private String vehicleColor;

  /** The vehicle license. */
  private String vehicleLicense;

  /** The hunting license. */
  private String huntingLicense;

  /** The safety course. */
  private String safetyCourse;

  /** The starting field. */
  private String startingField;

  /** The current field. */
  private String currentField;

  /** The photo. */
  private String photo;

  /** The source. */
  private String source;

  /** The created date. */
  
  private Instant createdDate;

  /**
   * Gets the fname.
   *
   * @return the fname
   */
  public String getFname() {
    return fname;
  }

  /**
   * Sets the fname.
   *
   * @param fname
   *          the new fname
   */
  public void setFname(String fname) {
    this.fname = fname;
  }

  /**
   * Gets the mname.
   *
   * @return the mname
   */
  public String getMname() {
    return mname;
  }

  /**
   * Sets the mname.
   *
   * @param mname
   *          the new mname
   */
  public void setMname(String mname) {
    this.mname = mname;
  }

  /**
   * Gets the lname.
   *
   * @return the lname
   */
  public String getLname() {
    return lname;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password
   *          the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Sets the lname.
   *
   * @param lname
   *          the new lname
   */
  public void setLname(String lname) {
    this.lname = lname;
  }

  /**
   * Gets the dob.
   *
   * @return the dob
   */
  public Instant getDob() {
    return dob;
  }

  /**
   * Sets the dob.
   *
   * @param dob
   *          the new dob
   */
  public void setDob(Instant dob) {
    this.dob = dob;
  }

  /**
   * Gets the gender.
   *
   * @return the gender
   */
  public Boolean getGender() {
    return gender;
  }

  /**
   * Sets the gender.
   *
   * @param gender
   *          the new gender
   */
  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  /**
   * Gets the address 1.
   *
   * @return the address 1
   */
  public String getAddress1() {
    return address1;
  }

  /**
   * Sets the address 1.
   *
   * @param address1
   *          the new address 1
   */
  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  /**
   * Gets the address 2.
   *
   * @return the address 2
   */
  public String getAddress2() {
    return address2;
  }

  /**
   * Sets the address 2.
   *
   * @param address2
   *          the new address 2
   */
  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  /**
   * Gets the state.
   *
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state.
   *
   * @param state
   *          the new state
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets the city.
   *
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city.
   *
   * @param city
   *          the new city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Gets the zip code.
   *
   * @return the zip code
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Sets the zip code.
   *
   * @param zipCode
   *          the new zip code
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Gets the country.
   *
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country.
   *
   * @param country
   *          the new country
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Gets the member type.
   *
   * @return the member type
   */
  public String getMemberType() {
    return memberType;
  }

  /**
   * Sets the member type.
   *
   * @param memberType
   *          the new member type
   */
  public void setMemberType(String memberType) {
    this.memberType = memberType;
  }

  /**
   * Gets the email id.
   *
   * @return the email id
   */
  public String getEmailId() {
    return emailId;
  }

  /**
   * Sets the email id.
   *
   * @param emailId
   *          the new email id
   */
  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  /**
   * Gets the user class.
   *
   * @return the user class
   */
  public String getUserClass() {
    return userClass;
  }

  /**
   * Sets the user class.
   *
   * @param userClass
   *          the new user class
   */
  public void setUserClass(String userClass) {
    this.userClass = userClass;
  }

  /**
   * Gets the phone.
   *
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets the phone.
   *
   * @param phone
   *          the new phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Gets the guest of.
   *
   * @return the guest of
   */
  public String getGuestOf() {
    return guestOf;
  }

  /**
   * Sets the guest of.
   *
   * @param guestOf
   *          the new guest of
   */
  public void setGuestOf(String guestOf) {
    this.guestOf = guestOf;
  }

  /**
   * Gets the hunt date.
   *
   * @return the hunt date
   */
  public Instant getHuntDate() {
    return huntDate;
  }

  /**
   * Sets the hunt date.
   *
   * @param huntDate
   *          the new hunt date
   */
  public void setHuntDate(Instant huntDate) {
    this.huntDate = huntDate;
  }

  /**
   * Gets the vehicle.
   *
   * @return the vehicle
   */
  public String getVehicle() {
    return vehicle;
  }

  /**
   * Sets the vehicle.
   *
   * @param vehicle
   *          the new vehicle
   */
  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }

  /**
   * Gets the vehicle color.
   *
   * @return the vehicle color
   */
  public String getVehicleColor() {
    return vehicleColor;
  }

  /**
   * Sets the vehicle color.
   *
   * @param vehicleColor
   *          the new vehicle color
   */
  public void setVehicleColor(String vehicleColor) {
    this.vehicleColor = vehicleColor;
  }

  /**
   * Gets the vehicle license.
   *
   * @return the vehicle license
   */
  public String getVehicleLicense() {
    return vehicleLicense;
  }

  /**
   * Sets the vehicle license.
   *
   * @param vehicleLicense
   *          the new vehicle license
   */
  public void setVehicleLicense(String vehicleLicense) {
    this.vehicleLicense = vehicleLicense;
  }

  /**
   * Gets the hunting license.
   *
   * @return the hunting license
   */
  public String getHuntingLicense() {
    return huntingLicense;
  }

  /**
   * Sets the hunting license.
   *
   * @param huntingLicense
   *          the new hunting license
   */
  public void setHuntingLicense(String huntingLicense) {
    this.huntingLicense = huntingLicense;
  }

  /**
   * Gets the safety course.
   *
   * @return the safety course
   */
  public String getSafetyCourse() {
    return safetyCourse;
  }

  /**
   * Sets the safety course.
   *
   * @param safetyCourse
   *          the new safety course
   */
  public void setSafetyCourse(String safetyCourse) {
    this.safetyCourse = safetyCourse;
  }

  /**
   * Gets the starting field.
   *
   * @return the starting field
   */
  public String getStartingField() {
    return startingField;
  }

  /**
   * Sets the starting field.
   *
   * @param startingField
   *          the new starting field
   */
  public void setStartingField(String startingField) {
    this.startingField = startingField;
  }

  /**
   * Gets the current field.
   *
   * @return the current field
   */
  public String getCurrentField() {
    return currentField;
  }

  /**
   * Sets the current field.
   *
   * @param currentField
   *          the new current field
   */
  public void setCurrentField(String currentField) {
    this.currentField = currentField;
  }

  /**
   * Gets the photo.
   *
   * @return the photo
   */
  public String getPhoto() {
    return photo;
  }

  /**
   * Sets the photo.
   *
   * @param photo
   *          the new photo
   */
  public void setPhoto(String photo) {
    this.photo = photo;
  }

  /**
   * Gets the source.
   *
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets the source.
   *
   * @param source
   *          the new source
   */
  public void setSource(String source) {
    this.source = source;
  }

  /**
   * Gets the created date.
   *
   * @return the created date
   */
  public Instant getCreatedDate() {
    return createdDate;
  }

  /**
   * Sets the created date.
   *
   * @param createdDate
   *          the new created date
   */
  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

}
