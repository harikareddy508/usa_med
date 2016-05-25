/**
 * 
 */
package com.usamd.modelBean;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MedicalCondtionBean.
 *
 * @author USAWebMD
 */
public class MedicalCondtionBean {
  
  /** The mc id. */
  private String mcId;
  
  /** The name. */
  private String name;
  
  /** The description. */
  private String description;
  
  /** The symptoms list. */
  private List<BodyPartSymptomBean> symptomsList;
  
  /** The medicine list. */
  private List<MedicineInfoBean> medicineList;
  
  /**
   * Gets the mc id.
   *
   * @return the mcId
   */
  public String getMcId() {
    return mcId;
  }
  
  /**
   * Sets the mc id.
   *
   * @param mcId the mcId to set
   */
  public void setMcId(String mcId) {
    this.mcId = mcId;
  }
  
  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Sets the name.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Gets the description.
   *
   * @return the decription
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * Gets the symptoms list.
   *
   * @return the symptomsList
   */
  public List<BodyPartSymptomBean> getSymptomsList() {
    return symptomsList;
  }
  
  /**
   * Sets the symptoms list.
   *
   * @param symptomsList the symptomsList to set
   */
  public void setSymptomsList(List<BodyPartSymptomBean> symptomsList) {
    this.symptomsList = symptomsList;
  }
  
  /**
   * Gets the medicine list.
   *
   * @return the medicineList
   */
  public List<MedicineInfoBean> getMedicineList() {
    return medicineList;
  }
  
  /**
   * Sets the medicine list.
   *
   * @param medicineList the medicineList to set
   */
  public void setMedicineList(List<MedicineInfoBean> medicineList) {
    this.medicineList = medicineList;
  }
  
}
