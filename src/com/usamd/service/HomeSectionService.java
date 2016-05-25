/**
 * 
 */
package com.usamd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.SectionUIDAO;
import com.usamd.modelBean.SectionUIBean;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeSectionService.
 *
 * @author USAWebMD
 */
@Service
public class HomeSectionService {
  
  /** The section uidao. */
  @Autowired
  private SectionUIDAO sectionUIDAO;

 
  /**
   * Fetch section by section id.
   *
   * @param sectionId the section id
   * @return the section ui bean
   */
  public SectionUIBean fetchSectionBySectionId(String sectionId){
    return sectionUIDAO.fetchSectionBySectionId(sectionId);
  }
  
  /**
   * Save section information.
   *
   * @param bean the bean
   * @return the section ui bean
   */
  public SectionUIBean saveSectionInformation(SectionUIBean bean){
    SectionUIBean fetchedBean = sectionUIDAO.fetchSectionBySectionId(bean.getSectionId());
    System.out.println(" fetchedBean "+fetchedBean);
    if(fetchedBean==null){
      sectionUIDAO.addSectionUI(bean);
    }
    else{
      sectionUIDAO.updateSectionUI(bean);
    }
    return bean;
  }
  
  /**
   * Fetch all sections.
   *
   * @return the list
   */
  public List<SectionUIBean> fetchAllSections(){
    return sectionUIDAO.fetchAllSections();
  }
  
}
