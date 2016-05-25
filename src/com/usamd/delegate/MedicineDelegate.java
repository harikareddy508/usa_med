/**
 * 
 */
package com.usamd.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usamd.modelBean.MedicineInfoBean;
import com.usamd.service.MedicineInfoService;

// TODO: Auto-generated Javadoc
/**
 * The Class MedicineDelegate.
 *
 * @author USAWebMD
 */
@Component
public class MedicineDelegate {
  
  /** The medicine info service. */
  @Autowired
  private MedicineInfoService medicineInfoService;
  
  
  /**
   * Insert or update medcine.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean insertOrUpdateMedcine(MedicineInfoBean bean){
    return medicineInfoService.insertOrUpdateMedicine(bean);
  }
  
  
  /**
   * Delete medicine.
   *
   * @param medId the med id
   * @return true, if successful
   */
  public boolean deleteMedicine(String medId){
    return medicineInfoService.deleteMedicine(medId);
  }
  
  /**
   * Fetch medicines.
   *
   * @param bean the bean
   * @return the list
   */
  public List<MedicineInfoBean> fetchMedicines(MedicineInfoBean bean){
    return medicineInfoService.fetchMedicines(bean);
  }

}
