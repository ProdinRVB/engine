package org.demo.shoelace.components.select;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A Shoelace option.
 * 
 * @see <a href="https://shoelace.style/components/options">Shoelace Options</a>
 * @since 1.0.0
 */
public final class SlOption {
  private String text;
  private String value;
  private String prefix;
  private String suffix;
  private boolean disable = false;
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  /**
   * Create a new option.
   * 
   * @param text  the option text
   * @param value the option value
   */
  public SlOption(String text, String value) {
    this.setText(text);
    this.setValue(value);
  }

  /**
   * Create a new option.
   * 
   * @param text the option text
   */
  public SlOption(String value) {
    this(value, value);
  }

  /**
   * Set the option text.
   * 
   * @param text the text
   * @return the option
   */
  public SlOption setText(String text) {
    String oldValue = this.text;
    this.text = text;
    this.pcs.firePropertyChange("text", oldValue, text);
    return this;
  }

  /**
   * Get the option text.
   * 
   * @return the text
   */
  public String getText() {
    return this.text;
  }

  /**
   * Set the option value.
   * 
   * The option's value. When selected, the containing form control will receive
   * this value. The value must be unique from other options in the same group.
   * Values may not contain spaces, as spaces are used as delimiters when listing
   * multiple values.
   * 
   * @param value the value
   * @return the option
   */
  public SlOption setValue(String value) {
    String oldValue = this.value;
    this.value = value.replaceAll("\\s", "");
    this.pcs.firePropertyChange("value", oldValue, value);
    return this;
  }

  /**
   * Get the option value.
   * 
   * @return the value
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Set the option prefix.
   * 
   * The option's prefix. This is useful for adding icons or other elements to the
   * beginning of an option.
   * 
   * @param prefix the prefix
   * @return the option
   */
  public SlOption setPrefix(String prefix) {
    String oldValue = this.prefix;
    this.prefix = prefix;
    this.pcs.firePropertyChange("prefix", oldValue, prefix);
    return this;
  }

  /**
   * Get the option prefix.
   * 
   * @return the prefix
   */
  public String getPrefix() {
    return this.prefix;
  }

  /**
   * Set the option suffix.
   * 
   * The option's suffix. This is useful for adding icons or other elements to the
   * end of an option.
   * 
   * @param suffix the suffix
   * @return the option
   */
  public SlOption setSuffix(String suffix) {
    String oldValue = this.suffix;
    this.suffix = suffix;
    this.pcs.firePropertyChange("suffix", oldValue, suffix);
    return this;
  }

  /**
   * Get the option suffix.
   * 
   * @return the suffix
   */
  public String getSuffix() {
    return this.suffix;
  }

  /**
   * Set the option disable.
   * 
   * Disables the option.
   * 
   * @param disable the disable
   * @return the option
   */
  public SlOption setDisable(boolean disable) {
    boolean oldValue = this.disable;
    this.disable = disable;
    this.pcs.firePropertyChange("disable", oldValue, disable);
    return this;
  }

  /**
   * Get the option disable.
   * 
   * @return the disable
   */
  public boolean isDisabled() {
    return this.disable;
  }

  /**
   * Add a property change listener.
   * 
   * @param listener the listener
   * @return the breadcrumb item
   */
  @SuppressWarnings("unused")
  private SlOption addPropertyChangeListener(PropertyChangeListener listener) {
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
  private SlOption removePropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.removePropertyChangeListener(listener);
    return this;
  }
}
