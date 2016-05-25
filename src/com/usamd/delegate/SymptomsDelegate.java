/**
 * 
 */
package com.usamd.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usamd.modelBean.BodyPartSymptomBean;
import com.usamd.modelBean.MedCondSymptomBean;
import com.usamd.modelBean.MedicalCondtionBean;
import com.usamd.service.BodyPartSymptomService;
import com.usamd.service.MedCondSymptomService;
import com.usamd.service.MedicalConditionService;

// TODO: Auto-generated Javadoc
/**
 * The Class SymptomsDelegate.
 *
 * @author USAWebMD
 */
@Component
public class SymptomsDelegate {

  /** The body part symptom service. */
  @Autowired
  private BodyPartSymptomService bodyPartSymptomService;

  /** The medical condtion service. */
  @Autowired
  private MedicalConditionService medicalCondtionService;

  /** The med cond symptom service. */
  @Autowired
  private MedCondSymptomService medCondSymptomService;

  /**
   * Insert symptom.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addOrUpdateSymptom(BodyPartSymptomBean bean) {
    return bodyPartSymptomService.addOrUpdateSymptom(bean);
  }


  /**
   * Delete symptom.
   *
   * @param smpId the smp id
   * @return true, if successful
   */
  public boolean deleteSymptom(String smpId){
    return bodyPartSymptomService.deleteSymptom(smpId);
  }


  /**
   * Fetch body symptoms.
   *
   * @param bodyPart the body part
   * @return the list
   */
  public List<BodyPartSymptomBean> fetchBodySymptoms(String bodyPart) {
    return bodyPartSymptomService.fetchBodyPartSymptoms(bodyPart);
  }


  /**
   * Adds the medical condition.
   *
   * @param bean the bean
   * @param bodySmpList the body smp list
   * @return true, if successful
   */
  public boolean addMedicalCondition(MedicalCondtionBean bean, List<BodyPartSymptomBean> bodySmpList) {
    // Add medical condition
    medicalCondtionService.insertMedicalCondition(bean);
    String genMcId = bean.getMcId();
    List<MedCondSymptomBean> smpList = new ArrayList<MedCondSymptomBean>();

    // Split symptoms

    for (BodyPartSymptomBean smp : bodySmpList) {
      MedCondSymptomBean medCond = new MedCondSymptomBean();
      medCond.setMcId(genMcId);
      medCond.setSmpId(smp.getSmpId());
      smpList.add(medCond);
    }
    if (smpList != null && !smpList.isEmpty()) {
      medCondSymptomService.insertSymptoms(smpList);
    }
    return true;
  }

  /**
   * Fetch med cond based on smps.
   *
   * @param selectedSmpsList the selected smps list
   * @return the list
   */
  public List<MedicalCondtionBean> fetchMedCondBasedOnSmps(List<BodyPartSymptomBean> selectedSmpsList){
    int i =0;
    int size = selectedSmpsList.size();
    String smpIds = "";
    for(BodyPartSymptomBean bean : selectedSmpsList){
      if(i==size-1){
        smpIds = smpIds +"'"+bean.getSmpId()+"'";
        break;
      }
      smpIds = smpIds +"'"+bean.getSmpId()+"'"+",";
      i++;
    }
    List<MedicalCondtionBean>  fetchedList = medicalCondtionService.fetchMedCondBasedOnSmps(smpIds);
    return fetchedList;
  } 
  

  /**
   * Fetch medical condtions by name or mc id.
   *
   * @param medCondName the med cond name
   * @param mcId the mc id
   * @return the list
   */
  public List<MedicalCondtionBean> fetchMedicalCondtionsByNameOrMcId(String medCondName,String mcId){
    return medicalCondtionService.fetchMedicalCondtionsByNameOrMcId(medCondName,mcId);
  }

  /**
   * Delete medical condtion.
   * This method deletes the data from MEDICAL_CONDITION table and MED_COND_SYMPTOMS table by ON DELETE CASCADE.
   *
   * @param mcId the mc id
   * @return true, if successful
   */
  public boolean deleteMedicalCondtion(String mcId){
    return medicalCondtionService.deleteMedicalCondtion(mcId);
  }


  /**
   * Checks if is any med cond present by smp id.
   *
   * @param smpIds the smp ids
   * @return true, if is any med cond present by smp id
   */
  public boolean isAnyMedCondPresentBySmpId(String smpIds){
    List<MedCondSymptomBean> list = medCondSymptomService.fetchRecordByMcIdAndSmpId(null,smpIds);
    if(list==null ||list.isEmpty()){
      return false;
    }
    return true;
  }
  
  /**
   * Fetch symptoms by mc id.
   *
   * @param mcId the mc id
   * @return the list
   */
  public List<BodyPartSymptomBean> fetchSymptomsByMcId(String mcId){
    return bodyPartSymptomService.fetchSymptomsByMcId(mcId);
  }
  

}
