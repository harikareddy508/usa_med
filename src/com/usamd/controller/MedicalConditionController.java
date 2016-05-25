/**
 * 
 */
package com.usamd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usamd.constants.GlobalConstants;
import com.usamd.constants.MessageConstants;
import com.usamd.constants.URLConstants;
import com.usamd.constants.ViewConstants;
import com.usamd.delegate.SymptomsDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.BodyPartSymptomBean;
import com.usamd.modelBean.MedicalCondtionBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.validator.MedicalConditionValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class MedicalConditionController.
 *
 * @author USAWebMD
 */
@Controller
public class MedicalConditionController extends AbstractController {


 

  /** The symptoms delegate. */

  @Autowired
  private SymptomsDelegate symptomsDelegate;  

  /** The login validator. */
  @Autowired
  private MedicalConditionValidator medicalConditionValidator;

  /**
   * Init binder.
   *
   * @param binder the binder
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(medicalConditionValidator);
  }

  /**
   * Display add med condition page.
   *
   * @param request the request
   * @param response the response
   * @param medicalConditionBean the medical condition bean
   * @param model the model
   * @return the string
   * @throws JSONException the JSON exception
   */
  @RequestMapping(value = URLConstants.URL_ADD_MEDICAL_CONDITION, method = RequestMethod.GET)
  public String displayAddMedCondition(
      HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.MEDICAL_CONDITION_BEAN) MedicalCondtionBean medicalConditionBean,
      Model model) throws JSONException {
    WebUserBean loggedUser = getLoggedUser(request);

    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }

    request.getSession().removeAttribute(GlobalConstants.SELECTED_SMPS_LIST);
    request.getSession().removeAttribute(GlobalConstants.BODYPART_SYMPTOM_MAP);
    request.getSession().removeAttribute(GlobalConstants.ALL_SYMPTOM_LIST);
    // fetch all body symptoms mapping and store it in a map.
    List<BodyPartSymptomBean> list = new ArrayList<BodyPartSymptomBean>();
    list = symptomsDelegate.fetchBodySymptoms(GlobalConstants.BLANK_STRING);
    Map<String, List<BodyPartSymptomBean>> bodyMap = new HashMap<>();
    List<BodyPartSymptomBean> symptomList = new ArrayList<>();
    String bodyPart = "";
    List<BodyPartSymptomBean> partSmpList = null;
    for (BodyPartSymptomBean bean : list) {
      if (!bodyPart.equals(bean.getBodyPart())) {
        if (partSmpList != null && !partSmpList.isEmpty()) {
          bodyMap.put(bodyPart, partSmpList);
          symptomList.addAll(partSmpList);
        }
        partSmpList = new ArrayList<>();
        bodyPart = bean.getBodyPart();
      }
      partSmpList.add(bean);
    }
    symptomList.addAll(partSmpList);
    bodyMap.put(bodyPart, partSmpList);
    if (request.getSession() != null) {
      request.getSession().setAttribute(GlobalConstants.BODYPART_SYMPTOM_MAP, bodyMap);
      request.getSession().setAttribute(GlobalConstants.ALL_SYMPTOM_LIST, symptomList);
    }
    //The below code is for the edit medical condition flow
    if (model.asMap().get(GlobalConstants.SELECTED_SMPS_LIST) != null) {
      request.getSession().setAttribute(GlobalConstants.SELECTED_SMPS_LIST,
          model.asMap().get(GlobalConstants.SELECTED_SMPS_LIST));
      // This signifies that data needs to be loaded in the selected symptoms table on JSP.
      model.addAttribute(GlobalConstants.LOAD, GlobalConstants.STRING_Y); 
    }


    return ViewConstants.VIEW_ADD_MEDICAL_CONDITION;
  }

  /**
   * Process add med condition.
   *
   * @param request the request
   * @param response the response
   * @param medicalConditionBean the medical condition bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = URLConstants.URL_ADD_MEDICAL_CONDITION, method = RequestMethod.POST)
  public String processAddMedCondition(
      HttpServletRequest request,
      HttpServletResponse response,
      @Valid @ModelAttribute(value = AbstractBean.MEDICAL_CONDITION_BEAN) MedicalCondtionBean medicalConditionBean,
      BindingResult results, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }

    //Below code fetches the selected smps list to be added to the database.
    List<BodyPartSymptomBean> selectedSmpsList = null;
    if (request.getSession() != null) {
      if (request.getSession().getAttribute(GlobalConstants.SELECTED_SMPS_LIST) != null) {
        selectedSmpsList =
            (List<BodyPartSymptomBean>) request.getSession().getAttribute(
                GlobalConstants.SELECTED_SMPS_LIST);
      }

    }
    if(!results.hasErrors()){
      if(selectedSmpsList==null || selectedSmpsList.size()<=0){
        model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
            this.getMessageSource().getMessage(MessageConstants.NO_SYMPTOMS_SELECTED, null, null));
      }
      else{
        boolean success = symptomsDelegate.addMedicalCondition(medicalConditionBean, selectedSmpsList);
        if (success) {
          model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
              this.getMessageSource().getMessage(MessageConstants.EDIT_SUCCESS, null, null));
          request.getSession().removeAttribute(GlobalConstants.SELECTED_SMPS_LIST);
          medicalConditionBean = new MedicalCondtionBean();
          model.addAttribute(AbstractBean.MEDICAL_CONDITION_BEAN, medicalConditionBean);
        } else {
          model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
              this.getMessageSource().getMessage(MessageConstants.EDIT_FAILURE, null, null));
        }
      }    
    }
    else{
      model.addAttribute(GlobalConstants.LOAD, GlobalConstants.STRING_Y); 
    }


    return ViewConstants.VIEW_ADD_MEDICAL_CONDITION;
  }


  /**
   * Display symptoms checker.
   *
   * @param request the request
   * @param response the response
   * @param medicalConditionBean the medical condition bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_SYMPTOMS_CHECKER, method = RequestMethod.GET)
  public String displaySymptomsChecker(
      HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.MEDICAL_CONDITION_BEAN) MedicalCondtionBean medicalConditionBean) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    request.getSession().removeAttribute(GlobalConstants.SELECTED_SMPS_LIST);
    request.getSession().removeAttribute(GlobalConstants.BODYPART_SYMPTOM_MAP);
    request.getSession().removeAttribute(GlobalConstants.ALL_SYMPTOM_LIST);
    // fetch all body symptoms mapping and store it in a map.
    List<BodyPartSymptomBean> list = new ArrayList<BodyPartSymptomBean>();
    list = symptomsDelegate.fetchBodySymptoms(GlobalConstants.BLANK_STRING);
    Map<String, List<BodyPartSymptomBean>> bodyMap = new HashMap<>();
    List<BodyPartSymptomBean> symptomList = new ArrayList<>();
    String bodyPart =GlobalConstants.BLANK_STRING;
    List<BodyPartSymptomBean> partSmpList = null;
    for (BodyPartSymptomBean bean : list) {
      if (!bodyPart.equals(bean.getBodyPart())) {
        if (partSmpList != null && !partSmpList.isEmpty()) {
          bodyMap.put(bodyPart, partSmpList);
          symptomList.addAll(partSmpList);
        }
        partSmpList = new ArrayList<>();
        bodyPart = bean.getBodyPart();
      }
      partSmpList.add(bean);
    }
    symptomList.addAll(partSmpList);
    bodyMap.put(bodyPart, partSmpList);
    if (request.getSession() != null) {
      request.getSession().setAttribute(GlobalConstants.BODYPART_SYMPTOM_MAP, bodyMap);
      request.getSession().setAttribute(GlobalConstants.ALL_SYMPTOM_LIST, symptomList);
    }

    return ViewConstants.VIEW_SYMPTOMS_CHECKER;
  }



  /**
   * ---------AJAX CALL---------------------------------------------------------
   * Delete sel smps map called via AJAX Call.
   *
   * @param request the request
   * @param smpId the smp id
   * @return the list
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = URLConstants.URL_DELETE_SEL_SMPS_MAP, method = RequestMethod.POST)
  public @ResponseBody List<BodyPartSymptomBean> deleteSelSmpsMap(HttpServletRequest request,
      @RequestParam(required = true, value = "smpId") String smpId) {

    List<BodyPartSymptomBean> selectedSmpsList = null;
    if (request.getSession() != null) {
      if (request.getSession().getAttribute(GlobalConstants.SELECTED_SMPS_LIST) != null) {
        selectedSmpsList =
            (List<BodyPartSymptomBean>) request.getSession().getAttribute(
                GlobalConstants.SELECTED_SMPS_LIST);
      }

    }
    //Fetch the session symptom list and remove the selected symptom.
    if (selectedSmpsList != null && selectedSmpsList.size() > 0) {
      BodyPartSymptomBean newSmp = new BodyPartSymptomBean();
      newSmp.setSmpId(smpId);
      int index = selectedSmpsList.indexOf(newSmp);
      if (index >= 0) {
        selectedSmpsList.remove(index);
      }

    }
    //Set the new symptom list post deletion in the selected smps session list.
    request.getSession().setAttribute(GlobalConstants.SELECTED_SMPS_LIST, selectedSmpsList);
    return selectedSmpsList;
  }

  /**
   * Display search medical condition.
   *
   * @param request the request
   * @param response the response
   * @param medicalCondtionBean the medical condtion bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_SEARCH_MEDICAL_CONDITION, method = RequestMethod.GET)
  public String displaySearchMedicalCondition(
      HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.MEDICAL_CONDITION_BEAN) MedicalCondtionBean medicalCondtionBean,
      Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    MedicalCondtionBean searchBean = null;
    //Search bean to be fecthed only when redirected post delete method. For fresh display no search results to be populated.
    if (GlobalConstants.STRING_Y.equals(model.asMap().get(GlobalConstants.REDIRECT))
        && request.getSession().getAttribute(GlobalConstants.SEARCH_MEDICAL_CONDITION) != null) {
      searchBean =
          (MedicalCondtionBean) request.getSession().getAttribute(
              GlobalConstants.SEARCH_MEDICAL_CONDITION);
      request.getSession().removeAttribute(GlobalConstants.SEARCH_MEDICAL_CONDITION);
    }
    if (searchBean != null) {
      List<MedicalCondtionBean> medCondList =
          symptomsDelegate.fetchMedicalCondtionsByNameOrMcId(searchBean.getName(), null);
      model.addAttribute(GlobalConstants.MED_CONDITION_LIST, medCondList);
      model.addAttribute(AbstractBean.MEDICINE_INFO_BEAN, searchBean);
    }
    return ViewConstants.VIEW_SEARCH_MEDICAL_CONDITION;
  }


  /**
   * Process search medicine.
   *
   * @param request the request
   * @param response the response
   * @param medicalCondtionBean the medical condtion bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_SEARCH_MEDICAL_CONDITION, method = RequestMethod.POST)
  public String processSearchMedicine(
      HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.MEDICAL_CONDITION_BEAN) MedicalCondtionBean medicalCondtionBean,
      Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    if (request.getSession().getAttribute(GlobalConstants.SEARCH_MEDICAL_CONDITION) != null) {
      request.getSession().removeAttribute(GlobalConstants.SEARCH_MEDICAL_CONDITION);
    }
    List<MedicalCondtionBean> medCondList =
        symptomsDelegate.fetchMedicalCondtionsByNameOrMcId(medicalCondtionBean.getName(), null);
    request.getSession()
    .setAttribute(GlobalConstants.SEARCH_MEDICAL_CONDITION, medicalCondtionBean);
    model.addAttribute(GlobalConstants.MED_CONDITION_LIST, medCondList);
    return ViewConstants.VIEW_SEARCH_MEDICAL_CONDITION;
  }


  /**
   * Process delete medicine.
   *
   * @param request the request
   * @param response the response
   * @param mcId the mc id
   * @param redirectAttr the redirect attr
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_DELETE_MEDICAL_CONDITION + "/{mcId}",
      method = RequestMethod.GET)
  public String processDeleteMedicine(HttpServletRequest request, HttpServletResponse response,
      @PathVariable(value = "mcId") String mcId, RedirectAttributes redirectAttr) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    boolean isSuccess = symptomsDelegate.deleteMedicalCondtion(mcId);
    if (isSuccess) {
      applicationLogger.debug(MessageConstants.DELETE_SUCCESS);
      redirectAttr.addFlashAttribute(GlobalConstants.SUCCESS_MESSAGE, this.getMessageSource()
          .getMessage(MessageConstants.DELETE_SUCCESS, null, null));


    } else {
      applicationLogger.debug(MessageConstants.DELETE_FAILURE);
      redirectAttr.addFlashAttribute(GlobalConstants.FAILURE_MESSAGE, this.getMessageSource()
          .getMessage(MessageConstants.DELETE_FAILURE, null, null));

    }
    redirectAttr.addFlashAttribute(GlobalConstants.REDIRECT, GlobalConstants.STRING_Y);
    return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_SEARCH_MEDICAL_CONDITION;
  }

  /**
   * Display edit medical condition.
   *
   * @param request the request
   * @param response the response
   * @param mcId the mc id
   * @param redirectAttr the redirect attr
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_EDIT_MEDICAL_CONDITION + "/{mcId}",
      method = RequestMethod.GET)
  public String displayEditMedicalCondition(HttpServletRequest request,
      HttpServletResponse response, @PathVariable(value = "mcId") String mcId,
      RedirectAttributes redirectAttr) {
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    MedicalCondtionBean bean = new MedicalCondtionBean();
    bean.setMcId(mcId);
    List<MedicalCondtionBean> list = symptomsDelegate.fetchMedicalCondtionsByNameOrMcId(null, mcId);
    if (list != null && list.size() > 0) {
      bean = list.get(0);
      // Fetch symptoms
      List<BodyPartSymptomBean> smpList = symptomsDelegate.fetchSymptomsByMcId(bean.getMcId());
      redirectAttr.addFlashAttribute(GlobalConstants.SELECTED_SMPS_LIST, smpList);
    }
    redirectAttr.addFlashAttribute(AbstractBean.MEDICAL_CONDITION_BEAN, bean);
    return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_ADD_MEDICAL_CONDITION;
  }



}
