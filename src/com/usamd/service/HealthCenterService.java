/**
 * 
 */
package com.usamd.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.HealthCenterDAO;
import com.usamd.modelBean.HealthCenterBean;

// TODO: Auto-generated Javadoc
/**
 * The Class HealthCenterService.
 *
 * @author USAWebMD
 */
@Service
public class HealthCenterService {
  
  /** The health center dao. */
  @Autowired
  private HealthCenterDAO healthCenterDAO;

  /**
   * Adds the health center.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addHealthCenter(HealthCenterBean bean) {

    return healthCenterDAO.addHealthCenter(bean);
  }


  /**
   * Update health center.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateHealthCenter(HealthCenterBean bean) {


    return healthCenterDAO.updateHealthCenter(bean);
  }


  /**
   * Delete health center.
   *
   * @param centerId the center id
   * @return true, if successful
   */
  public boolean deleteHealthCenter(String centerId) {

    return healthCenterDAO.deleteHealthCenter(centerId);
  }



  /**
   * Search health center.
   *
   * @param centerIdSet the center id set
   * @return the list
   */
  public List<HealthCenterBean> searchHealthCenterByCenterIds(Set<String> centerIdSet) {
    String centerIds = "";
    int i = 0;
    if(centerIdSet!=null){
      int size = centerIdSet.size();
      for(String centerId : centerIdSet){
        if(i==size-1){
          centerIds = centerIds +"'"+centerId+"'";
          break;
        }
        centerIds = centerIds +"'"+centerId+"'"+",";
        i++;
      }
    }
    return healthCenterDAO.searchHealthCenterByCenterIds(centerIds);
  }
}
