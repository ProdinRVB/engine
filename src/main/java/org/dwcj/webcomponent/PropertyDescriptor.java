package org.dwcj.webcomponent;

/**
 * A property descriptor for a web component property. This class is used to
 * define the properties of a web component.
 * 
 * @author Hyyan Abo Fakher
 *
 * @param <T> the type of the property
 */
public class PropertyDescriptor<T> {
  private final String name;
  private final T defaultValue;
  private final boolean isAttribute;

  /**
   * Create a new property descriptor
   * 
   * @param name         the name of the property
   * @param defaultValue the default value of the property
   * @param isAttribute  true if the property is an attribute
   */
  public PropertyDescriptor(String name, T defaultValue, boolean isAttribute) {
    super();
    this.name = name;
    this.defaultValue = defaultValue;
    this.isAttribute = isAttribute;
  }

  /**
   * Get the name of the property
   * 
   * @return the name of the property
   */
  public String getName() {
    return name;
  }

  /**
   * Get the default value of the property
   * 
   * @return the default value of the property
   */
  public T getDefaultValue() {
    return defaultValue;
  }

  /**
   * Check if the property is an attribute
   * 
   * @return true if the property is an attribute
   */
  public boolean isAttribute() {
    return isAttribute;
  }

  /**
   * Check if the property is a property
   * 
   * @return true if the property is a property
   */
  public boolean isProperty() {
    return !isAttribute;
  }

  /**
   * Create a new property descriptor
   * 
   * @param name         the name of the property
   * @param defaultValue the default value of the property
   * @return the property descriptor
   */
  public static <T> PropertyDescriptor<T> property(String name, T defaultValue) {
    return new PropertyDescriptor<>(name, defaultValue, false);
  }

  /**
   * Create a new attribute descriptor
   * 
   * @param name         the name of the attribute
   * @param defaultValue the default value of the attribute
   * @return the attribute descriptor
   */
  public static <T> PropertyDescriptor<T> attribute(String name, T defaultValue) {
    return new PropertyDescriptor<>(name, defaultValue, true);
  }
}