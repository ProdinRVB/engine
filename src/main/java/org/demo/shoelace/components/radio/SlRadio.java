package org.demo.shoelace.components.radio;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.radio.events.SlRadioBlurEvent;
import org.demo.shoelace.components.radio.events.SlRadioFocusEvent;
import org.demo.shoelace.enums.SlSize;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * A radio component.
 * 
 * @see <a href="https://shoelace.style/components/radio">Shoelace - Radio</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-radio")
public final class SlRadio extends SlComponent implements HasControlText {

  // Properties
  private static PropertyDescriptor<String> VALUE = PropertyDescriptor.property("value", "");
  private static PropertyDescriptor<String> SIZE = PropertyDescriptor.property("size", SlSize.MEDIUM.getValue());
  private static PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);

  /**
   * Create a new radio component.
   */
  public SlRadio() {
    super();
  }

  /**
   * Create a new radio component with the given value.
   * 
   * @param text the radio's text
   */
  public SlRadio(String text) {
    super();
    setText(text);
  }

  /**
   * Create a new radio component with the given value.
   * 
   * @param text  the radio's text
   * @param value the radio's value
   */
  public SlRadio(String text, String value) {
    super();
    setText(text);
    setValue(value);
  }

  /**
   * Set the radio's value. When selected, the radio group will receive this
   * value.
   * 
   * @param value
   * @return the radio
   */
  public SlRadio setValue(String value) {
    set(VALUE, value);
    return this;
  }

  /**
   * Get the radio's value. When selected, the radio group will receive this
   * value.
   * 
   * @return the radio's value
   */
  public String getValue() {
    return get(VALUE);
  }

  /**
   * Set the radio's size.
   * 
   * @param size the size
   * @return the radio
   */
  public SlRadio setSize(SlSize size) {
    set(SIZE, size.getValue());
    return this;
  }

  /**
   * Get the radio's size.
   * 
   * @return the radio's size
   */
  public SlSize getSize() {
    return SlSize.valueOf(get(SIZE));
  }

  /**
   * Set whether the radio is disabled.
   * 
   * @param disabled whether the radio is disabled
   * @return the radio
   */
  public SlRadio setDisabled(boolean disabled) {
    set(DISABLED, disabled);
    return this;
  }

  /**
   * Get whether the radio is disabled.
   * 
   * @return whether the radio is disabled
   */
  public boolean isDisabled() {
    return get(DISABLED, true, Boolean.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlRadio setText(String text) {
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

  /**
   * Add a focus listener to the radio.
   * 
   * @param listener the listener
   * @return the radio
   */
  public SlRadio addFocusListener(EventListener<SlRadioFocusEvent> listener) {
    addEventListener(SlRadioFocusEvent.class, listener);
    return this;
  }

  /**
   * Remove a focus listener from the radio.
   * 
   * @param listener The listener to remove.
   * @return the checkbox
   */
  public SlRadio removeFocusListener(EventListener<SlRadioFocusEvent> listener) {
    removeEventListener(SlRadioFocusEvent.class, listener);
    return this;
  }

  /**
   * Add a blur listener to the radio.
   * 
   * @param listener The listener to add.
   * @return the checkbox
   */
  public SlRadio addBlurListener(EventListener<SlRadioBlurEvent> listener) {
    addEventListener(SlRadioBlurEvent.class, listener);
    return this;
  }

  /**
   * Remove a blur listener from the radio.
   * 
   * @param listener The listener to remove.
   * @return the checkbox
   */
  public SlRadio removeBlurListener(EventListener<SlRadioBlurEvent> listener) {
    removeEventListener(SlRadioBlurEvent.class, listener);
    return this;
  }
}
