/**
 * 
 */
package com.usamd.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.usamd.modelBean.AddressBean;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressDAO.
 *
 * @author USAWebMD
 */
@Repository
public class AddressDAO extends AbstractDAO {
  /** The Constant AND. */
  private static final String AND = " AND ";

  /** The Constant WHERE_CLAUSE. */
  private static final String WHERE_CLAUSE = " WHERE ";

  /** The Constant SINGLE_QUOT. */
  private static final String SINGLE_QUOT = "'";

  /**
   * Adds the address.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addAddress(AddressBean bean) {
    StringBuilder query =
        new StringBuilder("INSERT INTO ").append(AbstractDAO.ADDRESS_TABLE)
        .append("(RESIDENT_ID,RESIDENT_TYPE,ADDR_LINE1,ADDR_LINE2,")
        .append("CITY,STATE,ZIPCODE,CREATE_USER,CREATE_DATE)").append(" VALUES ")
        .append("(? ,? ,? ,? ,? ,? ,?, ? ,NOW())");
    getJdbcTemplate().update(query.toString(), bean.getResidentId(), bean.getResidentType(),
        bean.getAddrLine1(), bean.getAddrLine2(), bean.getCity(), bean.getState(),
        bean.getZipcode(), bean.getCreateUser());
    return true;
  }


  /**
   * Update address.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateAddress(AddressBean bean) {
    StringBuilder query =
        new StringBuilder("UPDATE ").append(AbstractDAO.ADDRESS_TABLE)
        .append(" SET ADDR_LINE1=?,ADDR_LINE2=?,CITY=?,STATE=?,ZIPCODE=?,")
        .append("UPDATE_USER=?,UPDATE_DATE=NOW() WHERE RESIDENT_ID=?");

    getJdbcTemplate().update(query.toString(), bean.getAddrLine1(), bean.getAddrLine2(),
        bean.getCity(), bean.getState(), bean.getZipcode(),
        bean.getUpdateUser(), bean.getResidentId());
    return true;
  }

  /**
   * Fetch address.
   *
   * @param bean the bean
   * @return the list
   */
  public List<AddressBean> fetchAddress(AddressBean bean) {
    StringBuilder whereClause = new StringBuilder("");
    StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.ADDRESS_TABLE);
    List<AddressBean> fetchedAddrList = null;
    if (bean.getResidentId() != null && !bean.getResidentId().isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("RESIDENT_ID = '");
      whereClause.append(bean.getResidentId());
      whereClause.append(SINGLE_QUOT);
    }
    if (bean.getResidentType() != null && !bean.getResidentType().isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("RESIDENT_TYPE = '");
      whereClause.append(bean.getResidentType());
      whereClause.append(SINGLE_QUOT);
    }
    if (bean.getCity() != null && !bean.getCity().isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("CITY = '");
      whereClause.append(bean.getCity());
      whereClause.append(SINGLE_QUOT);
    }
    if (bean.getZipcode() != null && !bean.getZipcode().isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("ZIPCODE = '");
      whereClause.append(bean.getZipcode());
      whereClause.append(SINGLE_QUOT);
    }
    if (bean.getState() != null && !bean.getState().isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("STATE LIKE '");
      whereClause.append(bean.getState());
      whereClause.append(SINGLE_QUOT);
    }
    if (whereClause.length() > 0) {
      query.append(whereClause);
    }

    fetchedAddrList =
        (List<AddressBean>) getJdbcTemplate().query(query.toString(),
            new BeanPropertyRowMapper<AddressBean>(AddressBean.class));
    return fetchedAddrList;
  }

  /**
   * Delete address.
   *
   * @param residentId the resident id
   * @return true, if successful
   */
  public boolean deleteAddress(String residentId) {
    StringBuilder query =
        new StringBuilder("DELETE FROM ").append(AbstractDAO.ADDRESS_TABLE).append(
            " WHERE RESIDENT_ID=?");
    getJdbcTemplate().update(query.toString(), residentId);
    return true;
  }

  /**
   * Fetch address by zipcode.
   *
   * @param zipcode the zipcode
   * @param residentType the resident type
   * @return the list
   */
  public List<AddressBean> fetchAddressByZipcode(String zipcode,String residentType){
    List<AddressBean> fetchedAddrList = null;
    StringBuilder whereClause = new StringBuilder("");
    StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.ADDRESS_TABLE);
    if (zipcode != null && !zipcode.isEmpty()) {     
      query.append(WHERE_CLAUSE)
      .append(" ZIPCODE = '")
      .append(zipcode)
      .append(SINGLE_QUOT);
    }
    if (residentType != null && !residentType.isEmpty()) {
      if (whereClause.length() <= 0) {
        whereClause.append(WHERE_CLAUSE);
      } else {
        whereClause.append(AND);
      }
      whereClause.append("RESIDENT_TYPE = '");
      whereClause.append(residentType);
      whereClause.append(SINGLE_QUOT);
    }
    fetchedAddrList =
        (List<AddressBean>) getJdbcTemplate().query(query.toString(),
            new BeanPropertyRowMapper<AddressBean>(AddressBean.class));
    return fetchedAddrList;
  }


  /**
   * Fetch doc address beween zipcodes.
   *
   * @param zipcode the zipcode
   * @return the list
   */
  public List<AddressBean> fetchDocAddressBeweenZipcodes(String zipcode){
    List<AddressBean> fetchedAddrList = null;
    StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.ADDRESS_TABLE).append(" WHERE RESIDENT_TYPE = 'C' ");
    if (zipcode != null && !zipcode.isEmpty()) {     
      query.append(" AND ZIPCODE BETWEEN '"+zipcode+"'-5 AND '"+zipcode+"'+5");
    }
    fetchedAddrList =
        (List<AddressBean>) getJdbcTemplate().query(query.toString(),
            new BeanPropertyRowMapper<AddressBean>(AddressBean.class));
    return fetchedAddrList;
  }

  
  /**
   * Search address by resident ids.
   *
   * @param residentIds the resident ids
   * @param residentType the resident type
   * @param zipcode the zipcode
   * @return the list
   */
  public List<AddressBean> searchAddressByResidentIds(String residentIds, String residentType,String zipcode) {
    StringBuilder query =
        new StringBuilder("SELECT * FROM ").append(AbstractDAO.ADDRESS_TABLE).append(
            " WHERE RESIDENT_ID IN (" + residentIds + ") AND RESIDENT_TYPE='"+residentType+"'");
    if(zipcode!=null && !zipcode.isEmpty()){
      query.append(" AND ZIPCODE BETWEEN '"+zipcode+"'-5 AND '"+zipcode+"'+5");
    }
    List<AddressBean> fetchedList = null;
    fetchedList =
        (List<AddressBean>) getJdbcTemplate().query(query.toString(),
            new BeanPropertyRowMapper<AddressBean>(AddressBean.class));
    return fetchedList;
  }


}
