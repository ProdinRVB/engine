package org.demo.shoelace.badge;

import org.demo.shoelace.SlComponent;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;

@NodeName("sl-badge")
public final class SlBadge extends SlComponent<SlBadge> implements HasControlText {

  /**
   * The badge's theme variant.
   */
  public enum Variant {
    PRIMARY("primary"),
    SUCCESS("success"),
    NEUTRAL("neutral"),
    WARNING("warning"),
    DANGER("danger");

    private final String value;

    private Variant(String value) {
      this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * @param value the value to set
     */
    @Override
    public String toString() {
      return value;
    }
  }

  // Properties
  private static PropertyDescriptor<String> VARIANT = PropertyDescriptor.property("variant",
      SlBadge.Variant.PRIMARY.getValue());
  private static PropertyDescriptor<Boolean> PILL = PropertyDescriptor.property("pill", false);
  private static PropertyDescriptor<Boolean> PULSE = PropertyDescriptor.property("pulse", false);
  private static PropertyDescriptor<Boolean> UPDATE_COMPLETE = PropertyDescriptor.property("updateComplete",
      false);

  /**
   * Creates a new badge
   */
  public SlBadge() {
    super();
  }

  /**
   * Creates a new badge with the given text.
   * 
   * @param text The badge's text.
   */
  public SlBadge(String text) {
    super();
    setText(text);
  }

  /**
   * Set the badge's theme variant.
   * 
   * @param variant The badge's theme variant.
   * @return the control
   * @see Variant
   */
  public SlBadge setVariant(Variant variant) {
    set(VARIANT, variant.getValue());
    return this;
  }

  /**
   * Get the badge's theme variant.
   * 
   * @return the badge's theme variant
   * @see Variant
   */
  public Variant getVariant() {
    return Variant.valueOf(get(VARIANT));
  }

  /**
   * Draws a pill-style badge with rounded edges.
   * 
   * @param pill Draws a pill-style badge with rounded edges.
   * @return the control
   */
  public SlBadge setPill(boolean pill) {
    set(PILL, pill);
    return this;
  }

  /**
   * Checks if the badge is pill-style.
   * 
   * @return Draws a pill-style badge with rounded edges.
   */
  public boolean isPill() {
    return get(PILL);
  }

  /**
   * Makes the badge pulsate to draw attention.
   * 
   * @param pulse Makes the badge pulsate to draw attention.
   * @return the control
   */
  public SlBadge setPulse(boolean pulse) {
    set(PULSE, pulse);
    return this;
  }

  /**
   * Checks if the badge is pulsating.
   * 
   * @return Makes the badge pulsate to draw attention.
   */
  public boolean isPulse() {
    return get(PULSE);
  }

  /**
   * A read-only promise that resolves when the component has finished updating.
   * 
   * @return A read-only promise that resolves when the component has finished
   *         updating.
   */
  public boolean isUpdateComplete() {
    return get(UPDATE_COMPLETE, true, Boolean.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlBadge setText(String text) {
    addRawSlot(text);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return getRawSlot();
  }
}
