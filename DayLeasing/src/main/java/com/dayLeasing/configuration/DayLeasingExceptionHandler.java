package com.dayLeasing.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
// TODO: Auto-generated Javadoc

/**
 * The Class DayLeasingExceptionHandler.
 *
 * @author Balaram
 */
@ControllerAdvice
public class DayLeasingExceptionHandler {

  /**
   * Handle invalid request.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(value = InValidRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<ErrorMessage> handleInvalidRequest(InValidRequestException exception) {
    ErrorMessage errorMessage = new ErrorMessage(exception);

    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
  }

}
