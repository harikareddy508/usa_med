/**
 * 
 */
package com.usamd.modelBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class ReferenceDataBean.
 *
 * @author USAWebMD
 */
public class ReferenceDataBean {

  /** The reference data map. */
  private static Map<String, List<ReferenceTableBean>> referenceDataMap =
      new HashMap<String, List<ReferenceTableBean>>();
  
  /** The rt states map. */
  private static Map<String,List<RTStatesBean>> rtStatesMap = new HashMap<>();
  


  /**
   * Gets the reference data map.
   *
   * @return the reference data map
   */
  public Map<String, List<ReferenceTableBean>> getReferenceDataMap() {
    return referenceDataMap;
  }

  /**
   * Sets the reference data map.
   *
   * @param referenceDataMap the reference data map
   */
  public void setReferenceDataMap(Map<String, List<ReferenceTableBean>> referenceDataMap) {
    ReferenceDataBean.referenceDataMap = referenceDataMap;
  }

  /**
   * Gets the rt states map.
   *
   * @return the rtStatesMap
   */
  public  Map<String,List<RTStatesBean>> getRtStatesMap() {
    return rtStatesMap;
  }

  /**
   * Sets the rt states map.
   *
   * @param rtStatesMap the rtStatesMap to set
   */
  public  void setRtStatesMap(Map<String,List<RTStatesBean>> rtStatesMap) {
    ReferenceDataBean.rtStatesMap = rtStatesMap;
  }

  

 

}
