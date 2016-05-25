/**
 * 
 */
package com.usamd.delegate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usamd.constants.GlobalConstants;
import com.usamd.modelBean.AddressBean;
import com.usamd.modelBean.DoctorBean;
import com.usamd.modelBean.HealthCenterBean;
import com.usamd.service.AddressService;
import com.usamd.service.DoctorService;
import com.usamd.service.HealthCenterService;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorDelegate.
 *
 * @author USAWebMD
 */
@Component
public class DoctorDelegate {

  /** The doctor service. */
  @Autowired
  private DoctorService doctorService;

  /** The health center service. */
  @Autowired
  private HealthCenterService healthCenterService;

  /** The address service. */
  @Autowired
  private AddressService addressService;

  /**
   * Adds the doctor details.
   *
   * @param doctorBean the doctor bean
   * @param createUser the create user
   * @return true, if successful
   */
  public boolean addDoctorDetails(DoctorBean doctorBean, String createUser) {
    boolean success = true;
    // First add health center if not already exist
    String centerId = doctorBean.getHealthCenter().getCenterId();
    doctorBean.setCreateUser(createUser);
    if (centerId == null || centerId.isEmpty()) {
      doctorBean.getHealthCenter().setCreateUser(createUser);
      doctorBean.getHealthCenter().getAddress().setCreateUser(createUser);
      success = healthCenterService.addHealthCenter(doctorBean.getHealthCenter());
      centerId = doctorBean.getHealthCenter().getCenterId();
      doctorBean.getHealthCenter().getAddress().setResidentId(centerId);
      doctorBean.getHealthCenter().getAddress()
      .setResidentType(GlobalConstants.RESIDENT_TYPE_CENTER);
      success = addressService.addAddress(doctorBean.getHealthCenter().getAddress());
    }
    // centerId got generated post insertion
    // Add doctor
    if (success) {
      doctorBean.setCenterId(centerId);
      success = doctorService.addDoctor(doctorBean);
    }
    return success;
  }

  /**
   * Update doctor.
   *
   * @param doctorBean the doctor bean
   * @return true, if successful
   */
  public boolean updateDoctor(DoctorBean doctorBean) {
    boolean success = true;
    // First add health center if not already exist
    DoctorBean searchDoc = new DoctorBean();
    searchDoc.setHealthCenter(doctorBean.getHealthCenter());
    List<DoctorBean> list = doctorService.searchDoctor(searchDoc);
    boolean addnewHC = false;

    // Check the list to verify if other doctors with same centerId exists or not.
    if (list != null && list.size() > 0) {
      if (list.size() > 1) {
        // other docs exist for this center, hence need to add a new health center.
        addnewHC = true;
      }
      // If it is the same doctor that we are updating for or not. If not same, we will add a new
      // one, else will update same.
      else if (!doctorBean.getDoctorId().equals(list.get(0).getDoctorId())) {
        addnewHC = true;
      }
    }
    if (addnewHC) {
      healthCenterService.addHealthCenter(doctorBean.getHealthCenter());
    } else {
      // update health center
      healthCenterService.updateHealthCenter(doctorBean.getHealthCenter());
    }
    // Update address
    AddressBean address = doctorBean.getHealthCenter().getAddress();
    if (address.getResidentId() != null && !address.getResidentId().isEmpty()) {
      address.setUpdateUser(doctorBean.getUpdateUser());
      addressService.updateAddress(address);
    } else {
      address.setResidentType(GlobalConstants.RESIDENT_TYPE_CENTER);
      address.setResidentId(doctorBean.getHealthCenter().getCenterId());
      address.setCreateUser(doctorBean.getUpdateUser());
      addressService.addAddress(address);
    }


    if (success) {
      success = doctorService.updateDoctor(doctorBean);
    }
    return success;
  }


  /**
   * Delete doctor.
   *
   * @param doctorId the doctor id
   * @return true, if successful
   */
  public boolean deleteDoctor(String doctorId) {
    boolean success = true;
    // Fetch existing doctor
    DoctorBean doctor = doctorService.fetchDoctoryById(doctorId);
    if (doctor == null) {
      return false;
    }
    // Fetch if there are other centers associated with this one.
    // If other health centers exist, dont delete center and address, else delete center and address
    Set<String> centerIdSet = new HashSet<>();
    centerIdSet.add(doctor.getCenterId());

    // Delete doctor
    success = doctorService.deleteDoctor(doctorId);

    /*List<HealthCenterBean> healthCenterList = null;
    healthCenterList = healthCenterService.searchHealthCenterByCenterIds(centerIdSet);
    if (healthCenterList != null && healthCenterList.size() <= 1) {
      // delete health center
      // first delete address
      success = addressService.deleteAddress(doctor.getCenterId());
      success = healthCenterService.deleteHealthCenter(doctor.getCenterId());
    }*/

    return success;
  }

  /**
   * Search doctor.
   *
   * @param bean the bean
   * @return the list
   */
  public List<DoctorBean> searchDoctor(DoctorBean bean) {
    List<DoctorBean> docList = null;
    List<AddressBean> addressList = null;
    /*
     * List<HealthCenterBean> healthCenterList = null; Set<String> centerIdSet = new HashSet<>();
     */
    // Fetch all health centers which are located in the given Zip-code
    String zipcode = bean.getHealthCenter().getAddress().getZipcode();
    System.out.println("Zip code  :: " + zipcode);
    if (zipcode != null) {
      addressList =
          addressService.fetchAddressByZipcode(zipcode, GlobalConstants.RESIDENT_TYPE_CENTER);
    }
    // Fetch by specialization
    docList = fetchDoctors(bean.getSpecialization(), addressList);
    return docList;
  }


  /**
   * Locate doctor.
   *
   * @param zipcode the zipcode
   * @param specialization the specialization
   * @return the list
   */
  public List<DoctorBean> locateDoctor(String zipcode, String specialization) {
    List<DoctorBean> docList = null;
    List<AddressBean> addressList = null;
    if (zipcode != null) {
      addressList = addressService.fetchAddressBeweenZipcodes(zipcode);
      if(addressList==null | addressList.size()<=0){
        return docList;
      }
    }
    docList = fetchDoctors(specialization, addressList);
    return docList;
  }

  /**
   * Fetch doctors.
   *
   * @param specialization the specialization
   * @param addressList the address list
   * @return the list
   */
  private List<DoctorBean> fetchDoctors(String specialization, List<AddressBean> addressList) {
    List<DoctorBean> docList = null;
    List<HealthCenterBean> healthCenterList = null;
    Set<String> centerIdSet = new HashSet<>();
    if (addressList != null && !addressList.isEmpty()) {
      for (AddressBean addr : addressList) {
        centerIdSet.add(addr.getResidentId());
      }
    }
    docList = doctorService.fetchDoctoryByCenterIds(centerIdSet, specialization);
    if (docList == null || docList.isEmpty()) {
      System.out.println("No Doc results found");
      return null; // No results found
    }
    if (centerIdSet == null || centerIdSet.isEmpty()) {
      // It means data has been fetched by specialization only
      for (DoctorBean doc : docList) {
        centerIdSet.add(doc.getCenterId());
      }
    }
    // fetch all health Centers
    healthCenterList = healthCenterService.searchHealthCenterByCenterIds(centerIdSet);


    // set addresses in doctor Bean
    docList = populateMyDoctorBeans(docList, addressList, healthCenterList);

    // Fetch by specialization
    return docList;
  }

  /**
   * Populate my doctor beans.
   *
   * @param docList the doc list
   * @param addressList the address list
   * @param healthCenterList the health center list
   * @return the list
   */
  private List<DoctorBean> populateMyDoctorBeans(List<DoctorBean> docList,
      List<AddressBean> addressList, List<HealthCenterBean> healthCenterList) {
    // Map address and healthCenter to doctor
    for (DoctorBean doctor : docList) {
      if (healthCenterList != null) {
        for (HealthCenterBean hc : healthCenterList) {
          if (addressList != null) {
            for (AddressBean address : addressList) {
              if (hc.getCenterId().equals(address.getResidentId())) {
                hc.setAddress(address);
                break;
              }
            }
          }
          if (hc.getCenterId().equals(doctor.getCenterId())) {
            doctor.setHealthCenter(hc);
            break;
          }
        }
      }
    }
    return docList;
  }


  /**
   * Fetch health centers.
   *
   * @param specialization the specialization
   * @param zipcode the zipcode
   * @return the list
   */
  public List<HealthCenterBean> fetchHealthCenters(String specialization, String zipcode) {
    List<DoctorBean> docList = null;
    List<AddressBean> addressList = null;
    List<HealthCenterBean> healthCenterList = null;
    Set<String> centerIdSet = new HashSet<>();

    if (specialization != null && !specialization.isEmpty()) {
      docList = doctorService.fetchDoctoryByCenterIds(centerIdSet, specialization);
      // prepare centerIds
      if (centerIdSet == null || centerIdSet.isEmpty()) {
        // It means data has been fetched by specialization only
        for (DoctorBean doc : docList) {
          centerIdSet.add(doc.getCenterId());
        }
      }
      // Fetch address of all doctors based on center Ids
      healthCenterList = healthCenterService.searchHealthCenterByCenterIds(centerIdSet);
      addressList =
          addressService.searchAddressByResidentIds(centerIdSet,
              GlobalConstants.RESIDENT_TYPE_CENTER, zipcode);
      for (HealthCenterBean hc : healthCenterList) {
        if (docList != null) {
          List<DoctorBean> hcDoc = new ArrayList<DoctorBean>();
          for (DoctorBean doc : docList) {
            if (doc.getCenterId().equals(hc.getCenterId())) {
              hcDoc.add(doc);
            }
          }
          hc.setDoctorsList(hcDoc);
          if (addressList != null && !addressList.isEmpty()) {
            for (AddressBean addr : addressList) {
              if (addr.getResidentId().equals(hc.getCenterId())) {
                hc.setAddress(addr);
                break;
              }
            }
          }
        }
      }

    } else {
      addressList = addressService.fetchAddressBeweenZipcodes(zipcode);
      if (addressList != null && !addressList.isEmpty()) {
        for (AddressBean addr : addressList) {
          centerIdSet.add(addr.getResidentId());
        }
      }
      docList = doctorService.fetchDoctoryByCenterIds(centerIdSet, specialization);
      // fetch all health Centers
      healthCenterList = healthCenterService.searchHealthCenterByCenterIds(centerIdSet);
      if (healthCenterList != null && !healthCenterList.isEmpty()) {
        for (HealthCenterBean hc : healthCenterList) {
          if (docList != null) {
            List<DoctorBean> hcDoc = new ArrayList<DoctorBean>();
            for (DoctorBean doc : docList) {
              if (doc.getCenterId().equals(hc.getCenterId())) {
                hcDoc.add(doc);
              }
            }
            hc.setDoctorsList(hcDoc);
          }
          if (addressList != null && !addressList.isEmpty()) {
            for (AddressBean addr : addressList) {
              if (addr.getResidentId().equals(hc.getCenterId())) {
                hc.setAddress(addr);
                break;
              }
            }
          }
        }

      }
    }


    return healthCenterList;
  }


  /**
   * Fetch existing health centers.
   *
   * @return the list
   */
  public List<HealthCenterBean> fetchExistingHealthCenters() {
    return healthCenterService.searchHealthCenterByCenterIds(null);
  }



}
