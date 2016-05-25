/**
 * 
 */
package com.usamd.modelBean;

// TODO: Auto-generated Javadoc
/**
 * The Class MedicineInfoBean.
 *
 * @author USAWebMD
 */
public class MedicineInfoBean extends AbstractBean {
  
  /** The med id. */
  private String medId;
  
  /** The med name. */
  private String medName;
  
  /** The uses. */
  private String uses;
  
  /** The color. */
  private String color;

  /**
   * Gets the med id.
   *
   * @return the med id
   */
  public String getMedId() {
    return medId;
  }

  /**
   * Sets the med id.
   *
   * @param medId the new med id
   */
  public void setMedId(String medId) {
    this.medId = medId;
  }

  /**
   * Gets the med name.
   *
   * @return the med name
   */
  public String getMedName() {
    return medName;
  }

  /**
   * Sets the med name.
   *
   * @param medName the new med name
   */
  public void setMedName(String medName) {
    this.medName = medName;
  }

  /**
   * Gets the uses.
   *
   * @return the uses
   */
  public String getUses() {
    return uses;
  }

  /**
   * Sets the uses.
   *
   * @param uses the new uses
   */
  public void setUses(String uses) {
    this.uses = uses;
  }

  /**
   * Gets the color.
   *
   * @return the color
   */
  public String getColor() {
    return color;
  }

  /**
   * Sets the color.
   *
   * @param color the color to set
   */
  public void setColor(String color) {
    this.color = color;
  }

}
