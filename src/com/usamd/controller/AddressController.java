/**
 * 
 */
package com.usamd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usamd.constants.URLConstants;
import com.usamd.delegate.ReferenceDataDelegate;
import com.usamd.modelBean.RTStatesBean;


// TODO: Auto-generated Javadoc
/**
 * The Class AddressController.
 *
 * @author USAWebMD
 */
@Controller
public class AddressController extends AbstractController{ 
  
  
  /**
   * Fetch cities by state.
   *
   * @param stateCd the state cd
   * @return the list
   */
  @RequestMapping(value = URLConstants.URL_FETCH_STATES, method = RequestMethod.POST)
  public @ResponseBody  List<String> fetchCitiesByState(@RequestParam(required=true, value="stateCd") String stateCd) {
    List<String>   mycities = new ArrayList<String>  ();
    Map<String,List<RTStatesBean>> statesMap = ReferenceDataDelegate.getStates();
    if(!(stateCd==null || stateCd.isEmpty())){
      List<RTStatesBean> beanList = statesMap.get(stateCd);
      for(RTStatesBean bean : beanList){
        mycities.add(bean.getCity());
      }
    }   
  
    return mycities;
  }
  
}
