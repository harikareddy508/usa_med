/**
 * 
 */
package com.usamd.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usamd.modelBean.AddressBean;
import com.usamd.modelBean.LoginBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.service.AddressService;
import com.usamd.service.WebUserService;
import com.usamd.utilities.LoggerInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginDelegate.
 *
 * @author USAWebMD
 */
@Component("loginDelegate")
public class LoginDelegate implements LoggerInterface {

  /** The web user service. */
  @Autowired
  private WebUserService webUserService;
  
  /** The address service. */
  @Autowired
  private AddressService addressService;

  /**
   * Validate login details.
   *
   * @param loginBean the login bean
   * @return the web user bean
   */
  public WebUserBean validateLoginDetails(LoginBean loginBean) {
    WebUserBean userBean = null;
    boolean isAuthLogin = false;

    userBean = webUserService.fetchUserByUsername(loginBean.getUsername());
    AddressBean address = new AddressBean();
    address.setResidentId(userBean.getUsername());
    List<AddressBean> list=addressService.fetchAddress(address);
    if(list!=null && !list.isEmpty()){
      address = list.get(0);
      userBean.setAddress(address);
    }
    if (userBean != null) {
      String enteredPassword = String.valueOf(loginBean.getPassword());
      if (enteredPassword != null && !enteredPassword.isEmpty()
          && enteredPassword.equals(String.valueOf(userBean.getPassword()))) {
        userBean.setFullName();
        isAuthLogin = true;
        applicationLogger.debug("==========User Authenticated ==================");
      }
    }

    if (!isAuthLogin) {
      applicationLogger.debug("==========User NOT Authenticated ==================");
      userBean = null;
    }
    return userBean;
  }


  /**
   * Reset password.
   *
   * @param loginBean the login bean
   * @return true, if successful
   */
  public boolean resetPassword(LoginBean loginBean) {
    WebUserBean userBean = null;
    boolean isSuccess = false;


    userBean = webUserService.fetchUserByUsername(loginBean.getUsername());
    if (userBean != null) {
      String inputSecurityQuesCd = loginBean.getSecurityQuesCd();
      String inputSecurityAns = loginBean.getSecurityAns();
      if (inputSecurityQuesCd != null && inputSecurityQuesCd.equals(userBean.getSecurityQuesCd())
          && inputSecurityAns != null
          && inputSecurityAns.equalsIgnoreCase(userBean.getSecurityAns())) {
        webUserService.updatePassword(loginBean.getPassword(), loginBean.getUsername(),
            "PASSWORD RESET");
        isSuccess = true;
      }
    }

    return isSuccess;
  }
}
