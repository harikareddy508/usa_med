/**
 * 
 */
package com.usamd.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.modelBean.MedCondSymptomBean;

// TODO: Auto-generated Javadoc
/**
 * The Class MedCondSymptomDAO.
 *
 * @author USAWebMD
 */
@Repository
public class MedCondSymptomDAO extends AbstractDAO{

  /**
   * Insert batch.
   *
   * @param smpList the smp list
   * @return true, if successful
   */
  public boolean insertBatch(final List<MedCondSymptomBean> smpList) {
    StringBuilder query = new StringBuilder("INSERT INTO ");
    query.append(AbstractDAO.MED_COND_SYMPTOM_TABLE).append("(MC_ID,SMP_ID)").append(" VALUES(?,?)");

    List<Object[]> parameters = new ArrayList<Object[]>();

    for (MedCondSymptomBean bean : smpList) {
      parameters.add(new Object[] {bean.getMcId(),bean.getSmpId()});
    }
    getJdbcTemplate().batchUpdate(query.toString(), parameters);
    return true;
  }

  /**
   * Fetch record by mc id and smp id.
   *
   * @param mcIds the mc ids
   * @param smpIds the smp ids
   * @return the list
   */
  public List<MedCondSymptomBean> fetchRecordByMcIdAndSmpId(String mcIds, String smpIds){
    try{
      StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.MED_COND_SYMPTOM_TABLE);
      StringBuilder whereClause = new StringBuilder("");
      if(mcIds!=null && !mcIds.isEmpty()){
        if(whereClause.length()>0){
          query.append(" MC_ID IN  ("+mcIds+")");
        }
        else{
          query.append(" WHERE MC_ID IN  ("+mcIds+")");
        }

      }
      if(smpIds!=null && !smpIds.isEmpty()){
        if(whereClause.length()>0){
          query.append(" SMP_ID IN  ("+smpIds+")");
        }
        else{
          query.append(" WHERE SMP_ID IN ("+smpIds+")");
        }

      }
      query.append(whereClause);
      return getJdbcTemplate().query(query.toString(), new BeanPropertyRowMapper<MedCondSymptomBean>(MedCondSymptomBean.class));}
    catch (EmptyResultDataAccessException e) {
      return null;
    }
  }



}
