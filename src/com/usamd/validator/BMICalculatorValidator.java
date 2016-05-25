package com.usamd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.usamd.modelBean.BMICalViewBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BMICalculatorValidator.
 */
@Component("bmiCalculatorValidator")
public class BMICalculatorValidator implements Validator {

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> arg0) {
    return BMICalViewBean.class.isAssignableFrom(arg0);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object bmiCalViewBeanObj, Errors errors) {
    
    BMICalViewBean bmiCalViewBean = (BMICalViewBean) bmiCalViewBeanObj;
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heightFeet", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heightInch", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "NotEmpty.field");
    
    if(bmiCalViewBean.getWeight() != null && !bmiCalViewBean.getWeight().isEmpty()) {
      try {
        double weight = Double.valueOf(bmiCalViewBean.getWeight());
        if(weight <= 10.0) {
          errors.rejectValue("weight","Incorrect.bmiCalViewBean.weight");
        }
      } catch (Exception e) {
        errors.rejectValue("weight","Invalid.field");
      }
    }
    
    if(bmiCalViewBean.getHeightFeet() != null && !bmiCalViewBean.getHeightFeet().isEmpty()) {
      try {
        int heightFeet = Integer.valueOf(bmiCalViewBean.getHeightFeet())*12;
        int heightInch = Integer.valueOf(bmiCalViewBean.getHeightInch());
        if((heightFeet + heightInch)  <= 10.0) {
          errors.rejectValue("heightFeet","Incorrect.bmiCalViewBean.height");
        }
      } catch (Exception e) {
        errors.rejectValue("heightFeet","Invalid.field");
      }
    }
    
    if(bmiCalViewBean.getAge() != null && !bmiCalViewBean.getAge().isEmpty()) {
      try {
        int age = Integer.valueOf(bmiCalViewBean.getAge());
        if((age)  <= 1.0) {
          errors.rejectValue("age","Incorrect.bmiCalViewBean.age");
        }
      } catch (Exception e) {
        errors.rejectValue("age","Invalid.field");
      }
    }
    
  }

}
