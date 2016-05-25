/**
 * 
 */
package com.usamd.delegate;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usamd.modelBean.HomeScreenBean;
import com.usamd.modelBean.SectionUIBean;
import com.usamd.service.HomeSectionService;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeSectionDelegate.
 *
 * @author USAWebMD
 */
@Component
public class HomeSectionDelegate {
  
  /** The home section service. */
  @Autowired
  private HomeSectionService homeSectionService;

  /**
   * Save section information.
   *
   * @param bean the bean
   * @return the section ui bean
   */
  public SectionUIBean saveSectionInformation(SectionUIBean bean) {
    bean=homeSectionService.saveSectionInformation(bean);
    if(!"VIDEO".equals(bean.getSectionId())){
      byte[] encodedImage = Base64.encode(bean.getImage());
      String encodedString = new String(encodedImage);
      bean.setEncodedImage(encodedString);
    }
    return bean;
  }
  
  /**
   * Fetch all sections.
   *
   * @return the home screen bean
   */
  public HomeScreenBean fetchAllSections() {
    List<SectionUIBean> list = new ArrayList<SectionUIBean>();
    HomeScreenBean homeScreenBean = new HomeScreenBean();
    list = homeSectionService.fetchAllSections();
    for (SectionUIBean bean : list) {
      if(bean!=null && !"VIDEO".equals(bean.getSectionId())){
        byte[] encodedImage = Base64.encode(bean.getImage());
        String encodedString = new String(encodedImage);
        bean.setEncodedImage(encodedString);
      }
      if (bean.getSectionId().equalsIgnoreCase("LEFTIMP")) {
        homeScreenBean.setLeftImpSection(bean);
      } else if (bean.getSectionId().equalsIgnoreCase("RIGHTIMP")) {
        homeScreenBean.setRightImpSection(bean);
      } else if (bean.getSectionId().equalsIgnoreCase("ARTICLE1")) {
        homeScreenBean.setArticleSection1(bean);
      } else if (bean.getSectionId().equalsIgnoreCase("ARTICLE2")) {
        homeScreenBean.setArticleSection2(bean);
      } else if (bean.getSectionId().equalsIgnoreCase("ARTICLE3")) {
        homeScreenBean.setArticleSection3(bean);
      } else if (bean.getSectionId().equalsIgnoreCase("VIDEO")) {
        homeScreenBean.setVideoSection(bean);
      }
    }
    return homeScreenBean;
  }

    


  /**
   * Fetch section information.
   *
   * @param sectionId the section id
   * @return the section ui bean
   */
  public SectionUIBean fetchSectionInformation(String sectionId){
    SectionUIBean bean = homeSectionService.fetchSectionBySectionId(sectionId);
    if(bean!=null && !"VIDEO".equals(sectionId)){
      byte[] encodedImage = Base64.encode(bean.getImage());
      String encodedString = new String(encodedImage);
      bean.setEncodedImage(encodedString);
    }   
    return bean ;
  }

}
