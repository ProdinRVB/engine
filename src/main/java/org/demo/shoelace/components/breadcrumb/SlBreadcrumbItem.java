package org.demo.shoelace.components.breadcrumb;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.demo.shoelace.enums.SlTarget;

/**
 * A breadcrumb item.
 * 
 * @author Hyyan Abo Fakher
 */
public final class SlBreadcrumbItem {

  private String text = "";
  private String href = "";
  private SlTarget target = SlTarget.SELF;
  private String rel = "noreferrer noopener";
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);

  /**
   * Create a new breadcrumb item.
   * 
   * @param text the text to display
   */
  public SlBreadcrumbItem(String text) {
    this.setText(text);
  }

  /**
   * Create a new breadcrumb item.
   * 
   * @param text the text to display
   * @param href the href to use
   */
  public SlBreadcrumbItem(String text, String href) {
    this.setText(text);
    this.setHref(href);
  }

  /**
   * Create a new breadcrumb item.
   * 
   * @param text   the text to display
   * @param href   the href to use
   * @param target the target to use
   */
  public SlBreadcrumbItem(String text, String href, SlTarget target) {
    this.setText(text);
    this.setHref(href);
    this.setTarget(target);
  }

  /**
   * Create a new breadcrumb item.
   * 
   * @param text   the text to display
   * @param href   the href to use
   * @param target the target to use
   * @param rel    the rel to use
   */
  public SlBreadcrumbItem(String text, String href, SlTarget target, String rel) {
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
  public SlBreadcrumbItem setText(String text) {
    String oldValue = this.text;
    this.text = text;
    support.firePropertyChange("text", oldValue, text);

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
  public SlBreadcrumbItem setHref(String href) {
    String oldValue = this.href;
    this.href = href;
    support.firePropertyChange("href", oldValue, href);

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
  public SlBreadcrumbItem setRel(String rel) {
    String oldValue = this.rel;
    this.rel = rel;
    support.firePropertyChange("rel", oldValue, rel);

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
  public SlBreadcrumbItem setTarget(SlTarget target) {
    SlTarget oldValue = this.target;
    this.target = target;
    support.firePropertyChange("target", oldValue, target);

    return this;
  }

  /**
   * Get the target to use.
   * 
   * @return the target
   */
  public SlTarget getTarget() {
    return target;
  }

  /**
   * Add a property change listener.
   * 
   * @param listener the listener
   * @return the breadcrumb item
   */
  @SuppressWarnings("unused")
  private SlBreadcrumbItem addPropertyChangeListener(PropertyChangeListener listener) {
    this.support.addPropertyChangeListener(listener);
    return this;
  }

  /**
   * Remove a property change listener.
   * 
   * @param listener the listener
   * @return the breadcrumb item
   */
  @SuppressWarnings("unused")
  private SlBreadcrumbItem removePropertyChangeListener(PropertyChangeListener listener) {
    this.support.removePropertyChangeListener(listener);
    return this;
  }
}
