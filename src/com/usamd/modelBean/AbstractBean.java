/**
 * 
 */
package com.usamd.modelBean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.usamd.constants.GlobalConstants;

// TODO: Auto-generated Javadoc
/**
 * This class acts as a model bean for carrying common information like create user and update user
 * details. This bean also holds bean constants.
 *
 * @author USAWebMD
 */
public abstract class AbstractBean {

  /*---- Begin - BEAN CONSTANTS --------------------------- */

  /** The Constant LOGIN_BEAN. */
  public static final String LOGIN_BEAN = "loginBean";

  /** The Constant USER_BEAN. */
  public static final String WEB_USER_BEAN = "webUserBean";
  
  /** The Constant DOCTOR_BEAN. */
  public static final String DOCTOR_BEAN = "doctorBean";
  
  /** The Constant ADDRESS_BEAN. */
  public static final String ADDRESS_BEAN = "webUserBean";
  
  /** The Constant BMI_CAL_VIEW_BEAN. */
  public static final String BMI_CAL_VIEW_BEAN = "bmiCalViewBean";
  
  /** The Constant BMI_CATEGORY_BEAN. */
  public static final String BMI_CATEGORY_BEAN = "bmiCategoryBean";
  
  /** The Constant HEALTH_CENTER_BEAN. */
  public static final String HEALTH_CENTER_BEAN = "healthCenterBean";
  
  /** The Constant MEDICINE_INFO_BEAN. */
  public static final String MEDICINE_INFO_BEAN = "medicineInfoBean";
  
  /** The Constant SECTION_UI_BEAN. */
  public static final String HOME_SCREEN_BEAN = "homeScreenBean";
  
  /** The Constant SECTION_UI_BEAN. */
  public static final String SECTION_UI_BEAN = "sectionUIBean";
  
  /** The Constant BODY_PART_SYMPTOM_BEAN. */
  public static final String BODY_PART_SYMPTOM_BEAN = "bodyPartSymptomBean";
  
  /** The Constant MEDICAL_CONDITION_BEAN. */
  public static final String MEDICAL_CONDITION_BEAN = "medicalConditionBean";

  /*---- End - BEAN CONSTANTS --------------------------- */



  /** The create user. */
  private String createUser;

  /** The create date. */
  @DateTimeFormat(pattern = GlobalConstants.DATE_FORMAT_MM_DD_YYYY)
  private Date createDate = new Date();

  /** The update user. */
  private String updateUser;


  /** The update date. */
  private Date updateDate=new Date();

  /**
   * Gets the creates the user.
   *
   * @return the creates the user
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * Sets the creates the user.
   *
   * @param createUser the new creates the user
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  /**
   * Gets the creates the date.
   *
   * @return the creates the date
   */
  public Date getCreateDate() {
    return createDate;
  }

  /**
   * Sets the creates the date.
   *
   * @param createDate the new creates the date
   */
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  /**
   * Gets the update user.
   *
   * @return the update user
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * Sets the update user.
   *
   * @param updateUser the new update user
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  /**
   * Gets the update date.
   *
   * @return the update date
   */
  public Date getUpdateDate() {
    return updateDate;
  }

  /**
   * Sets the update date.
   *
   * @param updateDate the new update date
   */
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
}
