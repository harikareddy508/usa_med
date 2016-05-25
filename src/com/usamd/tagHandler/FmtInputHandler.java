/**
 * 
 */
package com.usamd.tagHandler;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.usamd.delegate.ReferenceDataDelegate;
import com.usamd.modelBean.AddressBean;

// TODO: Auto-generated Javadoc
/**
 * The Class RefLabelHandler.
 */
public class FmtInputHandler extends SimpleTagSupport {
  // private static final String ATTR_TEMPLATE="%s='%s'";



  /** The code. */
  private AddressBean address;

  /** The reference table name. */
  private String fname;

  /** The element name. */
  private String lname;

  /** The from date. */
  private Date fromDate;



  /**
   * Gets the address.
   *
   * @return the address
   */
  public AddressBean getAddress() {
    return address;
  }



  /**
   * Sets the address.
   *
   * @param address the new address
   */
  public void setAddress(AddressBean address) {
    this.address = address;
  }



  /**
   * Gets the fname.
   *
   * @return the fname
   */
  public String getFname() {
    return fname;
  }



  /**
   * Sets the fname.
   *
   * @param fname the new fname
   */
  public void setFname(String fname) {
    this.fname = fname;
  }



  /**
   * Gets the lname.
   *
   * @return the lname
   */
  public String getLname() {
    return lname;
  }



  /**
   * Sets the lname.
   *
   * @param lname the new lname
   */
  public void setLname(String lname) {
    this.lname = lname;
  }



  /**
   * Gets the from date.
   *
   * @return the fromDate
   */
  public Date getFromDate() {
    return fromDate;
  }



  /**
   * Sets the from date.
   *
   * @param fromDate the fromDate to set
   */
  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }



  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
   */
  @Override
  public void doTag() throws IOException {
    String description = null;
    PageContext pageContext = (PageContext) getJspContext();
    JspWriter out = pageContext.getOut();
    if (address != null) {
      // Format address
      description = address.getAddrLine1()+" ";
      if (address.getAddrLine2() != null) {
        description = description + address.getAddrLine2() + "<br>";
      }
      if( address.getCity()!=null){
        description =
            description + address.getCity() +" ";
      }
      if(address.getState()!=null){
        description=description+ ReferenceDataDelegate.getDescriptionByCode(address.getState(), "RT_STATES")+" ";
      }
      if(address.getZipcode()!=null){
        description=description+ address.getZipcode();
      }
      
      /*description =
          description + address.getCity() + " "
              + ReferenceDataDelegate.getDescriptionByCode(address.getState(), "RT_STATES") + " "
              + address.getZipcode();*/

    } else if (fromDate != null) {
      Calendar cal = Calendar.getInstance();
      Calendar fromCal = Calendar.getInstance();
      fromCal.setTime(fromDate);
      float months =
          (cal.get(Calendar.YEAR) - fromCal.get(Calendar.YEAR)) * 12 + (cal.get(Calendar.MONTH) - fromCal
              .get(Calendar.MONTH));
      float exp = months / 12;
      DecimalFormat f = new DecimalFormat("##.0");  // this will helps you to always keeps in two decimal places
      description = f.format(exp) + " years ";
    } else {
      description = fname + " " + lname;
    }

    // html=description;
    out.println(description);
  }



}
