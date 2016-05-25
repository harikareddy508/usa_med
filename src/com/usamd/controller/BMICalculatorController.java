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
import com.usamd.constants.URLConstants;
import com.usamd.constants.ViewConstants;
import com.usamd.delegate.BMIDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.BMICalViewBean;
import com.usamd.modelBean.BMICategoryBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.validator.BMICalculatorValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class BMICalculatorController.
 *
 * @author USAWebMD
 */
@Controller("bmiCalculatorController")
public class BMICalculatorController  extends AbstractController{

 

  /** The bmi calculator validator. */
  @Autowired
  private BMICalculatorValidator bmiCalculatorValidator;

  /** The bmi delegate. */
  @Autowired
  private BMIDelegate bmiDelegate;

  /**
   * Inits the binder.
   *
   * @param binder the binder
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(bmiCalculatorValidator);
  }

  /**
   * Display bmi calculator.
   *
   * @param request the request
   * @param response the response
   * @param bmiCalViewBean the bmi cal view bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_CALCULATE_BMI, method = RequestMethod.GET)
  public String displayBMICalculator(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.BMI_CAL_VIEW_BEAN) BMICalViewBean bmiCalViewBean, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      // to redirect logged in user directly to home page.
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    return ViewConstants.VIEW_CALCULATE_BMI;
  }

  /**
   * Calculate bmi.
   *
   * @param request the request
   * @param response the response
   * @param bmiCalViewBean the bmi cal view bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_CALCULATE_BMI, method = RequestMethod.POST)
  public String calculateBMI(HttpServletRequest request, HttpServletResponse response,
      @Valid @ModelAttribute(value = AbstractBean.BMI_CAL_VIEW_BEAN) BMICalViewBean bmiCalViewBean,
      BindingResult results, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      // To redirect logged in user directly to home page.
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    if (!results.hasErrors()) {
      String bmi = bmiDelegate.getBMIResults(bmiCalViewBean);
      BMICategoryBean bean = bmiDelegate.fetchCategoryByBMI(bmi,bmiCalViewBean.getAge());
      model.addAttribute(GlobalConstants.BMI,bmi);
      model.addAttribute(GlobalConstants.CATEGORY,bean!=null?bean.getCategory():"");
      model.addAttribute(GlobalConstants.HEALTH_PLAN,bean!=null?bean.getHealthPlan():"");
    }
    return ViewConstants.VIEW_CALCULATE_BMI;
  }




}
