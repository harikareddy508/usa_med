/**
 * 
 */
package com.usamd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.usamd.modelBean.MedicineInfoBean;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginValidator.
 */

@Component("medicineValidator")
public class MedicineValidator implements Validator {


  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> arg0) {
    return MedicineInfoBean.class.isAssignableFrom(arg0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#validate(java.lang.Object,
   * org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medName", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uses", "NotEmpty.field");
  }
  
  
  /**
   * Validate search.
   *
   * @param bean the bean
   * @param errors the errors
   */
  public void validateSearch(MedicineInfoBean bean,Errors errors){
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medName", "NotEmpty.field");
  }


}
