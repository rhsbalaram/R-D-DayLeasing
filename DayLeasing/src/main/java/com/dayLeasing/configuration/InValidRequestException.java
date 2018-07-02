package com.dayLeasing.configuration;
// TODO: Auto-generated Javadoc

/**s
 * The Class InValidRequestException.
 *
 * @author Balaram
 */
public class InValidRequestException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The error message. */
  private String errorMessage;

  /**
   * Instantiates a new in valid request exception.
   *
   * @param errorMsg the error msg
   */
  public InValidRequestException(String errorMsg) {
    super(errorMsg);
    this.errorMessage = errorMsg;
  }

  /**
   * Gets the error message.
   *
   * @return the error message
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Sets the error message.
   *
   * @param errorMessage the new error message
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
