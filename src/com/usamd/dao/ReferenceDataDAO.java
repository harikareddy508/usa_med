/**
 * 
 */
package com.usamd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.modelBean.RTStatesBean;
import com.usamd.modelBean.ReferenceTableBean;

// TODO: Auto-generated Javadoc
/**
 * The Class ReferenceDataDAO.
 *
 * @author LeaveManagementApplication
 */
@Repository
public class ReferenceDataDAO extends AbstractDAO {

  /**
   * Fetch reference table data.
   *
   * @param refTableName the ref table name
   * @return the list
   */
  public List<ReferenceTableBean> fetchReferenceTableData(String refTableName) {       
    StringBuilder sql =
        new StringBuilder("SELECT * FROM ").append(refTableName)
            .append(" ORDER BY DESCRIPTION ASC");
    List<ReferenceTableBean> referenceData = new ArrayList<>();
    referenceData =
        getJdbcTemplate().query(sql.toString(),
            new BeanPropertyRowMapper<ReferenceTableBean>(ReferenceTableBean.class));
    return referenceData;
  }

  /**
   * This method loads the names of all the reference tables from Load ref table names.
   *
   * @return the list
   */
  public List<String> loadRefTableNames() {
    StringBuilder sql = new StringBuilder("SELECT * FROM ").append(RT_ALL_REF_TABLE);
    List<String> tableNames = new ArrayList<String>();
    tableNames = getJdbcTemplate().queryForList(sql.toString(), String.class);
    return tableNames;
  }
  
  /**
   * Fetch all states.
   *
   * @return the list
   */
  public List<ReferenceTableBean> fetchAllStates(){
    StringBuilder sql =
        new StringBuilder("SELECT DISTINCT STATE_CD,STATE_DESC FROM ").append(RT_STATES).append(
            " ORDER BY STATE_DESC");
    List<ReferenceTableBean> referenceData = new ArrayList<>();
    referenceData =
        getJdbcTemplate().query(sql.toString(),new RowMapper<ReferenceTableBean>(){
          @Override
          public ReferenceTableBean mapRow(final ResultSet rs, final int rownum) throws SQLException {
            final ReferenceTableBean bean = new ReferenceTableBean();
            bean.setCode(rs.getString("STATE_CD"));
            bean.setDescription(rs.getString("STATE_DESC"));
            return bean;
          }
          
        });
    return referenceData;
  }


  /**
   * Load rt states table data.
   *
   * @return the list
   */
  public List<RTStatesBean> loadRTStatesTableData() {
    StringBuilder sql =
        new StringBuilder("SELECT * FROM ").append(RT_STATES).append(
            " ORDER BY STATE_DESC");
    List<RTStatesBean> referenceData = new ArrayList<>();
    referenceData =
        getJdbcTemplate().query(sql.toString(),
            new BeanPropertyRowMapper<RTStatesBean>(RTStatesBean.class));

    return referenceData;
  }

}
