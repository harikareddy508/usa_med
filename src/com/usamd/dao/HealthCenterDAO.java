/**
 * 
 */
package com.usamd.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.constants.GlobalConstants;
import com.usamd.modelBean.HealthCenterBean;

// TODO: Auto-generated Javadoc
/**
 * The Class HealthCenterDAO.
 *
 * @author USAWebMD
 */
@Repository
public class HealthCenterDAO extends AbstractDAO {


  /**
   * Adds the health center.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addHealthCenter(HealthCenterBean bean) {
    String genHealthCenterId = generateHealthCenterId();

    StringBuilder query =
        new StringBuilder("INSERT INTO ").append(AbstractDAO.HEALTH_CENTER_TABLE)
            .append("(CENTER_ID,CENTER_NAME, CREATE_USER,CREATE_DATE)").append(" VALUES ")
            .append("(? ,? ,? ,NOW())");
    getJdbcTemplate().update(query.toString(), genHealthCenterId, bean.getCenterName(),
        bean.getCreateUser());
    bean.setCenterId(genHealthCenterId);
    return true;
  }


  /**
   * Update health center.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateHealthCenter(HealthCenterBean bean) {

    StringBuilder query =
        new StringBuilder("UPDATE ").append(AbstractDAO.HEALTH_CENTER_TABLE).append(
            " SET CENTER_NAME =?,UPDATE_USER=?,UPDATE_DATE=NOW() WHERE CENTER_ID=?");

    getJdbcTemplate().update(query.toString(), bean.getCenterName(), bean.getUpdateUser(),
        bean.getCenterId());
    return true;
  }


  /**
   * Delete health center.
   *
   * @param centerId the center id
   * @return true, if successful
   */
  public boolean deleteHealthCenter(String centerId) {
    StringBuilder query =
        new StringBuilder("DELETE FROM ").append(AbstractDAO.HEALTH_CENTER_TABLE).append(
            " WHERE CENTER_ID=?");
    getJdbcTemplate().update(query.toString(), centerId);
    return true;
  }



  /**
   * Search health center.
   *
   * @param centerIds the center ids
   * @return the list
   */
  public List<HealthCenterBean> searchHealthCenterByCenterIds(String centerIds) {
    StringBuilder query =
        new StringBuilder("SELECT * FROM ").append(AbstractDAO.HEALTH_CENTER_TABLE);
    if(centerIds!=null && !centerIds.isEmpty()){
      query.append(" WHERE CENTER_ID IN (" + centerIds + ")");
    }
    List<HealthCenterBean> fetchedList = null;
    fetchedList =
        (List<HealthCenterBean>) getJdbcTemplate().query(query.toString(),
            new BeanPropertyRowMapper<HealthCenterBean>(HealthCenterBean.class));
    return fetchedList;
  }

  /**
   * Generate health center id.
   *
   * @return the string
   */
  private String generateHealthCenterId() {
    try {
      StringBuilder query =
          new StringBuilder("SELECT IFNULL(MAX(CAST(SUBSTRING(CENTER_ID,2) AS UNSIGNED)),0) FROM ").append(AbstractDAO.HEALTH_CENTER_TABLE);
      int i = getJdbcTemplate().queryForObject(query.toString(), null, Integer.class);
      String genCenterId = GlobalConstants.RESIDENT_TYPE_CENTER + String.format("%04d", i + 1);
      return genCenterId;

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
}
