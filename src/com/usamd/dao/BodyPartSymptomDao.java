/**
 * 
 */
package com.usamd.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.modelBean.BodyPartSymptomBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BodyPartSymptomDao.
 *
 * @author USAWebMD
 */
@Repository
public class BodyPartSymptomDao extends AbstractDAO {
    
    /**
     * Inert symptom.
     *
     * @param bean the bean
     * @return true, if successful
     */
    public boolean insertSymptom(BodyPartSymptomBean bean){
      String genSmpId = generateSmpId();
      StringBuilder query = new StringBuilder("INSERT INTO ").append(AbstractDAO.BODY_PART_SYMPTOM_TABLE);
      query.append( "(SMP_ID,BODY_PART,DESCRIPTION) VALUES(?,?,?)");
      getJdbcTemplate().update(query.toString(),new Object[]{genSmpId,bean.getBodyPart(),bean.getDescription()});
      bean.setSmpId(genSmpId);
      return true;
    }
    
    /**
     * Update symptom.
     *
     * @param bean the bean
     * @return true, if successful
     */
    public boolean updateSymptom(BodyPartSymptomBean bean){
      StringBuilder query = new StringBuilder("UPDATE ").append(AbstractDAO.BODY_PART_SYMPTOM_TABLE);
      query.append( " SET BODY_PART=?,DESCRIPTION=? WHERE SMP_ID=?");
      getJdbcTemplate().update(query.toString(),new Object[]{bean.getBodyPart(),bean.getDescription(),bean.getSmpId()});
      return true;
    }
    
    /**
     * Delete symptom.
     *
     * @param smpId the smp id
     * @return true, if successful
     */
    public boolean deleteSymptom(String smpId){
      StringBuilder query = new StringBuilder("DELETE FROM ").append(AbstractDAO.BODY_PART_SYMPTOM_TABLE);
      query.append( " WHERE SMP_ID=?");
      getJdbcTemplate().update(query.toString(),new Object[]{smpId});
      return true;
    }
    
    
    /**
     * Fetch body part symptoms.
     *
     * @param bodyPart the body part
     * @return the list
     */
    public List<BodyPartSymptomBean> fetchBodyPartSymptoms(String bodyPart){
      try{
        StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.BODY_PART_SYMPTOM_TABLE);
        if(bodyPart!=null && !bodyPart.isEmpty()){
          query.append(" WHERE BODY_PART='"+bodyPart+"'");
        }
        query.append(" ORDER BY BODY_PART, DESCRIPTION");
        return getJdbcTemplate().query(query.toString(), new BeanPropertyRowMapper<BodyPartSymptomBean>(BodyPartSymptomBean.class));}
      catch (EmptyResultDataAccessException e) {
        return null;
      }
    }
    
  /**
   * Fetch symptoms by mc id.
   *
   * @param mcId the mc id
   * @return the list
   */
  public List<BodyPartSymptomBean> fetchSymptomsByMcId(String mcId) {
    try {
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.BODY_PART_SYMPTOM_TABLE);
      query.append(" WHERE SMP_ID IN (SELECT SMP_ID FROM ")
          .append(AbstractDAO.MED_COND_SYMPTOM_TABLE).append(" WHERE MC_ID ='").append(mcId)
          .append("')");
      return getJdbcTemplate().query(query.toString(),
          new BeanPropertyRowMapper<BodyPartSymptomBean>(BodyPartSymptomBean.class));
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

    /**
     * Generate smp id.
     *
     * @return the string
     */
    private String generateSmpId() {
      try {
        StringBuilder query =
            new StringBuilder("SELECT IFNULL(MAX(CAST(SUBSTRING(SMP_ID,2) AS UNSIGNED)),0) FROM ").append(AbstractDAO.BODY_PART_SYMPTOM_TABLE);
        int i = getJdbcTemplate().queryForObject(query.toString(), null, Integer.class);
        String genCenterId = "S" + String.format("%04d", i + 1);
        return genCenterId;

      } catch (EmptyResultDataAccessException e) {
        return null;
      }
    }
    
    
    
}


