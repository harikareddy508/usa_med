/**
 * 
 */
package com.usamd.modelBean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.usamd.constants.GlobalConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorBean.
 *
 * @author USAWebMD
 */
public class DoctorBean extends AbstractUserBean {



  /** The doctor id. */
  private String doctorId;
  
  /** The center id. */
  private String centerId;

  /** The date of practice. */
  @DateTimeFormat(pattern = GlobalConstants.DATE_FORMAT_MM_DD_YYYY)
  private Date dateOfPractice;

  /** The specializations. */
  private String specialization;
  
  /** The health center. */
  private HealthCenterBean healthCenter;
  
  
  /**
   * Gets the doctor id.
   *
   * @return the doctor id
   */
  public String getDoctorId() {
    return doctorId;
  }

  /**
   * Sets the doctor id.
   *
   * @param doctorId the new doctor id
   */
  public void setDoctorId(String doctorId) {
    this.doctorId = doctorId;
  }

  /**
   * Gets the date of practice.
   *
   * @return the date of practice
   */
  public Date getDateOfPractice() {
    return dateOfPractice;
  }

  /**
   * Sets the date of practice.
   *
   * @param dateOfPractice the new date of practice
   */
  public void setDateOfPractice(Date dateOfPractice) {
    this.dateOfPractice = dateOfPractice;
  }

  /**
   * Gets the specializations.
   *
   * @return the specializations
   */
  public String getSpecialization() {
    return specialization;
  }

  /**
   * Sets the specializations.
   *
   * @param specialization the new specialization
   */
  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }

  
  /**
   * Gets the health center.
   *
   * @return the healthCenter
   */
  public HealthCenterBean getHealthCenter() {
    return healthCenter;
  }

  /**
   * Sets the health center.
   *
   * @param healthCenter the healthCenter to set
   */
  public void setHealthCenter(HealthCenterBean healthCenter) {
    this.healthCenter = healthCenter;
  }

  /**
   * Gets the center id.
   *
   * @return the centerId
   */
  public String getCenterId() {
    return centerId;
  }

  /**
   * Sets the center id.
   *
   * @param centerId the centerId to set
   */
  public void setCenterId(String centerId) {
    this.centerId = centerId;
  }

}
