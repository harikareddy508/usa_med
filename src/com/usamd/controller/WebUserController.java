/**
 * 
 */
package com.usamd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usamd.constants.GlobalConstants;
import com.usamd.constants.MessageConstants;
import com.usamd.constants.URLConstants;
import com.usamd.constants.ViewConstants;
import com.usamd.delegate.WebUserDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.validator.WebUserValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUserController.
 *
 * @author USAWebMD
 */
@Controller
public class WebUserController extends AbstractController {
  /** The user delegate. */
  @Autowired
  private WebUserDelegate webUserDelegate;

  /** The user validator. */
  @Autowired
  private WebUserValidator webUserValidator;


  /**
   * Inits the binder.
   *
   * @param binder the binder
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(webUserValidator);
  }

  /**
   * Display web user registration page.
   *
   * @param request the request
   * @param response the response
   * @param webUserBean the web user bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_REGISTER, method = RequestMethod.GET)
  public String displayRegister(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.WEB_USER_BEAN) WebUserBean webUserBean) {
    return ViewConstants.VIEW_REGISTER;
  }


  /**
   * Process add user.
   *
   * @param request the request
   * @param response the response
   * @param webUserBean the web user bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_REGISTER, method = RequestMethod.POST)
  public String processRegister(HttpServletRequest request, HttpServletResponse response,
      @Valid @ModelAttribute(value = AbstractBean.WEB_USER_BEAN) WebUserBean webUserBean,
      BindingResult results, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser != null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    if (!results.hasErrors()) {
      boolean isSuccess = webUserDelegate.addUser(webUserBean);
      if (isSuccess) {
        applicationLogger.debug(MessageConstants.ADD_USER_SUCCESS);
        webUserBean.setFullName();

        Object[] obj = {webUserBean.getUsername()};

        model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.ADD_USER_SUCCESS, obj, null));


      } else {
        applicationLogger.debug(MessageConstants.ADD_USER_FAILURE);
        model.addAttribute(
            GlobalConstants.FAILURE_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.ADD_USER_FAILURE,
                new Object[] {webUserBean.getEmailId()}, null));

      }
    }
    return ViewConstants.VIEW_REGISTER;
  }


  /**
   * Display user profile.
   *
   * @param request the request
   * @param response the response
   * @param webUserBean the web user bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_MY_PROFILE, method = RequestMethod.GET)
  public String displayUserProfile(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.WEB_USER_BEAN) WebUserBean webUserBean, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      // TODO to redirect logged in user directly to home page.
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    webUserBean = loggedUser;
    webUserBean.setRetypePassword(webUserBean.getPassword());
    model.addAttribute(AbstractBean.WEB_USER_BEAN, webUserBean);
    return ViewConstants.VIEW_MY_PROFILE;
  }


  /**
   * Process save profile.
   *
   * @param request the request
   * @param response the response
   * @param webUserBean the web user bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_MY_PROFILE, method = RequestMethod.POST)
  public String processSaveProfile(HttpServletRequest request, HttpServletResponse response,
      @Valid @ModelAttribute(value = AbstractBean.WEB_USER_BEAN) WebUserBean webUserBean,
      BindingResult results, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    if (!results.hasErrors()) {
      boolean isSuccess = webUserDelegate.updateMyProfile(webUserBean);
      if (isSuccess) {
        applicationLogger.debug(MessageConstants.ADD_USER_SUCCESS);
        webUserBean.setFullName();
        setLoggedUser(webUserBean, request);

        model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.EDIT_SUCCESS, null, null));
      } else {
        applicationLogger.debug(MessageConstants.EDIT_FAILURE);
        model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.EDIT_FAILURE, null, null));
      }
    }
    return ViewConstants.VIEW_MY_PROFILE;
  }

}
