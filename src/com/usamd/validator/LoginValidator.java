/**
 * 
 */
package com.usamd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.usamd.modelBean.LoginBean;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginValidator.
 */

@Component("loginValidator")
public class LoginValidator implements Validator {


  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> arg0) {
    return LoginBean.class.isAssignableFrom(arg0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#validate(java.lang.Object,
   * org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.field");
    LoginBean loginBean = (LoginBean) target;
    boolean passwordRejected = validatePassword(loginBean.getPassword(), errors,loginBean.getValidationType());

    if (loginBean.getValidationType() == 1) {
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityQuesCd", "NotEmpty.field");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityAns", "NotEmpty.field");
      if (!passwordRejected) {
        validateRetypePassword(loginBean.getPassword(), loginBean.getRetypePassword(), errors);
      }
    }


  }

  /**
   * Validate password.
   *
   * @param password the password
   * @param errors the errors
   * @param valType the val type
   * @return true, if successful
   */
  private boolean validatePassword(char[] password, Errors errors,int valType) {
    String strPassword = String.valueOf(password);
    // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.field");
    boolean rejected = false;
    if (strPassword == null || strPassword.trim().isEmpty()) {
      errors.rejectValue("password", "NotEmpty.field");
      rejected = true;
    } else if (valType==1 && strPassword.length() < 6) {
      errors.rejectValue("password", "password.length.lessThan6");
      rejected = true;
    }
    return rejected;
  }

  /**
   * Validate password.
   *
   * @param password the password
   * @param retypePassword the retype password
   * @param errors the errors
   */
  private void validateRetypePassword(char[] password, char[] retypePassword, Errors errors) {
    String strPassword = String.valueOf(password);

    String strRetypePassword = String.valueOf(retypePassword);
    if (!strPassword.equals(strRetypePassword)) {
      errors.rejectValue("password", "password.confirm.invalid");
      errors.rejectValue("retypePassword", "password.confirm.invalid");

    }

  }
}
