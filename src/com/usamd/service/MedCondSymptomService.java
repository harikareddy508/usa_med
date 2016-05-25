/**
 * 
 */
package com.usamd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.MedCondSymptomDAO;
import com.usamd.modelBean.MedCondSymptomBean;

// TODO: Auto-generated Javadoc
/**
 * The Class MedCondSymptomService.
 *
 * @author USAWebMD
 */
@Service
public class MedCondSymptomService {

  /** The med cond symptom dao. */
  @Autowired
  private MedCondSymptomDAO medCondSymptomDAO;

  /**
   * Insert symptoms.
   *
   * @param smpList the smp list
   * @return true, if successful
   */
  public boolean insertSymptoms(List<MedCondSymptomBean> smpList) {
    return medCondSymptomDAO.insertBatch(smpList);
  }

  /**
   * Fetch record by mc id and smp id.
   *
   * @param mcIds the mc ids
   * @param smpIds the smp ids
   * @return the list
   */
  public List<MedCondSymptomBean> fetchRecordByMcIdAndSmpId(String mcIds, String smpIds) {
    return medCondSymptomDAO.fetchRecordByMcIdAndSmpId(mcIds, smpIds);
  }

}
