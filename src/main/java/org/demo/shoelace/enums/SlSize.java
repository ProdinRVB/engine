package org.demo.shoelace.enums;

/**
 * The components available sizes
 * 
 * @author Hyyan Abo Fakher
 */
public enum SlSize {
  SMALL("small"),
  MEDIUM("medium"),
  LARGE("large");

  private final String value;

  SlSize(String value) {
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

  /**
   * @param value the value as string
   * @return the size
   */
  public static SlSize fromString(String value) {
    for (SlSize size : SlSize.values()) {
      if (size.value.equals(value)) {
        return size;
      }
    }
    return null;
  }
}