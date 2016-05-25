/**
 * 
 */
package com.usamd.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usamd.constants.GlobalConstants;
import com.usamd.constants.MessageConstants;
import com.usamd.constants.URLConstants;
import com.usamd.constants.ViewConstants;
import com.usamd.delegate.DoctorDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.DoctorBean;
import com.usamd.modelBean.HealthCenterBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.validator.DoctorValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorController handles all the request for Doctor and health center modules like
 * Add/Search/Edit/Delete and Locate Doctor and Health Centers.
 * 
 * @author USAWebMD
 */
@Controller
public class DoctorController extends AbstractController {

  /** The doctor delegate. */
 

  /** The user delegate. */
  @Autowired
  private DoctorDelegate doctorDelegate;

  /** The user validator. */
  @Autowired
  private DoctorValidator doctorValidator;


  /**
   * Inits the binder.
   *
   * @param binder the binder
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(doctorValidator);
  }


  /**
   * This controller method displays the <b>Add Doctor Page</b> with pre-populated dropdown for
   * existing health centers. <Strong>ROLE : - ADMIN</Strong>
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_ADD_DOCTOR, method = RequestMethod.GET)
  public String displayAddDoctor(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      // to redirect logged in user directly to home page.
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    List<HealthCenterBean> hcList = doctorDelegate.fetchExistingHealthCenters();
    model.addAttribute(GlobalConstants.EXISTING_HEALTH_CENTERS, hcList);

    return ViewConstants.VIEW_ADD_DOCTOR;
  }



  /**
   * This controller method saves the doctor information added from the Add Doctor Page. <br>
   * This method first validates the input information and then proceeds to it. It also sends the
   * success or failure message to the Page.
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_ADD_DOCTOR, method = RequestMethod.POST)
  public String processAddDoctor(HttpServletRequest request, HttpServletResponse response,
      @Valid @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean,
      BindingResult results, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      // To redirect logged in user directly to home page.
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    if (!results.hasErrors()) {
      boolean isSuccess = doctorDelegate.addDoctorDetails(doctorBean, loggedUser.getUsername());
      if (isSuccess) {
        applicationLogger.debug(MessageConstants.ADD_USER_SUCCESS);
        doctorBean.setFullName();
        model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.ADD_SUCCESS, null, null));
        doctorBean = new DoctorBean();
        model.addAttribute(AbstractBean.DOCTOR_BEAN,doctorBean);


      } else {
        applicationLogger.debug(MessageConstants.ADD_USER_FAILURE);
        model.addAttribute(
            GlobalConstants.FAILURE_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.ADD_FAILURE,
                new Object[] {doctorBean.getEmailId()}, null));

      }
    }
    List<HealthCenterBean> hcList = doctorDelegate.fetchExistingHealthCenters();
    model.addAttribute(GlobalConstants.EXISTING_HEALTH_CENTERS, hcList);
    return ViewConstants.VIEW_ADD_DOCTOR;
  }



  /**
   * This controller method displays the <b>Search Doctor Page</b>.
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_SEARCH_DOCTOR, method = RequestMethod.GET)
  public String displaySearchDoctor(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }

    if (request.getSession().getAttribute(GlobalConstants.SEARCH_DOC_RESULTS) != null) {
      request.getSession().removeAttribute(GlobalConstants.SEARCH_DOC_RESULTS);
    }
    return ViewConstants.VIEW_SEARCH_DOCTOR;
  }


  /**
   * This controller method fetches the doctor information depending upon the search criteria
   * entered in the <b>Search Doctor Page</b>.
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_SEARCH_DOCTOR, method = RequestMethod.POST)
  public String processSearchDoctor(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    List<DoctorBean> doctorList = doctorDelegate.searchDoctor(doctorBean);
    request.getSession().setAttribute(GlobalConstants.SEARCH_DOC_RESULTS, doctorList);
    return ViewConstants.VIEW_SEARCH_DOCTOR;
  }


  /**
   * This controller method displays the <b>Edit Doctor Page</b>. <br>
   * This method fetches the doctor information of the edited user and display it on the screen when
   * it gets loaded.
   *
   * @param doctorId the doctor id
   * @param request the request
   * @param response the response
   * @param redirectAttributes the redirect attributes
   * @param doctorBean the doctor bean
   * @param model the model
   * @return the string
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = URLConstants.URL_EDIT_DOCTOR + "/{doctorId}", method = RequestMethod.GET)
  public String displayEditDoctor(@PathVariable("doctorId") String doctorId,
      HttpServletRequest request, HttpServletResponse response,
      RedirectAttributes redirectAttributes,
      @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    List<DoctorBean> searchResults = null;
    if (request.getSession().getAttribute(GlobalConstants.SEARCH_DOC_RESULTS) != null) {
      searchResults = (List<DoctorBean>) request.getSession().getAttribute(GlobalConstants.SEARCH_DOC_RESULTS);
    }
    if (searchResults != null) {
      for (DoctorBean doc : searchResults) {
        if (doc.getDoctorId().equals(doctorId)) {
          doctorBean = doc;
        }
      }
    }
    List<HealthCenterBean> hcList = doctorDelegate.fetchExistingHealthCenters();
    model.addAttribute(GlobalConstants.EXISTING_HEALTH_CENTERS, hcList);
    model.addAttribute(AbstractBean.DOCTOR_BEAN, doctorBean);
    return ViewConstants.VIEW_EDIT_DOCTOR;
  }

  /**
   * This controller method saves the information edited on the <b>Edit Doctor Page</b> and displays
   * the success/failure message.
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_EDIT_SAVE, method = RequestMethod.POST)
  public String processEdit(HttpServletRequest request, HttpServletResponse response,
      @Valid @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean,
      BindingResult results, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_LOGIN;
    }
    if (!results.hasErrors()) {
      boolean isUpdated = doctorDelegate.updateDoctor(doctorBean);
      if (isUpdated) {
        model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.EDIT_SUCCESS, null, null));
      } else {
        model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.EDIT_FAILURE, null, null));
      }
    }
    List<HealthCenterBean> hcList = doctorDelegate.fetchExistingHealthCenters();
    model.addAttribute(GlobalConstants.EXISTING_HEALTH_CENTERS, hcList);
    return ViewConstants.VIEW_EDIT_DOCTOR;
  }



  /**
   * This controller method deletes the requested doctor from the application and sends the
   * success/failure messages.
   *
   * @param doctorId the doctor id
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @param redirectAttributes the redirect attributes
   * @return the string
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = URLConstants.URL_DELETE_DOCTOR + "/{doctorId}",
      method = RequestMethod.GET)
  public String processDeleteDoctor(@PathVariable("doctorId") String doctorId,
      HttpServletRequest request, HttpServletResponse response, @ModelAttribute(
          value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean,
      RedirectAttributes redirectAttributes) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_LOGIN;
    }

    boolean isDeleted = doctorDelegate.deleteDoctor(doctorId);


    if (isDeleted) {
      List<DoctorBean> searchList = null;
      List<DoctorBean> postDelSearchList = new ArrayList<DoctorBean>();
      if (request.getSession().getAttribute(GlobalConstants.SEARCH_DOC_RESULTS) != null) {
        searchList = (List<DoctorBean>) request.getSession().getAttribute(GlobalConstants.SEARCH_DOC_RESULTS);
        for (DoctorBean doc : searchList) {
          if (!doc.getDoctorId().equals(doctorId)) {
            postDelSearchList.add(doc);
          }

        }
        request.getSession().setAttribute(GlobalConstants.SEARCH_DOC_RESULTS, postDelSearchList);
      }
      redirectAttributes.addFlashAttribute(GlobalConstants.SUCCESS_MESSAGE, this.getMessageSource()
          .getMessage(MessageConstants.DELETE_SUCCESS, null, null));
    } else {
      redirectAttributes.addFlashAttribute(GlobalConstants.FAILURE_MESSAGE, this.getMessageSource()
          .getMessage(MessageConstants.DELETE_FAILURE, null, null));
    }
    return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_SEARCH_DOCTOR;

  }


  /**
   * This controller method displays the <b>Locate Doctor Page</b>.
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_LOCATE_DOCTOR, method = RequestMethod.GET)
  public String displayLocateDoctor(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }

    if (request.getSession().getAttribute(GlobalConstants.SEARCH_DOC_RESULTS) != null) {
      request.getSession().removeAttribute(GlobalConstants.SEARCH_DOC_RESULTS);
    }
    return ViewConstants.VIEW_LOCATE_DOCTOR;
  }


  /**
   * This controller method fetches the doctors depending upon the criteria added on <b>Locate
   * Doctor Page</b>.
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_LOCATE_DOCTOR, method = RequestMethod.POST)
  public String processLocateDoctor(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    List<DoctorBean> doctorList =
        doctorDelegate.locateDoctor(doctorBean.getHealthCenter().getAddress().getZipcode(),
            doctorBean.getSpecialization());
    model.addAttribute(GlobalConstants.SEARCH_DOC_RESULTS, doctorList);
    if(doctorList==null || doctorList.isEmpty()){
      model.addAttribute(GlobalConstants.FAILURE_MESSAGE, this.getMessageSource()
          .getMessage(MessageConstants.SEARCH_FAILURE, null, null));
    }
    return ViewConstants.VIEW_LOCATE_DOCTOR;
  }


  /**
   * This controller method displays the <b>Locate Health Center<b> Page.
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_LOCATE_CENTER, method = RequestMethod.GET)
  public String displayLocateHealthCenter(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    return ViewConstants.VIEW_LOCATE_HEALTH_CENTER;
  }

  /**
   * This controller method fetches the health center details depending on the criteria entered on
   * <b>Locate Health Center Page<b>.
   *
   * @param request the request
   * @param response the response
   * @param doctorBean the doctor bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_LOCATE_CENTER, method = RequestMethod.POST)
  public String processLocateHealthCenter(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.DOCTOR_BEAN) DoctorBean doctorBean, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    List<HealthCenterBean> healthCenterList =
        doctorDelegate.fetchHealthCenters(doctorBean.getSpecialization(), doctorBean
            .getHealthCenter().getAddress().getZipcode());
    model.addAttribute(GlobalConstants.SEARCH_CENTER_RESULTS, healthCenterList);
    return ViewConstants.VIEW_LOCATE_HEALTH_CENTER;
  }


}
