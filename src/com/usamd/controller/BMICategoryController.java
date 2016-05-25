/**
 * 
 */
package com.usamd.controller;

import java.util.List;

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
import com.usamd.delegate.BMIDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.BMICategoryBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.validator.BMICategoryValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class BMICategoryController.
 *
 * @author USAWebMD
 */
@Controller("bmiCategoryController")
public class BMICategoryController  extends AbstractController{

  /** The bmi category validator. */
 

  @Autowired
  private BMICategoryValidator bmiCategoryValidator;
  
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
    binder.setValidator(bmiCategoryValidator);
  }
  
  /**
   * Display configure bmi.
   *
   * @param request the request
   * @param response the response
   * @param bmiCategoryBean the bmi category bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_CONFIGURE_BMI, method = RequestMethod.GET)
  public String displayConfigureBMI(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.BMI_CATEGORY_BEAN) BMICategoryBean bmiCategoryBean, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      // to redirect logged in user directly to home page.
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    return ViewConstants.VIEW_CONFIGURE_BMI;
  }
  
  
  /**
   * Process configure bmi.
   *
   * @param request the request
   * @param response the response
   * @param bmiCategoryBean the bmi category bean
   * @param result the result
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_CONFIGURE_BMI, method = RequestMethod.POST)
  public String processConfigureBMI(HttpServletRequest request, HttpServletResponse response,
     @Valid @ModelAttribute(value = AbstractBean.BMI_CATEGORY_BEAN) BMICategoryBean bmiCategoryBean,BindingResult result, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      // to redirect logged in user directly to home page.
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    if(result.hasErrors()){
      return ViewConstants.VIEW_CONFIGURE_BMI;
    }
    int isSuccess = bmiDelegate.addOrUpdateBMICategory(bmiCategoryBean);
    if (isSuccess==0) {
      model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.EDIT_SUCCESS, null, null));
    }else if(isSuccess==1){
      model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.OVERLAPPING_BMI_RANGE, null, null));
    }
    else {
      model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.EDIT_FAILURE, null, null));
    }
    if(bmiCategoryBean.getBodyType()!=null && !bmiCategoryBean.getBodyType().isEmpty()){
      List<BMICategoryBean> list = bmiDelegate.fetchByBodyType(bmiCategoryBean.getBodyType(),null);
      model.addAttribute(GlobalConstants.CATEGORY_LIST,list);
    }
    return ViewConstants.VIEW_CONFIGURE_BMI;
  }
  
  /**
   * Fetch all bmi category.
   *
   * @param request the request
   * @param response the response
   * @param bmiCategoryBean the bmi category bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_FETCH_BMI_CATEGORY, method = RequestMethod.POST)
  public String fetchAllBMICategory(HttpServletRequest request, HttpServletResponse response,
     @ModelAttribute(value = AbstractBean.BMI_CATEGORY_BEAN) BMICategoryBean bmiCategoryBean,Model model){
     
    List<BMICategoryBean> list = bmiDelegate.fetchByBodyType(bmiCategoryBean.getBodyType(),null);
    if(bmiCategoryBean.getCategory()!=null && !bmiCategoryBean.getCategory().isEmpty()){
      List<BMICategoryBean> catList=bmiDelegate.fetchByBodyType(bmiCategoryBean.getBodyType(),bmiCategoryBean.getCategory());
      if(catList!=null && catList.size()>0){
        bmiCategoryBean = catList.get(0);
      }
    }   
    model.addAttribute(AbstractBean.BMI_CATEGORY_BEAN, bmiCategoryBean);
    model.addAttribute(GlobalConstants.CATEGORY_LIST,list);
    return ViewConstants.VIEW_CONFIGURE_BMI;
    
  }
    
}
