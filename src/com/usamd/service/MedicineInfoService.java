/**
 * 
 */
package com.usamd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.MedicineInfoDAO;
import com.usamd.modelBean.MedicineInfoBean;

// TODO: Auto-generated Javadoc
/**
 * The Class MedicineInfoService.
 *
 * @author USAWebMD
 */
@Service
public class MedicineInfoService {
  
  /** The medicine info dao. */
  @Autowired
  private MedicineInfoDAO medicineInfoDAO;
  
  /**
   * Insert or update medicine.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean insertOrUpdateMedicine(MedicineInfoBean bean){
    if(bean.getMedId()!=null && !bean.getMedId().isEmpty()){
      return medicineInfoDAO.updateMedicine(bean);      
    }
    else{
      return medicineInfoDAO.insertMedicine(bean);
    }
  }
  
  
  /**
   * Delete medicine.
   *
   * @param medId the med id
   * @return true, if successful
   */
  public boolean deleteMedicine(String medId){
    return medicineInfoDAO.deleteMedicine(medId);
  }  
  
  /**
   * Fetch medicines.
   *
   * @param bean the bean
   * @return the list
   */
  public List<MedicineInfoBean> fetchMedicines(MedicineInfoBean bean){
    return medicineInfoDAO.fetchMedicines(bean);
  }
  
  
}
