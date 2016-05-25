package com.usamd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.usamd.modelBean.BMICategoryBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BMICategoryValidator.
 */
@Component("bmiCategoryValidator")
public class BMICategoryValidator implements Validator {

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> arg0) {
    return BMICategoryBean.class.isAssignableFrom(arg0);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object bmiCatViewObj, Errors errors) {    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bodyType", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "percentileRangeLow", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "percentileRangeHigh", "NotEmpty.field");
   
    
  }

}
