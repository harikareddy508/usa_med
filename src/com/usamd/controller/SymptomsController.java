/**
 * 
 */
package com.usamd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usamd.constants.GlobalConstants;
import com.usamd.constants.MessageConstants;
import com.usamd.constants.URLConstants;
import com.usamd.constants.ViewConstants;
import com.usamd.delegate.SymptomsDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.BodyPartSymptomBean;
import com.usamd.modelBean.MedicalCondtionBean;
import com.usamd.modelBean.WebUserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class SymptomsController.
 *
 * @author USAWebMD
 */
@Controller
public class SymptomsController extends AbstractController {


  /** The symptoms delegate. */
  @Autowired
  private SymptomsDelegate symptomsDelegate;



  /**
   * Display add symptoms.
   *
   * @param request the request
   * @param response the response
   * @param bodyPartSymptomBean the body part symptom bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_ADD_SYMPTOM, method = RequestMethod.GET)
  public String displayAddSymptoms(
      HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.BODY_PART_SYMPTOM_BEAN) BodyPartSymptomBean bodyPartSymptomBean) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    return ViewConstants.VIEW_ADD_SYMPTOM;
  }


  /**
   * Process add symptoms.
   *
   * @param request the request
   * @param response the response
   * @param bodyPartSymptomBean the body part symptom bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_ADD_SYMPTOM, method = RequestMethod.POST)
  public String processAddSymptoms(
      HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.BODY_PART_SYMPTOM_BEAN) BodyPartSymptomBean bodyPartSymptomBean,
      Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }

    boolean isSuccess = symptomsDelegate.addOrUpdateSymptom(bodyPartSymptomBean);
    if (isSuccess) {
      applicationLogger.debug(MessageConstants.ADD_USER_SUCCESS);
      model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.EDIT_SUCCESS, null, null));
      BodyPartSymptomBean newBean = new BodyPartSymptomBean();
      model.addAttribute( AbstractBean.BODY_PART_SYMPTOM_BEAN, newBean);


    } else {
      applicationLogger.debug(MessageConstants.ADD_USER_FAILURE);
      model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.EDIT_SUCCESS, null, null));

    }
    List<BodyPartSymptomBean> list = new ArrayList<BodyPartSymptomBean>();
    list = symptomsDelegate.fetchBodySymptoms(bodyPartSymptomBean.getBodyPart());
    model.addAttribute(GlobalConstants.BODY_SYMPTOMS, list);
    return ViewConstants.VIEW_ADD_SYMPTOM;
  }



  /**---------AJAX CALL---------------------------------------------------------
   * HttpServletRequest request Fetch body symptoms. Invoked by JSP through an AJAX call.
   *
   * @param request the request
   * @param bodyPart the body part
   * @return the list
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = URLConstants.URL_FETCH_BODY_SYMPTOMS, method = RequestMethod.POST)
  public @ResponseBody List<BodyPartSymptomBean> fetchBodySymptoms(HttpServletRequest request,
      @RequestParam(required = true, value = "bodyPart") String bodyPart) {
    List<BodyPartSymptomBean> list = new ArrayList<BodyPartSymptomBean>();
    if (request.getSession() != null) {
      if (request.getSession().getAttribute(GlobalConstants.BODYPART_SYMPTOM_MAP) != null) {
        list =
            ((Map<String, List<BodyPartSymptomBean>>) request.getSession().getAttribute(
                GlobalConstants.BODYPART_SYMPTOM_MAP)).get(bodyPart);
      }
    }
    if (list == null || list.isEmpty()) {
      list = symptomsDelegate.fetchBodySymptoms(bodyPart);
    }

    return list;
  }



  /**
   * ---------AJAX CALL---------------------------------------------------------
   * Maintain sel smps map. This method is called from the symptoms checker module to maintain the
   * selected symptoms.
   * 
   * @param request the request
   * @param smpId the smp id
   * @return the list
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = URLConstants.URL_MAINTAIN_SEL_SMPS_MAP, method = RequestMethod.POST)
  public @ResponseBody List<BodyPartSymptomBean> maintainSelSmpsMap(HttpServletRequest request,
      @RequestParam(required = true, value = "smpId") String smpId) {

    BodyPartSymptomBean newSmp = new BodyPartSymptomBean();
    newSmp.setSmpId(smpId);
    List<BodyPartSymptomBean> selectedSmpsList = null;
    List<BodyPartSymptomBean> allSymptomList = null;
    if (request.getSession() != null) {
      if (request.getSession().getAttribute(GlobalConstants.SELECTED_SMPS_LIST) != null) {
        selectedSmpsList =
            (List<BodyPartSymptomBean>) request.getSession().getAttribute(
                GlobalConstants.SELECTED_SMPS_LIST);
      }

      if (request.getSession().getAttribute(GlobalConstants.ALL_SYMPTOM_LIST) != null) {
        allSymptomList =
            (List<BodyPartSymptomBean>) request.getSession().getAttribute(
                GlobalConstants.ALL_SYMPTOM_LIST);
      }
    }
    if (allSymptomList != null && !allSymptomList.isEmpty()) {
      if (selectedSmpsList == null) {
        selectedSmpsList = new ArrayList<>();
      }
      if (!selectedSmpsList.contains(newSmp)) {
        int index = allSymptomList.indexOf(newSmp);
        if (index >= 0) {
          newSmp = allSymptomList.get(index);
          selectedSmpsList.add(newSmp);
        }

      }

    }
    request.getSession().setAttribute(GlobalConstants.SELECTED_SMPS_LIST, selectedSmpsList);
    return selectedSmpsList;
  }


  /**
   * ---------AJAX CALL---------------------------------------------------------
   * Reset selected symps. This method is called via ajax, to reset all the selected symptoms.
   *
   * @param request the request
   * @return true, if successful
   */
  @RequestMapping(value = URLConstants.URL_RESET_SELECTED_SMPS, method = RequestMethod.POST)
  public @ResponseBody boolean resetSelectedSymps(HttpServletRequest request) {
    if (request.getSession() != null) {
      request.getSession().removeAttribute(GlobalConstants.SELECTED_SMPS_LIST);
    }
    return true;
  }


  /**
   * ---------AJAX CALL---------------------------------------------------------
   * Predict med condition. This method is called via ajax, to fetch medical conditions for all the
   * selected symptoms.
   *
   * @param request the request
   * @return the list
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = URLConstants.URL_PREDICT_MED_CONDITION, method = RequestMethod.POST)
  public @ResponseBody List<MedicalCondtionBean> predictMedCondition(HttpServletRequest request) {
    List<BodyPartSymptomBean> selectedSmpsList = null;
    List<MedicalCondtionBean> medCondList = new ArrayList<>();
    if (request.getSession() != null) {
      if (request.getSession().getAttribute(GlobalConstants.SELECTED_SMPS_LIST) != null) {
        selectedSmpsList =
            (List<BodyPartSymptomBean>) request.getSession().getAttribute(
                GlobalConstants.SELECTED_SMPS_LIST);
      }
    }
    medCondList = symptomsDelegate.fetchMedCondBasedOnSmps(selectedSmpsList);
    return medCondList;
  }

  /**
   * This controller method deletes the selected symptom from the <b>Add Symptom Page</b>.
   *
   * @param request the request
   * @param smpId the smp id
   * @param bodyPartSymptomBean the body part symptom bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_DELETE_SYMPTOM, method = RequestMethod.POST)
  public String deleteSymptom(HttpServletRequest request,
      @RequestParam(value = "delSmpId") String smpId, @ModelAttribute(
          value = AbstractBean.BODY_PART_SYMPTOM_BEAN) BodyPartSymptomBean bodyPartSymptomBean,
      Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    //Check if this symptom is associated with any medical condtion.
    boolean isMedCondPresent = symptomsDelegate.isAnyMedCondPresentBySmpId("'"+smpId+"'");
    if(isMedCondPresent){
      model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.MED_COND_EXIST_FOR_DEL_SMP, null, null));
    }
    else{
      boolean isDeleted = symptomsDelegate.deleteSymptom(smpId);
      if (isDeleted) {
        model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.DELETE_SUCCESS, null, null));
      } else {
        model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.DELETE_FAILURE, null, null));
      }
    }
    List<BodyPartSymptomBean> list = new ArrayList<BodyPartSymptomBean>();
    list = symptomsDelegate.fetchBodySymptoms(null);
    model.addAttribute(GlobalConstants.BODY_SYMPTOMS, list);   
    return ViewConstants.VIEW_ADD_SYMPTOM;
  }


}
