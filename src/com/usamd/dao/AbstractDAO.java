/**
 * 
 */
package com.usamd.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.usamd.utilities.LoggerInterface;

// TODO: Auto-generated Javadoc
/**
 * This class is the Parent class for all the DAO classes which contains common properties like
 * datasource, JDBC Template,etc.
 *
 * @author USAWebMD
 */
public abstract class AbstractDAO implements LoggerInterface {


  /** The Constant USER_TABLE. */
  protected static final String WEB_USER_TABLE = "WEB_USER";
  
  /** The Constant ADDRESS_TABLE. */
  protected static final String ADDRESS_TABLE = "ADDRESS";
  
  /** The Constant DOCTOR_TABLE. */
  protected static final String DOCTOR_TABLE = "DOCTOR";
  
  /** The Constant HEALTH_CENTER_TABLE. */
  protected static final String HEALTH_CENTER_TABLE = "HEALTH_CENTER";
  
  /** The Constant MEDICINE_INFO_TABLE. */
  protected static final String MEDICINE_INFO_TABLE = "MEDICINE_INFO";
  
  /** The Constant SECTION_UI_TABLE. */
  protected static final String SECTION_UI_TABLE = "SECTION_UI";
  
  /** The Constant BODY_PART_SYMPTOM_TABLE. */
  protected static final String BODY_PART_SYMPTOM_TABLE = "BODY_PART_SYMPTOM";
  
  /** The Constant MEDICAL_CONDITION_TABLE. */
  protected static final String MEDICAL_CONDITION_TABLE = "MEDICAL_CONDITION";
  
  /** The Constant MED_COND_SYMPTOM_TABLE. */
  protected static final String MED_COND_SYMPTOM_TABLE = "MED_COND_SYMPTOM";
  
  /** The Constant BMI_CATEGORY_TABLE. */
  protected static final String BMI_CATEGORY_TABLE = "BMI_CATEGORY";
  

  /** The Constant RT_ALL_REF_TABLE. */
  protected static final String RT_ALL_REF_TABLE = "RT_ALL_REF";
  
  /** The Constant RT_STATES. */
  protected static final String RT_STATES = "RT_STATES";


  /** The data source. */
  @Autowired
  protected DataSource dataSource;

  /** The jdbc template. */
  @Autowired
  private JdbcTemplate jdbcTemplate;


  /**
   * Gets the jdbc template.
   *
   * @return the jdbcTemplate
   */
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  /**
   * Sets the jdbc template.
   *
   * @param jdbcTemplate the jdbcTemplate to set
   */
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Gets the data source.
   *
   * @return the dataSource
   */
  public DataSource getDataSource() {
    return dataSource;
  }

  /**
   * Sets the data source.
   *
   * @param dataSource the dataSource to set
   */
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }


}
