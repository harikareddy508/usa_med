/**
 * 
 */
package com.usamd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.usamd.constants.GlobalConstants;
import com.usamd.constants.MessageConstants;
import com.usamd.constants.URLConstants;
import com.usamd.constants.ViewConstants;
import com.usamd.delegate.HomeSectionDelegate;
import com.usamd.modelBean.AbstractBean;
import com.usamd.modelBean.HomeScreenBean;
import com.usamd.modelBean.LoginBean;
import com.usamd.modelBean.SectionUIBean;
import com.usamd.modelBean.WebUserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeSectionController.
 *
 * @author USAWebMD
 */
@Controller
public class HomeSectionController extends AbstractController {

  /** The home section delegate. */
 
  /** The home section delegate. */
  @Autowired
  private HomeSectionDelegate homeSectionDelegate;



  /**
   * Display home page.
   *
   * @param request the request
   * @param response the response
   * @param loginBean the login bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_HOME_PAGE, method = RequestMethod.GET)
  public String displayHomePage(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.LOGIN_BEAN) LoginBean loginBean, Model model) {
    WebUserBean loggedUser = getLoggedUser(request);
    // Set the logged user in session
    request.getSession().setAttribute(GlobalConstants.LOGGED_USER, loggedUser);
    // Fetch all sections
    HomeScreenBean homeScreenBean = homeSectionDelegate.fetchAllSections();
    request.getSession().setAttribute(AbstractBean.HOME_SCREEN_BEAN, homeScreenBean);

    return ViewConstants.VIEW_HOME_PAGE;
  }

  /**
   * Display section ui page.
   *
   * @param request the request
   * @param response the response
   * @param sectionUIBean the section ui bean
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_SECTION, method = RequestMethod.GET)
  public String displaySectionUIPage(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.SECTION_UI_BEAN) SectionUIBean sectionUIBean) {
    return ViewConstants.VIEW_SECTION;
  }

  /**
   * Process section ui page.
   *
   * @param request the request
   * @param response the response
   * @param file the file
   * @param sectionUIBean the section ui bean
   * @param model the model
   * @return the string
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = URLConstants.URL_SECTION, method = RequestMethod.POST)
  public String processSectionUIPage(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("uploadImage") MultipartFile file, @ModelAttribute(
          value = AbstractBean.SECTION_UI_BEAN) SectionUIBean sectionUIBean, Model model)
      throws IOException {
    if (!GlobalConstants.VIDEO_SECTION.equals(sectionUIBean.getSectionId())
        && !file.getContentType().equals("image/jpeg")) {
      model.addAttribute(GlobalConstants.FAILURE_MESSAGE,
          this.getMessageSource().getMessage(MessageConstants.INVALID_IMAGE_FORMAT, null, null));
      return ViewConstants.VIEW_SECTION;
    }
    if (!GlobalConstants.VIDEO_SECTION.equals(sectionUIBean.getSectionId())) {
      byte[] imageByte = file.getBytes();
      sectionUIBean.setImage(imageByte);
    }
    homeSectionDelegate.saveSectionInformation(sectionUIBean);
    model.addAttribute(GlobalConstants.SUCCESS_MESSAGE,
        this.getMessageSource().getMessage(MessageConstants.EDIT_SUCCESS, null, null));
    return ViewConstants.VIEW_SECTION;
  }


  /**
   * Fetch section information.
   *
   * @param request the request
   * @param response the response
   * @param sectionUIBean the section ui bean
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = URLConstants.URL_SECTION_INFO, method = RequestMethod.POST)
  public String fetchSectionInformation(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute(value = AbstractBean.SECTION_UI_BEAN) SectionUIBean sectionUIBean, Model model) {
    SectionUIBean fetchedSectionUIBean =
        homeSectionDelegate.fetchSectionInformation(sectionUIBean.getSectionId());
    if (fetchedSectionUIBean == null) {
      fetchedSectionUIBean = new SectionUIBean();
      fetchedSectionUIBean.setSectionId(sectionUIBean.getSectionId());
    }
    model.addAttribute(AbstractBean.SECTION_UI_BEAN, fetchedSectionUIBean);
    return ViewConstants.VIEW_SECTION;
  }
}
