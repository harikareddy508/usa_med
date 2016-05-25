/**
 * 
 */
package com.usamd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.BMICategoryDAO;
import com.usamd.modelBean.BMICategoryBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BMICategoryService.
 *
 * @author USAWebMD
 */
@Service
public class BMICategoryService {
  
  /** The bmi category dao. */
  @Autowired
  private BMICategoryDAO bmiCategoryDAO;
  
  /**
   * Adds the or update bmi category.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addOrUpdateBMICategory(BMICategoryBean bean){
    boolean success = false;
    if(bmiCategoryDAO.isCategoryPresent(bean)){
      success=bmiCategoryDAO.updateBMICategory(bean);
    }
    else{
      success= bmiCategoryDAO.insertBMICategory(bean);
    }
    return success;
  }
  
  
  /**
   * Checks if is range exist for other type.
   *
   * @param bean the bean
   * @return true, if is range exist for other type
   */
  public boolean isRangeExistForOtherType(BMICategoryBean bean){
    return bmiCategoryDAO.isRangeExistForOtherType(bean);
  }
  
  /**
   * Fetch by body type.
   *
   * @param bodyType the body type
   * @param category the category
   * @return the list
   */
  public List<BMICategoryBean> fetchByBodyType(String bodyType,String category){
    return bmiCategoryDAO.fetchByBodyType(bodyType,category);
  }
  
  /**
   * Fetch category by bmi.
   *
   * @param bmi the bmi
   * @param bodyType the body type
   * @return the BMI category bean
   */
  public BMICategoryBean fetchCategoryByBMI(String bmi,String bodyType){
    return bmiCategoryDAO.fetchCategoryByBMI(bmi,bodyType);
  }
  
  
  
  
}
