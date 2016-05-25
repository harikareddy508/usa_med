/**
 * 
 */
package com.usamd.exception;

// TODO: Auto-generated Javadoc
/**
 * This class will handle all the custom exception related to database operation.
 *
 */
public class DataException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new data exception.
   */
  public DataException() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Instantiates a new data exception.
   *
   * @param message the message
   * @param cause the cause
   * @param enableSuppression the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  public DataException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  /**
   * Instantiates a new data exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public DataException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  /**
   * Instantiates a new data exception.
   *
   * @param message the message
   */
  public DataException(String message) {
    super(message);
  }

  /**
   * Instantiates a new data exception.
   *
   * @param cause the cause
   */
  public DataException(Throwable cause) {
    super(cause);
  }



}
