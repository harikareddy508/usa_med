/**
 * 
 */
package com.usamd.modelBean;

import com.usamd.constants.GlobalConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractUserBean.
 *
 * @author USAWebMD
 */
public abstract class AbstractUserBean extends AbstractBean {
  /** The first name. */
  private String firstName;

  /** The last name. */
  private String lastName;

  /** The email id. */
  private String emailId;

  /** The phone number. */
  private String phoneNumber;



  /** Custom field to store full name of the user. */
  private String fullName;

  /**
   * Gets the first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name.
   *
   * @param firstName the new first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }



  /**
   * Gets the last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name.
   *
   * @param lastName the new last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
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
   * @param emailId the new email id
   */
  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }



  /**
   * Gets the full name.
   *
   * @return the full name
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Sets the full name.
   *
   * @param fullName the new full name
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }



  /**
   * Gets the phone number.
   *
   * @return the phone number
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the phone number.
   *
   * @param phoneNumber the new phone number
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Sets the full name.
   */
  public void setFullName() {
    StringBuilder fullNameStr = new StringBuilder(this.firstName);

    if (this.lastName != null && !this.lastName.isEmpty()) {
      fullNameStr.append(GlobalConstants.SPACE_STRING);
      fullNameStr.append(this.lastName);
    }
    this.fullName = fullNameStr.toString();
  }
}
