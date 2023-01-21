package org.demo.shoelace.components.tabs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A Shoelace tab.
 * 
 * @see <a href="https://shoelace.style/components/tab">Shoelace Tab</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
public final class SlTab {
  private String id = "";
  private String text = "";
  private boolean closable = false;
  private boolean disable = false;
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  /**
   * Create a new tab.
   * 
   * @param id the id
   */
  public SlTab(String id) {
    this.setId(id);
    this.setText(id);
  }

  /**
   * Create a new tab.
   * 
   * @param id    the id
   * @param label the label
   */
  public SlTab(String id, String label) {
    this.setId(id);
    this.setText(label);
  }

  /**
   * Set the label.
   * 
   * @param label the label
   * @return the tab
   */
  public SlTab setText(String label) {
    String old = this.text;
    this.text = label;
    this.pcs.firePropertyChange("label", old, label);
    return this;
  }

  /**
   * Get the label.
   * 
   * @return the label
   */
  public String getText() {
    return this.text;
  }

  /**
   * Get the id.
   * 
   * @return the id
   */
  public String getId() {
    return this.id;
  }

  /**
   * Set the closable.
   * 
   * @param closeable the closeable
   * @return the tab
   */
  public SlTab setClosable(boolean closeable) {
    boolean old = this.closable;
    this.closable = closeable;
    this.pcs.firePropertyChange("closeable", old, closeable);
    return this;
  }

  /**
   * Get the closable.
   * 
   * @return the closable
   */
  public boolean isClosable() {
    return this.closable;
  }

  /**
   * Set the disable.
   * 
   * @param disable the disable
   * @return the tab
   */
  public SlTab setDisable(boolean disable) {
    boolean old = this.disable;
    this.disable = disable;
    this.pcs.firePropertyChange("disable", old, disable);
    return this;
  }

  /**
   * Get the disable.
   * 
   * @return the disable
   */
  public boolean isDisabled() {
    return this.disable;
  }

  /**
   * Set the id.
   * 
   * @param id the id
   * @return the tab
   */
  private SlTab setId(String id) {
    String old = this.id;
    this.id = id;
    this.pcs.firePropertyChange("id", old, id);
    return this;
  }

  /**
   * Add a property change listener.
   * 
   * @param listener the listener
   * @return the breadcrumb item
   */
  @SuppressWarnings("unused")
  private SlTab addPropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.addPropertyChangeListener(listener);
    return this;
  }

  /**
   * Remove a property change listener.
   * 
   * @param listener the listener
   * @return the breadcrumb item
   */
  @SuppressWarnings("unused")
  private SlTab removePropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.removePropertyChangeListener(listener);
    return this;
  }
}
