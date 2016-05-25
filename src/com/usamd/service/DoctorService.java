/**
 * 
 */
package com.usamd.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.DoctorDAO;
import com.usamd.modelBean.DoctorBean;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorService.
 *
 * @author USAWebMD
 */
@Service
public class DoctorService {
  
  /** The doctor dao. */
  @Autowired
  private DoctorDAO doctorDAO;


  /**
   * Adds the doctor.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addDoctor(DoctorBean bean) {
    return doctorDAO.addDoctor(bean);
  }


  /**
   * Update doctor.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateDoctor(DoctorBean bean) {
    return doctorDAO.updateDoctor(bean);
  }

  /**
   * Delete doctor.
   *
   * @param doctorId the doctor id
   * @return true, if successful
   */
  public boolean deleteDoctor(String doctorId) {
    return doctorDAO.deleteDoctor(doctorId);
  }


  /**
   * Search doctor by doctorId, specialization and health centerId.
   *
   * @param bean the bean
   * @return the list
   */
  public List<DoctorBean> searchDoctor(DoctorBean bean) {
    return doctorDAO.searchDoctor(bean);
  }
  
  /**
   * Fetch doctory by id.
   *
   * @param doctorId the doctor id
   * @return the doctor bean
   */
  public DoctorBean fetchDoctoryById(String doctorId) {
    return doctorDAO.fetchDoctoryById(doctorId);
  }
  
  /**
   * Fetch doctory by center ids.
   *
   * @param centerIdSet the center id set
   * @param specialization the specialization
   * @return the list
   */
  public List<DoctorBean> fetchDoctoryByCenterIds(Set<String> centerIdSet,String specialization) {
    String centerIds = "";
    int i = 0;
    int size = centerIdSet.size();
    for(String centerId : centerIdSet){
      if(i==size-1){
        centerIds = centerIds +"'"+centerId+"'";
        break;
      }
      centerIds = centerIds +"'"+centerId+"'"+",";
      i++;
    }
    System.out.println("Service CenterIds "+centerIdSet+ " string :: "+centerIds);
    return doctorDAO.fetchDoctoryByCenterIds(centerIds,specialization);
  }

 
}
