/**
 * 
 */
package com.usamd.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usamd.constants.GlobalConstants;
import com.usamd.modelBean.AddressBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.service.AddressService;
import com.usamd.service.WebUserService;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUserDelegate.
 *
 * @author USAWebMD
 */
@Component("webUserDelegate")
public class WebUserDelegate {

  /** The web user service. */
  @Autowired
  private WebUserService webUserService;
  
  /** The address service. */
  @Autowired
  private AddressService addressService;

  /**
   * Adds the user.
   *
   * @param webUserBean the web user bean
   * @return true, if successful
   */
  public boolean addUser(WebUserBean webUserBean) {
    boolean isSuccess = false;
    // check if user is already registered
    if (webUserService.fetchUserByEmailId(webUserBean.getEmailId()) != null) {
      return isSuccess;
    }
    
    isSuccess = webUserService.addUser(webUserBean);
    //add address
    AddressBean address = webUserBean.getAddress();
    address.setResidentId(webUserBean.getUsername());
    address.setResidentType(GlobalConstants.RESIDENT_TYPE_USER);
    address.setCreateUser(webUserBean.getUsername());
    isSuccess = addressService.addAddress(address);
    return isSuccess;
  }

  /**
   * Update my profile.
   *
   * @param webUserBean the web user bean
   * @return true, if successful
   */
  public boolean updateMyProfile(WebUserBean webUserBean) {
    webUserBean.setUpdateUser(webUserBean.getUsername());
    AddressBean address = webUserBean.getAddress();
    System.out.println("Address line "+address.getAddrLine1());
    address.setResidentId(webUserBean.getUsername());
    address.setUpdateUser(webUserBean.getUsername());
    addressService.updateAddress(address);
    return webUserService.updateUser(webUserBean);
  }


}
