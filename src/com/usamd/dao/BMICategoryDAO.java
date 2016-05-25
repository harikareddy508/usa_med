/**
 * 
 */
package com.usamd.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.modelBean.BMICategoryBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BMICategoryDAO.
 *
 * @author USAWebMD
 */
@Repository
public class BMICategoryDAO extends AbstractDAO {
  
 
  /**
   * Insert bmi category.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean insertBMICategory(BMICategoryBean bean) {
    String genCatId=generateCatId();
    StringBuilder query =
        new StringBuilder("INSERT INTO ").append(AbstractDAO.BMI_CATEGORY_TABLE)
        .append("(CAT_ID,CATEGORY,BODY_TYPE,PERCENTILE_RANGE_HIGH,")
        .append("PERCENTILE_RANGE_LOW,HEALTH_PLAN,CREATE_USER,CREATE_DATE)").append(" VALUES ")
        .append("(? ,? ,? ,? ,? ,? ,?, NOW())");
    getJdbcTemplate().update(query.toString(),genCatId,bean.getCategory(),bean.getBodyType(),bean.getPercentileRangeHigh(),
        bean.getPercentileRangeLow(),bean.getHealthPlan(),bean.getCreateUser());
    bean.setCatId(genCatId);
    return true;
  }

  /**
   * Update bmi category.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateBMICategory(BMICategoryBean bean) {
    StringBuilder query =
        new StringBuilder("UPDATE ").append(AbstractDAO.BMI_CATEGORY_TABLE)
        .append(" SET PERCENTILE_RANGE_HIGH=?,PERCENTILE_RANGE_LOW=?,HEALTH_PLAN=?,")
        .append("UPDATE_USER=?,UPDATE_DATE=NOW() WHERE  CATEGORY=? AND BODY_TYPE=?");
    getJdbcTemplate().update(query.toString(),bean.getPercentileRangeHigh(),
        bean.getPercentileRangeLow(),bean.getHealthPlan(),bean.getUpdateUser(),bean.getCategory(),bean.getBodyType());
    return true;
  }
  
  
  /**
   * Checks if is category present.
   *
   * @param bean the bean
   * @return true, if is category present
   */
  public boolean isCategoryPresent(BMICategoryBean bean){
    try {
      StringBuilder query =
          new StringBuilder("SELECT COUNT(0) FROM ").append(AbstractDAO.BMI_CATEGORY_TABLE);
      query.append(" WHERE CATEGORY='").append(bean.getCategory()).append("' AND BODY_TYPE='").append(bean.getBodyType()).append("'");
      int i = getJdbcTemplate().queryForObject(query.toString(), null, Integer.class);
      if(i>0){
        return true;
      }
      return false;

    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }
  
  
  /**
   * Generate cat id.
   *
   * @return the string
   */
  private String generateCatId() {
    try {
      StringBuilder query =
          new StringBuilder("SELECT IFNULL(MAX(CAST(SUBSTRING(CAT_ID,3) AS UNSIGNED)),0) FROM ").append(AbstractDAO.BMI_CATEGORY_TABLE);
      int i = getJdbcTemplate().queryForObject(query.toString(), null, Integer.class);
      String genCatId = "BC" + String.format("%03d", i + 1);
      return genCatId;

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  
  
  /**
   * Fetch by body type.
   *
   * @param bodyType the body type
   * @param category the category
   * @return the list
   */
  public List<BMICategoryBean> fetchByBodyType(String bodyType,String category){
    try {
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.BMI_CATEGORY_TABLE).append(" WHERE BODY_TYPE='").append(bodyType)
          .append("'");
      if(category!=null && !category.isEmpty()){
        query.append(" AND CATEGORY = '").append(category).append("'");
      }
      query.append(" ORDER BY PERCENTILE_RANGE_LOW");
      List<BMICategoryBean> list =(List<BMICategoryBean>)getJdbcTemplate()
          .query(query.toString(),new BeanPropertyRowMapper<BMICategoryBean>(BMICategoryBean.class));
      return list;

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  
  
  /**
   * Checks if is range exist for other type.
   *
   * @param bean the bean
   * @return true, if is range exist for other type
   */
  public boolean isRangeExistForOtherType(BMICategoryBean bean){
    try {
      StringBuilder query =
          new StringBuilder("SELECT COUNT(0) FROM ").append(AbstractDAO.BMI_CATEGORY_TABLE);
      query.append(" WHERE PERCENTILE_RANGE_LOW<=").append(bean.getPercentileRangeHigh()).append(" AND ")
      .append(bean.getPercentileRangeLow()).append("< PERCENTILE_RANGE_HIGH AND CATEGORY<>'" ).append(bean.getCategory()+"'"
          + " AND BODY_TYPE='"+bean.getBodyType()+"'");
      int i = getJdbcTemplate().queryForObject(query.toString(), null, Integer.class);
      if(i>0){
        return true;
      }
      return false;

    } catch (EmptyResultDataAccessException e) {
      return false;
    }
    
  }
  
  /**
   * Fetch category by bmi.
   *
   * @param bmi the bmi
   * @param bodyType the body type
   * @return the BMI category bean
   */
  public BMICategoryBean fetchCategoryByBMI(String bmi,String bodyType){
    try {
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.BMI_CATEGORY_TABLE)
          .append(" WHERE BODY_TYPE='").append(bodyType)
          .append("' AND PERCENTILE_RANGE_LOW<=").append(bmi).append(" AND PERCENTILE_RANGE_HIGH>").append(bmi);
      List<BMICategoryBean> list =(List<BMICategoryBean>)getJdbcTemplate()
          .query(query.toString(),new BeanPropertyRowMapper<BMICategoryBean>(BMICategoryBean.class));
      return list!=null && list.size()>0? list.get(0) : null;

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  
  

}
