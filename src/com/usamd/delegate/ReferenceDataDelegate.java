/**
 * 
 */
package com.usamd.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usamd.modelBean.RTStatesBean;
import com.usamd.modelBean.ReferenceDataBean;
import com.usamd.modelBean.ReferenceTableBean;
import com.usamd.service.ReferenceDataService;
import com.usamd.utilities.LoggerInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class ReferenceDataDelegate.
 *
 * @author LeaveManagementApplication
 */
@Component
public class ReferenceDataDelegate implements LoggerInterface {

  /** The reference data bean. */
  private static ReferenceDataBean referenceDataBean;
  /** The reference data service. */
  @Autowired
  private ReferenceDataService referenceDataService;
  

  static {
    referenceDataBean = new ReferenceDataBean();
  }

  /**
   * This method fetches the data for reference tables from the database at the server startup and
   * stores it in referenceMap of ReferenceDataBean.
   * 
   */
  @Autowired
  public void setReferenceDataBean() {
    Map<String, List<ReferenceTableBean>> refDataMap =
        new HashMap<String, List<ReferenceTableBean>>();
    List<String> refTableNames = referenceDataService.loadRefTableNames();
    for (String refTableName : refTableNames) {
      List<ReferenceTableBean> list = referenceDataService.fetchReferenceTableData(refTableName);
      refDataMap.put(refTableName, list);
      Map<String,List<RTStatesBean>> statesMap = loadAddressByState();
      referenceDataBean.setRtStatesMap(statesMap);
      applicationLogger.debug("REFERENCE DATE LOADED FOR TABLE :: " + refTableName);
    }
    referenceDataBean.setReferenceDataMap(refDataMap);
  }

  
  


  /**
   * Gets the reference data.
   *
   * @param refTableName the ref table name
   * @return the reference data
   */
  public static List<ReferenceTableBean> getReferenceData(String refTableName) {
    return referenceDataBean.getReferenceDataMap().get(refTableName.toUpperCase());
  }
  
  /**
   * Gets the states.
   *
   * @return the states
   */
  public static Map<String,List<RTStatesBean>> getStates() {
    return referenceDataBean.getRtStatesMap();
  }



  /**
   * Gets the description by code.
   *
   * @param code the code
   * @param refTableName the ref table name
   * @return the description by code
   */
  public static String getDescriptionByCode(String code, String refTableName) {

    List<ReferenceTableBean> refData =
        referenceDataBean.getReferenceDataMap().get(refTableName.toUpperCase());
    for (ReferenceTableBean bean : refData) {
      if (code.equals(bean.getCode())) {
        return bean.getDescription();
      }
    }
    return "";
  }
  
 /**
  * Load address by state.
  *
  * @return the map
  */
 public Map<String,List<RTStatesBean>> loadAddressByState(){
   Map<String,List<RTStatesBean>> statesMap = new HashMap<>();
   List<RTStatesBean> statesList = referenceDataService.fetchRTStatesData();
   List<RTStatesBean> stateCityList  = null;
   String  stateKey = "";
   for(RTStatesBean bean : statesList){
     if(!stateKey.equals(bean.getStateCd())){
       if(stateCityList!=null){
         statesMap.put(stateKey, stateCityList);
       }
       stateKey = bean.getStateCd();
       stateCityList  = new ArrayList<RTStatesBean>();
     }
    // else{
       stateCityList.add(bean);
    // }
    
   }
   
    return statesMap;
  }


}
