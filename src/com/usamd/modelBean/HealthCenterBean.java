/**
 * 
 */
package com.usamd.modelBean;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class HealthCenterBean.
 *
 * @author USAWebMD
 */
public class HealthCenterBean extends AbstractBean{
  
  /** The center id. */
  private String centerId;
  
  /** The center name. */
  private String centerName;
  
  /** The address bean. */
  private AddressBean address;
  
  /** The doctors list. */
  private List<DoctorBean> doctorsList;
  
  
  
  /**
   * Gets the center id.
   *
   * @return the center id
   */
  public String getCenterId() {
    return centerId;
  }
  
  /**
   * Sets the center id.
   *
   * @param centerId the new center id
   */
  public void setCenterId(String centerId) {
    this.centerId = centerId;
  }
  
  /**
   * Gets the center name.
   *
   * @return the center name
   */
  public String getCenterName() {
    return centerName;
  }
  
  /**
   * Sets the center name.
   *
   * @param centerName the new center name
   */
  public void setCenterName(String centerName) {
    this.centerName = centerName;
  }
  
  /**
   * Gets the address bean.
   *
   * @return the address bean
   */
  public AddressBean getAddress() {
    return address;
  }
  
  /**
   * Sets the address bean.
   *
   * @param address the new address
   */
  public void setAddress(AddressBean address) {
    this.address = address;
  }

  /**
   * Gets the doctors list.
   *
   * @return the doctorsList
   */
  public List<DoctorBean> getDoctorsList() {
    return doctorsList;
  }

  /**
   * Sets the doctors list.
   *
   * @param doctorsList the doctorsList to set
   */
  public void setDoctorsList(List<DoctorBean> doctorsList) {
    this.doctorsList = doctorsList;
  } 
  
  
}
