/**
 * 
 */
package com.usamd.modelBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BodyPartSymptomBean.
 *
 * @author USAWebMD
 */

public class BodyPartSymptomBean {
  
  /** The smp id. */
  private String smpId;
  
  /** The body part. */
  private String bodyPart;
  
  /** The description. */
  private String description;
  
  /**
   * Gets the smp id.
   *
   * @return the smpId
   */
  public String getSmpId() {
    return smpId;
  }
  
  /**
   * Sets the smp id.
   *
   * @param smpId the smpId to set
   */
  public void setSmpId(String smpId) {
    this.smpId = smpId;
  }
  
  /**
   * Gets the body part.
   *
   * @return the bodyPart
   */
  public String getBodyPart() {
    return bodyPart;
  }
  
  /**
   * Sets the body part.
   *
   * @param bodyPart the bodyPart to set
   */
  public void setBodyPart(String bodyPart) {
    this.bodyPart = bodyPart;
  }
  
  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * Sets the description.
   *
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }
 
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    BodyPartSymptomBean bean = (BodyPartSymptomBean)obj;
    return this.smpId.equals(bean.getSmpId());
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return 99*this.smpId.hashCode();
  }
  
}
