/**
 * 
 */
package com.usamd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.ReferenceDataDAO;
import com.usamd.modelBean.RTStatesBean;
import com.usamd.modelBean.ReferenceTableBean;

// TODO: Auto-generated Javadoc
/**
 * The Class ReferenceDataService.
 *
 * @author LeaveManagementApplication
 */
@Service
public class ReferenceDataService {

  /** The reference data dao. */
  @Autowired
  private ReferenceDataDAO referenceDataDAO;


  /**
   * Fetch reference table data.
   *
   * @param refTableName the ref table name
   * @return the list
   */
  public List<ReferenceTableBean> fetchReferenceTableData(String refTableName) {
    if (refTableName != null && !refTableName.isEmpty()) {
      if("RT_STATES".equalsIgnoreCase(refTableName)){
        return referenceDataDAO.fetchAllStates();
      }
      return referenceDataDAO.fetchReferenceTableData(refTableName.toUpperCase());
    } else {
      return null;
    }
  }
  
  /**
   * Fetch rt states data.
   *
   * @return the list
   */
  public List<RTStatesBean> fetchRTStatesData() {
      return referenceDataDAO.loadRTStatesTableData();
   
  }

  /**
   * Load ref table names.
   *
   * @return the list
   */
  public List<String> loadRefTableNames() {
    return referenceDataDAO.loadRefTableNames();
  }
  
 
  
  
}
