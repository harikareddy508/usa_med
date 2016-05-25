/**
 * 
 */
package com.usamd.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.modelBean.MedicineInfoBean;
// TODO: Auto-generated Javadoc
/**
 * The Class MedicineInfoDAO.
 *
 * @author USAWebMD
 */
@Repository
public class MedicineInfoDAO extends AbstractDAO {
  
  
  
  /**
   * Insert medicine.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean insertMedicine(MedicineInfoBean bean){
    String genMedId = generateMedicineId();
    StringBuilder query = new StringBuilder("INSERT INTO ").append(AbstractDAO.MEDICINE_INFO_TABLE);
    query.append( "(MED_ID,MED_NAME,USES,COLOR) VALUES(?,?,?,?)");
    getJdbcTemplate().update(query.toString(),new Object[]{genMedId,bean.getMedName(),bean.getUses(),bean.getColor()});
    bean.setMedId(genMedId);
    return true;
  }
  
  /**
   * Update medicine.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateMedicine(MedicineInfoBean bean){
    StringBuilder query = new StringBuilder("UPDATE ").append(AbstractDAO.MEDICINE_INFO_TABLE);
    query.append( " SET MED_NAME=?,USES=?,COLOR=? WHERE MED_ID=?");
    getJdbcTemplate().update(query.toString(),new Object[]{bean.getMedName(),bean.getUses(),bean.getColor(),bean.getMedId()});
    return true;
  }
  
  /**
   * Delete medicine.
   *
   * @param medId the med id
   * @return true, if successful
   */
  public boolean deleteMedicine(String medId){
    StringBuilder query = new StringBuilder("DELETE FROM ").append(AbstractDAO.MEDICINE_INFO_TABLE);
    query.append( " WHERE MED_ID=?");
    getJdbcTemplate().update(query.toString(),new Object[]{medId});
    return true;
  }
  
  
  /**
   * Fetch medicine by med id.
   *
   * @param bean the bean
   * @return the list
   */
  public List<MedicineInfoBean> fetchMedicines(MedicineInfoBean bean){
    try{
      StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.MEDICINE_INFO_TABLE);
      StringBuilder whereClause = new StringBuilder("");
      if(bean.getMedId()!=null && !bean.getMedId().isEmpty()){
        if(whereClause.length()>0){
          whereClause.append(" AND ");
        }
        whereClause.append(" MED_ID = '"+bean.getMedId()+"'");
      }
      if(bean.getMedName()!=null && !bean.getMedName().isEmpty()){
        if(whereClause.length()>0){
          whereClause.append(" AND ");
        }
        whereClause.append("  MED_NAME LIKE '"+bean.getMedName()+"'");
      }
      if(bean.getColor()!=null && !bean.getColor().isEmpty()){
        if(whereClause.length()>0){
          whereClause.append(" AND ");
        }
        whereClause.append("  COLOR = '"+bean.getColor()+"'");
      }
      if(whereClause.length()>0){
        query.append(" WHERE ").append(whereClause);
      }
     
      query.append(" ORDER BY MED_NAME");
      return getJdbcTemplate().query(query.toString(), new BeanPropertyRowMapper<MedicineInfoBean>(MedicineInfoBean.class));}
    catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  
  
  
  
  /**
   * Generate medicine id.
   *
   * @return the string
   */
  private String generateMedicineId() {
    try {
      StringBuilder query =
          new StringBuilder("SELECT IFNULL(MAX(CAST(SUBSTRING(MED_ID,3) AS UNSIGNED)),0) FROM ").append(AbstractDAO.MEDICINE_INFO_TABLE);
      int i = getJdbcTemplate().queryForObject(query.toString(), null, Integer.class);
      String genCenterId = "MD" + String.format("%03d", i + 1);
      return genCenterId;

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  
  

}
