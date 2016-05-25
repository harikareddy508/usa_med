/**
 * 
 */
package com.usamd.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.modelBean.SectionUIBean;

// TODO: Auto-generated Javadoc
/**
 * The Class SectionUIDAO.
 *
 * @author USAWebMD
 */
@Repository
public class SectionUIDAO extends AbstractDAO {

  /**
   * This method inserts the section informations in the database.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addSectionUI(SectionUIBean bean) {
    StringBuilder query =
        new StringBuilder("INSERT INTO ")
    .append(AbstractDAO.SECTION_UI_TABLE)
    .append(
        "(SECTION_ID, IMAGE, CAPTION, ACTUAL_URL, URL_DESC, CREATE_USER,CREATE_DATE) ")
        .append(" VALUES ").append("(? ,? ,? ,? ,? ,? ,NOW())");
    getJdbcTemplate().update(query.toString(), bean.getSectionId(), bean.getImage(),
        bean.getCaption(), bean.getActualUrl(), bean.getUrlDesc(), bean.getCreateUser());
    return true;
  }


  /**
   * This method updates the section information in the database based on the Section Id.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateSectionUI(SectionUIBean bean) {
    StringBuilder query =
        new StringBuilder("UPDATE ")
    .append(AbstractDAO.SECTION_UI_TABLE)
    .append(
        " SET IMAGE=?, CAPTION=?, ACTUAL_URL=?, URL_DESC=?,UPDATE_USER=?,UPDATE_DATE=NOW() WHERE SECTION_ID=? ");
    getJdbcTemplate().update(query.toString(), bean.getImage(), bean.getCaption(),
        bean.getActualUrl(), bean.getUrlDesc(), bean.getUpdateUser(), bean.getSectionId());
    return true;
  } 

  /**
   * Fetch section all section.
   *
   * @return the list
   */
  public List<SectionUIBean> fetchAllSections() {
    try{
      List<SectionUIBean> list = new ArrayList<SectionUIBean>();
      StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.SECTION_UI_TABLE);    
      list =
          (List<SectionUIBean>) getJdbcTemplate().query(query.toString(),
              new BeanPropertyRowMapper<SectionUIBean>(SectionUIBean.class));
      return list;}
    catch (EmptyResultDataAccessException e) {
      return null;
    }
  }  

  /**
   * Fetch section by section id.
   *
   * @param sectionId the section id
   * @return the section ui bean
   */
  public SectionUIBean fetchSectionBySectionId(String sectionId) {
    try{
      StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.SECTION_UI_TABLE);
      query.append(" WHERE SECTION_ID='").append(sectionId).append("'");
      SectionUIBean bean =
          (SectionUIBean) getJdbcTemplate().queryForObject(query.toString(),null,
              new BeanPropertyRowMapper<SectionUIBean>(SectionUIBean.class));
      return bean;}
    catch (EmptyResultDataAccessException e) {
      return null;
    }
  }   


}
