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
import com.usamd.modelBean.DoctorBean;
import com.usamd.utilities.Utility;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUserValidator.
 *
 * @author USAWebMD
 */
@Component("doctorValidator")
public class DoctorValidator implements Validator {
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
    return DoctorBean.class.isAssignableFrom(arg0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#validate(java.lang.Object,
   * org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object target, Errors errors) {
    DoctorBean doctorBean = (DoctorBean) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.field");    
    

    // Date of birth validation
    validateDateOfPractice(doctorBean.getDateOfPractice(), errors);

    // email validation
    validateEmailId(doctorBean.getEmailId(), errors);
    validatePhoneNumber(doctorBean.getPhoneNumber(), errors);
    if(doctorBean.getHealthCenter().getCenterId()==null || doctorBean.getHealthCenter().getCenterId().isEmpty()){
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "healthCenter.centerName", "NotEmpty.field");
      validateAddress(doctorBean.getHealthCenter().getAddress(), errors);
    }
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
  private void validateDateOfPractice(Date dob, Errors errors) {
    System.out.println("DOB :::::::::::::: " + dob);
    if (dob == null) {
      errors.rejectValue("dateOfPractice", "NotEmpty.field");
    } else {
      Calendar cal = Calendar.getInstance();
      if (Utility.isBeforeDay(cal.getTime(), dob)) {
        errors.rejectValue("dateOfPractice", "date.futureDate");
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
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "healthCenter.address.addrLine1","NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "healthCenter.address.state","NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "healthCenter.address.city","NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "healthCenter.address.zipcode","NotEmpty.field");
    /*Map<String,List<RTStatesBean>> statesMap = ReferenceDataDelegate.getStates();
    if(!(address.getState()==null || address.getState().isEmpty())){
      List<RTStatesBean> beanList = statesMap.get(address.getState());
      for(RTStatesBean bean : beanList){
        if(bean.getCity().equals(address.getCity())){
          if(!bean.getZipcode().equals(address.getZipcode())){
            errors.rejectValue("healthCenter.address.zipcode", "zipcode.invalidZipcode");
            break;
          }
        }
      }*/
    }  
  



}
