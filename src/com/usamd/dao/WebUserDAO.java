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
import com.usamd.modelBean.WebUserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUserDAO.
 *
 * @author USAWebMD
 */
@Repository("webUserDAO")
public class WebUserDAO extends AbstractDAO {
  /** The Constant AND. */
  private static final String AND = " AND ";

  /** The Constant WHERE_CLAUSE. */
  private static final String WHERE_CLAUSE = " WHERE ";

  /** The Constant BLANK_STRING. */
  private static final String BLANK_STRING = "";

  /** The Constant SINGLE_QUOT. */
  private static final String SINGLE_QUOT = "'";

  /**
   * This method will add the user details in the database.
   *
   * @param userBean the user bean
   * @return true, if successful
   */
  public boolean addUser(WebUserBean userBean) {
    String dob = null;
    String genUsername = generateUsername(userBean.getFirstName(), userBean.getLastName());
    userBean.setCreateUser(genUsername);
    if (userBean.getDateOfBirth() != null) {
      SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DATE_FORMAT_YYYY_MM_DD);
      dob = sdf.format(userBean.getDateOfBirth());
    }

    StringBuilder query =
        new StringBuilder("INSERT INTO ").append(AbstractDAO.WEB_USER_TABLE)
            .append("(USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,ROLE_CD,DATE_OF_BIRTH,GENDER_CD,")
            .append("EMAIL_ID,PHONE_NUMBER,CREATE_USER,")
            .append(" SECURITY_QUES_CD, SECURITY_ANS,CREATE_DATE)").append(" VALUES ")
            .append("(? ,? ,? ,? ,? ,? ,?, ? , ? , ? , ? , ? ,NOW())");
    getJdbcTemplate().update(query.toString(), genUsername, String.valueOf(userBean.getPassword()),
        userBean.getFirstName(), userBean.getLastName(), userBean.getRoleCd(), dob,
        userBean.getGenderCd(), userBean.getEmailId(), userBean.getPhoneNumber(),
        userBean.getCreateUser(), userBean.getSecurityQuesCd(), userBean.getSecurityAns());
    userBean.setUsername(genUsername);
    return true;
  }

  /**
   * Update user.
   *
   * @param userBean the user bean
   * @return true, if successful
   */
  public boolean updateUser(WebUserBean userBean) {
    String dob = null;
    if (userBean.getDateOfBirth() != null) {
      SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DATE_FORMAT_YYYY_MM_DD);
      dob = sdf.format(userBean.getDateOfBirth());
    }
    StringBuilder query =
        new StringBuilder("UPDATE ")
            .append(AbstractDAO.WEB_USER_TABLE)
            .append(" SET FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,GENDER_CD=?,EMAIL_ID=?,PHONE_NUMBER=?,")
            .append("UPDATE_USER=?,SECURITY_QUES_CD=?, SECURITY_ANS=?,PASSWORD=?,UPDATE_DATE=NOW()")
            .append(" WHERE USERNAME=?");
    getJdbcTemplate().update(query.toString(), userBean.getFirstName(), userBean.getLastName(),
        dob,userBean.getGenderCd(), userBean.getEmailId(), userBean.getPhoneNumber(),
        userBean.getUpdateUser(), userBean.getSecurityQuesCd(), userBean.getSecurityAns(),
        String.valueOf(userBean.getPassword()), userBean.getUsername());
    return true;
  }


  /**
   * Delete user details.
   *
   * @param username the username
   * @return true, if successful
   */
  public boolean deleteUserDetails(String username) {
    StringBuilder query =
        new StringBuilder("DELETE FROM ").append(AbstractDAO.WEB_USER_TABLE).append(
            " WHERE USERNAME=?");
    getJdbcTemplate().update(query.toString(), username);
    return true;
  }


  /**
   * This method will update the password details for the specified user.
   *
   * @param password the password
   * @param username the username
   * @param updateUser the update user
   * @return true, if successful
   */
  public boolean updatePassword(char[] password, String username, String updateUser) {
    StringBuilder query =
        new StringBuilder("UPDATE ").append(AbstractDAO.WEB_USER_TABLE).append(
            " SET PASSWORD = ?,UPDATE_USER=?,UPDATE_DATE=NOW() WHERE USERNAME = ?");
    try {
      getJdbcTemplate().update(query.toString(), String.valueOf(password), updateUser, username);
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }



  /**
   * Fetch users.
   *
   * @param userBean the user bean
   * @return the list
   */
  public List<WebUserBean> fetchUsers(WebUserBean userBean) {
    List<WebUserBean> fetchedUserList = null;
    StringBuilder query = new StringBuilder("SELECT * FROM ").append(AbstractDAO.WEB_USER_TABLE);
    StringBuilder whereClause = new StringBuilder(BLANK_STRING);
    if (userBean != null) {
      if (userBean.getUsername() != null && !userBean.getUsername().isEmpty()) {
        if (whereClause.length() <= 0) {
          whereClause.append(WHERE_CLAUSE);
        } else {
          whereClause.append(AND);
        }
        whereClause.append("USERNAME LIKE '");
        whereClause.append(userBean.getUsername());
        whereClause.append(SINGLE_QUOT);
      }
      if (userBean.getFirstName() != null && !userBean.getFirstName().isEmpty()) {
        if (whereClause.length() <= 0) {
          whereClause.append(WHERE_CLAUSE);
        } else {
          whereClause.append(AND);
        }
        whereClause.append("FIRST_NAME LIKE '");
        whereClause.append(userBean.getFirstName());
        whereClause.append(SINGLE_QUOT);
      }

      if (userBean.getLastName() != null && !userBean.getLastName().isEmpty()) {
        if (whereClause.length() <= 0) {
          whereClause.append(WHERE_CLAUSE);
        } else {
          whereClause.append(AND);
        }
        whereClause.append("LAST_NAME LIKE '");
        whereClause.append(userBean.getLastName());
        whereClause.append(SINGLE_QUOT);
      }
      if (userBean.getDateOfBirth() != null) {
        // DATE_FORMAT(NOW(),'%m-%d-%Y');
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd/YYYY");

        String condition =
            " DATE_FORMAT(DATE_OF_BIRTH,'%m/%d/%Y') = '" + sf.format(userBean.getDateOfBirth())
                + "'";
        whereClause.append(whereClause.length() > 0 ? AND + condition : " WHERE " + condition);
      }
      if (userBean.getEmailId() != null && !userBean.getEmailId().isEmpty()) {
        if (whereClause.length() <= 0) {
          whereClause.append(WHERE_CLAUSE);
        } else {
          whereClause.append(AND);
        }
        whereClause.append("EMAIL_ID LIKE '");
        whereClause.append(userBean.getEmailId());
        whereClause.append(SINGLE_QUOT);
      }

      if (whereClause.length() > 0) {
        query.append(whereClause);
      }
    }
    fetchedUserList =
        (List<WebUserBean>) getJdbcTemplate().query(query.toString(),
            new BeanPropertyRowMapper<WebUserBean>(WebUserBean.class));

    return fetchedUserList;

  }


  /**
   * Fetch user by username.
   *
   * @param username the username
   * @return the web user bean
   */
  public WebUserBean fetchUserByUsername(String username) {

    try {
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.WEB_USER_TABLE).append(
              " WHERE USERNAME=? ");
      return getJdbcTemplate().queryForObject(query.toString(), new Object[] {username},
          new BeanPropertyRowMapper<WebUserBean>(WebUserBean.class));

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }


  /**
   * Fetch user by email id.
   *
   * @param emailId the email id
   * @return the web user bean
   */
  public WebUserBean fetchUserByEmailId(String emailId) {

    try {
      StringBuilder query =
          new StringBuilder("SELECT * FROM ").append(AbstractDAO.WEB_USER_TABLE).append(
              " WHERE EMAIL_ID=? ");
      return getJdbcTemplate().queryForObject(query.toString(), new Object[] {emailId},
          new BeanPropertyRowMapper<WebUserBean>(WebUserBean.class));

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Generate username.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @return the string
   */
  private String generateUsername(String firstName, String lastName) {
    int i = 1;
    StringBuilder str = new StringBuilder(firstName.substring(0, i)).append(lastName);
    WebUserBean userBean;
    userBean = fetchUserByUsername(str.toString());

    while (userBean != null) {
      i++;
      str = new StringBuilder(firstName.substring(0, i)).append(lastName);
      userBean = fetchUserByUsername(str.toString());
    }
    return str.toString();
  }


}
