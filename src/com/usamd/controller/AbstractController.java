/**
 * 
 */
package com.usamd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.usamd.constants.GlobalConstants;
import com.usamd.modelBean.WebUserBean;
import com.usamd.utilities.LoggerInterface;
import com.usamd.utilities.MessageHelper;

// TODO: Auto-generated Javadoc
/**
 * This class will acts as Parent class for all the Controller classes and provide interface for
 * logging and reading data from properties files.
 * 
 * @author USAWebMD
 *
 */
public abstract class AbstractController implements LoggerInterface {

  /** The message source. */
  @Autowired
  private MessageSource messageSource;


  /** The message helper. */
  private MessageHelper messageHelper = new MessageHelper();

  /**
   * Gets the message source.
   *
   * @return the messageSource
   */
  public MessageSource getMessageSource() {
    return messageSource;
  }

  /**
   * Sets the message source.
   *
   * @param messageSource the messageSource to set
   */
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * Gets the message helper.
   *
   * @return the messageHelper
   */
  public MessageHelper getMessageHelper() {
    return messageHelper;
  }

  /**
   * Sets the message helper.
   *
   * @param messageHelper the messageHelper to set
   */
  public void setMessageHelper(MessageHelper messageHelper) {
    this.messageHelper = messageHelper;
  }



  /**
   * Gets the logged user.
   *
   * @param request the request
   * @return the logged user
   */
  public WebUserBean getLoggedUser(HttpServletRequest request) {
    if (request.getSession() != null) {
      if (request.getSession().getAttribute(GlobalConstants.LOGGED_USER) != null) {
        WebUserBean loggedUser =
            (WebUserBean) request.getSession().getAttribute(GlobalConstants.LOGGED_USER);
        return loggedUser;
      }
    }
    return null;
  }

  /**
   * Sets the logged user.
   *
   * @param loggedUser the logged user
   * @param request the request
   */
  public void setLoggedUser(WebUserBean loggedUser,HttpServletRequest request) {
    if (request.getSession() != null) {
      if (request.getSession().getAttribute(GlobalConstants.LOGGED_USER) != null) {
           request.getSession().setAttribute(GlobalConstants.LOGGED_USER,loggedUser);
       
      }
    }
  }


}
