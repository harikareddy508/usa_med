
package com.usamd.modelBean;
// TODO: Auto-generated Javadoc
/**
 * The Class SectionUIBean.
 *
 * @author USAWebMD
 */
public class SectionUIBean extends AbstractBean {
  
  /** The section id. */
  private String sectionId;
  
  /** The caption. */
  private String caption;
  
  /** The actual url. */
  private String actualUrl;
  
  /** The url desc. */
  private String urlDesc;
  
  /** The image. */
  private byte[] image;
  
  /** The encoded image. */
  private String encodedImage;
  
 
  /**
   * Gets the image.
   *
   * @return the image
   */
  public byte[] getImage() {
    return image;
  }

  /**
   * Sets the image.
   *
   * @param image the image to set
   */
  public void setImage(byte[] image) {
    this.image = image;
  }

  /**
   * Sets the actual url.
   *
   * @param actualUrl the actualUrl to set
   */
  public void setActualUrl(String actualUrl) {
    this.actualUrl = actualUrl;
  }

  /**
   * Gets the section id.
   *
   * @return the section id
   */
  public String getSectionId() {
    return sectionId;
  }
  
  /**
   * Sets the section id.
   *
   * @param sectionId the new section id
   */
  public void setSectionId(String sectionId) {
    this.sectionId = sectionId;
  }
  
 
  
  /**
   * Gets the caption.
   *
   * @return the caption
   */
  public String getCaption() {
    return caption;
  }
  
  /**
   * Sets the caption.
   *
   * @param caption the new caption
   */
  public void setCaption(String caption) {
    this.caption = caption;
  }
  
  /**
   * Gets the actual url.
   *
   * @return the actual url
   */
  public String getActualUrl() {
    return actualUrl;
  }
  
  /**
   * Sets the actual url.
   *
   * @param actualUrl the new actual url
   */
  public void setActualURL(String actualUrl) {
    this.actualUrl = actualUrl;
  }

  /**
   * Gets the url desc.
   *
   * @return the urlDesc
   */
  public String getUrlDesc() {
    return urlDesc;
  }

  /**
   * Sets the url desc.
   *
   * @param urlDesc the urlDesc to set
   */
  public void setUrlDesc(String urlDesc) {
    this.urlDesc = urlDesc;
  }

  /**
   * Gets the encoded image.
   *
   * @return the encodedImage
   */
  public String getEncodedImage() {
    return encodedImage;
  }

  /**
   * Sets the encoded image.
   *
   * @param encodedImage the encodedImage to set
   */
  public void setEncodedImage(String encodedImage) {
    this.encodedImage = encodedImage;
  }


}
