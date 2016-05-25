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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usamd.constants.GlobalConstants;
import com.usamd.constants.MessageConstants;
import com.usamd.constants.URLConstants;
import com.usamd.constants.ViewConstants;
import com.usamd.delegate.LoginDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.LoginBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.validator.LoginValidator;

// TODO: Auto-generated Javadoc
/**
 * This class will handle all the operation related to login module which include displaying login
 * details, Validating login details and navigating to another controller.
 * 
 *
 * @author USAWebMD
 */
@Controller("loginController")
public class LoginController extends AbstractController {

  /** The login delegate. */
  @Autowired
  private LoginDelegate loginDelegate;

  /** The login validator. */
  @Autowired
  private LoginValidator loginValidator;

  /**
   * Init binder.
   *
   * @param binder the binder
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(loginValidator);
  }



  /**
   * Display login.
   *
   * @param request the request
   * @param response the response
   * @param loginBean the login bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_LOGIN, method = RequestMethod.GET)
  public String displayLogin(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.LOGIN_BEAN) LoginBean loginBean) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser != null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    return ViewConstants.VIEW_LOGIN;
  }

  /**
   * This method validates the login credentials entered by the user.
   *
   * @param request the request
   * @param response the response
   * @param loginBean the login bean
   * @param bindingReults the binding reults
   * @param redirectAttributes the redirect attributes
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_LOGIN, method = RequestMethod.POST)
  public String processLogin(HttpServletRequest request, HttpServletResponse response,
      @Valid @ModelAttribute(value = AbstractBean.LOGIN_BEAN) LoginBean loginBean,
      BindingResult bindingReults, RedirectAttributes redirectAttributes) {
    // TODO : Session handling
    // to redirect logged in user directly to home page.
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser != null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }

    if (bindingReults.hasErrors()) {
      return ViewConstants.VIEW_LOGIN;
    }
    WebUserBean validatedUser = loginDelegate.validateLoginDetails(loginBean);
    // return "homePage";
    if (validatedUser == null) {
      redirectAttributes.addFlashAttribute(GlobalConstants.FAILURE_MESSAGE, this.getMessageSource()
          .getMessage(MessageConstants.LOGIN_FAILURE, null, null));
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_LOGIN;

    } else {
      // Set the logged user in session
      request.getSession().setAttribute(GlobalConstants.LOGGED_USER, validatedUser);
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;

    }
  }

  /**
   * Process logout session.
   *
   * @param request the request
   * @param response the response
   * @param redirectAttributes the redirect attributes
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_LOGOUT, method = RequestMethod.GET)
  public String processLogoutSession(HttpServletRequest request, HttpServletResponse response,
      RedirectAttributes redirectAttributes) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser != null) {
      // invalidate session
      request.getSession().invalidate();
      redirectAttributes.addFlashAttribute(GlobalConstants.SUCCESS_MESSAGE, this.getMessageSource()
          .getMessage(MessageConstants.LOGOUT_SUCCESS, null, null));
    }

    return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
  }


  /**
   * Display forgot password.
   *
   * @param request the request
   * @param response the response
   * @param loginBean the login bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_FORGOT_PASSWORD, method = RequestMethod.GET)
  public String displayForgotPassword(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.LOGIN_BEAN) LoginBean loginBean) {
    return ViewConstants.VIEW_FORGOT_PASSWORD;
  }


  /**
   * Process forgot password.
   *
   * @param request the request
   * @param response the response
   * @param loginBean the login bean
   * @param bindingReults the binding reults
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_FORGOT_PASSWORD, method = RequestMethod.POST)
  public String processForgotPassword(HttpServletRequest request, HttpServletResponse response,
      @Valid @ModelAttribute(value = AbstractBean.LOGIN_BEAN) LoginBean loginBean,
      BindingResult bindingReults, Model model) {
    if (!bindingReults.hasErrors()) {
      boolean isPassResetSuccess = loginDelegate.resetPassword(loginBean);
      if (isPassResetSuccess) {
        model
            .addAttribute(
                GlobalConstants.SUCCESS_MESSAGE,
                this.getMessageSource().getMessage(MessageConstants.PASSWORD_RESET_SUCCESS, null,
                    null));
        model.addAttribute(AbstractBean.LOGIN_BEAN, new LoginBean());
      } else {
        model
            .addAttribute(
                GlobalConstants.FAILURE_MESSAGE,
                this.getMessageSource().getMessage(MessageConstants.PASSWORD_RESET_FAILURE, null,
                    null));
      }
    }
    return ViewConstants.VIEW_FORGOT_PASSWORD;
  }



}
