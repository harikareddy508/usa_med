/**
 * 
 */
package com.usamd.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.constants.GlobalConstants;
import com.usamd.modelBean.DoctorBean;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorDAO.
 *
 * @author USAWebMD
 */
@Repository
public class DoctorDAO extends AbstractDAO {
  /** The Constant AND. */
  private static final String AND = " AND ";

  /** The Constant WHERE_CLAUSE. */
  private static final String WHERE_CLAUSE = " WHERE ";

  /** The Constant SINGLE_QUOT. */
  private static final String SINGLE_QUOT = "'";

  /**
   * Adds the doctor.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addDoctor(DoctorBean bean) {
    String genDoctorId = generateDoctorId(bean.getFirstName(), bean.getLastName());
    String dop = null;
    if (bean.getDateOfPractice() != null) {
      SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DATE_FORMAT_YYYY_MM_DD);
      dop = sdf.format(bean.getDateOfPractice());
    }
    StringBuilder query =
        new StringBuilder("INSERT INTO ")
            .append(AbstractDAO.DOCTOR_TABLE)
            .append(
                "(DOCTOR_ID, FIRST_NAME, LAST_NAME, EMAIL_ID, PHONE_NUMBER, DATE_OF_PRACTICE, CREATE_USER,")
            .append("SPECIALIZATION, CENTER_ID, CREATE_DATE) ").append(" VALUES ")
            .append("(? ,? ,? ,? ,? ,? ,?, ? , ? ,NOW())");
    getJdbcTemplate().update(query.toString(), genDoctorId, bean.getFirstName(),
        bean.getLastName(), bean.getEmailId(), bean.getPhoneNumber(), dop, bean.getCreateUser(),
        bean.getSpecialization(), bean.getCenterId());
    bean.setDoctorId(genDoctorId);
    return true;
  }


  /**
   * Update doctor.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateDoctor(DoctorBean bean) {
    String dop = null;
    if (bean.getDateOfPractice() != null) {
      SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DATE_FORMAT_YYYY_MM_DD);
      dop = sdf.format(bean.getDateOfPractice());
    }
    StringBuilder query =
        new StringBuilder("UPDATE ")
            .append(AbstractDAO.DOCTOR_TABLE)
            .append(
                " SET FIRST_NAME =?,LAST_NAME =?,EMAIL_ID =?,PHONE_NUMBER =?,DATE_OF_PRACTICE =?,")
            .append(
                "SPECIALIZATION =?,  CENTER_ID=?,UPDATE_USER=?,UPDATE_DATE=NOW() WHERE DOCTOR_ID=?");

    getJdbcTemplate().update(query.toString(), bean.getFirstName(), bean.getLastName(),
        bean.getEmailId(), bean.getPhoneNumber(), dop, bean.getSpecialization(),
        bean.getCenterId(), bean.getUpdateUser(),bean.getDoctorId());
    return true;
  }

  /**
   * Delete doctor.
   *
   * @param doctorId the doctor id
   * @return true, if successful
   */
  public boolean deleteDoctor(String doctorId) {
    StringBuilder query =
        new StringBuilder("DELETE FROM ").append(AbstractDAO.DOCTOR_TABLE).append(
            " WHERE DOCTOR_ID=?");
    getJdbcTemplate().update(query.toString(), doctorId);
    return true;
  }


  /**
   * Search doctor by doctorId, specialization and health centerId.
   *
   * @param bean the bean
   * @return the list
   */
  public List<DoctorBean> searchDoctor(DoctorBean bean) {
    StringBuilder whereClause = new StringBuilder("");
    StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.DOCTOR_TABLE);
    List<DoctorBean> fetchedList = null;
    if (bean.getDoctorId() != null && !bean.getDoctorId().isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("DOCTOR_ID = '");
      whereClause.append(bean.getDoctorId());
      whereClause.append(SINGLE_QUOT);
    }
    if (bean.getSpecialization() != null && !bean.getSpecialization().isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("SPECIALIZATION = '");
      whereClause.append(bean.getSpecialization());
      whereClause.append(SINGLE_QUOT);
    }
    if (bean.getHealthCenter().getCenterId() != null
        && !bean.getHealthCenter().getCenterId().isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("CENTER_ID = '");
      whereClause.append(bean.getHealthCenter().getCenterId());
      whereClause.append(SINGLE_QUOT);
    }

    if (whereClause.length() > 0) {
      query.append(whereClause);
    }

    fetchedList =
        (List<DoctorBean>) getJdbcTemplate().query(query.toString(),
            new BeanPropertyRowMapper<DoctorBean>(DoctorBean.class));
    return fetchedList;
  }

  /**
   * Fetch user by username.
   *
   * @param doctorId the doctor id
   * @return the web user bean
   */
  public DoctorBean fetchDoctoryById(String doctorId) {

    try {
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.DOCTOR_TABLE).append(
              " WHERE DOCTOR_ID=? ");
      return getJdbcTemplate().queryForObject(query.toString(), new Object[] {doctorId},
          new BeanPropertyRowMapper<DoctorBean>(DoctorBean.class));

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Fetch doctory by center ids.
   *
   * @param centerIds the center ids
   * @param specialization the specialization
   * @return the list
   */
  public List<DoctorBean> fetchDoctoryByCenterIds(String centerIds,String specialization) {
    StringBuilder whereClause = new StringBuilder("");
    try {
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.DOCTOR_TABLE);
      
      if(centerIds!=null &&!centerIds.isEmpty()){
        whereClause.append(" CENTER_ID IN (").append(centerIds).append(")");
      }
      if(specialization!=null &&!specialization.isEmpty()){
        if(whereClause.length()>0){
          whereClause.append(" AND SPECIALIZATION = ('").append(specialization).append("')");
        }
        else{
          whereClause.append(" SPECIALIZATION = ('").append(specialization).append("')");
        }
       
      }
      if(whereClause.length()>0){
        query.append(WHERE_CLAUSE).append(whereClause);
      }
     
              
      return getJdbcTemplate().query(query.toString(),
          new BeanPropertyRowMapper<DoctorBean>(DoctorBean.class));

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Generate doctor id.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @return the string
   */
  private String generateDoctorId(String firstName, String lastName) {
    int i = 1;
    StringBuilder str = new StringBuilder(firstName.substring(0, i)).append(lastName);
    DoctorBean doctorBean = fetchDoctoryById(str.toString());

    while (doctorBean != null) {
      i++;
      str = new StringBuilder(firstName.substring(0, i)).append(lastName);
      doctorBean = fetchDoctoryById(str.toString());
    }
    return str.toString();
  }
}
