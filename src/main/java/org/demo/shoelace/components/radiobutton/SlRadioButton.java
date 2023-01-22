package org.demo.shoelace.components.radiobutton;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.radiobutton.events.SlRadioButtonBlurEvent;
import org.demo.shoelace.components.radiobutton.events.SlRadioButtonFocusEvent;
import org.demo.shoelace.enums.SlSize;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * A radio component.
 * 
 * @see <a href="https://shoelace.style/components/radio-button">Shoelace -
 *      Radio Button</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-radio-button")
@InlineStyleSheet(id = "sl-radio-button-styles", once = true, value = "" +
    "[sl-radio-button] [slot='prefix']," +
    "[sl-radio-button] [slot='suffix']{" +
    " display: flex" +
    "}")
public final class SlRadioButton extends SlComponent implements HasControlText {

  // Properties
  private final PropertyDescriptor<String> VALUE = PropertyDescriptor.property("value", "");
  private final PropertyDescriptor<String> SIZE = PropertyDescriptor.property("size", SlSize.MEDIUM.getValue());
  private final PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);
  private final PropertyDescriptor<Boolean> PILL = PropertyDescriptor.property("pill", false);

  /**
   * Create a new radio button component.
   */
  public SlRadioButton() {
    super();
  }

  /**
   * Create a new radio button component with the given value.
   * 
   * @param text the radio's text
   */
  public SlRadioButton(String text) {
    super();
    setText(text);
  }

  /**
   * Create a new radio button component with the given value.
   * 
   * @param text  the radio's text
   * @param value the radio's value
   */
  public SlRadioButton(String text, String value) {
    super();
    setText(text);
    setValue(value);
  }

  /**
   * Set the radio's value. When selected, the radio group will receive this
   * value.
   * 
   * @param value the radio's value
   * @return the radio button
   */
  public SlRadioButton setValue(String value) {
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
   * @param size the radio's size
   * @return the radio button
   */
  public SlRadioButton setSize(SlSize size) {
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
   * Set the radio button's disabled state.
   * 
   * @param disabled the radio button's disabled state
   * @return the radio button
   */
  public SlRadioButton setDisabled(boolean disabled) {
    set(DISABLED, disabled);
    return this;
  }

  /**
   * Get the radio button's disabled state.
   * 
   * @return the radio button's disabled state
   */
  public boolean isDisabled() {
    return get(DISABLED, true, Boolean.class);
  }

  /**
   * Set the radio button's pill state.
   * 
   * @param pill the radio button's pill state
   * @return the radio button
   */
  public SlRadioButton setPill(boolean pill) {
    set(PILL, pill);
    return this;
  }

  /**
   * Get the radio button's pill state.
   * 
   * @return the radio button's pill state
   */
  public boolean isPill() {
    return get(PILL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlRadioButton setText(String text) {
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
   * Set the prefix of the button.
   * 
   * @param prefix the prefix
   * @return the radio button
   */
  public SlRadioButton setPrefix(String prefix) {
    addRawSlot("prefix", prefix);
    return this;
  }

  /**
   * Get the prefix of the button.
   * 
   * @return the prefix
   */
  public String getPrefix() {
    return getRawSlot("prefix");
  }

  /**
   * Set the suffix of the button.
   * 
   * @param suffix the suffix
   * @return the radio button
   */
  public SlRadioButton setSuffix(String suffix) {
    addRawSlot("suffix", suffix);
    return this;
  }

  /**
   * Get the suffix of the button.
   * 
   * @return the suffix
   */
  public String getSuffix() {
    return getRawSlot("suffix");
  }

  /**
   * Add a focus listener to the radio button.
   * 
   * @param listener the listener
   * @return the radio button
   */
  public SlRadioButton addFocusListener(EventListener<SlRadioButtonFocusEvent> listener) {
    addEventListener(SlRadioButtonFocusEvent.class, listener);
    return this;
  }

  /**
   * Remove a focus listener from the radio button.
   * 
   * @param listener the listener
   * @return the radio button
   */
  public SlRadioButton removeFocusListener(EventListener<SlRadioButtonFocusEvent> listener) {
    removeEventListener(SlRadioButtonFocusEvent.class, listener);
    return this;
  }

  /**
   * Add a blur listener to the radio button.
   * 
   * @param listener the listener
   * @return the radio button
   */
  public SlRadioButton addBlurListener(EventListener<SlRadioButtonBlurEvent> listener) {
    addEventListener(SlRadioButtonBlurEvent.class, listener);
    return this;
  }

  /**
   * Remove a blur listener from the radio button.
   * 
   * @param listener the listener
   * @return the radio button
   */
  public SlRadioButton removeBlurListener(EventListener<SlRadioButtonBlurEvent> listener) {
    removeEventListener(SlRadioButtonBlurEvent.class, listener);
    return this;
  }
}
