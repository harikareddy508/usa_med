/**
 * 
 */
package com.usamd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.usamd.modelBean.MedicalCondtionBean;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginValidator.
 */

@Component("medicalConditionValidator")
public class MedicalConditionValidator implements Validator {


  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> arg0) {
    return MedicalCondtionBean.class.isAssignableFrom(arg0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#validate(java.lang.Object,
   * org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.field");

  }

}
