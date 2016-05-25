/**
 * 
 */
package com.usamd.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usamd.dao.AddressDAO;
import com.usamd.modelBean.AddressBean;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressService.
 *
 * @author USAWebMD
 */
@Service
public class AddressService {

  /** The address dao. */
  @Autowired
  private AddressDAO addressDAO;


  /**
   * Adds the address.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean addAddress(AddressBean bean) {
    return addressDAO.addAddress(bean);
  }

  /**
   * Update address.
   *
   * @param bean the bean
   * @return true, if successful
   */
  public boolean updateAddress(AddressBean bean) {
    return addressDAO.updateAddress(bean);
  }

  /**
   * Fetch address.
   *
   * @param bean the bean
   * @return the list
   */
  public List<AddressBean> fetchAddress(AddressBean bean) {
    return addressDAO.fetchAddress(bean);
  }
  
  /**
   * Delete address.
   *
   * @param residentId the resident id
   * @return true, if successful
   */
  public boolean deleteAddress(String residentId) {
    return addressDAO.deleteAddress(residentId);
  }
  
  
  /**
   * Fetch address by zipcode.
   *
   * @param zipcode the zipcode
   * @param residentType the resident type
   * @return the list
   */
  public List<AddressBean> fetchAddressByZipcode(String zipcode,String residentType){
    return addressDAO.fetchAddressByZipcode(zipcode,residentType);
  }
  
  /**
   * Fetch address beween zipcodes.
   *
   * @param zipcode the zipcode
   * @return the list
   */
  public List<AddressBean> fetchAddressBeweenZipcodes(String zipcode){
    return addressDAO.fetchDocAddressBeweenZipcodes(zipcode);
  }
  
  /**
   * Search address by resident ids.
   *
   * @param residentIdSet the resident id set
   * @param residentType the resident type
   * @param zipcode the zipcode
   * @return the list
   */
  public List<AddressBean> searchAddressByResidentIds(Set<String> residentIdSet, String residentType,String zipcode) {
    String residentIds = "";
    int i = 0;
    int size = residentIdSet.size();
    for(String centerId : residentIdSet){
      if(i==size-1){
        residentIds = residentIds +"'"+centerId+"'";
        break;
      }
      residentIds = residentIds +"'"+centerId+"'"+",";
      i++;
    }
    return addressDAO.searchAddressByResidentIds(residentIds, residentType,zipcode);
  }
}
