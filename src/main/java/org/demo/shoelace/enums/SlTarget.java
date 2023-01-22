package org.demo.shoelace.enums;

/**
 * Tells the browser where to open the link.
 */
public enum SlTarget {
  BLANK("_blank"),
  PARENT("_parent"),
  SELF("_self"),
  TOP("_top");

  private final String value;

  SlTarget(String value) {
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
   * @return the target
   */
  public static SlTarget fromString(String value) {
    for (SlTarget target : SlTarget.values()) {
      if (target.value.equals(value)) {
        return target;
      }
    }
    return null;
  }
}