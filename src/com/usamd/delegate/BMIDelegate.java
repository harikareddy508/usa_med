package com.usamd.delegate;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usamd.modelBean.BMICalViewBean;
import com.usamd.modelBean.BMICategoryBean;
import com.usamd.service.BMICategoryService;

// TODO: Auto-generated Javadoc
/**
 * The Class BMIDelegate.
 */
@Component
public class BMIDelegate {
  
  /** The bmi category service. */
  @Autowired
  private BMICategoryService bmiCategoryService;
  

  /**
   * Gets the BMI results.
   *
   * @param bmiCalViewBean the bmi cal view bean
   * @return the BMI results
   */
  public String getBMIResults (BMICalViewBean bmiCalViewBean) {
    double bmi = 0.0;
    if(bmiCalViewBean != null) {
      double weight = Double.valueOf(bmiCalViewBean.getWeight());
      int heightFeet = bmiCalViewBean.getHeightFeet() != null ? Integer.valueOf(bmiCalViewBean.getHeightFeet()):0;
      int heightInch = bmiCalViewBean.getHeightInch() != null ? Integer.valueOf(bmiCalViewBean.getHeightInch()):0;
      
      int height = (heightFeet * 12) + heightInch;
      
      bmi = (703 * weight)/ (Math.pow(height, 2));
    }
    DecimalFormat f = new DecimalFormat("##.00");  // this will helps you to always keeps in two decimal places
    return f.format(bmi);
  }
  
  /**
   * Fetch category by bmi.
   *
   * @param bmi the bmi
   * @param age the age
   * @return the BMI category bean
   */
  public BMICategoryBean fetchCategoryByBMI(String bmi,String age){
    String bodyType = "";
    if(Integer.parseInt(age)>18){
      bodyType="AD";
    }
    else{
      bodyType="KID";
    }
    return bmiCategoryService.fetchCategoryByBMI(bmi,bodyType);
  }
  
  
  
  /**
   * Adds the or update bmi category.
   *
   * @param bean the bean
   * @return the int
   */
  public int addOrUpdateBMICategory(BMICategoryBean bean){
    if(bmiCategoryService.isRangeExistForOtherType(bean)){
      return 1;
    }
    if(bmiCategoryService.addOrUpdateBMICategory(bean)){
      return 0;
    }
    return -1;
  }
  
  /**
   * Fetch by body type.
   *
   * @param bodyType the body type
   * @param category the category
   * @return the list
   */
  public List<BMICategoryBean> fetchByBodyType(String bodyType,String category){
    return bmiCategoryService.fetchByBodyType(bodyType,category);
  }
  
}
