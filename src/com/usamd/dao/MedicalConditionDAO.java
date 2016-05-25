/**
 * 
 */
package com.usamd.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.modelBean.MedicalCondtionBean;

// TODO: Auto-generated Javadoc
/**
 * The Class MedicalConditionDAO.
 *
 * @author USAWebMD
 */
@Repository
public class MedicalConditionDAO extends AbstractDAO {

  /**
   * Insert medical condition.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean insertMedicalCondition(MedicalCondtionBean bean) {
    String genMcId = generateMcId();
    StringBuilder query =
        new StringBuilder("INSERT INTO ").append(AbstractDAO.MEDICAL_CONDITION_TABLE);
    query.append("(MC_ID,NAME,DESCRIPTION) VALUES(?,?,?)");
    getJdbcTemplate().update(query.toString(),
        new Object[] {genMcId, bean.getName(), bean.getDescription()});
    bean.setMcId(genMcId);
    return true;
  }


  /**
   * Generate smp id.
   *
   * @return the string
   */
  private String generateMcId() {
    try {
      StringBuilder query =
          new StringBuilder("SELECT IFNULL(MAX(CAST(SUBSTRING(MC_ID,3) AS UNSIGNED)),0) FROM ").append(AbstractDAO.MEDICAL_CONDITION_TABLE);
      int i = getJdbcTemplate().queryForObject(query.toString(), null, Integer.class);
      String genCenterId = "MC" + String.format("%03d", i + 1);
      return genCenterId;

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Fetch med cond based on smps.
   *
   * @param smpIds the smp ids
   * @return the list
   */
  public List<MedicalCondtionBean> fetchMedCondBasedOnSmps(String smpIds) {
    try {
      List<MedicalCondtionBean> list = null;
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.MEDICAL_CONDITION_TABLE);
      query.append(" WHERE MC_ID IN (SELECT MC_ID FROM ")
      .append(AbstractDAO.MED_COND_SYMPTOM_TABLE).append(" WHERE SMP_ID IN (" + smpIds + "))");
      list =
          (List<MedicalCondtionBean>) getJdbcTemplate().query(query.toString(),
              new BeanPropertyRowMapper<MedicalCondtionBean>(MedicalCondtionBean.class));
      return list;

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Fetch medical condtions by name or mc id.
   *
   * @param medCondName the med cond name
   * @param mcId the mc id
   * @return the list
   */
  public List<MedicalCondtionBean> fetchMedicalCondtionsByNameOrMcId(String medCondName,String mcId){
    try {
      List<MedicalCondtionBean> list = null;
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.MEDICAL_CONDITION_TABLE);
      StringBuilder whereClause = new StringBuilder("");
      if(medCondName!=null && !medCondName.isEmpty()){
        if(whereClause.length()>0){
          query.append(" NAME LIKE  ('"+medCondName+"')");
        }
        else{
          query.append(" WHERE NAME LIKE  ('"+medCondName+"')");
        }
      }
      if(mcId!=null && !mcId.isEmpty()){
        if(whereClause.length()>0){
          query.append(" MC_ID = '"+mcId+"'");
        }
        else{
          query.append(" WHERE MC_ID ='"+mcId+"'");
        }
      }
      query.append(whereClause);
      list =
          (List<MedicalCondtionBean>) getJdbcTemplate().query(query.toString(),
              new BeanPropertyRowMapper<MedicalCondtionBean>(MedicalCondtionBean.class));
      return list;

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }


  /**
   * Delete medical condtion.
   *
   * @param mcId the mc id
   * @return true, if successful
   */
  public boolean deleteMedicalCondtion(String mcId){
    StringBuilder query = new StringBuilder("DELETE FROM ").append(AbstractDAO.MEDICAL_CONDITION_TABLE);
    query.append( " WHERE MC_ID=?");
    getJdbcTemplate().update(query.toString(),new Object[]{mcId});
    return true;
  }

}
