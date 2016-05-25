/**
 * 
 */
package com.usamd.validator;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.usamd.modelBean.AddressBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.utilities.Utility;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUserValidator.
 *
 * @author USAWebMD
 */
@Component("webUserValidator")
public class WebUserValidator implements Validator {
  /** The email validator. */
  @Autowired
  private EmailValidator emailValidator;

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> arg0) {
    return WebUserBean.class.isAssignableFrom(arg0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#validate(java.lang.Object,
   * org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object target, Errors errors) {
    WebUserBean userBean = (WebUserBean) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genderCd", "NotEmpty.field");

    // Date of birth validation
    validateDateOfBirth(userBean.getDateOfBirth(), errors);

    // email validation
    validateEmailId(userBean.getEmailId(), errors);
    validatePassword(userBean.getPassword(),userBean.getRetypePassword(), errors);
    validatePhoneNumber(userBean.getPhoneNumber(), errors);

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityQuesCd", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityAns", "NotEmpty.field");
    validateAddress(userBean.getAddress(),errors);

  }


  /**
   * Validate phone number.
   *
   * @param phoneNumber the phone number
   * @param errors the errors
   */
  private void validatePhoneNumber(String phoneNumber, Errors errors) {
    if (phoneNumber != null && !phoneNumber.isEmpty()) {
      try {
        new BigInteger(phoneNumber);
      } catch (NumberFormatException ex) {
        errors.rejectValue("phoneNumber", "phoneNumber.invalid");
      }
    }
  }

  /**
   * Validate date of birth.
   *
   * @param dob the dob
   * @param errors the errors
   */
  private void validateDateOfBirth(Date dob, Errors errors) {
    System.out.println("DOB :::::::::::::: "+dob);
    if (dob == null) {
      errors.rejectValue("dateOfBirth", "NotEmpty.field");
    } else {
      Calendar cal = Calendar.getInstance();
      if (Utility.isBeforeDay(cal.getTime(), dob)) {
        errors.rejectValue("dateOfBirth", "date.futureDate");
      }
    }
  }


  /**
   * Validate password.
   *
   * @param password the password
   * @param retypePassword the retype password
   * @param errors the errors
   */
  private void validatePassword(char[] password,char[] retypePassword, Errors errors) {
    String strPassword = String.valueOf(password);
    // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.field");
    if (strPassword == null || strPassword.trim().isEmpty()) {
      errors.rejectValue("password", "NotEmpty.field");
    } else if (strPassword.length() < 6) {
        errors.rejectValue("password", "password.length.lessThan6");
      }
    else{
      String strRetypePassword = String.valueOf(retypePassword);
      if(!strPassword.equals(strRetypePassword)){
        errors.rejectValue("password", "password.confirm.invalid");
        errors.rejectValue("retypePassword", "password.confirm.invalid");
      }
    }
  }


  /**
   * Validates emailId. If email Id is entered, it should be valid email Address.
   *
   * @param emailId the email id
   * @param errors the errors
   */
  private void validateEmailId(String emailId, Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "NotEmpty.field");

    if (emailId.trim().length() > 0 && !emailValidator.valid(emailId)) {
      errors.rejectValue("emailId", "email.invalidFormat");
    }
  }
  
  
  /**
   * Validate address.
   *
   * @param address the address
   * @param errors the errors
   */
  private void validateAddress(AddressBean address, Errors errors){
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.addrLine1","NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.state","NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city","NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.zipcode","NotEmpty.field");    
  }



}
