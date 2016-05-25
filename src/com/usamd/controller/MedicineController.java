/**
 * 
 */
package com.usamd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usamd.constants.GlobalConstants;
import com.usamd.constants.MessageConstants;
import com.usamd.constants.URLConstants;
import com.usamd.constants.ViewConstants;
import com.usamd.delegate.MedicineDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.MedicineInfoBean;
import com.usamd.modelBean.WebUserBean;
import com.usamd.validator.MedicineValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class MedicineController.
 *
 * @author USAWebMD
 */
@Controller
public class MedicineController extends AbstractController{
  
 

  /** The medicine validator. */
  @Autowired
  private MedicineValidator medicineValidator;
  
  /** The medicine delegate. */
  @Autowired
  private MedicineDelegate medicineDelegate;
  
  /**
   * Inits the binder.
   *
   * @param binder the binder
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(medicineValidator);
  }

  
  /**
   * Display medicine page.
   *
   * @param request the request
   * @param response the response
   * @param medicineInfoBean the medicine info bean
   * @return the string
   */
  @RequestMapping(value=URLConstants.URL_MEDICINE, method=RequestMethod.GET)
  public String displayMedicinePage(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value=AbstractBean.MEDICINE_INFO_BEAN) MedicineInfoBean medicineInfoBean){
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    
    return ViewConstants.VIEW_MEDICINE;
  }
  
  
  /**
   * Process medicine page.
   *
   * @param request the request
   * @param response the response
   * @param medicineInfoBean the medicine info bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @RequestMapping(value=URLConstants.URL_MEDICINE, method=RequestMethod.POST)
  public String processMedicinePage(HttpServletRequest request, HttpServletResponse response,
      @Valid @ModelAttribute(value=AbstractBean.MEDICINE_INFO_BEAN) MedicineInfoBean medicineInfoBean, BindingResult results,Model model){
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    if(results.hasErrors()){
      return ViewConstants.VIEW_MEDICINE; 
    }
    boolean isSuccess = medicineDelegate.insertOrUpdateMedcine(medicineInfoBean);
    if (isSuccess) {
      applicationLogger.debug(MessageConstants.ADD_SUCCESS);
      model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.ADD_SUCCESS, null, null));
      medicineInfoBean = new MedicineInfoBean();


    } else {
      applicationLogger.debug(MessageConstants.ADD_FAILURE);
      model.addAttribute(
          GlobalConstants.FAILURE_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.ADD_FAILURE,null, null));

    }
    return ViewConstants.VIEW_MEDICINE;
  }
  
  /**
   * Display search medicine.
   *
   * @param request the request
   * @param response the response
   * @param medicineInfoBean the medicine info bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value=URLConstants.URL_SEARCH_MEDICINE, method=RequestMethod.GET)
  public String displaySearchMedicine(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value=AbstractBean.MEDICINE_INFO_BEAN) MedicineInfoBean medicineInfoBean,Model model){
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    
    MedicineInfoBean searchBean = null;
    if(request.getSession().getAttribute(GlobalConstants.SEARCH_MEDICINE)!=null){
      searchBean=(MedicineInfoBean)request.getSession().getAttribute(GlobalConstants.SEARCH_MEDICINE);
      request.getSession().removeAttribute(GlobalConstants.SEARCH_MEDICINE);      
    }
    if(searchBean!=null){
      List<MedicineInfoBean> medicineList = medicineDelegate.fetchMedicines(searchBean);
      model.addAttribute(GlobalConstants.MEDICINE_LIST,medicineList);
      model.addAttribute(AbstractBean.MEDICINE_INFO_BEAN,searchBean);
    }  
    return ViewConstants.VIEW_SEARCH_MEDICINE;
  }
  
  
  /**
   * Process search medicine.
   *
   * @param request the request
   * @param response the response
   * @param medicineInfoBean the medicine info bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @RequestMapping(value=URLConstants.URL_SEARCH_MEDICINE, method=RequestMethod.POST)
  public String processSearchMedicine(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value=AbstractBean.MEDICINE_INFO_BEAN) MedicineInfoBean medicineInfoBean,BindingResult results,Model model){
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    
    
    if(request.getSession().getAttribute(GlobalConstants.SEARCH_MEDICINE)!=null){
      request.getSession().removeAttribute(GlobalConstants.SEARCH_MEDICINE);
    }
    else{
      medicineValidator.validateSearch(medicineInfoBean, results);
      if(results.hasErrors()){
        return ViewConstants.VIEW_SEARCH_MEDICINE;
      }
    }
    List<MedicineInfoBean> medicineList = medicineDelegate.fetchMedicines(medicineInfoBean);
    request.getSession().setAttribute(GlobalConstants.SEARCH_MEDICINE, medicineInfoBean);
    model.addAttribute(GlobalConstants.MEDICINE_LIST,medicineList);
    return ViewConstants.VIEW_SEARCH_MEDICINE;
  }
  
  
  /**
   * Process delete medicine.
   *
   * @param request the request
   * @param response the response
   * @param medId the med id
   * @param redirectAttr the redirect attr
   * @return the string
   */
  @RequestMapping(value=URLConstants.URL_DELETE_MEDICINE+"/{medId}", method=RequestMethod.GET)
  public String processDeleteMedicine(HttpServletRequest request, HttpServletResponse response,
      @PathVariable(value="medId") String medId,RedirectAttributes redirectAttr){
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    boolean isSuccess= medicineDelegate.deleteMedicine(medId);
    if (isSuccess) {
      applicationLogger.debug(MessageConstants.DELETE_SUCCESS);
      redirectAttr.addFlashAttribute(GlobalConstants.SUCCESS_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.DELETE_SUCCESS, null, null));


    } else {
      applicationLogger.debug(MessageConstants.DELETE_FAILURE);
      redirectAttr.addFlashAttribute(
          GlobalConstants.FAILURE_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.DELETE_FAILURE,null, null));

    }   
    return GlobalConstants.REDIRECT_REQ+ViewConstants.VIEW_SEARCH_MEDICINE;
  }
  
  /**
   * Display edit medicine.
   *
   * @param request the request
   * @param response the response
   * @param medId the med id
   * @param redirectAttr the redirect attr
   * @return the string
   */
  @RequestMapping(value=URLConstants.URL_EDIT_MEDICINE+"/{medId}", method=RequestMethod.GET)
  public String displayEditMedicine(HttpServletRequest request, HttpServletResponse response,
      @PathVariable(value="medId") String medId,RedirectAttributes redirectAttr){
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    MedicineInfoBean bean = new MedicineInfoBean();
    bean.setMedId(medId);
    bean= medicineDelegate.fetchMedicines(bean).get(0);
    redirectAttr.addFlashAttribute(AbstractBean.MEDICINE_INFO_BEAN,bean);
    return GlobalConstants.REDIRECT_REQ+ViewConstants.VIEW_MEDICINE;
  }  
  
  
  
  /**
   * Display find medicine.
   *
   * @param request the request
   * @param response the response
   * @param medicineInfoBean the medicine info bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value=URLConstants.URL_FIND_MEDICINE, method=RequestMethod.GET)
  public String displayFindMedicine(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value=AbstractBean.MEDICINE_INFO_BEAN) MedicineInfoBean medicineInfoBean,Model model){
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
   
    return ViewConstants.VIEW_FIND_MEDICINE;
  }
  
  
  /**
   * Process find medicine.
   *
   * @param request the request
   * @param response the response
   * @param medicineInfoBean the medicine info bean
   * @param results the results
   * @param model the model
   * @return the string
   */
  @RequestMapping(value=URLConstants.URL_FIND_MEDICINE, method=RequestMethod.POST)
  public String processFindMedicine(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value=AbstractBean.MEDICINE_INFO_BEAN) MedicineInfoBean medicineInfoBean,BindingResult results,Model model){
    WebUserBean loggedUser = getLoggedUser(request);
    if (loggedUser == null) {
      return GlobalConstants.REDIRECT_REQ + ViewConstants.VIEW_HOME_PAGE;
    }
    medicineValidator.validateSearch(medicineInfoBean, results);
    if(results.hasErrors()){
      return ViewConstants.VIEW_FIND_MEDICINE;
    }
    request.getSession().removeAttribute(GlobalConstants.MED_MAP);
    List<MedicineInfoBean> medicineList = medicineDelegate.fetchMedicines(medicineInfoBean);
    Map<String,MedicineInfoBean> medMap = new HashMap<>();
    if(medicineList!=null){
      for(MedicineInfoBean bean : medicineList){
        medMap.put(bean.getMedId(),bean);
      }
    }
    request.getSession().setAttribute(GlobalConstants.MED_MAP,medMap);
    model.addAttribute(GlobalConstants.MEDICINE_LIST,medicineList);
    return ViewConstants.VIEW_FIND_MEDICINE;
  }
  
  /**
   * Maintain sel smps map.
   *
   * @param request the request
   * @param medId the med id
   * @return the medicine info bean
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = URLConstants.FETCH_MEDICINE_INFO, method = RequestMethod.POST)
  public @ResponseBody MedicineInfoBean maintainSelSmpsMap(HttpServletRequest request,
      @RequestParam(required = true, value = "medId") String medId) {

    Map<String,MedicineInfoBean> medMap = new HashMap<>();
    MedicineInfoBean med = new MedicineInfoBean();
    if( request.getSession()!=null &&  request.getSession().getAttribute(GlobalConstants.MED_MAP)!=null){
      medMap =( Map<String,MedicineInfoBean> )request.getSession().getAttribute(GlobalConstants.MED_MAP);
      med = medMap.get(medId);
    } 
    return med;
  }
 
  
}
