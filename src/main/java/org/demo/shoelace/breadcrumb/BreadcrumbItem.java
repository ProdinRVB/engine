package org.demo.shoelace.breadcrumb;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A breadcrumb item.
 * 
 * @author Hyyan Abo Fakher
 */
public final class BreadcrumbItem {

    /**
   * Tells the browser where to open the link.
   */
  public enum Target {
    BLANK("_blank"),
    PARENT("_parent"),
    SELF("_self"),
    TOP("_top");

    private final String value;

    Target(String value) {
      this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * @param value the value as string
     */
    @Override
    public String toString() {
      return value;
    }
  }

  private String text = "";
  private String href = "";
  private Target target = Target.SELF;
  private String rel = "noreferrer noopener";
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  /**
   * Create a new breadcrumb item.
   * 
   * @param text the text to display
   */
  public BreadcrumbItem(String text) {
    this.setText(text);
  }

  /**
   * Create a new breadcrumb item.
   * 
   * @param text the text to display
   * @param href the href to use
   */
  public BreadcrumbItem(String text, String href) {
    this.setText(text);
    this.setHref(href);
  }

  /**
   * Create a new breadcrumb item.
   * 
   * @param text the text to display
   * @param href the href to use
   * @param target the target to use
   */
  public BreadcrumbItem(String text, String href, Target target) {
    this.setText(text);
    this.setHref(href);
    this.setTarget(target);
  }

  /**
   * Create a new breadcrumb item.
   * 
   * @param text the text to display
   * @param href the href to use
   * @param target the target to use
   * @param rel the rel to use
   */
  public BreadcrumbItem(String text, String href, Target target, String rel) {
    this.setText(text);
    this.setHref(href);
    this.setTarget(target);
    this.setRel(rel);
  }

  /**
   * Set the text to display.
   * 
   * @param text the text
   * @return the breadcrumb item
   */
  public BreadcrumbItem setText(String text) {
    String oldValue = this.text;
    this.text = text;
    pcs.firePropertyChange("text", oldValue, text);

    return this;
  }

  /**
   * Get the text to display.
   * 
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Set the href to use.
   * 
   * Optional URL to direct the user to when the breadcrumb item is activated.
   * When set, a link will be rendered internally. When unset, a button will be
   * rendered instead.
   * 
   * @param href the href
   * @return the breadcrumb item
   */
  public BreadcrumbItem setHref(String href) {
    String oldValue = this.href;
    this.href = href;
    pcs.firePropertyChange("href", oldValue, href);

    return this;
  }

  /**
   * Get the href to use.
   * 
   * @return the href
   */
  public String getHref() {
    return href;
  }

  /**
   * Set the rel to use.
   * 
   * The rel attribute to use on the link. Only used when href is set.
   * 
   * @param rel the rel
   * @return the breadcrumb item
   */
  public BreadcrumbItem setRel(String rel) {
    String oldValue = this.rel;
    this.rel = rel;
    pcs.firePropertyChange("rel", oldValue, rel);

    return this;
  }

  /**
   * Get the rel to use.
   * 
   * @return the rel
   */
  public String getRel() {
    return rel;
  }

  /**
   * Set the target to use.
   * 
   * The target attribute to use on the link. Only used when href is set.
   * 
   * @param target the target
   * @return the breadcrumb item
   */
  public BreadcrumbItem setTarget(Target target) {
    Target oldValue = this.target;
    this.target = target;
    pcs.firePropertyChange("target", oldValue, target);

    return this;
  }

  /**
   * Get the target to use.
   * 
   * @return the target
   */
  public Target getTarget() {
    return target;
  }

  /**
   * Add a property change listener.
   * 
   * @param listener the listener
   * @return the breadcrumb item
   */
  public BreadcrumbItem addPropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.addPropertyChangeListener(listener);
    return this;
  }

  /**
   * Remove a property change listener.
   * 
   * @param listener the listener
   * @return the breadcrumb item
   */
  public BreadcrumbItem removePropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.removePropertyChangeListener(listener);
    return this;
  }
}
