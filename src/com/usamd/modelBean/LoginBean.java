package com.usamd.modelBean;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginBean.
 */
@Component
public class LoginBean {

  /** The user id. */
  private String username;

  /** The password. */
  private char[] password;

  /** The retype password. */
  private char[] retypePassword;

  /** The security ques cd. */
  private String securityQuesCd;

  /** The security ans. */
  private String securityAns;

  /** The validation type. */
  private int validationType = 0; // 0 for Login, 1 for Password reset.


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
   * Gets the retype password.
   *
   * @return the retype password
   */
  public char[] getRetypePassword() {
    return retypePassword;
  }

  /**
   * Sets the retype password.
   *
   * @param retypePassword the new retype password
   */
  public void setRetypePassword(char[] retypePassword) {
    this.retypePassword = retypePassword;
  }

  /**
   * Gets the validation type.
   *
   * @return the validation type
   */
  public int getValidationType() {
    return validationType;
  }

  /**
   * Sets the validation type.
   *
   * @param validationType the new validation type
   */
  public void setValidationType(int validationType) {
    this.validationType = validationType;
  }



}
