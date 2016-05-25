/**
 * 
 */
package com.usamd.tagHandler;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.usamd.delegate.ReferenceDataDelegate;
// TODO: Auto-generated Javadoc
/**
 * The Class RefLabelHandler.
 */
public class RefLabelHandler extends SimpleTagSupport {
  // private static final String ATTR_TEMPLATE="%s='%s'";



  /** The code. */
  private String code;

  /** The reference table name. */
  private String referenceTableName;

  /** The element name. */
  private String elementName;

  /**
   * Gets the code.
   *
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the code.
   *
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Gets the reference table name.
   *
   * @return the referenceTableName
   */
  public String getReferenceTableName() {
    return referenceTableName;
  }

  /**
   * Sets the reference table name.
   *
   * @param referenceTableName the referenceTableName to set
   */
  public void setReferenceTableName(String referenceTableName) {
    this.referenceTableName = referenceTableName;
  }

  /**
   * Gets the element name.
   *
   * @return the element name
   */
  public String getElementName() {
    return elementName;
  }

  /**
   * Sets the element name.
   *
   * @param elementName the new element name
   */
  public void setElementName(String elementName) {
    this.elementName = elementName;
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

    // String html = "";
    if (this.code == null || this.code.isEmpty() || "NA".equalsIgnoreCase(this.code)) {
      description = "";
    } else {
      description = ReferenceDataDelegate.getDescriptionByCode(this.code, this.referenceTableName);
    }


    // html=description;
    out.println(description);
  }



}
