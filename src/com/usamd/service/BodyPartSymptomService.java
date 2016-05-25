/**
 * 
 */
package com.usamd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.BodyPartSymptomDao;
import com.usamd.modelBean.BodyPartSymptomBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BodyPartSymptomService.
 *
 * @author USAWebMD
 */
@Service
public class BodyPartSymptomService {
  
  /** The body part symptom dao. */
  @Autowired
  private BodyPartSymptomDao bodyPartSymptomDao;
  
  /**
   * Insert symptom.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addOrUpdateSymptom(BodyPartSymptomBean bean){
    boolean success = false;
    if(bean.getSmpId()!=null && !bean.getSmpId().isEmpty()){
      success=bodyPartSymptomDao.updateSymptom(bean);
    }
    else{
      success= bodyPartSymptomDao.insertSymptom(bean);
    }
    return success;
  }
  
  /**
   * Delete symptom.
   *
   * @param smpId the smp id
   * @return true, if successful
   */
  public boolean deleteSymptom(String smpId){
    return bodyPartSymptomDao.deleteSymptom(smpId);
  }
  
  
  /**
   * Fetch body part symptoms.
   *
   * @param bodyPart the body part
   * @return the list
   */
  public List<BodyPartSymptomBean> fetchBodyPartSymptoms(String bodyPart){
    return bodyPartSymptomDao.fetchBodyPartSymptoms(bodyPart);
  }
  
  /**
   * Fetch symptoms by mc id.
   *
   * @param mcId the mc id
   * @return the list
   */
  public List<BodyPartSymptomBean> fetchSymptomsByMcId(String mcId){
    return bodyPartSymptomDao.fetchSymptomsByMcId(mcId);
  }
  
  
}
