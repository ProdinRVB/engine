package org.demo.ui5.button;

import org.demo.ui5.button.events.Ui5ButtonClickEvent;
import org.demo.shoelace.components.SlComponent;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

@NodeName("ui5-button")
public final class Ui5Button extends SlComponent implements HasControlText {

  /**
   * The button's Design
   */
  public enum Design {
    DEFAULT("Default"),
    EMPHASIZED("Emphasized"),
    POSITIVE("Positive"),
    NEGATIVE("Negative"),
    TRANSPARENT("Transparent"),
    ATTENTION("Attention");

    private final String value;

    Design(String value) {
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
     * @return the variant
     */
    public static Design fromString(String value) {
      for (Design variant : Design.values()) {
        if (variant.value.equals(value)) {
          return variant;
        }
      }
      return null;
    }
  }

  // Properties
  private final PropertyDescriptor<String> DESIGN = PropertyDescriptor.property("design", Design.DEFAULT.getValue());
  private final PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);
  private final PropertyDescriptor<String> TOOLTIP = PropertyDescriptor.property("tooltip", "");

  public Ui5Button() {
    super();
  }

  public Ui5Button(String text) {
    super();
    setText(text);
  }

  /**
   * Set the text of the button.
   * 
   * @param text the text
   * @return the button
   */
  public Ui5Button setText(String text) {
    addRawSlot(text);
    return this;
  }

  /**
   * Get the text of the button.
   * 
   * @return the text
   */
  public String getText() {
    return getRawSlot();
  }

  /**
   * Set the design of the button.
   * 
   * @param design the design
   * @return the button
   */
  public Ui5Button setDesign(Design design) {
    set(DESIGN, design.getValue());
    return this;
  }

  /**
   * Get the design of the button.
   * 
   * @return the design
   */
  public Design getDesign() {
    return Design.fromString(get(DESIGN));
  }

  /**
   * Set the disabled state of the button.
   * 
   * @param disabled the disabled state
   * @return the button
   */
  public Ui5Button setDisabled(boolean disabled) {
    set(DISABLED, disabled);
    return this;
  }

  /**
   * Get the disabled state of the button.
   * 
   * @return the disabled state
   */
  public boolean isDisabled() {
    return get(DISABLED, true, Boolean.class);
  }

  /**
   * Set the tooltip of the button.
   * 
   * @param tooltip the tooltip
   * @return the button
   */
  public Ui5Button setTooltip(String tooltip) {
    set(TOOLTIP, tooltip);
    return this;
  }

  /**
   * Get the tooltip of the button.
   * 
   * @return the tooltip
   */
  public String getTooltip() {
    return get(TOOLTIP);
  }

  /**
   * Add a click listener to the button.
   * 
   * @param listener the listener
   * @return the button
   */
  public Ui5Button addClickListener(EventListener<Ui5ButtonClickEvent> listener) {
    addEventListener(Ui5ButtonClickEvent.class, listener);
    return this;
  }

  /**
   * Remove a click listener from the button.
   * 
   * @param listener the listener
   * @return the button
   */
  public Ui5Button removeClickListener(EventListener<Ui5ButtonClickEvent> listener) {
    removeEventListener(Ui5ButtonClickEvent.class, listener);
    return this;
  }

  /**
   * Click the button.
   * 
   * @return the button
   */
  public Ui5Button click() {
    callAsyncFunction("click");
    return this;
  }

}
