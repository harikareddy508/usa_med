/**
 * 
 */
package com.usamd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.MedicalConditionDAO;
import com.usamd.modelBean.MedicalCondtionBean;

// TODO: Auto-generated Javadoc
/**
 * The Class MedicalConditionService.
 *
 * @author USAWebMD
 */
@Service
public class MedicalConditionService {
  
  /** The medical condition dao. */
  @Autowired
  private MedicalConditionDAO medicalConditionDAO;
  
  /**
   * Insert medical condition.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean insertMedicalCondition(MedicalCondtionBean bean){
    return medicalConditionDAO.insertMedicalCondition(bean);
  }
  
  
  /**
   * Fetch med cond based on smps.
   *
   * @param smpIds the smp ids
   * @return the list
   */
  public List<MedicalCondtionBean> fetchMedCondBasedOnSmps(String smpIds){
    return medicalConditionDAO.fetchMedCondBasedOnSmps(smpIds);
  }
  
  
  /**
   * Fetch medical condtions by name or mc id.
   *
   * @param medCondName the med cond name
   * @param mcId the mc id
   * @return the list
   */
  public List<MedicalCondtionBean> fetchMedicalCondtionsByNameOrMcId(String medCondName,String mcId){
    return medicalConditionDAO.fetchMedicalCondtionsByNameOrMcId(medCondName,mcId);
  }
  
  /**
   * Delete medical condtion.
   *
   * @param mcId the mc id
   * @return true, if successful
   */
  public boolean deleteMedicalCondtion(String mcId){
    return medicalConditionDAO.deleteMedicalCondtion(mcId);
  }
  
  
  
  
  
}
