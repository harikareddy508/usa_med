package com.usamd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.WebUserDAO;
import com.usamd.modelBean.WebUserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUserService.
 *
 * @author USAWebMD
 */
@Service("webUserService")
public class WebUserService {
  /** The user dao. */
  @Autowired
  private WebUserDAO userDAO;

  /**
   * Adds the user.
   *
   * @param userBean the user bean
   * @return true, if successful
   */
  public boolean addUser(WebUserBean userBean) {
    boolean isSuccess = userDAO.addUser(userBean);
    return isSuccess;
  }

  /**
   * Update user.
   *
   * @param userBean the user bean
   * @return true, if successful
   */
  public boolean updateUser(WebUserBean userBean) {
    boolean isSuccess = userDAO.updateUser(userBean);
    return isSuccess;
  }

  /**
   * Delete user details.
   *
   * @param username the username
   * @return true, if successful
   */
  public boolean deleteUserDetails(String username) {
    boolean isSuccess = userDAO.deleteUserDetails(username);
    return isSuccess;
  }



  /**
   * Update password.
   *
   * @param password the password
   * @param username the username
   * @param updateUser the update user
   * @return true, if successful
   */
  public boolean updatePassword(char[] password, String username, String updateUser) {
    boolean isSuccess = userDAO.updatePassword(password, username, updateUser);
    return isSuccess;
  }

  /**
   * Validate login details.
   *
   * @param username the username
   * @return the user bean
   */
  public WebUserBean fetchUserByUsername(String username) {
    WebUserBean userBean = userDAO.fetchUserByUsername(username);
    return userBean;
  }
  
  /**
   * Fetch user by email id.
   *
   * @param emailId the email id
   * @return the web user bean
   */
  public WebUserBean fetchUserByEmailId(String emailId) {
    WebUserBean userBean = userDAO.fetchUserByEmailId(emailId);
    return userBean;
  }

  /**
   * Fetch users.
   *
   * @param userBean the user bean
   * @return the list
   */
  public List<WebUserBean> fetchUsers(WebUserBean userBean) {
    return userDAO.fetchUsers(userBean);
  }

}
