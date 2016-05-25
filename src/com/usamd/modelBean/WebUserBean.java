/**
 * 
 */
package com.usamd.modelBean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.usamd.constants.GlobalConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUserBean.
 *
 * @author USAWebMD
 */
public class WebUserBean extends AbstractUserBean {
  /** The username. */
  private String username;

  /** The password. */
  private char[] password;
  
  /** The retype password. */
  private char[] retypePassword; // just for validation purpose

  /** The role cd. */
  private String roleCd;

  /** The security ques cd. */
  private String securityQuesCd;

  /** The security ans. */
  private String securityAns;

  /** The date of birth. */
  @DateTimeFormat(pattern = GlobalConstants.DATE_FORMAT_MM_DD_YYYY)
  private Date dateOfBirth;

  /** The gender cd. */
  private String genderCd;

  /** The address. */
  private AddressBean address;



  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username.
   *
   * @param username the new username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public char[] getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password the new password
   */
  public void setPassword(char[] password) {
    this.password = password;
  }

  /**
   * Gets the retype password.
   *
   * @return the retypePassword
   */
  public char[] getRetypePassword() {
    return retypePassword;
  }

  /**
   * Sets the retype password.
   *
   * @param retypePassword the retypePassword to set
   */
  public void setRetypePassword(char[] retypePassword) {
    this.retypePassword = retypePassword;
  }

  /**
   * Gets the role cd.
   *
   * @return the role cd
   */
  public String getRoleCd() {
    return roleCd;
  }

  /**
   * Sets the role cd.
   *
   * @param roleCd the new role cd
   */
  public void setRoleCd(String roleCd) {
    this.roleCd = roleCd;
  }

  /**
   * Gets the security ques cd.
   *
   * @return the security ques cd
   */
  public String getSecurityQuesCd() {
    return securityQuesCd;
  }

  /**
   * Sets the security ques cd.
   *
   * @param securityQuesCd the new security ques cd
   */
  public void setSecurityQuesCd(String securityQuesCd) {
    this.securityQuesCd = securityQuesCd;
  }

  /**
   * Gets the security ans.
   *
   * @return the security ans
   */
  public String getSecurityAns() {
    return securityAns;
  }

  /**
   * Sets the security ans.
   *
   * @param securityAns the new security ans
   */
  public void setSecurityAns(String securityAns) {
    this.securityAns = securityAns;
  }

  /**
   * Gets the date of birth.
   *
   * @return the dateOfBirth
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Sets the date of birth.
   *
   * @param dateOfBirth the dateOfBirth to set
   */
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * Gets the gender cd.
   *
   * @return the genderCd
   */
  public String getGenderCd() {
    return genderCd;
  }

  /**
   * Sets the gender cd.
   *
   * @param genderCd the genderCd to set
   */
  public void setGenderCd(String genderCd) {
    this.genderCd = genderCd;
  }

  /**
   * Gets the address.
   *
   * @return the address
   */
  public AddressBean getAddress() {
    return address;
  }

  /**
   * Sets the address.
   *
   * @param address the address to set
   */
  public void setAddress(AddressBean address) {
    this.address = address;
  }
}
