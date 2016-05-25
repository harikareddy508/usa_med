/**
 * 
 */
package com.usamd.modelBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BMICategoryBean.
 *
 * @author USAWebMD
 */
public class BMICategoryBean extends AbstractBean {
  
  /** The cat id. */
  private String catId;
  
  /** The category. */
  private String category;
  
  /** The body type. */
  private String bodyType;// Adult, Children
  
  /** The percentile range. */
  private String percentileRangeHigh;
  
  /** The percentile range low. */
  private String percentileRangeLow;
  
  /** The health plan. */
  private String healthPlan;
  
  
  

  /**
   * Gets the percentile range high.
   *
   * @return the percentileRangeHigh
   */
  public String getPercentileRangeHigh() {
    return percentileRangeHigh;
  }

  /**
   * Sets the percentile range high.
   *
   * @param percentileRangeHigh the percentileRangeHigh to set
   */
  public void setPercentileRangeHigh(String percentileRangeHigh) {
    this.percentileRangeHigh = percentileRangeHigh;
  }

  /**
   * Gets the percentile range low.
   *
   * @return the percentileRangeLow
   */
  public String getPercentileRangeLow() {
    return percentileRangeLow;
  }

  /**
   * Sets the percentile range low.
   *
   * @param percentileRangeLow the percentileRangeLow to set
   */
  public void setPercentileRangeLow(String percentileRangeLow) {
    this.percentileRangeLow = percentileRangeLow;
  }

  /**
   * Gets the cat id.
   *
   * @return the catId
   */
  public String getCatId() {
    return catId;
  }

  /**
   * Sets the cat id.
   *
   * @param catId the catId to set
   */
  public void setCatId(String catId) {
    this.catId = catId;
  }

  /**
   * Gets the category.
   *
   * @return the category
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the category.
   *
   * @param category the new category
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Gets the body type.
   *
   * @return the body type
   */
  public String getBodyType() {
    return bodyType;
  }

  /**
   * Sets the body type.
   *
   * @param bodyType the new body type
   */
  public void setBodyType(String bodyType) {
    this.bodyType = bodyType;
  }

 

  /**
   * Gets the health plan.
   *
   * @return the health plan
   */
  public String getHealthPlan() {
    return healthPlan;
  }

  /**
   * Sets the health plan.
   *
   * @param healthPlan the new health plan
   */
  public void setHealthPlan(String healthPlan) {
    this.healthPlan = healthPlan;
  }


}
